package net.minecraft.world.entity;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import dev.pumpkin.shim.Unimplemented;

public abstract class AgeableMob extends PathfinderMob {

    protected AgeableMob(EntityType<? extends AgeableMob> type, Level level) {
    }

    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, EntitySpawnReason spawnReason, SpawnGroupData groupData) {
        throw Unimplemented.forMember("net/minecraft/world/entity/AgeableMob.finalizeSpawn:(Lnet/minecraft/world/level/ServerLevelAccessor;Lnet/minecraft/world/DifficultyInstance;Lnet/minecraft/world/entity/EntitySpawnReason;Lnet/minecraft/world/entity/SpawnGroupData;)Lnet/minecraft/world/entity/SpawnGroupData;");
    }

    public abstract AgeableMob getBreedOffspring(final ServerLevel level, AgeableMob partner);

    protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        throw Unimplemented.forMember("net/minecraft/world/entity/AgeableMob.mobInteract:(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/InteractionResult;");
    }

    protected void defineSynchedData(SynchedEntityData.Builder entityData) {
        throw Unimplemented.forMember("net/minecraft/world/entity/AgeableMob.defineSynchedData:(Lnet/minecraft/network/syncher/SynchedEntityData$Builder;)V");
    }

    protected void addAdditionalSaveData(ValueOutput output) {
        throw Unimplemented.forMember("net/minecraft/world/entity/AgeableMob.addAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueOutput;)V");
    }

    protected void readAdditionalSaveData(ValueInput input) {
        throw Unimplemented.forMember("net/minecraft/world/entity/AgeableMob.readAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueInput;)V");
    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> accessor) {
        throw Unimplemented.forMember("net/minecraft/world/entity/AgeableMob.onSyncedDataUpdated:(Lnet/minecraft/network/syncher/EntityDataAccessor;)V");
    }

    public void aiStep() {
        throw Unimplemented.forMember("net/minecraft/world/entity/AgeableMob.aiStep:()V");
    }

    public final boolean isBaby() {
        throw Unimplemented.forMember("net/minecraft/world/entity/AgeableMob.isBaby:()Z");
    }

    public final void setBaby(boolean baby) {
        throw Unimplemented.forMember("net/minecraft/world/entity/AgeableMob.setBaby:(Z)V");
    }

    public static class AgeableMobGroupData implements SpawnGroupData {

        public AgeableMobGroupData(boolean shouldSpawnBaby, float babySpawnChance) {
        }

        public AgeableMobGroupData(boolean shouldSpawnBaby) {
        }

        public AgeableMobGroupData(float babySpawnChance) {
        }

        public AgeableMobGroupData() {
        }
    }

    public AgeableMob() {
    }
}
