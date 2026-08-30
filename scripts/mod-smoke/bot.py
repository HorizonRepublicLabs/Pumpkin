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
# handshake: intention(protocol, host, port, next=login)
send(sock, 0x00, varint(PROTO) + mcstring(HOST) + struct.pack(">H", PORT) + varint(2))
# login hello(name, uuid)
send(sock, 0x00, mcstring(NAME) + bytes(16))

state = "login"
give_seen = []
seen_pids = {}
print("BOT: connected", flush=True)
deadline = time.time() + 60
while time.time() < deadline:
    pid, body = read_packet(sock)
    if state == "login":
        if pid == 3:  # compression -- disabled in config, but handle by failing loudly
            sys.exit("BOT: unexpected compression enable")
        if pid == 2:  # login_finished
            send(sock, 3, b"")  # login_acknowledged
            # client information -- the server holds known-packs back until the client
            # has said something about itself
            info = (mcstring("en_us") + struct.pack("b", 2) + varint(0) + b"\x01"
                    + b"\x00" + varint(1) + b"\x00" + b"\x01")
            send(sock, 0, info)
            state = "config"
            print("BOT: login acknowledged -> config", flush=True)
    elif state == "config":
        if pid == 14:  # select_known_packs -> echo verbatim
            send(sock, 7, body)
            print("BOT: known packs echoed", flush=True)
        elif pid == 3:  # finish_configuration -> ack
            send(sock, 3, b"")
            state = "play"
            print("BOT: PLAY STATE", flush=True)
    elif state == "play":
        seen_pids[pid] = seen_pids.get(pid, 0) + 1
        if pid == 18:  # container_set_content: window varint, state varint, count varint, stacks
            try:
                _win, pos = read_varint(body, 0)
                _sid, pos = read_varint(body, pos)
                n, pos = read_varint(body, pos)
                for _ in range(n):
                    count, pos = read_varint(body, pos)
                    if count > 0:
                        item_id, pos = read_varint(body, pos)
                        give_seen.append(item_id)
                        if item_id >= 1537:
                            print(f"BOT: DYNAMIC ITEM {item_id} IN CONTAINER CONTENT", flush=True)
                        # skip component counts: add varint, remove varint, then give up on
                        # the rest of this packet -- components are unbounded
                        raise StopIteration
            except StopIteration:
                break
            except Exception as e:
                print("BOT: content parse error", e, flush=True)
        if pid == 4:  # clientbound keep_alive(config)= n/a; play keep_alive id 28? watch both
            pass
        if pid == 28:  # keep_alive: echo the i64 so the server keeps us
            send(sock, 44, body)
        if pid in (20, 108):  # container_set_slot / set_player_inventory
            try:
                if pid == 20:
                    pos = 1  # window id i8
                    _state_id, pos = read_varint(body, pos)
                    pos += 2  # slot i16
                else:
                    _slot, pos = read_varint(body, 0)
                count, pos = read_varint(body, pos)
                if count > 0:
                    item_id, pos = read_varint(body, pos)
                    print(f"BOT: SLOT PACKET pid={pid} count={count} item_id={item_id}", flush=True)
                    give_seen.append(item_id)
                    if item_id >= 1537:  # Item::BASE_COUNT -- a dynamic, mod-registered id
                        print("BOT: DYNAMIC ITEM RECEIVED", flush=True)
                        break
            except Exception as e:
                print("BOT: slot parse error", e, flush=True)
sock.close()
print("BOT: done, ids seen:", give_seen, flush=True)
print("BOT: play pids:", dict(sorted(seen_pids.items())), flush=True)
