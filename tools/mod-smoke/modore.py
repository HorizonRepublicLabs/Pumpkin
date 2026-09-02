"""A mod's ore is really in the ground, not merely announced at boot.

The startup line says how many mod ore features "will generate in new chunks", which is
a promise about the future: the biome tag has to resolve, the feature has to land in the
right generation step, and the ore's own block has to come out of the runtime registry.
This generates real chunks and looks for the blocks in the saved palettes.
"""
import glob, os, struct, subprocess, sys, threading, time, zlib

sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)))
from nbt import nbt_read, compound_get

srv = sys.argv[1]; binary = "/Users/shbodya/.cargo/target/debug/pumpkin"
here = os.path.dirname(os.path.abspath(__file__))

ORES = [b"mysticalagriculture:prosperity_ore", b"mysticalagriculture:inferium_ore"]
# Somewhere to send the bot so chunks past the spawn ring get generated too.
STOPS = [(0, 100, 0), (300, 100, 300), (-300, 100, -300), (600, 100, 0)]


def boot(seconds):
    proc = subprocess.Popen([binary], stdin=subprocess.PIPE, stdout=subprocess.PIPE,
                            stderr=subprocess.STDOUT, text=True, cwd=srv)
    lines, ready = [], threading.Event()
    def reader():
        for line in proc.stdout:
            lines.append(line.rstrip())
            if "Server is now running" in line: ready.set()
    threading.Thread(target=reader, daemon=True).start()
    ready.wait(150); time.sleep(2)
    idle = subprocess.Popen([sys.executable, os.path.join(here, "idlebot.py"), str(seconds + 40)],
                            stdout=subprocess.PIPE, stderr=subprocess.STDOUT, text=True)
    for _ in range(60):
        if any("joined the game" in l for l in lines): break
        time.sleep(1)
    time.sleep(2)
    for x, y, z in STOPS:
        proc.stdin.write(f"tp PumpkinBot {x} {y} {z}\n"); proc.stdin.flush()
        time.sleep(seconds / len(STOPS))
    idle.kill()
    time.sleep(4)
    proc.stdin.write("stop\n"); proc.stdin.flush()
    try: proc.wait(120)
    except subprocess.TimeoutExpired: proc.kill()
    return lines


def palette_names():
    """Every block name in every saved section palette, with its chunk for context."""
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
            # Cheap pre-filter: walking every chunk's NBT costs far more than a substring
            # test, and a name absent from the raw bytes is absent from the palette.
            if not any(ore in raw for ore in ORES):
                continue
            try:
                root, _ = nbt_read(raw, 3 + struct.unpack_from(">H", raw, 1)[0], 10)
            except Exception:
                continue
            _, sections = compound_get(root, b"sections")
            if sections is None or sections[0] != "list":
                continue
            for section in sections[2]:
                if section[0] != "compound":
                    continue
                _, states = compound_get(section, b"block_states")
                if states is None or states[0] != "compound":
                    continue
                _, palette = compound_get(states, b"palette")
                if palette is None or palette[0] != "list":
                    continue
                for entry in palette[2]:
                    if entry[0] != "compound":
                        continue
                    _, name = compound_get(entry, b"Name")
                    if name is not None:
                        yield os.path.basename(path), i, name[1]


print("Generating chunks with a bot walking the world", flush=True)
lines = boot(80)

chunks = {ore: set() for ore in ORES}
for region, index, name in palette_names():
    if name in chunks:
        chunks[name].add((region, index))

for line in lines:
    if "Mod worldgen" in line or "not placeable" in line:
        print(line, flush=True)

counts = {ore.decode(): len(where) for ore, where in chunks.items()}
print("ORE VERDICT:", ", ".join(f"{name}={n} chunk(s)" for name, n in counts.items()))
sys.exit(0 if all(n > 0 for n in counts.values()) else 1)
