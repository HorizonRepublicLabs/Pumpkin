package net.minecraft.server.packs.resources;

import java.util.Optional;
import net.minecraft.resources.Identifier;

public interface ResourceProvider {

    Optional<Resource> getResource(Identifier location);
}
