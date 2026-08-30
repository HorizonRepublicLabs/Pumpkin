"""The full infusion: 8 pedestals loaded, altar given its input, then wait for the craft."""
import socket, struct, sys, time, threading, os
HOST, PORT, PROTO = "127.0.0.1", 25599, 776
def varint(n):
    out = b""; n &= 0xFFFFFFFF
    while True:
        b = n & 0x7F; n >>= 7
        out += bytes([b | (0x80 if n else 0)])
        if not n: return out
def rv(buf, pos):
    n = shift = 0
    while True:
        b = buf[pos]; pos += 1
        n |= (b & 0x7F) << shift
        if not b & 0x80: return n, pos
        shift += 7
def rvs(sock):
    n = shift = 0
    while True:
        b = sock.recv(1)
        if not b: raise EOFError
        n |= (b[0] & 0x7F) << shift
        if not b[0] & 0x80: return n
        shift += 7
SEND_LOCK = None
def send(sock, pid, payload):
    body = varint(pid) + payload
    with SEND_LOCK:
        sock.sendall(varint(len(body)) + body)
def readp(sock):
    length = rvs(sock)
    buf = b""
    while len(buf) < length:
        c = sock.recv(length - len(buf))
        if not c: raise EOFError
        buf += c
    pid, pos = rv(buf, 0)
    return pid, buf[pos:]
def mcs(s): b = s.encode(); return varint(len(b)) + b
def bp(x, y, z):
    val = (((x & 0x3FFFFFF) << 38) | ((z & 0x3FFFFFF) << 12) | (y & 0xFFF)) & 0xFFFFFFFFFFFFFFFF
    return struct.pack(">Q", val)

seq = [10]
def click(sock, x, y, z):
    seq[0] += 1
    send(sock, 66, (varint(0) + bp(x, y, z) + varint(1)
                    + struct.pack(">fff", 0.5, 1.0, 0.5) + b"\x00" + b"\x00" + varint(seq[0])))
def slot(sock, n):
    send(sock, 53, struct.pack(">h", n)); time.sleep(0.4)

# altar at (1,150,0); pedestals at cucumber offsets; ground blocks are set by the drive
ALTAR = (1, 150, 0)
OFFSETS = [(3,0,0),(0,0,3),(-3,0,0),(0,0,-3),(2,0,2),(2,0,-2),(-2,0,2),(-2,0,-2)]
PEDESTALS = [(1+dx, 150+dy, dz) for dx, dy, dz in OFFSETS]
# hotbar after gives: 0=pedestals(8) 1=gold(4) 2=essence(4) 3=blank_augment 4=altar
INGREDIENT_SLOT = [1, 2, 1, 2, 1, 2, 1, 2]

def act(sock):
  try:
    time.sleep(16)  # all gives landed
    # place the altar
    slot(sock, 4)
    print("REQ tp 1 151 2", flush=True); time.sleep(3)
    click(sock, 1, 149, 0)
    print("BOT: altar placed", flush=True); time.sleep(1)
    # place + load each pedestal
    for i, (px, py, pz) in enumerate(PEDESTALS):
        print(f"REQ tp {px} 151 {pz+2}", flush=True); time.sleep(3)
        slot(sock, 0)
        click(sock, px, py-1, pz)  # place pedestal on its ground block
        time.sleep(1)
        slot(sock, INGREDIENT_SLOT[i])
        click(sock, px, py, pz)  # load it
        print(f"BOT: pedestal {i} loaded", flush=True); time.sleep(1)
    # input on the altar
    print("REQ tp 1 151 2", flush=True); time.sleep(3)
    slot(sock, 3)
    click(sock, *ALTAR)
    print("BOT: altar input placed", flush=True)
    time.sleep(15)  # let it craft
    slot(sock, 8)  # empty hand: pull whatever the altar now holds
    click(sock, *ALTAR)
    print("BOT: altar polled", flush=True)
    time.sleep(3)
    print("BOT: done", flush=True)
    os._exit(0)
  except Exception as e:
    print("BOT: act failed:", e, flush=True)
    os._exit(1)

SEND_LOCK = threading.Lock()
sock = socket.create_connection((HOST, PORT), timeout=120)
send(sock, 0, varint(PROTO) + mcs(HOST) + struct.pack(">H", PORT) + varint(2))
send(sock, 0, mcs("PumpkinBot") + bytes(16))
state = "login"
deadline = time.time() + 240
while time.time() < deadline:
    pid, body = readp(sock)
    if state == "login" and pid == 2:
        send(sock, 3, b"")
        send(sock, 0, mcs("en_us") + struct.pack("b", 2) + varint(0) + b"\x01" + b"\x00" + varint(1) + b"\x00" + b"\x01")
        state = "config"
    elif state == "config":
        if pid == 14: send(sock, 7, body)
        elif pid == 3:
            send(sock, 3, b"")
            send(sock, 44, b"")
            state = "play"
            print("BOT: PLAY", flush=True)
            threading.Thread(target=act, args=(sock,), daemon=True).start()
    elif state == "play":
        if pid == 44 and len(body) == 8:
            send(sock, 28, body)
        if pid == 72:
            tid, _ = rv(body, 0)
            send(sock, 0, varint(tid))
