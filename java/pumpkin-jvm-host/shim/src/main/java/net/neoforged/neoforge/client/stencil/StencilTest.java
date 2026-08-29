package net.neoforged.neoforge.client.stencil;

import dev.pumpkin.shim.Unimplemented;

public record StencilTest(StencilPerFaceTest front, StencilPerFaceTest back, int readMask, int writeMask, int referenceValue) {

    public StencilTest(StencilPerFaceTest test, int readMask, int writeMask, int referenceValue) {
        this((StencilPerFaceTest) null, (StencilPerFaceTest) null, (int) 0, (int) 0, (int) 0);
        throw Unimplemented.forMember("net/neoforged/neoforge/client/stencil/StencilTest.<init>:(Lnet/neoforged/neoforge/client/stencil/StencilPerFaceTest;III)V");
    }
}
