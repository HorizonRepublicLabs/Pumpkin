package net.minecraft.world.level.material;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import dev.pumpkin.shim.Unimplemented;

public abstract class FlowingFluid extends Fluid {

    protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
        throw Unimplemented.forMember("net/minecraft/world/level/material/FlowingFluid.createFluidStateDefinition:(Lnet/minecraft/world/level/block/state/StateDefinition$Builder;)V");
    }

    public Vec3 getFlow(BlockGetter level, BlockPos pos, FluidState fluidState) {
        throw Unimplemented.forMember("net/minecraft/world/level/material/FlowingFluid.getFlow:(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/material/FluidState;)Lnet/minecraft/world/phys/Vec3;");
    }

    public abstract Fluid getFlowing();

    public abstract Fluid getSource();

    public FluidState getSource(boolean falling) {
        throw Unimplemented.forMember("net/minecraft/world/level/material/FlowingFluid.getSource:(Z)Lnet/minecraft/world/level/material/FluidState;");
    }

    public boolean canConvertToSource(FluidState state, ServerLevel level, BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/material/FlowingFluid.canConvertToSource:(Lnet/minecraft/world/level/material/FluidState;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;)Z");
    }

    protected abstract boolean canConvertToSource(ServerLevel level);

    protected abstract void beforeDestroyingBlock(LevelAccessor level, BlockPos pos, BlockState state);

    protected abstract int getSlopeFindDistance(LevelReader level);

    protected abstract int getDropOff(LevelReader level);

    public void tick(ServerLevel level, BlockPos pos, BlockState blockState, FluidState fluidState) {
        throw Unimplemented.forMember("net/minecraft/world/level/material/FlowingFluid.tick:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/material/FluidState;)V");
    }

    public float getHeight(FluidState fluidState, BlockGetter level, BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/material/FlowingFluid.getHeight:(Lnet/minecraft/world/level/material/FluidState;Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;)F");
    }

    public float getOwnHeight(FluidState fluidState) {
        throw Unimplemented.forMember("net/minecraft/world/level/material/FlowingFluid.getOwnHeight:(Lnet/minecraft/world/level/material/FluidState;)F");
    }

    public abstract int getAmount(final FluidState fluidState);

    public VoxelShape getShape(FluidState state, BlockGetter level, BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/material/FlowingFluid.getShape:(Lnet/minecraft/world/level/material/FluidState;Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/phys/shapes/VoxelShape;");
    }

    private record BlockStatePairKey(BlockState first, BlockState second, Direction direction) {

        public boolean equals(Object o) {
            throw Unimplemented.forMember("net/minecraft/world/level/material/FlowingFluid$BlockStatePairKey.equals:(Ljava/lang/Object;)Z");
        }

        public int hashCode() {
            throw Unimplemented.forMember("net/minecraft/world/level/material/FlowingFluid$BlockStatePairKey.hashCode:()I");
        }
    }

    protected class SpreadContext {

        private SpreadContext(BlockGetter level, BlockPos origin) {
        }

        public BlockState getBlockState(BlockPos pos) {
            throw Unimplemented.forMember("net/minecraft/world/level/material/FlowingFluid$SpreadContext.getBlockState:(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/state/BlockState;");
        }

        protected SpreadContext() {
        }
    }

    public FlowingFluid() {
    }
}
