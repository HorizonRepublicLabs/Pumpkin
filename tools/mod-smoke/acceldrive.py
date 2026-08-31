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
placed = threading.Event()
def breader():
    for line in bot.stdout:
        print(line.rstrip(), flush=True)
        if "accelerator placed" in line: placed.set()
threading.Thread(target=breader, daemon=True).start()
time.sleep(5)
for cmd in ["setblock 0 147 -1 minecraft:stone",
            "tp PumpkinBot 0 148 1",
            "give PumpkinBot mysticalagriculture:inferium_growth_accelerator 1"]:
    proc.stdin.write(cmd + "\n"); proc.stdin.flush(); time.sleep(1.2)
placed.wait(60)
time.sleep(2)
# farmland and the crop go in above the accelerator, water for moisture
for cmd in ["setblock 0 149 -1 minecraft:farmland",
            "setblock 2 149 -1 minecraft:water",
            "setblock 0 150 -1 mysticalagriculture:inferium_crop[age=1]"]:
    proc.stdin.write(cmd + "\n"); proc.stdin.flush(); time.sleep(1.2)
try: bot.wait(500)
except subprocess.TimeoutExpired: bot.kill()
time.sleep(3)
proc.stdin.write("stop\n"); proc.stdin.flush()
try: proc.wait(90)
except subprocess.TimeoutExpired: proc.kill()
print("--- server accelerator lines ---")
print("\n".join(l for l in lines if any(k in l for k in
    ("scheduled tick", "onPlace", "stopped in the mod", "Unimplemented")))[-2500:])
