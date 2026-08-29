package net.minecraft.network.protocol.status;

import java.util.List;
import java.util.Optional;
import net.minecraft.network.chat.Component;
import net.minecraft.server.players.NameAndId;
import dev.pumpkin.shim.Unimplemented;

public record ServerStatus(Component description, Optional<ServerStatus.Players> players, Optional<ServerStatus.Version> version, Optional<ServerStatus.Favicon> favicon, boolean enforcesSecureChat, boolean isModded) {

    public ServerStatus(Component description, Optional<Players> players, Optional<Version> version, Optional<Favicon> favicon, boolean enforcesSecureChat) {
        this((Component) null, (Optional<ServerStatus.Players>) null, (Optional<ServerStatus.Version>) null, (Optional<ServerStatus.Favicon>) null, (boolean) false, (boolean) false);
        throw Unimplemented.forMember("net/minecraft/network/protocol/status/ServerStatus.<init>:(Lnet/minecraft/network/chat/Component;Ljava/util/Optional;Ljava/util/Optional;Ljava/util/Optional;Z)V");
    }

    public record Favicon(byte[] iconBytes) {
    }

    public record Players(int max, int online, List<NameAndId> sample) {
    }

    public record Version(String name, int protocol) {
    }
}
