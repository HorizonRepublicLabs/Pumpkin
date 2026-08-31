package net.minecraft.world.level.material;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.TypedInstance;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateHolder;
import net.minecraft.world.level.block.state.properties.Property;
import net.neoforged.neoforge.common.extensions.IFluidStateExtension;
import dev.pumpkin.shim.Unimplemented;

public final class FluidState extends StateHolder<Fluid, FluidState> implements TypedInstance<Fluid>, IFluidStateExtension {

    public FluidState(Fluid owner, Property<?>[] propertyKeys, Comparable<?>[] propertyValues) {
    }

    public Fluid getType() {
        throw Unimplemented.forMember("net/minecraft/world/level/material/FluidState.getType:()Lnet/minecraft/world/level/material/Fluid;");
    }

    public boolean isSource() {
        throw Unimplemented.forMember("net/minecraft/world/level/material/FluidState.isSource:()Z");
    }

    public boolean isEmpty() {
        throw Unimplemented.forMember("net/minecraft/world/level/material/FluidState.isEmpty:()Z");
    }

    public float getHeight(BlockGetter level, BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/material/FluidState.getHeight:(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;)F");
    }

    public int getAmount() {
        throw Unimplemented.forMember("net/minecraft/world/level/material/FluidState.getAmount:()I");
    }

    public BlockState createLegacyBlock() {
        throw Unimplemented.forMember("net/minecraft/world/level/material/FluidState.createLegacyBlock:()Lnet/minecraft/world/level/block/state/BlockState;");
    }

    public Holder<Fluid> typeHolder() {
        throw Unimplemented.forMember("net/minecraft/world/level/material/FluidState.typeHolder:()Lnet/minecraft/core/Holder;");
    }

    public float getExplosionResistance() {
        throw Unimplemented.forMember("net/minecraft/world/level/material/FluidState.getExplosionResistance:()F");
    }

    public FluidState() {
    }
}
