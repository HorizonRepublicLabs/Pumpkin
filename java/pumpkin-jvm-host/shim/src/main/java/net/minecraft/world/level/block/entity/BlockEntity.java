package net.minecraft.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.TypedInstance;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.nbt.CompoundTag;
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

    protected Level level;

    // Pumpkin divergence: the position is kept; getBlockPos answers with it.
    private BlockPos pumpkinPosition;

    public BlockEntity(BlockEntityType<?> type, BlockPos worldPosition, BlockState blockState) {
        this.pumpkinPosition = worldPosition;
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

    // Pumpkin divergence: the base writes vanilla bookkeeping (components) the shim does
    // not model; a subclass's own state is what persistence carries, and it calls super
    // first. Accepting quietly here is what lets that state through.
    protected void loadAdditional(ValueInput input) {
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

    protected static void setChanged(Level level, BlockPos worldPosition, BlockState blockState) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BlockEntity.setChanged:(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)V");
    }

    public BlockPos getBlockPos() {
        return pumpkinPosition;
    }

    public BlockState getBlockState() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BlockEntity.getBlockState:()Lnet/minecraft/world/level/block/state/BlockState;");
    }

    public Packet<ClientGamePacketListener> getUpdatePacket() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BlockEntity.getUpdatePacket:()Lnet/minecraft/network/protocol/Packet;");
    }

    public boolean isRemoved() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BlockEntity.isRemoved:()Z");
    }

    public void preRemoveSideEffects(BlockPos pos, BlockState state) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BlockEntity.preRemoveSideEffects:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)V");
    }

    public BlockEntityType<?> getType() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BlockEntity.getType:()Lnet/minecraft/world/level/block/entity/BlockEntityType;");
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

    public DataComponentMap components() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BlockEntity.components:()Lnet/minecraft/core/component/DataComponentMap;");
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
