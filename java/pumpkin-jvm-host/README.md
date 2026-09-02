# Running Pumpkin with NeoForge mods

Pumpkin loads NeoForge mods by starting a JVM inside the server process and giving the
mod a generated stand-in for Minecraft and NeoForge — the *shim*. The mod's own code runs;
the shim answers it out of Pumpkin's world.

Targets **NeoForge 26.2** mod jars. Exercised against Mekanism (+ Generators, Tools,
Additions), MysticalAgriculture and Cucumber.

## What you need

- **JDK 25.** The Gradle toolchain asks for exactly this
  (`JavaLanguageVersion.of(25)`), and the server discovers the JVM to boot through
  `JAVA_HOME`.
- A Rust toolchain, as for any Pumpkin build.

`JAVA_HOME` must be exported for the Gradle build **and** for the server itself. Without
it the server starts, fails to boot the JVM, and reports every mod as
`Failed to start the JVM: Couldn't automatically discover the Java VM's location` — the
server keeps running, just with no mods.

```bash
export JAVA_HOME=/path/to/jdk-25    # macOS/Homebrew: /opt/homebrew/opt/openjdk@25/libexec/openjdk.jdk/Contents/Home
```

## Build

Two halves: the Java host, then the server.

```bash
# 1. The shim, the mini-FML surface and the host bridge.
cd java/pumpkin-jvm-host
./gradlew build

# 2. The game libraries the shim needs at runtime. Once per checkout.
./gradlew :shim:collectRuntimeLibs

# 3. The server, with the JVM plugin loader compiled in.
cd ../..
cargo build --release --features jvm-plugins -p pumpkin
```

Step 2 is separate on purpose and is not part of `build`. The shim *compiles* against the
game libraries but does not bundle them, so a JVM booted on the three jars alone loads the
shim and then dies on the first mod with `NoClassDefFoundError: org/slf4j/LoggerFactory`.
`collectRuntimeLibs` materialises those libraries into `libs/`.

The `jvm-plugins` feature is what compiles the JVM plugin loader in at all; without it
the server builds and runs, but nothing is registered that can load a `.jar`.

## Set up a server directory

The JVM classpath is resolved **relative to the working directory** the server runs in, as
`java/pumpkin-jvm-host/{shim,fml,host}/build/libs/*.jar` plus `java/pumpkin-jvm-host/libs/`.
So either run from the repository root, or give your server directory a `java` symlink:

```bash
mkdir -p ~/myserver/mods
cd ~/myserver
ln -s /path/to/Pumpkin/java java
cp /path/to/*.jar mods/          # NeoForge 26.2 mod jars
```

If the classpath cannot be found the server says so by name and keeps going without mods:
`classpath entry ... does not exist; if the server was started from an ...`.

## Configure

Mods are plugins, and the plugin system has to be told to accept them. In `pumpkin.toml`:

```toml
[plugins]
enabled = true
allow_unsigned = true
ask_permission_confirmation = false
```

All three matter:

- `enabled` — off, nothing in `mods/` is looked at.
- `allow_unsigned` — **jar signature verification is not implemented at all**, so with
  this false every jar is refused outright rather than loaded unverified. See the
  security note below before turning it on.
- `ask_permission_confirmation` — left at its default of `true`, the server stops and
  waits for a console answer while loading, and a server started non-interactively never
  gets past it. This is the quiet one: the symptom is mods that simply never appear.

## Run

```bash
cd ~/myserver
JAVA_HOME=/path/to/jdk-25 /path/to/Pumpkin/target/release/pumpkin
```

A healthy boot includes these lines (their exact order varies):

```
Starting the JVM with classpath java/pumpkin-jvm-host/shim/build/libs/shim.jar:...
[mod/INFO] Mystical Agriculture: Loaded 1 plugins
Mod item tags: 351 tag(s) loaded
JVM burndown: no stubbed shim member was reached this boot
Mod worldgen: 2 ore feature(s) will generate in new chunks; 1 refused
Server is now running.
```

