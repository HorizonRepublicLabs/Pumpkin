package net.minecraft.server.packs.resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import net.minecraft.server.packs.PackResources;
import dev.pumpkin.shim.Unimplemented;

public class Resource {

    public Resource(PackResources source, IoSupplier<InputStream> streamSupplier, IoSupplier<ResourceMetadata> metadataSupplier) {
    }

    public Resource(PackResources source, IoSupplier<InputStream> streamSupplier) {
    }

    public BufferedReader openAsReader() throws IOException {
        throw Unimplemented.forMember("net/minecraft/server/packs/resources/Resource.openAsReader:()Ljava/io/BufferedReader;");
    }

    public Resource() {
    }
}
