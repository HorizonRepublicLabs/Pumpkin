package net.neoforged.neoforgespi.language;

import java.util.List;

/**
 * Hand-written, not generated: {@code neoforgespi} is published as a separate NeoForge
 * artifact whose sources are not in the decompiled tree. On the generator's "no source
 * found" list; do not delete it as un-regenerable.
 *
 * <p>One mod file, which may declare several mods. Only {@code getMods} is declared, which
 * is all the manifest records the mods calling.
 */
public interface IModFileInfo {
    List<IModInfo> getMods();
}
