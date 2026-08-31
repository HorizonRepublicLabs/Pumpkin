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
    }

    public DamageSource getSource() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/damagesource/DamageContainer.getSource:()Lnet/minecraft/world/damagesource/DamageSource;");
    }

    public float getNewDamage() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/damagesource/DamageContainer.getNewDamage:()F");
    }

    public void setShouldCauseSideEffects(boolean sideEffects) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/damagesource/DamageContainer.setShouldCauseSideEffects:(Z)V");
    }

    public DamageContainer() {
    }
}
