package net.minecraft.advancements.predicates;

import java.util.Map;
import java.util.Optional;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import dev.pumpkin.shim.Unimplemented;

public record MobEffectsPredicate(Map<Holder<MobEffect>, MobEffectsPredicate.MobEffectInstancePredicate> effectMap) {

    public boolean matches(Entity entity) {
        throw Unimplemented.forMember("net/minecraft/advancements/predicates/MobEffectsPredicate.matches:(Lnet/minecraft/world/entity/Entity;)Z");
    }

    public boolean matches(LivingEntity entity) {
        throw Unimplemented.forMember("net/minecraft/advancements/predicates/MobEffectsPredicate.matches:(Lnet/minecraft/world/entity/LivingEntity;)Z");
    }

    public boolean matches(Map<Holder<MobEffect>, MobEffectInstance> effects) {
        throw Unimplemented.forMember("net/minecraft/advancements/predicates/MobEffectsPredicate.matches:(Ljava/util/Map;)Z");
    }

    public static class Builder {

        public MobEffectsPredicate build() {
            throw Unimplemented.forMember("net/minecraft/advancements/predicates/MobEffectsPredicate$Builder.build:()Lnet/minecraft/advancements/predicates/MobEffectsPredicate;");
        }

        public Builder() {
        }
    }

    public record MobEffectInstancePredicate(MinMaxBounds.Ints amplifier, MinMaxBounds.Ints duration, Optional<Boolean> ambient, Optional<Boolean> visible) {

        public MobEffectInstancePredicate() {
            this((MinMaxBounds.Ints) null, (MinMaxBounds.Ints) null, (Optional<Boolean>) null, (Optional<Boolean>) null);
        }

        public boolean matches(MobEffectInstance instance) {
            throw Unimplemented.forMember("net/minecraft/advancements/predicates/MobEffectsPredicate$MobEffectInstancePredicate.matches:(Lnet/minecraft/world/effect/MobEffectInstance;)Z");
        }
    }
}
