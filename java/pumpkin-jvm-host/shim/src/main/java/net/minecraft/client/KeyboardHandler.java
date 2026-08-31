package net.minecraft.client;

import com.mojang.blaze3d.platform.Window;
import dev.pumpkin.shim.Unimplemented;

public class KeyboardHandler {

    public KeyboardHandler(Minecraft minecraft) {
    }

    public void setup(Window window) {
        throw Unimplemented.forMember("net/minecraft/client/KeyboardHandler.setup:(Lcom/mojang/blaze3d/platform/Window;)V");
    }

    public String getClipboard() {
        throw Unimplemented.forMember("net/minecraft/client/KeyboardHandler.getClipboard:()Ljava/lang/String;");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/client/KeyboardHandler.tick:()V");
    }

    public KeyboardHandler() {
    }
}
