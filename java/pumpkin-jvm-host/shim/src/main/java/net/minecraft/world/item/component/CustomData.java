package net.minecraft.world.item.component;

import java.util.function.Consumer;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import dev.pumpkin.shim.Unimplemented;

public final class CustomData {

    private CustomData(CompoundTag tag) {
        throw Unimplemented.forMember("net/minecraft/world/item/component/CustomData.<init>:(Lnet/minecraft/nbt/CompoundTag;)V");
    }

    public static CustomData of(CompoundTag tag) {
        throw Unimplemented.forMember("net/minecraft/world/item/component/CustomData.of:(Lnet/minecraft/nbt/CompoundTag;)Lnet/minecraft/world/item/component/CustomData;");
    }

    public static void update(DataComponentType<CustomData> component, ItemStack itemStack, Consumer<CompoundTag> consumer) {
        throw Unimplemented.forMember("net/minecraft/world/item/component/CustomData.update:(Lnet/minecraft/core/component/DataComponentType;Lnet/minecraft/world/item/ItemStack;Ljava/util/function/Consumer;)V");
    }

    public static void set(DataComponentType<CustomData> component, ItemStack itemStack, CompoundTag tag) {
        throw Unimplemented.forMember("net/minecraft/world/item/component/CustomData.set:(Lnet/minecraft/core/component/DataComponentType;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/nbt/CompoundTag;)V");
    }

    public CustomData update(Consumer<CompoundTag> consumer) {
        throw Unimplemented.forMember("net/minecraft/world/item/component/CustomData.update:(Ljava/util/function/Consumer;)Lnet/minecraft/world/item/component/CustomData;");
    }

    public boolean isEmpty() {
        throw Unimplemented.forMember("net/minecraft/world/item/component/CustomData.isEmpty:()Z");
    }

    public CompoundTag copyTag() {
        throw Unimplemented.forMember("net/minecraft/world/item/component/CustomData.copyTag:()Lnet/minecraft/nbt/CompoundTag;");
    }

    public boolean contains(String key) {
        throw Unimplemented.forMember("net/minecraft/world/item/component/CustomData.contains:(Ljava/lang/String;)Z");
    }

    public boolean equals(Object obj) {
        throw Unimplemented.forMember("net/minecraft/world/item/component/CustomData.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/minecraft/world/item/component/CustomData.hashCode:()I");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/world/item/component/CustomData.toString:()Ljava/lang/String;");
    }

    protected CustomData() {
    }
}
