package net.neoforged.neoforge.transfer.item;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.transfer.CombinedResourceHandler;
import net.neoforged.neoforge.transfer.ResourceHandler;
import dev.pumpkin.shim.Unimplemented;

public class LivingEntityEquipmentWrapper {

    public static ResourceHandler<ItemResource> of(LivingEntity entity, EquipmentSlot.Type equipmentType) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/LivingEntityEquipmentWrapper.of:(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/entity/EquipmentSlot$Type;)Lnet/neoforged/neoforge/transfer/ResourceHandler;");
    }

    public static ResourceHandler<ItemResource> of(LivingEntity entity, EquipmentSlot equipmentSlot) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/LivingEntityEquipmentWrapper.of:(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/entity/EquipmentSlot;)Lnet/neoforged/neoforge/transfer/ResourceHandler;");
    }

    private LivingEntityEquipmentWrapper(LivingEntity entity) {
    }

    private class EquipmentTypeWrapper extends CombinedResourceHandler<ItemResource> {

        EquipmentTypeWrapper(SlotWrapper... handlers) {
        }

        protected EquipmentTypeWrapper() {
        }
    }

    private class SlotWrapper extends ItemStackResourceHandler {

        private SlotWrapper(EquipmentSlot slot) {
        }

        protected ItemStack getStack() {
            throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/LivingEntityEquipmentWrapper$SlotWrapper.getStack:()Lnet/minecraft/world/item/ItemStack;");
        }

        protected void setStack(ItemStack stack) {
            throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/LivingEntityEquipmentWrapper$SlotWrapper.setStack:(Lnet/minecraft/world/item/ItemStack;)V");
        }

        protected boolean isValid(ItemResource resource) {
            throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/LivingEntityEquipmentWrapper$SlotWrapper.isValid:(Lnet/neoforged/neoforge/transfer/item/ItemResource;)Z");
        }

        protected int getCapacity(ItemResource resource) {
            throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/LivingEntityEquipmentWrapper$SlotWrapper.getCapacity:(Lnet/neoforged/neoforge/transfer/item/ItemResource;)I");
        }

        protected void onRootCommit(ItemStack originalState) {
            throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/LivingEntityEquipmentWrapper$SlotWrapper.onRootCommit:(Lnet/minecraft/world/item/ItemStack;)V");
        }

        public String toString() {
            throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/LivingEntityEquipmentWrapper$SlotWrapper.toString:()Ljava/lang/String;");
        }

        protected SlotWrapper() {
        }
    }

    public LivingEntityEquipmentWrapper() {
    }
}
