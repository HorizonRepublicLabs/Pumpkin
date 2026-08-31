package net.neoforged.neoforge.client.gui.modlist;

import java.io.InputStream;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.IoSupplier;
import net.minecraft.server.packs.resources.ResourceManager;
import dev.pumpkin.shim.Unimplemented;

public interface ImageResource {

    IoSupplier<InputStream> get(ResourceManager resourceManager);

    static ImageResource packAsset(Identifier id) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/gui/modlist/ImageResource.packAsset:(Lnet/minecraft/resources/Identifier;)Lnet/neoforged/neoforge/client/gui/modlist/ImageResource;");
    }

    record PackRoot(String packId, String path) implements ImageResource {

        public IoSupplier<InputStream> get(ResourceManager resourceManager) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/gui/modlist/ImageResource$PackRoot.get:(Lnet/minecraft/server/packs/resources/ResourceManager;)Lnet/minecraft/server/packs/resources/IoSupplier;");
        }

        public String toString() {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/gui/modlist/ImageResource$PackRoot.toString:()Ljava/lang/String;");
        }
    }

    record PackAsset(Identifier path) implements ImageResource {

        public IoSupplier<InputStream> get(ResourceManager resourceManager) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/gui/modlist/ImageResource$PackAsset.get:(Lnet/minecraft/server/packs/resources/ResourceManager;)Lnet/minecraft/server/packs/resources/IoSupplier;");
        }

        public String toString() {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/gui/modlist/ImageResource$PackAsset.toString:()Ljava/lang/String;");
        }
    }
}
