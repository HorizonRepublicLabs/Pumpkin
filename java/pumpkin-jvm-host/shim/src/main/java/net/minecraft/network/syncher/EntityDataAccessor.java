package net.minecraft.network.syncher;

import dev.pumpkin.shim.Unimplemented;

public record EntityDataAccessor<T>(int id, EntityDataSerializer<T> serializer) {

    public boolean equals(Object o) {
        throw Unimplemented.forMember("net/minecraft/network/syncher/EntityDataAccessor.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/minecraft/network/syncher/EntityDataAccessor.hashCode:()I");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/network/syncher/EntityDataAccessor.toString:()Ljava/lang/String;");
    }
}
