package net.minecraft.client.multiplayer;

import java.util.function.BooleanSupplier;
import net.minecraft.core.SectionPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.chunk.ChunkSource;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.chunk.status.ChunkStatus;
import net.minecraft.world.level.lighting.LevelLightEngine;
import dev.pumpkin.shim.Unimplemented;

public class ClientChunkCache extends ChunkSource {

    public ClientChunkCache(ClientLevel level, int serverChunkRadius) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientChunkCache.<init>:(Lnet/minecraft/client/multiplayer/ClientLevel;I)V");
    }

    public LevelLightEngine getLightEngine() {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientChunkCache.getLightEngine:()Lnet/minecraft/world/level/lighting/LevelLightEngine;");
    }

    public LevelChunk getChunk(int x, int z, ChunkStatus targetStatus, boolean loadOrGenerate) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientChunkCache.getChunk:(IILnet/minecraft/world/level/chunk/status/ChunkStatus;Z)Lnet/minecraft/world/level/chunk/LevelChunk;");
    }

    public BlockGetter getLevel() {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientChunkCache.getLevel:()Lnet/minecraft/world/level/BlockGetter;");
    }

    public void tick(BooleanSupplier haveTime, boolean tickChunks) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientChunkCache.tick:(Ljava/util/function/BooleanSupplier;Z)V");
    }

    public String gatherStats() {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientChunkCache.gatherStats:()Ljava/lang/String;");
    }

    public int getLoadedChunksCount() {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientChunkCache.getLoadedChunksCount:()I");
    }

    public void onLightUpdate(LightLayer layer, SectionPos pos) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientChunkCache.onLightUpdate:(Lnet/minecraft/world/level/LightLayer;Lnet/minecraft/core/SectionPos;)V");
    }

    public void onSectionEmptinessChanged(int sectionX, int sectionY, int sectionZ, boolean empty) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientChunkCache.onSectionEmptinessChanged:(IIIZ)V");
    }

    private final class Storage {

        private Storage(int chunkRadius) {
            throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientChunkCache$Storage.<init>:(I)V");
        }

        public LevelChunk getChunk(int index) {
            throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientChunkCache$Storage.getChunk:(I)Lnet/minecraft/world/level/chunk/LevelChunk;");
        }

        protected Storage() {
        }
    }

    protected ClientChunkCache() {
    }
}
