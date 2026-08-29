package net.minecraft.gametest.framework;

import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.common.extensions.GameTestHelperExtension;
import dev.pumpkin.shim.Unimplemented;

public class GameTestHelper implements GameTestHelperExtension {

    public GameTestHelper(GameTestInfo testInfo) {
    }

    public ServerLevel getLevel() {
        throw Unimplemented.forMember("net/minecraft/gametest/framework/GameTestHelper.getLevel:()Lnet/minecraft/server/level/ServerLevel;");
    }

    public BlockState getBlockState(BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/gametest/framework/GameTestHelper.getBlockState:(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/state/BlockState;");
    }

    public <T extends BlockEntity> T getBlockEntity(BlockPos pos, Class<T> type) {
        throw Unimplemented.forMember("net/minecraft/gametest/framework/GameTestHelper.getBlockEntity:(Lnet/minecraft/core/BlockPos;Ljava/lang/Class;)Lnet/minecraft/world/level/block/entity/BlockEntity;");
    }

    public void discard(Entity entity) {
        throw Unimplemented.forMember("net/minecraft/gametest/framework/GameTestHelper.discard:(Lnet/minecraft/world/entity/Entity;)V");
    }

    public void setBlock(int x, int y, int z, Block block) {
        throw Unimplemented.forMember("net/minecraft/gametest/framework/GameTestHelper.setBlock:(IIILnet/minecraft/world/level/block/Block;)V");
    }

    public void setBlock(int x, int y, int z, BlockState state) {
        throw Unimplemented.forMember("net/minecraft/gametest/framework/GameTestHelper.setBlock:(IIILnet/minecraft/world/level/block/state/BlockState;)V");
    }

    public void setBlock(BlockPos blockPos, Block block) {
        throw Unimplemented.forMember("net/minecraft/gametest/framework/GameTestHelper.setBlock:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/Block;)V");
    }

    public void setBlock(BlockPos blockPos, BlockState state) {
        throw Unimplemented.forMember("net/minecraft/gametest/framework/GameTestHelper.setBlock:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)V");
    }

    public <T extends Entity> List<T> getEntities(EntityType<T> entityType, BlockPos pos, double distance) {
        throw Unimplemented.forMember("net/minecraft/gametest/framework/GameTestHelper.getEntities:(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/core/BlockPos;D)Ljava/util/List;");
    }

    public int getHeight(Heightmap.Types heightmap, int x, int z) {
        throw Unimplemented.forMember("net/minecraft/gametest/framework/GameTestHelper.getHeight:(Lnet/minecraft/world/level/levelgen/Heightmap$Types;II)I");
    }

    private class TestBlockPlaceContext extends BlockPlaceContext {

        public TestBlockPlaceContext(Level level, InteractionHand hand, ItemStack itemStackInHand, BlockHitResult hitResult, Direction placeDirection) {
        }

        public Direction getNearestLookingDirection() {
            throw Unimplemented.forMember("net/minecraft/gametest/framework/GameTestHelper$TestBlockPlaceContext.getNearestLookingDirection:()Lnet/minecraft/core/Direction;");
        }

        public Direction[] getNearestLookingDirections() {
            throw Unimplemented.forMember("net/minecraft/gametest/framework/GameTestHelper$TestBlockPlaceContext.getNearestLookingDirections:()[Lnet/minecraft/core/Direction;");
        }

        protected TestBlockPlaceContext() {
        }
    }

    public GameTestHelper() {
    }
}
