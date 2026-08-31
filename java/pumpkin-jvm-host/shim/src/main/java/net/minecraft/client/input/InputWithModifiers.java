package net.minecraft.client.input;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import dev.pumpkin.shim.Unimplemented;

public interface InputWithModifiers {

    int input();

    int modifiers();

    default boolean isLeft() {
        throw Unimplemented.forMember("net/minecraft/client/input/InputWithModifiers.isLeft:()Z");
    }

    default boolean isRight() {
        throw Unimplemented.forMember("net/minecraft/client/input/InputWithModifiers.isRight:()Z");
    }

    default boolean isUp() {
        throw Unimplemented.forMember("net/minecraft/client/input/InputWithModifiers.isUp:()Z");
    }

    default boolean isDown() {
        throw Unimplemented.forMember("net/minecraft/client/input/InputWithModifiers.isDown:()Z");
    }

    @Retention(RetentionPolicy.CLASS)
    @Target(ElementType.TYPE_USE)
    @interface Modifiers {
    }
}
