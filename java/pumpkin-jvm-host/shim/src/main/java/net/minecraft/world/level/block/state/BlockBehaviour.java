package net.minecraft.world.level.block.state;

import com.mojang.serialization.MapCodec;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.TypedInstance;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureElement;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import dev.pumpkin.shim.Unimplemented;

public abstract class BlockBehaviour implements FeatureElement {

    public BlockBehaviour(BlockBehaviour.Properties properties) {
    }

    protected abstract MapCodec<? extends Block> codec();

    protected FluidState getFluidState(BlockState state) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour.getFluidState:(Lnet/minecraft/world/level/block/state/BlockState;)Lnet/minecraft/world/level/material/FluidState;");
    }

    public FeatureFlagSet requiredFeatures() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour.requiredFeatures:()Lnet/minecraft/world/flag/FeatureFlagSet;");
    }

    protected List<ItemStack> getDrops(BlockState state, LootParams.Builder params) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour.getDrops:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/storage/loot/LootParams$Builder;)Ljava/util/List;");
    }

    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour.randomTick:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Lnet/minecraft/util/RandomSource;)V");
    }

    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour.tick:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Lnet/minecraft/util/RandomSource;)V");
    }

    public final Optional<ResourceKey<LootTable>> getLootTable() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour.getLootTable:()Ljava/util/Optional;");
    }

    public abstract Item asItem();

    protected abstract Block asBlock();

    public abstract static class BlockStateBase extends StateHolder<Block, BlockState> implements TypedInstance<Block> {

        private final boolean isAir = false;

        private final boolean requiresCorrectToolForDrops = false;

        private final boolean canOcclude = false;

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

        public boolean isAir() {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$BlockStateBase.isAir:()Z");
        }

        public BlockState rotate(Rotation rotation) {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$BlockStateBase.rotate:(Lnet/minecraft/world/level/block/Rotation;)Lnet/minecraft/world/level/block/state/BlockState;");
        }

        public float getDestroySpeed(BlockGetter level, BlockPos pos) {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$BlockStateBase.getDestroySpeed:(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;)F");
        }

        public boolean canOcclude() {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$BlockStateBase.canOcclude:()Z");
        }

        public VoxelShape getShape(BlockGetter level, BlockPos pos, CollisionContext context) {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$BlockStateBase.getShape:(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/phys/shapes/CollisionContext;)Lnet/minecraft/world/phys/shapes/VoxelShape;");
        }

        public VoxelShape getCollisionShape(BlockGetter level, BlockPos pos, CollisionContext context) {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$BlockStateBase.getCollisionShape:(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/phys/shapes/CollisionContext;)Lnet/minecraft/world/phys/shapes/VoxelShape;");
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

        public boolean is(TagKey<Block> tag, Predicate<BlockBehaviour.BlockStateBase> predicate) {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$BlockStateBase.is:(Lnet/minecraft/tags/TagKey;Ljava/util/function/Predicate;)Z");
        }

        public boolean hasBlockEntity() {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$BlockStateBase.hasBlockEntity:()Z");
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

        private boolean requiresCorrectToolForDrops;

        private BlockBehaviour.StateArgumentPredicate<EntityType<?>> isValidSpawn;

        private BlockBehaviour.StatePredicate isRedstoneConductor;

        private BlockBehaviour.StatePredicate isSuffocating;

        private BlockBehaviour.StatePredicate isViewBlocking;

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

        // Pumpkin divergence: real body. Accepted and dropped -- occlusion is a client-side rendering concern.
        // The chain must return `this` for the mod's next call to land.
        public BlockBehaviour.Properties noOcclusion() {
            return this;
        }

        // Pumpkin divergence: real body. Accepted and dropped -- Pumpkin has no per-block sound table yet.
        // The chain must return `this` for the mod's next call to land.
        public BlockBehaviour.Properties sound(SoundType soundType) {
            return this;
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

        // Pumpkin divergence: real body. Recorded, and dropped at the sink for the same
        // reason strength is -- BlockSpec models requires_tool and cannot be told.
        public BlockBehaviour.Properties requiresCorrectToolForDrops() {
            this.pumpkinRequiresTool = true;
            return this;
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
