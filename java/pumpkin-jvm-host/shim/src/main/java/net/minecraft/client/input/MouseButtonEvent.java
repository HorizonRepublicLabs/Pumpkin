package net.minecraft.client.input;

import dev.pumpkin.shim.Unimplemented;

public record MouseButtonEvent(double x, double y, MouseButtonInfo buttonInfo) implements InputWithModifiers {

    public int input() {
        throw Unimplemented.forMember("net/minecraft/client/input/MouseButtonEvent.input:()I");
    }

    public int modifiers() {
        throw Unimplemented.forMember("net/minecraft/client/input/MouseButtonEvent.modifiers:()I");
    }
}
