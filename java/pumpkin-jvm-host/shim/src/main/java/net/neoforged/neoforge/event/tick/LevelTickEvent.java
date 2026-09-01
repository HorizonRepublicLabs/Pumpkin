package net.neoforged.neoforge.event.tick;

import java.util.function.BooleanSupplier;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.Event;
import dev.pumpkin.shim.Unimplemented;

public abstract class LevelTickEvent extends Event {

    // Pumpkin divergence: the event carries what its constructor was given; a bare
    // constructor left it empty, and asking then still refuses.
    private Level pumpkinLevel;

    protected LevelTickEvent(BooleanSupplier hasTime, Level level) {
        this.pumpkinLevel = level;
    }

    public Level getLevel() {
        if (pumpkinLevel == null) {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/tick/LevelTickEvent.getLevel:()Lnet/minecraft/world/level/Level;");
        }
        return pumpkinLevel;
    }

    public static class Pre extends LevelTickEvent {

        public Pre(BooleanSupplier haveTime, Level level) {
            super(haveTime, level);
        }

        public Pre() {
        }
    }

    public static class Post extends LevelTickEvent {

        public Post(BooleanSupplier haveTime, Level level) {
            super(haveTime, level);
        }

        public Post() {
        }
    }

    public LevelTickEvent() {
    }
}
