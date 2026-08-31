package net.minecraft.util.valueproviders;

import com.mojang.serialization.Codec;

public class IntProviders {

    public static final Codec<IntProvider> CODEC =
            dev.pumpkin.shim.Stubs.throwingCodec("net.minecraft.util.valueproviders.IntProviders.CODEC");

    public IntProviders() {
    }

    // Pumpkin divergence: no throwing clinit -- the one static is already an inert
    // throwing codec, so the class composes and fails by name on first real use.
}
