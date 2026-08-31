package net.minecraft.core.dispenser;

import net.minecraft.world.item.ItemStack;
import dev.pumpkin.shim.Unimplemented;

public class EquipmentDispenseItemBehavior extends DefaultDispenseItemBehavior {

    protected ItemStack execute(BlockSource source, ItemStack dispensed) {
        throw Unimplemented.forMember("net/minecraft/core/dispenser/EquipmentDispenseItemBehavior.execute:(Lnet/minecraft/core/dispenser/BlockSource;Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/world/item/ItemStack;");
    }

    public static boolean dispenseEquipment(BlockSource source, ItemStack dispensed) {
        throw Unimplemented.forMember("net/minecraft/core/dispenser/EquipmentDispenseItemBehavior.dispenseEquipment:(Lnet/minecraft/core/dispenser/BlockSource;Lnet/minecraft/world/item/ItemStack;)Z");
    }

    public EquipmentDispenseItemBehavior() {
    }
}
