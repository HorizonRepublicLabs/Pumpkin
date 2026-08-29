package net.minecraft.network.protocol.game;

import java.util.Set;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundLoginPacket(int playerId, boolean hardcore, Set<ResourceKey<Level>> levels, int maxPlayers, int chunkRadius, int simulationDistance, boolean reducedDebugInfo, boolean showDeathScreen, boolean doLimitedCrafting, CommonPlayerSpawnInfo commonPlayerSpawnInfo, boolean onlineMode, boolean enforcesSecureChat) implements Packet<ClientGamePacketListener> {

    private ClientboundLoginPacket(RegistryFriendlyByteBuf input) {
        this((int) 0, (boolean) false, (Set<ResourceKey<Level>>) null, (int) 0, (int) 0, (int) 0, (boolean) false, (boolean) false, (boolean) false, (CommonPlayerSpawnInfo) null, (boolean) false, (boolean) false);
    }

    private void write(RegistryFriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundLoginPacket.write:(Lnet/minecraft/network/RegistryFriendlyByteBuf;)V");
    }

    public PacketType<ClientboundLoginPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundLoginPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundLoginPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }
}
