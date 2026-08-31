package net.minecraft.world.entity.animal.goat;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import dev.pumpkin.shim.Unimplemented;

public class Goat extends Animal {

    public Goat(EntityType<? extends Goat> type, Level level) {
    }

    protected Brain<Goat> makeBrain(Brain.Packed packedBrain) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/goat/Goat.makeBrain:(Lnet/minecraft/world/entity/ai/Brain$Packed;)Lnet/minecraft/world/entity/ai/Brain;");
    }

    protected void ageBoundaryReached() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/goat/Goat.ageBoundaryReached:()V");
    }

    protected int calculateFallDamage(double fallDistance, float damageModifier) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/goat/Goat.calculateFallDamage:(DF)I");
    }

    protected SoundEvent getAmbientSound() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/goat/Goat.getAmbientSound:()Lnet/minecraft/sounds/SoundEvent;");
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/goat/Goat.getHurtSound:(Lnet/minecraft/world/damagesource/DamageSource;)Lnet/minecraft/sounds/SoundEvent;");
    }

    protected SoundEvent getDeathSound() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/goat/Goat.getDeathSound:()Lnet/minecraft/sounds/SoundEvent;");
    }

    protected void playStepSound(BlockPos pos, BlockState blockState) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/goat/Goat.playStepSound:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)V");
    }

    public Goat getBreedOffspring(ServerLevel level, AgeableMob partner) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/goat/Goat.getBreedOffspring:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/AgeableMob;)Lnet/minecraft/world/entity/animal/goat/Goat;");
    }

    public float getAgeScale() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/goat/Goat.getAgeScale:()F");
    }

    public Brain<Goat> getBrain() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/goat/Goat.getBrain:()Lnet/minecraft/world/entity/ai/Brain;");
    }

    protected void customServerAiStep(ServerLevel level) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/goat/Goat.customServerAiStep:(Lnet/minecraft/server/level/ServerLevel;)V");
    }

    public int getMaxHeadYRot() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/goat/Goat.getMaxHeadYRot:()I");
    }

    public void setYHeadRot(float yHeadRot) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/goat/Goat.setYHeadRot:(F)V");
    }

    protected void playEatingSound() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/goat/Goat.playEatingSound:()V");
    }

    public boolean isFood(ItemStack itemStack) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/goat/Goat.isFood:(Lnet/minecraft/world/item/ItemStack;)Z");
    }

    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/goat/Goat.mobInteract:(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/InteractionResult;");
    }

    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, EntitySpawnReason spawnReason, SpawnGroupData groupData) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/goat/Goat.finalizeSpawn:(Lnet/minecraft/world/level/ServerLevelAccessor;Lnet/minecraft/world/DifficultyInstance;Lnet/minecraft/world/entity/EntitySpawnReason;Lnet/minecraft/world/entity/SpawnGroupData;)Lnet/minecraft/world/entity/SpawnGroupData;");
    }

    public EntityDimensions getDefaultDimensions(Pose pose) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/goat/Goat.getDefaultDimensions:(Lnet/minecraft/world/entity/Pose;)Lnet/minecraft/world/entity/EntityDimensions;");
    }

    protected void addAdditionalSaveData(ValueOutput output) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/goat/Goat.addAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueOutput;)V");
    }

    protected void readAdditionalSaveData(ValueInput input) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/goat/Goat.readAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueInput;)V");
    }

    public void handleEntityEvent(byte id) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/goat/Goat.handleEntityEvent:(B)V");
    }

    public void aiStep() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/goat/Goat.aiStep:()V");
    }

    protected void defineSynchedData(SynchedEntityData.Builder entityData) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/goat/Goat.defineSynchedData:(Lnet/minecraft/network/syncher/SynchedEntityData$Builder;)V");
    }

    public boolean isScreamingGoat() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/goat/Goat.isScreamingGoat:()Z");
    }

    public Goat() {
    }
}
