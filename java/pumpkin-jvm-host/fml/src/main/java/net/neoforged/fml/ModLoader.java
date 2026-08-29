package net.neoforged.fml;

import dev.pumpkin.shim.Unimplemented;

/**
 * Hand-written, not generated: FML is published as a separate NeoForge artifact whose
 * sources are not in the decompiled tree. On the generator's "no source found" list; do
 * not delete it as un-regenerable.
 *
 * <p>Only {@code hasErrors} is modelled, which is all the manifest records the mods
 * touching. It throws rather than answering {@code false}: whether mod loading failed is
 * a fact about a loading pipeline Pumpkin does not have, so there is no honest answer,
 * and a cheerful {@code false} would let a mod proceed on a premise nothing established.
 */
public final class ModLoader {
    private ModLoader() {
    }

    public static boolean hasErrors() {
        throw Unimplemented.forMember("net/neoforged/fml/ModLoader.hasErrors:()Z");
    }
}
