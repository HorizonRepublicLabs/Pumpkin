package net.minecraft.world.level.chunk;

import java.util.function.Predicate;
import net.minecraft.core.Holder;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import dev.pumpkin.shim.Unimplemented;

public class LevelChunkSection {

    private LevelChunkSection(LevelChunkSection source) {
    }

    public LevelChunkSection(PalettedContainer<BlockState> states, PalettedContainerRO<Holder<Biome>> biomes) {
    }

    public LevelChunkSection(PalettedContainerFactory containerFactory) {
    }

    public BlockState getBlockState(int sectionX, int sectionY, int sectionZ) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/LevelChunkSection.getBlockState:(III)Lnet/minecraft/world/level/block/state/BlockState;");
    }

    public void acquire() {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/LevelChunkSection.acquire:()V");
    }

    public void release() {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/LevelChunkSection.release:()V");
    }

    public BlockState setBlockState(int sectionX, int sectionY, int sectionZ, BlockState state) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/LevelChunkSection.setBlockState:(IIILnet/minecraft/world/level/block/state/BlockState;)Lnet/minecraft/world/level/block/state/BlockState;");
    }

    public BlockState setBlockState(int sectionX, int sectionY, int sectionZ, BlockState state, boolean checkThreading) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/LevelChunkSection.setBlockState:(IIILnet/minecraft/world/level/block/state/BlockState;Z)Lnet/minecraft/world/level/block/state/BlockState;");
    }

    public void read(FriendlyByteBuf buffer) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/LevelChunkSection.read:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public void write(FriendlyByteBuf buffer) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/LevelChunkSection.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public int getSerializedSize() {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/LevelChunkSection.getSerializedSize:()I");
    }

    public boolean maybeHas(Predicate<BlockState> predicate) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/LevelChunkSection.maybeHas:(Ljava/util/function/Predicate;)Z");
    }

    public Holder<Biome> getNoiseBiome(int quartX, int quartY, int quartZ) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/LevelChunkSection.getNoiseBiome:(III)Lnet/minecraft/core/Holder;");
    }

    public LevelChunkSection copy() {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/LevelChunkSection.copy:()Lnet/minecraft/world/level/chunk/LevelChunkSection;");
    }

    public LevelChunkSection() {
    }
}
