package net.neoforged.fml.config;

/**
 * Hand-written, not generated: FML is published as a separate artifact whose sources are
 * not in the decompiled tree. On the generator's "no source found" list; do not delete it
 * as un-regenerable.
 *
 * <p>Only the nested {@link Type} is modelled, because that is all the manifest records the
 * mods touching: {@code ModConfig.Type.COMMON}, {@code CLIENT} and {@code STARTUP}.
 */
public class ModConfig {
    /** Which phase and which side a config file belongs to. */
    public enum Type {
        STARTUP,
        CLIENT,
        COMMON,
        SERVER
    }
}
