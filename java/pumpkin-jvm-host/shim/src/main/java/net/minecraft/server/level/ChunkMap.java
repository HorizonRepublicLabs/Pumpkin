package net.minecraft.server.level;

import com.mojang.datafixers.DataFixer;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;
import java.util.function.Predicate;
import java.util.function.Supplier;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.util.StaticCache2D;
import net.minecraft.util.thread.BlockableEventLoop;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.TicketStorage;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.chunk.LightChunkGetter;
import net.minecraft.world.level.chunk.status.ChunkStatus;
import net.minecraft.world.level.chunk.status.ChunkStep;
import net.minecraft.world.level.chunk.storage.SimpleRegionStorage;
import net.minecraft.world.level.entity.ChunkStatusUpdateListener;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import net.minecraft.world.level.storage.LevelStorageSource;
import net.minecraft.world.level.storage.SavedDataStorage;
import dev.pumpkin.shim.Unimplemented;

public class ChunkMap extends SimpleRegionStorage implements ChunkHolder.PlayerProvider, GeneratingChunkMap {

    public ChunkMap(ServerLevel level, LevelStorageSource.LevelStorageAccess levelStorage, DataFixer dataFixer, StructureTemplateManager structureManager, Executor executor, BlockableEventLoop<Runnable> mainThreadExecutor, LightChunkGetter chunkGetter, ChunkGenerator generator, ChunkStatusUpdateListener chunkStatusListener, Supplier<SavedDataStorage> overworldDataStorage, TicketStorage ticketStorage, int serverViewDistance, boolean syncWrites) {
    }

    protected ThreadedLevelLightEngine getLightEngine() {
        throw Unimplemented.forMember("net/minecraft/server/level/ChunkMap.getLightEngine:()Lnet/minecraft/server/level/ThreadedLevelLightEngine;");
    }

    private ChunkHolder updateChunkScheduling(long node, int level, ChunkHolder chunk, int oldLevel) {
        throw Unimplemented.forMember("net/minecraft/server/level/ChunkMap.updateChunkScheduling:(JILnet/minecraft/server/level/ChunkHolder;I)Lnet/minecraft/server/level/ChunkHolder;");
    }

    private void onLevelChange(ChunkPos pos, IntSupplier oldLevel, int newLevel, IntConsumer setQueueLevel) {
        throw Unimplemented.forMember("net/minecraft/server/level/ChunkMap.onLevelChange:(Lnet/minecraft/world/level/ChunkPos;Ljava/util/function/IntSupplier;ILjava/util/function/IntConsumer;)V");
    }

    public void close() throws IOException {
        throw Unimplemented.forMember("net/minecraft/server/level/ChunkMap.close:()V");
    }

    public GenerationChunkHolder acquireGeneration(long chunkNode) {
        throw Unimplemented.forMember("net/minecraft/server/level/ChunkMap.acquireGeneration:(J)Lnet/minecraft/server/level/GenerationChunkHolder;");
    }

    public void releaseGeneration(GenerationChunkHolder chunkHolder) {
        throw Unimplemented.forMember("net/minecraft/server/level/ChunkMap.releaseGeneration:(Lnet/minecraft/server/level/GenerationChunkHolder;)V");
    }

    public CompletableFuture<ChunkAccess> applyStep(GenerationChunkHolder chunkHolder, ChunkStep step, StaticCache2D<GenerationChunkHolder> cache) {
        throw Unimplemented.forMember("net/minecraft/server/level/ChunkMap.applyStep:(Lnet/minecraft/server/level/GenerationChunkHolder;Lnet/minecraft/world/level/chunk/status/ChunkStep;Lnet/minecraft/util/StaticCache2D;)Ljava/util/concurrent/CompletableFuture;");
    }

    public ChunkGenerationTask scheduleGenerationTask(ChunkStatus targetStatus, ChunkPos pos) {
        throw Unimplemented.forMember("net/minecraft/server/level/ChunkMap.scheduleGenerationTask:(Lnet/minecraft/world/level/chunk/status/ChunkStatus;Lnet/minecraft/world/level/ChunkPos;)Lnet/minecraft/server/level/ChunkGenerationTask;");
    }

    public void runGenerationTasks() {
        throw Unimplemented.forMember("net/minecraft/server/level/ChunkMap.runGenerationTasks:()V");
    }

    public int size() {
        throw Unimplemented.forMember("net/minecraft/server/level/ChunkMap.size:()I");
    }

    public List<ServerPlayer> getPlayers(ChunkPos pos, boolean borderOnly) {
        throw Unimplemented.forMember("net/minecraft/server/level/ChunkMap.getPlayers:(Lnet/minecraft/world/level/ChunkPos;Z)Ljava/util/List;");
    }

    protected void addEntity(Entity entity) {
        throw Unimplemented.forMember("net/minecraft/server/level/ChunkMap.addEntity:(Lnet/minecraft/world/entity/Entity;)V");
    }

    protected void tick() {
        throw Unimplemented.forMember("net/minecraft/server/level/ChunkMap.tick:()V");
    }

    private class DistanceManager extends net.minecraft.server.level.DistanceManager {

        protected DistanceManager(TicketStorage ticketStorage, Executor executor, Executor mainThreadExecutor) {
        }

        protected boolean isChunkToRemove(long node) {
            throw Unimplemented.forMember("net/minecraft/server/level/ChunkMap$DistanceManager.isChunkToRemove:(J)Z");
        }

        protected ChunkHolder getChunk(long node) {
            throw Unimplemented.forMember("net/minecraft/server/level/ChunkMap$DistanceManager.getChunk:(J)Lnet/minecraft/server/level/ChunkHolder;");
        }

        protected ChunkHolder updateChunkScheduling(long node, int level, ChunkHolder chunk, int oldLevel) {
            throw Unimplemented.forMember("net/minecraft/server/level/ChunkMap$DistanceManager.updateChunkScheduling:(JILnet/minecraft/server/level/ChunkHolder;I)Lnet/minecraft/server/level/ChunkHolder;");
        }

        protected DistanceManager() {
        }
    }

    private class TrackedEntity implements ServerEntity.Synchronizer {

        public TrackedEntity(Entity entity, int range, int updateInterval, boolean trackDelta) {
        }

        public boolean equals(Object obj) {
            throw Unimplemented.forMember("net/minecraft/server/level/ChunkMap$TrackedEntity.equals:(Ljava/lang/Object;)Z");
        }

        public int hashCode() {
            throw Unimplemented.forMember("net/minecraft/server/level/ChunkMap$TrackedEntity.hashCode:()I");
        }

        public void sendToTrackingPlayers(Packet<? super ClientGamePacketListener> packet) {
            throw Unimplemented.forMember("net/minecraft/server/level/ChunkMap$TrackedEntity.sendToTrackingPlayers:(Lnet/minecraft/network/protocol/Packet;)V");
        }

        public void sendToTrackingPlayersAndSelf(Packet<? super ClientGamePacketListener> packet) {
            throw Unimplemented.forMember("net/minecraft/server/level/ChunkMap$TrackedEntity.sendToTrackingPlayersAndSelf:(Lnet/minecraft/network/protocol/Packet;)V");
        }

        public void sendToTrackingPlayersFiltered(Packet<? super ClientGamePacketListener> packet, Predicate<ServerPlayer> targetPredicate) {
            throw Unimplemented.forMember("net/minecraft/server/level/ChunkMap$TrackedEntity.sendToTrackingPlayersFiltered:(Lnet/minecraft/network/protocol/Packet;Ljava/util/function/Predicate;)V");
        }

        protected TrackedEntity() {
        }
    }

    public ChunkMap() {
    }
}
