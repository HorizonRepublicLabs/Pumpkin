package net.minecraft.world.entity.item;

import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TraceableEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.portal.TeleportTransition;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import dev.pumpkin.shim.Unimplemented;

public class PrimedTnt extends Entity implements TraceableEntity {

    private float explosionPower;

    public PrimedTnt(EntityType<? extends PrimedTnt> type, Level level) {
    }

    public PrimedTnt(Level level, double x, double y, double z, LivingEntity owner) {
    }

    protected void defineSynchedData(SynchedEntityData.Builder entityData) {
        throw Unimplemented.forMember("net/minecraft/world/entity/item/PrimedTnt.defineSynchedData:(Lnet/minecraft/network/syncher/SynchedEntityData$Builder;)V");
    }

    protected Entity.MovementEmission getMovementEmission() {
        throw Unimplemented.forMember("net/minecraft/world/entity/item/PrimedTnt.getMovementEmission:()Lnet/minecraft/world/entity/Entity$MovementEmission;");
    }

    public boolean isPickable() {
        throw Unimplemented.forMember("net/minecraft/world/entity/item/PrimedTnt.isPickable:()Z");
    }

    protected double getDefaultGravity() {
        throw Unimplemented.forMember("net/minecraft/world/entity/item/PrimedTnt.getDefaultGravity:()D");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/world/entity/item/PrimedTnt.tick:()V");
    }

    protected void addAdditionalSaveData(ValueOutput output) {
        throw Unimplemented.forMember("net/minecraft/world/entity/item/PrimedTnt.addAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueOutput;)V");
    }

    protected void readAdditionalSaveData(ValueInput input) {
        throw Unimplemented.forMember("net/minecraft/world/entity/item/PrimedTnt.readAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueInput;)V");
    }

    public LivingEntity getOwner() {
        throw Unimplemented.forMember("net/minecraft/world/entity/item/PrimedTnt.getOwner:()Lnet/minecraft/world/entity/LivingEntity;");
    }

    public void restoreFrom(Entity oldEntity) {
        throw Unimplemented.forMember("net/minecraft/world/entity/item/PrimedTnt.restoreFrom:(Lnet/minecraft/world/entity/Entity;)V");
    }

    public void setFuse(int time) {
        throw Unimplemented.forMember("net/minecraft/world/entity/item/PrimedTnt.setFuse:(I)V");
    }

    public int getFuse() {
        throw Unimplemented.forMember("net/minecraft/world/entity/item/PrimedTnt.getFuse:()I");
    }

    public void setBlockState(BlockState blockState) {
        throw Unimplemented.forMember("net/minecraft/world/entity/item/PrimedTnt.setBlockState:(Lnet/minecraft/world/level/block/state/BlockState;)V");
    }

    public Entity teleport(TeleportTransition transition) {
        throw Unimplemented.forMember("net/minecraft/world/entity/item/PrimedTnt.teleport:(Lnet/minecraft/world/level/portal/TeleportTransition;)Lnet/minecraft/world/entity/Entity;");
    }

    public final boolean hurtServer(ServerLevel level, DamageSource source, float damage) {
        throw Unimplemented.forMember("net/minecraft/world/entity/item/PrimedTnt.hurtServer:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;F)Z");
    }

    public PrimedTnt() {
    }
}
