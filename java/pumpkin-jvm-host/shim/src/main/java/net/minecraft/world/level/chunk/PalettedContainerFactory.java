package net.minecraft.world.level.chunk;

import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import dev.pumpkin.shim.Unimplemented;

public record PalettedContainerFactory(Strategy<BlockState> blockStatesStrategy, BlockState defaultBlockState, Codec<PalettedContainer<BlockState>> blockStatesContainerCodec, Strategy<Holder<Biome>> biomeStrategy, Holder<Biome> defaultBiome, Codec<PalettedContainerRO<Holder<Biome>>> biomeContainerCodec) {

    public static PalettedContainerFactory create(RegistryAccess registries) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/PalettedContainerFactory.create:(Lnet/minecraft/core/RegistryAccess;)Lnet/minecraft/world/level/chunk/PalettedContainerFactory;");
    }
}
