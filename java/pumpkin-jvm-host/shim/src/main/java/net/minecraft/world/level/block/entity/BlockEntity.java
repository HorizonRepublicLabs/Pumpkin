package net.minecraft.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.TypedInstance;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.ProblemReporter;
import net.minecraft.util.debug.DebugValueSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.common.extensions.IBlockEntityExtension;
import dev.pumpkin.shim.Unimplemented;

public abstract class BlockEntity extends net.neoforged.neoforge.attachment.AttachmentHolder implements DebugValueSource, TypedInstance<BlockEntityType<?>>, IBlockEntityExtension {

    // Pumpkin divergence: real -- the ctor receives it.
    private BlockEntityType<?> type;

    protected Level level;

    // Pumpkin divergence: real -- subclasses read the field directly.
    protected BlockPos worldPosition;

    private DataComponentMap components;

    // Pumpkin divergence: position and state are kept; the getters answer with them.
    private BlockPos pumpkinPosition;

    private BlockState pumpkinBlockState;

    public BlockEntity(BlockEntityType<?> type, BlockPos worldPosition, BlockState blockState) {
        this.type = type;
        this.pumpkinPosition = worldPosition;
        this.worldPosition = worldPosition;
        this.pumpkinBlockState = blockState;
    }

    // Pumpkin divergence: real body over the protected field the bridge sets.
    public Level getLevel() {
        return level;
    }

    // Pumpkin divergence: no vanilla counterpart in this form. The bridge attaches the
    // level when it creates the mod's entity.
    public void pumpkinSetLevel(Level level) {
        this.level = level;
    }

    // Pumpkin divergence: real body -- vanilla stores the level here, and mods
    // override this as their earliest world hook (Mekanism initializes transmitter
    // acceptor caches in it), so the base must be callable.
    public void setLevel(Level level) {
        pumpkinSetLevel(level);
    }

    public boolean hasLevel() {
        return level != null;
    }

    // Pumpkin divergence: the base writes vanilla bookkeeping (components) the shim does
    // not model; a subclass's own state is what persistence carries, and it calls super
    // first. Accepting quietly here is what lets that state through.
    protected void loadAdditional(ValueInput input) {
    }

