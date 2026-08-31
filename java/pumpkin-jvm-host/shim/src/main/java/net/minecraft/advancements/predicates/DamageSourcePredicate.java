package net.minecraft.advancements.predicates;

import java.util.List;
import java.util.Optional;
import net.minecraft.advancements.predicates.entity.EntityPredicate;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public record DamageSourcePredicate(List<TagPredicate<DamageType>> tags, Optional<EntityPredicate> directEntity, Optional<EntityPredicate> sourceEntity, Optional<Boolean> isDirect) {

    public boolean matches(ServerPlayer player, DamageSource source) {
        throw Unimplemented.forMember("net/minecraft/advancements/predicates/DamageSourcePredicate.matches:(Lnet/minecraft/server/level/ServerPlayer;Lnet/minecraft/world/damagesource/DamageSource;)Z");
    }

    public boolean matches(ServerLevel level, Vec3 position, DamageSource source) {
        throw Unimplemented.forMember("net/minecraft/advancements/predicates/DamageSourcePredicate.matches:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/world/damagesource/DamageSource;)Z");
    }

    public static class Builder {

        public DamageSourcePredicate.Builder tag(TagPredicate<DamageType> tag) {
            throw Unimplemented.forMember("net/minecraft/advancements/predicates/DamageSourcePredicate$Builder.tag:(Lnet/minecraft/advancements/predicates/TagPredicate;)Lnet/minecraft/advancements/predicates/DamageSourcePredicate$Builder;");
        }

        public DamageSourcePredicate build() {
            throw Unimplemented.forMember("net/minecraft/advancements/predicates/DamageSourcePredicate$Builder.build:()Lnet/minecraft/advancements/predicates/DamageSourcePredicate;");
        }

        public Builder() {
        }
    }
}
