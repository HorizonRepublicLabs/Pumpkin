package net.minecraft.world;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import dev.pumpkin.shim.Unimplemented;

public class Containers {

    public static void dropContents(Level level, BlockPos pos, Container container) {
        throw Unimplemented.forMember("net/minecraft/world/Containers.dropContents:(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/Container;)V");
    }

    public static void dropContents(Level level, Entity entity, Container container) {
        throw Unimplemented.forMember("net/minecraft/world/Containers.dropContents:(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/Container;)V");
    }

    private static void dropContents(Level level, double x, double y, double z, Container container) {
        throw Unimplemented.forMember("net/minecraft/world/Containers.dropContents:(Lnet/minecraft/world/level/Level;DDDLnet/minecraft/world/Container;)V");
    }

    public static void dropContents(Level level, BlockPos pos, NonNullList<ItemStack> list) {
        throw Unimplemented.forMember("net/minecraft/world/Containers.dropContents:(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/NonNullList;)V");
    }

    public static void dropItemStack(Level level, double x, double y, double z, ItemStack itemStack) {
        throw Unimplemented.forMember("net/minecraft/world/Containers.dropItemStack:(Lnet/minecraft/world/level/Level;DDDLnet/minecraft/world/item/ItemStack;)V");
    }

    public Containers() {
    }
}
