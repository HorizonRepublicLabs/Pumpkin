package net.minecraft.world.entity.monster.piglin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import dev.pumpkin.shim.Unimplemented;

public class PiglinAi {

    public static void angerNearbyPiglins(ServerLevel level, Player player, boolean onlyIfTheySeeThePlayer) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/piglin/PiglinAi.angerNearbyPiglins:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/player/Player;Z)V");
    }

    private static boolean isFood(ItemStack itemStack) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/piglin/PiglinAi.isFood:(Lnet/minecraft/world/item/ItemStack;)Z");
    }

    public PiglinAi() {
    }
}
