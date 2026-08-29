package net.minecraft.server.level;

import java.util.function.Predicate;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.Entity;

public class ServerEntity {

    public ServerEntity(ServerLevel level, Entity entity, int updateInterval, boolean trackDelta, ServerEntity.Synchronizer synchronizer) {
    }

    public interface Synchronizer {

        void sendToTrackingPlayers(Packet<? super ClientGamePacketListener> packet);

        void sendToTrackingPlayersAndSelf(Packet<? super ClientGamePacketListener> packet);

        void sendToTrackingPlayersFiltered(Packet<? super ClientGamePacketListener> packet, Predicate<ServerPlayer> predicate);
    }

    public ServerEntity() {
    }
}
