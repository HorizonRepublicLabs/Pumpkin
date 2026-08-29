package net.minecraft.world.item.crafting.display;

import com.mojang.serialization.MapCodec;
import java.util.List;
import java.util.stream.Stream;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.tags.TagKey;
import net.minecraft.util.context.ContextMap;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.equipment.trim.TrimPattern;
import dev.pumpkin.shim.Unimplemented;

public interface SlotDisplay {

    <T> Stream<T> resolve(ContextMap context, DisplayContentsFactory<T> builder);

    SlotDisplay.Type<? extends SlotDisplay> type();

    default ItemStack resolveForFirstStack(ContextMap context) {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/display/SlotDisplay.resolveForFirstStack:(Lnet/minecraft/util/context/ContextMap;)Lnet/minecraft/world/item/ItemStack;");
    }

    class AnyFuel implements SlotDisplay {

        protected AnyFuel() {
        }

        public SlotDisplay.Type<SlotDisplay.AnyFuel> type() {
            throw Unimplemented.forMember("net/minecraft/world/item/crafting/display/SlotDisplay$AnyFuel.type:()Lnet/minecraft/world/item/crafting/display/SlotDisplay$Type;");
        }

        public String toString() {
            throw Unimplemented.forMember("net/minecraft/world/item/crafting/display/SlotDisplay$AnyFuel.toString:()Ljava/lang/String;");
        }

        public <T> Stream<T> resolve(ContextMap context, DisplayContentsFactory<T> factory) {
            throw Unimplemented.forMember("net/minecraft/world/item/crafting/display/SlotDisplay$AnyFuel.resolve:(Lnet/minecraft/util/context/ContextMap;Lnet/minecraft/world/item/crafting/display/DisplayContentsFactory;)Ljava/util/stream/Stream;");
        }
    }

    record Composite(List<SlotDisplay> contents) implements SlotDisplay {

        public SlotDisplay.Type<SlotDisplay.Composite> type() {
            throw Unimplemented.forMember("net/minecraft/world/item/crafting/display/SlotDisplay$Composite.type:()Lnet/minecraft/world/item/crafting/display/SlotDisplay$Type;");
        }

        public <T> Stream<T> resolve(ContextMap context, DisplayContentsFactory<T> factory) {
            throw Unimplemented.forMember("net/minecraft/world/item/crafting/display/SlotDisplay$Composite.resolve:(Lnet/minecraft/util/context/ContextMap;Lnet/minecraft/world/item/crafting/display/DisplayContentsFactory;)Ljava/util/stream/Stream;");
        }

        public boolean isEnabled(FeatureFlagSet enabledFeatures) {
            throw Unimplemented.forMember("net/minecraft/world/item/crafting/display/SlotDisplay$Composite.isEnabled:(Lnet/minecraft/world/flag/FeatureFlagSet;)Z");
        }
    }

    record DyedSlotDemo(SlotDisplay dye, SlotDisplay target) implements SlotDisplay {

        public SlotDisplay.Type<SlotDisplay.DyedSlotDemo> type() {
            throw Unimplemented.forMember("net/minecraft/world/item/crafting/display/SlotDisplay$DyedSlotDemo.type:()Lnet/minecraft/world/item/crafting/display/SlotDisplay$Type;");
        }

        public <T> Stream<T> resolve(ContextMap context, DisplayContentsFactory<T> factory) {
            throw Unimplemented.forMember("net/minecraft/world/item/crafting/display/SlotDisplay$DyedSlotDemo.resolve:(Lnet/minecraft/util/context/ContextMap;Lnet/minecraft/world/item/crafting/display/DisplayContentsFactory;)Ljava/util/stream/Stream;");
        }
    }

    class Empty implements SlotDisplay {

        public static final SlotDisplay.Empty INSTANCE = null;

        protected Empty() {
        }

        public SlotDisplay.Type<SlotDisplay.Empty> type() {
            throw Unimplemented.forMember("net/minecraft/world/item/crafting/display/SlotDisplay$Empty.type:()Lnet/minecraft/world/item/crafting/display/SlotDisplay$Type;");
        }

