package com.mojang.blaze3d.systems;

import java.util.List;
import dev.pumpkin.shim.Unimplemented;

public class BackendCreationException extends Exception {

    public BackendCreationException(String message, BackendCreationException.Reason reason, List<String> missingCapabilities) {
    }

    public BackendCreationException(String message, BackendCreationException.Reason reason) {
    }

    public BackendCreationException.Reason getReason() {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/BackendCreationException.getReason:()Lcom/mojang/blaze3d/systems/BackendCreationException$Reason;");
    }

    public List<String> getMissingCapabilities() {
        throw Unimplemented.forMember("com/mojang/blaze3d/systems/BackendCreationException.getMissingCapabilities:()Ljava/util/List;");
    }

    public enum Reason {

        GLFW_ERROR,
        VULKAN_LOADER_MISSING,
        VULKAN_INSTANCE_CREATION_FAILED,
        VULKAN_NO_DEVICE,
        VULKAN_DEVICE_VERSION_TOO_LOW,
        VULKAN_NO_GRAPHICS_QUEUE,
        VULKAN_MISSING_EXTENSION,
        VULKAN_MISSING_FEATURE,
        OPENGL_MISSING,
        OTHER;

        public String displayName() {
            throw Unimplemented.forMember("com/mojang/blaze3d/systems/BackendCreationException$Reason.displayName:()Ljava/lang/String;");
        }
    }

    public BackendCreationException() {
    }
}
