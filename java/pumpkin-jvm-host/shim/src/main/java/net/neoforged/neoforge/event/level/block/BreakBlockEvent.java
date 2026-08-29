package net.neoforged.neoforge.event.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.ICancellableEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import dev.pumpkin.shim.Unimplemented;

public class BreakBlockEvent extends BlockEvent implements ICancellableEvent {

    public BreakBlockEvent(Level level, BlockPos pos, BlockState state, Player player) {
    }

    public Player getPlayer() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/level/block/BreakBlockEvent.getPlayer:()Lnet/minecraft/world/entity/player/Player;");
    }

    public void setCanceled(boolean canceled) {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/level/block/BreakBlockEvent.setCanceled:(Z)V");
    }

    public BreakBlockEvent() {
    }
}
