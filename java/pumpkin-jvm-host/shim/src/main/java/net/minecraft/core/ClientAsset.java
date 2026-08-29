package net.minecraft.core;

import net.minecraft.resources.Identifier;
import dev.pumpkin.shim.Unimplemented;

public interface ClientAsset {

    Identifier id();

    record DownloadedTexture(Identifier texturePath, String url) implements ClientAsset.Texture {

        public Identifier id() {
            throw Unimplemented.forMember("net/minecraft/core/ClientAsset$DownloadedTexture.id:()Lnet/minecraft/resources/Identifier;");
        }
    }

    record ResourceTexture(Identifier id, Identifier texturePath) implements ClientAsset.Texture {

        public ResourceTexture(Identifier texture) {
            this((Identifier) null, (Identifier) null);
            throw Unimplemented.forMember("net/minecraft/core/ClientAsset$ResourceTexture.<init>:(Lnet/minecraft/resources/Identifier;)V");
        }
    }

    interface Texture extends ClientAsset {

        Identifier texturePath();
    }
}
