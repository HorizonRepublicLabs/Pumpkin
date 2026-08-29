package net.neoforged.neoforge.event.entity.player;

import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.ICancellableEvent;
import dev.pumpkin.shim.Unimplemented;

public abstract class PlayerXpEvent extends PlayerEvent {

    public PlayerXpEvent(Player player) {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/player/PlayerXpEvent.<init>:(Lnet/minecraft/world/entity/player/Player;)V");
    }

    public static class PickupXp extends PlayerXpEvent implements ICancellableEvent {

        public PickupXp(Player player, ExperienceOrb orb) {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/player/PlayerXpEvent$PickupXp.<init>:(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/entity/ExperienceOrb;)V");
        }

        public ExperienceOrb getOrb() {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/player/PlayerXpEvent$PickupXp.getOrb:()Lnet/minecraft/world/entity/ExperienceOrb;");
        }

        protected PickupXp() {
        }
    }

    public static class XpChange extends PlayerXpEvent implements ICancellableEvent {

        public XpChange(Player player, int amount) {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/player/PlayerXpEvent$XpChange.<init>:(Lnet/minecraft/world/entity/player/Player;I)V");
        }

        public int getAmount() {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/player/PlayerXpEvent$XpChange.getAmount:()I");
        }

        protected XpChange() {
        }
    }

    public static class LevelChange extends PlayerXpEvent implements ICancellableEvent {

        public LevelChange(Player player, int levels) {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/player/PlayerXpEvent$LevelChange.<init>:(Lnet/minecraft/world/entity/player/Player;I)V");
        }

        protected LevelChange() {
        }
    }

    protected PlayerXpEvent() {
    }
}
