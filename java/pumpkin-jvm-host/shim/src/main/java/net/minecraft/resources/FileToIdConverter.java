package net.minecraft.resources;

import java.util.Map;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import dev.pumpkin.shim.Unimplemented;

public record FileToIdConverter(String prefix, String extension) {

    public Identifier fileToId(Identifier file) {
        throw Unimplemented.forMember("net/minecraft/resources/FileToIdConverter.fileToId:(Lnet/minecraft/resources/Identifier;)Lnet/minecraft/resources/Identifier;");
    }

    public Map<Identifier, Resource> listMatchingResources(ResourceManager manager) {
        throw Unimplemented.forMember("net/minecraft/resources/FileToIdConverter.listMatchingResources:(Lnet/minecraft/server/packs/resources/ResourceManager;)Ljava/util/Map;");
    }
}
