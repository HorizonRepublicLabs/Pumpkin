package net.minecraft.world.entity;

import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.scores.PlayerTeam;
import dev.pumpkin.shim.Unimplemented;

public abstract class TamableAnimal extends Animal implements OwnableEntity {

    protected TamableAnimal(EntityType<? extends TamableAnimal> type, Level level) {
    }

    protected void defineSynchedData(SynchedEntityData.Builder entityData) {
        throw Unimplemented.forMember("net/minecraft/world/entity/TamableAnimal.defineSynchedData:(Lnet/minecraft/network/syncher/SynchedEntityData$Builder;)V");
    }

    protected void addAdditionalSaveData(ValueOutput output) {
        throw Unimplemented.forMember("net/minecraft/world/entity/TamableAnimal.addAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueOutput;)V");
    }

    protected void readAdditionalSaveData(ValueInput input) {
        throw Unimplemented.forMember("net/minecraft/world/entity/TamableAnimal.readAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueInput;)V");
    }

    public boolean canBeLeashed() {
        throw Unimplemented.forMember("net/minecraft/world/entity/TamableAnimal.canBeLeashed:()Z");
    }

    public void handleEntityEvent(byte id) {
        throw Unimplemented.forMember("net/minecraft/world/entity/TamableAnimal.handleEntityEvent:(B)V");
    }

    public EntityReference<LivingEntity> getOwnerReference() {
        throw Unimplemented.forMember("net/minecraft/world/entity/TamableAnimal.getOwnerReference:()Lnet/minecraft/world/entity/EntityReference;");
    }

    public boolean canAttack(LivingEntity target) {
        throw Unimplemented.forMember("net/minecraft/world/entity/TamableAnimal.canAttack:(Lnet/minecraft/world/entity/LivingEntity;)Z");
    }

    public PlayerTeam getTeam() {
        throw Unimplemented.forMember("net/minecraft/world/entity/TamableAnimal.getTeam:()Lnet/minecraft/world/scores/PlayerTeam;");
    }

    protected boolean considersEntityAsAlly(Entity other) {
        throw Unimplemented.forMember("net/minecraft/world/entity/TamableAnimal.considersEntityAsAlly:(Lnet/minecraft/world/entity/Entity;)Z");
    }

    public void die(DamageSource source) {
        throw Unimplemented.forMember("net/minecraft/world/entity/TamableAnimal.die:(Lnet/minecraft/world/damagesource/DamageSource;)V");
    }

    public class TamableAnimalPanicGoal extends PanicGoal {

        public TamableAnimalPanicGoal(double speedModifier, TagKey<DamageType> panicCausingDamageTypes) {
        }

        public TamableAnimalPanicGoal(double speedModifier) {
        }

        public void tick() {
            throw Unimplemented.forMember("net/minecraft/world/entity/TamableAnimal$TamableAnimalPanicGoal.tick:()V");
        }

        public TamableAnimalPanicGoal() {
        }
    }

    public TamableAnimal() {
    }
}
