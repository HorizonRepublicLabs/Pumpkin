package net.neoforged.neoforge.event.tick;

import net.minecraft.world.entity.Entity;
import net.neoforged.bus.api.ICancellableEvent;
import net.neoforged.neoforge.event.entity.EntityEvent;
import dev.pumpkin.shim.Unimplemented;

public abstract class EntityTickEvent extends EntityEvent {

    protected EntityTickEvent(Entity entity) {
    }

    public static class Pre extends EntityTickEvent implements ICancellableEvent {

        public Pre(Entity entity) {
        }

        public void setCanceled(boolean canceled) {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/tick/EntityTickEvent$Pre.setCanceled:(Z)V");
        }

        public Pre() {
        }
    }

    public static class Post extends EntityTickEvent {

        public Post(Entity entity) {
        }

        public Post() {
        }
    }

    public EntityTickEvent() {
    }
}
