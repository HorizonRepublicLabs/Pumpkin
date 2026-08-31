package net.minecraft.world.inventory;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import dev.pumpkin.shim.Unimplemented;

public abstract class ItemCombinerMenu extends AbstractContainerMenu {

    protected abstract void onTake(Player player, ItemStack carried);

    protected abstract boolean isValidBlock(BlockState state);

    public ItemCombinerMenu(MenuType<?> menuType, int containerId, Inventory inventory, ContainerLevelAccess access, ItemCombinerMenuSlotDefinition itemInputSlots) {
    }

    public abstract void createResult();

    public void slotsChanged(Container container) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/ItemCombinerMenu.slotsChanged:(Lnet/minecraft/world/Container;)V");
    }

    public void removed(Player player) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/ItemCombinerMenu.removed:(Lnet/minecraft/world/entity/player/Player;)V");
    }

    public boolean stillValid(Player player) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/ItemCombinerMenu.stillValid:(Lnet/minecraft/world/entity/player/Player;)Z");
    }

    public ItemStack quickMoveStack(Player player, int slotIndex) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/ItemCombinerMenu.quickMoveStack:(Lnet/minecraft/world/entity/player/Player;I)Lnet/minecraft/world/item/ItemStack;");
    }

    public int getResultSlot() {
        throw Unimplemented.forMember("net/minecraft/world/inventory/ItemCombinerMenu.getResultSlot:()I");
    }

    public ItemCombinerMenu() {
    }
}
