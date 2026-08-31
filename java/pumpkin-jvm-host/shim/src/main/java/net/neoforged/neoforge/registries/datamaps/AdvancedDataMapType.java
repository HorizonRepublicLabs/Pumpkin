package net.neoforged.neoforge.registries.datamaps;

import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import dev.pumpkin.shim.Unimplemented;

public final class AdvancedDataMapType<R, T, VR extends DataMapValueRemover<R, T>> extends DataMapType<R, T> {

    private AdvancedDataMapType(ResourceKey<Registry<R>> registryKey, Identifier id, Codec<T> codec, Codec<T> networkCodec, boolean mandatorySync, Codec<VR> remover, DataMapValueMerger<R, T> merger) {
    }

    public static <T, R> AdvancedDataMapType.Builder<T, R, DataMapValueRemover.Default<T, R>> builder(Identifier id, ResourceKey<Registry<R>> registry, Codec<T> codec) {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/datamaps/AdvancedDataMapType.builder:(Lnet/minecraft/resources/Identifier;Lnet/minecraft/resources/ResourceKey;Lcom/mojang/serialization/Codec;)Lnet/neoforged/neoforge/registries/datamaps/AdvancedDataMapType$Builder;");
    }

    public static final class Builder<T, R, VR extends DataMapValueRemover<R, T>> extends DataMapType.Builder<T, R> {

        private Codec<VR> remover;

        private DataMapValueMerger<R, T> merger;

        Builder(ResourceKey<Registry<R>> registryKey, Identifier id, Codec<T> codec) {
        }

        public <VR1 extends DataMapValueRemover<R, T>> AdvancedDataMapType.Builder<T, R, VR1> remover(Codec<VR1> remover) {
            throw Unimplemented.forMember("net/neoforged/neoforge/registries/datamaps/AdvancedDataMapType$Builder.remover:(Lcom/mojang/serialization/Codec;)Lnet/neoforged/neoforge/registries/datamaps/AdvancedDataMapType$Builder;");
        }

        public AdvancedDataMapType.Builder<T, R, VR> merger(DataMapValueMerger<R, T> merger) {
            throw Unimplemented.forMember("net/neoforged/neoforge/registries/datamaps/AdvancedDataMapType$Builder.merger:(Lnet/neoforged/neoforge/registries/datamaps/DataMapValueMerger;)Lnet/neoforged/neoforge/registries/datamaps/AdvancedDataMapType$Builder;");
        }

        public AdvancedDataMapType.Builder<T, R, VR> synced(Codec<T> networkCodec, boolean mandatory) {
            throw Unimplemented.forMember("net/neoforged/neoforge/registries/datamaps/AdvancedDataMapType$Builder.synced:(Lcom/mojang/serialization/Codec;Z)Lnet/neoforged/neoforge/registries/datamaps/AdvancedDataMapType$Builder;");
        }

        public AdvancedDataMapType<R, T, VR> build() {
            throw Unimplemented.forMember("net/neoforged/neoforge/registries/datamaps/AdvancedDataMapType$Builder.build:()Lnet/neoforged/neoforge/registries/datamaps/AdvancedDataMapType;");
        }

        public Builder() {
        }
    }

    public AdvancedDataMapType() {
    }
}
