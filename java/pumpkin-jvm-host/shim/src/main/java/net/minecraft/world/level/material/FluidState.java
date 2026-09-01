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

    // Pumpkin divergence: the fluid is kept; getType/isEmpty answer from it.
    private Fluid pumpkinFluid;

    public FluidState(Fluid owner, Property<?>[] propertyKeys, Comparable<?>[] propertyValues) {
        this.pumpkinFluid = owner;
    }

    public Fluid getType() {
        return pumpkinFluid == null ? net.minecraft.world.level.material.Fluids.EMPTY : pumpkinFluid;
    }

    public boolean isSource() {
        throw Unimplemented.forMember("net/minecraft/world/level/material/FluidState.isSource:()Z");
    }

    public boolean isEmpty() {
        return getType() == net.minecraft.world.level.material.Fluids.EMPTY;
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
        return getType().builtInRegistryHolder();
    }

    // Pumpkin divergence: tag membership from the real fluid tag tables, over the
    // carried fluid's own name; a nameless fluid wears no tags.
    @Override
    public boolean is(net.minecraft.tags.TagKey<Fluid> tag) {
        String name = getType().pumpkinVanillaName;
        if (name == null) {
            return false;
        }
        String id = name.contains(":") ? name : "minecraft:" + name;
        return dev.pumpkin.bridge.PumpkinTags.containsKind("fluid", tag.location().toString(), id);
    }

    public float getExplosionResistance() {
        throw Unimplemented.forMember("net/minecraft/world/level/material/FluidState.getExplosionResistance:()F");
    }

    public FluidState() {
    }
}
