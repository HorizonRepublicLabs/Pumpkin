"""Energy across a transmitter: coal into the heat generator, a universal cable
between it and the smelter, and the ingot proves the network carried the power."""
import os, subprocess, sys

srv = sys.argv[1]; bot = sys.argv[2]; binary = "/Users/shbodya/.cargo/target/debug/pumpkin"
here = os.path.dirname(os.path.abspath(__file__))

r = subprocess.run(["python3", os.path.join(here, "mekcabledrive.py"), binary, bot],
                   cwd=srv, capture_output=True, text=True, timeout=780)
smelted = "filled=[(3, 932, 1)]" in r.stdout
print("CABLE VERDICT:", "INGOT VIA CABLE" if smelted else "no ingot")
if not smelted:
    print(r.stdout[-1500:])
sys.exit(0 if smelted else 1)
