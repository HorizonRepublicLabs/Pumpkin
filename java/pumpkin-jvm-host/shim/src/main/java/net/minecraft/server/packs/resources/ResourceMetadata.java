package net.minecraft.server.packs.resources;

import java.util.Map;
import java.util.Optional;
import net.minecraft.server.packs.metadata.MetadataSectionType;
import dev.pumpkin.shim.Unimplemented;

public interface ResourceMetadata {

    <T> Optional<T> getSection(MetadataSectionType<T> serializer);

    class MapBased implements ResourceMetadata {

        private MapBased(Map<MetadataSectionType<?>, ?> values) {
            throw Unimplemented.forMember("net/minecraft/server/packs/resources/ResourceMetadata$MapBased.<init>:(Ljava/util/Map;)V");
        }

        public <T> Optional<T> getSection(MetadataSectionType<T> serializer) {
            throw Unimplemented.forMember("net/minecraft/server/packs/resources/ResourceMetadata$MapBased.getSection:(Lnet/minecraft/server/packs/metadata/MetadataSectionType;)Ljava/util/Optional;");
        }

        protected MapBased() {
        }
    }
}
