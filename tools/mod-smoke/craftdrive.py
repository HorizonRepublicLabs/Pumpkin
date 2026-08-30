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
    for line in bot.stdout:
        line = line.rstrip()
        print(line, flush=True)
        if line.startswith("REQ tp "):
            proc.stdin.write("tp PumpkinBot " + line[7:] + "\n"); proc.stdin.flush()
        if "altar input placed" in line:
            time.sleep(1)
            proc.stdin.write("setblock 1 151 0 minecraft:redstone_block\n"); proc.stdin.flush()
threading.Thread(target=breader, daemon=True).start()
time.sleep(6)
cmds = ["setblock 1 149 0 minecraft:stone"]
for dx, dz in [(3,0),(0,3),(-3,0),(0,-3),(2,2),(2,-2),(-2,2),(-2,-2)]:
    cmds.append(f"setblock {1+dx} 149 {dz} minecraft:stone")
cmds += ["give PumpkinBot mysticalagriculture:infusion_pedestal 8",
         "give PumpkinBot minecraft:gold_ingot 2",
         "give PumpkinBot mysticalagriculture:imperium_essence 4",
         "give PumpkinBot minecraft:golden_pickaxe 1",
         "give PumpkinBot minecraft:golden_pickaxe 1",
         "give PumpkinBot mysticalagriculture:blank_augment 1",
         "give PumpkinBot mysticalagriculture:infusion_altar 1"]
for cmd in cmds:
    proc.stdin.write(cmd + "\n"); proc.stdin.flush(); time.sleep(0.6)
try: bot.wait(180)
except subprocess.TimeoutExpired: bot.kill()
time.sleep(2)
proc.stdin.write("stop\n"); proc.stdin.flush()
try: proc.wait(90)
except subprocess.TimeoutExpired: proc.kill()
