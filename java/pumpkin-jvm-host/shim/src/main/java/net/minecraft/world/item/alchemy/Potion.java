package net.minecraft.world.item.alchemy;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.flag.FeatureElement;
import net.minecraft.world.flag.FeatureFlagSet;
import dev.pumpkin.shim.Unimplemented;

public class Potion implements FeatureElement {

    public Potion(String name, MobEffectInstance... effects) {
    }

    public FeatureFlagSet requiredFeatures() {
        throw Unimplemented.forMember("net/minecraft/world/item/alchemy/Potion.requiredFeatures:()Lnet/minecraft/world/flag/FeatureFlagSet;");
    }

    public String name() {
        throw Unimplemented.forMember("net/minecraft/world/item/alchemy/Potion.name:()Ljava/lang/String;");
    }

    public Potion() {
    }
}
