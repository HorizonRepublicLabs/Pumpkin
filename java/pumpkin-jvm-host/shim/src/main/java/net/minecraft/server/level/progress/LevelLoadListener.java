package net.minecraft.server.level.progress;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;

public interface LevelLoadListener {

    void start(LevelLoadListener.Stage stage, int totalChunks);

    void update(LevelLoadListener.Stage stage, int currentChunks, int totalChunks);

    void finish(LevelLoadListener.Stage stage);

    void updateFocus(ResourceKey<Level> dimension, ChunkPos chunkPos);

    enum Stage {

        START_SERVER, PREPARE_GLOBAL_SPAWN, LOAD_INITIAL_CHUNKS, LOAD_PLAYER_CHUNKS
    }
}
