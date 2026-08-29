package net.neoforged.neoforge.event.entity.player;

import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.ICancellableEvent;
import dev.pumpkin.shim.Unimplemented;

public abstract class PlayerXpEvent extends PlayerEvent {

    public PlayerXpEvent(Player player) {
    }

    public static class PickupXp extends PlayerXpEvent implements ICancellableEvent {

        public PickupXp(Player player, ExperienceOrb orb) {
        }

        public ExperienceOrb getOrb() {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/player/PlayerXpEvent$PickupXp.getOrb:()Lnet/minecraft/world/entity/ExperienceOrb;");
        }

        public PickupXp() {
        }
    }

    public static class XpChange extends PlayerXpEvent implements ICancellableEvent {

        public XpChange(Player player, int amount) {
        }

        public int getAmount() {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/player/PlayerXpEvent$XpChange.getAmount:()I");
        }

        public XpChange() {
        }
    }

    public static class LevelChange extends PlayerXpEvent implements ICancellableEvent {

        public LevelChange(Player player, int levels) {
        }

        public LevelChange() {
        }
    }

    public PlayerXpEvent() {
    }
}
