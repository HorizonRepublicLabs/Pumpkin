package net.minecraft.world.entity;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import java.util.UUID;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.level.Level;
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
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityReference.<init>:(Lnet/minecraft/world/level/entity/UniquelyIdentifyable;)V");
    }

    private EntityReference(UUID uuid) {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityReference.<init>:(Ljava/util/UUID;)V");
    }

    public UUID getUUID() {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityReference.getUUID:()Ljava/util/UUID;");
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

    public static <StoredEntityType extends UniquelyIdentifyable> EntityReference<StoredEntityType> read(ValueInput input, String key) {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityReference.read:(Lnet/minecraft/world/level/storage/ValueInput;Ljava/lang/String;)Lnet/minecraft/world/entity/EntityReference;");
    }

    public boolean equals(Object obj) {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityReference.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityReference.hashCode:()I");
    }

    protected EntityReference() {
    }
}