        public String toString() {
            throw Unimplemented.forMember("net/minecraft/world/item/crafting/display/SlotDisplay$Empty.toString:()Ljava/lang/String;");
        }

        public <T> Stream<T> resolve(ContextMap context, DisplayContentsFactory<T> factory) {
            throw Unimplemented.forMember("net/minecraft/world/item/crafting/display/SlotDisplay$Empty.resolve:(Lnet/minecraft/util/context/ContextMap;Lnet/minecraft/world/item/crafting/display/DisplayContentsFactory;)Ljava/util/stream/Stream;");
        }
    }

    record ItemSlotDisplay(Holder<Item> item) implements SlotDisplay {

        public ItemSlotDisplay(Item item) {
            this((Holder<Item>) null);
        }

        public SlotDisplay.Type<SlotDisplay.ItemSlotDisplay> type() {
            throw Unimplemented.forMember("net/minecraft/world/item/crafting/display/SlotDisplay$ItemSlotDisplay.type:()Lnet/minecraft/world/item/crafting/display/SlotDisplay$Type;");
        }

        public <T> Stream<T> resolve(ContextMap context, DisplayContentsFactory<T> factory) {
            throw Unimplemented.forMember("net/minecraft/world/item/crafting/display/SlotDisplay$ItemSlotDisplay.resolve:(Lnet/minecraft/util/context/ContextMap;Lnet/minecraft/world/item/crafting/display/DisplayContentsFactory;)Ljava/util/stream/Stream;");
        }

        public boolean isEnabled(FeatureFlagSet enabledFeatures) {
            throw Unimplemented.forMember("net/minecraft/world/item/crafting/display/SlotDisplay$ItemSlotDisplay.isEnabled:(Lnet/minecraft/world/flag/FeatureFlagSet;)Z");
        }
    }

    class ItemStackContentsFactory implements DisplayContentsFactory.ForStacks<ItemStack> {

        public ItemStack forStack(ItemStack stack) {
            throw Unimplemented.forMember("net/minecraft/world/item/crafting/display/SlotDisplay$ItemStackContentsFactory.forStack:(Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/world/item/ItemStack;");
        }

        protected ItemStackContentsFactory() {
        }
    }

    record ItemStackSlotDisplay(ItemStackTemplate stack) implements SlotDisplay {

        public SlotDisplay.Type<SlotDisplay.ItemStackSlotDisplay> type() {
            throw Unimplemented.forMember("net/minecraft/world/item/crafting/display/SlotDisplay$ItemStackSlotDisplay.type:()Lnet/minecraft/world/item/crafting/display/SlotDisplay$Type;");
        }

        public <T> Stream<T> resolve(ContextMap context, DisplayContentsFactory<T> factory) {
            throw Unimplemented.forMember("net/minecraft/world/item/crafting/display/SlotDisplay$ItemStackSlotDisplay.resolve:(Lnet/minecraft/util/context/ContextMap;Lnet/minecraft/world/item/crafting/display/DisplayContentsFactory;)Ljava/util/stream/Stream;");
        }

        public boolean isEnabled(FeatureFlagSet enabledFeatures) {
            throw Unimplemented.forMember("net/minecraft/world/item/crafting/display/SlotDisplay$ItemStackSlotDisplay.isEnabled:(Lnet/minecraft/world/flag/FeatureFlagSet;)Z");
        }
    }

    record OnlyWithComponent(SlotDisplay source, DataComponentType<?> component) implements SlotDisplay {

        public <T> Stream<T> resolve(ContextMap context, DisplayContentsFactory<T> builder) {
            throw Unimplemented.forMember("net/minecraft/world/item/crafting/display/SlotDisplay$OnlyWithComponent.resolve:(Lnet/minecraft/util/context/ContextMap;Lnet/minecraft/world/item/crafting/display/DisplayContentsFactory;)Ljava/util/stream/Stream;");
        }

