# neoforge-probe

A synthetic NeoForge client. It performs a handshake, logs in, sends the `neoforge` brand,
answers known packs, and prints every configuration-phase custom payload the server sends —
decoding the payload setup and the registry snapshots.

It exists because the behaviour that matters only happens against a real modded client,
which makes every change slow to check and dependent on someone being at a keyboard. This
reproduces the part that can be checked automatically.

## Use

The server must be in offline mode with compression off, since the probe implements neither:

```toml
[networking.java]
encryption = false
online_mode = false

[networking.java.compression]
enabled = false
```

Then:

```sh
cargo run --release -- 127.0.0.1:25565
```

Expect the payload setup listing every negotiated channel per protocol phase, one snapshot
per synced registry with its entry count, and the sync-completed payload. Running it against
the Mystical Agriculture example plugin prints:

```
payload neoforge:network (555 bytes)
   protocol 4: 3 channel(s)
      neoforge:frozen_registry_sync_start @ 1
      ...
   protocol 1: 4 channel(s)
      mysticalagriculture:experience_pickup @ 1
      ...
payload neoforge:frozen_registry (42396 bytes)
   minecraft:block: 1408 entries, 212 from the mod
```
