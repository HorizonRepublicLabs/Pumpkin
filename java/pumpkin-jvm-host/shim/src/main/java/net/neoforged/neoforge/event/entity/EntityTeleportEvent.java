package net.neoforged.neoforge.event.entity;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrownEnderpearl;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.ICancellableEvent;
import dev.pumpkin.shim.Unimplemented;

public class EntityTeleportEvent extends EntityEvent implements ICancellableEvent {

    protected final ServerLevel targetLevel = null;

    public EntityTeleportEvent(Entity entity, ServerLevel targetLevel, double targetX, double targetY, double targetZ) {
    }

    public ServerLevel getTargetLevel() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/EntityTeleportEvent.getTargetLevel:()Lnet/minecraft/server/level/ServerLevel;");
    }

    public double getTargetX() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/EntityTeleportEvent.getTargetX:()D");
    }

    public double getTargetY() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/EntityTeleportEvent.getTargetY:()D");
    }

    public double getTargetZ() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/EntityTeleportEvent.getTargetZ:()D");
    }

    public Vec3 getTarget() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/EntityTeleportEvent.getTarget:()Lnet/minecraft/world/phys/Vec3;");
    }

    public static class TeleportCommand extends EntityTeleportEvent implements ICancellableEvent {

        public TeleportCommand(Entity entity, ServerLevel targetLevel, double targetX, double targetY, double targetZ) {
        }

        public TeleportCommand() {
        }
    }

    public static class SpreadPlayersCommand extends EntityTeleportEvent implements ICancellableEvent {

        public SpreadPlayersCommand(Entity entity, ServerLevel targetLevel, double targetX, double targetY, double targetZ) {
        }

        public SpreadPlayersCommand() {
        }
    }

    public static class EnderEntity extends EntityTeleportEvent implements ICancellableEvent {

        public EnderEntity(LivingEntity entity, double targetX, double targetY, double targetZ) {
        }

        public EnderEntity() {
        }
    }

    public static class EnderPearl extends EntityTeleportEvent implements ICancellableEvent {

        public EnderPearl(ServerPlayer entity, double targetX, double targetY, double targetZ, ThrownEnderpearl pearlEntity, float attackDamage, HitResult hitResult) {
        }

        public ServerPlayer getPlayer() {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/EntityTeleportEvent$EnderPearl.getPlayer:()Lnet/minecraft/server/level/ServerPlayer;");
        }

        public EnderPearl() {
        }
    }

    public static class ItemConsumption extends EntityTeleportEvent implements ICancellableEvent {

        public ItemConsumption(LivingEntity entity, ItemStack itemStack, double targetX, double targetY, double targetZ) {
        }

        public ItemConsumption() {
        }
    }

    public EntityTeleportEvent() {
    }
}
