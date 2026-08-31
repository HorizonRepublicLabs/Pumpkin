package net.minecraft.core.dispenser;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import dev.pumpkin.shim.Unimplemented;

public class SulfurCubeBlockDispenseItemBehavior extends DefaultDispenseItemBehavior {

    protected ItemStack execute(BlockSource source, ItemStack dispensed) {
        throw Unimplemented.forMember("net/minecraft/core/dispenser/SulfurCubeBlockDispenseItemBehavior.execute:(Lnet/minecraft/core/dispenser/BlockSource;Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/world/item/ItemStack;");
    }

    public static boolean dispenseBlock(ServerLevel level, BlockPos pos, ItemStack dispensed) {
        throw Unimplemented.forMember("net/minecraft/core/dispenser/SulfurCubeBlockDispenseItemBehavior.dispenseBlock:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/item/ItemStack;)Z");
    }

    public SulfurCubeBlockDispenseItemBehavior() {
    }
}
