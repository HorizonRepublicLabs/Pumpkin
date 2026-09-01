package net.neoforged.neoforge.attachment;

import dev.pumpkin.shim.Unimplemented;

public abstract class AttachmentHolder implements IAttachmentHolder {

    // Pumpkin divergence: a real in-memory store. NeoForge lazily allocates the same
    // map; the difference is persistence, which Pumpkin's save path does not carry
    // yet -- attachments live for the run.
    private java.util.Map<AttachmentType<?>, Object> pumpkinAttachments;

    private java.util.Map<AttachmentType<?>, Object> pumpkinStore() {
        if (pumpkinAttachments == null) {
            pumpkinAttachments = new java.util.IdentityHashMap<>(4);
        }
        return pumpkinAttachments;
    }

    public final boolean hasAttachments() {
        return pumpkinAttachments != null && !pumpkinAttachments.isEmpty();
    }

    public final boolean hasData(AttachmentType<?> type) {
        return pumpkinAttachments != null && pumpkinAttachments.containsKey(type);
    }

    @SuppressWarnings("unchecked")
    public final <T> T getData(AttachmentType<T> type) {
        T existing = getExistingDataOrNull(type);
        if (existing != null) {
            return existing;
        }
        if (type.pumpkinDefault == null) {
            throw Unimplemented.forMember(
                    "net/neoforged/neoforge/attachment/AttachmentHolder.getData (type built without a default)");
        }
        T created = (T) type.pumpkinDefault.apply(this);
        pumpkinStore().put(type, created);
        return created;
    }

    @SuppressWarnings("unchecked")
    public <T> T getExistingDataOrNull(AttachmentType<T> type) {
        return pumpkinAttachments == null ? null : (T) pumpkinAttachments.get(type);
    }

    @SuppressWarnings("unchecked")
    public <T> T setData(AttachmentType<T> type, T data) {
        return (T) pumpkinStore().put(type, data);
    }

    @SuppressWarnings("unchecked")
    public <T> T removeData(AttachmentType<T> type) {
        return pumpkinAttachments == null ? null : (T) pumpkinAttachments.remove(type);
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
