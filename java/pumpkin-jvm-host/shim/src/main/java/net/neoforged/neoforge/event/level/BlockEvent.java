package net.neoforged.neoforge.event.level;

import java.util.EnumSet;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.portal.PortalShape;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.ICancellableEvent;
import net.neoforged.neoforge.common.ItemAbility;
import net.neoforged.neoforge.common.util.BlockSnapshot;
import dev.pumpkin.shim.Unimplemented;

public abstract class BlockEvent extends Event {

    public BlockEvent(LevelAccessor level, BlockPos pos, BlockState state) {
    }

    public LevelAccessor getLevel() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/level/BlockEvent.getLevel:()Lnet/minecraft/world/level/LevelAccessor;");
    }

    public BlockPos getPos() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/level/BlockEvent.getPos:()Lnet/minecraft/core/BlockPos;");
    }

    public BlockState getState() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/level/BlockEvent.getState:()Lnet/minecraft/world/level/block/state/BlockState;");
    }

    public static class EntityPlaceEvent extends BlockEvent implements ICancellableEvent {

        public EntityPlaceEvent(BlockSnapshot blockSnapshot, BlockState placedAgainst, Entity entity) {
        }

        public EntityPlaceEvent() {
        }
    }

    public static class EntityMultiPlaceEvent extends EntityPlaceEvent implements ICancellableEvent {

        public EntityMultiPlaceEvent(List<BlockSnapshot> blockSnapshots, BlockState placedAgainst, Entity entity) {
        }

        public EntityMultiPlaceEvent() {
        }
    }

    public static class NeighborNotifyEvent extends BlockEvent implements ICancellableEvent {

        public NeighborNotifyEvent(Level level, BlockPos pos, BlockState state, EnumSet<Direction> notifiedSides, boolean forceRedstoneUpdate) {
        }

        public NeighborNotifyEvent() {
        }
    }

    public static class FluidPlaceBlockEvent extends BlockEvent implements ICancellableEvent {

        public FluidPlaceBlockEvent(LevelAccessor level, BlockPos pos, BlockPos liquidPos, BlockState state) {
        }

        public FluidPlaceBlockEvent() {
        }
    }

    public static class FarmlandTrampleEvent extends BlockEvent implements ICancellableEvent {

        public FarmlandTrampleEvent(Level level, BlockPos pos, BlockState state, double fallDistance, Entity entity) {
        }

        public FarmlandTrampleEvent() {
        }
    }

    public static class PortalSpawnEvent extends BlockEvent implements ICancellableEvent {

        public PortalSpawnEvent(LevelAccessor level, BlockPos pos, BlockState state, PortalShape size) {
        }

        public PortalSpawnEvent() {
        }
    }

    public static class BlockToolModificationEvent extends BlockEvent implements ICancellableEvent {

        public BlockToolModificationEvent(BlockState originalState, UseOnContext context, ItemAbility itemAbility, boolean simulate) {
        }

        public Player getPlayer() {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/level/BlockEvent$BlockToolModificationEvent.getPlayer:()Lnet/minecraft/world/entity/player/Player;");
        }

        public BlockToolModificationEvent() {
        }
    }

    public BlockEvent() {
    }
}
