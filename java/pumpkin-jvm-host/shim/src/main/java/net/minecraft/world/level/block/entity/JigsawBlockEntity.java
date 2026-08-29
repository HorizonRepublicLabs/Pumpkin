package net.minecraft.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.Identifier;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import dev.pumpkin.shim.Unimplemented;

public class JigsawBlockEntity extends BlockEntity {

    public JigsawBlockEntity(BlockPos worldPosition, BlockState blockState) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/JigsawBlockEntity.<init>:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)V");
    }

    public Identifier getName() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/JigsawBlockEntity.getName:()Lnet/minecraft/resources/Identifier;");
    }

    public Identifier getTarget() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/JigsawBlockEntity.getTarget:()Lnet/minecraft/resources/Identifier;");
    }

    protected void saveAdditional(ValueOutput output) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/JigsawBlockEntity.saveAdditional:(Lnet/minecraft/world/level/storage/ValueOutput;)V");
    }

    protected void loadAdditional(ValueInput input) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/JigsawBlockEntity.loadAdditional:(Lnet/minecraft/world/level/storage/ValueInput;)V");
    }

    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/JigsawBlockEntity.getUpdateTag:(Lnet/minecraft/core/HolderLookup$Provider;)Lnet/minecraft/nbt/CompoundTag;");
    }

    public enum JointType implements StringRepresentable {

        ROLLABLE, ALIGNED;

        public String getSerializedName() {
            throw Unimplemented.forMember("net/minecraft/world/level/block/entity/JigsawBlockEntity$JointType.getSerializedName:()Ljava/lang/String;");
        }
    }

    protected JigsawBlockEntity() {
    }
}
