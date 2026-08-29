package net.neoforged.neoforge.event.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.Pose;
import net.neoforged.bus.api.Event;
import dev.pumpkin.shim.Unimplemented;

public abstract class EntityEvent extends Event {

    public EntityEvent(Entity entity) {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/EntityEvent.<init>:(Lnet/minecraft/world/entity/Entity;)V");
    }

    public Entity getEntity() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/EntityEvent.getEntity:()Lnet/minecraft/world/entity/Entity;");
    }

    public static class EntityConstructing extends EntityEvent {

        public EntityConstructing(Entity entity) {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/EntityEvent$EntityConstructing.<init>:(Lnet/minecraft/world/entity/Entity;)V");
        }

        protected EntityConstructing() {
        }
    }

    public static class EnteringSection extends EntityEvent {

        public EnteringSection(Entity entity, long packedOldPos, long packedNewPos) {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/EntityEvent$EnteringSection.<init>:(Lnet/minecraft/world/entity/Entity;JJ)V");
        }

        protected EnteringSection() {
        }
    }

    public static class Size extends EntityEvent {

        public Size(Entity entity, Pose pose, EntityDimensions size) {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/EntityEvent$Size.<init>:(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/entity/Pose;Lnet/minecraft/world/entity/EntityDimensions;)V");
        }

        public Size(Entity entity, Pose pose, EntityDimensions oldSize, EntityDimensions newSize) {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/EntityEvent$Size.<init>:(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/entity/Pose;Lnet/minecraft/world/entity/EntityDimensions;Lnet/minecraft/world/entity/EntityDimensions;)V");
        }

        protected Size() {
        }
    }

    protected EntityEvent() {
    }
}
