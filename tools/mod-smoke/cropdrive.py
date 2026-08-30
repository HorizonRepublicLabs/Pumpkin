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
time.sleep(5)
for cmd in ["fill -2 148 -2 2 148 2 minecraft:dirt",
            "fill -2 149 -2 2 149 2 minecraft:farmland",
            "setblock 0 149 0 minecraft:water",
            "tp PumpkinBot 0 150 4",
            "give PumpkinBot mysticalagriculture:inferium_seeds 32"]:
    proc.stdin.write(cmd + "\n"); proc.stdin.flush(); time.sleep(1.5)
try: bot.wait(500)
except subprocess.TimeoutExpired: bot.kill()
time.sleep(3)
proc.stdin.write("stop\n"); proc.stdin.flush()
try: proc.wait(90)
except subprocess.TimeoutExpired: proc.kill()
print("--- server tail ---")
print("\n".join(l for l in lines if any(k in l.lower() for k in
    ("gave", "filled", "grew", "error", "panic", "random tick", "warn")))[-2000:])
