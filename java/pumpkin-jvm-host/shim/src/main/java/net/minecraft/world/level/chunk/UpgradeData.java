package net.minecraft.world.level.chunk;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.block.state.BlockState;
import dev.pumpkin.shim.Unimplemented;

public class UpgradeData {

    private UpgradeData(LevelHeightAccessor levelHeightAccessor) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/UpgradeData.<init>:(Lnet/minecraft/world/level/LevelHeightAccessor;)V");
    }

    public UpgradeData(CompoundTag tag, LevelHeightAccessor levelHeightAccessor) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/UpgradeData.<init>:(Lnet/minecraft/nbt/CompoundTag;Lnet/minecraft/world/level/LevelHeightAccessor;)V");
    }

    private UpgradeData(UpgradeData source) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/UpgradeData.<init>:(Lnet/minecraft/world/level/chunk/UpgradeData;)V");
    }

    public boolean isEmpty() {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/UpgradeData.isEmpty:()Z");
    }

    public UpgradeData copy() {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/UpgradeData.copy:()Lnet/minecraft/world/level/chunk/UpgradeData;");
    }

    public interface BlockFixer {

        BlockState updateShape(final BlockState state, final Direction direction, final BlockState neighbour, final LevelAccessor level, final BlockPos pos, final BlockPos neighbourPos);
    }

    private enum BlockFixers implements UpgradeData.BlockFixer {

        BLACKLIST {

            public BlockState updateShape(BlockState state, Direction direction, BlockState neighbour, LevelAccessor level, BlockPos pos, BlockPos neighbourPos) {
                throw Unimplemented.forMember("net/minecraft/world/level/chunk/UpgradeData$BlockFixers$BLACKLIST.updateShape:()");
            }
        }
        , DEFAULT {

            public BlockState updateShape(BlockState state, Direction direction, BlockState neighbour, LevelAccessor level, BlockPos pos, BlockPos neighbourPos) {
                throw Unimplemented.forMember("net/minecraft/world/level/chunk/UpgradeData$BlockFixers$DEFAULT.updateShape:()");
            }
        }
        , CHEST {

            public BlockState updateShape(BlockState state, Direction direction, BlockState neighbour, LevelAccessor level, BlockPos pos, BlockPos neighbourPos) {
                throw Unimplemented.forMember("net/minecraft/world/level/chunk/UpgradeData$BlockFixers$CHEST.updateShape:()");
            }
        }
        , LEAVES {

            public BlockState updateShape(BlockState state, Direction direction, BlockState neighbour, LevelAccessor level, BlockPos pos, BlockPos neighbourPos) {
                throw Unimplemented.forMember("net/minecraft/world/level/chunk/UpgradeData$BlockFixers$LEAVES.updateShape:()");
            }

            public void processChunk(LevelAccessor level) {
                throw Unimplemented.forMember("net/minecraft/world/level/chunk/UpgradeData$BlockFixers$LEAVES.processChunk:()");
            }
        }
        , STEM_BLOCK {

            public BlockState updateShape(BlockState state, Direction direction, BlockState neighbour, LevelAccessor level, BlockPos pos, BlockPos neighbourPos) {
                throw Unimplemented.forMember("net/minecraft/world/level/chunk/UpgradeData$BlockFixers$STEM_BLOCK.updateShape:()");
            }
        }

    }

    public UpgradeData() {
    }
}
