package net.neoforged.neoforge.registries.datamaps;

import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import dev.pumpkin.shim.Unimplemented;

public class DataMapType<R, T> {

    DataMapType(ResourceKey<Registry<R>> registryKey, Identifier id, Codec<T> codec, Codec<T> networkCodec, boolean mandatorySync) {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/datamaps/DataMapType.<init>:(Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/resources/Identifier;Lcom/mojang/serialization/Codec;Lcom/mojang/serialization/Codec;Z)V");
    }

    public Identifier id() {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/datamaps/DataMapType.id:()Lnet/minecraft/resources/Identifier;");
    }

    public Codec<T> codec() {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/datamaps/DataMapType.codec:()Lcom/mojang/serialization/Codec;");
    }

    public static class Builder<T, R> {

        Builder(ResourceKey<Registry<R>> registryKey, Identifier id, Codec<T> codec) {
            throw Unimplemented.forMember("net/neoforged/neoforge/registries/datamaps/DataMapType$Builder.<init>:(Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/resources/Identifier;Lcom/mojang/serialization/Codec;)V");
        }

        public DataMapType<R, T> build() {
            throw Unimplemented.forMember("net/neoforged/neoforge/registries/datamaps/DataMapType$Builder.build:()Lnet/neoforged/neoforge/registries/datamaps/DataMapType;");
        }

        public Builder() {
        }
    }

    public DataMapType() {
    }
}
