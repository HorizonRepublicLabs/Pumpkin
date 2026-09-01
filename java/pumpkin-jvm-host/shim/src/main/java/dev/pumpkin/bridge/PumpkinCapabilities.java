package dev.pumpkin.bridge;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.neoforged.neoforge.capabilities.BaseCapability;
import net.neoforged.neoforge.capabilities.IBlockCapabilityProvider;
import net.neoforged.neoforge.capabilities.ICapabilityProvider;

/**
 * The providers mods registered through {@code RegisterCapabilitiesEvent}, consulted by
 * the level and item capability lookups. Keys are the interned capability tokens and the
 * registered type/block/item objects themselves -- identity is what NeoForge keys on too.
 */
public final class PumpkinCapabilities {
    private PumpkinCapabilities() {
    }

    /** capability -> block entity type -> provider. */
    public static final Map<BaseCapability<?, ?>, Map<Object, ICapabilityProvider<?, ?, ?>>> BLOCK_ENTITY =
            new ConcurrentHashMap<>();

    /** capability -> block -> provider. */
    public static final Map<BaseCapability<?, ?>, Map<Object, IBlockCapabilityProvider<?, ?>>> BLOCK =
            new ConcurrentHashMap<>();

    /** capability -> item -> provider. */
    public static final Map<BaseCapability<?, ?>, Map<Object, ICapabilityProvider<?, ?, ?>>> ITEM =
            new ConcurrentHashMap<>();

    /** capability -> entity type -> provider. */
    public static final Map<BaseCapability<?, ?>, Map<Object, ICapabilityProvider<?, ?, ?>>> ENTITY =
            new ConcurrentHashMap<>();

    public static <K, V> void put(Map<BaseCapability<?, ?>, Map<Object, V>> registry,
            BaseCapability<?, ?> capability, Object key, V provider) {
        registry.computeIfAbsent(capability, ignored -> new ConcurrentHashMap<>())
                .put(key, provider);
    }

    public static <V> V get(Map<BaseCapability<?, ?>, Map<Object, V>> registry,
            BaseCapability<?, ?> capability, Object key) {
        Map<Object, V> byKey = registry.get(capability);
        return byKey == null || key == null ? null : byKey.get(key);
    }
}
