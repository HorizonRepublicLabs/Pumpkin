package net.minecraft.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import dev.pumpkin.shim.Unimplemented;

public class StructureBlockEntity extends BlockEntity implements BoundingBoxRenderable {

    public StructureBlockEntity(BlockPos worldPosition, BlockState blockState) {
    }

    protected void saveAdditional(ValueOutput output) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/StructureBlockEntity.saveAdditional:(Lnet/minecraft/world/level/storage/ValueOutput;)V");
    }

    protected void loadAdditional(ValueInput input) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/StructureBlockEntity.loadAdditional:(Lnet/minecraft/world/level/storage/ValueInput;)V");
    }

    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/StructureBlockEntity.getUpdateTag:(Lnet/minecraft/core/HolderLookup$Provider;)Lnet/minecraft/nbt/CompoundTag;");
    }

    public long getSeed() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/StructureBlockEntity.getSeed:()J");
    }

    public void setSeed(long seed) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/StructureBlockEntity.setSeed:(J)V");
    }

    public BoundingBoxRenderable.Mode renderMode() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/StructureBlockEntity.renderMode:()Lnet/minecraft/world/level/block/entity/BoundingBoxRenderable$Mode;");
    }

    public BoundingBoxRenderable.RenderableBox getRenderableBox() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/StructureBlockEntity.getRenderableBox:()Lnet/minecraft/world/level/block/entity/BoundingBoxRenderable$RenderableBox;");
    }

    public enum UpdateType {

        UPDATE_DATA, SAVE_AREA, LOAD_AREA, SCAN_AREA
    }

    public StructureBlockEntity() {
    }
}
