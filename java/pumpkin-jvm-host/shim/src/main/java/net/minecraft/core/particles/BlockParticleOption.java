package net.minecraft.core.particles;

import net.minecraft.world.level.block.state.BlockState;
import dev.pumpkin.shim.Unimplemented;

public class BlockParticleOption implements ParticleOptions {

    public BlockParticleOption(ParticleType<BlockParticleOption> type, BlockState state) {
    }

    public BlockParticleOption(ParticleType<BlockParticleOption> type, BlockState state, net.minecraft.core.BlockPos pos) {
    }

    public ParticleType<BlockParticleOption> getType() {
        throw Unimplemented.forMember("net/minecraft/core/particles/BlockParticleOption.getType:()Lnet/minecraft/core/particles/ParticleType;");
    }

    public net.minecraft.core.BlockPos getPos() {
        throw Unimplemented.forMember("net/minecraft/core/particles/BlockParticleOption.getPos:()Lnet/minecraft/core/BlockPos;");
    }

    public BlockParticleOption() {
    }
}
