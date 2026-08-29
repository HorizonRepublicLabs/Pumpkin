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
 *
 * <p>Two of the four are answered and two still throw, on the line this project draws
 * everywhere: answer what Pumpkin knows, throw for what it does not have. Which mods loaded
 * is something the host established itself, so {@code isLoaded} is a fact, and answering it
 * wrongly would be worse than throwing -- a mod told an integration is absent quietly takes
 * a different path, and nothing reports that it was misled. Annotation scan data is not a
 * fact Pumpkin has at all, so those two stop.
 */
public class ModList {
    private static final ModList INSTANCE = new ModList();

    private static final java.util.Set<String> LOADED = java.util.concurrent.ConcurrentHashMap.newKeySet();

    protected ModList() {
    }

    public static ModList get() {
        return INSTANCE;
    }

    /**
     * Records a mod as loaded. Called by the host once a mod's entry class has constructed.
     *
     * <p>Not part of NeoForge's API -- there, the list is built by the loader before any mod
     * runs. Here the host discovers mods one at a time, so it tells the list as it goes.
     */
    public static void pumpkinMarkLoaded(String modId) {
        LOADED.add(modId);
    }

    public List<ModFileScanData> getAllScanData() {
        throw Unimplemented.forMember("net/neoforged/fml/ModList.getAllScanData:()Ljava/util/List;");
    }

    public IModFileInfo getModFileById(String modId) {
        throw Unimplemented.forMember("net/neoforged/fml/ModList.getModFileById:"
                + "(Ljava/lang/String;)Lnet/neoforged/neoforgespi/language/IModFileInfo;");
    }

    /**
     * Whether {@code modId} is loaded.
     *
     * <p>A fact the host owns, so it is answered. Note the ordering it implies: a mod asking
     * about another mod during construction sees only what loaded before it, which is also
     * true of NeoForge and is why mods do this in setup events rather than constructors.
     */
    public boolean isLoaded(String modId) {
        return LOADED.contains(modId);
    }
}
