package net.neoforged.neoforge.registries.datamaps;

import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import dev.pumpkin.shim.Unimplemented;

public final class AdvancedDataMapType<R, T, VR extends DataMapValueRemover<R, T>> extends DataMapType<R, T> {

    private AdvancedDataMapType(ResourceKey<Registry<R>> registryKey, Identifier id, Codec<T> codec, Codec<T> networkCodec, boolean mandatorySync, Codec<VR> remover, DataMapValueMerger<R, T> merger) {
    }

    // Pumpkin divergence: real construction, like DataMapType -- the id is the identity.
    public static <T, R> AdvancedDataMapType.Builder<T, R, DataMapValueRemover.Default<T, R>> builder(Identifier id, ResourceKey<Registry<R>> registry, Codec<T> codec) {
        Builder<T, R, DataMapValueRemover.Default<T, R>> builder = new Builder<>();
        builder.pumpkinId = id;
        return builder;
    }

    public static final class Builder<T, R, VR extends DataMapValueRemover<R, T>> extends DataMapType.Builder<T, R> {

        private Codec<VR> remover;

        private DataMapValueMerger<R, T> merger;

        Builder(ResourceKey<Registry<R>> registryKey, Identifier id, Codec<T> codec) {
        }

        @SuppressWarnings("unchecked")
        public <VR1 extends DataMapValueRemover<R, T>> AdvancedDataMapType.Builder<T, R, VR1> remover(Codec<VR1> remover) {
            return (AdvancedDataMapType.Builder<T, R, VR1>) this;
        }

        public AdvancedDataMapType.Builder<T, R, VR> merger(DataMapValueMerger<R, T> merger) {
            return this;
        }

        public AdvancedDataMapType.Builder<T, R, VR> synced(Codec<T> networkCodec, boolean mandatory) {
            return this;
        }

        public AdvancedDataMapType<R, T, VR> build() {
            AdvancedDataMapType<R, T, VR> type = new AdvancedDataMapType<>();
            type.pumpkinAdvancedId = pumpkinId;
            return type;
        }

        public Builder() {
        }
    }

    Identifier pumpkinAdvancedId;

    @Override
    public Identifier id() {
        return pumpkinAdvancedId;
    }

    public AdvancedDataMapType() {
    }
}
