package net.neoforged.neoforge.attachment;

import com.mojang.serialization.MapCodec;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import net.neoforged.neoforge.common.util.ValueIOSerializable;
import dev.pumpkin.shim.Unimplemented;

public final class AttachmentType<T> {

    // Pumpkin divergence: real bodies. An attachment type is its default-value
    // constructor plus (for the serializable flavours) how to persist it. Attachments
    // live in a real in-memory store on the holder; Pumpkin's save path does not
    // carry them yet, so persistence is the one part that stays behind.
    final Function<IAttachmentHolder, T> pumpkinDefault;

    private AttachmentType(Builder<T> builder) {
        this.pumpkinDefault = builder.pumpkinDefault;
    }

    public static <T> Builder<T> builder(Supplier<T> defaultValueSupplier) {
        return new Builder<>(holder -> defaultValueSupplier.get());
    }

    public static <T> Builder<T> builder(Function<IAttachmentHolder, T> defaultValueConstructor) {
        return new Builder<>(defaultValueConstructor);
    }

    public static <T extends ValueIOSerializable> Builder<T> serializable(Supplier<T> defaultValueSupplier) {
        return new Builder<>(holder -> defaultValueSupplier.get());
    }

    public static <T extends ValueIOSerializable> Builder<T> serializable(Function<IAttachmentHolder, T> defaultValueConstructor) {
        return new Builder<>(defaultValueConstructor);
    }

    public static class Builder<T> {

        private IAttachmentCopyHandler<T> copyHandler;
        final Function<IAttachmentHolder, T> pumpkinDefault;

        private Builder(Function<IAttachmentHolder, T> defaultValueSupplier) {
            this.pumpkinDefault = defaultValueSupplier;
        }

        // Pumpkin divergence: serializers and copy handlers are how attachments
        // persist and clone; the in-memory store needs neither yet, and the chain
        // keeps building.
        public Builder<T> serialize(IAttachmentSerializer<T> serializer) {
            return this;
        }

        public Builder<T> serialize(MapCodec<T> codec) {
            return this;
        }

        public Builder<T> serialize(MapCodec<T> codec, Predicate<? super T> shouldSerialize) {
            return this;
        }

        public Builder<T> copyHandler(IAttachmentCopyHandler<T> cloner) {
            this.copyHandler = cloner;
            return this;
        }

        public AttachmentType<T> build() {
            return new AttachmentType<>(this);
        }

        public Builder() {
            this.pumpkinDefault = null;
        }
    }

    public AttachmentType() {
        this.pumpkinDefault = null;
    }
}
