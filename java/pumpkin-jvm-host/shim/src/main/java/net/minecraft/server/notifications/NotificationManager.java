package net.minecraft.server.notifications;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.IpBanListEntry;
import net.minecraft.server.players.NameAndId;
import net.minecraft.server.players.ServerOpListEntry;
import net.minecraft.server.players.UserBanListEntry;
import net.minecraft.world.level.gamerules.GameRule;
import dev.pumpkin.shim.Unimplemented;

public class NotificationManager implements NotificationService {

    public void playerJoined(ServerPlayer player) {
        throw Unimplemented.forMember("net/minecraft/server/notifications/NotificationManager.playerJoined:(Lnet/minecraft/server/level/ServerPlayer;)V");
    }

    public void playerLeft(ServerPlayer player) {
        throw Unimplemented.forMember("net/minecraft/server/notifications/NotificationManager.playerLeft:(Lnet/minecraft/server/level/ServerPlayer;)V");
    }

    public void serverStarted() {
        throw Unimplemented.forMember("net/minecraft/server/notifications/NotificationManager.serverStarted:()V");
    }

    public void serverShuttingDown() {
        throw Unimplemented.forMember("net/minecraft/server/notifications/NotificationManager.serverShuttingDown:()V");
    }

    public void serverSaveStarted() {
        throw Unimplemented.forMember("net/minecraft/server/notifications/NotificationManager.serverSaveStarted:()V");
    }

    public void serverSaveCompleted() {
        throw Unimplemented.forMember("net/minecraft/server/notifications/NotificationManager.serverSaveCompleted:()V");
    }

    public void serverActivityOccured() {
        throw Unimplemented.forMember("net/minecraft/server/notifications/NotificationManager.serverActivityOccured:()V");
    }

    public void playerOped(ServerOpListEntry operator) {
        throw Unimplemented.forMember("net/minecraft/server/notifications/NotificationManager.playerOped:(Lnet/minecraft/server/players/ServerOpListEntry;)V");
    }

    public void playerDeoped(ServerOpListEntry operator) {
        throw Unimplemented.forMember("net/minecraft/server/notifications/NotificationManager.playerDeoped:(Lnet/minecraft/server/players/ServerOpListEntry;)V");
    }

    public void playerAddedToAllowlist(NameAndId player) {
        throw Unimplemented.forMember("net/minecraft/server/notifications/NotificationManager.playerAddedToAllowlist:(Lnet/minecraft/server/players/NameAndId;)V");
    }

    public void playerRemovedFromAllowlist(NameAndId player) {
        throw Unimplemented.forMember("net/minecraft/server/notifications/NotificationManager.playerRemovedFromAllowlist:(Lnet/minecraft/server/players/NameAndId;)V");
    }

    public void ipBanned(IpBanListEntry ban) {
        throw Unimplemented.forMember("net/minecraft/server/notifications/NotificationManager.ipBanned:(Lnet/minecraft/server/players/IpBanListEntry;)V");
    }

    public void ipUnbanned(String ip) {
        throw Unimplemented.forMember("net/minecraft/server/notifications/NotificationManager.ipUnbanned:(Ljava/lang/String;)V");
    }

    public void playerBanned(UserBanListEntry ban) {
        throw Unimplemented.forMember("net/minecraft/server/notifications/NotificationManager.playerBanned:(Lnet/minecraft/server/players/UserBanListEntry;)V");
    }

    public void playerUnbanned(NameAndId player) {
        throw Unimplemented.forMember("net/minecraft/server/notifications/NotificationManager.playerUnbanned:(Lnet/minecraft/server/players/NameAndId;)V");
    }

    public <T> void onGameRuleChanged(GameRule<T> gameRule, T value) {
        throw Unimplemented.forMember("net/minecraft/server/notifications/NotificationManager.onGameRuleChanged:(Lnet/minecraft/world/level/gamerules/GameRule;Ljava/lang/Object;)V");
    }

    public void statusHeartbeat() {
        throw Unimplemented.forMember("net/minecraft/server/notifications/NotificationManager.statusHeartbeat:()V");
    }

    public NotificationManager() {
    }
}
