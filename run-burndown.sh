#!/usr/bin/env bash
# Boots the server against the mod jars in plugins/ and prints what the shim still lacks.
#
# Rebuilds both halves first, deliberately. regen.sh rebuilds the Java side but nothing
# rebuilds Rust, so ./target/debug/pumpkin goes stale against fresh jars -- once that
# produced a run with no plugin output at all, which reads as a regression and is not one.
set -euo pipefail
cd "$(dirname "$0")"

export JAVA_HOME=${JAVA_HOME:-/opt/homebrew/opt/openjdk@25/libexec/openjdk.jdk/Contents/Home}
SECONDS_TO_RUN=${SECONDS_TO_RUN:-60}

( cd java/pumpkin-jvm-host && ./gradlew --quiet build )
cargo build --quiet -p pumpkin --features jvm-plugins

log=$(mktemp -t burndown)
# Asked of cargo rather than hardcoded: a shared target-dir in ~/.cargo/config.toml moves
# the binary out of ./target, and a hardcoded path would quietly run a stale copy from
# before the move -- the exact staleness this script exists to prevent.
PUMPKIN_BIN=$(cargo metadata --format-version 1 --no-deps 2>/dev/null \
    | python3 -c "import json,sys; print(json.load(sys.stdin)['target_directory'])")/debug/pumpkin
timeout "$SECONDS_TO_RUN" "$PUMPKIN_BIN" > "$log" 2>&1 || true

if ! grep -q "JVM burndown" "$log"; then
    echo "No burndown line. The JVM never booted -- are there .jar plugins in plugins/?"
    grep -iE "plugin|jvm" "$log" | head -5
    echo "full log: $log"
    exit 1
fi
sed -n '/JVM burndown/,/Started server/p' "$log" | grep -v "Started server"
echo
echo "first failure:"
grep -m1 -A2 "Caused by: dev.pumpkin.shim.Unimplemented" "$log" | head -3
echo "full log: $log"
