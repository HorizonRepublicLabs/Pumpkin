package net.minecraft.server.players;

import com.mojang.authlib.GameProfile;
import java.util.UUID;
import dev.pumpkin.shim.Unimplemented;

public record NameAndId(UUID id, String name) {

    public NameAndId(GameProfile profile) {
        this((UUID) null, (String) null);
        throw Unimplemented.forMember("net/minecraft/server/players/NameAndId.<init>:(Lcom/mojang/authlib/GameProfile;)V");
    }
}
