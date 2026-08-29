package net.minecraft.world.level.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import dev.pumpkin.shim.Unimplemented;

public abstract class BaseEntityBlock extends Block implements EntityBlock {

    public BaseEntityBlock(BlockBehaviour.Properties properties) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/BaseEntityBlock.<init>:(Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;)V");
    }

    protected abstract MapCodec<? extends BaseEntityBlock> codec();

    protected boolean triggerEvent(BlockState state, Level level, BlockPos pos, int b0, int b1) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/BaseEntityBlock.triggerEvent:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;II)Z");
    }

    protected MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/BaseEntityBlock.getMenuProvider:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/MenuProvider;");
    }

    protected BaseEntityBlock() {
    }
}
