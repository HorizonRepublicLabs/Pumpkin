"""Places a tinkering table and right-clicks it; watches for the mod menu opening."""
import socket, struct, sys, time, threading, os
HOST, PORT, PROTO = "127.0.0.1", 25599, 776
LOCK = threading.Lock()
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
def send(sock, pid, payload):
    body = varint(pid) + payload
    with LOCK:
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

def act(sock):
    try:
        time.sleep(12)
        send(sock, 53, struct.pack(">h", 0)); time.sleep(0.5)
        send(sock, 66, (varint(0) + bp(0, 149, 0) + varint(1)
                        + struct.pack(">fff", 0.5, 1.0, 0.5) + b"\x00" + b"\x00" + varint(1)))
        print("BOT: table placed", flush=True); time.sleep(2)
        send(sock, 66, (varint(0) + bp(0, 150, 0) + varint(1)
                        + struct.pack(">fff", 0.5, 1.0, 0.5) + b"\x00" + b"\x00" + varint(2)))
        print("BOT: table clicked", flush=True)
        time.sleep(3)
        wid = getattr(sys.modules[__name__], "window_id", None)
        if wid is None:
            print("BOT: no window opened", flush=True); os._exit(1)
        nslots = getattr(sys.modules[__name__], "slot_count", 0)
        def container_click(slot, button, mode):
            payload = (varint(wid) + varint(1) + struct.pack(">h", slot)
                       + struct.pack("b", button) + varint(mode)
                       + varint(0) + b"\x00" + b"\x00")
            send(sock, 18, payload)
        # the essence sits in player-inventory slot 0 (hotbar), which the menu maps at
        # the end of its slot list: pick it up, drop it into machine slot 0
        pick_index = nslots - 9 + 1  # hotbar slot 1: the essence (slot 0 held the table)
        container_click(pick_index, 0, 0)
        print(f"BOT: picked up from menu slot {pick_index}", flush=True)
        time.sleep(1.5)
        container_click(7, 0, 0)  # first player-storage slot in the menu
        print("BOT: placed into player storage via the menu", flush=True)
        time.sleep(2)
        container_click(7, 1, 4)  # Q: throw the whole stack out of the menu
        print("BOT: threw menu slot 7", flush=True)
        time.sleep(3)
        os._exit(0)
    except Exception as e:
        print("BOT: act failed:", e, flush=True); os._exit(1)

sock = socket.create_connection((HOST, PORT), timeout=120)
send(sock, 0, varint(PROTO) + mcs(HOST) + struct.pack(">H", PORT) + varint(2))
send(sock, 0, mcs("PumpkinBot") + bytes(16))
state = "login"
deadline = time.time() + 120
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
        if pid == 59:
            wid, pos = rv(body, 0)
            mtype, pos = rv(body, pos)
            sys.modules[__name__].window_id = wid
            print(f"BOT: OPEN_SCREEN window={wid} menu_type={mtype}", flush=True)
        if pid == 18:
            wid, pos = rv(body, 0)
            if wid >= 100:
                _state, pos = rv(body, pos)
                n, pos = rv(body, pos)
                sys.modules[__name__].slot_count = n
                # crude non-empty count: entries with a leading nonzero count varint
                filled = []
                p2 = pos
                for i in range(n):
                    c, p2 = rv(body, p2)
                    if c > 0:
                        item_id, p2 = rv(body, p2)
                        a, p2 = rv(body, p2); r, p2 = rv(body, p2)
                        filled.append((i, item_id, c))
                print(f"BOT: CONTENT window={wid} slots={n} filled={filled}", flush=True)
