package net.minecraft.server.packs;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
import java.util.function.BiConsumer;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.metadata.MetadataSectionType;
import net.minecraft.server.packs.resources.IoSupplier;
import net.neoforged.neoforge.common.extensions.IPackResourcesExtension;

public interface PackResources extends AutoCloseable, IPackResourcesExtension {

    IoSupplier<InputStream> getRootResource(String... path);

    IoSupplier<InputStream> getResource(PackType type, Identifier location);

    void listResources(PackType type, String namespace, String directory, PackResources.ResourceOutput output);

    Set<String> getNamespaces(PackType type);

    <T> T getMetadataSection(MetadataSectionType<T> metadataSerializer) throws IOException;

    PackLocationInfo location();

    void close();

    interface ResourceOutput extends BiConsumer<Identifier, IoSupplier<InputStream>> {
    }
}
