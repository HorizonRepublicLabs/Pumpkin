package dev.pumpkin.bridge;

import java.io.IOException;
import java.util.Optional;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.ServerTickRateManager;
import dev.pumpkin.shim.Unimplemented;

/**
 * The stand-in {@link MinecraftServer} tick events carry.
 *
 * <p>Mods reach through a tick event for exactly one thing this host has seen so far:
 * {@code getServer().tickRateManager().runsNormally()}. The tick rate manager is real
 * (Pumpkin has no freeze or sprint, so the rate is always normal); everything else a
 * real server would answer stays an honest refusal until a mod asks for it.
 */
public final class PumpkinMinecraftServer extends MinecraftServer {
    private static PumpkinMinecraftServer instance;
    private final ServerTickRateManager pumpkinTickRate = new ServerTickRateManager();

    public static synchronized PumpkinMinecraftServer pumpkinInstance() {
        if (instance == null) {
            instance = new PumpkinMinecraftServer();
        }
        return instance;
    }

    private PumpkinMinecraftServer() {
        super(null, null, null, null, Optional.empty(), null, null, null, null, false, null);
    }

    @Override
    public ServerTickRateManager tickRateManager() {
        return pumpkinTickRate;
    }

    @Override
    public net.minecraft.server.players.PlayerList getPlayerList() {
        // No modded client speaks to this host: the bridge serves vanilla-protocol
        // players on the Rust side, and none of them can decode a mod's custom
        // payloads. An empty player list is the truthful answer, and mod sync
        // packets fan out to nobody.
        return pumpkinPlayers;
    }

    private final net.minecraft.server.players.PlayerList pumpkinPlayers =
            new net.minecraft.server.players.PlayerList() {
                @Override
                public int getViewDistance() {
                    return 10;
                }

                @Override
                public java.util.List<net.minecraft.server.level.ServerPlayer> getPlayers() {
                    return java.util.List.of();
                }
            };

    @Override
    public Iterable<net.minecraft.server.level.ServerLevel> getAllLevels() {
        // The one dimension this host models: the overworld stand-in every bridge
        // call answers through.
        return java.util.List.of(PumpkinInteractions.pumpkinLevel());
    }

    @Override
    protected boolean initServer() throws IOException {
        throw Unimplemented.forMember("net/minecraft/server/MinecraftServer.initServer:()Z");
    }

    @Override
    public net.minecraft.server.permissions.LevelBasedPermissionSet operatorUserPermissions() {
        throw Unimplemented.forMember(
                "net/minecraft/server/MinecraftServer.operatorUserPermissions");
    }

    @Override
    public net.minecraft.server.permissions.PermissionSet getFunctionCompilationPermissions() {
        throw Unimplemented.forMember(
                "net/minecraft/server/MinecraftServer.getFunctionCompilationPermissions");
    }

    @Override
    public boolean shouldRconBroadcast() {
        throw Unimplemented.forMember("net/minecraft/server/MinecraftServer.shouldRconBroadcast:()Z");
    }

    @Override
    protected net.minecraft.util.debugchart.SampleLogger getTickTimeLogger() {
        throw Unimplemented.forMember("net/minecraft/server/MinecraftServer.getTickTimeLogger");
    }

    @Override
    public boolean isTickTimeLoggingEnabled() {
        throw Unimplemented.forMember(
                "net/minecraft/server/MinecraftServer.isTickTimeLoggingEnabled:()Z");
    }

    @Override
    public net.minecraft.SystemReport fillServerSystemReport(net.minecraft.SystemReport systemReport) {
        throw Unimplemented.forMember("net/minecraft/server/MinecraftServer.fillServerSystemReport");
    }

    @Override
    public boolean isDedicatedServer() {
        // Pumpkin is a server; there is no integrated-client variant of this host.
        return true;
    }

    @Override
    public int getRateLimitPacketsPerSecond() {
        throw Unimplemented.forMember(
                "net/minecraft/server/MinecraftServer.getRateLimitPacketsPerSecond:()I");
    }

    @Override
    public int getCommandSpamThresholdSeconds() {
        throw Unimplemented.forMember(
                "net/minecraft/server/MinecraftServer.getCommandSpamThresholdSeconds:()I");
    }

    @Override
    public int getChatSpamThresholdSeconds() {
        throw Unimplemented.forMember(
                "net/minecraft/server/MinecraftServer.getChatSpamThresholdSeconds:()I");
    }

    @Override
    public boolean useNativeTransport() {
        throw Unimplemented.forMember("net/minecraft/server/MinecraftServer.useNativeTransport:()Z");
    }

    @Override
    public boolean isPublished() {
        throw Unimplemented.forMember("net/minecraft/server/MinecraftServer.isPublished:()Z");
    }

    @Override
    public boolean shouldInformAdmins() {
        throw Unimplemented.forMember("net/minecraft/server/MinecraftServer.shouldInformAdmins:()Z");
    }

    @Override
    public boolean isSingleplayerOwner(net.minecraft.server.players.NameAndId nameAndId) {
        throw Unimplemented.forMember("net/minecraft/server/MinecraftServer.isSingleplayerOwner");
    }

    @Override
    public String getMotd() {
        throw Unimplemented.forMember("net/minecraft/server/ServerInfo.getMotd:()Ljava/lang/String;");
    }

    @Override
    public String getServerVersion() {
        throw Unimplemented.forMember(
                "net/minecraft/server/ServerInfo.getServerVersion:()Ljava/lang/String;");
    }

    @Override
    public int getPlayerCount() {
        throw Unimplemented.forMember("net/minecraft/server/ServerInfo.getPlayerCount:()I");
    }

    @Override
    public int getMaxPlayers() {
        throw Unimplemented.forMember("net/minecraft/server/ServerInfo.getMaxPlayers:()I");
    }
}
