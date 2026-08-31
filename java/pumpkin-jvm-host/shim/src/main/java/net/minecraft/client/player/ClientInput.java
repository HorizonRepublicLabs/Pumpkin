package net.minecraft.client.player;

import net.minecraft.world.entity.player.Input;
import dev.pumpkin.shim.Unimplemented;

public class ClientInput {

    public Input keyPresses;

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/client/player/ClientInput.tick:()V");
    }

    public ClientInput() {
    }
}
