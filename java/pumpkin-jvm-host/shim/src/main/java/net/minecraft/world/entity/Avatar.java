package net.minecraft.world.entity;

import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.item.component.ResolvableProfile;
import net.minecraft.world.level.Level;
import dev.pumpkin.shim.Unimplemented;

public abstract class Avatar extends LivingEntity {

    protected Avatar(EntityType<? extends LivingEntity> type, Level level) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Avatar.<init>:(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/level/Level;)V");
    }

    protected void defineSynchedData(SynchedEntityData.Builder entityData) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Avatar.defineSynchedData:(Lnet/minecraft/network/syncher/SynchedEntityData$Builder;)V");
    }

    public HumanoidArm getMainArm() {
        throw Unimplemented.forMember("net/minecraft/world/entity/Avatar.getMainArm:()Lnet/minecraft/world/entity/HumanoidArm;");
    }

    public EntityDimensions getDefaultDimensions(Pose pose) {
        throw Unimplemented.forMember("net/minecraft/world/entity/Avatar.getDefaultDimensions:(Lnet/minecraft/world/entity/Pose;)Lnet/minecraft/world/entity/EntityDimensions;");
    }

    public abstract ResolvableProfile getProfile();

    public Avatar() {
    }
}
