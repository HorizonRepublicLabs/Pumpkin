package net.neoforged.neoforge.fluids;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockAndLightGetter;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.FluidState;
import net.neoforged.neoforge.common.SoundAction;
import dev.pumpkin.shim.Unimplemented;

public class FluidType {

    public FluidType(final Properties properties) {
    }

    public Component getDescription() {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidType.getDescription:()Lnet/minecraft/network/chat/Component;");
    }

    public String getDescriptionId() {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidType.getDescriptionId:()Ljava/lang/String;");
    }

    public int getLightLevel() {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidType.getLightLevel:()I");
    }

    public int getTemperature() {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidType.getTemperature:()I");
    }

    public SoundEvent getSound(SoundAction action) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidType.getSound:(Lnet/neoforged/neoforge/common/SoundAction;)Lnet/minecraft/sounds/SoundEvent;");
    }

    public SoundEvent getSound(Entity entity, SoundAction action) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidType.getSound:(Lnet/minecraft/world/entity/Entity;Lnet/neoforged/neoforge/common/SoundAction;)Lnet/minecraft/sounds/SoundEvent;");
    }

    public SoundEvent getSound(LivingEntity entity, BlockGetter getter, BlockPos pos, SoundAction action) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidType.getSound:(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/neoforged/neoforge/common/SoundAction;)Lnet/minecraft/sounds/SoundEvent;");
    }

    public int getLightLevel(FluidState state, BlockAndLightGetter getter, BlockPos pos) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidType.getLightLevel:(Lnet/minecraft/world/level/material/FluidState;Lnet/minecraft/world/level/BlockAndLightGetter;Lnet/minecraft/core/BlockPos;)I");
    }

    public int getTemperature(FluidState state, BlockAndLightGetter getter, BlockPos pos) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidType.getTemperature:(Lnet/minecraft/world/level/material/FluidState;Lnet/minecraft/world/level/BlockAndLightGetter;Lnet/minecraft/core/BlockPos;)I");
    }

    public boolean canConvertToSource(FluidStack stack) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidType.canConvertToSource:(Lnet/neoforged/neoforge/fluids/FluidStack;)Z");
    }

    public SoundEvent getSound(FluidStack stack, SoundAction action) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidType.getSound:(Lnet/neoforged/neoforge/fluids/FluidStack;Lnet/neoforged/neoforge/common/SoundAction;)Lnet/minecraft/sounds/SoundEvent;");
    }

    public Component getDescription(FluidStack stack) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidType.getDescription:(Lnet/neoforged/neoforge/fluids/FluidStack;)Lnet/minecraft/network/chat/Component;");
    }

    public String getDescriptionId(FluidStack stack) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidType.getDescriptionId:(Lnet/neoforged/neoforge/fluids/FluidStack;)Ljava/lang/String;");
    }

    public int getLightLevel(FluidStack stack) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidType.getLightLevel:(Lnet/neoforged/neoforge/fluids/FluidStack;)I");
    }

    public int getTemperature(FluidStack stack) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidType.getTemperature:(Lnet/neoforged/neoforge/fluids/FluidStack;)I");
    }

    public ItemStack getBucket(FluidStack stack) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidType.getBucket:(Lnet/neoforged/neoforge/fluids/FluidStack;)Lnet/minecraft/world/item/ItemStack;");
    }

    public final boolean canBePlacedInLevel(BlockAndLightGetter getter, BlockPos pos, FluidState state) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidType.canBePlacedInLevel:(Lnet/minecraft/world/level/BlockAndLightGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/material/FluidState;)Z");
    }

    public final boolean canBePlacedInLevel(BlockAndLightGetter getter, BlockPos pos, FluidStack stack) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidType.canBePlacedInLevel:(Lnet/minecraft/world/level/BlockAndLightGetter;Lnet/minecraft/core/BlockPos;Lnet/neoforged/neoforge/fluids/FluidStack;)Z");
    }

    public final boolean isLighterThanAir() {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidType.isLighterThanAir:()Z");
    }

    public boolean isVaporizedOnPlacement(Level level, BlockPos pos, FluidStack stack) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidType.isVaporizedOnPlacement:(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/neoforged/neoforge/fluids/FluidStack;)Z");
    }

    public void onVaporize(LivingEntity entity, Level level, BlockPos pos, FluidStack stack) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidType.onVaporize:(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/neoforged/neoforge/fluids/FluidStack;)V");
    }

    public String toString() {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidType.toString:()Ljava/lang/String;");
    }

    public static final class Properties {

        private String descriptionId;

        private int lightLevel, density, temperature, viscosity;

        protected Properties() {
        }

        public static Properties create() {
            throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidType$Properties.create:()Lnet/neoforged/neoforge/fluids/FluidType$Properties;");
        }

        public Properties descriptionId(String descriptionId) {
            throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidType$Properties.descriptionId:(Ljava/lang/String;)Lnet/neoforged/neoforge/fluids/FluidType$Properties;");
        }

        public Properties canConvertToSource(boolean canConvertToSource) {
            throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidType$Properties.canConvertToSource:(Z)Lnet/neoforged/neoforge/fluids/FluidType$Properties;");
        }

        public Properties sound(SoundAction action, SoundEvent sound) {
            throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidType$Properties.sound:(Lnet/neoforged/neoforge/common/SoundAction;Lnet/minecraft/sounds/SoundEvent;)Lnet/neoforged/neoforge/fluids/FluidType$Properties;");
        }

        public Properties lightLevel(int lightLevel) {
            throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidType$Properties.lightLevel:(I)Lnet/neoforged/neoforge/fluids/FluidType$Properties;");
        }

        public Properties density(int density) {
            throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidType$Properties.density:(I)Lnet/neoforged/neoforge/fluids/FluidType$Properties;");
        }

        public Properties temperature(int temperature) {
            throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidType$Properties.temperature:(I)Lnet/neoforged/neoforge/fluids/FluidType$Properties;");
        }

        public Properties viscosity(int viscosity) {
            throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidType$Properties.viscosity:(I)Lnet/neoforged/neoforge/fluids/FluidType$Properties;");
        }
    }

    public record DripstoneDripInfo(float chance, ParticleOptions dripParticle, Block filledCauldron) {
    }

    public FluidType() {
    }
}
