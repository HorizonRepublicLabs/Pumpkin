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

    // Pumpkin divergence: no vanilla counterpart in this form. The generator the builder
    // recorded, if any.
    private CreativeModeTab.DisplayItemsGenerator pumpkinDisplayItemsGenerator;

    /**
     * Runs the tab's display-items generator against a counting output and returns how
     * many entries it produced.
     *
     * <p>The tab is client-side presentation the server never renders, so the entries are
     * not kept. Running the generator is still worth doing: it forces every holder the mod
     * put in its tab to resolve, which catches a broken registration at load time instead
     * of never.
     */
    public int pumpkinRunDisplayItems() {
        if (pumpkinDisplayItemsGenerator == null) {
            return 0;
        }
        final int[] count = {0};
        CreativeModeTab.Output collector = new CreativeModeTab.Output() {
            @Override
            public void accept(ItemStack stack, CreativeModeTab.TabVisibility tabVisibility) {
                count[0]++;
            }

            @Override
            public void accept(ItemStack stack) {
                count[0]++;
            }

            @Override
            public void accept(ItemLike item, CreativeModeTab.TabVisibility tabVisibility) {
                java.util.Objects.requireNonNull(item, "a creative tab accepted a null item");
                count[0]++;
            }

            @Override
            public void accept(ItemLike item) {
                java.util.Objects.requireNonNull(item, "a creative tab accepted a null item");
                count[0]++;
            }
        };
        // The parameters carry facts the server does not have: no feature flags beyond
        // vanilla, no permissions, and a holder provider that throws with a named member
        // if the generator actually reaches for it.
        pumpkinDisplayItemsGenerator.accept(new CreativeModeTab.ItemDisplayParameters(
                FeatureFlagSet.of(), false,
                dev.pumpkin.shim.Stubs.of(net.minecraft.core.HolderLookup.Provider.class,
                        "net/minecraft/core/HolderLookup$Provider")),
                collector);
        return count[0];
    }
    // Pumpkin divergence: real body. A creative tab is client-side presentation Pumpkin
    // never renders; the builder exists so a mod's registration completes, and the tab it
    // yields is inert.
    public static CreativeModeTab.Builder builder() {
        return new Builder(null, 0);
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

        private Identifier backgroundTexture;

        public Builder(CreativeModeTab.Row row, int column) {
        }

        // Pumpkin divergence: accepted and dropped -- client-side presentation.

        public CreativeModeTab.Builder title(Component displayName) {

            return this;

        }

        // Pumpkin divergence: accepted and dropped -- client-side presentation.

        public CreativeModeTab.Builder icon(Supplier<ItemStack> iconGenerator) {

            return this;

        }

        // Pumpkin divergence: recorded, not dropped. The tab itself is client-side
        // presentation, but running the generator at registration proves every holder the
        // mod put in its tab actually resolves -- see pumpkinRunDisplayItems().
        private CreativeModeTab.DisplayItemsGenerator pumpkinDisplayItemsGenerator;

        public CreativeModeTab.Builder displayItems(CreativeModeTab.DisplayItemsGenerator displayItemsGenerator) {
            this.pumpkinDisplayItemsGenerator = displayItemsGenerator;
            return this;
        }

        // Pumpkin divergence: tab presentation, accepted and dropped; chain lives.
        public CreativeModeTab.Builder backgroundTexture(Identifier backgroundTexture) {
            return this;
        }

        // Pumpkin divergence: tab presentation, accepted and dropped; chain lives.
        public CreativeModeTab.Builder withSearchBar() {
            return this;
        }

        // Pumpkin divergence: tab presentation, accepted and dropped; chain lives.
        public CreativeModeTab.Builder withSearchBar(int searchBarWidth) {
            return this;
        }

        // Pumpkin divergence: tab presentation, accepted and dropped; chain lives.
        public CreativeModeTab.Builder withTabFactory(java.util.function.Function<CreativeModeTab.Builder, CreativeModeTab> tabFactory) {
            return this;
        }

        // Pumpkin divergence: tab presentation, accepted and dropped; chain lives.
        public CreativeModeTab.Builder withTabsBefore(net.minecraft.resources.Identifier... tabs) {
            return this;
        }

        public final CreativeModeTab.Builder withTabsBefore(net.minecraft.resources.ResourceKey<CreativeModeTab>... tabs) {
            throw Unimplemented.forMember("net/minecraft/world/item/CreativeModeTab$Builder.withTabsBefore:([Lnet/minecraft/resources/ResourceKey;)Lnet/minecraft/world/item/CreativeModeTab$Builder;");
        }

        // Pumpkin divergence: accepted and dropped -- client-side presentation.

        public CreativeModeTab.Builder displayItems(Collection<? extends net.minecraft.core.Holder<? extends ItemLike>> collection) {

            return this;

        }
        // Pumpkin divergence: real body -- an inert tab that keeps its generator; its
        // vanilla methods still throw.
        public CreativeModeTab build() {
            CreativeModeTab tab = new CreativeModeTab();
            tab.pumpkinDisplayItemsGenerator = pumpkinDisplayItemsGenerator;
            return tab;
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

        // Pumpkin divergence: vanilla body verbatim -- pure delegation.
        default void accept(ItemStack stack) {
            accept(stack, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }

        // Pumpkin divergence: vanilla body verbatim -- pure delegation.
        default void accept(ItemLike item, CreativeModeTab.TabVisibility tabVisibility) {
            accept(new ItemStack(item), tabVisibility);
        }

        // Pumpkin divergence: vanilla body verbatim -- pure delegation.
        default void accept(ItemLike item) {
            accept(new ItemStack(item), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
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
