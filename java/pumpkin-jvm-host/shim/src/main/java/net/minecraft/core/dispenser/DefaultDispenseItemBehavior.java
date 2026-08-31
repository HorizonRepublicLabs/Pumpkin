package net.minecraft.core.dispenser;

import net.minecraft.world.item.ItemStack;
import dev.pumpkin.shim.Unimplemented;

public class DefaultDispenseItemBehavior implements DispenseItemBehavior {

    public final ItemStack dispense(BlockSource source, ItemStack dispensed) {
        throw Unimplemented.forMember("net/minecraft/core/dispenser/DefaultDispenseItemBehavior.dispense:(Lnet/minecraft/core/dispenser/BlockSource;Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/world/item/ItemStack;");
    }

    protected ItemStack execute(BlockSource source, ItemStack dispensed) {
        throw Unimplemented.forMember("net/minecraft/core/dispenser/DefaultDispenseItemBehavior.execute:(Lnet/minecraft/core/dispenser/BlockSource;Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/world/item/ItemStack;");
    }

    protected ItemStack consumeWithRemainder(BlockSource source, ItemStack dispensed, ItemStack remainder) {
        throw Unimplemented.forMember("net/minecraft/core/dispenser/DefaultDispenseItemBehavior.consumeWithRemainder:(Lnet/minecraft/core/dispenser/BlockSource;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/world/item/ItemStack;");
    }

    public DefaultDispenseItemBehavior() {
    }
}
