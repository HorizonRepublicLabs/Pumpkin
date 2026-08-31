package net.neoforged.fml.loading;

import dev.pumpkin.shim.Unimplemented;
import java.nio.file.Path;

/**
 * Hand-written, not generated: FML is published as a separate NeoForge artifact whose
 * sources are not in the decompiled tree. On the generator's "no source found" list; do
 * not delete it as un-regenerable.
 *
 * <p>The well-known directories of a NeoForge installation. All four of NeoForge's
 * constants are declared, in its order, even though the manifest records only {@code
 * CONFIGDIR}: an enum's ordinals are part of its identity, and a shortened list would
 * renumber the rest.
 *
 */
public enum FMLPaths {
    GAMEDIR,
    MODSDIR,
    CONFIGDIR,
    FMLCONFIG;

    // Real bodies: these are facts about the running process. The server's working
    // directory is the game directory -- mods really do load from mods/ under it -- and
    // config/ is where a mod's file lands if it writes one. Pumpkin itself does not read
    // config files yet; the path is real even while nothing consumes it.
    // Real body: resolve against the game directory and make sure it exists -- what a
    // mod immediately does with the result anyway.
    public static Path getOrCreateGameRelativePath(Path path) {
        Path resolved = Path.of(System.getProperty("user.dir")).resolve(path);
        try {
            java.nio.file.Files.createDirectories(resolved);
        } catch (java.io.IOException e) {
            throw new java.io.UncheckedIOException(e);
        }
        return resolved;
    }

    public Path get() {
        Path gameDir = Path.of(System.getProperty("user.dir"));
        return switch (this) {
            case GAMEDIR -> gameDir;
            case MODSDIR -> gameDir.resolve("mods");
            case CONFIGDIR, FMLCONFIG -> gameDir.resolve("config");
        };
    }
}
