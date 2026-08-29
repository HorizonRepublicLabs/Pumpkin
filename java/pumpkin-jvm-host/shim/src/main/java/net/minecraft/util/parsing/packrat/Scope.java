package net.minecraft.util.parsing.packrat;

import dev.pumpkin.shim.Unimplemented;

public final class Scope {

    public Scope() {
    }

    public <T> T get(Atom<T> name) {
        throw Unimplemented.forMember("net/minecraft/util/parsing/packrat/Scope.get:(Lnet/minecraft/util/parsing/packrat/Atom;)Ljava/lang/Object;");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/util/parsing/packrat/Scope.toString:()Ljava/lang/String;");
    }
}
