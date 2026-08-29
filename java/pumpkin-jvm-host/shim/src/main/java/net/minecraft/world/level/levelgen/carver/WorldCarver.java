package net.minecraft.world.level.levelgen.carver;

import com.mojang.serialization.Codec;
import java.util.function.Function;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.CarvingMask;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.Aquifer;
import dev.pumpkin.shim.Unimplemented;

public abstract class WorldCarver<C extends CarverConfiguration> {

    private static <C extends CarverConfiguration, F extends WorldCarver<C>> F register(String name, F carver) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/carver/WorldCarver.register:(Ljava/lang/String;Lnet/minecraft/world/level/levelgen/carver/WorldCarver;)Lnet/minecraft/world/level/levelgen/carver/WorldCarver;");
    }

    public WorldCarver(Codec<C> codec) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/carver/WorldCarver.<init>:(Lcom/mojang/serialization/Codec;)V");
    }

    public abstract boolean carve(final CarvingContext context, final C configuration, final ChunkAccess chunk, final Function<BlockPos, Holder<Biome>> biomeGetter, final RandomSource random, final Aquifer aquifer, final ChunkPos sourceChunkPos, CarvingMask mask);

    public abstract boolean isStartChunk(final C configuration, final RandomSource random);

    public interface CarveSkipChecker {

        boolean shouldSkip(CarvingContext context, double xd, double yd, double zd, int y);
    }

    protected WorldCarver() {
    }
}
