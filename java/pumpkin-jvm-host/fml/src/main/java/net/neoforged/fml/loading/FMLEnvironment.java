package net.neoforged.fml.loading;

import net.neoforged.api.distmarker.Dist;

/**
 * Hand-written, not generated: FML is published as a separate NeoForge artifact whose
 * sources are not in the decompiled tree. On the generator's "no source found" list; do
 * not delete it as un-regenerable.
 *
 * <p>Both members answer rather than throw, which is the exception in this shim and needs
 * its reason stated. The rule everything else follows -- never return a plausible value
 * for something the shim does not know -- is about facts Pumpkin has no access to. These
 * two are not that: Pumpkin is a dedicated server, and it is a shipped server rather than
 * a NeoForge development workspace. Throwing here would stop both mods inside their
 * constructor, on a question whose answer is not in doubt, and would make every later
 * finding unreachable.
 */
public final class FMLEnvironment {
    private FMLEnvironment() {
    }

    /** Always {@link Dist#DEDICATED_SERVER}. Pumpkin has no client and never will. */
    public static Dist getDist() {
        return Dist.DEDICATED_SERVER;
    }

    /**
     * Always {@code true}. "Production" in FML means "not running out of a NeoForge
     * development workspace with unobfuscated names and dev-only assertions", and nothing
     * that loads this class is.
     */
    public static boolean isProduction() {
        return true;
    }
}
