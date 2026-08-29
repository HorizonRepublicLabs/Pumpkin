package net.minecraft.network.protocol.game;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.inventory.RecipeBookType;
import dev.pumpkin.shim.Unimplemented;

public class ServerboundRecipeBookChangeSettingsPacket implements Packet<ServerGamePacketListener> {

    public ServerboundRecipeBookChangeSettingsPacket(RecipeBookType bookType, boolean isOpen, boolean isFiltering) {
    }

    private ServerboundRecipeBookChangeSettingsPacket(FriendlyByteBuf input) {
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundRecipeBookChangeSettingsPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ServerboundRecipeBookChangeSettingsPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundRecipeBookChangeSettingsPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundRecipeBookChangeSettingsPacket.handle:(Lnet/minecraft/network/protocol/game/ServerGamePacketListener;)V");
    }

    public ServerboundRecipeBookChangeSettingsPacket() {
    }
}
