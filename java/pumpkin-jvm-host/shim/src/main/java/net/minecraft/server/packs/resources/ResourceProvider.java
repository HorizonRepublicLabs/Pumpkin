package net.minecraft.server.packs.resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import net.minecraft.resources.Identifier;
import dev.pumpkin.shim.Unimplemented;

public interface ResourceProvider {

    Optional<Resource> getResource(Identifier location);

    default InputStream open(Identifier location) throws IOException {
        throw Unimplemented.forMember("net/minecraft/server/packs/resources/ResourceProvider.open:(Lnet/minecraft/resources/Identifier;)Ljava/io/InputStream;");
    }
}
