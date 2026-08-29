package net.neoforged.neoforge.client.event;

import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.Event;
import dev.pumpkin.shim.Unimplemented;

public class ComputeFovModifierEvent extends Event {

    public ComputeFovModifierEvent(Player player, float fovModifier, float fovScale) {
    }

    public Player getPlayer() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/ComputeFovModifierEvent.getPlayer:()Lnet/minecraft/world/entity/player/Player;");
    }

    public float getNewFovModifier() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/ComputeFovModifierEvent.getNewFovModifier:()F");
    }

    public void setNewFovModifier(float newFovModifier) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/ComputeFovModifierEvent.setNewFovModifier:(F)V");
    }

    public ComputeFovModifierEvent() {
    }
}
