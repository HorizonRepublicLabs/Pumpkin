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
bot = subprocess.Popen([sys.executable, sys.argv[2]], stdout=subprocess.PIPE, stderr=subprocess.STDOUT, text=True)
def breader():
    for line in bot.stdout: print(line.rstrip(), flush=True)
threading.Thread(target=breader, daemon=True).start()
time.sleep(5)
for cmd in ["setblock 0 149 0 minecraft:stone",
            "tp PumpkinBot 0 150 2",
            "give PumpkinBot mysticalagriculture:tinkering_table 1"]:
    proc.stdin.write(cmd + "\n"); proc.stdin.flush(); time.sleep(1)
try: bot.wait(60)
except subprocess.TimeoutExpired: bot.kill()
time.sleep(2)
proc.stdin.write("stop\n"); proc.stdin.flush()
try: proc.wait(90)
except subprocess.TimeoutExpired: proc.kill()
