package net.minecraft.server;

import com.mojang.datafixers.util.Either;
import java.net.URI;
import java.util.List;
import net.minecraft.network.chat.Component;
import dev.pumpkin.shim.Unimplemented;

public record ServerLinks(List<ServerLinks.Entry> entries) {

    public boolean isEmpty() {
        throw Unimplemented.forMember("net/minecraft/server/ServerLinks.isEmpty:()Z");
    }

    public record Entry(Either<ServerLinks.KnownLinkType, Component> type, URI link) {
    }

    public enum KnownLinkType {

        BUG_REPORT,
        COMMUNITY_GUIDELINES,
        SUPPORT,
        STATUS,
        FEEDBACK,
        COMMUNITY,
        WEBSITE,
        FORUMS,
        NEWS,
        ANNOUNCEMENTS;

        public ServerLinks.Entry create(URI link) {
            throw Unimplemented.forMember("net/minecraft/server/ServerLinks$KnownLinkType.create:(Ljava/net/URI;)Lnet/minecraft/server/ServerLinks$Entry;");
        }
    }

    public record UntrustedEntry(Either<ServerLinks.KnownLinkType, Component> type, String link) {
    }
}
