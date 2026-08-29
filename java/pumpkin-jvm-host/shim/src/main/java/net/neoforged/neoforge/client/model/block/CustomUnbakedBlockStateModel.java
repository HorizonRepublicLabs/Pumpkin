package net.neoforged.neoforge.client.model.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.client.renderer.block.dispatch.BlockStateModel;

public interface CustomUnbakedBlockStateModel extends BlockStateModel.Unbaked {

    MapCodec<? extends CustomUnbakedBlockStateModel> codec();
}
