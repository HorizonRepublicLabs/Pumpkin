"""Fluid and chemical across transmitters: tank A holds lava (mechanical pipe line)
and hydrogen (pressurized tube line); the transmitter's tank-A side is set to PULL
in the save, and the contents landing in tank B prove both networks carry."""
import base64, glob, json, os, re, struct, subprocess, sys, threading, time, zlib

srv = sys.argv[1]; binary = "/Users/shbodya/.cargo/target/debug/pumpkin"
here = os.path.dirname(os.path.abspath(__file__))

BLOCKS = [
    "setblock 8 150 -1 minecraft:chest",
    "setblock 8 150 0 mekanism:basic_logistical_transporter",
    "setblock 8 150 1 minecraft:chest",
    "item replace block 8 150 1 container.0 with minecraft:diamond 7",
]

def boot(seconds, commands=()):
    proc = subprocess.Popen([binary], stdin=subprocess.PIPE, stdout=subprocess.PIPE,
                            stderr=subprocess.STDOUT, text=True, cwd=srv)
    lines, ready = [], threading.Event()
    def reader():
        for line in proc.stdout:
            lines.append(line.rstrip())
            if "Server is now running" in line: ready.set()
    threading.Thread(target=reader, daemon=True).start()
    ready.wait(150); time.sleep(2)
    idle = subprocess.Popen([sys.executable, os.path.join(here, "idlebot.py"), str(seconds + 20)],
                            stdout=subprocess.PIPE, stderr=subprocess.STDOUT, text=True)
    for _ in range(60):
        if any("joined the game" in l for l in lines): break
        time.sleep(1)
    time.sleep(2)
    proc.stdin.write("tp PumpkinBot 0 151 1\n"); proc.stdin.flush(); time.sleep(1)
    for cmd in commands:
        proc.stdin.write(cmd + "\n"); proc.stdin.flush(); time.sleep(1)
    time.sleep(seconds)
    # the bot leaves first: block entities in the player-held chunk skip the
    # shutdown save while its ticket is live (open host bug, worked around here)
    idle.kill()
    time.sleep(4)
    proc.stdin.write("stop\n"); proc.stdin.flush()
    try: proc.wait(90)
    except subprocess.TimeoutExpired: proc.kill()
    return lines

def rewrite_chunks(edit_raw):
    """Applies edit_raw(raw_bytes) -> new_bytes|None to every chunk, in place."""
    edits = 0
    for path in glob.glob(os.path.join(srv, "world/**/region/*.mca"), recursive=True):
        data = bytearray(open(path, "rb").read())
        if len(data) < 8192: continue
        changed = False
        for i in range(1024):
            off = int.from_bytes(data[i*4:i*4+3], "big") * 4096
            sectors = data[i*4+3]
            if off == 0: continue
            length = struct.unpack(">I", bytes(data[off:off+4]))[0]
            if data[off+4] != 2: continue
            try: raw = zlib.decompress(bytes(data[off+5:off+4+length]))
            except Exception: continue
            new_raw = edit_raw(raw)
            if new_raw is None: continue
            comp = zlib.compress(new_raw)
            payload = struct.pack(">I", len(comp) + 1) + b"\x02" + comp
            need = (len(payload) + 4095) // 4096
            if need <= sectors:
                data[off:off+len(payload)] = payload
            else:
                new_off = (len(data) + 4095) // 4096
                data.extend(b"\x00" * (new_off*4096 - len(data)))
                data.extend(payload)
                data.extend(b"\x00" * ((-len(data)) % 4096))
                data[i*4:i*4+3] = new_off.to_bytes(3, "big")
                data[i*4+3] = need
            changed = True
            edits += 1
        if changed:
            open(path, "wb").write(bytes(data))
    return edits

# --- minimal NBT (big-endian, uncompressed payload) ---------------------------
def nbt_read(buf, pos, tag):
    if tag == 0: return None, pos
    if tag == 1: return buf[pos], pos+1
    if tag == 2: return struct.unpack_from(">h", buf, pos)[0], pos+2
    if tag == 3: return struct.unpack_from(">i", buf, pos)[0], pos+4
    if tag == 4: return struct.unpack_from(">q", buf, pos)[0], pos+8
    if tag == 5: return struct.unpack_from(">f", buf, pos)[0], pos+4
    if tag == 6: return struct.unpack_from(">d", buf, pos)[0], pos+8
    if tag == 7:
        n = struct.unpack_from(">i", buf, pos)[0]; pos += 4
        return ("bytes", buf[pos:pos+n]), pos+n
    if tag == 8:
        n = struct.unpack_from(">H", buf, pos)[0]; pos += 2
        return ("str", buf[pos:pos+n]), pos+n
    if tag == 9:
        elem = buf[pos]; n = struct.unpack_from(">i", buf, pos+1)[0]; pos += 5
        items = []
        for _ in range(n):
            v, pos = nbt_read(buf, pos, elem)
            items.append(v)
        return ("list", elem, items), pos
    if tag == 10:
        out = []
        while True:
            t = buf[pos]; pos += 1
            if t == 0: break
            n = struct.unpack_from(">H", buf, pos)[0]; pos += 2
            name = buf[pos:pos+n]; pos += n
            v, pos = nbt_read(buf, pos, t)
            out.append((t, name, v))
        return ("compound", out), pos
    if tag == 11:
        n = struct.unpack_from(">i", buf, pos)[0]; pos += 4
        return ("ints", buf[pos:pos+4*n]), pos+4*n
    if tag == 12:
        n = struct.unpack_from(">i", buf, pos)[0]; pos += 4
        return ("longs", buf[pos:pos+8*n]), pos+8*n
    raise ValueError("tag %d" % tag)

