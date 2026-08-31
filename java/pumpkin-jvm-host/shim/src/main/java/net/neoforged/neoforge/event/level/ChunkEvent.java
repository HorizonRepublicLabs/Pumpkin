package net.neoforged.neoforge.event.level;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.LevelChunk;
import dev.pumpkin.shim.Unimplemented;

public abstract class ChunkEvent<T extends ChunkAccess> extends LevelEvent {

    public ChunkEvent(T chunk) {
    }

    public ChunkEvent(T chunk, LevelAccessor level) {
    }

    public T getChunk() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/level/ChunkEvent.getChunk:()Lnet/minecraft/world/level/chunk/ChunkAccess;");
    }

    public static class Load extends ChunkEvent<LevelChunk> {

        public Load(LevelChunk chunk, boolean newChunk) {
        }

        public boolean isNewChunk() {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/level/ChunkEvent$Load.isNewChunk:()Z");
        }

        public Load() {
        }
    }

    public static class Unload extends ChunkEvent<LevelChunk> {

        public Unload(LevelChunk chunk) {
        }

        public Unload() {
        }
    }

    public ChunkEvent() {
    }
}
