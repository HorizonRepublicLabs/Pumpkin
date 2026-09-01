import subprocess, sys, time, threading, atexit
proc = subprocess.Popen([sys.argv[1]], stdin=subprocess.PIPE, stdout=subprocess.PIPE,
                        stderr=subprocess.STDOUT, text=True)
lines, ready = [], threading.Event()
atexit.register(lambda: open("full-server.log", "w").write("\n".join(lines)))
def reader():
    for line in proc.stdout:
        lines.append(line.rstrip())
        if "Server is now running" in line: ready.set()
threading.Thread(target=reader, daemon=True).start()
ready.wait(150); time.sleep(2)
bot = subprocess.Popen([sys.executable, sys.argv[2]], stdout=subprocess.PIPE,
                       stderr=subprocess.STDOUT, text=True)
def breader():
    for line in bot.stdout: print(line.rstrip(), flush=True)
threading.Thread(target=breader, daemon=True).start()
joined = threading.Event()
# wait until the bot is actually in the world so its chunks are loaded
for _ in range(60):
    if any("joined the game" in l for l in lines): joined.set(); break
    time.sleep(1)
time.sleep(3)
for cmd in ["tp PumpkinBot 0 151 1",
            "setblock 2 150 -1 mekanism:energized_smelter",
            "setblock 2 150 0 mekanismgenerators:heat_generator",
            "setblock 1 150 -1 minecraft:chest",
            "setblock 2 149 -1 minecraft:hopper",
            "setblock 3 150 -1 minecraft:chest",
            "give PumpkinBot minecraft:raw_iron 1",
            "give PumpkinBot minecraft:coal 64",
            "give PumpkinBot minecraft:coal 64",
            "give PumpkinBot minecraft:coal 64",
            "give PumpkinBot minecraft:coal 64"]:
    proc.stdin.write(cmd + "\n"); proc.stdin.flush(); time.sleep(1.2)
try: bot.wait(620)
except subprocess.TimeoutExpired: bot.kill()
time.sleep(3)
proc.stdin.write("stop\n"); proc.stdin.flush()
try: proc.wait(90)
except subprocess.TimeoutExpired: proc.kill()
print("--- server drops/errors ---")
print("\n".join(l for l in lines if any(k in l for k in
    ("ERROR", "Unimplemented", "useBlockOn", "Exception", "Caused")))[-2500:])
