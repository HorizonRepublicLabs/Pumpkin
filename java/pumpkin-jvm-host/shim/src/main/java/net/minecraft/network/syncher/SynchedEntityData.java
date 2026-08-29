package net.minecraft.network.syncher;

import net.minecraft.network.RegistryFriendlyByteBuf;
import dev.pumpkin.shim.Unimplemented;

public class SynchedEntityData {

    private SynchedEntityData(SyncedDataHolder entity, SynchedEntityData.DataItem<?>[] itemsById) {
        throw Unimplemented.forMember("net/minecraft/network/syncher/SynchedEntityData.<init>:(Lnet/minecraft/network/syncher/SyncedDataHolder;[Lnet/minecraft/network/syncher/SynchedEntityData$DataItem;)V");
    }

    private <T> SynchedEntityData.DataItem<T> getItem(EntityDataAccessor<T> accessor) {
        throw Unimplemented.forMember("net/minecraft/network/syncher/SynchedEntityData.getItem:(Lnet/minecraft/network/syncher/EntityDataAccessor;)Lnet/minecraft/network/syncher/SynchedEntityData$DataItem;");
    }

    public <T> T get(EntityDataAccessor<T> accessor) {
        throw Unimplemented.forMember("net/minecraft/network/syncher/SynchedEntityData.get:(Lnet/minecraft/network/syncher/EntityDataAccessor;)Ljava/lang/Object;");
    }

    public <T> void set(EntityDataAccessor<T> accessor, T value) {
        throw Unimplemented.forMember("net/minecraft/network/syncher/SynchedEntityData.set:(Lnet/minecraft/network/syncher/EntityDataAccessor;Ljava/lang/Object;)V");
    }

    public <T> void set(EntityDataAccessor<T> accessor, T value, boolean forceDirty) {
        throw Unimplemented.forMember("net/minecraft/network/syncher/SynchedEntityData.set:(Lnet/minecraft/network/syncher/EntityDataAccessor;Ljava/lang/Object;Z)V");
    }

    public static class Builder {

        public Builder(SyncedDataHolder entity) {
            throw Unimplemented.forMember("net/minecraft/network/syncher/SynchedEntityData$Builder.<init>:(Lnet/minecraft/network/syncher/SyncedDataHolder;)V");
        }

        public SynchedEntityData build() {
            throw Unimplemented.forMember("net/minecraft/network/syncher/SynchedEntityData$Builder.build:()Lnet/minecraft/network/syncher/SynchedEntityData;");
        }

        public Builder() {
        }
    }

    public static class DataItem<T> {

        public DataItem(EntityDataAccessor<T> accessor, T initialValue) {
            throw Unimplemented.forMember("net/minecraft/network/syncher/SynchedEntityData$DataItem.<init>:(Lnet/minecraft/network/syncher/EntityDataAccessor;Ljava/lang/Object;)V");
        }

        public SynchedEntityData.DataValue<T> value() {
            throw Unimplemented.forMember("net/minecraft/network/syncher/SynchedEntityData$DataItem.value:()Lnet/minecraft/network/syncher/SynchedEntityData$DataValue;");
        }

        public DataItem() {
        }
    }

    public record DataValue<T>(int id, EntityDataSerializer<T> serializer, T value) {

        public static <T> SynchedEntityData.DataValue<T> create(EntityDataAccessor<T> accessor, T value) {
            throw Unimplemented.forMember("net/minecraft/network/syncher/SynchedEntityData$DataValue.create:(Lnet/minecraft/network/syncher/EntityDataAccessor;Ljava/lang/Object;)Lnet/minecraft/network/syncher/SynchedEntityData$DataValue;");
        }

        public void write(RegistryFriendlyByteBuf output) {
            throw Unimplemented.forMember("net/minecraft/network/syncher/SynchedEntityData$DataValue.write:(Lnet/minecraft/network/RegistryFriendlyByteBuf;)V");
        }

        public static SynchedEntityData.DataValue<?> read(RegistryFriendlyByteBuf input, int id) {
            throw Unimplemented.forMember("net/minecraft/network/syncher/SynchedEntityData$DataValue.read:(Lnet/minecraft/network/RegistryFriendlyByteBuf;I)Lnet/minecraft/network/syncher/SynchedEntityData$DataValue;");
        }
    }

    public SynchedEntityData() {
    }
}
