package net.minecraft.core.dispenser;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import dev.pumpkin.shim.Unimplemented;

public class ShearsDispenseItemBehavior extends OptionalDispenseItemBehavior {

    protected ItemStack execute(BlockSource source, ItemStack dispensed) {
        throw Unimplemented.forMember("net/minecraft/core/dispenser/ShearsDispenseItemBehavior.execute:(Lnet/minecraft/core/dispenser/BlockSource;Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/world/item/ItemStack;");
    }

    private static boolean tryShearBeehive(ServerLevel level, ItemStack tool, BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/core/dispenser/ShearsDispenseItemBehavior.tryShearBeehive:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/core/BlockPos;)Z");
    }

    public ShearsDispenseItemBehavior() {
    }
}
