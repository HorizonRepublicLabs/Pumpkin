"""Reads the configuration-phase tag sync and reports what a modded client is told.

A NeoForge client with the mods installed refuses to finish joining when a tag its own
items resolve is never mentioned by the server ("Missing tag TagKey[minecraft:block /
mekanism:incorrect_for_disassembler]"), so this probe stops at the tags packet and prints
the mod tags it carries per registry.

    python3 tools/mod-smoke/tagprobe.py [port]
"""
import socket
import struct
import sys
import zlib

HOST = "127.0.0.1"
PORT = int(sys.argv[1]) if len(sys.argv) > 1 else 25565
from smoke_env import PROTO
NAME = "TagProbe"
UPDATE_TAGS = 13


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
    return buf[pos : pos + length].decode(), pos + length


def send(sock, pid, payload):
    send_compressed(sock, pid, payload)


# Set once the server enables compression; the server this probe targets is the user's
# own, so unlike the smoke bots it cannot assume compression is off.
COMPRESSION = -1


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


def send_compressed(sock, pid, payload):
    body = varint(pid) + payload
    if COMPRESSION < 0:
        sock.sendall(varint(len(body)) + body)
        return
    # Below the threshold a packet is sent with a 0 length marker and no deflate.
    framed = varint(0) + body
    sock.sendall(varint(len(framed)) + framed)


def mcstring(s):
    b = s.encode()
    return varint(len(b)) + b


sock = socket.create_connection((HOST, PORT), timeout=30)
send(sock, 0x00, varint(PROTO) + mcstring(HOST) + struct.pack(">H", PORT) + varint(2))
send(sock, 0x00, mcstring(NAME) + bytes(16))

state = "login"
while True:
    pid, body = read_packet(sock)
    if state == "login":
        if pid == 3:  # login_compression
            COMPRESSION, _ = read_varint(body, 0)
            globals()["COMPRESSION"] = COMPRESSION
            continue
        if pid == 2:
            send(sock, 3, b"")
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

    if pid == 14:  # select_known_packs -> echo verbatim
        send(sock, 7, body)
        continue
    if pid == 3:  # finish_configuration without ever seeing tags
        sys.exit("PROBE: FAIL -- configuration finished, no tags packet")
    if pid != UPDATE_TAGS:
        continue

    count, pos = read_varint(body, 0)
    total_mod = 0
    for _ in range(count):
        registry, pos = read_string(body, pos)
        tag_count, pos = read_varint(body, pos)
        mod_tags = []
        for _ in range(tag_count):
            name, pos = read_string(body, pos)
            members, pos = read_varint(body, pos)
            for _ in range(members):
                _id, pos = read_varint(body, pos)
            if not name.startswith(("minecraft:", "c:")):
                mod_tags.append((name, members))
        total_mod += len(mod_tags)
        if mod_tags:
            print(f"{registry}: {tag_count} tag(s), {len(mod_tags)} from mods")
            for name, members in sorted(mod_tags)[:3]:
                print(f"    {name} ({members} member(s))")
    print(f"PROBE: {total_mod} mod tag(s) sent across {count} registries")
    marker = b"mekanism:incorrect_for_disassembler"
    verdict = "PRESENT" if marker in body else "MISSING"
    print(f"PROBE: mekanism:incorrect_for_disassembler {verdict}")
    sys.exit(0 if verdict == "PRESENT" else 1)
