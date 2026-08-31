package net.minecraft.world.level.block.state;

import com.mojang.serialization.MapCodec;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.TypedInstance;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.flag.FeatureElement;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.SupportType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import dev.pumpkin.shim.Unimplemented;

public abstract class BlockBehaviour implements FeatureElement {

    public BlockBehaviour(BlockBehaviour.Properties properties) {
    }

    protected abstract MapCodec<? extends Block> codec();

    protected boolean isPathfindable(BlockState state, PathComputationType type) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour.isPathfindable:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/pathfinder/PathComputationType;)Z");
    }

    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess ticks, BlockPos pos, Direction directionToNeighbour, BlockPos neighbourPos, BlockState neighbourState, RandomSource random) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour.updateShape:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/LevelReader;Lnet/minecraft/world/level/ScheduledTickAccess;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Direction;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/util/RandomSource;)Lnet/minecraft/world/level/block/state/BlockState;");
    }

    protected boolean skipRendering(BlockState state, BlockState neighborState, Direction direction) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour.skipRendering:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/Direction;)Z");
    }

    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, Orientation orientation, boolean movedByPiston) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour.neighborChanged:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/Block;Lnet/minecraft/world/level/redstone/Orientation;Z)V");
    }

    protected void affectNeighborsAfterRemoval(BlockState state, ServerLevel level, BlockPos pos, boolean movedByPiston) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour.affectNeighborsAfterRemoval:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Z)V");
    }

    protected void onExplosionHit(BlockState state, ServerLevel level, BlockPos pos, Explosion explosion, BiConsumer<ItemStack, BlockPos> onHit) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour.onExplosionHit:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/Explosion;Ljava/util/function/BiConsumer;)V");
    }

    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour.useItemOn:(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;Lnet/minecraft/world/phys/BlockHitResult;)Lnet/minecraft/world/InteractionResult;");
    }

    protected boolean triggerEvent(BlockState state, Level level, BlockPos pos, int b0, int b1) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour.triggerEvent:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;II)Z");
    }

    protected FluidState getFluidState(BlockState state) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour.getFluidState:(Lnet/minecraft/world/level/block/state/BlockState;)Lnet/minecraft/world/level/material/FluidState;");
    }

    protected boolean hasAnalogOutputSignal(BlockState state) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour.hasAnalogOutputSignal:(Lnet/minecraft/world/level/block/state/BlockState;)Z");
    }

    public FeatureFlagSet requiredFeatures() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour.requiredFeatures:()Lnet/minecraft/world/flag/FeatureFlagSet;");
    }

    protected List<ItemStack> getDrops(BlockState state, LootParams.Builder params) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour.getDrops:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/storage/loot/LootParams$Builder;)Ljava/util/List;");
    }

    protected VoxelShape getOcclusionShape(BlockState state) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour.getOcclusionShape:(Lnet/minecraft/world/level/block/state/BlockState;)Lnet/minecraft/world/phys/shapes/VoxelShape;");
    }

    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour.getShape:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/phys/shapes/CollisionContext;)Lnet/minecraft/world/phys/shapes/VoxelShape;");
    }

    protected VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour.getCollisionShape:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/phys/shapes/CollisionContext;)Lnet/minecraft/world/phys/shapes/VoxelShape;");
    }

    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour.randomTick:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Lnet/minecraft/util/RandomSource;)V");
    }

    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour.tick:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Lnet/minecraft/util/RandomSource;)V");
    }

    protected float getDestroyProgress(BlockState state, Player player, BlockGetter level, BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour.getDestroyProgress:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;)F");
    }

    protected void spawnAfterBreak(BlockState state, ServerLevel level, BlockPos pos, ItemStack tool, boolean dropExperience) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour.spawnAfterBreak:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/item/ItemStack;Z)V");
    }

    protected int getSignal(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour.getSignal:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Direction;)I");
    }

    public final Optional<ResourceKey<LootTable>> getLootTable() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour.getLootTable:()Ljava/util/Optional;");
    }

    public final String getDescriptionId() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour.getDescriptionId:()Ljava/lang/String;");
    }

    protected ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour.getCloneItemStack:(Lnet/minecraft/world/level/LevelReader;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Z)Lnet/minecraft/world/item/ItemStack;");
    }

    public abstract Item asItem();

    protected abstract Block asBlock();

    public abstract static class BlockStateBase extends StateHolder<Block, BlockState> implements TypedInstance<Block> {

        private final boolean isAir = false;

        private final boolean liquid = false;

        private final float destroySpeed = 0.0F;

        private final boolean requiresCorrectToolForDrops = false;

        private final boolean canOcclude = false;

        private final BlockBehaviour.StatePredicate isRedstoneConductor = null;

        protected BlockStateBase(Block owner, Property<?>[] propertyKeys, Comparable<?>[] propertyValues) {
        }

        // Pumpkin divergence: real body. A state answers which block it belongs to --
        // set by Block.defaultBlockState, the only place states are built. A state with
        // no owner still fails loudly, naming this member, rather than returning null.
        // pumpkinOwner is public because Block sets it from another package; it is a
        // Pumpkin seam, not vanilla API a mod could compile against.
        public Block pumpkinOwner;

        public Block getBlock() {
            if (pumpkinOwner == null) {
                throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$BlockStateBase.getBlock:()Lnet/minecraft/world/level/block/Block;");
            }
            return pumpkinOwner;
        }

        public Holder<Block> typeHolder() {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$BlockStateBase.typeHolder:()Lnet/minecraft/core/Holder;");
        }

        public int getLightDampening() {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$BlockStateBase.getLightDampening:()I");
        }

        public int getLightEmission() {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$BlockStateBase.getLightEmission:()I");
        }

        public boolean isAir() {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$BlockStateBase.isAir:()Z");
        }

        public boolean liquid() {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$BlockStateBase.liquid:()Z");
        }

        public BlockState rotate(Rotation rotation) {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$BlockStateBase.rotate:(Lnet/minecraft/world/level/block/Rotation;)Lnet/minecraft/world/level/block/state/BlockState;");
        }

        public boolean isRedstoneConductor(BlockGetter level, BlockPos pos) {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$BlockStateBase.isRedstoneConductor:(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;)Z");
        }

        public int getSignal(BlockGetter level, BlockPos pos, Direction direction) {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$BlockStateBase.getSignal:(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Direction;)I");
        }

        public float getDestroySpeed(BlockGetter level, BlockPos pos) {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$BlockStateBase.getDestroySpeed:(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;)F");
        }

        public float getDestroyProgress(Player player, BlockGetter level, BlockPos pos) {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$BlockStateBase.getDestroyProgress:(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;)F");
        }

        public boolean isSolidRender() {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$BlockStateBase.isSolidRender:()Z");
        }

        public boolean canOcclude() {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$BlockStateBase.canOcclude:()Z");
        }

        public VoxelShape getShape(BlockGetter level, BlockPos pos) {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$BlockStateBase.getShape:(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/phys/shapes/VoxelShape;");
        }

        public VoxelShape getShape(BlockGetter level, BlockPos pos, CollisionContext context) {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$BlockStateBase.getShape:(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/phys/shapes/CollisionContext;)Lnet/minecraft/world/phys/shapes/VoxelShape;");
        }

        public VoxelShape getCollisionShape(BlockGetter level, BlockPos pos) {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$BlockStateBase.getCollisionShape:(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/phys/shapes/VoxelShape;");
        }

        public VoxelShape getCollisionShape(BlockGetter level, BlockPos pos, CollisionContext context) {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$BlockStateBase.getCollisionShape:(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/phys/shapes/CollisionContext;)Lnet/minecraft/world/phys/shapes/VoxelShape;");
        }

        public VoxelShape getBlockSupportShape(BlockGetter level, BlockPos pos) {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$BlockStateBase.getBlockSupportShape:(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/phys/shapes/VoxelShape;");
        }

        public VoxelShape getVisualShape(BlockGetter level, BlockPos pos, CollisionContext context) {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$BlockStateBase.getVisualShape:(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/phys/shapes/CollisionContext;)Lnet/minecraft/world/phys/shapes/VoxelShape;");
        }

        public VoxelShape getInteractionShape(BlockGetter level, BlockPos pos) {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$BlockStateBase.getInteractionShape:(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/phys/shapes/VoxelShape;");
        }

        public void handleNeighborChanged(Level level, BlockPos pos, Block block, Orientation orientation, boolean movedByPiston) {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$BlockStateBase.handleNeighborChanged:(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/Block;Lnet/minecraft/world/level/redstone/Orientation;Z)V");
        }

        public void onExplosionHit(ServerLevel level, BlockPos pos, Explosion explosion, BiConsumer<ItemStack, BlockPos> onHit) {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$BlockStateBase.onExplosionHit:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/Explosion;Ljava/util/function/BiConsumer;)V");
        }

        // Pumpkin divergence: vanilla body in spirit -- dispatch to the owning block's
        // randomTick. Reflection because the method is protected in another package;
        // this is how a growth accelerator forces a tick on the crop above it.
        public void randomTick(ServerLevel level, BlockPos pos, RandomSource random) {
            try {
                java.lang.reflect.Method method = null;
                for (Class<?> type = getBlock().getClass(); type != null; type = type.getSuperclass()) {
                    for (java.lang.reflect.Method candidate : type.getDeclaredMethods()) {
                        if (candidate.getName().equals("randomTick")
                                && candidate.getParameterCount() == 4) {
                            method = candidate;
                            break;
                        }
                    }
                    if (method != null) {
                        break;
                    }
                }
                if (method == null) {
                    return;
                }
                method.setAccessible(true);
                method.invoke(getBlock(), this, level, pos, random);
            } catch (java.lang.reflect.InvocationTargetException e) {
                if (e.getCause() instanceof RuntimeException cause) {
                    throw cause;
                }
                throw new IllegalStateException(e.getCause());
            } catch (ReflectiveOperationException e) {
                throw new IllegalStateException(e);
            }
        }

        public void spawnAfterBreak(ServerLevel level, BlockPos pos, ItemStack tool, boolean dropExperience) {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$BlockStateBase.spawnAfterBreak:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/item/ItemStack;Z)V");
        }

        public InteractionResult useItemOn(ItemStack itemStack, Level level, Player player, InteractionHand hand, BlockHitResult hitResult) {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$BlockStateBase.useItemOn:(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;Lnet/minecraft/world/phys/BlockHitResult;)Lnet/minecraft/world/InteractionResult;");
        }

        public InteractionResult useWithoutItem(Level level, Player player, BlockHitResult hitResult) {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$BlockStateBase.useWithoutItem:(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/phys/BlockHitResult;)Lnet/minecraft/world/InteractionResult;");
        }

        public boolean canBeReplaced(BlockPlaceContext context) {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$BlockStateBase.canBeReplaced:(Lnet/minecraft/world/item/context/BlockPlaceContext;)Z");
        }

        public boolean canBeReplaced(Fluid fluid) {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$BlockStateBase.canBeReplaced:(Lnet/minecraft/world/level/material/Fluid;)Z");
        }

        public boolean canBeReplaced() {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$BlockStateBase.canBeReplaced:()Z");
        }

        public boolean canSurvive(LevelReader level, BlockPos pos) {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$BlockStateBase.canSurvive:(Lnet/minecraft/world/level/LevelReader;Lnet/minecraft/core/BlockPos;)Z");
        }

        public boolean is(TagKey<Block> tag, Predicate<BlockBehaviour.BlockStateBase> predicate) {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$BlockStateBase.is:(Lnet/minecraft/tags/TagKey;Ljava/util/function/Predicate;)Z");
        }

        public boolean hasBlockEntity() {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$BlockStateBase.hasBlockEntity:()Z");
        }

        public FluidState getFluidState() {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$BlockStateBase.getFluidState:()Lnet/minecraft/world/level/material/FluidState;");
        }

        public SoundType getSoundType() {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$BlockStateBase.getSoundType:()Lnet/minecraft/world/level/block/SoundType;");
        }

        public boolean isFaceSturdy(BlockGetter level, BlockPos pos, Direction direction) {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$BlockStateBase.isFaceSturdy:(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Direction;)Z");
        }

        public boolean isFaceSturdy(BlockGetter level, BlockPos pos, Direction direction, SupportType supportType) {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$BlockStateBase.isFaceSturdy:(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Direction;Lnet/minecraft/world/level/block/SupportType;)Z");
        }

        public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, boolean includeData) {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$BlockStateBase.getCloneItemStack:(Lnet/minecraft/world/level/LevelReader;Lnet/minecraft/core/BlockPos;Z)Lnet/minecraft/world/item/ItemStack;");
        }

        protected abstract BlockState asState();

        public boolean requiresCorrectToolForDrops() {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$BlockStateBase.requiresCorrectToolForDrops:()Z");
        }

        private static final class Cache {

            private Cache(BlockState state) {
            }

            protected Cache() {
            }
        }

        public BlockStateBase() {
        }
    }

    public interface OffsetFunction {

        Vec3 evaluate(BlockState state, BlockPos pos);
    }

    public enum OffsetType {

        NONE, XZ, XYZ
    }

    public interface PostProcess {

        BlockPos getPostProcessPos(BlockState state, BlockGetter level, BlockPos pos);
    }

    public static class Properties {

        // Pumpkin divergence from the generated shim: this field, pumpkinTemplate(String)
        // and template() have no vanilla counterpart, and of() below has a real body.
        // Pumpkin registers a block by copying a vanilla one, so something has to name the
        // template; a mod that never says gets stone. Re-apply by hand after any
        // regeneration -- grep for "Pumpkin divergence".
        private String pumpkinTemplate = "stone";

        // Pumpkin divergence: recorded from strength() and requiresCorrectToolForDrops().
        // Pumpkin models all three; the sink cannot carry them yet. Kept so that fixing that
        // is a change to the sink and not a hunt through the mods for what they asked for.
        private Float pumpkinDestroyTime;

        private Float pumpkinExplosionResistance;

        private boolean pumpkinRequiresTool;

        public Float pumpkinDestroyTime() {
            return pumpkinDestroyTime;
        }

        public Float pumpkinExplosionResistance() {
            return pumpkinExplosionResistance;
        }

        public boolean pumpkinRequiresTool() {
            return pumpkinRequiresTool;
        }

        // Pumpkin divergence: no vanilla counterpart. Names the vanilla block to copy.
        public Properties pumpkinTemplate(String template) {
            this.pumpkinTemplate = template;
            return this;
        }

        // Pumpkin divergence: no vanilla counterpart. Read by Block.pumpkinTemplate().
        public String template() {
            return pumpkinTemplate;
        }

        private Function<BlockState, MapColor> mapColor;

        private ToIntFunction<BlockState> lightEmission;

        private boolean requiresCorrectToolForDrops;

        private float friction;

        private boolean ignitedByLava;

        private boolean liquid;

        private PushReaction pushReaction;

        private NoteBlockInstrument instrument;

        private boolean replaceable;

        private BlockBehaviour.StateArgumentPredicate<EntityType<?>> isValidSpawn;

        private BlockBehaviour.StatePredicate isRedstoneConductor;

        private BlockBehaviour.StatePredicate isSuffocating;

        private BlockBehaviour.StatePredicate isViewBlocking;

        private Predicate<BlockState> emissiveRendering;

        private boolean dynamicShape;

        // Pumpkin divergence: real body. of() below is the only way a mod gets one of
        // these, and it has to return something the builder calls can chain off.
        protected Properties() {
        }

        // Pumpkin divergence: real body.
        // Pumpkin divergence: real body. Every mod block starts here.
        public static BlockBehaviour.Properties of() {
            return new Properties();
        }

        // Pumpkin divergence: real body. A copy carries the template forward -- that is the
        // only state Pumpkin reads off these properties today.
        // Pumpkin divergence: carries the source's template forward. A crop built with
        // ofFullCopy(Blocks.WHEAT) must register as a wheat copy, not stone -- wheat's
        // states are what make it randomly tick.
        public static BlockBehaviour.Properties ofFullCopy(BlockBehaviour block) {
            Properties properties = new Properties();
            if (block instanceof net.minecraft.world.level.block.Block source) {
                properties.pumpkinTemplate = source.pumpkinTemplate();
            }
            return properties;
        }

        public static BlockBehaviour.Properties ofLegacyCopy(BlockBehaviour block) {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$Properties.ofLegacyCopy:(Lnet/minecraft/world/level/block/state/BlockBehaviour;)Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;");
        }

        public BlockBehaviour.Properties mapColor(DyeColor dyeColor) {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$Properties.mapColor:(Lnet/minecraft/world/item/DyeColor;)Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;");
        }

        public BlockBehaviour.Properties mapColor(MapColor mapColor) {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$Properties.mapColor:(Lnet/minecraft/world/level/material/MapColor;)Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;");
        }

        public BlockBehaviour.Properties mapColor(Function<BlockState, MapColor> mapColor) {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$Properties.mapColor:(Ljava/util/function/Function;)Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;");
        }

        public BlockBehaviour.Properties noCollision() {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$Properties.noCollision:()Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;");
        }

        // Pumpkin divergence: real body. Accepted and dropped -- occlusion is a client-side rendering concern.
        // The chain must return `this` for the mod's next call to land.
        public BlockBehaviour.Properties noOcclusion() {
            return this;
        }

        public BlockBehaviour.Properties friction(float friction) {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$Properties.friction:(F)Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;");
        }

        // Pumpkin divergence: real body. Accepted and dropped -- Pumpkin has no per-block sound table yet.
        // The chain must return `this` for the mod's next call to land.
        public BlockBehaviour.Properties sound(SoundType soundType) {
            return this;
        }

        public BlockBehaviour.Properties lightLevel(ToIntFunction<BlockState> lightEmission) {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$Properties.lightLevel:(Ljava/util/function/ToIntFunction;)Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;");
        }

        // Pumpkin divergence: real body, and the one that loses information.
        //
        // Pumpkin's own registration models both of these -- BlockSpec carries hardness and
        // blast_resistance -- but the sink between here and it is registerBlock(id, template)
        // and has nowhere to put them. So the block registers with whatever its vanilla
        // template has, not what the mod asked for.
        //
        // Recorded rather than ignored so that widening the sink is a small change here
        // instead of an archaeology exercise. Until then a mod's stone-hard block may be
        // dirt-hard, which is wrong and worth fixing before anyone plays on this.
        public BlockBehaviour.Properties strength(float destroyTime, float explosionResistance) {
            this.pumpkinDestroyTime = destroyTime;
            this.pumpkinExplosionResistance = explosionResistance;
            return this;
        }

        // Pumpkin divergence: real body. Vanilla treats one argument as both values.
        public BlockBehaviour.Properties strength(float destroyTime) {
            return strength(destroyTime, destroyTime);
        }

        public BlockBehaviour.Properties dynamicShape() {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$Properties.dynamicShape:()Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;");
        }

        public BlockBehaviour.Properties noLootTable() {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$Properties.noLootTable:()Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;");
        }

        public BlockBehaviour.Properties ignitedByLava() {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$Properties.ignitedByLava:()Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;");
        }

        public BlockBehaviour.Properties liquid() {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$Properties.liquid:()Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;");
        }

        public BlockBehaviour.Properties pushReaction(PushReaction pushReaction) {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$Properties.pushReaction:(Lnet/minecraft/world/level/material/PushReaction;)Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;");
        }

        // Pumpkin divergence: real body. A spawn/render predicate Pumpkin does not

        // consult; accepted and dropped, chain returns `this`.

        public BlockBehaviour.Properties isValidSpawn(BlockBehaviour.StateArgumentPredicate<EntityType<?>> isValidSpawn) {

            return this;

        }

        // Pumpkin divergence: real body. A spawn/render predicate Pumpkin does not

        // consult; accepted and dropped, chain returns `this`.

        public BlockBehaviour.Properties isRedstoneConductor(BlockBehaviour.StatePredicate isRedstoneConductor) {

            return this;

        }

        // Pumpkin divergence: real body. A spawn/render predicate Pumpkin does not

        // consult; accepted and dropped, chain returns `this`.

        public BlockBehaviour.Properties isSuffocating(BlockBehaviour.StatePredicate isSuffocating) {

            return this;

        }

        // Pumpkin divergence: real body. A spawn/render predicate Pumpkin does not

        // consult; accepted and dropped, chain returns `this`.

        public BlockBehaviour.Properties isViewBlocking(BlockBehaviour.StatePredicate isViewBlocking) {

            return this;

        }

        public BlockBehaviour.Properties emissiveRendering(Predicate<BlockState> emissiveRendering) {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$Properties.emissiveRendering:(Ljava/util/function/Predicate;)Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;");
        }

        // Pumpkin divergence: real body. Recorded, and dropped at the sink for the same
        // reason strength is -- BlockSpec models requires_tool and cannot be told.
        public BlockBehaviour.Properties requiresCorrectToolForDrops() {
            this.pumpkinRequiresTool = true;
            return this;
        }

        public BlockBehaviour.Properties instrument(NoteBlockInstrument instrument) {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$Properties.instrument:(Lnet/minecraft/world/level/block/state/properties/NoteBlockInstrument;)Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;");
        }

        public BlockBehaviour.Properties replaceable() {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$Properties.replaceable:()Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;");
        }

        // Pumpkin divergence: real body. The id arrives again at registration, from the
        // DeferredRegister that owns the holder, so nothing here needs to keep it.
        public BlockBehaviour.Properties setId(ResourceKey<Block> id) {
            return this;
        }
    }

    public interface StateArgumentPredicate<A> {

        boolean test(BlockState state, BlockGetter level, BlockPos pos, A a);
    }

    public interface StatePredicate {

        boolean test(BlockState state, BlockGetter level, BlockPos pos);
    }

    public BlockBehaviour() {
    }
}
