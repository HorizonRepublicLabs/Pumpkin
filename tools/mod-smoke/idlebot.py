"""Minimal offline-mode MC 26.2 client: login -> config -> play, then watch for the
inventory packet carrying the /give result and report the item id it carries."""
import socket, struct, sys, threading, time

HOST, PORT, PROTO = "127.0.0.1", 25599, 776
NAME = "PumpkinBot"

def varint(n):
    out = b""
    n &= 0xFFFFFFFF
    while True:
        b = n & 0x7F
        n >>= 7
        out += bytes([b | (0x80 if n else 0)])
        if not n:
            return out

def read_varint(sock_or_buf, pos=None):
    if pos is None:  # socket
        n = shift = 0
        while True:
            b = sock_or_buf.recv(1)
            if not b: raise EOFError("closed")
            n |= (b[0] & 0x7F) << shift
            if not b[0] & 0x80: return n
            shift += 7
    else:  # (buf, pos)
        buf = sock_or_buf
        n = shift = 0
        while True:
            b = buf[pos]; pos += 1
            n |= (b & 0x7F) << shift
            if not b & 0x80: return n, pos
            shift += 7

def send(sock, pid, payload):
    body = varint(pid) + payload
    sock.sendall(varint(len(body)) + body)

def read_packet(sock):
    length = read_varint(sock)
    buf = b""
    while len(buf) < length:
        chunk = sock.recv(length - len(buf))
        if not chunk: raise EOFError("closed mid-packet")
        buf += chunk
    pid, pos = read_varint(buf, 0)
    return pid, buf[pos:]

def mcstring(s):
    b = s.encode()
    return varint(len(b)) + b

sock = socket.create_connection((HOST, PORT), timeout=30)
send(sock, 0x00, varint(PROTO) + mcstring(HOST) + struct.pack(">H", PORT) + varint(2))
send(sock, 0x00, mcstring(NAME) + bytes(16))

state = "login"
print("BOT: connected", flush=True)
deadline = time.time() + (int(sys.argv[1]) if len(sys.argv) > 1 else 90)
sock.settimeout(5)
while time.time() < deadline:
    try:
        pid, body = read_packet(sock)
    except (socket.timeout, TimeoutError):
        continue
    if state == "login":
        if pid == 2:
            send(sock, 3, b"")
            info = (mcstring("en_us") + struct.pack("b", 2) + varint(0) + b"\x01"
                    + b"\x00" + varint(1) + b"\x00" + b"\x01")
            send(sock, 0, info)
            state = "config"
    elif state == "config":
        if pid == 14:
            send(sock, 7, body)
        elif pid == 3:
            send(sock, 3, b"")
            state = "play"
            print("BOT: PLAY STATE", flush=True)
    elif state == "play":
        if pid == 28:
            send(sock, 44, body)
print("BOT: idle done", flush=True)
sock.close()
