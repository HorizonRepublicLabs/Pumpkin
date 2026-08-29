package net.neoforged.api.distmarker;

/**
 * Hand-written, not generated: published in a separate NeoForge artifact whose sources are
 * not in the decompiled tree. On the generator's "no source found" list; do not delete it
 * as un-regenerable.
 *
 * <p>Which side is running. Pumpkin is a server, so a mod asking gets the honest answer
 * rather than a stub that throws -- one of the few things the shim can actually answer.
 */
public enum Dist {
    CLIENT,
    DEDICATED_SERVER;

    public boolean isClient() {
        return this == CLIENT;
    }

    public boolean isDedicatedServer() {
        return this == DEDICATED_SERVER;
    }
}
