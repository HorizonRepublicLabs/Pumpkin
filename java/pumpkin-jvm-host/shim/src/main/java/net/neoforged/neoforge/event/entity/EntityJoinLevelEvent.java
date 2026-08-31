package net.neoforged.neoforge.event.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.ICancellableEvent;
import dev.pumpkin.shim.Unimplemented;

public class EntityJoinLevelEvent extends EntityEvent implements ICancellableEvent {

    public EntityJoinLevelEvent(Entity entity, Level level) {
    }

    public EntityJoinLevelEvent(Entity entity, Level level, boolean loadedFromDisk) {
    }

    public Level getLevel() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/EntityJoinLevelEvent.getLevel:()Lnet/minecraft/world/level/Level;");
    }

    public EntityJoinLevelEvent() {
    }
}
