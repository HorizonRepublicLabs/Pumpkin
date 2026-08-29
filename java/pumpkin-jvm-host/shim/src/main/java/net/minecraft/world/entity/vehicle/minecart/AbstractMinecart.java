package net.minecraft.world.entity.vehicle.minecart;

import net.minecraft.core.Direction;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.BlockUtil;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.InterpolationHandler;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.vehicle.VehicleEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public abstract class AbstractMinecart extends VehicleEntity {

    protected AbstractMinecart(EntityType<?> type, Level level) {
    }

    protected AbstractMinecart(EntityType<?> type, Level level, double x, double y, double z) {
    }

    protected Entity.MovementEmission getMovementEmission() {
        throw Unimplemented.forMember("net/minecraft/world/entity/vehicle/minecart/AbstractMinecart.getMovementEmission:()Lnet/minecraft/world/entity/Entity$MovementEmission;");
    }

    protected void defineSynchedData(SynchedEntityData.Builder entityData) {
        throw Unimplemented.forMember("net/minecraft/world/entity/vehicle/minecart/AbstractMinecart.defineSynchedData:(Lnet/minecraft/network/syncher/SynchedEntityData$Builder;)V");
    }

    public boolean canCollideWith(Entity entity) {
        throw Unimplemented.forMember("net/minecraft/world/entity/vehicle/minecart/AbstractMinecart.canCollideWith:(Lnet/minecraft/world/entity/Entity;)Z");
    }

    public boolean isPushable() {
        throw Unimplemented.forMember("net/minecraft/world/entity/vehicle/minecart/AbstractMinecart.isPushable:()Z");
    }

    public Vec3 getRelativePortalPosition(Direction.Axis axis, BlockUtil.FoundRectangle portalArea) {
        throw Unimplemented.forMember("net/minecraft/world/entity/vehicle/minecart/AbstractMinecart.getRelativePortalPosition:(Lnet/minecraft/core/Direction$Axis;Lnet/minecraft/util/BlockUtil$FoundRectangle;)Lnet/minecraft/world/phys/Vec3;");
    }

    protected Vec3 getPassengerAttachmentPoint(Entity passenger, EntityDimensions dimensions, float scale) {
        throw Unimplemented.forMember("net/minecraft/world/entity/vehicle/minecart/AbstractMinecart.getPassengerAttachmentPoint:(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/entity/EntityDimensions;F)Lnet/minecraft/world/phys/Vec3;");
    }

    public Vec3 getDismountLocationForPassenger(LivingEntity passenger) {
        throw Unimplemented.forMember("net/minecraft/world/entity/vehicle/minecart/AbstractMinecart.getDismountLocationForPassenger:(Lnet/minecraft/world/entity/LivingEntity;)Lnet/minecraft/world/phys/Vec3;");
    }

    protected float getBlockSpeedFactor() {
        throw Unimplemented.forMember("net/minecraft/world/entity/vehicle/minecart/AbstractMinecart.getBlockSpeedFactor:()F");
    }

    public void animateHurt(float yaw) {
        throw Unimplemented.forMember("net/minecraft/world/entity/vehicle/minecart/AbstractMinecart.animateHurt:(F)V");
    }

    public boolean isPickable() {
        throw Unimplemented.forMember("net/minecraft/world/entity/vehicle/minecart/AbstractMinecart.isPickable:()Z");
    }

    public Direction getMotionDirection() {
        throw Unimplemented.forMember("net/minecraft/world/entity/vehicle/minecart/AbstractMinecart.getMotionDirection:()Lnet/minecraft/core/Direction;");
    }

    protected double getDefaultGravity() {
        throw Unimplemented.forMember("net/minecraft/world/entity/vehicle/minecart/AbstractMinecart.getDefaultGravity:()D");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/world/entity/vehicle/minecart/AbstractMinecart.tick:()V");
    }

    protected double getMaxSpeed(ServerLevel level) {
        throw Unimplemented.forMember("net/minecraft/world/entity/vehicle/minecart/AbstractMinecart.getMaxSpeed:(Lnet/minecraft/server/level/ServerLevel;)D");
    }

    public void lerpPositionAndRotationStep(int stepsToTarget, double targetX, double targetY, double targetZ, double targetYRot, double targetXRot) {
        throw Unimplemented.forMember("net/minecraft/world/entity/vehicle/minecart/AbstractMinecart.lerpPositionAndRotationStep:(IDDDDD)V");
    }

    public void applyGravity() {
        throw Unimplemented.forMember("net/minecraft/world/entity/vehicle/minecart/AbstractMinecart.applyGravity:()V");
    }

    public void reapplyPosition() {
        throw Unimplemented.forMember("net/minecraft/world/entity/vehicle/minecart/AbstractMinecart.reapplyPosition:()V");
    }

    public boolean updateFluidInteraction() {
        throw Unimplemented.forMember("net/minecraft/world/entity/vehicle/minecart/AbstractMinecart.updateFluidInteraction:()Z");
    }

    public Vec3 getKnownMovement() {
        throw Unimplemented.forMember("net/minecraft/world/entity/vehicle/minecart/AbstractMinecart.getKnownMovement:()Lnet/minecraft/world/phys/Vec3;");
    }

    public InterpolationHandler getInterpolation() {
        throw Unimplemented.forMember("net/minecraft/world/entity/vehicle/minecart/AbstractMinecart.getInterpolation:()Lnet/minecraft/world/entity/InterpolationHandler;");
    }

    public void recreateFromPacket(ClientboundAddEntityPacket packet) {
        throw Unimplemented.forMember("net/minecraft/world/entity/vehicle/minecart/AbstractMinecart.recreateFromPacket:(Lnet/minecraft/network/protocol/game/ClientboundAddEntityPacket;)V");
    }

    public void lerpMotion(Vec3 movement) {
        throw Unimplemented.forMember("net/minecraft/world/entity/vehicle/minecart/AbstractMinecart.lerpMotion:(Lnet/minecraft/world/phys/Vec3;)V");
    }

    protected void moveAlongTrack(ServerLevel level) {
        throw Unimplemented.forMember("net/minecraft/world/entity/vehicle/minecart/AbstractMinecart.moveAlongTrack:(Lnet/minecraft/server/level/ServerLevel;)V");
    }

    protected float getAirDrag() {
        throw Unimplemented.forMember("net/minecraft/world/entity/vehicle/minecart/AbstractMinecart.getAirDrag:()F");
    }

    public void move(MoverType moverType, Vec3 delta) {
        throw Unimplemented.forMember("net/minecraft/world/entity/vehicle/minecart/AbstractMinecart.move:(Lnet/minecraft/world/entity/MoverType;Lnet/minecraft/world/phys/Vec3;)V");
    }

    public void applyEffectsFromBlocks() {
        throw Unimplemented.forMember("net/minecraft/world/entity/vehicle/minecart/AbstractMinecart.applyEffectsFromBlocks:()V");
    }

    public boolean isOnRails() {
        throw Unimplemented.forMember("net/minecraft/world/entity/vehicle/minecart/AbstractMinecart.isOnRails:()Z");
    }

    protected void readAdditionalSaveData(ValueInput input) {
        throw Unimplemented.forMember("net/minecraft/world/entity/vehicle/minecart/AbstractMinecart.readAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueInput;)V");
    }

    protected void addAdditionalSaveData(ValueOutput output) {
        throw Unimplemented.forMember("net/minecraft/world/entity/vehicle/minecart/AbstractMinecart.addAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueOutput;)V");
    }

    public void push(Entity entity) {
        throw Unimplemented.forMember("net/minecraft/world/entity/vehicle/minecart/AbstractMinecart.push:(Lnet/minecraft/world/entity/Entity;)V");
    }

    public abstract ItemStack getPickResult();

    public AbstractMinecart() {
    }
}
