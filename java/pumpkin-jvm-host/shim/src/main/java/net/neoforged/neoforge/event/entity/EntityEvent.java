package net.neoforged.neoforge.event.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.Pose;
import net.neoforged.bus.api.Event;
import dev.pumpkin.shim.Unimplemented;

public abstract class EntityEvent extends Event {

    public EntityEvent(Entity entity) {
    }

    public Entity getEntity() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/EntityEvent.getEntity:()Lnet/minecraft/world/entity/Entity;");
    }

    public static class EntityConstructing extends EntityEvent {

        public EntityConstructing(Entity entity) {
        }

        public EntityConstructing() {
        }
    }

    public static class EnteringSection extends EntityEvent {

        public EnteringSection(Entity entity, long packedOldPos, long packedNewPos) {
        }

        public EnteringSection() {
        }
    }

    public static class Size extends EntityEvent {

        public Size(Entity entity, Pose pose, EntityDimensions size) {
        }

        public Size(Entity entity, Pose pose, EntityDimensions oldSize, EntityDimensions newSize) {
        }

        public Size() {
        }
    }

    public EntityEvent() {
    }
}
