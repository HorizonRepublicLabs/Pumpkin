package net.minecraft.world.level.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import dev.pumpkin.shim.Unimplemented;

public class CropBlock extends VegetationBlock implements BonemealableBlock {

    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 7);

    public MapCodec<? extends CropBlock> codec() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/CropBlock.codec:()Lcom/mojang/serialization/MapCodec;");
    }

    public CropBlock(BlockBehaviour.Properties properties) {
        // Pumpkin divergence: chains the properties up. Without this the block's
        // template (and everything else recorded on Properties) silently resets
        // to the defaults -- a crop built ofFullCopy(WHEAT) registered as stone.
        super(properties);
    }

    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/CropBlock.getShape:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/phys/shapes/CollisionContext;)Lnet/minecraft/world/phys/shapes/VoxelShape;");
    }

    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/CropBlock.mayPlaceOn:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;)Z");
    }

    // Pumpkin divergence: vanilla body -- crops age 0 to 7.
    public int getMaxAge() {
        return 7;
    }

    // Pumpkin divergence: vanilla body.
    public BlockState getStateForAge(int age) {
        return defaultBlockState().setValue(AGE, age);
    }

    // Pumpkin divergence: vanilla body.
    public final boolean isMaxAge(BlockState state) {
        return state.getValue(AGE) >= getMaxAge();
    }

    // Pumpkin divergence: vanilla body -- a full-grown crop stops ticking.
    protected boolean isRandomlyTicking(BlockState state) {
        return !isMaxAge(state);
    }

    // Pumpkin divergence: vanilla body -- light gate, farmland-weighted growth chance,
    // one age step written back through the level. The level is Pumpkin's stand-in, whose
    // getBlockState answers from the neighborhood snapshot the random-tick bridge carries.
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (level.getRawBrightness(pos, 0) >= 9) {
            int age = state.getValue(AGE);
            if (age < getMaxAge()) {
                float speed = pumpkinGrowthSpeed(level, pos);
                if (random.nextInt((int) (25.0F / speed) + 1) == 0) {
                    level.setBlock(pos, getStateForAge(age + 1), 2);
                }
            }
        }
    }

    // Pumpkin divergence: vanilla getGrowthSpeed, private because only randomTick above
    // calls it. Moist farmland under the crop counts 3, dry 1, diagonals a quarter; crops
    // of the same kind in a row or touching diagonally halve the total.
    private float pumpkinGrowthSpeed(Level level, BlockPos pos) {
        float speed = 1.0F;
        BlockPos below = pos.below();
        for (int dx = -1; dx <= 1; dx++) {
            for (int dz = -1; dz <= 1; dz++) {
                float gain = 0.0F;
                BlockState soil = level.getBlockState(below.offset(dx, 0, dz));
                if (soil.getBlock() instanceof FarmlandBlock) {
                    gain = 1.0F;
                    if (soil.getValue(FarmlandBlock.MOISTURE) > 0) {
                        gain = 3.0F;
                    }
                }
                if (dx != 0 || dz != 0) {
                    gain /= 4.0F;
                }
                speed += gain;
            }
        }
        boolean row = level.getBlockState(pos.offset(-1, 0, 0)).getBlock() == this
                || level.getBlockState(pos.offset(1, 0, 0)).getBlock() == this;
        boolean column = level.getBlockState(pos.offset(0, 0, -1)).getBlock() == this
                || level.getBlockState(pos.offset(0, 0, 1)).getBlock() == this;
        if (row && column) {
            speed /= 2.0F;
        } else {
            boolean diagonal = level.getBlockState(pos.offset(-1, 0, -1)).getBlock() == this
                    || level.getBlockState(pos.offset(1, 0, -1)).getBlock() == this
                    || level.getBlockState(pos.offset(-1, 0, 1)).getBlock() == this
                    || level.getBlockState(pos.offset(1, 0, 1)).getBlock() == this;
            if (diagonal) {
                speed /= 2.0F;
            }
        }
        return speed;
    }

    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/CropBlock.canSurvive:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/LevelReader;Lnet/minecraft/core/BlockPos;)Z");
    }

    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity, InsideBlockEffectApplier effectApplier, boolean isPrecise) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/CropBlock.entityInside:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/entity/InsideBlockEffectApplier;Z)V");
    }

    protected ItemLike getBaseSeedId() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/CropBlock.getBaseSeedId:()Lnet/minecraft/world/level/ItemLike;");
    }

    protected ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/CropBlock.getCloneItemStack:(Lnet/minecraft/world/level/LevelReader;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Z)Lnet/minecraft/world/item/ItemStack;");
    }

    // Pumpkin divergence: vanilla body -- a crop takes bonemeal until it is grown.
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return !isMaxAge(state);
    }

    // Pumpkin divergence: vanilla body -- crops never fail a bonemeal.
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    // Pumpkin divergence: vanilla body -- two to five age steps, capped at maturity;
    // Mth.nextInt(random, 2, 5) spelled out over the RandomSource the level hands us.
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        int age = state.getValue(AGE) + random.nextInt(4) + 2;
        level.setBlock(pos, getStateForAge(Math.min(age, getMaxAge())), 2);
    }

    // Pumpkin divergence: vanilla body -- a crop is its age.
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    public CropBlock() {
    }
}
