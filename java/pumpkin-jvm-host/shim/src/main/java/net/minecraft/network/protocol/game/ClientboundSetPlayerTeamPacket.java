package net.minecraft.network.protocol.game;

import java.util.Collection;
import java.util.Optional;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.scores.PlayerTeam;
import net.minecraft.world.scores.Team;
import net.minecraft.world.scores.TeamColor;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundSetPlayerTeamPacket implements Packet<ClientGamePacketListener> {

    private ClientboundSetPlayerTeamPacket(String name, int method, Optional<ClientboundSetPlayerTeamPacket.Parameters> parameters, Collection<String> players) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetPlayerTeamPacket.<init>:(Ljava/lang/String;ILjava/util/Optional;Ljava/util/Collection;)V");
    }

    private ClientboundSetPlayerTeamPacket(RegistryFriendlyByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetPlayerTeamPacket.<init>:(Lnet/minecraft/network/RegistryFriendlyByteBuf;)V");
    }

    private void write(RegistryFriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetPlayerTeamPacket.write:(Lnet/minecraft/network/RegistryFriendlyByteBuf;)V");
    }

    public PacketType<ClientboundSetPlayerTeamPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetPlayerTeamPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetPlayerTeamPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    public String getName() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetPlayerTeamPacket.getName:()Ljava/lang/String;");
    }

    public Collection<String> getPlayers() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetPlayerTeamPacket.getPlayers:()Ljava/util/Collection;");
    }

    public enum Action {

        ADD, REMOVE
    }

    public record Parameters(Component displayName, Component playerPrefix, Component playerSuffix, Team.Visibility nameTagVisibility, Team.CollisionRule collisionRule, Optional<TeamColor> color, byte options) {

        public Parameters(PlayerTeam team) {
            this((Component) null, (Component) null, (Component) null, (Team.Visibility) null, (Team.CollisionRule) null, (Optional<TeamColor>) null, (byte) 0);
            throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetPlayerTeamPacket$Parameters.<init>:(Lnet/minecraft/world/scores/PlayerTeam;)V");
        }
    }

    public ClientboundSetPlayerTeamPacket() {
    }
}
