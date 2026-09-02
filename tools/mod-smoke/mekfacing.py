"""A machine placed by a player faces the way that player was looking.

Before this, every mod block went into the world in its default state, so an energized
smelter always faced north however the placer stood -- and facing is not decoration for a
Mekanism machine: its side configuration names front, back and sides, so a wrongly-faced
machine takes items in and pushes them out of the wrong faces.

Places the same machine four times, once per cardinal yaw, and reads the facing back out
of the saved chunk palette. Passing means the four placements produced four facings.
"""
import glob, os, struct, subprocess, sys, threading, time, zlib

sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)))
from nbt import nbt_read, compound_get

srv = sys.argv[1]; binary = "/Users/shbodya/.cargo/target/debug/pumpkin"
here = os.path.dirname(os.path.abspath(__file__))

MACHINE = b"mekanism:energized_smelter"
# yaw -> the direction the player is looking, as Minecraft counts it
YAWS = [(0.0, "south"), (90.0, "west"), (180.0, "north"), (270.0, "east")]


def boot(yaw):
    """Boots, has the bot face `yaw` and place the machine, returns the saved facing."""
    proc = subprocess.Popen([binary], stdin=subprocess.PIPE, stdout=subprocess.PIPE,
                            stderr=subprocess.STDOUT, text=True, cwd=srv)
    lines, ready = [], threading.Event()
    def reader():
        for line in proc.stdout:
            lines.append(line.rstrip())
            if "Server is now running" in line: ready.set()
    threading.Thread(target=reader, daemon=True).start()
    ready.wait(150); time.sleep(2)
    bot = subprocess.Popen([sys.executable, os.path.join(here, "mekfacingbot.py"), str(yaw)],
                           stdout=subprocess.PIPE, stderr=subprocess.STDOUT, text=True)
    threading.Thread(target=lambda: [lines.append(l.rstrip()) for l in bot.stdout],
                     daemon=True).start()
    for _ in range(60):
        if any("joined the game" in l for l in lines): break
        time.sleep(1)
    time.sleep(2)
    for cmd in ["tp PumpkinBot 5 151 3",
                "setblock 5 149 0 minecraft:stone",
                "setblock 5 150 0 minecraft:air",
                "give PumpkinBot mekanism:energized_smelter 1"]:
        proc.stdin.write(cmd + "\n"); proc.stdin.flush(); time.sleep(1.2)
    try: bot.wait(120)
    except subprocess.TimeoutExpired: bot.kill()
    time.sleep(4)
    proc.stdin.write("stop\n"); proc.stdin.flush()
    try: proc.wait(90)
    except subprocess.TimeoutExpired: proc.kill()
    return facing_in_save(), lines


def facing_in_save():
    """The machine's `facing`, walked out of the chunk's block-state palette.

    A byte search will not do: the palette entry writes its Properties compound and its
    Name in whichever order the server's map iterated, so "the bytes after the name" is
    sometimes the facing and sometimes the next entry.
    """
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
            if MACHINE not in raw:
                continue
            try:
                root, _ = nbt_read(raw, 3 + struct.unpack_from(">H", raw, 1)[0], 10)
            except Exception:
                continue
            found = facing_in_chunk(root)
            if found is not None:
                return found
    return "machine not in the save"


def facing_in_chunk(root):
    """`facing` of the machine's palette entry, or None when this chunk has none."""
    _, sections = compound_get(root, b"sections")
    if sections is None or sections[0] != "list":
        return None
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
            if name is None or name[1] != MACHINE:
                continue
            _, properties = compound_get(entry, b"Properties")
            if properties is None or properties[0] != "compound":
                return "no properties on the palette entry"
            _, facing = compound_get(properties, b"facing")
            return "no facing property" if facing is None else facing[1].decode()
    return None


results = []
for yaw, looking in YAWS:
    for stale in glob.glob(os.path.join(srv, "world")):
        subprocess.run(["rm", "-rf", stale])
    facing, lines = boot(yaw)
    print(f"PLACED looking {looking} (yaw {yaw:.0f}) -> facing={facing}", flush=True)
    results.append(facing)
    bad = [l for l in lines if "getStateForPlacement" in l or "registered states" in l]
    if bad:
        print("\n".join(bad[:3]), flush=True)

print("FACING VERDICT:", ",".join(results))
sys.exit(0 if len(set(results)) == 4 else 1)
