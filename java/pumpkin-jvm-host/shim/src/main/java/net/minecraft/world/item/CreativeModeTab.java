package net.minecraft.world.item;

import java.util.Collection;
import java.util.function.Supplier;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.level.ItemLike;
import dev.pumpkin.shim.Unimplemented;

public class CreativeModeTab {

    private CreativeModeTab(CreativeModeTab.Row row, int column, CreativeModeTab.Type type, Component displayName, Supplier<ItemStack> iconGenerator, CreativeModeTab.DisplayItemsGenerator displayItemsGenerator, net.minecraft.resources.Identifier scrollerSpriteLocation, boolean hasSearchBar, int searchBarWidth, net.minecraft.resources.Identifier tabsImage, int labelColor, java.util.List<net.minecraft.resources.Identifier> tabsBefore, java.util.List<net.minecraft.resources.Identifier> tabsAfter) {
    }

    protected CreativeModeTab(CreativeModeTab.Builder builder) {
    }

    public static CreativeModeTab.Builder builder() {
        throw Unimplemented.forMember("net/minecraft/world/item/CreativeModeTab.builder:()Lnet/minecraft/world/item/CreativeModeTab$Builder;");
    }

    public static CreativeModeTab.Builder builder(CreativeModeTab.Row row, int column) {
        throw Unimplemented.forMember("net/minecraft/world/item/CreativeModeTab.builder:(Lnet/minecraft/world/item/CreativeModeTab$Row;I)Lnet/minecraft/world/item/CreativeModeTab$Builder;");
    }

    public Component getDisplayName() {
        throw Unimplemented.forMember("net/minecraft/world/item/CreativeModeTab.getDisplayName:()Lnet/minecraft/network/chat/Component;");
    }

    public CreativeModeTab.Type getType() {
        throw Unimplemented.forMember("net/minecraft/world/item/CreativeModeTab.getType:()Lnet/minecraft/world/item/CreativeModeTab$Type;");
    }

    public boolean contains(ItemStack stack) {
        throw Unimplemented.forMember("net/minecraft/world/item/CreativeModeTab.contains:(Lnet/minecraft/world/item/ItemStack;)Z");
    }

    public static class Builder {

        public Builder(CreativeModeTab.Row row, int column) {
        }

        public CreativeModeTab.Builder title(Component displayName) {
            throw Unimplemented.forMember("net/minecraft/world/item/CreativeModeTab$Builder.title:(Lnet/minecraft/network/chat/Component;)Lnet/minecraft/world/item/CreativeModeTab$Builder;");
        }

        public CreativeModeTab.Builder icon(Supplier<ItemStack> iconGenerator) {
            throw Unimplemented.forMember("net/minecraft/world/item/CreativeModeTab$Builder.icon:(Ljava/util/function/Supplier;)Lnet/minecraft/world/item/CreativeModeTab$Builder;");
        }

        public CreativeModeTab.Builder displayItems(CreativeModeTab.DisplayItemsGenerator displayItemsGenerator) {
            throw Unimplemented.forMember("net/minecraft/world/item/CreativeModeTab$Builder.displayItems:(Lnet/minecraft/world/item/CreativeModeTab$DisplayItemsGenerator;)Lnet/minecraft/world/item/CreativeModeTab$Builder;");
        }

        public CreativeModeTab.Builder displayItems(Collection<? extends net.minecraft.core.Holder<? extends ItemLike>> collection) {
            throw Unimplemented.forMember("net/minecraft/world/item/CreativeModeTab$Builder.displayItems:(Ljava/util/Collection;)Lnet/minecraft/world/item/CreativeModeTab$Builder;");
        }

        public CreativeModeTab build() {
            throw Unimplemented.forMember("net/minecraft/world/item/CreativeModeTab$Builder.build:()Lnet/minecraft/world/item/CreativeModeTab;");
        }

        public Builder() {
        }
    }

    public interface DisplayItemsGenerator {

        void accept(CreativeModeTab.ItemDisplayParameters parameters, CreativeModeTab.Output output);
    }

    private static class ItemDisplayBuilder implements CreativeModeTab.Output {

        public ItemDisplayBuilder(CreativeModeTab tab, FeatureFlagSet featureFlagSet) {
        }

        public void accept(ItemStack stack, CreativeModeTab.TabVisibility tabVisibility) {
            throw Unimplemented.forMember("net/minecraft/world/item/CreativeModeTab$ItemDisplayBuilder.accept:(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/CreativeModeTab$TabVisibility;)V");
        }

        protected ItemDisplayBuilder() {
        }
    }

    public record ItemDisplayParameters(FeatureFlagSet enabledFeatures, boolean hasPermissions, HolderLookup.Provider holders) {
    }

    public interface Output {

        void accept(final ItemStack stack, final CreativeModeTab.TabVisibility tabVisibility);

        default void accept(ItemStack stack) {
            throw Unimplemented.forMember("net/minecraft/world/item/CreativeModeTab$Output.accept:(Lnet/minecraft/world/item/ItemStack;)V");
        }

        default void accept(ItemLike item, CreativeModeTab.TabVisibility tabVisibility) {
            throw Unimplemented.forMember("net/minecraft/world/item/CreativeModeTab$Output.accept:(Lnet/minecraft/world/level/ItemLike;Lnet/minecraft/world/item/CreativeModeTab$TabVisibility;)V");
        }

        default void accept(ItemLike item) {
            throw Unimplemented.forMember("net/minecraft/world/item/CreativeModeTab$Output.accept:(Lnet/minecraft/world/level/ItemLike;)V");
        }
    }

    public enum Row {

        TOP, BOTTOM
    }

    public enum TabVisibility {

        PARENT_AND_SEARCH_TABS, PARENT_TAB_ONLY, SEARCH_TAB_ONLY
    }

    public enum Type {

        CATEGORY, INVENTORY, HOTBAR, SEARCH
    }

    public CreativeModeTab() {
    }
}
