package net.minecraft.world.entity;

import java.util.EnumMap;
import net.minecraft.world.item.ItemStack;
import dev.pumpkin.shim.Unimplemented;

public class EntityEquipment {

    private EntityEquipment(EnumMap<EquipmentSlot, ItemStack> items) {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityEquipment.<init>:(Ljava/util/EnumMap;)V");
    }

    public EntityEquipment() {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityEquipment.<init>:()V");
    }

    public ItemStack set(EquipmentSlot slot, ItemStack itemStack) {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityEquipment.set:(Lnet/minecraft/world/entity/EquipmentSlot;Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/world/item/ItemStack;");
    }

    public ItemStack get(EquipmentSlot slot) {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityEquipment.get:(Lnet/minecraft/world/entity/EquipmentSlot;)Lnet/minecraft/world/item/ItemStack;");
    }

    public boolean isEmpty() {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityEquipment.isEmpty:()Z");
    }

    public void clear() {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityEquipment.clear:()V");
    }
}
