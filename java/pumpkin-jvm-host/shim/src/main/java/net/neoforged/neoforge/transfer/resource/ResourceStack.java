package net.neoforged.neoforge.transfer.resource;

import dev.pumpkin.shim.Unimplemented;

public record ResourceStack<T extends Resource>(T resource, int amount) {

    public boolean isEmpty() {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/resource/ResourceStack.isEmpty:()Z");
    }

    public String toString() {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/resource/ResourceStack.toString:()Ljava/lang/String;");
    }
}
