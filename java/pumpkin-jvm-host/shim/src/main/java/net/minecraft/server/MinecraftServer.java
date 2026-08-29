package net.minecraft.server;

import com.mojang.datafixers.DataFixer;
import java.io.IOException;
import java.net.Proxy;
import java.util.Optional;
import java.util.UUID;
import net.minecraft.SystemReport;
import net.minecraft.commands.CommandSource;
import net.minecraft.core.LayeredRegistryAccess;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.status.ServerStatus;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.progress.LevelLoadListener;
import net.minecraft.server.network.ServerConnectionListener;
import net.minecraft.server.notifications.NotificationManager;
import net.minecraft.server.packs.repository.PackRepository;
import net.minecraft.server.packs.resources.CloseableResourceManager;
import net.minecraft.server.permissions.LevelBasedPermissionSet;
import net.minecraft.server.permissions.PermissionSet;
import net.minecraft.server.players.NameAndId;
import net.minecraft.util.debugchart.SampleLogger;
import net.minecraft.util.thread.ReentrantBlockableEventLoop;
import net.minecraft.world.clock.ServerClockManager;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.FuelValues;
import net.minecraft.world.level.chunk.storage.ChunkIOErrorReporter;
import net.minecraft.world.level.chunk.storage.RegionStorageInfo;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.level.storage.LevelData;
import net.minecraft.world.level.storage.LevelStorageSource;
import dev.pumpkin.shim.Unimplemented;

public abstract class MinecraftServer extends ReentrantBlockableEventLoop<TickTask> implements CommandSource, ServerInfo, ChunkIOErrorReporter {

    public MinecraftServer(Thread serverThread, LevelStorageSource.LevelStorageAccess storageSource, PackRepository packRepository, WorldStem worldStem, Optional<GameRules> gameRules, Proxy proxy, DataFixer fixerUpper, Services services, LevelLoadListener levelLoadListener, boolean propagatesCrashes, NotificationManager notificationManager) {
        throw Unimplemented.forMember("net/minecraft/server/MinecraftServer.<init>:(Ljava/lang/Thread;Lnet/minecraft/world/level/storage/LevelStorageSource$LevelStorageAccess;Lnet/minecraft/server/packs/repository/PackRepository;Lnet/minecraft/server/WorldStem;Ljava/util/Optional;Ljava/net/Proxy;Lcom/mojang/datafixers/DataFixer;Lnet/minecraft/server/Services;Lnet/minecraft/server/level/progress/LevelLoadListener;ZLnet/minecraft/server/notifications/NotificationManager;)V");
    }

    protected abstract boolean initServer() throws IOException;

    public boolean isHardcore() {
        throw Unimplemented.forMember("net/minecraft/server/MinecraftServer.isHardcore:()Z");
    }

    public abstract LevelBasedPermissionSet operatorUserPermissions();

    public abstract PermissionSet getFunctionCompilationPermissions();

    public abstract boolean shouldRconBroadcast();

    public void close() {
        throw Unimplemented.forMember("net/minecraft/server/MinecraftServer.close:()V");
    }

    protected void waitForTasks() {
        throw Unimplemented.forMember("net/minecraft/server/MinecraftServer.waitForTasks:()V");
    }

    public TickTask wrapRunnable(Runnable runnable) {
        throw Unimplemented.forMember("net/minecraft/server/MinecraftServer.wrapRunnable:(Ljava/lang/Runnable;)Lnet/minecraft/server/TickTask;");
    }

    protected boolean shouldRun(TickTask task) {
        throw Unimplemented.forMember("net/minecraft/server/MinecraftServer.shouldRun:(Lnet/minecraft/server/TickTask;)Z");
    }

    protected boolean pollTask() {
        throw Unimplemented.forMember("net/minecraft/server/MinecraftServer.pollTask:()Z");
    }

    protected abstract SampleLogger getTickTimeLogger();

    public abstract boolean isTickTimeLoggingEnabled();

    public ServerLevel getLevel(ResourceKey<Level> dimension) {
        throw Unimplemented.forMember("net/minecraft/server/MinecraftServer.getLevel:(Lnet/minecraft/resources/ResourceKey;)Lnet/minecraft/server/level/ServerLevel;");
    }

    public String getServerVersion() {
        throw Unimplemented.forMember("net/minecraft/server/MinecraftServer.getServerVersion:()Ljava/lang/String;");
    }

    public int getPlayerCount() {
        throw Unimplemented.forMember("net/minecraft/server/MinecraftServer.getPlayerCount:()I");
    }

    public ServerClockManager clockManager() {
        throw Unimplemented.forMember("net/minecraft/server/MinecraftServer.clockManager:()Lnet/minecraft/world/clock/ServerClockManager;");
    }

    public abstract SystemReport fillServerSystemReport(final SystemReport systemReport);

    public void sendSystemMessage(Component message) {
        throw Unimplemented.forMember("net/minecraft/server/MinecraftServer.sendSystemMessage:(Lnet/minecraft/network/chat/Component;)V");
    }

    public void setDifficultyLocked(boolean locked) {
        throw Unimplemented.forMember("net/minecraft/server/MinecraftServer.setDifficultyLocked:(Z)V");
    }

    public abstract boolean isDedicatedServer();

    public abstract int getRateLimitPacketsPerSecond();

    public abstract int getCommandSpamThresholdSeconds();

    public abstract int getChatSpamThresholdSeconds();

    public abstract boolean useNativeTransport();

    public String getMotd() {
        throw Unimplemented.forMember("net/minecraft/server/MinecraftServer.getMotd:()Ljava/lang/String;");
    }

