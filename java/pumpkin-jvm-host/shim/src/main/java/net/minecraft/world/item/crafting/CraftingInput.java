package net.minecraft.world.item.crafting;

import java.util.List;
import net.minecraft.world.entity.player.StackedItemContents;
import net.minecraft.world.item.ItemStack;
import dev.pumpkin.shim.Unimplemented;

public class CraftingInput implements RecipeInput {

    public static final CraftingInput EMPTY = null;

    private final int width = 0;

    private final int height = 0;

    private final List<ItemStack> items = null;

    private final StackedItemContents stackedContents = null;

    private final int ingredientCount = 0;

    private CraftingInput(int width, int height, List<ItemStack> items) {
    }

    // Pumpkin divergence: real bodies -- an input really carries its grid. This is what
    // a machine hands to Recipe.matches; nothing here is behaviour, only storage.
    private int pumpkinWidth;

    private int pumpkinHeight;

    private List<ItemStack> pumpkinItems = List.of();

    public static CraftingInput of(int width, int height, List<ItemStack> items) {
        CraftingInput input = new CraftingInput();
        input.pumpkinWidth = width;
        input.pumpkinHeight = height;
        input.pumpkinItems = items;
        return input;
    }

    public static CraftingInput.Positioned ofPositioned(int width, int height, List<ItemStack> items) {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/CraftingInput.ofPositioned:(IILjava/util/List;)Lnet/minecraft/world/item/crafting/CraftingInput$Positioned;");
    }

    public ItemStack getItem(int index) {
        return pumpkinItems.get(index);
    }

    public ItemStack getItem(int x, int y) {
        return pumpkinItems.get(x + y * pumpkinWidth);
    }

    public int size() {
        return pumpkinItems.size();
    }

    public boolean isEmpty() {
        return pumpkinItems.stream().allMatch(ItemStack::isEmpty);
    }

    public StackedItemContents stackedContents() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/CraftingInput.stackedContents:()Lnet/minecraft/world/entity/player/StackedItemContents;");
    }

    public List<ItemStack> items() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/CraftingInput.items:()Ljava/util/List;");
    }

    // Pumpkin divergence: vanilla body -- how many slots actually hold something.
    public int ingredientCount() {
        int count = 0;
        for (ItemStack stack : pumpkinItems) {
            if (!stack.isEmpty()) {
                count++;
            }
        }
        return count;
    }

    public int width() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/CraftingInput.width:()I");
    }

    public int height() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/CraftingInput.height:()I");
    }

    public boolean equals(Object obj) {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/CraftingInput.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/CraftingInput.hashCode:()I");
    }

    public record Positioned(CraftingInput input, int left, int top) {

        public static final CraftingInput.Positioned EMPTY = null;
    }

    public CraftingInput() {
    }
}
