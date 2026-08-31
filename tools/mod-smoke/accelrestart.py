"""Phase 1: place accelerator + crop, idle briefly, stop. Phase 2: restart the same
world, idle long, assert the tick chain resumed (crop keeps growing)."""
import subprocess, sys, time, threading, os

BINARY, BOT = sys.argv[1], sys.argv[2]

def run_server(log_name, commands, bot_env, wait_bot):
    proc = subprocess.Popen([BINARY], stdin=subprocess.PIPE, stdout=subprocess.PIPE,
                            stderr=subprocess.STDOUT, text=True)
    lines, ready = [], threading.Event()
    def reader():
        for line in proc.stdout:
            lines.append(line.rstrip())
            if "Server is now running" in line: ready.set()
    threading.Thread(target=reader, daemon=True).start()
    ready.wait(150); time.sleep(2)
    env = dict(os.environ); env.update(bot_env)
    bot = subprocess.Popen([sys.executable, BOT], stdout=subprocess.PIPE,
                           stderr=subprocess.STDOUT, text=True, env=env)
    placed = threading.Event()
    def breader():
        for line in bot.stdout:
            print(line.rstrip(), flush=True)
            if "accelerator placed" in line: placed.set()
    threading.Thread(target=breader, daemon=True).start()
    time.sleep(5)
    for i, cmd in enumerate(commands):
        if cmd == "WAIT_PLACED":
            placed.wait(60); time.sleep(2); continue
        proc.stdin.write(cmd + "\n"); proc.stdin.flush(); time.sleep(1.2)
    try: bot.wait(500)
    except subprocess.TimeoutExpired: bot.kill()
    time.sleep(3)
    proc.stdin.write("stop\n"); proc.stdin.flush()
    try: proc.wait(90)
    except subprocess.TimeoutExpired: proc.kill()
    open(log_name, "w").write("\n".join(lines))
    return lines

print("=== PHASE 1 ===", flush=True)
lines = run_server("phase1.log", [
    "setblock 0 147 -1 minecraft:stone",
    "tp PumpkinBot 0 148 1",
    "give PumpkinBot mysticalagriculture:inferium_growth_accelerator 1",
    "WAIT_PLACED",
    "setblock 0 149 -1 minecraft:farmland",
    "setblock 2 149 -1 minecraft:water",
    "setblock 0 150 -1 mysticalagriculture:inferium_crop[age=1]",
], {"ACCEL_IDLE": "40"}, True)
p1 = sum(1 for l in lines if "scheduled tick set" in l)
print(f"phase 1 growth writes: {p1}", flush=True)

print("=== PHASE 2 (restart, no re-place) ===", flush=True)
# The bot only idles this time: accelerator + crop already in the world.
lines = run_server("phase2.log", [
    "tp PumpkinBot 0 148 1",
], {"ACCEL_IDLE": "180", "ACCEL_SKIP_PLACE": "1"}, False)
p2 = sum(1 for l in lines if "scheduled tick set" in l)
print(f"phase 2 growth writes: {p2}", flush=True)
print("\n".join(l for l in lines if "scheduled tick set" in l)[-800:], flush=True)
print("RESULT: " + ("CHAIN SURVIVED RESTART" if p2 > 0 else "DORMANT AFTER RESTART"), flush=True)
