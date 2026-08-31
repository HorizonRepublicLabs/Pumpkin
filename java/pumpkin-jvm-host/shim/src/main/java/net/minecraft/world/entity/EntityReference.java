package net.minecraft.world.entity;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import java.util.UUID;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.entity.UUIDLookup;
import net.minecraft.world.level.entity.UniquelyIdentifyable;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import dev.pumpkin.shim.Unimplemented;

public final class EntityReference<StoredEntityType extends UniquelyIdentifyable> {

    public static <Type extends UniquelyIdentifyable> Codec<EntityReference<Type>> codec() {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityReference.codec:()Lcom/mojang/serialization/Codec;");
    }

    public static <Type extends UniquelyIdentifyable> StreamCodec<ByteBuf, EntityReference<Type>> streamCodec() {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityReference.streamCodec:()Lnet/minecraft/network/codec/StreamCodec;");
    }

    private EntityReference(StoredEntityType entity) {
    }

    private EntityReference(UUID uuid) {
    }

    public static <T extends UniquelyIdentifyable> EntityReference<T> of(T entity) {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityReference.of:(Lnet/minecraft/world/level/entity/UniquelyIdentifyable;)Lnet/minecraft/world/entity/EntityReference;");
    }

    public static <T extends UniquelyIdentifyable> EntityReference<T> of(UUID uuid) {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityReference.of:(Ljava/util/UUID;)Lnet/minecraft/world/entity/EntityReference;");
    }

    public UUID getUUID() {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityReference.getUUID:()Ljava/util/UUID;");
    }

    public StoredEntityType getEntity(UUIDLookup<? extends UniquelyIdentifyable> lookup, Class<StoredEntityType> clazz) {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityReference.getEntity:(Lnet/minecraft/world/level/entity/UUIDLookup;Ljava/lang/Class;)Lnet/minecraft/world/level/entity/UniquelyIdentifyable;");
    }

    public StoredEntityType getEntity(Level level, Class<StoredEntityType> clazz) {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityReference.getEntity:(Lnet/minecraft/world/level/Level;Ljava/lang/Class;)Lnet/minecraft/world/level/entity/UniquelyIdentifyable;");
    }

    private StoredEntityType resolve(UniquelyIdentifyable entity, Class<StoredEntityType> clazz) {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityReference.resolve:(Lnet/minecraft/world/level/entity/UniquelyIdentifyable;Ljava/lang/Class;)Lnet/minecraft/world/level/entity/UniquelyIdentifyable;");
    }

    public boolean matches(StoredEntityType entity) {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityReference.matches:(Lnet/minecraft/world/level/entity/UniquelyIdentifyable;)Z");
    }

    public void store(ValueOutput output, String key) {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityReference.store:(Lnet/minecraft/world/level/storage/ValueOutput;Ljava/lang/String;)V");
    }

    public static void store(EntityReference<?> reference, ValueOutput output, String key) {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityReference.store:(Lnet/minecraft/world/entity/EntityReference;Lnet/minecraft/world/level/storage/ValueOutput;Ljava/lang/String;)V");
    }

    public static <StoredEntityType extends UniquelyIdentifyable> StoredEntityType get(EntityReference<StoredEntityType> reference, Level level, Class<StoredEntityType> clazz) {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityReference.get:(Lnet/minecraft/world/entity/EntityReference;Lnet/minecraft/world/level/Level;Ljava/lang/Class;)Lnet/minecraft/world/level/entity/UniquelyIdentifyable;");
    }

    public static Entity getEntity(EntityReference<Entity> reference, Level level) {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityReference.getEntity:(Lnet/minecraft/world/entity/EntityReference;Lnet/minecraft/world/level/Level;)Lnet/minecraft/world/entity/Entity;");
    }

    public static <StoredEntityType extends UniquelyIdentifyable> EntityReference<StoredEntityType> read(ValueInput input, String key) {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityReference.read:(Lnet/minecraft/world/level/storage/ValueInput;Ljava/lang/String;)Lnet/minecraft/world/entity/EntityReference;");
    }

    public boolean equals(Object obj) {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityReference.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityReference.hashCode:()I");
    }

    public EntityReference() {
    }
}
