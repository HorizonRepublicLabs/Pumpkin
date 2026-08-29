package net.minecraft.server.level;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.lighting.LevelLightEngine;
import dev.pumpkin.shim.Unimplemented;

public class ChunkHolder extends GenerationChunkHolder {

    public ChunkHolder(ChunkPos pos, int ticketLevel, LevelHeightAccessor levelHeightAccessor, LevelLightEngine lightEngine, ChunkHolder.LevelChangeListener onLevelChange, ChunkHolder.PlayerProvider playerProvider) {
        throw Unimplemented.forMember("net/minecraft/server/level/ChunkHolder.<init>:(Lnet/minecraft/world/level/ChunkPos;ILnet/minecraft/world/level/LevelHeightAccessor;Lnet/minecraft/world/level/lighting/LevelLightEngine;Lnet/minecraft/server/level/ChunkHolder$LevelChangeListener;Lnet/minecraft/server/level/ChunkHolder$PlayerProvider;)V");
    }

    protected void addSaveDependency(CompletableFuture<?> sync) {
        throw Unimplemented.forMember("net/minecraft/server/level/ChunkHolder.addSaveDependency:(Ljava/util/concurrent/CompletableFuture;)V");
    }

    public int getTicketLevel() {
        throw Unimplemented.forMember("net/minecraft/server/level/ChunkHolder.getTicketLevel:()I");
    }

    public int getQueueLevel() {
        throw Unimplemented.forMember("net/minecraft/server/level/ChunkHolder.getQueueLevel:()I");
    }

    public interface LevelChangeListener {

        void onLevelChange(ChunkPos pos, IntSupplier oldLevel, int newLevel, IntConsumer setQueueLevel);
    }

    public interface PlayerProvider {

        List<ServerPlayer> getPlayers(ChunkPos pos, boolean borderOnly);
    }

    public ChunkHolder() {
    }
}
