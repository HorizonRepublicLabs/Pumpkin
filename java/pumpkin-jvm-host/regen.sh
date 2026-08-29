#!/bin/sh
# Regenerates the shim from the decompiled NeoForge tree and the real mod jars.
# Not run in CI: it needs the NeoForge, MysticalAgriculture and Cucumber checkouts,
# which live beside the Pumpkin repo and are not vendored.
set -e
cd "$(dirname "$0")"
./gradlew :testmod:jar

# Clear the generated roots first. Per-file emission is deterministic, but overwriting in
# place is not the same as regenerating: a class that leaves the used set leaves an orphan
# .java behind, which still compiles and never shows up in a diff of a later run. The
# committed tree has to be a function of the inputs, not of every run that ever happened.
#
# Only these three, and only under shim/. They are exactly the packages Shimmed.isShimmed
# accepts -- net/minecraft, net/neoforged and the three decompiled com/mojang ones -- and
# `find shim/src/main/java -name '*.java'` outside them is empty, so nothing hand-written
# lives here. fml/ is never touched: it holds the classes NeoForge publishes elsewhere and
# this repo writes by hand, plus Unimplemented, and the generator could not put any of them
# back.
for generated in shim/src/main/java/net/minecraft \
                 shim/src/main/java/net/neoforged \
                 shim/src/main/java/com/mojang; do
    rm -rf "$generated"
done
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
