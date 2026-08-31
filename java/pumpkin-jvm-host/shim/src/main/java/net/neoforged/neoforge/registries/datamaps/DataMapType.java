package net.neoforged.neoforge.registries.datamaps;

import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import dev.pumpkin.shim.Unimplemented;

public class DataMapType<R, T> {

    DataMapType(ResourceKey<Registry<R>> registryKey, Identifier id, Codec<T> codec, Codec<T> networkCodec, boolean mandatorySync) {
    }

    public static <T, R> Builder<T, R> builder(Identifier id, ResourceKey<Registry<R>> registry, Codec<T> codec) {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/datamaps/DataMapType.builder:(Lnet/minecraft/resources/Identifier;Lnet/minecraft/resources/ResourceKey;Lcom/mojang/serialization/Codec;)Lnet/neoforged/neoforge/registries/datamaps/DataMapType$Builder;");
    }

    public Identifier id() {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/datamaps/DataMapType.id:()Lnet/minecraft/resources/Identifier;");
    }

    public Codec<T> codec() {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/datamaps/DataMapType.codec:()Lcom/mojang/serialization/Codec;");
    }

    public static class Builder<T, R> {

        Builder(ResourceKey<Registry<R>> registryKey, Identifier id, Codec<T> codec) {
        }

        public Builder<T, R> synced(Codec<T> networkCodec, boolean mandatory) {
            throw Unimplemented.forMember("net/neoforged/neoforge/registries/datamaps/DataMapType$Builder.synced:(Lcom/mojang/serialization/Codec;Z)Lnet/neoforged/neoforge/registries/datamaps/DataMapType$Builder;");
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
