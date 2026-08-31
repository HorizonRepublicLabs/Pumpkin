package net.minecraft.world.level.material;

import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.extensions.IFluidExtension;
import dev.pumpkin.shim.Unimplemented;

public abstract class Fluid implements IFluidExtension {

    private FluidState defaultFluidState;

    private final Holder.Reference<Fluid> builtInRegistryHolder = null;

    protected Fluid() {
    }

    public final FluidState defaultFluidState() {
        throw Unimplemented.forMember("net/minecraft/world/level/material/Fluid.defaultFluidState:()Lnet/minecraft/world/level/material/FluidState;");
    }

    public abstract Item getBucket();

    protected void tick(ServerLevel level, BlockPos pos, BlockState blockState, FluidState fluidState) {
        throw Unimplemented.forMember("net/minecraft/world/level/material/Fluid.tick:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/material/FluidState;)V");
    }

    protected abstract boolean canBeReplacedWith(FluidState state, final BlockGetter level, final BlockPos pos, Fluid other, Direction direction);

    protected abstract Vec3 getFlow(BlockGetter level, BlockPos pos, FluidState fluidState);

    public abstract int getTickDelay(LevelReader level);

    protected boolean isEmpty() {
        throw Unimplemented.forMember("net/minecraft/world/level/material/Fluid.isEmpty:()Z");
    }

    protected abstract float getExplosionResistance();

    public abstract float getHeight(FluidState fluidState, final BlockGetter level, final BlockPos pos);

    public abstract float getOwnHeight(FluidState fluidState);

    protected abstract BlockState createLegacyBlock(FluidState fluidState);

    public abstract boolean isSource(FluidState fluidState);

    public abstract int getAmount(FluidState fluidState);

    public boolean isSame(Fluid other) {
        throw Unimplemented.forMember("net/minecraft/world/level/material/Fluid.isSame:(Lnet/minecraft/world/level/material/Fluid;)Z");
    }

    public boolean is(TagKey<Fluid> tag) {
        throw Unimplemented.forMember("net/minecraft/world/level/material/Fluid.is:(Lnet/minecraft/tags/TagKey;)Z");
    }

    public abstract VoxelShape getShape(final FluidState state, final BlockGetter level, final BlockPos pos);

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/world/level/material/Fluid.toString:()Ljava/lang/String;");
    }

    public Optional<SoundEvent> getPickupSound() {
        throw Unimplemented.forMember("net/minecraft/world/level/material/Fluid.getPickupSound:()Ljava/util/Optional;");
    }

    public Holder.Reference<Fluid> builtInRegistryHolder() {
        throw Unimplemented.forMember("net/minecraft/world/level/material/Fluid.builtInRegistryHolder:()Lnet/minecraft/core/Holder$Reference;");
    }

    public net.neoforged.neoforge.fluids.FluidType getFluidType() {
        throw Unimplemented.forMember("net/minecraft/world/level/material/Fluid.getFluidType:()Lnet/neoforged/neoforge/fluids/FluidType;");
    }
}
