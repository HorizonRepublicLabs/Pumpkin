package net.minecraft.world.level;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.chunk.ChunkSource;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.storage.LevelData;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.ticks.ScheduledTick;
import net.minecraft.world.ticks.TickPriority;
import dev.pumpkin.shim.Unimplemented;

public interface LevelAccessor extends CommonLevelAccessor, ScheduledTickAccess {

    long nextSubTickCount();

    default <T> ScheduledTick<T> createTick(BlockPos pos, T type, int tickDelay, TickPriority priority) {
        throw Unimplemented.forMember("net/minecraft/world/level/LevelAccessor.createTick:(Lnet/minecraft/core/BlockPos;Ljava/lang/Object;ILnet/minecraft/world/ticks/TickPriority;)Lnet/minecraft/world/ticks/ScheduledTick;");
    }

    default <T> ScheduledTick<T> createTick(BlockPos pos, T type, int tickDelay) {
        throw Unimplemented.forMember("net/minecraft/world/level/LevelAccessor.createTick:(Lnet/minecraft/core/BlockPos;Ljava/lang/Object;I)Lnet/minecraft/world/ticks/ScheduledTick;");
    }

    LevelData getLevelData();

    // Pumpkin divergence: a real monotonic clock in tick units. The stand-in level
    // does not tick, but mods stamp caches and cooldowns with this; wall time over
    // 50ms is the truthful equivalent of the server's tick counter.
    default long getGameTime() {
        return System.nanoTime() / 50_000_000L;
    }

    MinecraftServer getServer();

    default Difficulty getDifficulty() {
        throw Unimplemented.forMember("net/minecraft/world/level/LevelAccessor.getDifficulty:()Lnet/minecraft/world/Difficulty;");
    }

    ChunkSource getChunkSource();

    default boolean hasChunk(int chunkX, int chunkZ) {
        throw Unimplemented.forMember("net/minecraft/world/level/LevelAccessor.hasChunk:(II)Z");
    }

    RandomSource getRandom();

    default void updateNeighborsAt(BlockPos pos, Block sourceBlock) {
        throw Unimplemented.forMember("net/minecraft/world/level/LevelAccessor.updateNeighborsAt:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/Block;)V");
    }

    default void playSound(Entity except, BlockPos pos, SoundEvent soundEvent, SoundSource source) {
        throw Unimplemented.forMember("net/minecraft/world/level/LevelAccessor.playSound:(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/core/BlockPos;Lnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;)V");
    }

    void playSound(final Entity except, final BlockPos pos, final SoundEvent sound, final SoundSource source, final float volume, final float pitch);

    void addParticle(final ParticleOptions particle, final double x, final double y, final double z, final double xd, final double yd, final double zd);

    void levelEvent(final Entity source, final int type, final BlockPos pos, final int data);

    default void levelEvent(int type, BlockPos pos, int data) {
        throw Unimplemented.forMember("net/minecraft/world/level/LevelAccessor.levelEvent:(ILnet/minecraft/core/BlockPos;I)V");
    }

    void gameEvent(Holder<GameEvent> gameEvent, Vec3 position, GameEvent.Context context);

    default void gameEvent(Entity sourceEntity, Holder<GameEvent> gameEvent, Vec3 pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/LevelAccessor.gameEvent:(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/core/Holder;Lnet/minecraft/world/phys/Vec3;)V");
    }

    default void gameEvent(Entity sourceEntity, Holder<GameEvent> gameEvent, BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/LevelAccessor.gameEvent:(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/core/Holder;Lnet/minecraft/core/BlockPos;)V");
    }

    default void gameEvent(Holder<GameEvent> gameEvent, BlockPos pos, GameEvent.Context context) {
        throw Unimplemented.forMember("net/minecraft/world/level/LevelAccessor.gameEvent:(Lnet/minecraft/core/Holder;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/gameevent/GameEvent$Context;)V");
    }

    default void gameEvent(ResourceKey<GameEvent> gameEvent, BlockPos pos, GameEvent.Context context) {
        throw Unimplemented.forMember("net/minecraft/world/level/LevelAccessor.gameEvent:(Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/gameevent/GameEvent$Context;)V");
    }
}
