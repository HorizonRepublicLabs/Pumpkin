package net.minecraft.server.level;

import com.mojang.datafixers.DataFixer;
import it.unimi.dsi.fastutil.longs.LongSet;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;
import net.minecraft.core.SectionPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.util.thread.BlockableEventLoop;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.chunk.ChunkSource;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.chunk.LightChunk;
import net.minecraft.world.level.chunk.status.ChunkStatus;
import net.minecraft.world.level.entity.ChunkStatusUpdateListener;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import net.minecraft.world.level.storage.LevelStorageSource;
import net.minecraft.world.level.storage.SavedDataStorage;
import net.neoforged.neoforge.common.extensions.IServerChunkCacheExtension;
import dev.pumpkin.shim.Unimplemented;

public class ServerChunkCache extends ChunkSource implements IServerChunkCacheExtension {

    public final ChunkMap chunkMap = null;

    public ServerChunkCache(ServerLevel level, LevelStorageSource.LevelStorageAccess levelStorage, DataFixer fixerUpper, StructureTemplateManager structureTemplateManager, Executor executor, ChunkGenerator generator, int viewDistance, int simulationDistance, boolean syncWrites, ChunkStatusUpdateListener chunkStatusListener, Supplier<SavedDataStorage> overworldDataStorage) {
    }

    public ThreadedLevelLightEngine getLightEngine() {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerChunkCache.getLightEngine:()Lnet/minecraft/server/level/ThreadedLevelLightEngine;");
    }

    public ChunkAccess getChunk(int x, int z, ChunkStatus targetStatus, boolean loadOrGenerate) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerChunkCache.getChunk:(IILnet/minecraft/world/level/chunk/status/ChunkStatus;Z)Lnet/minecraft/world/level/chunk/ChunkAccess;");
    }

    public LevelChunk getChunkNow(int x, int z) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerChunkCache.getChunkNow:(II)Lnet/minecraft/world/level/chunk/LevelChunk;");
    }

    public CompletableFuture<ChunkResult<ChunkAccess>> getChunkFuture(int x, int z, ChunkStatus targetStatus, boolean loadOrGenerate) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerChunkCache.getChunkFuture:(IILnet/minecraft/world/level/chunk/status/ChunkStatus;Z)Ljava/util/concurrent/CompletableFuture;");
    }

    public boolean hasChunk(int x, int z) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerChunkCache.hasChunk:(II)Z");
    }

    public LightChunk getChunkForLighting(int x, int z) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerChunkCache.getChunkForLighting:(II)Lnet/minecraft/world/level/chunk/LightChunk;");
    }

    public Level getLevel() {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerChunkCache.getLevel:()Lnet/minecraft/world/level/Level;");
    }

    public void close() throws IOException {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerChunkCache.close:()V");
    }

    public void tick(BooleanSupplier haveTime, boolean tickChunks) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerChunkCache.tick:(Ljava/util/function/BooleanSupplier;Z)V");
    }

    public String gatherStats() {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerChunkCache.gatherStats:()Ljava/lang/String;");
    }

    public ChunkGenerator getGenerator() {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerChunkCache.getGenerator:()Lnet/minecraft/world/level/chunk/ChunkGenerator;");
    }

    public int getLoadedChunksCount() {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerChunkCache.getLoadedChunksCount:()I");
    }

    public void onLightUpdate(LightLayer layer, SectionPos pos) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerChunkCache.onLightUpdate:(Lnet/minecraft/world/level/LightLayer;Lnet/minecraft/core/SectionPos;)V");
    }

    public void addTicketWithRadius(TicketType type, ChunkPos pos, int radius) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerChunkCache.addTicketWithRadius:(Lnet/minecraft/server/level/TicketType;Lnet/minecraft/world/level/ChunkPos;I)V");
    }

    public boolean updateChunkForced(ChunkPos pos, boolean forced) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerChunkCache.updateChunkForced:(Lnet/minecraft/world/level/ChunkPos;Z)Z");
    }

    public LongSet getForceLoadedChunks() {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerChunkCache.getForceLoadedChunks:()Lit/unimi/dsi/fastutil/longs/LongSet;");
    }

    public void addEntity(Entity entity) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerChunkCache.addEntity:(Lnet/minecraft/world/entity/Entity;)V");
    }

    public void sendToTrackingPlayers(Entity entity, Packet<? super ClientGamePacketListener> packet) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerChunkCache.sendToTrackingPlayers:(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/network/protocol/Packet;)V");
    }

    public void setSpawnSettings(boolean spawnEnemies) {
        throw Unimplemented.forMember("net/minecraft/server/level/ServerChunkCache.setSpawnSettings:(Z)V");
    }

    private final class MainThreadExecutor extends BlockableEventLoop<Runnable> {

        private MainThreadExecutor(Level level) {
        }

        public Runnable wrapRunnable(Runnable runnable) {
            throw Unimplemented.forMember("net/minecraft/server/level/ServerChunkCache$MainThreadExecutor.wrapRunnable:(Ljava/lang/Runnable;)Ljava/lang/Runnable;");
        }

        protected boolean shouldRun(Runnable task) {
            throw Unimplemented.forMember("net/minecraft/server/level/ServerChunkCache$MainThreadExecutor.shouldRun:(Ljava/lang/Runnable;)Z");
        }

        protected boolean scheduleExecutables() {
            throw Unimplemented.forMember("net/minecraft/server/level/ServerChunkCache$MainThreadExecutor.scheduleExecutables:()Z");
        }

        protected Thread getRunningThread() {
            throw Unimplemented.forMember("net/minecraft/server/level/ServerChunkCache$MainThreadExecutor.getRunningThread:()Ljava/lang/Thread;");
        }

        protected void doRunTask(Runnable task) {
            throw Unimplemented.forMember("net/minecraft/server/level/ServerChunkCache$MainThreadExecutor.doRunTask:(Ljava/lang/Runnable;)V");
        }

        protected boolean pollTask() {
            throw Unimplemented.forMember("net/minecraft/server/level/ServerChunkCache$MainThreadExecutor.pollTask:()Z");
        }

        protected MainThreadExecutor() {
        }
    }

    public ServerChunkCache() {
    }
}
