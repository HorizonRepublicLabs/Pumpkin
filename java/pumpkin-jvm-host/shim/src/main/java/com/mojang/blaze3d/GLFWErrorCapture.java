package com.mojang.blaze3d;

import java.util.Iterator;
import org.lwjgl.glfw.GLFWErrorCallbackI;
import dev.pumpkin.shim.Unimplemented;

public class GLFWErrorCapture implements GLFWErrorCallbackI, Iterable<GLFWErrorCapture.Error> {

    public void invoke(int error, long description) {
        throw Unimplemented.forMember("com/mojang/blaze3d/GLFWErrorCapture.invoke:(IJ)V");
    }

    public Iterator<GLFWErrorCapture.Error> iterator() {
        throw Unimplemented.forMember("com/mojang/blaze3d/GLFWErrorCapture.iterator:()Ljava/util/Iterator;");
    }

    public record Error(int error, String description) {

        public String toString() {
            throw Unimplemented.forMember("com/mojang/blaze3d/GLFWErrorCapture$Error.toString:()Ljava/lang/String;");
        }
    }

    protected GLFWErrorCapture() {
    }
}
