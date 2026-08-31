package net.neoforged.neoforge.event;

import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.neoforged.bus.api.Event;
import dev.pumpkin.shim.Unimplemented;

public class ItemAttributeModifierEvent extends Event {

    public ItemAttributeModifierEvent(ItemStack stack, ItemAttributeModifiers defaultModifiers) {
    }

    public ItemStack getItemStack() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/ItemAttributeModifierEvent.getItemStack:()Lnet/minecraft/world/item/ItemStack;");
    }

    public boolean addModifier(Holder<Attribute> attribute, AttributeModifier modifier, EquipmentSlotGroup slot) {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/ItemAttributeModifierEvent.addModifier:(Lnet/minecraft/core/Holder;Lnet/minecraft/world/entity/ai/attributes/AttributeModifier;Lnet/minecraft/world/entity/EquipmentSlotGroup;)Z");
    }

    public void replaceModifier(Holder<Attribute> attribute, AttributeModifier modifier, EquipmentSlotGroup slot) {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/ItemAttributeModifierEvent.replaceModifier:(Lnet/minecraft/core/Holder;Lnet/minecraft/world/entity/ai/attributes/AttributeModifier;Lnet/minecraft/world/entity/EquipmentSlotGroup;)V");
    }

    public ItemAttributeModifiers build() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/ItemAttributeModifierEvent.build:()Lnet/minecraft/world/item/component/ItemAttributeModifiers;");
    }

    private static class ItemAttributeModifiersBuilder {

        ItemAttributeModifiersBuilder(ItemAttributeModifiers defaultModifiers) {
        }

        void clear() {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/ItemAttributeModifierEvent$ItemAttributeModifiersBuilder.clear:()V");
        }

        public ItemAttributeModifiers build() {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/ItemAttributeModifierEvent$ItemAttributeModifiersBuilder.build:()Lnet/minecraft/world/item/component/ItemAttributeModifiers;");
        }

        private static record Key(Holder<Attribute> attr, Identifier id) {
        }

        protected ItemAttributeModifiersBuilder() {
        }
    }

    public ItemAttributeModifierEvent() {
    }
}
