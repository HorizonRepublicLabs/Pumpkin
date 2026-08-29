package net.minecraft.world.entity.vehicle;

import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import dev.pumpkin.shim.Unimplemented;

public abstract class VehicleEntity extends Entity {

    public VehicleEntity(EntityType<?> type, Level level) {
        throw Unimplemented.forMember("net/minecraft/world/entity/vehicle/VehicleEntity.<init>:(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/level/Level;)V");
    }

    public boolean hurtClient(DamageSource source) {
        throw Unimplemented.forMember("net/minecraft/world/entity/vehicle/VehicleEntity.hurtClient:(Lnet/minecraft/world/damagesource/DamageSource;)Z");
    }

    public boolean hurtServer(ServerLevel level, DamageSource source, float damage) {
        throw Unimplemented.forMember("net/minecraft/world/entity/vehicle/VehicleEntity.hurtServer:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;F)Z");
    }

    public boolean ignoreExplosion(Explosion explosion) {
        throw Unimplemented.forMember("net/minecraft/world/entity/vehicle/VehicleEntity.ignoreExplosion:(Lnet/minecraft/world/level/Explosion;)Z");
    }

    protected void defineSynchedData(SynchedEntityData.Builder entityData) {
        throw Unimplemented.forMember("net/minecraft/world/entity/vehicle/VehicleEntity.defineSynchedData:(Lnet/minecraft/network/syncher/SynchedEntityData$Builder;)V");
    }

    public int getDimensionChangingDelay() {
        throw Unimplemented.forMember("net/minecraft/world/entity/vehicle/VehicleEntity.getDimensionChangingDelay:()I");
    }

    protected abstract Item getDropItem();

    protected VehicleEntity() {
    }
}