    public final void loadWithComponents(ValueInput input) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BlockEntity.loadWithComponents:(Lnet/minecraft/world/level/storage/ValueInput;)V");
    }

    protected void saveAdditional(ValueOutput output) {
    }

    public final CompoundTag saveWithFullMetadata(HolderLookup.Provider registries) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BlockEntity.saveWithFullMetadata:(Lnet/minecraft/core/HolderLookup$Provider;)Lnet/minecraft/nbt/CompoundTag;");
    }

    public void saveWithFullMetadata(ValueOutput output) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BlockEntity.saveWithFullMetadata:(Lnet/minecraft/world/level/storage/ValueOutput;)V");
    }

    // Pumpkin divergence: a real dirty flag. The tick bridge reads and clears it to
    // decide whether an entity is worth re-serialising -- ticking machines call this
    // twenty times a second, and serialising unchanged ones would be pure waste.
    private boolean pumpkinChanged;

    public void setChanged() {
        pumpkinChanged = true;
    }

    public boolean pumpkinTakeChanged() {
        boolean changed = pumpkinChanged;
        pumpkinChanged = false;
        return changed;
    }

    // Pumpkin divergence: sets the flag without virtual dispatch. The chunk-dirty
    // fan-out calls this: going through setChanged() would re-enter a mod override
    // that marks the chunk dirty again, and the loop never ends.
    public final void pumpkinMarkChanged() {
        pumpkinChanged = true;
    }

    protected static void setChanged(Level level, BlockPos worldPosition, BlockState blockState) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BlockEntity.setChanged:(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)V");
    }

    public BlockPos getBlockPos() {
        return pumpkinPosition;
    }

    public BlockState getBlockState() {
        if (pumpkinBlockState == null) {
            throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BlockEntity.getBlockState:()Lnet/minecraft/world/level/block/state/BlockState; (entity built without a state)");
        }
        return pumpkinBlockState;
    }

    public Packet<ClientGamePacketListener> getUpdatePacket() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BlockEntity.getUpdatePacket:()Lnet/minecraft/network/protocol/Packet;");
    }

    public boolean isRemoved() {
        return pumpkinRemoved;
    }

    // Pumpkin divergence: real bodies -- vanilla's removed flag is exactly this pair,
    // and mods hook clearRemoved as their joined-the-world signal.
    private boolean pumpkinRemoved;

    public void setRemoved() {
        this.pumpkinRemoved = true;
    }

    public void clearRemoved() {
        this.pumpkinRemoved = false;
    }

    public void preRemoveSideEffects(BlockPos pos, BlockState state) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BlockEntity.preRemoveSideEffects:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)V");
    }

    public boolean triggerEvent(int b0, int b1) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BlockEntity.triggerEvent:(II)Z");
    }

    public BlockEntityType<?> getType() {
        if (type == null) {
            throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BlockEntity.getType:()Lnet/minecraft/world/level/block/entity/BlockEntityType; (entity built without one)");
        }
        return type;
    }

    public CompoundTag getPersistentData() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BlockEntity.getPersistentData:()Lnet/minecraft/nbt/CompoundTag;");
    }

    public final <T> T setData(net.neoforged.neoforge.attachment.AttachmentType<T> type, T data) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BlockEntity.setData:(Lnet/neoforged/neoforge/attachment/AttachmentType;Ljava/lang/Object;)Ljava/lang/Object;");
    }

    public final <T> T removeData(net.neoforged.neoforge.attachment.AttachmentType<T> type) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BlockEntity.removeData:(Lnet/neoforged/neoforge/attachment/AttachmentType;)Ljava/lang/Object;");
    }

    public final void syncData(net.neoforged.neoforge.attachment.AttachmentType<?> type) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BlockEntity.syncData:(Lnet/neoforged/neoforge/attachment/AttachmentType;)V");
    }

    public Holder<BlockEntityType<?>> typeHolder() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BlockEntity.typeHolder:()Lnet/minecraft/core/Holder;");
    }

    public void setBlockState(BlockState blockState) {
        this.pumpkinBlockState = blockState;
    }

    // Pumpkin divergence: the vanilla base reads name/lock/container-loot components;
    // none of those exist in this store, so the base has nothing to apply and the
    // subclass chain (a mod's own override calls super) runs clean.
    protected void applyImplicitComponents(DataComponentGetter components) {
    }

    protected void collectImplicitComponents(DataComponentMap.Builder components) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BlockEntity.collectImplicitComponents:(Lnet/minecraft/core/component/DataComponentMap$Builder;)V");
    }

    public void removeComponentsFromTag(ValueOutput output) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BlockEntity.removeComponentsFromTag:(Lnet/minecraft/world/level/storage/ValueOutput;)V");
    }

    public final DataComponentMap collectComponents() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BlockEntity.collectComponents:()Lnet/minecraft/core/component/DataComponentMap;");
    }

    public DataComponentMap components() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BlockEntity.components:()Lnet/minecraft/core/component/DataComponentMap;");
    }

    public void setComponents(DataComponentMap components) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BlockEntity.setComponents:(Lnet/minecraft/core/component/DataComponentMap;)V");
    }

    // Pumpkin divergence: a saved custom name is a plain string in this store; absent
    // means no custom name, vanilla's null.
    public static Component parseCustomNameSafe(ValueInput input, String name) {
        return input.getString(name)
                .<Component>map(net.minecraft.network.chat.MutableComponent::pumpkinOf)
                .orElse(null);
    }

    public ProblemReporter.PathElement problemPath() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BlockEntity.problemPath:()Lnet/minecraft/util/ProblemReporter$PathElement;");
    }

    public void registerDebugValues(ServerLevel level, DebugValueSource.Registration registration) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BlockEntity.registerDebugValues:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/util/debug/DebugValueSource$Registration;)V");
    }

    private record BlockEntityPathElement(BlockEntity blockEntity) implements ProblemReporter.PathElement {

        public String get() {
            throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BlockEntity$BlockEntityPathElement.get:()Ljava/lang/String;");
        }
    }

    public BlockEntity() {
    }
}
