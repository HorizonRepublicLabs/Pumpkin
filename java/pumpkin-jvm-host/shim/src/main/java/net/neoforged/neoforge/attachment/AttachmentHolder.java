package net.neoforged.neoforge.attachment;

import dev.pumpkin.shim.Unimplemented;

public abstract class AttachmentHolder implements IAttachmentHolder {

    public final boolean hasAttachments() {
        throw Unimplemented.forMember("net/neoforged/neoforge/attachment/AttachmentHolder.hasAttachments:()Z");
    }

    public final boolean hasData(AttachmentType<?> type) {
        throw Unimplemented.forMember("net/neoforged/neoforge/attachment/AttachmentHolder.hasData:(Lnet/neoforged/neoforge/attachment/AttachmentType;)Z");
    }

    public final <T> T getData(AttachmentType<T> type) {
        throw Unimplemented.forMember("net/neoforged/neoforge/attachment/AttachmentHolder.getData:(Lnet/neoforged/neoforge/attachment/AttachmentType;)Ljava/lang/Object;");
    }

    public <T> T getExistingDataOrNull(AttachmentType<T> type) {
        throw Unimplemented.forMember("net/neoforged/neoforge/attachment/AttachmentHolder.getExistingDataOrNull:(Lnet/neoforged/neoforge/attachment/AttachmentType;)Ljava/lang/Object;");
    }

    public <T> T setData(AttachmentType<T> type, T data) {
        throw Unimplemented.forMember("net/neoforged/neoforge/attachment/AttachmentHolder.setData:(Lnet/neoforged/neoforge/attachment/AttachmentType;Ljava/lang/Object;)Ljava/lang/Object;");
    }

    public <T> T removeData(AttachmentType<T> type) {
        throw Unimplemented.forMember("net/neoforged/neoforge/attachment/AttachmentHolder.removeData:(Lnet/neoforged/neoforge/attachment/AttachmentType;)Ljava/lang/Object;");
    }

    public static class AsField extends AttachmentHolder {

        public AsField(IAttachmentHolder exposedHolder) {
        }

        IAttachmentHolder getExposedHolder() {
            throw Unimplemented.forMember("net/neoforged/neoforge/attachment/AttachmentHolder$AsField.getExposedHolder:()Lnet/neoforged/neoforge/attachment/IAttachmentHolder;");
        }

        public void syncData(AttachmentType<?> type) {
            throw Unimplemented.forMember("net/neoforged/neoforge/attachment/AttachmentHolder$AsField.syncData:(Lnet/neoforged/neoforge/attachment/AttachmentType;)V");
        }

        public AsField() {
        }
    }

    public AttachmentHolder() {
    }
}
