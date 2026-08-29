package net.minecraft.server.packs.resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import net.minecraft.server.packs.PackResources;
import dev.pumpkin.shim.Unimplemented;

public class Resource {

    public Resource(PackResources source, IoSupplier<InputStream> streamSupplier, IoSupplier<ResourceMetadata> metadataSupplier) {
        throw Unimplemented.forMember("net/minecraft/server/packs/resources/Resource.<init>:(Lnet/minecraft/server/packs/PackResources;Lnet/minecraft/server/packs/resources/IoSupplier;Lnet/minecraft/server/packs/resources/IoSupplier;)V");
    }

    public Resource(PackResources source, IoSupplier<InputStream> streamSupplier) {
        throw Unimplemented.forMember("net/minecraft/server/packs/resources/Resource.<init>:(Lnet/minecraft/server/packs/PackResources;Lnet/minecraft/server/packs/resources/IoSupplier;)V");
    }

    public BufferedReader openAsReader() throws IOException {
        throw Unimplemented.forMember("net/minecraft/server/packs/resources/Resource.openAsReader:()Ljava/io/BufferedReader;");
    }

    protected Resource() {
    }
}
