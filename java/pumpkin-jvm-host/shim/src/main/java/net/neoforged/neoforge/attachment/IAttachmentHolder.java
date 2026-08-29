package net.neoforged.neoforge.attachment;

import java.util.function.Supplier;
import dev.pumpkin.shim.Unimplemented;

public interface IAttachmentHolder {

    boolean hasAttachments();

    boolean hasData(AttachmentType<?> type);

    default <T> boolean hasData(Supplier<AttachmentType<T>> type) {
        throw Unimplemented.forMember("net/neoforged/neoforge/attachment/IAttachmentHolder.hasData:(Ljava/util/function/Supplier;)Z");
    }

    <T> T getData(AttachmentType<T> type);

    default <T> T getData(Supplier<AttachmentType<T>> type) {
        throw Unimplemented.forMember("net/neoforged/neoforge/attachment/IAttachmentHolder.getData:(Ljava/util/function/Supplier;)Ljava/lang/Object;");
    }

    <T> T getExistingDataOrNull(AttachmentType<T> type);

    default <T> T getExistingDataOrNull(Supplier<AttachmentType<T>> type) {
        throw Unimplemented.forMember("net/neoforged/neoforge/attachment/IAttachmentHolder.getExistingDataOrNull:(Ljava/util/function/Supplier;)Ljava/lang/Object;");
    }

    <T> T setData(AttachmentType<T> type, T data);

    default <T> T setData(Supplier<AttachmentType<T>> type, T data) {
        throw Unimplemented.forMember("net/neoforged/neoforge/attachment/IAttachmentHolder.setData:(Ljava/util/function/Supplier;Ljava/lang/Object;)Ljava/lang/Object;");
    }

    <T> T removeData(AttachmentType<T> type);

    default <T> T removeData(Supplier<AttachmentType<T>> type) {
        throw Unimplemented.forMember("net/neoforged/neoforge/attachment/IAttachmentHolder.removeData:(Ljava/util/function/Supplier;)Ljava/lang/Object;");
    }
}
