package net.minecraft.core.component;

import com.mojang.serialization.Codec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import dev.pumpkin.shim.Unimplemented;

public interface DataComponentType<T> {

    static <T> DataComponentType.Builder<T> builder() {
        throw Unimplemented.forMember("net/minecraft/core/component/DataComponentType.builder:()Lnet/minecraft/core/component/DataComponentType$Builder;");
    }

    Codec<T> codec();

    boolean ignoreSwapAnimation();

    StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec();

    class Builder<T> {

        public DataComponentType.Builder<T> persistent(Codec<T> codec) {
            throw Unimplemented.forMember("net/minecraft/core/component/DataComponentType$Builder.persistent:(Lcom/mojang/serialization/Codec;)Lnet/minecraft/core/component/DataComponentType$Builder;");
        }

        public DataComponentType.Builder<T> networkSynchronized(StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec) {
            throw Unimplemented.forMember("net/minecraft/core/component/DataComponentType$Builder.networkSynchronized:(Lnet/minecraft/network/codec/StreamCodec;)Lnet/minecraft/core/component/DataComponentType$Builder;");
        }

        public DataComponentType<T> build() {
            throw Unimplemented.forMember("net/minecraft/core/component/DataComponentType$Builder.build:()Lnet/minecraft/core/component/DataComponentType;");
        }

        public DataComponentType.Builder<T> ignoreSwapAnimation() {
            throw Unimplemented.forMember("net/minecraft/core/component/DataComponentType$Builder.ignoreSwapAnimation:()Lnet/minecraft/core/component/DataComponentType$Builder;");
        }

        private static class SimpleType<T> implements DataComponentType<T> {

            private SimpleType(Codec<T> codec, StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec, boolean ignoreSwapAnimation) {
                throw Unimplemented.forMember("net/minecraft/core/component/DataComponentType$Builder$SimpleType.<init>:(Lcom/mojang/serialization/Codec;Lnet/minecraft/network/codec/StreamCodec;Z)V");
            }

            public boolean ignoreSwapAnimation() {
                throw Unimplemented.forMember("net/minecraft/core/component/DataComponentType$Builder$SimpleType.ignoreSwapAnimation:()Z");
            }

            public Codec<T> codec() {
                throw Unimplemented.forMember("net/minecraft/core/component/DataComponentType$Builder$SimpleType.codec:()Lcom/mojang/serialization/Codec;");
            }

            public StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec() {
                throw Unimplemented.forMember("net/minecraft/core/component/DataComponentType$Builder$SimpleType.streamCodec:()Lnet/minecraft/network/codec/StreamCodec;");
            }

            public String toString() {
                throw Unimplemented.forMember("net/minecraft/core/component/DataComponentType$Builder$SimpleType.toString:()Ljava/lang/String;");
            }

            protected SimpleType() {
            }
        }

        protected Builder() {
        }
    }
}
