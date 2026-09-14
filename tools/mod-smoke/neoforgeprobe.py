"""Joins as a NeoForge-branded client and reports the modded payloads the server sends.

A NeoForge client on a modded connection loads its server-type mod configs from the
server alone: a mod reading one that never arrived throws "Cannot get config value before
config is loaded" as the player joins, which reads as a client crash with no server-side
trace. This probe stands in for that client far enough to see which
`neoforge:config_file` payloads arrive.

    python3 tools/mod-smoke/neoforgeprobe.py [port]
"""

import socket
import struct
import sys
import zlib

HOST = "127.0.0.1"
PORT = int(sys.argv[1]) if len(sys.argv) > 1 else 25599
PROTO = 776
NAME = "NeoProbe"

CONFIG_CUSTOM_PAYLOAD_CLIENTBOUND = 1
CONFIG_CUSTOM_PAYLOAD_SERVERBOUND = 2
CONFIG_FINISH_CLIENTBOUND = 3
CONFIG_KNOWN_PACKS_CLIENTBOUND = 14
CONFIG_KNOWN_PACKS_SERVERBOUND = 7

COMPRESSION = -1


def varint(n):
    out = b""
    n &= 0xFFFFFFFF
    while True:
        b = n & 0x7F
        n >>= 7
        out += bytes([b | (0x80 if n else 0)])
        if not n:
            return out


def read_varint_sock(sock):
    n = shift = 0
    while True:
        b = sock.recv(1)
        if not b:
            raise EOFError("closed")
        n |= (b[0] & 0x7F) << shift
        if not b[0] & 0x80:
            return n
        shift += 7


def read_varint(buf, pos):
    n = shift = 0
    while True:
        b = buf[pos]
        pos += 1
        n |= (b & 0x7F) << shift
        if not b & 0x80:
            return n, pos
        shift += 7


def read_string(buf, pos):
    length, pos = read_varint(buf, pos)
    return buf[pos : pos + length].decode("utf-8", "replace"), pos + length


def mcstring(s):
    b = s.encode()
    return varint(len(b)) + b


def send(sock, pid, payload):
    body = varint(pid) + payload
    if COMPRESSION < 0:
        sock.sendall(varint(len(body)) + body)
        return
    framed = varint(0) + body
    sock.sendall(varint(len(framed)) + framed)


def read_packet(sock):
    length = read_varint_sock(sock)
    buf = b""
    while len(buf) < length:
        chunk = sock.recv(length - len(buf))
        if not chunk:
            raise EOFError("closed mid-packet")
        buf += chunk
    if COMPRESSION >= 0:
        uncompressed, pos = read_varint(buf, 0)
        buf = zlib.decompress(buf[pos:]) if uncompressed else buf[pos:]
    pid, pos = read_varint(buf, 0)
    return pid, buf[pos:]


sock = socket.create_connection((HOST, PORT), timeout=10)
send(sock, 0x00, varint(PROTO) + mcstring(HOST) + struct.pack(">H", PORT) + varint(2))
send(sock, 0x00, mcstring(NAME) + bytes(16))

state = "login"
configs = []
channels = []
while True:
    try:
        pid, body = read_packet(sock)
    except (TimeoutError, EOFError) as err:
        # Report what did arrive: the server may be waiting on a reply this stand-in
        # does not give, which is itself worth seeing.
        print(f"PROBE: stopped reading ({err})")
        break
    if state == "login":
        if pid == 3:
            COMPRESSION, _ = read_varint(body, 0)
            continue
        if pid == 2:  # login_finished
            send(sock, 3, b"")  # login_acknowledged
            # The brand is what tells the server this is a NeoForge client.
            send(
                sock,
                CONFIG_CUSTOM_PAYLOAD_SERVERBOUND,
                mcstring("minecraft:brand") + mcstring("neoforge"),
            )
            info = (
                mcstring("en_us")
                + struct.pack("b", 2)
                + varint(0)
                + b"\x01"
                + b"\x00"
                + varint(1)
                + b"\x00"
                + b"\x01"
            )
            send(sock, 0, info)
            state = "config"
        continue

    if pid == CONFIG_KNOWN_PACKS_CLIENTBOUND:
        send(sock, CONFIG_KNOWN_PACKS_SERVERBOUND, body)
        continue
    if pid == CONFIG_CUSTOM_PAYLOAD_CLIENTBOUND:
        channel, pos = read_string(body, 0)
        channels.append(channel)
        if channel == "neoforge:frozen_registry_sync_completed":
            # The server waits on this echo before finishing configuration.
            send(sock, CONFIG_CUSTOM_PAYLOAD_SERVERBOUND, mcstring(channel))
        if channel == "neoforge:config_file":
            name, pos = read_string(body, pos)
            size, pos = read_varint(body, pos)
            configs.append((name, size))
        continue
    if pid == CONFIG_FINISH_CLIENTBOUND:
        break

print(f"PROBE: {len(channels)} modded payload(s) in configuration")
for channel in dict.fromkeys(channels):
    print(f"    {channel} x{channels.count(channel)}")
print(f"PROBE: {len(configs)} config file(s) sent")
for name, size in configs:
    print(f"    {name} ({size} byte(s))")
sys.exit(0 if configs else 1)
