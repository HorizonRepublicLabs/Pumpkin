package net.minecraft.server.level;

import java.util.function.Predicate;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.Entity;
import dev.pumpkin.shim.Unimplemented;

public class ServerEntity {

    public ServerEntity(ServerLevel level, Entity entity, int updateInterval, boolean trackDelta, ServerEntity.Synchronizer synchronizer) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerEntity.<init>:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/Entity;IZLnet/minecraft/server/level/ServerEntity$Synchronizer;)V");
    }

    public interface Synchronizer {

        void sendToTrackingPlayers(Packet<? super ClientGamePacketListener> packet);

        void sendToTrackingPlayersAndSelf(Packet<? super ClientGamePacketListener> packet);

        void sendToTrackingPlayersFiltered(Packet<? super ClientGamePacketListener> packet, Predicate<ServerPlayer> predicate);
    }

    public ServerEntity() {
    }
}
