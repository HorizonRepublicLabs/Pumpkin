package net.minecraft.world.level.block.entity;

import java.util.List;
import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Vec3i;
import net.minecraft.gametest.framework.GameTestInstance;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import dev.pumpkin.shim.Unimplemented;

public class TestInstanceBlockEntity extends BlockEntity implements BoundingBoxRenderable, BeaconBeamOwner {

    public TestInstanceBlockEntity(BlockPos worldPosition, BlockState blockState) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/TestInstanceBlockEntity.<init>:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)V");
    }

    public void set(TestInstanceBlockEntity.Data data) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/TestInstanceBlockEntity.set:(Lnet/minecraft/world/level/block/entity/TestInstanceBlockEntity$Data;)V");
    }

    public Optional<ResourceKey<GameTestInstance>> test() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/TestInstanceBlockEntity.test:()Ljava/util/Optional;");
    }

    public Vec3i getSize() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/TestInstanceBlockEntity.getSize:()Lnet/minecraft/core/Vec3i;");
    }

    public void setChanged() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/TestInstanceBlockEntity.setChanged:()V");
    }

    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/TestInstanceBlockEntity.getUpdateTag:(Lnet/minecraft/core/HolderLookup$Provider;)Lnet/minecraft/nbt/CompoundTag;");
    }

    protected void loadAdditional(ValueInput input) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/TestInstanceBlockEntity.loadAdditional:(Lnet/minecraft/world/level/storage/ValueInput;)V");
    }

    protected void saveAdditional(ValueOutput output) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/TestInstanceBlockEntity.saveAdditional:(Lnet/minecraft/world/level/storage/ValueOutput;)V");
    }

    public BoundingBoxRenderable.Mode renderMode() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/TestInstanceBlockEntity.renderMode:()Lnet/minecraft/world/level/block/entity/BoundingBoxRenderable$Mode;");
    }

    public BoundingBoxRenderable.RenderableBox getRenderableBox() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/TestInstanceBlockEntity.getRenderableBox:()Lnet/minecraft/world/level/block/entity/BoundingBoxRenderable$RenderableBox;");
    }

    public List<BeaconBeamOwner.Section> getBeamSections() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/TestInstanceBlockEntity.getBeamSections:()Ljava/util/List;");
    }

    public record Data(Optional<ResourceKey<GameTestInstance>> test, Vec3i size, Rotation rotation, boolean ignoreEntities, TestInstanceBlockEntity.Status status, Optional<Component> errorMessage) {
    }

    public record ErrorMarker(BlockPos pos, Component text) {
    }

    public enum Status implements StringRepresentable {

        CLEARED, RUNNING, FINISHED;

        public String getSerializedName() {
            throw Unimplemented.forMember("net/minecraft/world/level/block/entity/TestInstanceBlockEntity$Status.getSerializedName:()Ljava/lang/String;");
        }
    }

    protected TestInstanceBlockEntity() {
    }
}
