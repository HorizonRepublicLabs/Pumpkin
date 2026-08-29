package net.minecraft.network.protocol.game;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.resources.Identifier;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundUpdateAdvancementsPacket implements Packet<ClientGamePacketListener> {

    public ClientboundUpdateAdvancementsPacket(boolean reset, Collection<AdvancementHolder> newAdvancements, Set<Identifier> removedAdvancements, Map<Identifier, AdvancementProgress> progress, boolean showAdvancements) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundUpdateAdvancementsPacket.<init>:(ZLjava/util/Collection;Ljava/util/Set;Ljava/util/Map;Z)V");
    }

    private ClientboundUpdateAdvancementsPacket(RegistryFriendlyByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundUpdateAdvancementsPacket.<init>:(Lnet/minecraft/network/RegistryFriendlyByteBuf;)V");
    }

    private void write(RegistryFriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundUpdateAdvancementsPacket.write:(Lnet/minecraft/network/RegistryFriendlyByteBuf;)V");
    }

    public PacketType<ClientboundUpdateAdvancementsPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundUpdateAdvancementsPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundUpdateAdvancementsPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    protected ClientboundUpdateAdvancementsPacket() {
    }
}
