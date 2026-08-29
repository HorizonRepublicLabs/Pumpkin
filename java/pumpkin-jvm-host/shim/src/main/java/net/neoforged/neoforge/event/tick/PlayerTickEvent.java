package net.neoforged.neoforge.event.tick;

import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import dev.pumpkin.shim.Unimplemented;

public abstract class PlayerTickEvent extends PlayerEvent {

    protected PlayerTickEvent(Player player) {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/tick/PlayerTickEvent.<init>:(Lnet/minecraft/world/entity/player/Player;)V");
    }

    public static class Pre extends PlayerTickEvent {

        public Pre(Player player) {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/tick/PlayerTickEvent$Pre.<init>:(Lnet/minecraft/world/entity/player/Player;)V");
        }

        public Pre() {
        }
    }

    public static class Post extends PlayerTickEvent {

        public Post(Player player) {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/tick/PlayerTickEvent$Post.<init>:(Lnet/minecraft/world/entity/player/Player;)V");
        }

        public Post() {
        }
    }

    public PlayerTickEvent() {
    }
}
