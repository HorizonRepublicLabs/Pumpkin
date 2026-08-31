package net.minecraft.world.entity.projectile.throwableitemprojectile;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityReference;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.portal.TeleportTransition;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import dev.pumpkin.shim.Unimplemented;

public class ThrownEnderpearl extends ThrowableItemProjectile {

    public ThrownEnderpearl(EntityType<? extends ThrownEnderpearl> type, Level level) {
    }

    public ThrownEnderpearl(Level level, LivingEntity mob, ItemStack itemStack) {
    }

    protected Item getDefaultItem() {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/throwableitemprojectile/ThrownEnderpearl.getDefaultItem:()Lnet/minecraft/world/item/Item;");
    }

    protected void setOwner(EntityReference<Entity> owner) {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/throwableitemprojectile/ThrownEnderpearl.setOwner:(Lnet/minecraft/world/entity/EntityReference;)V");
    }

    public Entity getOwner() {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/throwableitemprojectile/ThrownEnderpearl.getOwner:()Lnet/minecraft/world/entity/Entity;");
    }

    protected void onHitEntity(EntityHitResult hitResult) {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/throwableitemprojectile/ThrownEnderpearl.onHitEntity:(Lnet/minecraft/world/phys/EntityHitResult;)V");
    }

    protected void onHit(HitResult hitResult) {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/throwableitemprojectile/ThrownEnderpearl.onHit:(Lnet/minecraft/world/phys/HitResult;)V");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/throwableitemprojectile/ThrownEnderpearl.tick:()V");
    }

    public Entity teleport(TeleportTransition transition) {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/throwableitemprojectile/ThrownEnderpearl.teleport:(Lnet/minecraft/world/level/portal/TeleportTransition;)Lnet/minecraft/world/entity/Entity;");
    }

    public boolean canTeleport(Level from, Level to) {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/throwableitemprojectile/ThrownEnderpearl.canTeleport:(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/level/Level;)Z");
    }

    protected void onInsideBlock(BlockState state) {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/throwableitemprojectile/ThrownEnderpearl.onInsideBlock:(Lnet/minecraft/world/level/block/state/BlockState;)V");
    }

    public void onRemoval(Entity.RemovalReason reason) {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/throwableitemprojectile/ThrownEnderpearl.onRemoval:(Lnet/minecraft/world/entity/Entity$RemovalReason;)V");
    }

    public void onAboveBubbleColumn(boolean dragDown, BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/throwableitemprojectile/ThrownEnderpearl.onAboveBubbleColumn:(ZLnet/minecraft/core/BlockPos;)V");
    }

    public void onInsideBubbleColumn(boolean dragDown) {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/throwableitemprojectile/ThrownEnderpearl.onInsideBubbleColumn:(Z)V");
    }

    public ThrownEnderpearl() {
    }
}
