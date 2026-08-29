package net.neoforged.neoforge.attachment;

import com.mojang.serialization.MapCodec;
import java.util.function.Function;
import dev.pumpkin.shim.Unimplemented;

public final class AttachmentType<T> {

    private AttachmentType(Builder<T> builder) {
    }

    public static class Builder<T> {

        private Builder(Function<IAttachmentHolder, T> defaultValueSupplier) {
        }

        public Builder<T> serialize(IAttachmentSerializer<T> serializer) {
            throw Unimplemented.forMember("net/neoforged/neoforge/attachment/AttachmentType$Builder.serialize:(Lnet/neoforged/neoforge/attachment/IAttachmentSerializer;)Lnet/neoforged/neoforge/attachment/AttachmentType$Builder;");
        }

        public Builder<T> serialize(MapCodec<T> codec) {
            throw Unimplemented.forMember("net/neoforged/neoforge/attachment/AttachmentType$Builder.serialize:(Lcom/mojang/serialization/MapCodec;)Lnet/neoforged/neoforge/attachment/AttachmentType$Builder;");
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
