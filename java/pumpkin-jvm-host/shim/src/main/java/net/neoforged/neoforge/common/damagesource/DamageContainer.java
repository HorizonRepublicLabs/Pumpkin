package net.neoforged.neoforge.common.damagesource;

import net.minecraft.world.damagesource.DamageSource;
import dev.pumpkin.shim.Unimplemented;

public class DamageContainer {

    public enum Reduction {

        INVULNERABILITY,
        ARMOR,
        ENCHANTMENTS,
        MOB_EFFECTS,
        ABSORPTION,
        INNATE_RESISTANCE
    }

    public DamageContainer(DamageSource source, float originalDamage) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/damagesource/DamageContainer.<init>:(Lnet/minecraft/world/damagesource/DamageSource;F)V");
    }

    public DamageSource getSource() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/damagesource/DamageContainer.getSource:()Lnet/minecraft/world/damagesource/DamageSource;");
    }

    protected DamageContainer() {
    }
}
