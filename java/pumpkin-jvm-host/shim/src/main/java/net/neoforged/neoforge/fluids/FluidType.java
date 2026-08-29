package net.neoforged.neoforge.fluids;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.SoundAction;
import dev.pumpkin.shim.Unimplemented;

public class FluidType {

    public FluidType(final Properties properties) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidType.<init>:(Lnet/neoforged/neoforge/fluids/FluidType$Properties;)V");
    }

    public Component getDescription() {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidType.getDescription:()Lnet/minecraft/network/chat/Component;");
    }

    public SoundEvent getSound(SoundAction action) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidType.getSound:(Lnet/neoforged/neoforge/common/SoundAction;)Lnet/minecraft/sounds/SoundEvent;");
    }

    public boolean canConvertToSource(FluidStack stack) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidType.canConvertToSource:(Lnet/neoforged/neoforge/fluids/FluidStack;)Z");
    }

    public String toString() {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidType.toString:()Ljava/lang/String;");
    }

    public static final class Properties {

        protected Properties() {
            throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidType$Properties.<init>:()V");
        }

        public static Properties create() {
            throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidType$Properties.create:()Lnet/neoforged/neoforge/fluids/FluidType$Properties;");
        }

        public Properties canConvertToSource(boolean canConvertToSource) {
            throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidType$Properties.canConvertToSource:(Z)Lnet/neoforged/neoforge/fluids/FluidType$Properties;");
        }
    }

    public record DripstoneDripInfo(float chance, ParticleOptions dripParticle, Block filledCauldron) {
    }

    public FluidType() {
    }
}
