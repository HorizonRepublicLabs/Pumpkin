"""Right-clicks the prepared energized smelter, then moves raw iron into its input slot."""
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
        time.sleep(25)
        send(sock, 66, (varint(0) + bp(2, 150, -1) + varint(1)
                        + struct.pack(">fff", 0.5, 1.0, 0.5) + b"\x00" + b"\x00" + varint(1)))
        print("BOT: smelter clicked", flush=True)
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
        # raw iron sits in hotbar slot 0, mapped at the end of the menu's slot list;
        # shift-click lets the mod's own quickMoveStack route it
        pick_index = nslots - 9
        container_click(pick_index, 0, 1)
        print(f"BOT: shift-clicked raw iron at menu slot {pick_index}", flush=True)
        time.sleep(5)
        print("BOT: done", flush=True)
        os._exit(0)
    except Exception as e:
        print("BOT: act failed:", e, flush=True); os._exit(1)

deadline = time.time() + 120
sock = socket.create_connection((HOST, PORT), timeout=300)
send(sock, 0, varint(PROTO) + mcs(HOST) + struct.pack(">H", PORT) + varint(2))
send(sock, 0, mcs("PumpkinBot") + bytes(16))
state = "login"
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
        if pid == 20:
            wid, pos = rv(body, 0)
            st, pos = rv(body, pos)
            slot = struct.unpack(">h", body[pos:pos+2])[0]; pos += 2
            c, pos = rv(body, pos)
            extra = ""
            if c > 0:
                iid, pos = rv(body, pos)
                extra = f" item={iid} count={c}"
            print(f"BOT: SET_SLOT window={wid} slot={slot}{extra}", flush=True)
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
