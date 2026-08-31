package net.minecraft.network.syncher;

import net.minecraft.network.RegistryFriendlyByteBuf;
import dev.pumpkin.shim.Unimplemented;

public class SynchedEntityData {

    private SynchedEntityData(SyncedDataHolder entity, SynchedEntityData.DataItem<?>[] itemsById) {
    }

    public static <T> EntityDataAccessor<T> defineId(Class<? extends SyncedDataHolder> clazz, EntityDataSerializer<T> type) {
        throw Unimplemented.forMember("net/minecraft/network/syncher/SynchedEntityData.defineId:(Ljava/lang/Class;Lnet/minecraft/network/syncher/EntityDataSerializer;)Lnet/minecraft/network/syncher/EntityDataAccessor;");
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
        }

        public <T> SynchedEntityData.Builder define(EntityDataAccessor<T> accessor, T value) {
            throw Unimplemented.forMember("net/minecraft/network/syncher/SynchedEntityData$Builder.define:(Lnet/minecraft/network/syncher/EntityDataAccessor;Ljava/lang/Object;)Lnet/minecraft/network/syncher/SynchedEntityData$Builder;");
        }

        public SynchedEntityData build() {
            throw Unimplemented.forMember("net/minecraft/network/syncher/SynchedEntityData$Builder.build:()Lnet/minecraft/network/syncher/SynchedEntityData;");
        }

        public Builder() {
        }
    }

    public static class DataItem<T> {

        public DataItem(EntityDataAccessor<T> accessor, T initialValue) {
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
