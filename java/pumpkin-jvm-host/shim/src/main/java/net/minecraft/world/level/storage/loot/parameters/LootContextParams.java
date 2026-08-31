package net.minecraft.world.level.storage.loot.parameters;

import net.minecraft.util.context.ContextKey;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.Vec3;

public class LootContextParams {

    // Pumpkin divergence: a real key. A mod's getDrops asks the loot builder for the
    // break position through this; identity is all the lookup needs.
    public static final ContextKey<Vec3> ORIGIN = new ContextKey<>(null);

    public static final ContextKey<BlockEntity> BLOCK_ENTITY = new ContextKey<>(null);

    public LootContextParams() {
    }

    // Pumpkin divergence: no throwing initializer. ORIGIN above is real, and stopping the
    // class over its other (still-null) keys would stop a mod that only wanted ORIGIN.
}
