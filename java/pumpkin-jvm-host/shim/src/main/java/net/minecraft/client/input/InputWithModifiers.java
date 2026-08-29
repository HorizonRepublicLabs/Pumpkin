package net.minecraft.client.input;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public interface InputWithModifiers {

    int input();

    int modifiers();

    @Retention(RetentionPolicy.CLASS)
    @Target(ElementType.TYPE_USE)
    @interface Modifiers {
    }
}
