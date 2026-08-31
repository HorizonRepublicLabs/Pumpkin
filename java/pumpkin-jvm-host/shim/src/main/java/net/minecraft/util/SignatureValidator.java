package net.minecraft.util;

import dev.pumpkin.shim.Unimplemented;

public interface SignatureValidator {

    boolean validate(SignatureUpdater updater, byte[] signature);

    default boolean validate(byte[] payload, byte[] signature) {
        throw Unimplemented.forMember("net/minecraft/util/SignatureValidator.validate:([B[B)Z");
    }
}
