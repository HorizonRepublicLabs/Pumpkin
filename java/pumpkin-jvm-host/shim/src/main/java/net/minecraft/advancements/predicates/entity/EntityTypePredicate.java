package net.minecraft.advancements.predicates.entity;

import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public record EntityTypePredicate(HolderSet<EntityType<?>> types) implements EntitySubPredicate {

    public static final Codec<EntityTypePredicate> CODEC = null;

    public static EntityTypePredicate of(HolderGetter<EntityType<?>> lookup, EntityType<?> type) {
        throw Unimplemented.forMember("net/minecraft/advancements/predicates/entity/EntityTypePredicate.of:(Lnet/minecraft/core/HolderGetter;Lnet/minecraft/world/entity/EntityType;)Lnet/minecraft/advancements/predicates/entity/EntityTypePredicate;");
    }

    public static EntityTypePredicate of(HolderGetter<EntityType<?>> lookup, TagKey<EntityType<?>> type) {
        throw Unimplemented.forMember("net/minecraft/advancements/predicates/entity/EntityTypePredicate.of:(Lnet/minecraft/core/HolderGetter;Lnet/minecraft/tags/TagKey;)Lnet/minecraft/advancements/predicates/entity/EntityTypePredicate;");
    }

    public boolean matches(Holder<EntityType<?>> type) {
        throw Unimplemented.forMember("net/minecraft/advancements/predicates/entity/EntityTypePredicate.matches:(Lnet/minecraft/core/Holder;)Z");
    }

    public boolean matches(Entity entity, ServerLevel level, Vec3 position) {
        throw Unimplemented.forMember("net/minecraft/advancements/predicates/entity/EntityTypePredicate.matches:(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/phys/Vec3;)Z");
    }
}
