package net.minecraft.world.effect;

import java.util.function.Function;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.flag.FeatureElement;
import net.minecraft.world.flag.FeatureFlagSet;
import net.neoforged.neoforge.common.extensions.IMobEffectExtension;
import dev.pumpkin.shim.Unimplemented;

public class MobEffect implements FeatureElement, IMobEffectExtension {

    protected MobEffect(MobEffectCategory category, int color) {
    }

    protected MobEffect(MobEffectCategory category, int color, ParticleOptions particleOptions) {
    }

    protected MobEffect(MobEffectCategory category, int color, Function<MobEffectInstance, ParticleOptions> particleFactory) {
    }

    public String getDescriptionId() {
        throw Unimplemented.forMember("net/minecraft/world/effect/MobEffect.getDescriptionId:()Ljava/lang/String;");
    }

    public Component getDisplayName() {
        throw Unimplemented.forMember("net/minecraft/world/effect/MobEffect.getDisplayName:()Lnet/minecraft/network/chat/Component;");
    }

    public MobEffectCategory getCategory() {
        throw Unimplemented.forMember("net/minecraft/world/effect/MobEffect.getCategory:()Lnet/minecraft/world/effect/MobEffectCategory;");
    }

    public int getColor() {
        throw Unimplemented.forMember("net/minecraft/world/effect/MobEffect.getColor:()I");
    }

    public void removeAttributeModifiers(AttributeMap attributes) {
        throw Unimplemented.forMember("net/minecraft/world/effect/MobEffect.removeAttributeModifiers:(Lnet/minecraft/world/entity/ai/attributes/AttributeMap;)V");
    }

    public void addAttributeModifiers(AttributeMap attributes, int amplifier) {
        throw Unimplemented.forMember("net/minecraft/world/effect/MobEffect.addAttributeModifiers:(Lnet/minecraft/world/entity/ai/attributes/AttributeMap;I)V");
    }

    public FeatureFlagSet requiredFeatures() {
        throw Unimplemented.forMember("net/minecraft/world/effect/MobEffect.requiredFeatures:()Lnet/minecraft/world/flag/FeatureFlagSet;");
    }

    public record AttributeTemplate(Identifier id, double amount, AttributeModifier.Operation operation, it.unimi.dsi.fastutil.ints.Int2DoubleFunction curve) {

        public AttributeTemplate(Identifier id, double amount, AttributeModifier.Operation operation) {
            this((Identifier) null, (double) 0.0, (AttributeModifier.Operation) null, (it.unimi.dsi.fastutil.ints.Int2DoubleFunction) null);
        }

        public AttributeModifier create(int amplifier) {
            throw Unimplemented.forMember("net/minecraft/world/effect/MobEffect$AttributeTemplate.create:(I)Lnet/minecraft/world/entity/ai/attributes/AttributeModifier;");
        }
    }

    public MobEffect() {
    }
}
