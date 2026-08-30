import subprocess, sys, time, threading, os
srv_dir, bin_path, bot = sys.argv[1], sys.argv[2], sys.argv[3]
proc = subprocess.Popen([bin_path], stdin=subprocess.PIPE, stdout=subprocess.PIPE,
                        stderr=subprocess.STDOUT, text=True, cwd=srv_dir)
lines, ready = [], threading.Event()
def reader():
    for line in proc.stdout:
        lines.append(line.rstrip())
        if "Loaded mysticalagriculture" in line: ready.set()
threading.Thread(target=reader, daemon=True).start()
if not ready.wait(120):
    proc.kill(); print("SERVER NOT READY"); print("\n".join(lines[-25:])); sys.exit(1)
time.sleep(2)
b = subprocess.Popen([sys.executable, bot], stdout=subprocess.PIPE, stderr=subprocess.STDOUT, text=True)
bot_lines = []
def breader():
    for line in b.stdout: bot_lines.append(line.rstrip()); print(line.rstrip(), flush=True)
threading.Thread(target=breader, daemon=True).start()
# wait for play state
for _ in range(60):
    if any("PLAY STATE" in l for l in bot_lines): break
    time.sleep(1)
else:
    proc.stdin.write("stop\n"); proc.stdin.flush(); b.kill()
    print("BOT NEVER REACHED PLAY"); print("\n".join(lines[-15:])); sys.exit(1)
time.sleep(2)
proc.stdin.write("give PumpkinBot mysticalagriculture:prosperity_ingot 1\n"); proc.stdin.flush()
try: b.wait(30)
except subprocess.TimeoutExpired: b.kill()
proc.stdin.write("stop\n"); proc.stdin.flush()
try: proc.wait(30)
except subprocess.TimeoutExpired: proc.kill()
print("--- server give lines ---")
print("\n".join(l for l in lines if "Gave" in l or "PumpkinBot" in l)[:2000])
