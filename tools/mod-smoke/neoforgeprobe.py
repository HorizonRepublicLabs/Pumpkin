"""Joins as a NeoForge client and reports the modded payloads the server sends.

It answers the server's `neoforge:register` query the way a stock NeoForge client does --
offering NeoForge's optional built-ins and no mod channels -- and answers the common
`c:version` / `c:register` exchange. Against a server with mods whose channels are required,
that answer is refused: the probe then reports the refused channels instead.

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
from smoke_env import PROTO
NAME = "NeoProbe"

CONFIG_CUSTOM_PAYLOAD_CLIENTBOUND = 1
CONFIG_CUSTOM_PAYLOAD_SERVERBOUND = 2
CONFIG_FINISH_CLIENTBOUND = 3
CONFIG_DISCONNECT_CLIENTBOUND = 2
CONFIG_KNOWN_PACKS_CLIENTBOUND = 15
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
    # At or over the threshold the server insists on a compressed frame.
    if len(body) >= COMPRESSION:
        framed = varint(len(body)) + zlib.compress(body)
    else:
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

# A stock client's NeoForge built-ins, all optional: (phase ordinal, id, flow or None).
# Phase ordinals are vanilla ConnectionProtocol's: PLAY=1, CONFIGURATION=4. Flow: CLIENTBOUND=1.
BUILTINS = [
    (4, "neoforge:config_file", 1),
    (4, "neoforge:frozen_registry_sync_start", 1),
    (4, "neoforge:frozen_registry", 1),
    (4, "neoforge:frozen_registry_sync_completed", None),
    (4, "neoforge:feature_flags", 1),
    (1, "neoforge:advanced_open_screen", 1),
    (1, "neoforge:aux_light_data", 1),
]


def query_answer():
    """ModdedNetworkQueryPayload: phase -> [id, version, optional flow, optional]."""
    out = varint(2)
    for phase in (4, 1):
        entries = [b for b in BUILTINS if b[0] == phase]
        out += varint(phase) + varint(len(entries))
        for _, cid, flow in entries:
            out += mcstring(cid) + mcstring("1")
            out += (b"\x01" + varint(flow)) if flow is not None else b"\x00"
            out += b"\x01"  # optional
    return out


def custom_payload(channel, data):
    send(sock, CONFIG_CUSTOM_PAYLOAD_SERVERBOUND, mcstring(channel) + data)


state = "login"
configs = []
channels = []
refused = []
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
        if channel == "neoforge:register":
            custom_payload(channel, query_answer())
        if channel == "c:version":
            custom_payload(channel, varint(1) + varint(1))
        if channel == "c:register":
            custom_payload(channel, varint(1) + mcstring("play") + varint(0))
        if channel == "neoforge:modded_network_setup_failed":
            count, pos = read_varint(body, pos)
            for _ in range(count):
                cid, pos = read_string(body, pos)
                refused.append(cid)
                break  # the reason that follows is NBT; the first id is enough to report
        if channel == "neoforge:frozen_registry_sync_completed":
            # The server waits on this echo before finishing configuration.
            send(sock, CONFIG_CUSTOM_PAYLOAD_SERVERBOUND, mcstring(channel))
        if channel == "neoforge:config_file":
            name, pos = read_string(body, pos)
            size, pos = read_varint(body, pos)
            configs.append((name, size))
        continue
    if pid == CONFIG_DISCONNECT_CLIENTBOUND:
        print("PROBE: disconnected during configuration")
        break
    if pid == CONFIG_FINISH_CLIENTBOUND:
        break

print(f"PROBE: {len(channels)} modded payload(s) in configuration")
for channel in dict.fromkeys(channels):
    print(f"    {channel} x{channels.count(channel)}")
print(f"PROBE: {len(configs)} config file(s) sent")
for name, size in configs:
    print(f"    {name} ({size} byte(s))")
if refused:
    print(f"PROBE: refused by negotiation, first channel {refused[0]}")
sys.exit(0 if configs or refused else 1)
