package net.minecraft;

import java.util.Date;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.pack.PackFormat;
import net.minecraft.world.level.storage.DataVersion;
import dev.pumpkin.shim.Unimplemented;

public interface WorldVersion {

    DataVersion dataVersion();

    String id();

    String name();

    int protocolVersion();

    PackFormat packVersion(PackType packType);

    Date buildTime();

    boolean stable();

    record Simple(String id, String name, DataVersion dataVersion, int protocolVersion, PackFormat resourcePackVersion, PackFormat datapackVersion, Date buildTime, boolean stable) implements WorldVersion {

        public PackFormat packVersion(PackType packType) {
            throw Unimplemented.forMember("net/minecraft/WorldVersion$Simple.packVersion:(Lnet/minecraft/server/packs/PackType;)Lnet/minecraft/server/packs/metadata/pack/PackFormat;");
        }
    }
}
