package net.minecraft.world.item.component;

import java.util.List;
import java.util.function.Consumer;
import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import dev.pumpkin.shim.Unimplemented;

public record ItemAttributeModifiers(List<ItemAttributeModifiers.Entry> modifiers) {

    public static ItemAttributeModifiers.Builder builder() {
        throw Unimplemented.forMember("net/minecraft/world/item/component/ItemAttributeModifiers.builder:()Lnet/minecraft/world/item/component/ItemAttributeModifiers$Builder;");
    }

    public static class Builder {

        protected Builder() {
        }

        public ItemAttributeModifiers.Builder add(Holder<Attribute> attribute, AttributeModifier modifier, EquipmentSlotGroup slot) {
            throw Unimplemented.forMember("net/minecraft/world/item/component/ItemAttributeModifiers$Builder.add:(Lnet/minecraft/core/Holder;Lnet/minecraft/world/entity/ai/attributes/AttributeModifier;Lnet/minecraft/world/entity/EquipmentSlotGroup;)Lnet/minecraft/world/item/component/ItemAttributeModifiers$Builder;");
        }

        public ItemAttributeModifiers.Builder add(Holder<Attribute> attribute, AttributeModifier modifier, EquipmentSlotGroup slot, ItemAttributeModifiers.Display display) {
            throw Unimplemented.forMember("net/minecraft/world/item/component/ItemAttributeModifiers$Builder.add:(Lnet/minecraft/core/Holder;Lnet/minecraft/world/entity/ai/attributes/AttributeModifier;Lnet/minecraft/world/entity/EquipmentSlotGroup;Lnet/minecraft/world/item/component/ItemAttributeModifiers$Display;)Lnet/minecraft/world/item/component/ItemAttributeModifiers$Builder;");
        }

        public ItemAttributeModifiers build() {
            throw Unimplemented.forMember("net/minecraft/world/item/component/ItemAttributeModifiers$Builder.build:()Lnet/minecraft/world/item/component/ItemAttributeModifiers;");
        }
    }

    public interface Display {

        ItemAttributeModifiers.Display.Type type();

        void apply(Consumer<Component> consumer, Player player, Holder<Attribute> attribute, AttributeModifier modifier);

        record Default() implements ItemAttributeModifiers.Display {

            public ItemAttributeModifiers.Display.Type type() {
                throw Unimplemented.forMember("net/minecraft/world/item/component/ItemAttributeModifiers$Display$Default.type:()Lnet/minecraft/world/item/component/ItemAttributeModifiers$Display$Type;");
            }

            public void apply(Consumer<Component> consumer, Player player, Holder<Attribute> attribute, AttributeModifier modifier) {
                throw Unimplemented.forMember("net/minecraft/world/item/component/ItemAttributeModifiers$Display$Default.apply:(Ljava/util/function/Consumer;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/core/Holder;Lnet/minecraft/world/entity/ai/attributes/AttributeModifier;)V");
            }
        }

        record Hidden() implements ItemAttributeModifiers.Display {

            public ItemAttributeModifiers.Display.Type type() {
                throw Unimplemented.forMember("net/minecraft/world/item/component/ItemAttributeModifiers$Display$Hidden.type:()Lnet/minecraft/world/item/component/ItemAttributeModifiers$Display$Type;");
            }

            public void apply(Consumer<Component> consumer, Player player, Holder<Attribute> attribute, AttributeModifier modifier) {
                throw Unimplemented.forMember("net/minecraft/world/item/component/ItemAttributeModifiers$Display$Hidden.apply:(Ljava/util/function/Consumer;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/core/Holder;Lnet/minecraft/world/entity/ai/attributes/AttributeModifier;)V");
            }
        }

        record OverrideText(Component component) implements ItemAttributeModifiers.Display {

            public ItemAttributeModifiers.Display.Type type() {
                throw Unimplemented.forMember("net/minecraft/world/item/component/ItemAttributeModifiers$Display$OverrideText.type:()Lnet/minecraft/world/item/component/ItemAttributeModifiers$Display$Type;");
            }

            public void apply(Consumer<Component> consumer, Player player, Holder<Attribute> attribute, AttributeModifier modifier) {
                throw Unimplemented.forMember("net/minecraft/world/item/component/ItemAttributeModifiers$Display$OverrideText.apply:(Ljava/util/function/Consumer;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/core/Holder;Lnet/minecraft/world/entity/ai/attributes/AttributeModifier;)V");
            }
        }

        enum Type implements StringRepresentable {

            DEFAULT, HIDDEN, OVERRIDE;

            public String getSerializedName() {
                throw Unimplemented.forMember("net/minecraft/world/item/component/ItemAttributeModifiers$Display$Type.getSerializedName:()Ljava/lang/String;");
            }

            private int id() {
                throw Unimplemented.forMember("net/minecraft/world/item/component/ItemAttributeModifiers$Display$Type.id:()I");
            }

            private StreamCodec<RegistryFriendlyByteBuf, ? extends ItemAttributeModifiers.Display> streamCodec() {
                throw Unimplemented.forMember("net/minecraft/world/item/component/ItemAttributeModifiers$Display$Type.streamCodec:()Lnet/minecraft/network/codec/StreamCodec;");
            }
        }
    }

    public record Entry(Holder<Attribute> attribute, AttributeModifier modifier, EquipmentSlotGroup slot, ItemAttributeModifiers.Display display) {

        public Entry(Holder<Attribute> attribute, AttributeModifier modifier, EquipmentSlotGroup slot) {
            this((Holder<Attribute>) null, (AttributeModifier) null, (EquipmentSlotGroup) null, (ItemAttributeModifiers.Display) null);
        }

        public boolean matches(Holder<Attribute> attribute, Identifier id) {
            throw Unimplemented.forMember("net/minecraft/world/item/component/ItemAttributeModifiers$Entry.matches:(Lnet/minecraft/core/Holder;Lnet/minecraft/resources/Identifier;)Z");
        }
    }
}
