package com.mojang.blaze3d.platform;

import com.mojang.blaze3d.systems.BackendCreationException;
import com.mojang.blaze3d.systems.GpuBackend;
import net.minecraft.client.main.SilentInitException;
import dev.pumpkin.shim.Unimplemented;

public final class Window implements AutoCloseable {

    public Window(WindowEventHandler eventHandler, DisplayData displayData, String fullscreenVideoModeString, boolean exclusiveFullscreen, String title, MonitorManager monitorManager, GpuBackend backend) throws BackendCreationException {
    }

    public void close() {
        throw Unimplemented.forMember("com/mojang/blaze3d/platform/Window.close:()V");
    }

    public long handle() {
        throw Unimplemented.forMember("com/mojang/blaze3d/platform/Window.handle:()J");
    }

    public boolean isFocused() {
        throw Unimplemented.forMember("com/mojang/blaze3d/platform/Window.isFocused:()Z");
    }

    public int getWidth() {
        throw Unimplemented.forMember("com/mojang/blaze3d/platform/Window.getWidth:()I");
    }

    public int getHeight() {
        throw Unimplemented.forMember("com/mojang/blaze3d/platform/Window.getHeight:()I");
    }

    public int getGuiScaledWidth() {
        throw Unimplemented.forMember("com/mojang/blaze3d/platform/Window.getGuiScaledWidth:()I");
    }

    public int getGuiScaledHeight() {
        throw Unimplemented.forMember("com/mojang/blaze3d/platform/Window.getGuiScaledHeight:()I");
    }

    public int getX() {
        throw Unimplemented.forMember("com/mojang/blaze3d/platform/Window.getX:()I");
    }

    public int getY() {
        throw Unimplemented.forMember("com/mojang/blaze3d/platform/Window.getY:()I");
    }

    public static class WindowInitFailed extends SilentInitException {

        public WindowInitFailed(String message) {
        }

        public WindowInitFailed() {
        }
    }

    public Window() {
    }
}
