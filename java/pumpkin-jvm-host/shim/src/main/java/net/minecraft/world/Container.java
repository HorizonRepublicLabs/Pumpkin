package net.minecraft.world;

import java.util.Iterator;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.SlotProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.extensions.ContainerExtension;
import dev.pumpkin.shim.Unimplemented;

public interface Container extends Clearable, Iterable<ItemStack>, SlotProvider, ContainerExtension {

    int getContainerSize();

    boolean isEmpty();

    ItemStack getItem(int slot);

    ItemStack removeItem(int slot, int count);

    ItemStack removeItemNoUpdate(int slot);

    void setItem(int slot, ItemStack itemStack);

    void setChanged();

    boolean stillValid(Player player);

    default SlotAccess getSlot(int slot) {
        throw Unimplemented.forMember("net/minecraft/world/Container.getSlot:(I)Lnet/minecraft/world/entity/SlotAccess;");
    }

    default Iterator<ItemStack> iterator() {
        throw Unimplemented.forMember("net/minecraft/world/Container.iterator:()Ljava/util/Iterator;");
    }

    class ContainerIterator implements Iterator<ItemStack> {

        public ContainerIterator(Container container) {
        }

        public boolean hasNext() {
            throw Unimplemented.forMember("net/minecraft/world/Container$ContainerIterator.hasNext:()Z");
        }

        public ItemStack next() {
            throw Unimplemented.forMember("net/minecraft/world/Container$ContainerIterator.next:()Lnet/minecraft/world/item/ItemStack;");
        }

        protected ContainerIterator() {
        }
    }
}
