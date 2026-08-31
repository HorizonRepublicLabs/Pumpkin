package net.neoforged.neoforge.common;

import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import dev.pumpkin.shim.Unimplemented;

public interface IShearable {

    default boolean isShearable(Player player, ItemStack item, Level level, BlockPos pos) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/IShearable.isShearable:(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;)Z");
    }

    default List<ItemStack> onSheared(Player player, ItemStack item, Level level, BlockPos pos) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/IShearable.onSheared:(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;)Ljava/util/List;");
    }

    default void spawnShearedDrop(ServerLevel level, BlockPos pos, ItemStack drop) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/IShearable.spawnShearedDrop:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/item/ItemStack;)V");
    }
}
