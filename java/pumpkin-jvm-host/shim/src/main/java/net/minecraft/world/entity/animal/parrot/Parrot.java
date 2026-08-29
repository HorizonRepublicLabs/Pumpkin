package net.minecraft.world.entity.animal.parrot;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomFlyingGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public class Parrot extends ShoulderRidingEntity {

    public Parrot(EntityType<? extends Parrot> type, Level level) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/parrot/Parrot.<init>:(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/level/Level;)V");
    }

    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, EntitySpawnReason spawnReason, SpawnGroupData groupData) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/parrot/Parrot.finalizeSpawn:(Lnet/minecraft/world/level/ServerLevelAccessor;Lnet/minecraft/world/DifficultyInstance;Lnet/minecraft/world/entity/EntitySpawnReason;Lnet/minecraft/world/entity/SpawnGroupData;)Lnet/minecraft/world/entity/SpawnGroupData;");
    }

    protected boolean canBeABaby() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/parrot/Parrot.canBeABaby:()Z");
    }

    protected void registerGoals() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/parrot/Parrot.registerGoals:()V");
    }

    protected PathNavigation createNavigation(Level level) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/parrot/Parrot.createNavigation:(Lnet/minecraft/world/level/Level;)Lnet/minecraft/world/entity/ai/navigation/PathNavigation;");
    }

    public void aiStep() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/parrot/Parrot.aiStep:()V");
    }

    public void setRecordPlayingNearby(BlockPos jukebox, boolean isPlaying) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/parrot/Parrot.setRecordPlayingNearby:(Lnet/minecraft/core/BlockPos;Z)V");
    }

    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/parrot/Parrot.mobInteract:(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/InteractionResult;");
    }

    public boolean isFood(ItemStack itemStack) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/parrot/Parrot.isFood:(Lnet/minecraft/world/item/ItemStack;)Z");
    }

    protected void checkFallDamage(double ya, boolean onGround, BlockState onState, BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/parrot/Parrot.checkFallDamage:(DZLnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/BlockPos;)V");
    }

    public boolean canMate(Animal partner) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/parrot/Parrot.canMate:(Lnet/minecraft/world/entity/animal/Animal;)Z");
    }

    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob partner) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/parrot/Parrot.getBreedOffspring:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/AgeableMob;)Lnet/minecraft/world/entity/AgeableMob;");
    }

    public SoundEvent getAmbientSound() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/parrot/Parrot.getAmbientSound:()Lnet/minecraft/sounds/SoundEvent;");
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/parrot/Parrot.getHurtSound:(Lnet/minecraft/world/damagesource/DamageSource;)Lnet/minecraft/sounds/SoundEvent;");
    }

    protected SoundEvent getDeathSound() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/parrot/Parrot.getDeathSound:()Lnet/minecraft/sounds/SoundEvent;");
    }

    protected void playStepSound(BlockPos pos, BlockState blockState) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/parrot/Parrot.playStepSound:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)V");
    }

    protected boolean isFlapping() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/parrot/Parrot.isFlapping:()Z");
    }

    protected void onFlap() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/parrot/Parrot.onFlap:()V");
    }

    public float getVoicePitch() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/parrot/Parrot.getVoicePitch:()F");
    }

    public SoundSource getSoundSource() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/parrot/Parrot.getSoundSource:()Lnet/minecraft/sounds/SoundSource;");
    }

    public boolean isPushable() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/parrot/Parrot.isPushable:()Z");
    }

    protected void doPush(Entity entity) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/parrot/Parrot.doPush:(Lnet/minecraft/world/entity/Entity;)V");
    }

    public boolean hurtServer(ServerLevel level, DamageSource source, float damage) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/parrot/Parrot.hurtServer:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;F)Z");
    }

    public <T> T get(DataComponentType<? extends T> type) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/parrot/Parrot.get:(Lnet/minecraft/core/component/DataComponentType;)Ljava/lang/Object;");
    }

    protected void applyImplicitComponents(DataComponentGetter components) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/parrot/Parrot.applyImplicitComponents:(Lnet/minecraft/core/component/DataComponentGetter;)V");
    }

    protected <T> boolean applyImplicitComponent(DataComponentType<T> type, T value) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/parrot/Parrot.applyImplicitComponent:(Lnet/minecraft/core/component/DataComponentType;Ljava/lang/Object;)Z");
    }

    protected void defineSynchedData(SynchedEntityData.Builder entityData) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/parrot/Parrot.defineSynchedData:(Lnet/minecraft/network/syncher/SynchedEntityData$Builder;)V");
    }

    protected void addAdditionalSaveData(ValueOutput output) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/parrot/Parrot.addAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueOutput;)V");
    }

    protected void readAdditionalSaveData(ValueInput input) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/parrot/Parrot.readAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueInput;)V");
    }

    protected boolean omnidirectionalAirMover() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/parrot/Parrot.omnidirectionalAirMover:()Z");
    }

    protected boolean canFlyToOwner() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/parrot/Parrot.canFlyToOwner:()Z");
    }

    public Vec3 getLeashOffset() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/parrot/Parrot.getLeashOffset:()Lnet/minecraft/world/phys/Vec3;");
    }

    private static class ParrotWanderGoal extends WaterAvoidingRandomFlyingGoal {

        public ParrotWanderGoal(PathfinderMob mob, double speedModifier) {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/parrot/Parrot$ParrotWanderGoal.<init>:(Lnet/minecraft/world/entity/PathfinderMob;D)V");
        }

        protected Vec3 getPosition() {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/parrot/Parrot$ParrotWanderGoal.getPosition:()Lnet/minecraft/world/phys/Vec3;");
        }

        protected ParrotWanderGoal() {
        }
    }

    public enum Variant implements StringRepresentable {

        RED_BLUE, BLUE, GREEN, YELLOW_BLUE, GRAY;

        public int getId() {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/parrot/Parrot$Variant.getId:()I");
        }

        public static Parrot.Variant byId(int id) {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/parrot/Parrot$Variant.byId:(I)Lnet/minecraft/world/entity/animal/parrot/Parrot$Variant;");
        }

        public String getSerializedName() {
            throw Unimplemented.forMember("net/minecraft/world/entity/animal/parrot/Parrot$Variant.getSerializedName:()Ljava/lang/String;");
        }
    }

    protected Parrot() {
    }
}
