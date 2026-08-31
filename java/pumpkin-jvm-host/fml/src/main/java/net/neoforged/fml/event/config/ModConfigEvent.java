package net.neoforged.fml.event.config;

import net.neoforged.bus.api.Event;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.IModBusEvent;

/**
 * Fired when a mod's config file loads or reloads. Pumpkin does not read config files
 * yet, so nothing posts these; mods subscribe at construction and their listeners simply
 * wait. The type exists so that subscription -- which happens unconditionally -- resolves.
 */
public abstract class ModConfigEvent extends Event implements IModBusEvent {
    private final ModConfig config;

    protected ModConfigEvent(ModConfig config) {
        this.config = config;
    }

    public ModConfig getConfig() {
        return config;
    }

    public static class Loading extends ModConfigEvent {
        public Loading(ModConfig config) {
            super(config);
        }
    }

    public static class Reloading extends ModConfigEvent {
        public Reloading(ModConfig config) {
            super(config);
        }
    }

    public static class Unloading extends ModConfigEvent {
        public Unloading(ModConfig config) {
            super(config);
        }
    }
}
