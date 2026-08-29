package net.minecraft.network.protocol.game;

import it.unimi.dsi.fastutil.shorts.ShortSet;
import net.minecraft.core.SectionPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.level.chunk.LevelChunkSection;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundSectionBlocksUpdatePacket implements Packet<ClientGamePacketListener> {

    public ClientboundSectionBlocksUpdatePacket(SectionPos sectionPos, ShortSet changes, LevelChunkSection section) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSectionBlocksUpdatePacket.<init>:(Lnet/minecraft/core/SectionPos;Lit/unimi/dsi/fastutil/shorts/ShortSet;Lnet/minecraft/world/level/chunk/LevelChunkSection;)V");
    }

    private ClientboundSectionBlocksUpdatePacket(FriendlyByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSectionBlocksUpdatePacket.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSectionBlocksUpdatePacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ClientboundSectionBlocksUpdatePacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSectionBlocksUpdatePacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSectionBlocksUpdatePacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    public ClientboundSectionBlocksUpdatePacket() {
    }
}
