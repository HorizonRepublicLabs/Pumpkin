package dev.pumpkin.bridge;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

/**
 * The mod-side block entities, by position.
 *
 * <p>The server's authoritative entity lives in Rust; what a mod's interaction code needs
 * is <em>its own</em> tile entity class at that position, because it downcasts what
 * {@code Level.getBlockEntity} returns. Instances are created lazily on first use and
 * live for the process -- persisting their contents back into the Rust entity is the next
 * slice.
 *
 * <p>Lives in the shim module but outside the generated roots, so a regeneration leaves
 * it alone; see regen.sh's wipe list.
 */
public final class PumpkinBlockEntities {
    private PumpkinBlockEntities() {
    }

    private static final Map<Long, BlockEntity> BY_POSITION = new ConcurrentHashMap<>();

    private static long key(int x, int y, int z) {
        return ((long) x & 0x3FF_FFFF) << 38 | ((long) z & 0x3FF_FFFF) << 12 | (y & 0xFFF);
    }

    /** The entity at a position, building it with the type's factory on first use. */
    public static BlockEntity getOrCreate(BlockEntityType<?> type, int x, int y, int z) {
        return getOrCreate(type, x, y, z, null);
    }

    // Pumpkin divergence: the block's state travels in so the entity can answer
    // getBlockState() -- mod machines read their own facing/active from it.
    public static BlockEntity getOrCreate(BlockEntityType<?> type, int x, int y, int z,
            net.minecraft.world.level.block.state.BlockState state) {
        return BY_POSITION.computeIfAbsent(key(x, y, z), ignored -> {
            BlockEntity entity = type.pumpkinCreate(new BlockPos(x, y, z), state);
            // Through the real overridable setLevel, not the shim's field setter: mods
            // hook it as their earliest world signal (Mekanism initializes transmitter
            // acceptor caches there).
            try {
                entity.setLevel(PumpkinInteractions.pumpkinLevel());
            } catch (RuntimeException e) {
                System.err.println("[pumpkin] setLevel failed for " + entity.getClass() + ": " + e);
                entity.pumpkinSetLevel(PumpkinInteractions.pumpkinLevel());
            }
            // The vanilla placement hand-off: a fresh entity reads its initial state
            // from the placing stack's components; with no stack in play, the block
            // item's declared defaults are that state (Mekanism's side configs).
            Object itemObject = net.neoforged.neoforge.registries.DeferredHolder.pumpkinResolve(
                    "minecraft:item", pumpkinBlockIdFor(entity));
            if (itemObject instanceof net.minecraft.world.item.Item item
                    && !item.pumpkinDefaultComponents().isEmpty()) {
                java.util.Map<net.minecraft.core.component.DataComponentType<?>, Object> defaults =
                        item.pumpkinDefaultComponents();
                net.minecraft.core.component.DataComponentGetter getter =
                        new net.minecraft.core.component.DataComponentGetter() {
                            @SuppressWarnings("unchecked")
                            @Override
                            public <T> T get(net.minecraft.core.component.DataComponentType<? extends T> componentType) {
                                return (T) defaults.get(componentType);
                            }
                        };
                try {
                    java.lang.reflect.Method applyComponents = PumpkinInteractions.findMethod(
                            entity.getClass(), "applyImplicitComponents", 1);
                    applyComponents.setAccessible(true);
                    applyComponents.invoke(entity, getter);
                } catch (NoSuchMethodException absent) {
                    // Nothing to apply.
                } catch (ReflectiveOperationException e) {
                    Throwable cause = e.getCause() == null ? e : e.getCause();
                    System.err.println("[pumpkin] applyImplicitComponents failed for "
                            + entity.getClass() + ": " + cause);
                }
            }
            // The vanilla add-to-chunk hand-off: clearRemoved marks the entity live,
            // and mods hook it as their joined-the-world signal (Mekanism registers
            // transmitters for network pathfinding there).
            try {
                entity.clearRemoved();
            } catch (RuntimeException e) {
                System.err.println("[pumpkin] clearRemoved failed for " + entity.getClass()
                        + ": " + e);
            }
            // The vanilla lifecycle: onLoad fires once the entity joins a level. Mods
            // finalize state there (Mekanism applies its default side configs).
            try {
                java.lang.reflect.Method onLoad =
                        PumpkinInteractions.findMethod(entity.getClass(), "onLoad", 0);
                onLoad.setAccessible(true);
                onLoad.invoke(entity);
            } catch (NoSuchMethodException absent) {
                // A mod entity without one has nothing to finalize.
            } catch (ReflectiveOperationException e) {
                Throwable cause = e.getCause() == null ? e : e.getCause();
                System.err.println("[pumpkin] onLoad failed for " + entity.getClass() + ": " + cause);
            }
            return entity;
        });
    }

    /** The registered id of the entity's own block, for finding its block item. */
    private static String pumpkinBlockIdFor(BlockEntity entity) {
        try {
            String id = entity.getBlockState().getBlock().pumpkinRegisteredId();
            return id == null ? "" : id;
        } catch (RuntimeException stateless) {
            return "";
        }
    }

    /** Whether an entity was ever created at a position this run. */
    public static boolean exists(int x, int y, int z) {
        return BY_POSITION.containsKey(key(x, y, z));
    }

    /** Forgets the entity at a position; the block is gone. */
    public static void remove(int x, int y, int z) {
        BY_POSITION.remove(key(x, y, z));
    }

    /** The entity at a position, or null if none was ever created there. */
    public static BlockEntity get(int x, int y, int z) {
        return BY_POSITION.get(key(x, y, z));
    }
}
