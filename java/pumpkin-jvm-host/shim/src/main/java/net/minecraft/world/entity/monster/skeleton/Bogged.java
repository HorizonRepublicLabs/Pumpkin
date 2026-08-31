package net.minecraft.world.entity.monster.skeleton;

import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Shearable;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import dev.pumpkin.shim.Unimplemented;

public class Bogged extends AbstractSkeleton implements Shearable {

    public static AttributeSupplier.Builder createAttributes() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/Bogged.createAttributes:()Lnet/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder;");
    }

    public Bogged(EntityType<? extends Bogged> type, Level level) {
    }

    protected void defineSynchedData(SynchedEntityData.Builder entityData) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/Bogged.defineSynchedData:(Lnet/minecraft/network/syncher/SynchedEntityData$Builder;)V");
    }

    protected void addAdditionalSaveData(ValueOutput output) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/Bogged.addAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueOutput;)V");
    }

    protected void readAdditionalSaveData(ValueInput input) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/Bogged.readAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueInput;)V");
    }

    public boolean isSheared() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/Bogged.isSheared:()Z");
    }

    protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/Bogged.mobInteract:(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/InteractionResult;");
    }

    protected SoundEvent getAmbientSound() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/Bogged.getAmbientSound:()Lnet/minecraft/sounds/SoundEvent;");
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/Bogged.getHurtSound:(Lnet/minecraft/world/damagesource/DamageSource;)Lnet/minecraft/sounds/SoundEvent;");
    }

    protected SoundEvent getDeathSound() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/Bogged.getDeathSound:()Lnet/minecraft/sounds/SoundEvent;");
    }

    protected SoundEvent getStepSound() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/Bogged.getStepSound:()Lnet/minecraft/sounds/SoundEvent;");
    }

    protected AbstractArrow getArrow(ItemStack projectile, float power, ItemStack firingWeapon) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/Bogged.getArrow:(Lnet/minecraft/world/item/ItemStack;FLnet/minecraft/world/item/ItemStack;)Lnet/minecraft/world/entity/projectile/arrow/AbstractArrow;");
    }

    protected int getHardAttackInterval() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/Bogged.getHardAttackInterval:()I");
    }

    protected int getAttackInterval() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/Bogged.getAttackInterval:()I");
    }

    public void shear(ServerLevel level, SoundSource soundSource, ItemStack tool) {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/Bogged.shear:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/sounds/SoundSource;Lnet/minecraft/world/item/ItemStack;)V");
    }

    public boolean readyForShearing() {
        throw Unimplemented.forMember("net/minecraft/world/entity/monster/skeleton/Bogged.readyForShearing:()Z");
    }

    public Bogged() {
    }
}
