package net.minecraft.client;

import java.util.Optional;
import java.util.UUID;
import dev.pumpkin.shim.Unimplemented;

public class User {

    public User(String name, UUID uuid, String accessToken, Optional<String> xuid, Optional<String> clientId) {
        throw Unimplemented.forMember("net/minecraft/client/User.<init>:(Ljava/lang/String;Ljava/util/UUID;Ljava/lang/String;Ljava/util/Optional;Ljava/util/Optional;)V");
    }

    public String getName() {
        throw Unimplemented.forMember("net/minecraft/client/User.getName:()Ljava/lang/String;");
    }

    public User() {
    }
}
