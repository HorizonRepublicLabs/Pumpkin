package net.neoforged.neoforge.transfer.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.transfer.transaction.SnapshotJournal;
import net.neoforged.neoforge.transfer.transaction.TransactionContext;
import dev.pumpkin.shim.Unimplemented;

public final class PlayerInventoryWrapper extends VanillaContainerWrapper {

    public static PlayerInventoryWrapper of(Player player) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/PlayerInventoryWrapper.of:(Lnet/minecraft/world/entity/player/Player;)Lnet/neoforged/neoforge/transfer/item/PlayerInventoryWrapper;");
    }

    public static PlayerInventoryWrapper of(Inventory inventory) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/PlayerInventoryWrapper.of:(Lnet/minecraft/world/entity/player/Inventory;)Lnet/neoforged/neoforge/transfer/item/PlayerInventoryWrapper;");
    }

    PlayerInventoryWrapper(Inventory inventory) {
    }

    void resize() {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/PlayerInventoryWrapper.resize:()V");
    }

    void onRootCommit() {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/PlayerInventoryWrapper.onRootCommit:()V");
    }

    public ResourceHandler<ItemResource> getSlot(int slot) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/PlayerInventoryWrapper.getSlot:(I)Lnet/neoforged/neoforge/transfer/ResourceHandler;");
    }

    public ResourceHandler<ItemResource> getHandSlot(InteractionHand hand) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/PlayerInventoryWrapper.getHandSlot:(Lnet/minecraft/world/InteractionHand;)Lnet/neoforged/neoforge/transfer/ResourceHandler;");
    }

    public ResourceHandler<ItemResource> getHandSlots() {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/PlayerInventoryWrapper.getHandSlots:()Lnet/neoforged/neoforge/transfer/ResourceHandler;");
    }

    public ResourceHandler<ItemResource> getMainSlots() {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/PlayerInventoryWrapper.getMainSlots:()Lnet/neoforged/neoforge/transfer/ResourceHandler;");
    }

    public int insert(ItemResource resource, int amount, TransactionContext transaction) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/PlayerInventoryWrapper.insert:(Lnet/neoforged/neoforge/transfer/item/ItemResource;ILnet/neoforged/neoforge/transfer/transaction/TransactionContext;)I");
    }

    public void drop(ItemResource resource, int amount, boolean dropAround, boolean includeThrowerName, TransactionContext transaction) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/PlayerInventoryWrapper.drop:(Lnet/neoforged/neoforge/transfer/item/ItemResource;IZZLnet/neoforged/neoforge/transfer/transaction/TransactionContext;)V");
    }

    public String toString() {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/PlayerInventoryWrapper.toString:()Ljava/lang/String;");
    }

    private class DroppedItems extends SnapshotJournal<Integer> {

        protected Integer createSnapshot() {
            throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/PlayerInventoryWrapper$DroppedItems.createSnapshot:()Ljava/lang/Integer;");
        }

        protected void revertToSnapshot(Integer snapshot) {
            throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/PlayerInventoryWrapper$DroppedItems.revertToSnapshot:(Ljava/lang/Integer;)V");
        }

        protected void onRootCommit(Integer originalState) {
            throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/PlayerInventoryWrapper$DroppedItems.onRootCommit:(Ljava/lang/Integer;)V");
        }

        private record DropInfo(ItemResource resource, int amount, boolean dropAround, boolean includeThrowerName) {
        }

        protected DroppedItems() {
        }
    }

    private class ArmorSlotWrapper extends SlotWrapper {

        ArmorSlotWrapper(int index, EquipmentSlot slot) {
        }

        protected boolean isValid(ItemResource resource) {
            throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/PlayerInventoryWrapper$ArmorSlotWrapper.isValid:(Lnet/neoforged/neoforge/transfer/item/ItemResource;)Z");
        }

        protected int getCapacity(ItemResource resource) {
            throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/PlayerInventoryWrapper$ArmorSlotWrapper.getCapacity:(Lnet/neoforged/neoforge/transfer/item/ItemResource;)I");
        }

        public int extract(int index, ItemResource resource, int amount, TransactionContext transaction) {
            throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/PlayerInventoryWrapper$ArmorSlotWrapper.extract:(ILnet/neoforged/neoforge/transfer/item/ItemResource;ILnet/neoforged/neoforge/transfer/transaction/TransactionContext;)I");
        }

        protected ArmorSlotWrapper() {
        }
    }

    public PlayerInventoryWrapper() {
    }
}
