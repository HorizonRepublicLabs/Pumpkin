package net.neoforged.neoforge.common.extensions;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.world.level.storage.ValueInput;
import net.neoforged.neoforge.model.data.ModelData;
import dev.pumpkin.shim.Unimplemented;

public interface IBlockEntityExtension {

    default void onDataPacket(Connection net, ValueInput valueInput) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IBlockEntityExtension.onDataPacket:(Lnet/minecraft/network/Connection;Lnet/minecraft/world/level/storage/ValueInput;)V");
    }

    default void handleUpdateTag(ValueInput input) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IBlockEntityExtension.handleUpdateTag:(Lnet/minecraft/world/level/storage/ValueInput;)V");
    }

    CompoundTag getPersistentData();

    default void onChunkUnloaded() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IBlockEntityExtension.onChunkUnloaded:()V");
    }

    default void onLoad() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IBlockEntityExtension.onLoad:()V");
    }

    default void requestModelDataUpdate() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IBlockEntityExtension.requestModelDataUpdate:()V");
    }

    default ModelData getModelData() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IBlockEntityExtension.getModelData:()Lnet/neoforged/neoforge/model/data/ModelData;");
    }

    default void invalidateCapabilities() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IBlockEntityExtension.invalidateCapabilities:()V");
    }
}
