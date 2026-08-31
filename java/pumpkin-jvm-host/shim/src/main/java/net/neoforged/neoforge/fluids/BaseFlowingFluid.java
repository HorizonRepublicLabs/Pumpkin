package net.neoforged.neoforge.fluids;

import java.util.Optional;
import java.util.function.Supplier;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import dev.pumpkin.shim.Unimplemented;

public abstract class BaseFlowingFluid extends FlowingFluid {

    protected BaseFlowingFluid(Properties properties) {
    }

    public FluidType getFluidType() {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/BaseFlowingFluid.getFluidType:()Lnet/neoforged/neoforge/fluids/FluidType;");
    }

    public Fluid getFlowing() {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/BaseFlowingFluid.getFlowing:()Lnet/minecraft/world/level/material/Fluid;");
    }

    public Fluid getSource() {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/BaseFlowingFluid.getSource:()Lnet/minecraft/world/level/material/Fluid;");
    }

    protected boolean canConvertToSource(ServerLevel level) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/BaseFlowingFluid.canConvertToSource:(Lnet/minecraft/server/level/ServerLevel;)Z");
    }

    public boolean canConvertToSource(FluidState state, ServerLevel level, BlockPos pos) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/BaseFlowingFluid.canConvertToSource:(Lnet/minecraft/world/level/material/FluidState;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;)Z");
    }

    protected void beforeDestroyingBlock(LevelAccessor worldIn, BlockPos pos, BlockState state) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/BaseFlowingFluid.beforeDestroyingBlock:(Lnet/minecraft/world/level/LevelAccessor;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)V");
    }

    protected int getSlopeFindDistance(LevelReader worldIn) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/BaseFlowingFluid.getSlopeFindDistance:(Lnet/minecraft/world/level/LevelReader;)I");
    }

    protected int getDropOff(LevelReader worldIn) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/BaseFlowingFluid.getDropOff:(Lnet/minecraft/world/level/LevelReader;)I");
    }

    public Item getBucket() {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/BaseFlowingFluid.getBucket:()Lnet/minecraft/world/item/Item;");
    }

    protected boolean canBeReplacedWith(FluidState state, BlockGetter level, BlockPos pos, Fluid fluidIn, Direction direction) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/BaseFlowingFluid.canBeReplacedWith:(Lnet/minecraft/world/level/material/FluidState;Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/material/Fluid;Lnet/minecraft/core/Direction;)Z");
    }

    public int getTickDelay(LevelReader level) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/BaseFlowingFluid.getTickDelay:(Lnet/minecraft/world/level/LevelReader;)I");
    }

    protected float getExplosionResistance() {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/BaseFlowingFluid.getExplosionResistance:()F");
    }

    protected BlockState createLegacyBlock(FluidState state) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/BaseFlowingFluid.createLegacyBlock:(Lnet/minecraft/world/level/material/FluidState;)Lnet/minecraft/world/level/block/state/BlockState;");
    }

    public boolean isSame(Fluid fluidIn) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/BaseFlowingFluid.isSame:(Lnet/minecraft/world/level/material/Fluid;)Z");
    }

    public Optional<SoundEvent> getPickupSound() {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/BaseFlowingFluid.getPickupSound:()Ljava/util/Optional;");
    }

    public static class Flowing extends BaseFlowingFluid {

        public Flowing(Properties properties) {
        }

        public int getAmount(FluidState state) {
            throw Unimplemented.forMember("net/neoforged/neoforge/fluids/BaseFlowingFluid$Flowing.getAmount:(Lnet/minecraft/world/level/material/FluidState;)I");
        }

        public boolean isSource(FluidState state) {
            throw Unimplemented.forMember("net/neoforged/neoforge/fluids/BaseFlowingFluid$Flowing.isSource:(Lnet/minecraft/world/level/material/FluidState;)Z");
        }

        public Flowing() {
        }
    }

    public static class Source extends BaseFlowingFluid {

        public Source(Properties properties) {
        }

        public int getAmount(FluidState state) {
            throw Unimplemented.forMember("net/neoforged/neoforge/fluids/BaseFlowingFluid$Source.getAmount:(Lnet/minecraft/world/level/material/FluidState;)I");
        }

        public boolean isSource(FluidState state) {
            throw Unimplemented.forMember("net/neoforged/neoforge/fluids/BaseFlowingFluid$Source.isSource:(Lnet/minecraft/world/level/material/FluidState;)Z");
        }

        public Source() {
        }
    }

    public static class Properties {

        private Supplier<? extends Item> bucket;

        private Supplier<? extends LiquidBlock> block;

        public Properties(Supplier<? extends FluidType> fluidType, Supplier<? extends Fluid> still, Supplier<? extends Fluid> flowing) {
        }

        public Properties bucket(Supplier<? extends Item> bucket) {
            return this;
        }

        public Properties block(Supplier<? extends LiquidBlock> block) {
            return this;
        }

        public Properties() {
        }
    }

    public BaseFlowingFluid() {
    }
}
