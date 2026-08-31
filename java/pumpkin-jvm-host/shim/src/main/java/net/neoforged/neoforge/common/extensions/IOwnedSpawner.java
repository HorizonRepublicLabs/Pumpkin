package net.neoforged.neoforge.common.extensions;

import com.mojang.datafixers.util.Either;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.entity.BlockEntity;

public interface IOwnedSpawner {

    Either<BlockEntity, Entity> getOwner();
}
