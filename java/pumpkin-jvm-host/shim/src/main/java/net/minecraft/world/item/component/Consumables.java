package net.minecraft.world.item.component;

import dev.pumpkin.shim.Unimplemented;

public class Consumables {

    // Pumpkin divergence: the vanilla drink defaults, minus the sound holder --
    // Pumpkin has no SoundEvents.GENERIC_DRINK stand-in yet; null stays null rather
    // than inventing one.
    public static Consumable.Builder defaultDrink() {
        return Consumable.builder().consumeSeconds(1.6F).animation(net.minecraft.world.item.ItemUseAnimation.DRINK).hasConsumeParticles(false);
    }

    public Consumables() {
    }
}
