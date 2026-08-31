package net.minecraft.world.entity;

import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import dev.pumpkin.shim.Unimplemented;

public class LightningBolt extends Entity {

    public LightningBolt(EntityType<? extends LightningBolt> type, Level level) {
    }

    public void setVisualOnly(boolean visualOnly) {
        throw Unimplemented.forMember("net/minecraft/world/entity/LightningBolt.setVisualOnly:(Z)V");
    }

    public SoundSource getSoundSource() {
        throw Unimplemented.forMember("net/minecraft/world/entity/LightningBolt.getSoundSource:()Lnet/minecraft/sounds/SoundSource;");
    }

    public void setDamage(float damage) {
        throw Unimplemented.forMember("net/minecraft/world/entity/LightningBolt.setDamage:(F)V");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/world/entity/LightningBolt.tick:()V");
    }

    public boolean shouldRenderAtSqrDistance(double distance) {
        throw Unimplemented.forMember("net/minecraft/world/entity/LightningBolt.shouldRenderAtSqrDistance:(D)Z");
    }

    protected void defineSynchedData(SynchedEntityData.Builder entityData) {
        throw Unimplemented.forMember("net/minecraft/world/entity/LightningBolt.defineSynchedData:(Lnet/minecraft/network/syncher/SynchedEntityData$Builder;)V");
    }

    protected void readAdditionalSaveData(ValueInput input) {
        throw Unimplemented.forMember("net/minecraft/world/entity/LightningBolt.readAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueInput;)V");
    }

    protected void addAdditionalSaveData(ValueOutput output) {
        throw Unimplemented.forMember("net/minecraft/world/entity/LightningBolt.addAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueOutput;)V");
    }

    public final boolean hurtServer(ServerLevel level, DamageSource source, float damage) {
        throw Unimplemented.forMember("net/minecraft/world/entity/LightningBolt.hurtServer:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;F)Z");
    }

    public LightningBolt() {
    }
}
