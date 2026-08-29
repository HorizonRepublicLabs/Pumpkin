package net.minecraft.network.protocol.game;

import java.util.Collection;
import java.util.List;
import net.minecraft.core.Holder;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundUpdateAttributesPacket implements Packet<ClientGamePacketListener> {

    public ClientboundUpdateAttributesPacket(int entityId, Collection<AttributeInstance> values) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundUpdateAttributesPacket.<init>:(ILjava/util/Collection;)V");
    }

    private ClientboundUpdateAttributesPacket(int entityId, List<ClientboundUpdateAttributesPacket.AttributeSnapshot> attributes) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundUpdateAttributesPacket.<init>:(ILjava/util/List;)V");
    }

    public PacketType<ClientboundUpdateAttributesPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundUpdateAttributesPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundUpdateAttributesPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    public record AttributeSnapshot(Holder<Attribute> attribute, double base, Collection<AttributeModifier> modifiers) {
    }

    protected ClientboundUpdateAttributesPacket() {
    }
}
