package net.minecraft.world.entity.player;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import java.util.function.Predicate;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.Container;
import net.minecraft.world.ItemStackWithSlot;
import net.minecraft.world.Nameable;
import net.minecraft.world.entity.EntityEquipment;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.ValueInput;
import dev.pumpkin.shim.Unimplemented;

public class Inventory implements Container, Nameable {

    public static final Int2ObjectMap<EquipmentSlot> EQUIPMENT_SLOT_MAPPING = null;

    public final Player player = null;

    public Inventory(Player player, EntityEquipment equipment) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Inventory.<init>:(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/entity/EntityEquipment;)V");
    }

    public ItemStack getSelectedItem() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Inventory.getSelectedItem:()Lnet/minecraft/world/item/ItemStack;");
    }

    public NonNullList<ItemStack> getNonEquipmentItems() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Inventory.getNonEquipmentItems:()Lnet/minecraft/core/NonNullList;");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Inventory.tick:()V");
    }

    public boolean add(ItemStack itemStack) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Inventory.add:(Lnet/minecraft/world/item/ItemStack;)Z");
    }

    public boolean add(int slot, ItemStack itemStack) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Inventory.add:(ILnet/minecraft/world/item/ItemStack;)Z");
    }

    public void placeItemBackInInventory(ItemStack itemStack) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Inventory.placeItemBackInInventory:(Lnet/minecraft/world/item/ItemStack;)V");
    }

    public void placeItemBackInInventory(ItemStack itemStack, boolean shouldSendSetSlotPacket) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Inventory.placeItemBackInInventory:(Lnet/minecraft/world/item/ItemStack;Z)V");
    }

    public ItemStack removeItem(int slot, int count) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Inventory.removeItem:(II)Lnet/minecraft/world/item/ItemStack;");
    }

    public ItemStack removeItemNoUpdate(int slot) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Inventory.removeItemNoUpdate:(I)Lnet/minecraft/world/item/ItemStack;");
    }

    public void setItem(int slot, ItemStack itemStack) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Inventory.setItem:(ILnet/minecraft/world/item/ItemStack;)V");
    }

    public void load(ValueInput.TypedInputList<ItemStackWithSlot> input) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Inventory.load:(Lnet/minecraft/world/level/storage/ValueInput$TypedInputList;)V");
    }

    public int getContainerSize() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Inventory.getContainerSize:()I");
    }

    public boolean isEmpty() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Inventory.isEmpty:()Z");
    }

    public ItemStack getItem(int slot) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Inventory.getItem:(I)Lnet/minecraft/world/item/ItemStack;");
    }

    public Component getName() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Inventory.getName:()Lnet/minecraft/network/chat/Component;");
    }

    public void setChanged() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Inventory.setChanged:()V");
    }

    public boolean stillValid(Player player) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Inventory.stillValid:(Lnet/minecraft/world/entity/player/Player;)Z");
    }

    public boolean contains(ItemStack searchStack) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Inventory.contains:(Lnet/minecraft/world/item/ItemStack;)Z");
    }

    public boolean contains(TagKey<Item> tag) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Inventory.contains:(Lnet/minecraft/tags/TagKey;)Z");
    }

    public boolean contains(Predicate<ItemStack> predicate) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Inventory.contains:(Ljava/util/function/Predicate;)Z");
    }

    public void clearContent() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Inventory.clearContent:()V");
    }

    public Inventory() {
    }
}
