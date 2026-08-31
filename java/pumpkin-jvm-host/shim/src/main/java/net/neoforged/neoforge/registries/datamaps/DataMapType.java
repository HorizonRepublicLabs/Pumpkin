package net.neoforged.neoforge.registries.datamaps;

import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import dev.pumpkin.shim.Unimplemented;

public class DataMapType<R, T> {

    DataMapType(ResourceKey<Registry<R>> registryKey, Identifier id, Codec<T> codec, Codec<T> networkCodec, boolean mandatorySync) {
    }

    // Pumpkin divergence: real construction -- a data map type is its id; the data
    // itself is datapack content nothing loads yet, and reads fail loudly on Registry.
    private Identifier pumpkinId;

    public static <T, R> Builder<T, R> builder(Identifier id, ResourceKey<Registry<R>> registry, Codec<T> codec) {
        Builder<T, R> builder = new Builder<>();
        builder.pumpkinId = id;
        return builder;
    }

    public Identifier id() {
        return pumpkinId;
    }

    public Codec<T> codec() {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/datamaps/DataMapType.codec:()Lcom/mojang/serialization/Codec;");
    }

    public static class Builder<T, R> {

        Builder(ResourceKey<Registry<R>> registryKey, Identifier id, Codec<T> codec) {
        }

        Identifier pumpkinId;

        public Builder<T, R> synced(Codec<T> networkCodec, boolean mandatory) {
            return this;
        }

        public DataMapType<R, T> build() {
            DataMapType<R, T> type = new DataMapType<>();
            type.pumpkinId = pumpkinId;
            return type;
        }

        public Builder() {
        }
    }

    public DataMapType() {
    }
}
