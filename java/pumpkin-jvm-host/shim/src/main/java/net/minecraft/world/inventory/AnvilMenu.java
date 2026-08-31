package net.minecraft.world.inventory;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import dev.pumpkin.shim.Unimplemented;

public class AnvilMenu extends ItemCombinerMenu {

    public AnvilMenu(int containerId, Inventory inventory) {
    }

    public AnvilMenu(int containerId, Inventory inventory, ContainerLevelAccess access) {
    }

    protected boolean isValidBlock(BlockState state) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/AnvilMenu.isValidBlock:(Lnet/minecraft/world/level/block/state/BlockState;)Z");
    }

    protected boolean mayPickup(Player player, boolean hasItem) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/AnvilMenu.mayPickup:(Lnet/minecraft/world/entity/player/Player;Z)Z");
    }

    protected void onTake(Player player, ItemStack carried) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/AnvilMenu.onTake:(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/item/ItemStack;)V");
    }

    public final void createResult() {
        throw Unimplemented.forMember("net/minecraft/world/inventory/AnvilMenu.createResult:()V");
    }

    public boolean setItemName(String name) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/AnvilMenu.setItemName:(Ljava/lang/String;)Z");
    }

    public int getCost() {
        throw Unimplemented.forMember("net/minecraft/world/inventory/AnvilMenu.getCost:()I");
    }

    public AnvilMenu() {
    }
}
