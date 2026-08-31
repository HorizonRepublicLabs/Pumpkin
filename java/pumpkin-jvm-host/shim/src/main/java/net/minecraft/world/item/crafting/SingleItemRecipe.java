package net.minecraft.world.item.crafting;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.level.Level;
import dev.pumpkin.shim.Unimplemented;

public abstract class SingleItemRecipe implements Recipe<SingleRecipeInput> {

    // Pumpkin divergence: the recipe really carries its input and its result
    // ("namespace:path:count"); matches/assemble answer from them.
    Ingredient pumpkinInput;

    String pumpkinResult;

    public SingleItemRecipe(Recipe.CommonInfo commonInfo, Ingredient input, ItemStackTemplate result) {
        this.pumpkinInput = input;
    }

    public abstract RecipeSerializer<? extends SingleItemRecipe> getSerializer();

    public abstract RecipeType<? extends SingleItemRecipe> getType();

    public boolean matches(SingleRecipeInput input, Level level) {
        return pumpkinInput != null && pumpkinInput.test(input.item());
    }

    public boolean showNotification() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/SingleItemRecipe.showNotification:()Z");
    }

    public Ingredient input() {
        if (pumpkinInput == null) {
            throw Unimplemented.forMember("net/minecraft/world/item/crafting/SingleItemRecipe.input:()Lnet/minecraft/world/item/crafting/Ingredient; (recipe built without one)");
        }
        return pumpkinInput;
    }

    // Pumpkin divergence: NeoForge access-transforms this public; built from the
    // carried result fact, the same holder shape the template codec produces.
    @SuppressWarnings("unchecked")
    public ItemStackTemplate result() {
        if (pumpkinResult == null) {
            throw Unimplemented.forMember("net/minecraft/world/item/crafting/SingleItemRecipe.result:()Lnet/minecraft/world/item/ItemStackTemplate; (recipe built without one)");
        }
        int colon = pumpkinResult.lastIndexOf(':');
        int count = Integer.parseInt(pumpkinResult.substring(colon + 1));
        net.minecraft.world.item.ItemStack stack = dev.pumpkin.bridge.PumpkinInteractions
                .pumpkinBuildStack(pumpkinResult.substring(0, colon), count);
        net.minecraft.core.Holder<net.minecraft.world.item.Item> holder =
                (net.minecraft.core.Holder<net.minecraft.world.item.Item>) dev.pumpkin.shim.Stubs.of(
                        net.minecraft.core.Holder.class, "net/minecraft/core/Holder",
                        java.util.Map.of("value", stack.getItem()));
        return new ItemStackTemplate(holder, count, null);
    }

    public PlacementInfo placementInfo() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/SingleItemRecipe.placementInfo:()Lnet/minecraft/world/item/crafting/PlacementInfo;");
    }

    public ItemStack pumpkinAssemble() {
        if (pumpkinResult == null) {
            throw Unimplemented.forMember("net/minecraft/world/item/crafting/SingleItemRecipe.assemble (recipe built without a result)");
        }
        int colon = pumpkinResult.lastIndexOf(':');
        return dev.pumpkin.bridge.PumpkinInteractions.pumpkinBuildStack(
                pumpkinResult.substring(0, colon), Integer.parseInt(pumpkinResult.substring(colon + 1)));
    }

    public ItemStack assemble(SingleRecipeInput input) {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/SingleItemRecipe.assemble:(Lnet/minecraft/world/item/crafting/SingleRecipeInput;)Lnet/minecraft/world/item/ItemStack;");
    }

    public interface Factory<T extends SingleItemRecipe> {

        T create(Recipe.CommonInfo commonInfo, Ingredient ingredient, ItemStackTemplate result);
    }

    public SingleItemRecipe() {
    }
}
