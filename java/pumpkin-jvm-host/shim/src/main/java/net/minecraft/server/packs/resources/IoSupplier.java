package net.minecraft.server.packs.resources;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import dev.pumpkin.shim.Unimplemented;

public interface IoSupplier<T> {

    static IoSupplier<InputStream> create(Path path) {
        throw Unimplemented.forMember("net/minecraft/server/packs/resources/IoSupplier.create:(Ljava/nio/file/Path;)Lnet/minecraft/server/packs/resources/IoSupplier;");
    }

    static IoSupplier<InputStream> create(ZipFile zipFile, ZipEntry entry) {
        throw Unimplemented.forMember("net/minecraft/server/packs/resources/IoSupplier.create:(Ljava/util/zip/ZipFile;Ljava/util/zip/ZipEntry;)Lnet/minecraft/server/packs/resources/IoSupplier;");
    }

    T get() throws IOException;
}