    public boolean isStopped() {
        throw Unimplemented.forMember("net/minecraft/server/MinecraftServer.isStopped:()Z");
    }

    public abstract boolean isPublished();

    public ServerConnectionListener getConnection() {
        throw Unimplemented.forMember("net/minecraft/server/MinecraftServer.getConnection:()Lnet/minecraft/server/network/ServerConnectionListener;");
    }

    public ServerStatus getStatus() {
        throw Unimplemented.forMember("net/minecraft/server/MinecraftServer.getStatus:()Lnet/minecraft/network/protocol/status/ServerStatus;");
    }

    public boolean scheduleExecutables() {
        throw Unimplemented.forMember("net/minecraft/server/MinecraftServer.scheduleExecutables:()Z");
    }

    public void executeIfPossible(Runnable command) {
        throw Unimplemented.forMember("net/minecraft/server/MinecraftServer.executeIfPossible:(Ljava/lang/Runnable;)V");
    }

    public Thread getRunningThread() {
        throw Unimplemented.forMember("net/minecraft/server/MinecraftServer.getRunningThread:()Ljava/lang/Thread;");
    }

    public void setRespawnData(LevelData.RespawnData respawnData) {
        throw Unimplemented.forMember("net/minecraft/server/MinecraftServer.setRespawnData:(Lnet/minecraft/world/level/storage/LevelData$RespawnData;)V");
    }

    public LevelData.RespawnData getRespawnData() {
        throw Unimplemented.forMember("net/minecraft/server/MinecraftServer.getRespawnData:()Lnet/minecraft/world/level/storage/LevelData$RespawnData;");
    }

    public boolean acceptsSuccess() {
        throw Unimplemented.forMember("net/minecraft/server/MinecraftServer.acceptsSuccess:()Z");
    }

    public boolean acceptsFailure() {
        throw Unimplemented.forMember("net/minecraft/server/MinecraftServer.acceptsFailure:()Z");
    }

    public abstract boolean shouldInformAdmins();

    public ServerScoreboard getScoreboard() {
        throw Unimplemented.forMember("net/minecraft/server/MinecraftServer.getScoreboard:()Lnet/minecraft/server/ServerScoreboard;");
    }

    public ServerTickRateManager tickRateManager() {
        throw Unimplemented.forMember("net/minecraft/server/MinecraftServer.tickRateManager:()Lnet/minecraft/server/ServerTickRateManager;");
    }

    public abstract boolean isSingleplayerOwner(NameAndId nameAndId);

    public RegistryAccess.Frozen registryAccess() {
        throw Unimplemented.forMember("net/minecraft/server/MinecraftServer.registryAccess:()Lnet/minecraft/core/RegistryAccess$Frozen;");
    }

    public LayeredRegistryAccess<RegistryLayer> registries() {
        throw Unimplemented.forMember("net/minecraft/server/MinecraftServer.registries:()Lnet/minecraft/core/LayeredRegistryAccess;");
    }

    public <T> void onGameRuleChanged(GameRule<T> rule, T value) {
        throw Unimplemented.forMember("net/minecraft/server/MinecraftServer.onGameRuleChanged:(Lnet/minecraft/world/level/gamerules/GameRule;Ljava/lang/Object;)V");
    }

    public void reportChunkLoadFailure(Throwable throwable, RegionStorageInfo storageInfo, ChunkPos pos) {
        throw Unimplemented.forMember("net/minecraft/server/MinecraftServer.reportChunkLoadFailure:(Ljava/lang/Throwable;Lnet/minecraft/world/level/chunk/storage/RegionStorageInfo;Lnet/minecraft/world/level/ChunkPos;)V");
    }

    public void reportChunkSaveFailure(Throwable throwable, RegionStorageInfo storageInfo, ChunkPos pos) {
        throw Unimplemented.forMember("net/minecraft/server/MinecraftServer.reportChunkSaveFailure:(Ljava/lang/Throwable;Lnet/minecraft/world/level/chunk/storage/RegionStorageInfo;Lnet/minecraft/world/level/ChunkPos;)V");
    }

    public PotionBrewing potionBrewing() {
        throw Unimplemented.forMember("net/minecraft/server/MinecraftServer.potionBrewing:()Lnet/minecraft/world/item/alchemy/PotionBrewing;");
    }

    public FuelValues fuelValues() {
        throw Unimplemented.forMember("net/minecraft/server/MinecraftServer.fuelValues:()Lnet/minecraft/world/level/block/entity/FuelValues;");
    }

    public ServerLinks serverLinks() {
        throw Unimplemented.forMember("net/minecraft/server/MinecraftServer.serverLinks:()Lnet/minecraft/server/ServerLinks;");
    }

    public enum MultiplayerScope {

        OFF, LAN;

        public Component getDisplayName() {
            throw Unimplemented.forMember("net/minecraft/server/MinecraftServer$MultiplayerScope.getDisplayName:()Lnet/minecraft/network/chat/Component;");
        }
    }

    public record ReloadableResources(CloseableResourceManager resourceManager, ReloadableServerResources managers) implements AutoCloseable {

        public void close() {
            throw Unimplemented.forMember("net/minecraft/server/MinecraftServer$ReloadableResources.close:()V");
        }
    }

    public record ServerResourcePackInfo(UUID id, String url, String hash, boolean isRequired, Component prompt) {
    }

    private static class TimeProfiler {

        private TimeProfiler(long startNanos, int startTick) {
            throw Unimplemented.forMember("net/minecraft/server/MinecraftServer$TimeProfiler.<init>:(JI)V");
        }

        protected TimeProfiler() {
        }
    }

    protected MinecraftServer() {
    }
}
