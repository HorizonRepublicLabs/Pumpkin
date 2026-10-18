"""Settings every smoke script shares, so a version bump is one edit."""
import os

# Protocol of the Minecraft version Pumpkin serves (26.3); see
# crates/pumpkin-util/src/version.rs.
PROTO = 777

# Items at or past this id are registered at runtime: vanilla's item count in 26.3,
# `Item::BASE_COUNT` in crates/pumpkin-data/src/generated/item.rs.
ITEM_BASE_COUNT = 1658

# The server binary the drivers boot. Override with PUMPKIN_BIN.
PUMPKIN_BIN = os.environ.get(
    "PUMPKIN_BIN", os.path.expanduser("~/.cargo/target/debug/pumpkin")
)
