package net.minecraft.core.registries;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.levelgen.feature.Feature;

public class Registries {

    public static final ResourceKey<Registry<BlockEntityType<?>>> BLOCK_ENTITY_TYPE = pumpkinRegistryKey("block_entity_type");

    public static final ResourceKey<Registry<Block>> BLOCK = pumpkinRegistryKey("block");

    public static final ResourceKey<Registry<CreativeModeTab>> CREATIVE_MODE_TAB = pumpkinRegistryKey("creative_mode_tab");

    public static final ResourceKey<Registry<DataComponentType<?>>> DATA_COMPONENT_TYPE = pumpkinRegistryKey("data_component_type");

    public static final ResourceKey<Registry<EntityType<?>>> ENTITY_TYPE = pumpkinRegistryKey("entity_type");

    public static final ResourceKey<Registry<Feature<?>>> FEATURE = pumpkinRegistryKey("worldgen/feature");

    public static final ResourceKey<Registry<Item>> ITEM = pumpkinRegistryKey("item");

    public static final ResourceKey<Registry<MenuType<?>>> MENU = pumpkinRegistryKey("menu");

    public static final ResourceKey<Registry<RecipeSerializer<?>>> RECIPE_SERIALIZER = pumpkinRegistryKey("recipe_serializer");

    public static final ResourceKey<Registry<RecipeType<?>>> RECIPE_TYPE = pumpkinRegistryKey("recipe_type");

    public static final ResourceKey<Registry<Enchantment>> ENCHANTMENT = pumpkinRegistryKey("enchantment");

    public static final ResourceKey<Registry<Recipe<?>>> RECIPE = pumpkinRegistryKey("recipe");

    // Pumpkin divergence from the generated shim: every key above is initialised, and the
    // throwing static initializer the pruner writes for a constants-holder is gone.
    //
    // The pruner treats Registries as a HOLDER -- a class of static finals whose real
    // initializers call registry code the shim does not have -- and makes touching it fail
    // loudly. That is the right default and the wrong answer here: a registry key is a pair
    // of names and nothing else, so the shim can supply the true value rather than a stub.
    // Without it, reading Registries.BLOCK throws during a mod's static initialisation and
    // no mod ever reaches its first registration.
    //
    // Re-apply by hand after any regeneration -- grep for "Pumpkin divergence".
    private static <T> ResourceKey<Registry<T>> pumpkinRegistryKey(String name) {
        return ResourceKey.createRegistryKey(Identifier.fromNamespaceAndPath("minecraft", name));
    }
}
