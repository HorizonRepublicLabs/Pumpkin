package net.minecraft.world.entity.player;

import java.util.Optional;
import net.minecraft.core.ClientAsset;
import dev.pumpkin.shim.Unimplemented;

public record PlayerSkin(ClientAsset.Texture body, ClientAsset.Texture cape, ClientAsset.Texture elytra, PlayerModelType model, boolean secure) {

    public PlayerSkin with(PlayerSkin.Patch patch) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/PlayerSkin.with:(Lnet/minecraft/world/entity/player/PlayerSkin$Patch;)Lnet/minecraft/world/entity/player/PlayerSkin;");
    }

    public record Patch(Optional<ClientAsset.ResourceTexture> body, Optional<ClientAsset.ResourceTexture> cape, Optional<ClientAsset.ResourceTexture> elytra, Optional<PlayerModelType> model) {

        public static PlayerSkin.Patch create(Optional<ClientAsset.ResourceTexture> texture, Optional<ClientAsset.ResourceTexture> capeTexture, Optional<ClientAsset.ResourceTexture> elytraTexture, Optional<PlayerModelType> model) {
            throw Unimplemented.forMember("net/minecraft/world/entity/player/PlayerSkin$Patch.create:(Ljava/util/Optional;Ljava/util/Optional;Ljava/util/Optional;Ljava/util/Optional;)Lnet/minecraft/world/entity/player/PlayerSkin$Patch;");
        }
    }
}
