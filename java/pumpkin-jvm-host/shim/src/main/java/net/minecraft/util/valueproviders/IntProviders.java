package net.minecraft.util.valueproviders;

import com.mojang.serialization.Codec;
import dev.pumpkin.shim.Unimplemented;

public class IntProviders {

    public static final Codec<IntProvider> CODEC =
            dev.pumpkin.shim.Stubs.throwingCodec("net.minecraft.util.valueproviders.IntProviders.CODEC");

    public IntProviders() {
    }

    static {
        if (true) {
            throw Unimplemented.forMember("net/minecraft/util/valueproviders/IntProviders");
        }
    }
}
