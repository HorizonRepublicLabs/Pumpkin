package net.minecraft.client.input;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import dev.pumpkin.shim.Unimplemented;

public record MouseButtonInfo(int button, int modifiers) implements InputWithModifiers {

    public int input() {
        throw Unimplemented.forMember("net/minecraft/client/input/MouseButtonInfo.input:()I");
    }

    @Retention(RetentionPolicy.CLASS)
    @Target(ElementType.TYPE_USE)
    public @interface Action {
    }

    @Retention(RetentionPolicy.CLASS)
    @Target(ElementType.TYPE_USE)
    public @interface MouseButton {
    }
}
