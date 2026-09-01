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

    // Pumpkin divergence: no vanilla counterpart -- the stand-in Fluids hands out for
    // WATER and LAVA. Fluid simulation runs on the Rust side; mods carry the token and
    // read its identity; every behaviour member throws by name.
    static FlowingFluid pumpkinInertFlowing(String name) {
        FlowingFluid fluid = new FlowingFluid() {
            public Fluid getFlowing() {
                return this;
            }

            public Fluid getSource() {
                return this;
            }

            protected boolean canConvertToSource(net.minecraft.server.level.ServerLevel level) {
                throw dev.pumpkin.shim.Unimplemented.forMember("net/minecraft/world/level/material/FlowingFluid.canConvertToSource (inert stand-in)");
            }

            protected void beforeDestroyingBlock(net.minecraft.world.level.LevelAccessor level, BlockPos pos, net.minecraft.world.level.block.state.BlockState state) {
                throw dev.pumpkin.shim.Unimplemented.forMember("net/minecraft/world/level/material/FlowingFluid.beforeDestroyingBlock (inert stand-in)");
            }

            protected int getSlopeFindDistance(net.minecraft.world.level.LevelReader level) {
                throw dev.pumpkin.shim.Unimplemented.forMember("net/minecraft/world/level/material/FlowingFluid.getSlopeFindDistance (inert stand-in)");
            }

            protected int getDropOff(net.minecraft.world.level.LevelReader level) {
                throw dev.pumpkin.shim.Unimplemented.forMember("net/minecraft/world/level/material/FlowingFluid.getDropOff (inert stand-in)");
            }

            public net.minecraft.world.item.Item getBucket() {
                throw dev.pumpkin.shim.Unimplemented.forMember("net/minecraft/world/level/material/Fluid.getBucket (inert stand-in)");
            }

            protected boolean canBeReplacedWith(net.minecraft.world.level.material.FluidState state, net.minecraft.world.level.BlockGetter level, BlockPos pos, Fluid other, net.minecraft.core.Direction direction) {
                throw dev.pumpkin.shim.Unimplemented.forMember("net/minecraft/world/level/material/Fluid.canBeReplacedWith (inert stand-in)");
            }


            public int getTickDelay(net.minecraft.world.level.LevelReader level) {
                throw dev.pumpkin.shim.Unimplemented.forMember("net/minecraft/world/level/material/Fluid.getTickDelay (inert stand-in)");
            }

            protected float getExplosionResistance() {
                throw dev.pumpkin.shim.Unimplemented.forMember("net/minecraft/world/level/material/Fluid.getExplosionResistance (inert stand-in)");
            }



            protected net.minecraft.world.level.block.state.BlockState createLegacyBlock(net.minecraft.world.level.material.FluidState fluidState) {
                throw dev.pumpkin.shim.Unimplemented.forMember("net/minecraft/world/level/material/Fluid.createLegacyBlock (inert stand-in)");
            }

            public boolean isSource(net.minecraft.world.level.material.FluidState fluidState) {
                throw dev.pumpkin.shim.Unimplemented.forMember("net/minecraft/world/level/material/Fluid.isSource (inert stand-in)");
            }

            public int getAmount(net.minecraft.world.level.material.FluidState fluidState) {
                throw dev.pumpkin.shim.Unimplemented.forMember("net/minecraft/world/level/material/Fluid.getAmount (inert stand-in)");
            }

        };
        fluid.pumpkinVanillaName = name;
        return fluid;
    }

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
