package net.minecraft.server.players;

import java.util.List;
import java.util.UUID;
import net.minecraft.core.LayeredRegistryAccess;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.RegistryLayer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.notifications.NotificationService;
import net.minecraft.world.level.storage.PlayerDataStorage;
import net.neoforged.neoforge.common.extensions.IPlayerListExtension;
import dev.pumpkin.shim.Unimplemented;

public abstract class PlayerList implements IPlayerListExtension {

    public PlayerList(MinecraftServer server, LayeredRegistryAccess<RegistryLayer> registries, PlayerDataStorage playerIo, NotificationService notificationService) {
    }

    public void remove(ServerPlayer player) {
        throw Unimplemented.forMember("net/minecraft/server/players/PlayerList.remove:(Lnet/minecraft/server/level/ServerPlayer;)V");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/server/players/PlayerList.tick:()V");
    }

    public boolean isOp(NameAndId nameAndId) {
        throw Unimplemented.forMember("net/minecraft/server/players/PlayerList.isOp:(Lnet/minecraft/server/players/NameAndId;)Z");
    }

    public int getPlayerCount() {
        throw Unimplemented.forMember("net/minecraft/server/players/PlayerList.getPlayerCount:()I");
    }

    public int getMaxPlayers() {
        throw Unimplemented.forMember("net/minecraft/server/players/PlayerList.getMaxPlayers:()I");
    }

    public int getViewDistance() {
        throw Unimplemented.forMember("net/minecraft/server/players/PlayerList.getViewDistance:()I");
    }

    public MinecraftServer getServer() {
        throw Unimplemented.forMember("net/minecraft/server/players/PlayerList.getServer:()Lnet/minecraft/server/MinecraftServer;");
    }

    public List<ServerPlayer> getPlayers() {
        throw Unimplemented.forMember("net/minecraft/server/players/PlayerList.getPlayers:()Ljava/util/List;");
    }

    public ServerPlayer getPlayer(UUID uuid) {
        throw Unimplemented.forMember("net/minecraft/server/players/PlayerList.getPlayer:(Ljava/util/UUID;)Lnet/minecraft/server/level/ServerPlayer;");
    }

    public ServerPlayer getPlayer(String playerName) {
        throw Unimplemented.forMember("net/minecraft/server/players/PlayerList.getPlayer:(Ljava/lang/String;)Lnet/minecraft/server/level/ServerPlayer;");
    }

    public PlayerList() {
    }
}
