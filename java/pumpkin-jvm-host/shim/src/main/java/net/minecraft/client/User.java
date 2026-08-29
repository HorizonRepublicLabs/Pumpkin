package net.minecraft.client;

import java.util.Optional;
import java.util.UUID;
import dev.pumpkin.shim.Unimplemented;

public class User {

    public User(String name, UUID uuid, String accessToken, Optional<String> xuid, Optional<String> clientId) {
    }

    public String getName() {
        throw Unimplemented.forMember("net/minecraft/client/User.getName:()Ljava/lang/String;");
    }

    public User() {
    }
}
