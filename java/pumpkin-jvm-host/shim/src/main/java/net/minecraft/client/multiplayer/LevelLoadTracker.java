package net.minecraft.client.multiplayer;

import java.util.concurrent.atomic.AtomicBoolean;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.progress.LevelLoadListener;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import dev.pumpkin.shim.Unimplemented;

public class LevelLoadTracker implements LevelLoadListener {

    public LevelLoadTracker() {
    }

    public LevelLoadTracker(long closeDelayMs) {
    }

    public void start(LevelLoadListener.Stage stage, int totalChunks) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/LevelLoadTracker.start:(Lnet/minecraft/server/level/progress/LevelLoadListener$Stage;I)V");
    }

    public void update(LevelLoadListener.Stage stage, int currentChunks, int totalChunks) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/LevelLoadTracker.update:(Lnet/minecraft/server/level/progress/LevelLoadListener$Stage;II)V");
    }

    public void finish(LevelLoadListener.Stage stage) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/LevelLoadTracker.finish:(Lnet/minecraft/server/level/progress/LevelLoadListener$Stage;)V");
    }

    public void updateFocus(ResourceKey<Level> dimension, ChunkPos chunkPos) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/LevelLoadTracker.updateFocus:(Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/world/level/ChunkPos;)V");
    }

    private record ClientLevelReady(long readyAt) implements LevelLoadTracker.ClientState {
    }

    private interface ClientState {

        default LevelLoadTracker.ClientState tick() {
            throw Unimplemented.forMember("net/minecraft/client/multiplayer/LevelLoadTracker$ClientState.tick:()Lnet/minecraft/client/multiplayer/LevelLoadTracker$ClientState;");
        }
    }

    private record WaitingForPlayerChunk(LocalPlayer player, ClientLevel level, AtomicBoolean playerSectionReady, long timeoutAfter) implements LevelLoadTracker.ClientState {

        public LevelLoadTracker.ClientState tick() {
            throw Unimplemented.forMember("net/minecraft/client/multiplayer/LevelLoadTracker$WaitingForPlayerChunk.tick:()Lnet/minecraft/client/multiplayer/LevelLoadTracker$ClientState;");
        }
    }

    private record WaitingForServer(LocalPlayer player, ClientLevel level, long timeoutAfter) implements LevelLoadTracker.ClientState {

        public LevelLoadTracker.ClientState loadingPacketsReceived() {
            throw Unimplemented.forMember("net/minecraft/client/multiplayer/LevelLoadTracker$WaitingForServer.loadingPacketsReceived:()Lnet/minecraft/client/multiplayer/LevelLoadTracker$ClientState;");
        }
    }
}
