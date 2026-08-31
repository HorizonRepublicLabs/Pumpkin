package net.minecraft.advancements.predicates.entity;

import com.mojang.serialization.Codec;
import java.util.Map;
import net.minecraft.advancements.predicates.ContextAwarePredicate;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public final class EntityPredicate {

    public static final Codec<ContextAwarePredicate> ADVANCEMENT_CODEC = null;

    public EntityPredicate(Map<Codec<? extends EntitySubPredicate>, EntitySubPredicate> parts) {
    }

    public boolean matches(ServerPlayer player, Entity entity) {
        throw Unimplemented.forMember("net/minecraft/advancements/predicates/entity/EntityPredicate.matches:(Lnet/minecraft/server/level/ServerPlayer;Lnet/minecraft/world/entity/Entity;)Z");
    }

    public boolean matches(ServerLevel level, Vec3 position, Entity entity) {
        throw Unimplemented.forMember("net/minecraft/advancements/predicates/entity/EntityPredicate.matches:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/world/entity/Entity;)Z");
    }

    public boolean equals(Object o) {
        throw Unimplemented.forMember("net/minecraft/advancements/predicates/entity/EntityPredicate.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/minecraft/advancements/predicates/entity/EntityPredicate.hashCode:()I");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/advancements/predicates/entity/EntityPredicate.toString:()Ljava/lang/String;");
    }

    public static class Builder {

        public EntityPredicate build() {
            throw Unimplemented.forMember("net/minecraft/advancements/predicates/entity/EntityPredicate$Builder.build:()Lnet/minecraft/advancements/predicates/entity/EntityPredicate;");
        }

        public Builder() {
        }
    }

    public EntityPredicate() {
    }
}
