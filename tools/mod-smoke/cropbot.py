"""Joins, plants inferium seeds across a farmland patch, then idles while crops tick."""
import socket, struct, time, threading, os

HOST, PORT, PROTO = "127.0.0.1", 25599, 776
def varint(n):
    out = b""; n &= 0xFFFFFFFF
    while True:
        b = n & 0x7F; n >>= 7
        out += bytes([b | (0x80 if n else 0)])
        if not n: return out
def read_varint_buf(buf, pos):
    n = shift = 0
    while True:
        b = buf[pos]; pos += 1
        n |= (b & 0x7F) << shift
        if not b & 0x80: return n, pos
        shift += 7
def read_varint_sock(sock):
    n = shift = 0
    while True:
        b = sock.recv(1)
        if not b: raise EOFError
        n |= (b[0] & 0x7F) << shift
        if not b[0] & 0x80: return n
        shift += 7
lock = threading.Lock()
def send(sock, pid, payload):
    body = varint(pid) + payload
    with lock: sock.sendall(varint(len(body)) + body)
def read_packet(sock):
    length = read_varint_sock(sock)
    buf = b""
    while len(buf) < length:
        chunk = sock.recv(length - len(buf))
        if not chunk: raise EOFError
        buf += chunk
    pid, pos = read_varint_buf(buf, 0)
    return pid, buf[pos:]
def mcstring(s):
    b = s.encode(); return varint(len(b)) + b
def encode_blockpos(x, y, z):
    val = ((x & 0x3FFFFFF) << 38) | ((z & 0x3FFFFFF) << 12) | (y & 0xFFF)
    return struct.pack(">Q", val & 0xFFFFFFFFFFFFFFFF)

sock = socket.create_connection((HOST, PORT), timeout=600)
send(sock, 0, varint(PROTO) + mcstring(HOST) + struct.pack(">H", PORT) + varint(2))
send(sock, 0, mcstring("PumpkinBot") + bytes(16))
state = "login"
planted = False
IDLE = int(os.environ.get("CROP_IDLE", "240"))
deadline = time.time() + IDLE + 120
while time.time() < deadline:
    pid, body = read_packet(sock)
    if state == "login" and pid == 2:
        send(sock, 3, b"")
        info = (mcstring("en_us") + struct.pack("b", 2) + varint(0) + b"\x01"
                + b"\x00" + varint(1) + b"\x00" + b"\x01")
        send(sock, 0, info)
        state = "config"
    elif state == "config":
        if pid == 14: send(sock, 7, body)
        elif pid == 3:
            send(sock, 3, b"")
            state = "play"
            send(sock, 44, b"")
            print("BOT: PLAY", flush=True)
    elif state == "play":
        if pid == 44 and len(body) == 8:
            send(sock, 28, body)
        if pid == 72:
            tid, _ = read_varint_buf(body, 0)
            send(sock, 0, varint(tid))
            print("BOT: teleport confirmed", flush=True)
        if (pid == 20 or pid == 108) and not planted:
            planted = True
            def act():
                time.sleep(8)  # the give has landed
                send(sock, 53, struct.pack(">h", 0))  # hotbar slot 0: seeds
                time.sleep(0.5)
                seq = 1
                for x in range(-2, 3):
                    for z in range(-2, 3):
                        if x == 0 and z == 0: continue  # water
                        send(sock, 66, (varint(0) + encode_blockpos(x, 149, z) + varint(1)
                                        + struct.pack(">fff", 0.5, 1.0, 0.5) + b"\x00" + b"\x00"
                                        + varint(seq)))
                        seq += 1
                        time.sleep(0.15)
                print("BOT: planted", flush=True)
                time.sleep(IDLE)  # crops tick while we hold the chunk active
                print("BOT: idle done", flush=True)
                os._exit(0)
            threading.Thread(target=act, daemon=True).start()
sock.close()
print("BOT: done", flush=True)
