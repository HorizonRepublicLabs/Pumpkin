package net.minecraft.world.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public class ExperienceOrb extends Entity {

    public ExperienceOrb(Level level, double x, double y, double z, int value) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ExperienceOrb.<init>:(Lnet/minecraft/world/level/Level;DDDI)V");
    }

    public ExperienceOrb(Level level, Vec3 pos, Vec3 roughly, int value) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ExperienceOrb.<init>:(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/world/phys/Vec3;I)V");
    }

    public ExperienceOrb(EntityType<? extends ExperienceOrb> type, Level level) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ExperienceOrb.<init>:(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/level/Level;)V");
    }

    protected Entity.MovementEmission getMovementEmission() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ExperienceOrb.getMovementEmission:()Lnet/minecraft/world/entity/Entity$MovementEmission;");
    }

    protected void defineSynchedData(SynchedEntityData.Builder entityData) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ExperienceOrb.defineSynchedData:(Lnet/minecraft/network/syncher/SynchedEntityData$Builder;)V");
    }

    protected double getDefaultGravity() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ExperienceOrb.getDefaultGravity:()D");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ExperienceOrb.tick:()V");
    }

    protected float getAirDrag() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ExperienceOrb.getAirDrag:()F");
    }

    public BlockPos getBlockPosBelowThatAffectsMyMovement() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ExperienceOrb.getBlockPosBelowThatAffectsMyMovement:()Lnet/minecraft/core/BlockPos;");
    }

    protected void doWaterSplashEffect() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ExperienceOrb.doWaterSplashEffect:()V");
    }

    public final boolean hurtClient(DamageSource source) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ExperienceOrb.hurtClient:(Lnet/minecraft/world/damagesource/DamageSource;)Z");
    }

    public final boolean hurtServer(ServerLevel level, DamageSource source, float damage) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ExperienceOrb.hurtServer:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;F)Z");
    }

    protected void addAdditionalSaveData(ValueOutput output) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ExperienceOrb.addAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueOutput;)V");
    }

    protected void readAdditionalSaveData(ValueInput input) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ExperienceOrb.readAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueInput;)V");
    }

    public void playerTouch(Player player) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ExperienceOrb.playerTouch:(Lnet/minecraft/world/entity/player/Player;)V");
    }

    public int getValue() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ExperienceOrb.getValue:()I");
    }

    public void setValue(int value) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ExperienceOrb.setValue:(I)V");
    }

    public boolean isAttackable() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ExperienceOrb.isAttackable:()Z");
    }

    public SoundSource getSoundSource() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ExperienceOrb.getSoundSource:()Lnet/minecraft/sounds/SoundSource;");
    }

    public InterpolationHandler getInterpolation() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ExperienceOrb.getInterpolation:()Lnet/minecraft/world/entity/InterpolationHandler;");
    }

    public ExperienceOrb() {
    }
}
