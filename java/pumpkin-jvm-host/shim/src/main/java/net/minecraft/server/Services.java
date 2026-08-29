package net.minecraft.server;

import com.mojang.authlib.GameProfileRepository;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.yggdrasil.ServicesKeySet;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import java.io.File;
import net.minecraft.server.players.ProfileResolver;
import net.minecraft.server.players.UserNameToIdResolver;
import dev.pumpkin.shim.Unimplemented;

public record Services(MinecraftSessionService sessionService, ServicesKeySet servicesKeySet, GameProfileRepository profileRepository, UserNameToIdResolver nameToIdCache, ProfileResolver profileResolver) {

    public static Services create(YggdrasilAuthenticationService serviceAccess, File nameCacheDir) {
        throw Unimplemented.forMember("net/minecraft/server/Services.create:(Lcom/mojang/authlib/yggdrasil/YggdrasilAuthenticationService;Ljava/io/File;)Lnet/minecraft/server/Services;");
    }
}
