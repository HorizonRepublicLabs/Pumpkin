package net.minecraft.world.entity.monster;

import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import dev.pumpkin.shim.Unimplemented;

public class Creeper extends Monster {

    private int explosionRadius;

    public Creeper(EntityType<? extends Creeper> type, Level level) {
    }

    protected void registerGoals() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Creeper.registerGoals:()V");
    }

    public static AttributeSupplier.Builder createAttributes() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Creeper.createAttributes:()Lnet/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder;");
    }

    public int getMaxFallDistance() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Creeper.getMaxFallDistance:()I");
    }

    public boolean causeFallDamage(double fallDistance, float damageModifier, DamageSource damageSource) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Creeper.causeFallDamage:(DFLnet/minecraft/world/damagesource/DamageSource;)Z");
    }

    protected void defineSynchedData(SynchedEntityData.Builder entityData) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Creeper.defineSynchedData:(Lnet/minecraft/network/syncher/SynchedEntityData$Builder;)V");
    }

    protected void addAdditionalSaveData(ValueOutput output) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Creeper.addAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueOutput;)V");
    }

    protected void readAdditionalSaveData(ValueInput input) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Creeper.readAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueInput;)V");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Creeper.tick:()V");
    }

    public void setTarget(LivingEntity target) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Creeper.setTarget:(Lnet/minecraft/world/entity/LivingEntity;)V");
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Creeper.getHurtSound:(Lnet/minecraft/world/damagesource/DamageSource;)Lnet/minecraft/sounds/SoundEvent;");
    }

    protected SoundEvent getDeathSound() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Creeper.getDeathSound:()Lnet/minecraft/sounds/SoundEvent;");
    }

    public boolean killedEntity(ServerLevel level, LivingEntity entity, DamageSource source) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Creeper.killedEntity:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/damagesource/DamageSource;)Z");
    }

    public boolean doHurtTarget(ServerLevel level, Entity target) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Creeper.doHurtTarget:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/Entity;)Z");
    }

    public boolean isPowered() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Creeper.isPowered:()Z");
    }

    public float getSwelling(float a) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Creeper.getSwelling:(F)F");
    }

    public void thunderHit(ServerLevel level, LightningBolt lightningBolt) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Creeper.thunderHit:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/LightningBolt;)V");
    }

    protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Creeper.mobInteract:(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/InteractionResult;");
    }

    private void spawnLingeringCloud() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/Creeper.spawnLingeringCloud:()V");
    }

    public Creeper() {
    }
}
