package net.minecraft.world.level.levelgen;

import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.state.BlockState;
import dev.pumpkin.shim.Unimplemented;

public interface Aquifer {

    BlockState computeSubstance(final DensityFunction.FunctionContext context, double density);

    boolean shouldScheduleFluidUpdate();

    interface FluidPicker {

        Aquifer.FluidStatus computeFluid(final int blockX, final int blockY, final int blockZ);
    }

    record FluidStatus(int fluidLevel, BlockState fluidType) {
    }

    class NoiseBasedAquifer implements Aquifer {

        private NoiseBasedAquifer(NoiseChunk noiseChunk, ChunkPos pos, NoiseRouter router, PositionalRandomFactory positionalRandomFactory, int minBlockY, int yBlockSize, Aquifer.FluidPicker globalFluidPicker) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/Aquifer$NoiseBasedAquifer.<init>:(Lnet/minecraft/world/level/levelgen/NoiseChunk;Lnet/minecraft/world/level/ChunkPos;Lnet/minecraft/world/level/levelgen/NoiseRouter;Lnet/minecraft/world/level/levelgen/PositionalRandomFactory;IILnet/minecraft/world/level/levelgen/Aquifer$FluidPicker;)V");
        }

        public BlockState computeSubstance(DensityFunction.FunctionContext context, double density) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/Aquifer$NoiseBasedAquifer.computeSubstance:(Lnet/minecraft/world/level/levelgen/DensityFunction$FunctionContext;D)Lnet/minecraft/world/level/block/state/BlockState;");
        }

        public boolean shouldScheduleFluidUpdate() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/Aquifer$NoiseBasedAquifer.shouldScheduleFluidUpdate:()Z");
        }

        private Aquifer.FluidStatus computeFluid(int x, int y, int z) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/Aquifer$NoiseBasedAquifer.computeFluid:(III)Lnet/minecraft/world/level/levelgen/Aquifer$FluidStatus;");
        }

        protected NoiseBasedAquifer() {
        }
    }
}
