package net.minecraft.world.level.saveddata;

import com.mojang.serialization.Codec;
import java.util.function.Supplier;
import net.minecraft.resources.Identifier;
import net.minecraft.util.datafix.DataFixTypes;
import dev.pumpkin.shim.Unimplemented;

public record SavedDataType<T extends SavedData>(Identifier id, Factory<T> factory, Factory<Codec<T>> codecFactory, DataFixTypes dataFixType) {

    public SavedDataType(Identifier id, Supplier<T> constructor, Codec<T> codec, DataFixTypes dataFixType) {
        this((Identifier) null, (Factory<T>) null, (Factory<Codec<T>>) null, (DataFixTypes) null);
        throw Unimplemented.forMember("net/minecraft/world/level/saveddata/SavedDataType.<init>:(Lnet/minecraft/resources/Identifier;Ljava/util/function/Supplier;Lcom/mojang/serialization/Codec;Lnet/minecraft/util/datafix/DataFixTypes;)V");
    }

    public SavedDataType(Identifier id, Supplier<T> constructor, Codec<T> codec) {
        this((Identifier) null, (Factory<T>) null, (Factory<Codec<T>>) null, (DataFixTypes) null);
        throw Unimplemented.forMember("net/minecraft/world/level/saveddata/SavedDataType.<init>:(Lnet/minecraft/resources/Identifier;Ljava/util/function/Supplier;Lcom/mojang/serialization/Codec;)V");
    }

    public SavedDataType(Identifier id, Factory<T> constructor, Factory<Codec<T>> codec) {
        this((Identifier) null, (Factory<T>) null, (Factory<Codec<T>>) null, (DataFixTypes) null);
        throw Unimplemented.forMember("net/minecraft/world/level/saveddata/SavedDataType.<init>:(Lnet/minecraft/resources/Identifier;Lnet/minecraft/world/level/saveddata/SavedDataType$Factory;Lnet/minecraft/world/level/saveddata/SavedDataType$Factory;)V");
    }

    public Codec<T> codec() {
        throw Unimplemented.forMember("net/minecraft/world/level/saveddata/SavedDataType.codec:()Lcom/mojang/serialization/Codec;");
    }

    public boolean equals(Object obj) {
        throw Unimplemented.forMember("net/minecraft/world/level/saveddata/SavedDataType.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/minecraft/world/level/saveddata/SavedDataType.hashCode:()I");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/world/level/saveddata/SavedDataType.toString:()Ljava/lang/String;");
    }

    public interface Factory<T> {

        T create(net.minecraft.server.level.ServerLevel level);
    }
}
