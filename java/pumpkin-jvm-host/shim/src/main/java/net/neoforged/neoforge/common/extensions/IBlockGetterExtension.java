package net.neoforged.neoforge.common.extensions;

import net.minecraft.core.BlockPos;
import net.neoforged.neoforge.model.data.ModelData;
import dev.pumpkin.shim.Unimplemented;

public interface IBlockGetterExtension {

    default ModelData getModelData(BlockPos pos) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IBlockGetterExtension.getModelData:(Lnet/minecraft/core/BlockPos;)Lnet/neoforged/neoforge/model/data/ModelData;");
    }
}
