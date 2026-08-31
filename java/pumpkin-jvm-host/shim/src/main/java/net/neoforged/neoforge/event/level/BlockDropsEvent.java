package net.neoforged.neoforge.event.level;

import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.ICancellableEvent;
import dev.pumpkin.shim.Unimplemented;

public class BlockDropsEvent extends BlockEvent implements ICancellableEvent {

    public BlockDropsEvent(ServerLevel level, BlockPos pos, BlockState state, BlockEntity blockEntity, List<ItemEntity> drops, Entity breaker, ItemStack tool) {
    }

    public List<ItemEntity> getDrops() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/level/BlockDropsEvent.getDrops:()Ljava/util/List;");
    }

    public void setCanceled(boolean canceled) {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/level/BlockDropsEvent.setCanceled:(Z)V");
    }

    public ServerLevel getLevel() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/level/BlockDropsEvent.getLevel:()Lnet/minecraft/server/level/ServerLevel;");
    }

    public BlockDropsEvent() {
    }
}
