# Mod smoke test

Proves that content registered for a converted mod reaches a real client over
the wire: a headless offline-mode client logs in, the driver runs
`give PumpkinBot mysticalagriculture:prosperity_ingot 1` on the server console, and
the bot asserts an item id at or above `ITEM_BASE_COUNT` (in `smoke_env.py`) arrives in its
inventory packets.

## Running

Needs a server directory with `online_mode = false`, encryption and compression
disabled, port 25599, and the converted mod installed (see `tools/mod-converter`). Then:

    python3 drive.py <server-dir> <path-to-pumpkin-binary> bot.py

`bot.py` speaks just enough of the protocol in `smoke_env.py` for login -> configuration ->
play, echoes the known-packs exchange, and watches CONTAINER_SET_SLOT (20),
SET_PLAYER_INVENTORY (110), and CONTAINER_SET_CONTENT (18). Packet ids are the
26.3 values from pumpkin-data's generated tables; bump them when the protocol moves.
