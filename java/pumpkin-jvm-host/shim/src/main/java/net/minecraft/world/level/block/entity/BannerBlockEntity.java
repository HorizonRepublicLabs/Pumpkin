package net.minecraft.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Nameable;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import dev.pumpkin.shim.Unimplemented;

public class BannerBlockEntity extends BlockEntity implements Nameable {

    public BannerBlockEntity(BlockPos worldPosition, BlockState blockState) {
    }

    public BannerBlockEntity(BlockPos worldPosition, BlockState blockState, DyeColor color) {
    }

    public Component getName() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BannerBlockEntity.getName:()Lnet/minecraft/network/chat/Component;");
    }

    public Component getCustomName() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BannerBlockEntity.getCustomName:()Lnet/minecraft/network/chat/Component;");
    }

    protected void saveAdditional(ValueOutput output) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BannerBlockEntity.saveAdditional:(Lnet/minecraft/world/level/storage/ValueOutput;)V");
    }

    protected void loadAdditional(ValueInput input) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BannerBlockEntity.loadAdditional:(Lnet/minecraft/world/level/storage/ValueInput;)V");
    }

    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BannerBlockEntity.getUpdateTag:(Lnet/minecraft/core/HolderLookup$Provider;)Lnet/minecraft/nbt/CompoundTag;");
    }

    public ItemStack getItem() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BannerBlockEntity.getItem:()Lnet/minecraft/world/item/ItemStack;");
    }

    protected void applyImplicitComponents(DataComponentGetter components) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BannerBlockEntity.applyImplicitComponents:(Lnet/minecraft/core/component/DataComponentGetter;)V");
    }

    protected void collectImplicitComponents(DataComponentMap.Builder components) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BannerBlockEntity.collectImplicitComponents:(Lnet/minecraft/core/component/DataComponentMap$Builder;)V");
    }

    public void removeComponentsFromTag(ValueOutput output) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BannerBlockEntity.removeComponentsFromTag:(Lnet/minecraft/world/level/storage/ValueOutput;)V");
    }

    public BannerBlockEntity() {
    }
}
