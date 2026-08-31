package net.minecraft.world.inventory;

import java.util.List;
import java.util.function.Predicate;
import net.minecraft.world.item.ItemStack;
import dev.pumpkin.shim.Unimplemented;

public class ItemCombinerMenuSlotDefinition {

    private ItemCombinerMenuSlotDefinition(List<ItemCombinerMenuSlotDefinition.SlotDefinition> inputSlots, ItemCombinerMenuSlotDefinition.SlotDefinition resultSlot) {
    }

    public static ItemCombinerMenuSlotDefinition.Builder create() {
        throw Unimplemented.forMember("net/minecraft/world/inventory/ItemCombinerMenuSlotDefinition.create:()Lnet/minecraft/world/inventory/ItemCombinerMenuSlotDefinition$Builder;");
    }

    public ItemCombinerMenuSlotDefinition.SlotDefinition getSlot(int index) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/ItemCombinerMenuSlotDefinition.getSlot:(I)Lnet/minecraft/world/inventory/ItemCombinerMenuSlotDefinition$SlotDefinition;");
    }

    public ItemCombinerMenuSlotDefinition.SlotDefinition getResultSlot() {
        throw Unimplemented.forMember("net/minecraft/world/inventory/ItemCombinerMenuSlotDefinition.getResultSlot:()Lnet/minecraft/world/inventory/ItemCombinerMenuSlotDefinition$SlotDefinition;");
    }

    public static class Builder {

        public ItemCombinerMenuSlotDefinition build() {
            throw Unimplemented.forMember("net/minecraft/world/inventory/ItemCombinerMenuSlotDefinition$Builder.build:()Lnet/minecraft/world/inventory/ItemCombinerMenuSlotDefinition;");
        }

        public Builder() {
        }
    }

    public record SlotDefinition(int slotIndex, int x, int y, Predicate<ItemStack> mayPlace) {
    }

    public ItemCombinerMenuSlotDefinition() {
    }
}
