package net.neoforged.neoforge.common.extensions;

import net.neoforged.neoforge.registries.datamaps.DataMapType;
import net.neoforged.neoforge.registries.datamaps.IWithData;
import dev.pumpkin.shim.Unimplemented;

public interface TypedInstanceExtension<T> extends IWithData<T> {

    // Pumpkin divergence: data maps (datapack JSON attaching values to registry
    // entries) are not loaded yet, so every entry answers "no data" -- which is a
    // real answer for most entries and a dropped one for the few with shipped maps.
    // Said out loud once per map so the gap is visible.
    java.util.Set<String> PUMPKIN_SAID = java.util.concurrent.ConcurrentHashMap.newKeySet();

    default <D> D getData(DataMapType<T, D> type) {
        if (PUMPKIN_SAID.add(String.valueOf(type))) {
            System.err.println("[pumpkin] data map " + type + " consulted; data maps are not"
                    + " loaded yet, so every entry answers as having none");
        }
        return null;
    }
}
