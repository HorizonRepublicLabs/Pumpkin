package net.minecraft.world.item.crafting;

import java.util.List;
import net.minecraft.world.entity.player.StackedItemContents;
import net.minecraft.world.item.ItemStack;
import dev.pumpkin.shim.Unimplemented;

public class CraftingInput implements RecipeInput {

    public static final CraftingInput EMPTY = null;

    private final List<ItemStack> items = null;

    private final StackedItemContents stackedContents = null;

    private final int ingredientCount = 0;

    private CraftingInput(int width, int height, List<ItemStack> items) {
    }

    public static CraftingInput of(int width, int height, List<ItemStack> items) {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/CraftingInput.of:(IILjava/util/List;)Lnet/minecraft/world/item/crafting/CraftingInput;");
    }

    public ItemStack getItem(int index) {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/CraftingInput.getItem:(I)Lnet/minecraft/world/item/ItemStack;");
    }

    public ItemStack getItem(int x, int y) {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/CraftingInput.getItem:(II)Lnet/minecraft/world/item/ItemStack;");
    }

    public int size() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/CraftingInput.size:()I");
    }

    public boolean isEmpty() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/CraftingInput.isEmpty:()Z");
    }

    public StackedItemContents stackedContents() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/CraftingInput.stackedContents:()Lnet/minecraft/world/entity/player/StackedItemContents;");
    }

    public List<ItemStack> items() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/CraftingInput.items:()Ljava/util/List;");
    }

    public int ingredientCount() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/CraftingInput.ingredientCount:()I");
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
    }

    public CraftingInput() {
    }
}
