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
            entity.pumpkinSetLevel(PumpkinInteractions.pumpkinLevel());
            return entity;
        });
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
