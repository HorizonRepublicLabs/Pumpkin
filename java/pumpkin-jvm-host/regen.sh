#!/bin/sh
# Regenerates the shim from the decompiled NeoForge tree and the real mod jars.
# Not run in CI: it needs the NeoForge, MysticalAgriculture and Cucumber checkouts,
# which live beside the Pumpkin repo and are not vendored.
set -e
cd "$(dirname "$0")"
./gradlew :testmod:jar
./gradlew :generator:generateShim --args="\
 --mc-sources ../../../NeoForge/projects/neoforge/src/main/java \
 --mc-sources ../../../NeoForge/projects/neoforge/src/client/java \
 --mc-sources ../../../NeoForge/projects/base/src/main/java \
 --neoforge-sources ../../../NeoForge/src/main/java \
 --neoforge-sources ../../../NeoForge/src/client/java \
 --mod-jar ../../../MysticalAgriculture/build/libs/MysticalAgriculture-26.2-9.0.7.jar \
 --mod-jar ../../../Cucumber/build/libs/Cucumber-26.2-9.0.5.jar \
 --mod-jar testmod/build/libs/testmod.jar \
 --absent-type com.mojang.authlib.yggdrasil.response.NameAndId \
 --out-shim shim/src/main/java --out-fml shim/src/main/java \
 --manifest generator/used-set.txt"

# Generation overwrites the eight files that carry Pumpkin's own behaviour. Put it back.
python3 generator/reconcile.py
