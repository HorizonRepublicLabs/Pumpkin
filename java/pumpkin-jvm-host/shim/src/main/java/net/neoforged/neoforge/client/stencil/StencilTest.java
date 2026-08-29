package net.neoforged.neoforge.client.stencil;

public record StencilTest(StencilPerFaceTest front, StencilPerFaceTest back, int readMask, int writeMask, int referenceValue) {

    public StencilTest(StencilPerFaceTest test, int readMask, int writeMask, int referenceValue) {
        this((StencilPerFaceTest) null, (StencilPerFaceTest) null, (int) 0, (int) 0, (int) 0);
    }
}
