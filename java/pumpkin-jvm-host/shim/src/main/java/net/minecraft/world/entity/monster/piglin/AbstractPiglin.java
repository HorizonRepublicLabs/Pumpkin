package net.minecraft.world.entity.monster.piglin;

import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import dev.pumpkin.shim.Unimplemented;

public abstract class AbstractPiglin extends Monster {

    public AbstractPiglin(EntityType<? extends AbstractPiglin> type, Level level) {
    }

    protected abstract boolean canHunt();

    protected void defineSynchedData(SynchedEntityData.Builder entityData) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/piglin/AbstractPiglin.defineSynchedData:(Lnet/minecraft/network/syncher/SynchedEntityData$Builder;)V");
    }

    protected void addAdditionalSaveData(ValueOutput output) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/piglin/AbstractPiglin.addAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueOutput;)V");
    }

    protected void readAdditionalSaveData(ValueInput input) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/piglin/AbstractPiglin.readAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueInput;)V");
    }

    protected void customServerAiStep(ServerLevel level) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/piglin/AbstractPiglin.customServerAiStep:(Lnet/minecraft/server/level/ServerLevel;)V");
    }

    public abstract PiglinArmPose getArmPose();

    public LivingEntity getTarget() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/piglin/AbstractPiglin.getTarget:()Lnet/minecraft/world/entity/LivingEntity;");
    }

    public void playAmbientSound() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/piglin/AbstractPiglin.playAmbientSound:()V");
    }

    protected abstract void playConvertedSound();

    public AbstractPiglin() {
    }
}
