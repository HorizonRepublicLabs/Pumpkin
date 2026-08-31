package com.mojang.blaze3d.platform;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.network.chat.Component;
import dev.pumpkin.shim.Unimplemented;

public class InputConstants {

    public static final InputConstants.Key UNKNOWN = null;

    public static InputConstants.Key getKey(KeyEvent event) {
        throw Unimplemented.forMember("com/mojang/blaze3d/platform/InputConstants.getKey:(Lnet/minecraft/client/input/KeyEvent;)Lcom/mojang/blaze3d/platform/InputConstants$Key;");
    }

    public static InputConstants.Key getKey(String name) {
        throw Unimplemented.forMember("com/mojang/blaze3d/platform/InputConstants.getKey:(Ljava/lang/String;)Lcom/mojang/blaze3d/platform/InputConstants$Key;");
    }

    public static boolean isKeyDown(Window window, int key) {
        throw Unimplemented.forMember("com/mojang/blaze3d/platform/InputConstants.isKeyDown:(Lcom/mojang/blaze3d/platform/Window;I)Z");
    }

    public static final class Key {

        private Key(String name, InputConstants.Type type, int value) {
        }

        public InputConstants.Type getType() {
            throw Unimplemented.forMember("com/mojang/blaze3d/platform/InputConstants$Key.getType:()Lcom/mojang/blaze3d/platform/InputConstants$Type;");
        }

        public int getValue() {
            throw Unimplemented.forMember("com/mojang/blaze3d/platform/InputConstants$Key.getValue:()I");
        }

        public String getName() {
            throw Unimplemented.forMember("com/mojang/blaze3d/platform/InputConstants$Key.getName:()Ljava/lang/String;");
        }

        public Component getDisplayName() {
            throw Unimplemented.forMember("com/mojang/blaze3d/platform/InputConstants$Key.getDisplayName:()Lnet/minecraft/network/chat/Component;");
        }

        public boolean equals(Object o) {
            throw Unimplemented.forMember("com/mojang/blaze3d/platform/InputConstants$Key.equals:(Ljava/lang/Object;)Z");
        }

        public int hashCode() {
            throw Unimplemented.forMember("com/mojang/blaze3d/platform/InputConstants$Key.hashCode:()I");
        }

        public String toString() {
            throw Unimplemented.forMember("com/mojang/blaze3d/platform/InputConstants$Key.toString:()Ljava/lang/String;");
        }

        public Key() {
        }
    }

    public enum Type {

        KEYSYM, SCANCODE, MOUSE;

        public InputConstants.Key getOrCreate(int value) {
            throw Unimplemented.forMember("com/mojang/blaze3d/platform/InputConstants$Type.getOrCreate:(I)Lcom/mojang/blaze3d/platform/InputConstants$Key;");
        }
    }

    @Retention(RetentionPolicy.CLASS)
    @Target(ElementType.TYPE_USE)
    public @interface Value {
    }

    public InputConstants() {
    }

    static {
        if (true) {
            throw Unimplemented.forMember("com/mojang/blaze3d/platform/InputConstants");
        }
    }
}