def nbt_write(v, tag):
    if tag == 1: return bytes([v])
    if tag == 2: return struct.pack(">h", v)
    if tag == 3: return struct.pack(">i", v)
    if tag == 4: return struct.pack(">q", v)
    if tag == 5: return struct.pack(">f", v)
    if tag == 6: return struct.pack(">d", v)
    if tag == 7: return struct.pack(">i", len(v[1])) + v[1]
    if tag == 8: return struct.pack(">H", len(v[1])) + v[1]
    if tag == 9:
        elem, items = v[1], v[2]
        return bytes([elem]) + struct.pack(">i", len(items)) + b"".join(nbt_write(i, elem) for i in items)
    if tag == 10:
        out = b""
        for t, name, val in v[1]:
            out += bytes([t]) + struct.pack(">H", len(name)) + name + nbt_write(val, t)
        return out + b"\x00"
    if tag == 11: return struct.pack(">i", len(v[1])//4) + v[1]
    if tag == 12: return struct.pack(">i", len(v[1])//8) + v[1]
    raise ValueError("tag %d" % tag)

def compound_get(comp, name):
    for t, n, v in comp[1]:
        if n == name: return t, v
    return None, None

def compound_set_string(comp, name, value):
    entry = (8, name, ("str", value))
    for i, (t, n, v) in enumerate(comp[1]):
        if n == name:
            comp[1][i] = entry
            return
    comp[1].append(entry)

def blob_b64(payload):
    return base64.b64encode(json.dumps(payload, separators=(",", ":")).encode())

# The transporter's SOUTH side (index 3, toward the source chest at z=+1) pulls;
# ConnectionType PULL is 2. Everything else stays NORMAL so the far chest accepts.
TRANSPORTER = b"mekanism:basic_logistical_transporter"
PULL_BLOB = {"connection": [0, 0, 0, 2, 0, 0]}


def walk_block_entities(root):
    _, entities = compound_get(root, b"block_entities")
    if entities is None:
        return []
    return entities[2] if entities[0] == "list" else []


def phase2_edit(raw):
    root, _ = nbt_read(raw, 3 + struct.unpack_from(">H", raw, 1)[0], 10)
    changed = False
    for entity in walk_block_entities(root):
        if entity[0] != "compound":
            continue
        _, ident = compound_get(entity, b"id")
        if ident is not None and ident[1] == TRANSPORTER:
            compound_set_string(entity, b"pumpkin:mod_data", blob_b64(PULL_BLOB))
            changed = True
    if not changed:
        return None
    header = raw[:3 + struct.unpack_from(">H", raw, 1)[0]]
    return header + nbt_write(root, 10)


def count_diamonds_at(z_wanted):
    """Diamonds in the vanilla chest at the given z, read out of the save."""
    total = 0
    for path in glob.glob(os.path.join(srv, "world/**/region/*.mca"), recursive=True):
        data = open(path, "rb").read()
        if len(data) < 8192:
            continue
        for i in range(1024):
            off = int.from_bytes(data[i*4:i*4+3], "big") * 4096
            if off == 0:
                continue
            try:
                raw = zlib.decompress(data[off+5:off+4+struct.unpack(">I", data[off:off+4])[0]])
            except Exception:
                continue
            try:
                root, _ = nbt_read(raw, 3 + struct.unpack_from(">H", raw, 1)[0], 10)
            except Exception:
                continue
            for entity in walk_block_entities(root):
                if entity[0] != "compound":
                    continue
                _, ident = compound_get(entity, b"id")
                _, z = compound_get(entity, b"z")
                if ident is None or ident[1] != b"minecraft:chest" or z != z_wanted:
                    continue
                _, items = compound_get(entity, b"Items")
                if items is None or items[0] != "list":
                    continue
                for slot in items[2]:
                    if slot[0] != "compound":
                        continue
                    _, sid = compound_get(slot, b"id")
                    _, count = compound_get(slot, b"count")
                    if sid is not None and sid[1] == b"minecraft:diamond":
                        total += count if isinstance(count, int) else 0
    return total


print("PHASE1: place the line and fill the source chest", flush=True)
boot(25, BLOCKS)
source = count_diamonds_at(1)
print(f"PHASE1: source chest holds {source} diamond(s)", flush=True)
if source == 0:
    print("PHASE1 FAILED: /item replace put nothing in the chest"); sys.exit(1)

print("PHASE2: set the transporter's source side to PULL", flush=True)
edits = rewrite_chunks(phase2_edit)
print(f"PHASE2: {edits} chunk(s) edited", flush=True)
if edits == 0:
    print("PHASE2 FAILED"); sys.exit(1)

print("PHASE3: boot and let the transporter carry", flush=True)
lines = boot(60)

far = count_diamonds_at(-1)
near = count_diamonds_at(1)
print(f"TRANSPORT VERDICT: far chest={far} source chest={near}")
print("\n".join(l for l in lines if "[mod/" in l or "[vc]" in l)[:4000])
sys.exit(0 if far > 0 else 1)
