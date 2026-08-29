package net.neoforged.neoforge.event.entity.player;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.ICancellableEvent;
import dev.pumpkin.shim.Unimplemented;

public class BonemealEvent extends Event implements ICancellableEvent {

    public BonemealEvent(Player player, Level level, BlockPos pos, BlockState state, ItemStack stack) {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/player/BonemealEvent.<init>:(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/item/ItemStack;)V");
    }

    public Player getPlayer() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/player/BonemealEvent.getPlayer:()Lnet/minecraft/world/entity/player/Player;");
    }

    public Level getLevel() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/player/BonemealEvent.getLevel:()Lnet/minecraft/world/level/Level;");
    }

    public BlockPos getPos() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/player/BonemealEvent.getPos:()Lnet/minecraft/core/BlockPos;");
    }

    public ItemStack getStack() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/player/BonemealEvent.getStack:()Lnet/minecraft/world/item/ItemStack;");
    }

    public boolean isSuccessful() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/player/BonemealEvent.isSuccessful:()Z");
    }

    public void setCanceled(boolean canceled) {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/player/BonemealEvent.setCanceled:(Z)V");
    }

    protected BonemealEvent() {
    }
}
