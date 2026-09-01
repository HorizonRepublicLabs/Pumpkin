package net.neoforged.neoforge.attachment;

import java.util.function.Supplier;
import dev.pumpkin.shim.Unimplemented;

public interface IAttachmentHolder {

    boolean hasAttachments();

    boolean hasData(AttachmentType<?> type);

    // Pumpkin divergence: vanilla derivations from the type-taking forms. The
    // holder short-circuits before resolving the supplier when nothing is attached
    // at all -- an empty store answers for every type.
    default <T> boolean hasData(Supplier<AttachmentType<T>> type) {
        return hasAttachments() && hasData(type.get());
    }

    <T> T getData(AttachmentType<T> type);

    default <T> T getData(Supplier<AttachmentType<T>> type) {
        return getData(type.get());
    }

    <T> T getExistingDataOrNull(AttachmentType<T> type);

    default <T> T getExistingDataOrNull(Supplier<AttachmentType<T>> type) {
        return hasAttachments() ? getExistingDataOrNull(type.get()) : null;
    }

    <T> T setData(AttachmentType<T> type, T data);

    default <T> T setData(Supplier<AttachmentType<T>> type, T data) {
        return setData(type.get(), data);
    }

    <T> T removeData(AttachmentType<T> type);

    default <T> T removeData(Supplier<AttachmentType<T>> type) {
        return hasAttachments() ? removeData(type.get()) : null;
    }
}
