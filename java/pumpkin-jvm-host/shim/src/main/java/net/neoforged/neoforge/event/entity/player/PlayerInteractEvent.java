package net.neoforged.neoforge.event.entity.player;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.TriState;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.ICancellableEvent;
import dev.pumpkin.shim.Unimplemented;

public abstract class PlayerInteractEvent extends PlayerEvent {

    protected PlayerInteractEvent(Player player, InteractionHand hand, BlockPos pos, Direction face) {
    }

    public static class EntityInteract extends PlayerInteractEvent implements ICancellableEvent {

        public EntityInteract(Player player, InteractionHand hand, Entity target, Vec3 location) {
        }

        public Entity getTarget() {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/player/PlayerInteractEvent$EntityInteract.getTarget:()Lnet/minecraft/world/entity/Entity;");
        }

        public EntityInteract() {
        }
    }

    public static class RightClickBlock extends PlayerInteractEvent implements ICancellableEvent {

        public RightClickBlock(Player player, InteractionHand hand, BlockPos pos, BlockHitResult hitVec) {
        }

        public void setUseBlock(TriState triggerBlock) {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/player/PlayerInteractEvent$RightClickBlock.setUseBlock:(Lnet/minecraft/util/TriState;)V");
        }

        public void setUseItem(TriState triggerItem) {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/player/PlayerInteractEvent$RightClickBlock.setUseItem:(Lnet/minecraft/util/TriState;)V");
        }

        public void setCanceled(boolean canceled) {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/player/PlayerInteractEvent$RightClickBlock.setCanceled:(Z)V");
        }

        public RightClickBlock() {
        }
    }

    public static class RightClickItem extends PlayerInteractEvent implements ICancellableEvent {

        public RightClickItem(Player player, InteractionHand hand) {
        }

        public RightClickItem() {
        }
    }

    public static class RightClickEmpty extends PlayerInteractEvent {

        public RightClickEmpty(Player player, InteractionHand hand) {
        }

        public RightClickEmpty() {
        }
    }

    public static class LeftClickBlock extends PlayerInteractEvent implements ICancellableEvent {

        public LeftClickBlock(Player player, BlockPos pos, Direction face, Action action) {
        }

        public void setCanceled(boolean canceled) {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/player/PlayerInteractEvent$LeftClickBlock.setCanceled:(Z)V");
        }

        public enum Action {

            START, STOP, ABORT, CLIENT_HOLD
        }

        public LeftClickBlock() {
        }
    }

    public static class LeftClickEmpty extends PlayerInteractEvent {

        public LeftClickEmpty(Player player) {
        }

        public LeftClickEmpty() {
        }
    }

    public InteractionHand getHand() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/player/PlayerInteractEvent.getHand:()Lnet/minecraft/world/InteractionHand;");
    }

    public BlockPos getPos() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/player/PlayerInteractEvent.getPos:()Lnet/minecraft/core/BlockPos;");
    }

    public Level getLevel() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/player/PlayerInteractEvent.getLevel:()Lnet/minecraft/world/level/Level;");
    }

    public PlayerInteractEvent() {
    }
}
