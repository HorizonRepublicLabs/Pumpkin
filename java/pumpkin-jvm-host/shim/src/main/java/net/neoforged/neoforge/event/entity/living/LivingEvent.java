package net.neoforged.neoforge.event.entity.living;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.event.entity.EntityEvent;
import dev.pumpkin.shim.Unimplemented;

public abstract class LivingEvent extends EntityEvent {

    public LivingEvent(LivingEntity entity) {
    }

    public LivingEntity getEntity() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/living/LivingEvent.getEntity:()Lnet/minecraft/world/entity/LivingEntity;");
    }

    public static class LivingJumpEvent extends LivingEvent {

        public LivingJumpEvent(LivingEntity e) {
        }

        public LivingJumpEvent() {
        }
    }

    public static class LivingVisibilityEvent extends LivingEvent {

        public LivingVisibilityEvent(LivingEntity livingEntity, Entity lookingEntity, double originalMultiplier) {
        }

        public LivingVisibilityEvent() {
        }
    }

    public LivingEvent() {
    }
}
