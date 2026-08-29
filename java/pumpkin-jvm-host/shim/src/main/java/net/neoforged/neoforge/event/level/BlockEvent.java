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
        throw Unimplemented.forMember("net/neoforged/neoforge/event/level/BlockEvent.<init>:(Lnet/minecraft/world/level/LevelAccessor;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)V");
    }

    public LevelAccessor getLevel() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/level/BlockEvent.getLevel:()Lnet/minecraft/world/level/LevelAccessor;");
    }

    public BlockPos getPos() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/level/BlockEvent.getPos:()Lnet/minecraft/core/BlockPos;");
    }

    public static class EntityPlaceEvent extends BlockEvent implements ICancellableEvent {

        public EntityPlaceEvent(BlockSnapshot blockSnapshot, BlockState placedAgainst, Entity entity) {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/level/BlockEvent$EntityPlaceEvent.<init>:(Lnet/neoforged/neoforge/common/util/BlockSnapshot;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/entity/Entity;)V");
        }

        public EntityPlaceEvent() {
        }
    }

    public static class EntityMultiPlaceEvent extends EntityPlaceEvent implements ICancellableEvent {

        public EntityMultiPlaceEvent(List<BlockSnapshot> blockSnapshots, BlockState placedAgainst, Entity entity) {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/level/BlockEvent$EntityMultiPlaceEvent.<init>:(Ljava/util/List;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/entity/Entity;)V");
        }

        public EntityMultiPlaceEvent() {
        }
    }

    public static class NeighborNotifyEvent extends BlockEvent implements ICancellableEvent {

        public NeighborNotifyEvent(Level level, BlockPos pos, BlockState state, EnumSet<Direction> notifiedSides, boolean forceRedstoneUpdate) {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/level/BlockEvent$NeighborNotifyEvent.<init>:(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Ljava/util/EnumSet;Z)V");
        }

        public NeighborNotifyEvent() {
        }
    }

    public static class FluidPlaceBlockEvent extends BlockEvent implements ICancellableEvent {

        public FluidPlaceBlockEvent(LevelAccessor level, BlockPos pos, BlockPos liquidPos, BlockState state) {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/level/BlockEvent$FluidPlaceBlockEvent.<init>:(Lnet/minecraft/world/level/LevelAccessor;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)V");
        }

        public FluidPlaceBlockEvent() {
        }
    }

    public static class FarmlandTrampleEvent extends BlockEvent implements ICancellableEvent {

        public FarmlandTrampleEvent(Level level, BlockPos pos, BlockState state, double fallDistance, Entity entity) {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/level/BlockEvent$FarmlandTrampleEvent.<init>:(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;DLnet/minecraft/world/entity/Entity;)V");
        }

        public FarmlandTrampleEvent() {
        }
    }

    public static class PortalSpawnEvent extends BlockEvent implements ICancellableEvent {

        public PortalSpawnEvent(LevelAccessor level, BlockPos pos, BlockState state, PortalShape size) {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/level/BlockEvent$PortalSpawnEvent.<init>:(Lnet/minecraft/world/level/LevelAccessor;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/portal/PortalShape;)V");
        }

        public PortalSpawnEvent() {
        }
    }

    public static class BlockToolModificationEvent extends BlockEvent implements ICancellableEvent {

        public BlockToolModificationEvent(BlockState originalState, UseOnContext context, ItemAbility itemAbility, boolean simulate) {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/level/BlockEvent$BlockToolModificationEvent.<init>:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/item/context/UseOnContext;Lnet/neoforged/neoforge/common/ItemAbility;Z)V");
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
