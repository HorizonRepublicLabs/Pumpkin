package net.minecraft.network;

import java.net.URI;
import java.nio.file.Path;
import java.util.Optional;
import net.minecraft.network.chat.Component;
import dev.pumpkin.shim.Unimplemented;

public record DisconnectionDetails(Component reason, Optional<Path> report, Optional<URI> bugReportLink) {

    public DisconnectionDetails(Component reason) {
        this((Component) null, (Optional<Path>) null, (Optional<URI>) null);
        throw Unimplemented.forMember("net/minecraft/network/DisconnectionDetails.<init>:(Lnet/minecraft/network/chat/Component;)V");
    }
}
