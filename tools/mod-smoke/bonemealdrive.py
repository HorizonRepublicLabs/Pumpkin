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
for cmd in ["setblock 0 149 -1 minecraft:farmland",
            "setblock 0 150 -1 mysticalagriculture:inferium_crop[age=1]",
            "tp PumpkinBot 0 150 1",
            "give PumpkinBot minecraft:bone_meal 16"]:
    proc.stdin.write(cmd + "\n"); proc.stdin.flush(); time.sleep(1.2)
try: bot.wait(120)
except subprocess.TimeoutExpired: bot.kill()
time.sleep(3)
proc.stdin.write("stop\n"); proc.stdin.flush()
try: proc.wait(90)
except subprocess.TimeoutExpired: proc.kill()
print("--- server bonemeal/errors ---")
print("\n".join(l for l in lines if any(k in l for k in
    ("bonemeal", "Unimplemented", "ERROR", "stopped in the mod")))[-2500:])
