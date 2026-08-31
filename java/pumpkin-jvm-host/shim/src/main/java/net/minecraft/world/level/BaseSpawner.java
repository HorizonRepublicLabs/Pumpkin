package net.minecraft.world.level;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.common.extensions.IOwnedSpawner;
import dev.pumpkin.shim.Unimplemented;

public abstract class BaseSpawner implements IOwnedSpawner {

    public abstract void broadcastEvent(final Level level, final BlockPos pos, int id);

    public com.mojang.datafixers.util.Either<net.minecraft.world.level.block.entity.BlockEntity, Entity> getOwner() {
        throw Unimplemented.forMember("net/minecraft/world/level/BaseSpawner.getOwner:()Lcom/mojang/datafixers/util/Either;");
    }

    public BaseSpawner() {
    }
}
