package net.minecraft.world.entity.animal.cow;

import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import dev.pumpkin.shim.Unimplemented;

public class Cow extends AbstractCow {

    public Cow(EntityType<? extends Cow> type, Level level) {
    }

    protected void defineSynchedData(SynchedEntityData.Builder entityData) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/cow/Cow.defineSynchedData:(Lnet/minecraft/network/syncher/SynchedEntityData$Builder;)V");
    }

    protected void addAdditionalSaveData(ValueOutput output) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/cow/Cow.addAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueOutput;)V");
    }

    protected void readAdditionalSaveData(ValueInput input) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/cow/Cow.readAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueInput;)V");
    }

    public Cow getBreedOffspring(ServerLevel level, AgeableMob partner) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/cow/Cow.getBreedOffspring:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/AgeableMob;)Lnet/minecraft/world/entity/animal/cow/Cow;");
    }

    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, EntitySpawnReason spawnReason, SpawnGroupData groupData) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/cow/Cow.finalizeSpawn:(Lnet/minecraft/world/level/ServerLevelAccessor;Lnet/minecraft/world/DifficultyInstance;Lnet/minecraft/world/entity/EntitySpawnReason;Lnet/minecraft/world/entity/SpawnGroupData;)Lnet/minecraft/world/entity/SpawnGroupData;");
    }

    protected CowSoundVariant getSoundSet() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/cow/Cow.getSoundSet:()Lnet/minecraft/world/entity/animal/cow/CowSoundVariant;");
    }

    public EntityDimensions getDefaultDimensions(Pose pose) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/cow/Cow.getDefaultDimensions:(Lnet/minecraft/world/entity/Pose;)Lnet/minecraft/world/entity/EntityDimensions;");
    }

    public <T> T get(DataComponentType<? extends T> type) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/cow/Cow.get:(Lnet/minecraft/core/component/DataComponentType;)Ljava/lang/Object;");
    }

    protected void applyImplicitComponents(DataComponentGetter components) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/cow/Cow.applyImplicitComponents:(Lnet/minecraft/core/component/DataComponentGetter;)V");
    }

    protected <T> boolean applyImplicitComponent(DataComponentType<T> type, T value) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/cow/Cow.applyImplicitComponent:(Lnet/minecraft/core/component/DataComponentType;Ljava/lang/Object;)Z");
    }

    public Cow() {
    }
}
