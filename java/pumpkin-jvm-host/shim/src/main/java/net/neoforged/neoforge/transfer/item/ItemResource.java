package net.neoforged.neoforge.transfer.item;

import java.util.function.Predicate;
import java.util.function.Supplier;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.transfer.resource.DataComponentHolderResource;
import dev.pumpkin.shim.Unimplemented;

public final class ItemResource implements DataComponentHolderResource<Item> {

    public static final ItemResource EMPTY = null;

    public static ItemResource of(ItemStack stack) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemResource.of:(Lnet/minecraft/world/item/ItemStack;)Lnet/neoforged/neoforge/transfer/item/ItemResource;");
    }

    public static ItemResource of(ItemStackTemplate template) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemResource.of:(Lnet/minecraft/world/item/ItemStackTemplate;)Lnet/neoforged/neoforge/transfer/item/ItemResource;");
    }

    public static ItemResource of(ItemLike item) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemResource.of:(Lnet/minecraft/world/level/ItemLike;)Lnet/neoforged/neoforge/transfer/item/ItemResource;");
    }

    public static ItemResource of(ItemLike item, DataComponentPatch patch) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemResource.of:(Lnet/minecraft/world/level/ItemLike;Lnet/minecraft/core/component/DataComponentPatch;)Lnet/neoforged/neoforge/transfer/item/ItemResource;");
    }

    public static ItemResource of(Holder<Item> holder) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemResource.of:(Lnet/minecraft/core/Holder;)Lnet/neoforged/neoforge/transfer/item/ItemResource;");
    }

    public static ItemResource of(Holder<Item> holder, DataComponentPatch patch) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemResource.of:(Lnet/minecraft/core/Holder;Lnet/minecraft/core/component/DataComponentPatch;)Lnet/neoforged/neoforge/transfer/item/ItemResource;");
    }

    private ItemResource(ItemStack stack) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemResource.<init>:(Lnet/minecraft/world/item/ItemStack;)V");
    }

    public Item value() {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemResource.value:()Lnet/minecraft/world/item/Item;");
    }

    public Item getItem() {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemResource.getItem:()Lnet/minecraft/world/item/Item;");
    }

    public Holder<Item> typeHolder() {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemResource.typeHolder:()Lnet/minecraft/core/Holder;");
    }

    public boolean isEmpty() {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemResource.isEmpty:()Z");
    }

    public boolean matches(ItemStack stack) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemResource.matches:(Lnet/minecraft/world/item/ItemStack;)Z");
    }

    public boolean matches(ItemStackTemplate template) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemResource.matches:(Lnet/minecraft/world/item/ItemStackTemplate;)Z");
    }

    public boolean is(ItemLike item) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemResource.is:(Lnet/minecraft/world/level/ItemLike;)Z");
    }

    public boolean test(Predicate<ItemStack> predicate) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemResource.test:(Ljava/util/function/Predicate;)Z");
    }

    public boolean isComponentsPatchEmpty() {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemResource.isComponentsPatchEmpty:()Z");
    }

    public ItemResource withMergedPatch(DataComponentPatch patch) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemResource.withMergedPatch:(Lnet/minecraft/core/component/DataComponentPatch;)Lnet/neoforged/neoforge/transfer/item/ItemResource;");
    }

    public <D> ItemResource with(DataComponentType<D> type, D data) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemResource.with:(Lnet/minecraft/core/component/DataComponentType;Ljava/lang/Object;)Lnet/neoforged/neoforge/transfer/item/ItemResource;");
    }

    public <D> ItemResource with(Supplier<? extends DataComponentType<D>> type, D data) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemResource.with:(Ljava/util/function/Supplier;Ljava/lang/Object;)Lnet/neoforged/neoforge/transfer/item/ItemResource;");
    }

    public ItemResource without(DataComponentType<?> type) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemResource.without:(Lnet/minecraft/core/component/DataComponentType;)Lnet/neoforged/neoforge/transfer/item/ItemResource;");
    }

    public ItemResource without(Supplier<? extends DataComponentType<?>> type) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemResource.without:(Ljava/util/function/Supplier;)Lnet/neoforged/neoforge/transfer/item/ItemResource;");
    }

    public DataComponentMap getComponents() {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemResource.getComponents:()Lnet/minecraft/core/component/DataComponentMap;");
    }

    public DataComponentPatch getComponentsPatch() {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemResource.getComponentsPatch:()Lnet/minecraft/core/component/DataComponentPatch;");
    }

    public ItemStack toStack(int count) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemResource.toStack:(I)Lnet/minecraft/world/item/ItemStack;");
    }

    public ItemStack toStack() {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemResource.toStack:()Lnet/minecraft/world/item/ItemStack;");
    }

    public int getMaxStackSize() {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemResource.getMaxStackSize:()I");
    }

    public Component getHoverName() {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemResource.getHoverName:()Lnet/minecraft/network/chat/Component;");
    }

    public boolean equals(Object obj) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemResource.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemResource.hashCode:()I");
    }

    public String toString() {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemResource.toString:()Ljava/lang/String;");
    }

    public ItemResource() {
    }
}
