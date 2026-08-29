package net.minecraft.world.level.chunk;

import java.util.function.Predicate;
import net.minecraft.core.Holder;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import dev.pumpkin.shim.Unimplemented;

public class LevelChunkSection {

    private LevelChunkSection(LevelChunkSection source) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/LevelChunkSection.<init>:(Lnet/minecraft/world/level/chunk/LevelChunkSection;)V");
    }

    public LevelChunkSection(PalettedContainer<BlockState> states, PalettedContainerRO<Holder<Biome>> biomes) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/LevelChunkSection.<init>:(Lnet/minecraft/world/level/chunk/PalettedContainer;Lnet/minecraft/world/level/chunk/PalettedContainerRO;)V");
    }

    public LevelChunkSection(PalettedContainerFactory containerFactory) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/LevelChunkSection.<init>:(Lnet/minecraft/world/level/chunk/PalettedContainerFactory;)V");
    }

    public void release() {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/LevelChunkSection.release:()V");
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
