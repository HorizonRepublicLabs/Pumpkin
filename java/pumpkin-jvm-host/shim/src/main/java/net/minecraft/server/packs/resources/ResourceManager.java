package net.minecraft.server.packs.resources;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.PackResources;
import dev.pumpkin.shim.Unimplemented;

public interface ResourceManager extends ResourceProvider {

    Set<String> getNamespaces();

    List<Resource> getResourceStack(Identifier location);

    Map<Identifier, Resource> listResources(String directory, Predicate<Identifier> filter);

    Map<Identifier, List<Resource>> listResourceStacks(String directory, Predicate<Identifier> filter);

    Stream<PackResources> listPacks();

    enum Empty implements ResourceManager {

        INSTANCE;

        public Set<String> getNamespaces() {
            throw Unimplemented.forMember("net/minecraft/server/packs/resources/ResourceManager$Empty.getNamespaces:()Ljava/util/Set;");
        }

        public Optional<Resource> getResource(Identifier location) {
            throw Unimplemented.forMember("net/minecraft/server/packs/resources/ResourceManager$Empty.getResource:(Lnet/minecraft/resources/Identifier;)Ljava/util/Optional;");
        }

        public List<Resource> getResourceStack(Identifier location) {
            throw Unimplemented.forMember("net/minecraft/server/packs/resources/ResourceManager$Empty.getResourceStack:(Lnet/minecraft/resources/Identifier;)Ljava/util/List;");
        }

        public Map<Identifier, Resource> listResources(String directory, Predicate<Identifier> filter) {
            throw Unimplemented.forMember("net/minecraft/server/packs/resources/ResourceManager$Empty.listResources:(Ljava/lang/String;Ljava/util/function/Predicate;)Ljava/util/Map;");
        }

        public Map<Identifier, List<Resource>> listResourceStacks(String directory, Predicate<Identifier> filter) {
            throw Unimplemented.forMember("net/minecraft/server/packs/resources/ResourceManager$Empty.listResourceStacks:(Ljava/lang/String;Ljava/util/function/Predicate;)Ljava/util/Map;");
        }

        public Stream<PackResources> listPacks() {
            throw Unimplemented.forMember("net/minecraft/server/packs/resources/ResourceManager$Empty.listPacks:()Ljava/util/stream/Stream;");
        }
    }
}
