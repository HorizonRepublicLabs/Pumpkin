package net.minecraft.server.level;

import net.minecraft.world.level.GameType;
import dev.pumpkin.shim.Unimplemented;

public class ServerPlayerGameMode {

    public ServerPlayerGameMode(ServerPlayer player) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayerGameMode.<init>:(Lnet/minecraft/server/level/ServerPlayer;)V");
    }

    public GameType getGameModeForPlayer() {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayerGameMode.getGameModeForPlayer:()Lnet/minecraft/world/level/GameType;");
    }

    public boolean isCreative() {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayerGameMode.isCreative:()Z");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerPlayerGameMode.tick:()V");
    }

    public ServerPlayerGameMode() {
    }
}
