package net.minecraft.world.entity.animal;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public abstract class Animal extends AgeableMob {

    protected Animal(EntityType<? extends Animal> type, Level level) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/Animal.<init>:(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/level/Level;)V");
    }

    protected void customServerAiStep(ServerLevel level) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/Animal.customServerAiStep:(Lnet/minecraft/server/level/ServerLevel;)V");
    }

    public void aiStep() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/Animal.aiStep:()V");
    }

    protected void actuallyHurt(ServerLevel level, DamageSource source, float dmg) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/Animal.actuallyHurt:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;F)V");
    }

    public float getWalkTargetValue(BlockPos pos, LevelReader level) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/Animal.getWalkTargetValue:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/LevelReader;)F");
    }

    protected void addAdditionalSaveData(ValueOutput output) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/Animal.addAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueOutput;)V");
    }

    protected void readAdditionalSaveData(ValueInput input) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/Animal.readAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueInput;)V");
    }

    public int getAmbientSoundInterval() {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/Animal.getAmbientSoundInterval:()I");
    }

    public boolean removeWhenFarAway(double distSqr) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/Animal.removeWhenFarAway:(D)Z");
    }

    protected int getBaseExperienceReward(ServerLevel level) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/Animal.getBaseExperienceReward:(Lnet/minecraft/server/level/ServerLevel;)I");
    }

    public abstract boolean isFood(final ItemStack itemStack);

    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/Animal.mobInteract:(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/InteractionResult;");
    }

    public void handleEntityEvent(byte id) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/Animal.handleEntityEvent:(B)V");
    }

    public Vec3 getDismountLocationForPassenger(LivingEntity passenger) {
        throw Unimplemented.forMember("net/minecraft/world/entity/animal/Animal.getDismountLocationForPassenger:(Lnet/minecraft/world/entity/LivingEntity;)Lnet/minecraft/world/phys/Vec3;");
    }

    public Animal() {
    }
}
