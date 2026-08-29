package net.minecraft.world.effect;

import java.util.function.Function;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.flag.FeatureElement;
import net.minecraft.world.flag.FeatureFlagSet;
import net.neoforged.neoforge.common.extensions.IMobEffectExtension;
import dev.pumpkin.shim.Unimplemented;

public class MobEffect implements FeatureElement, IMobEffectExtension {

    protected MobEffect(MobEffectCategory category, int color) {
        throw Unimplemented.forMember("net/minecraft/world/effect/MobEffect.<init>:(Lnet/minecraft/world/effect/MobEffectCategory;I)V");
    }

    protected MobEffect(MobEffectCategory category, int color, ParticleOptions particleOptions) {
        throw Unimplemented.forMember("net/minecraft/world/effect/MobEffect.<init>:(Lnet/minecraft/world/effect/MobEffectCategory;ILnet/minecraft/core/particles/ParticleOptions;)V");
    }

    protected MobEffect(MobEffectCategory category, int color, Function<MobEffectInstance, ParticleOptions> particleFactory) {
        throw Unimplemented.forMember("net/minecraft/world/effect/MobEffect.<init>:(Lnet/minecraft/world/effect/MobEffectCategory;ILjava/util/function/Function;)V");
    }

    public Component getDisplayName() {
        throw Unimplemented.forMember("net/minecraft/world/effect/MobEffect.getDisplayName:()Lnet/minecraft/network/chat/Component;");
    }

    public int getColor() {
        throw Unimplemented.forMember("net/minecraft/world/effect/MobEffect.getColor:()I");
    }

    public FeatureFlagSet requiredFeatures() {
        throw Unimplemented.forMember("net/minecraft/world/effect/MobEffect.requiredFeatures:()Lnet/minecraft/world/flag/FeatureFlagSet;");
    }

    public record AttributeTemplate(Identifier id, double amount, AttributeModifier.Operation operation, it.unimi.dsi.fastutil.ints.Int2DoubleFunction curve) {

        public AttributeTemplate(Identifier id, double amount, AttributeModifier.Operation operation) {
            this((Identifier) null, (double) 0.0, (AttributeModifier.Operation) null, (it.unimi.dsi.fastutil.ints.Int2DoubleFunction) null);
            throw Unimplemented.forMember("net/minecraft/world/effect/MobEffect$AttributeTemplate.<init>:(Lnet/minecraft/resources/Identifier;DLnet/minecraft/world/entity/ai/attributes/AttributeModifier$Operation;)V");
        }

        public AttributeModifier create(int amplifier) {
            throw Unimplemented.forMember("net/minecraft/world/effect/MobEffect$AttributeTemplate.create:(I)Lnet/minecraft/world/entity/ai/attributes/AttributeModifier;");
        }
    }

    protected MobEffect() {
    }
}
