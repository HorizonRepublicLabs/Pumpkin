package net.minecraft.util.parsing.packrat;

import dev.pumpkin.shim.Unimplemented;

public record Atom<T>(String name) {

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/util/parsing/packrat/Atom.toString:()Ljava/lang/String;");
    }
}
