"""Three phases: produce an ingot, flip the machine's item ejector on in the saved
blob, boot again and watch the ingot land in the adjacent vanilla chest."""
import base64, glob, json, re, struct, subprocess, sys, time, zlib, os

srv = sys.argv[1]; bot = sys.argv[2]; binary = "/Users/shbodya/.cargo/target/debug/pumpkin"
here = os.path.dirname(os.path.abspath(__file__))

print("PHASE1: produce ingot", flush=True)
r = subprocess.run(["python3", os.path.join(here, "mekejectdrive.py"), binary, bot],
                   cwd=srv, capture_output=True, text=True, timeout=780)
if "filled=[(3, 932, 1)]" not in r.stdout:
    print("PHASE1 FAILED"); print(r.stdout[-1500:]); sys.exit(1)
print("PHASE1 OK: ingot in output", flush=True)

print("PHASE2: enable ejector in saved blob", flush=True)
edited = 0
for path in glob.glob(os.path.join(srv, "world/**/region/*.mca"), recursive=True):
    data = bytearray(open(path, "rb").read())
    if len(data) < 8192:
        continue
    for i in range(1024):
        off = int.from_bytes(data[i*4:i*4+3], "big") * 4096
        sectors = data[i*4+3]
        if off == 0:
            continue
        length = struct.unpack(">I", bytes(data[off:off+4]))[0]
        if data[off+4] != 2:
            continue
        try:
            raw = zlib.decompress(bytes(data[off+5:off+4+length]))
        except Exception:
            continue
        m = re.search(rb"pumpkin:mod_data..([A-Za-z0-9+/=]{40,})", raw, re.S)
        if not m:
            continue
        blob = json.loads(base64.b64decode(m.group(1)))
        print("PHASE2: blob keys", sorted(blob)[:8], flush=True)
        if "component_config" not in blob:
            continue
        cfg = blob["component_config"]
        for key in list(cfg):
            if key.startswith("eject"):
                cfg[key] = True
        out = base64.b64encode(json.dumps(blob, separators=(",", ":")).encode()).decode().encode()
        old = m.group(1)
        start = m.start(1)
        # the NBT string length prefix sits two bytes before the payload
        raw2 = bytearray(raw)
        raw2[start-2:start] = struct.pack(">H", len(out))
        raw2[start:start+len(old)] = out
        comp = zlib.compress(bytes(raw2))
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
        open(path, "wb").write(bytes(data))
        edited += 1
        print("PHASE2: ejector enabled in", os.path.basename(path), flush=True)
if edited == 0:
    print("PHASE2 FAILED: no machine blob found"); sys.exit(1)

print("PHASE3: boot and watch the chest", flush=True)
proc = subprocess.Popen([binary], stdin=subprocess.PIPE, stdout=subprocess.PIPE,
                        stderr=subprocess.STDOUT, text=True, cwd=srv)
import threading
lines = []
def reader():
    for line in proc.stdout:
        lines.append(line.rstrip())
threading.Thread(target=reader, daemon=True).start()
time.sleep(45)
proc.stdin.write("stop\n"); proc.stdin.flush()
try: proc.wait(90)
except subprocess.TimeoutExpired: proc.kill()

found = False
for path in glob.glob(os.path.join(srv, "world/**/region/*.mca"), recursive=True):
    data = open(path, "rb").read()
    if len(data) < 8192: continue
    for i in range(1024):
        off = int.from_bytes(data[i*4:i*4+3], "big") * 4096
        if off == 0: continue
        try:
            raw = zlib.decompress(data[off+5:off+4+struct.unpack(">I", data[off:off+4])[0]])
        except Exception: continue
        for m in re.finditer(rb"minecraft:chest(.{0,900}?)iron_ingot", raw, re.S):
            found = True
print("EJECT VERDICT:", "INGOT IN CHEST" if found else "chest empty")
print("\n".join(l for l in lines if "Exception" in l or "EJECT" in l)[:800])
sys.exit(0 if found else 1)
