package net.minecraft.server.players;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import java.util.Optional;
import java.util.UUID;
import dev.pumpkin.shim.Unimplemented;

public interface ProfileResolver {

    Optional<GameProfile> fetchByName(String name);

    Optional<GameProfile> fetchById(UUID id);

    class Cached implements ProfileResolver {

        public Cached(MinecraftSessionService sessionService, UserNameToIdResolver nameToIdCache) {
        }

        public Optional<GameProfile> fetchByName(String name) {
            throw Unimplemented.forMember("net/minecraft/server/players/ProfileResolver$Cached.fetchByName:(Ljava/lang/String;)Ljava/util/Optional;");
        }

        public Optional<GameProfile> fetchById(UUID id) {
            throw Unimplemented.forMember("net/minecraft/server/players/ProfileResolver$Cached.fetchById:(Ljava/util/UUID;)Ljava/util/Optional;");
        }

        protected Cached() {
        }
    }
}
