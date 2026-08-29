package net.minecraft.network.protocol.game;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.level.saveddata.maps.MapDecoration;
import net.minecraft.world.level.saveddata.maps.MapId;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundMapItemDataPacket(MapId mapId, byte scale, boolean locked, Optional<List<MapDecoration>> decorations, Optional<MapItemSavedData.MapPatch> colorPatch) implements Packet<ClientGamePacketListener> {

    public ClientboundMapItemDataPacket(MapId mapId, byte scale, boolean locked, Collection<MapDecoration> decorations, MapItemSavedData.MapPatch colorPatch) {
        this((MapId) null, (byte) 0, (boolean) false, (Optional<List<MapDecoration>>) null, (Optional<MapItemSavedData.MapPatch>) null);
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundMapItemDataPacket.<init>:(Lnet/minecraft/world/level/saveddata/maps/MapId;BZLjava/util/Collection;Lnet/minecraft/world/level/saveddata/maps/MapItemSavedData$MapPatch;)V");
    }

    public PacketType<ClientboundMapItemDataPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundMapItemDataPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundMapItemDataPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }
}