        public SlotDisplay.Type<SlotDisplay.OnlyWithComponent> type() {
            throw Unimplemented.forMember("net/minecraft/world/item/crafting/display/SlotDisplay$OnlyWithComponent.type:()Lnet/minecraft/world/item/crafting/display/SlotDisplay$Type;");
        }
    }

    record SmithingTrimDemoSlotDisplay(SlotDisplay base, SlotDisplay material, Holder<TrimPattern> pattern) implements SlotDisplay {

        public SlotDisplay.Type<SlotDisplay.SmithingTrimDemoSlotDisplay> type() {
            throw Unimplemented.forMember("net/minecraft/world/item/crafting/display/SlotDisplay$SmithingTrimDemoSlotDisplay.type:()Lnet/minecraft/world/item/crafting/display/SlotDisplay$Type;");
        }

        public <T> Stream<T> resolve(ContextMap context, DisplayContentsFactory<T> factory) {
            throw Unimplemented.forMember("net/minecraft/world/item/crafting/display/SlotDisplay$SmithingTrimDemoSlotDisplay.resolve:(Lnet/minecraft/util/context/ContextMap;Lnet/minecraft/world/item/crafting/display/DisplayContentsFactory;)Ljava/util/stream/Stream;");
        }
    }

    record TagSlotDisplay(TagKey<Item> tag) implements SlotDisplay {

        public SlotDisplay.Type<SlotDisplay.TagSlotDisplay> type() {
            throw Unimplemented.forMember("net/minecraft/world/item/crafting/display/SlotDisplay$TagSlotDisplay.type:()Lnet/minecraft/world/item/crafting/display/SlotDisplay$Type;");
        }

        public <T> Stream<T> resolve(ContextMap context, DisplayContentsFactory<T> factory) {
            throw Unimplemented.forMember("net/minecraft/world/item/crafting/display/SlotDisplay$TagSlotDisplay.resolve:(Lnet/minecraft/util/context/ContextMap;Lnet/minecraft/world/item/crafting/display/DisplayContentsFactory;)Ljava/util/stream/Stream;");
        }
    }

    record Type<T extends SlotDisplay>(MapCodec<T> codec, StreamCodec<RegistryFriendlyByteBuf, T> streamCodec) {
    }

    record WithAnyPotion(SlotDisplay display) implements SlotDisplay {

        public SlotDisplay.Type<SlotDisplay.WithAnyPotion> type() {
            throw Unimplemented.forMember("net/minecraft/world/item/crafting/display/SlotDisplay$WithAnyPotion.type:()Lnet/minecraft/world/item/crafting/display/SlotDisplay$Type;");
        }

        public <T> Stream<T> resolve(ContextMap context, DisplayContentsFactory<T> factory) {
            throw Unimplemented.forMember("net/minecraft/world/item/crafting/display/SlotDisplay$WithAnyPotion.resolve:(Lnet/minecraft/util/context/ContextMap;Lnet/minecraft/world/item/crafting/display/DisplayContentsFactory;)Ljava/util/stream/Stream;");
        }
    }

    record WithRemainder(SlotDisplay input, SlotDisplay remainder) implements SlotDisplay {

        public SlotDisplay.Type<SlotDisplay.WithRemainder> type() {
            throw Unimplemented.forMember("net/minecraft/world/item/crafting/display/SlotDisplay$WithRemainder.type:()Lnet/minecraft/world/item/crafting/display/SlotDisplay$Type;");
        }

        public <T> Stream<T> resolve(ContextMap context, DisplayContentsFactory<T> factory) {
            throw Unimplemented.forMember("net/minecraft/world/item/crafting/display/SlotDisplay$WithRemainder.resolve:(Lnet/minecraft/util/context/ContextMap;Lnet/minecraft/world/item/crafting/display/DisplayContentsFactory;)Ljava/util/stream/Stream;");
        }

        public boolean isEnabled(FeatureFlagSet enabledFeatures) {
            throw Unimplemented.forMember("net/minecraft/world/item/crafting/display/SlotDisplay$WithRemainder.isEnabled:(Lnet/minecraft/world/flag/FeatureFlagSet;)Z");
        }
    }
}