The **burndown** line is the one worth reading. It names every stubbed shim member a mod
actually reached this boot — that is, every place a mod asked for something the shim does
not implement. "No stubbed shim member was reached" means nothing went unanswered.

## Reading the log

The bridge is deliberately loud about what it cannot do, because a mod that is quietly
half-supported is worse than one that visibly is not. Lines to expect:

- `[mod/LEVEL] name: message` — the mod's own logging, passed through.
- `[pumpkin] ...` — the bridge answering honestly about a limit. `registered a config`
  means the mod's config values are its declared defaults; editing a config file does
  nothing yet.
- `not placeable by this server: ... is the mod's own feature type` — a worldgen feature
  whose behaviour lives in the mod's Java code. Refused rather than guessed at.
- `X: its mod ticker is running` — said once per block type, the first time a mod's block
  entity ticks.

## Security

A JVM mod runs **in-process, in the server's JVM, with full privileges**, and its jar's
signature is never checked. `allow_unsigned = true` is not a formality — it is you
accepting that. Load only mod jars you would be willing to run as arbitrary code on that
machine, from a source you trust.

## What works

Verified end to end by the smokes in `tools/mod-smoke/`, each of which boots a real server,
drives a real client, and reads the result back out of the saved world:

| Smoke | What it proves |
| --- | --- |
| `mekhopper.py` | a machine smelts, and a vanilla hopper pulls its output |
| `mekcable.py` | a universal cable carries energy between machines |
| `mekpipe.py` | mechanical pipes and pressurised tubes carry fluid and chemicals |
| `mektrans.py` | a logistical transporter moves items between vanilla chests |
| `mekfacing.py` | a machine faces the way the player who placed it was looking |
| `modore.py` | a mod's ore really generates in the ground |
| `menudrive.py` | a machine GUI opens and its slots respond to clicks |

Most take just the server directory:

```bash
JAVA_HOME=/path/to/jdk-25 python3 tools/mod-smoke/mektrans.py ~/myserver
```

`mekhopper.py` and `mekcable.py` also need the path to the bot that drives them, and
`menudrive.py` is the odd one out — it takes the server binary and its bot, and is run
from inside the server directory:

```bash
python3 tools/mod-smoke/mekhopper.py ~/myserver "$PWD/tools/mod-smoke/mekpowerbot.py"
python3 tools/mod-smoke/mekcable.py  ~/myserver "$PWD/tools/mod-smoke/mekcablebot.py"
(cd ~/myserver && python3 /path/to/Pumpkin/tools/mod-smoke/menudrive.py \
    /path/to/Pumpkin/target/debug/pumpkin /path/to/Pumpkin/tools/mod-smoke/menubot.py)
```

Each starts from an empty world, so delete `world/` between runs.

## Known limits

These are answered loudly rather than faked:

- Mod config files are not read or written; a mod's config values are its declared
  defaults.
- Data maps are not implemented; a mod asking for one gets a refusal naming the member,
  which is what puts it in the burndown line rather than letting it pass unnoticed.
- A worldgen feature whose type belongs to the mod is refused, because what it places is
  decided by the mod's own code.
- Custom packets to clients are dropped — no connected client can decode a mod's packets,
  so what is lost is presentation, not server state.

## Changing the shim

The shim under `shim/src/main/java/net/**` and `com/mojang/**` is **generated**, and
`regen.sh` wipes and regenerates it. Hand edits there are replayed from
`generator/reconcile.py`, so every divergence must be recorded as an `edit(...)` entry —
otherwise the next regeneration silently undoes it. Files listed in that script's `PINNED`
set are installed wholesale from `generator/pinned/`, so those must be edited in **both**
places.

After any change:

```bash
cd java/pumpkin-jvm-host
./gradlew build test
./regen.sh            # must replay every recorded edit and leave the tree unchanged
```
