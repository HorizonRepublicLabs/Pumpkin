package net.neoforged.neoforge.attachment;

import com.mojang.serialization.MapCodec;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import net.neoforged.neoforge.common.util.ValueIOSerializable;
import dev.pumpkin.shim.Unimplemented;

public final class AttachmentType<T> {

    private AttachmentType(Builder<T> builder) {
    }

    public static <T> Builder<T> builder(Supplier<T> defaultValueSupplier) {
        throw Unimplemented.forMember("net/neoforged/neoforge/attachment/AttachmentType.builder:(Ljava/util/function/Supplier;)Lnet/neoforged/neoforge/attachment/AttachmentType$Builder;");
    }

    public static <T> Builder<T> builder(Function<IAttachmentHolder, T> defaultValueConstructor) {
        throw Unimplemented.forMember("net/neoforged/neoforge/attachment/AttachmentType.builder:(Ljava/util/function/Function;)Lnet/neoforged/neoforge/attachment/AttachmentType$Builder;");
    }

    public static <T extends ValueIOSerializable> Builder<T> serializable(Supplier<T> defaultValueSupplier) {
        throw Unimplemented.forMember("net/neoforged/neoforge/attachment/AttachmentType.serializable:(Ljava/util/function/Supplier;)Lnet/neoforged/neoforge/attachment/AttachmentType$Builder;");
    }

    public static <T extends ValueIOSerializable> Builder<T> serializable(Function<IAttachmentHolder, T> defaultValueConstructor) {
        throw Unimplemented.forMember("net/neoforged/neoforge/attachment/AttachmentType.serializable:(Ljava/util/function/Function;)Lnet/neoforged/neoforge/attachment/AttachmentType$Builder;");
    }

    public static class Builder<T> {

        private IAttachmentCopyHandler<T> copyHandler;

        private Builder(Function<IAttachmentHolder, T> defaultValueSupplier) {
        }

        public Builder<T> serialize(IAttachmentSerializer<T> serializer) {
            throw Unimplemented.forMember("net/neoforged/neoforge/attachment/AttachmentType$Builder.serialize:(Lnet/neoforged/neoforge/attachment/IAttachmentSerializer;)Lnet/neoforged/neoforge/attachment/AttachmentType$Builder;");
        }

        public Builder<T> serialize(MapCodec<T> codec) {
            throw Unimplemented.forMember("net/neoforged/neoforge/attachment/AttachmentType$Builder.serialize:(Lcom/mojang/serialization/MapCodec;)Lnet/neoforged/neoforge/attachment/AttachmentType$Builder;");
        }

        public Builder<T> serialize(MapCodec<T> codec, Predicate<? super T> shouldSerialize) {
            throw Unimplemented.forMember("net/neoforged/neoforge/attachment/AttachmentType$Builder.serialize:(Lcom/mojang/serialization/MapCodec;Ljava/util/function/Predicate;)Lnet/neoforged/neoforge/attachment/AttachmentType$Builder;");
        }

        public Builder<T> copyHandler(IAttachmentCopyHandler<T> cloner) {
            throw Unimplemented.forMember("net/neoforged/neoforge/attachment/AttachmentType$Builder.copyHandler:(Lnet/neoforged/neoforge/attachment/IAttachmentCopyHandler;)Lnet/neoforged/neoforge/attachment/AttachmentType$Builder;");
        }

        public AttachmentType<T> build() {
            throw Unimplemented.forMember("net/neoforged/neoforge/attachment/AttachmentType$Builder.build:()Lnet/neoforged/neoforge/attachment/AttachmentType;");
        }

        public Builder() {
        }
    }

    public AttachmentType() {
    }
}
