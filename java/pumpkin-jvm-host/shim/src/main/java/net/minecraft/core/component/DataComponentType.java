package net.minecraft.core.component;

import com.mojang.serialization.Codec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import dev.pumpkin.shim.Unimplemented;

public interface DataComponentType<T> {

    // Pumpkin divergence: real body. Both mods declare their components through this
    // chain; nothing reads one back yet, so declaring is all it has to survive.
    static <T> DataComponentType.Builder<T> builder() {
        return new Builder<>();
    }

    Codec<T> codec();

    boolean ignoreSwapAnimation();

    StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec();

    class Builder<T> {

        private boolean cacheEncoding;

        // Pumpkin divergence: real body. The codec would matter when components are
        // saved; Pumpkin does not persist them yet, so it is accepted and dropped and the
        // chain returns `this`.
        public DataComponentType.Builder<T> persistent(Codec<T> codec) {
            return this;
        }

        // Pumpkin divergence: real body. Same reasoning as persistent -- sync codecs
        // matter when a component crosses the wire, which none does yet.
        public DataComponentType.Builder<T> networkSynchronized(StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec) {
            return this;
        }

        public DataComponentType.Builder<T> cacheEncoding() {
            throw Unimplemented.forMember("net/minecraft/core/component/DataComponentType$Builder.cacheEncoding:()Lnet/minecraft/core/component/DataComponentType$Builder;");
        }

        // Pumpkin divergence: real body. The type is an interface, so the stub stands in:
        // it survives being registered and stored in the mod's own statics, and the first
        // actual read on it throws with the member that was wanted -- the failure moves to
        // where components are used, which is the next slice's territory, not declaration.
        public DataComponentType<T> build() {
            return dev.pumpkin.shim.Stubs.of(DataComponentType.class,
                    "net/minecraft/core/component/DataComponentType");
        }

        public DataComponentType.Builder<T> ignoreSwapAnimation() {
            throw Unimplemented.forMember("net/minecraft/core/component/DataComponentType$Builder.ignoreSwapAnimation:()Lnet/minecraft/core/component/DataComponentType$Builder;");
        }

        private static class SimpleType<T> implements DataComponentType<T> {

            private SimpleType(Codec<T> codec, StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec, boolean ignoreSwapAnimation) {
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
