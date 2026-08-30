package net.minecraft.world.level.block;

import com.mojang.serialization.MapCodec;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import java.util.function.Supplier;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.extensions.IBlockExtension;
import dev.pumpkin.shim.Unimplemented;

public class Block extends BlockBehaviour implements ItemLike, IBlockExtension {

    // Pumpkin divergence from the generated shim: this field, the constructor below and
    // pumpkinTemplate() have no vanilla counterpart in this form. Pumpkin registers a block
    // by copying a vanilla one, so a Block has to remember which vanilla block it was built
    // from; the property builder is where a mod says so. Re-apply by hand after any
    // regeneration -- grep for "Pumpkin divergence".
    private final BlockBehaviour.Properties pumpkinProperties;

    private final Holder.Reference<Block> builtInRegistryHolder = null;

    private BlockState defaultBlockState;

    protected MapCodec<? extends Block> codec() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/Block.codec:()Lcom/mojang/serialization/MapCodec;");
    }

    public static int getId(BlockState blockState) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/Block.getId:(Lnet/minecraft/world/level/block/state/BlockState;)I");
    }

    public static Block byItem(Item item) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/Block.byItem:(Lnet/minecraft/world/item/Item;)Lnet/minecraft/world/level/block/Block;");
    }

    // Pumpkin divergence: real-enough body. A collision shape is geometry Pumpkin never
    // consults -- the server's own collision runs in Rust. Mods build these in statics and
    // hand them back from getShape; an inert instance satisfies both, and its one abstract
    // member throws with a name if anything ever reads the geometry.
    public static VoxelShape box(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        return VoxelShape.pumpkinInert();
    }

    // Pumpkin divergence: real body. Vanilla's constructor builds a state definition and a
    // registry holder; the shim keeps only the one thing registration reads back.
    // Pumpkin divergence: the declared state properties, collected the way vanilla does
    // -- by running createBlockStateDefinition from the constructor. (Vanilla's famous
    // quirk: the subclass override runs before subclass fields initialise; mods are
    // written to survive it.)
    private java.util.List<net.minecraft.world.level.block.state.properties.Property<?>> pumpkinDeclaredProperties = java.util.List.of();

    public java.util.List<net.minecraft.world.level.block.state.properties.Property<?>> pumpkinDeclaredProperties() {
        return pumpkinDeclaredProperties;
    }

    // Pumpkin divergence: the base declaration hook vanilla keeps on BlockBehaviour;
    // the base declares nothing, subclasses add their properties.
    protected void createBlockStateDefinition(
            net.minecraft.world.level.block.state.StateDefinition.Builder<Block, net.minecraft.world.level.block.state.BlockState> builder) {
    }

    public Block(BlockBehaviour.Properties properties) {
        this.pumpkinProperties = properties;
        net.minecraft.world.level.block.state.StateDefinition.Builder<Block, net.minecraft.world.level.block.state.BlockState> builder =
                new net.minecraft.world.level.block.state.StateDefinition.Builder<>(this);
        createBlockStateDefinition(builder);
        this.pumpkinDeclaredProperties = builder.pumpkinProperties();
    }

    // Pumpkin divergence: no vanilla counterpart at all. The vanilla block whose definition
    // Pumpkin copies when it registers this one; DeferredRegister's flush passes it to the
    // native registerBlock.
    public String pumpkinTemplate() {
        return pumpkinProperties.template();
    }

    // Pumpkin divergence: no vanilla counterpart. Set by the registration sinks when this
    // block registers; read back when its BlockItem registers later, so the two can be
    // linked. Null until then -- an unregistered block's item places nothing.
    private String pumpkinRegisteredId;

    public void pumpkinSetRegisteredId(String id) {
        this.pumpkinRegisteredId = id;
    }

    public String pumpkinRegisteredId() {
        return pumpkinRegisteredId;
    }

    // Pumpkin divergence: no vanilla counterpart. The registration sinks read the recorded
    // strength and tool requirement off this on the way to Pumpkin.
    public BlockBehaviour.Properties pumpkinProperties() {
        return pumpkinProperties;
    }

    public void destroy(LevelAccessor level, BlockPos pos, BlockState state) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/Block.destroy:(Lnet/minecraft/world/level/LevelAccessor;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)V");
    }

    public static List<ItemStack> getDrops(BlockState state, ServerLevel level, BlockPos pos, BlockEntity blockEntity) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/Block.getDrops:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/entity/BlockEntity;)Ljava/util/List;");
    }

    public static List<ItemStack> getDrops(BlockState state, ServerLevel level, BlockPos pos, BlockEntity blockEntity, Entity breaker, ItemInstance tool) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/Block.getDrops:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/entity/BlockEntity;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/item/ItemInstance;)Ljava/util/List;");
    }

    public static void popResource(Level level, BlockPos pos, ItemStack itemStack) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/Block.popResource:(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/item/ItemStack;)V");
    }

    private static void popResource(Level level, Supplier<ItemEntity> entityFactory, ItemStack itemStack) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/Block.popResource:(Lnet/minecraft/world/level/Level;Ljava/util/function/Supplier;Lnet/minecraft/world/item/ItemStack;)V");
    }

    public void popExperience(ServerLevel level, BlockPos pos, int amount) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/Block.popExperience:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;I)V");
    }

    public float getExplosionResistance() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/Block.getExplosionResistance:()F");
    }

    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, BlockEntity blockEntity, ItemStack destroyedWith) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/Block.playerDestroy:(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/entity/BlockEntity;Lnet/minecraft/world/item/ItemStack;)V");
    }

    public MutableComponent getName() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/Block.getName:()Lnet/minecraft/network/chat/MutableComponent;");
    }

    // Pumpkin divergence: real body. Lazily built; any() answers this block's default
    // state, which is how machines write their initial LIT=false.
    private StateDefinition<Block, BlockState> pumpkinStateDefinition;

    public StateDefinition<Block, BlockState> getStateDefinition() {
        if (pumpkinStateDefinition == null) {
            pumpkinStateDefinition = new StateDefinition<>();
            pumpkinStateDefinition.pumpkinAny = this::defaultBlockState;
        }
        return pumpkinStateDefinition;
    }

    // Pumpkin divergence: real body. What a block constructor declares as its default is
    // what defaultBlockState() answers from then on.
    protected final void registerDefaultState(BlockState state) {
        this.defaultBlockState = state;
    }

    // Pumpkin divergence: real body. One BlockState per Block, built lazily. The state
    // object is a stub whose methods throw on use -- what a mod needs at registration is
    // for the object to exist and be identity-stable, which this gives it. Wiring states
    // to Pumpkin's real per-state ids is the binding step still ahead.
    public final BlockState defaultBlockState() {
        if (defaultBlockState == null) {
            defaultBlockState = new BlockState();
            defaultBlockState.pumpkinOwner = this;
            // Each declared property starts at its first value, matching how the Rust
            // side numbers states -- index 0 is all-first-values.
            java.util.Map<net.minecraft.world.level.block.state.properties.Property<?>, Comparable<?>> values =
                    new java.util.HashMap<>();
            for (net.minecraft.world.level.block.state.properties.Property<?> property
                    : pumpkinDeclaredProperties()) {
                if (!property.pumpkinPossibleValues.isEmpty()) {
                    values.put(property, property.pumpkinParse.get(property.pumpkinPossibleValues.get(0)));
                }
            }
            defaultBlockState.pumpkinValues = java.util.Map.copyOf(values);
        }
        return defaultBlockState;
    }

    public Item asItem() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/Block.asItem:()Lnet/minecraft/world/item/Item;");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/Block.toString:()Ljava/lang/String;");
    }

    protected Block asBlock() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/Block.asBlock:()Lnet/minecraft/world/level/block/Block;");
    }

    public Holder.Reference<Block> builtInRegistryHolder() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/Block.builtInRegistryHolder:()Lnet/minecraft/core/Holder$Reference;");
    }

    private record ShapePairKey(VoxelShape first, VoxelShape second) {

        public boolean equals(Object o) {
            throw Unimplemented.forMember("net/minecraft/world/level/block/Block$ShapePairKey.equals:(Ljava/lang/Object;)Z");
        }

        public int hashCode() {
            throw Unimplemented.forMember("net/minecraft/world/level/block/Block$ShapePairKey.hashCode:()I");
        }
    }

    @Retention(RetentionPolicy.CLASS)
    @Target(ElementType.TYPE_USE)
    public @interface UpdateFlags {
    }

    // Pumpkin divergence: the generator synthesises an empty no-argument constructor for
    // every class; this one has a final field to assign, so it delegates.
    public Block() {
        this(BlockBehaviour.Properties.of());
    }
}
