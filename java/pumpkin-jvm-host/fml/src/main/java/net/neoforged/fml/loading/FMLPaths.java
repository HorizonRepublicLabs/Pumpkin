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
 * <p>{@link #get()} throws. Where Pumpkin keeps its config is a real fact, but it lives on
 * the Rust side and is not reachable from here; answering with a guessed path would have a
 * mod write its config somewhere nothing reads.
 */
public enum FMLPaths {
    GAMEDIR,
    MODSDIR,
    CONFIGDIR,
    FMLCONFIG;

    public Path get() {
        throw Unimplemented.forMember("net/neoforged/fml/loading/FMLPaths.get:()Ljava/nio/file/Path;");
    }
}
