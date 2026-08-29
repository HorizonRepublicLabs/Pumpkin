package net.minecraft.world.entity.projectile;

import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TraceableEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import dev.pumpkin.shim.Unimplemented;

public abstract class Projectile extends Entity implements TraceableEntity {

    protected Projectile(EntityType<? extends Projectile> type, Level level) {
    }

    public Entity getOwner() {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/Projectile.getOwner:()Lnet/minecraft/world/entity/Entity;");
    }

    protected void addAdditionalSaveData(ValueOutput output) {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/Projectile.addAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueOutput;)V");
    }

    protected void readAdditionalSaveData(ValueInput input) {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/Projectile.readAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueInput;)V");
    }

    public void restoreFrom(Entity oldEntity) {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/Projectile.restoreFrom:(Lnet/minecraft/world/entity/Entity;)V");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/Projectile.tick:()V");
    }

    public void onAboveBubbleColumn(boolean dragDown, BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/Projectile.onAboveBubbleColumn:(ZLnet/minecraft/core/BlockPos;)V");
    }

    public void onInsideBubbleColumn(boolean dragDown) {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/Projectile.onInsideBubbleColumn:(Z)V");
    }

    public Packet<ClientGamePacketListener> getAddEntityPacket(ServerEntity serverEntity) {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/Projectile.getAddEntityPacket:(Lnet/minecraft/server/level/ServerEntity;)Lnet/minecraft/network/protocol/Packet;");
    }

    public void recreateFromPacket(ClientboundAddEntityPacket packet) {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/Projectile.recreateFromPacket:(Lnet/minecraft/network/protocol/game/ClientboundAddEntityPacket;)V");
    }

    public boolean mayInteract(ServerLevel level, BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/Projectile.mayInteract:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;)Z");
    }

    public boolean isPickable() {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/Projectile.isPickable:()Z");
    }

    public float getPickRadius() {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/Projectile.getPickRadius:()F");
    }

    public int getDimensionChangingDelay() {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/Projectile.getDimensionChangingDelay:()I");
    }

    public boolean hurtServer(ServerLevel level, DamageSource source, float damage) {
        throw Unimplemented.forMember("net/minecraft/world/entity/projectile/Projectile.hurtServer:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;F)Z");
    }

    public interface ProjectileFactory<T extends Projectile> {

        T create(final ServerLevel level, LivingEntity entity, ItemStack itemStack);
    }

    public Projectile() {
    }
}
