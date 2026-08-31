package net.minecraft.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import dev.pumpkin.shim.Unimplemented;

public enum SupportType {

    FULL {

        public boolean isSupporting(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            throw Unimplemented.forMember("net/minecraft/world/level/block/SupportType$FULL.isSupporting:()");
        }
    }
    , CENTER {

        public boolean isSupporting(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            throw Unimplemented.forMember("net/minecraft/world/level/block/SupportType$CENTER.isSupporting:()");
        }
    }
    , RIGID {

        public boolean isSupporting(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
            throw Unimplemented.forMember("net/minecraft/world/level/block/SupportType$RIGID.isSupporting:()");
        }
    }
    ;

    public abstract boolean isSupporting(final BlockState state, final BlockGetter level, final BlockPos pos, final Direction direction);
}
