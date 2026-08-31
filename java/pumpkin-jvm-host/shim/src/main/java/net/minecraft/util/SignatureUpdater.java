package net.minecraft.util;

import java.security.SignatureException;

public interface SignatureUpdater {

    void update(SignatureUpdater.Output output) throws SignatureException;

    interface Output {

        void update(byte[] payload) throws SignatureException;
    }
}
