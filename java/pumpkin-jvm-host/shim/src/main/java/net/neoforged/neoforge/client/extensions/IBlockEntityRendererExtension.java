package net.neoforged.neoforge.client.extensions;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.AABB;
import dev.pumpkin.shim.Unimplemented;

public interface IBlockEntityRendererExtension<T extends BlockEntity> {

    default AABB getRenderBoundingBox(T blockEntity) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/extensions/IBlockEntityRendererExtension.getRenderBoundingBox:(Lnet/minecraft/world/level/block/entity/BlockEntity;)Lnet/minecraft/world/phys/AABB;");
    }
}
