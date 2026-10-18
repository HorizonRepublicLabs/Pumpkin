"""Settings every smoke script shares, so a version bump is one edit."""
import os

# Protocol of the Minecraft version Pumpkin serves (26.3); see
# crates/pumpkin-util/src/version.rs.
PROTO = 777

# The server binary the drivers boot. Override with PUMPKIN_BIN.
PUMPKIN_BIN = os.environ.get(
    "PUMPKIN_BIN", os.path.expanduser("~/.cargo/target/debug/pumpkin")
)
