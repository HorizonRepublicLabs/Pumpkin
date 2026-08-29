package net.minecraft.network.protocol.game;

import java.util.List;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.item.crafting.display.RecipeDisplayEntry;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundRecipeBookAddPacket(List<ClientboundRecipeBookAddPacket.Entry> entries, boolean replace) implements Packet<ClientGamePacketListener> {

    public PacketType<ClientboundRecipeBookAddPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundRecipeBookAddPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundRecipeBookAddPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    public record Entry(RecipeDisplayEntry contents, byte flags) {

        public Entry(RecipeDisplayEntry contents, boolean notification, boolean highlight) {
            this((RecipeDisplayEntry) null, (byte) 0);
        }
    }
}
