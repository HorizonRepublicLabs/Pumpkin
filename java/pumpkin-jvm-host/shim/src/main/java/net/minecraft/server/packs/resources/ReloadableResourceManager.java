package net.minecraft.server.packs.resources;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackType;
import dev.pumpkin.shim.Unimplemented;

public class ReloadableResourceManager implements AutoCloseable, ResourceManager {

    public ReloadableResourceManager(PackType type) {
    }

    public void close() {
        throw Unimplemented.forMember("net/minecraft/server/packs/resources/ReloadableResourceManager.close:()V");
    }

    public Optional<Resource> getResource(Identifier location) {
        throw Unimplemented.forMember("net/minecraft/server/packs/resources/ReloadableResourceManager.getResource:(Lnet/minecraft/resources/Identifier;)Ljava/util/Optional;");
    }

    public Set<String> getNamespaces() {
        throw Unimplemented.forMember("net/minecraft/server/packs/resources/ReloadableResourceManager.getNamespaces:()Ljava/util/Set;");
    }

    public List<Resource> getResourceStack(Identifier location) {
        throw Unimplemented.forMember("net/minecraft/server/packs/resources/ReloadableResourceManager.getResourceStack:(Lnet/minecraft/resources/Identifier;)Ljava/util/List;");
    }

    public Map<Identifier, Resource> listResources(String directory, Predicate<Identifier> filenameFilter) {
        throw Unimplemented.forMember("net/minecraft/server/packs/resources/ReloadableResourceManager.listResources:(Ljava/lang/String;Ljava/util/function/Predicate;)Ljava/util/Map;");
    }

    public Map<Identifier, List<Resource>> listResourceStacks(String directory, Predicate<Identifier> filter) {
        throw Unimplemented.forMember("net/minecraft/server/packs/resources/ReloadableResourceManager.listResourceStacks:(Ljava/lang/String;Ljava/util/function/Predicate;)Ljava/util/Map;");
    }

    public Stream<PackResources> listPacks() {
        throw Unimplemented.forMember("net/minecraft/server/packs/resources/ReloadableResourceManager.listPacks:()Ljava/util/stream/Stream;");
    }

    public ReloadableResourceManager() {
    }
}
