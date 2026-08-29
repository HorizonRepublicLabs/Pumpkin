package net.minecraft.network.protocol.game;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.item.trading.MerchantOffers;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundMerchantOffersPacket implements Packet<ClientGamePacketListener> {

    public ClientboundMerchantOffersPacket(int containerId, MerchantOffers offers, int merchantLevel, int merchantXp, boolean showProgress, boolean canRestock) {
    }

    private ClientboundMerchantOffersPacket(RegistryFriendlyByteBuf input) {
    }

    private void write(RegistryFriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundMerchantOffersPacket.write:(Lnet/minecraft/network/RegistryFriendlyByteBuf;)V");
    }

    public PacketType<ClientboundMerchantOffersPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundMerchantOffersPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundMerchantOffersPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    public ClientboundMerchantOffersPacket() {
    }
}
