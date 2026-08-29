package net.neoforged.fml;

import dev.pumpkin.shim.Unimplemented;
import java.util.List;
import net.neoforged.neoforgespi.language.IModFileInfo;
import net.neoforged.neoforgespi.language.ModFileScanData;

/**
 * Hand-written, not generated: FML is published as a separate NeoForge artifact whose
 * sources are not in the decompiled tree. On the generator's "no source found" list; do
 * not delete it as un-regenerable.
 *
 * <p>The loaded-mod index. Carries the four members the manifest records the mods calling:
 * both real mods ask {@code isLoaded} whether an optional integration is present, and both
 * walk {@code getAllScanData} looking for their own plugin annotations.
 */
public class ModList {
    protected ModList() {
    }

    public static ModList get() {
        throw Unimplemented.forMember("net/neoforged/fml/ModList.get:()Lnet/neoforged/fml/ModList;");
    }

    public List<ModFileScanData> getAllScanData() {
        throw Unimplemented.forMember("net/neoforged/fml/ModList.getAllScanData:()Ljava/util/List;");
    }

    public IModFileInfo getModFileById(String modId) {
        throw Unimplemented.forMember("net/neoforged/fml/ModList.getModFileById:"
                + "(Ljava/lang/String;)Lnet/neoforged/neoforgespi/language/IModFileInfo;");
    }

    public boolean isLoaded(String modId) {
        throw Unimplemented.forMember("net/neoforged/fml/ModList.isLoaded:(Ljava/lang/String;)Z");
    }
}
