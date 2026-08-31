package net.neoforged.neoforge.event.tick;

import java.util.function.BooleanSupplier;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.Event;
import dev.pumpkin.shim.Unimplemented;

public abstract class LevelTickEvent extends Event {

    protected LevelTickEvent(BooleanSupplier hasTime, Level level) {
    }

    public Level getLevel() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/tick/LevelTickEvent.getLevel:()Lnet/minecraft/world/level/Level;");
    }

    public static class Pre extends LevelTickEvent {

        public Pre(BooleanSupplier haveTime, Level level) {
        }

        public Pre() {
        }
    }

    public static class Post extends LevelTickEvent {

        public Post(BooleanSupplier haveTime, Level level) {
        }

        public Post() {
        }
    }

    public LevelTickEvent() {
    }
}
