package net.minecraft.core.registries;

import com.mojang.serialization.MapCodec;
import net.minecraft.advancements.triggers.CriterionTrigger;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.predicates.DataComponentPredicate;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.heightproviders.HeightProviderType;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;

public class Registries {

    public static final ResourceKey<Registry<BlockEntityType<?>>> BLOCK_ENTITY_TYPE = pumpkinRegistryKey("block_entity_type");

    public static final ResourceKey<Registry<Block>> BLOCK = pumpkinRegistryKey("block");

    public static final ResourceKey<Registry<CreativeModeTab>> CREATIVE_MODE_TAB = pumpkinRegistryKey("creative_mode_tab");

    public static final ResourceKey<Registry<DataComponentPredicate.Type<?>>> DATA_COMPONENT_PREDICATE_TYPE = pumpkinRegistryKey("data_component_predicate_type");

    public static final ResourceKey<Registry<DataComponentType<?>>> DATA_COMPONENT_TYPE = pumpkinRegistryKey("data_component_type");

    public static final ResourceKey<Registry<EntityType<?>>> ENTITY_TYPE = pumpkinRegistryKey("entity_type");

    public static final ResourceKey<Registry<Feature<?>>> FEATURE = pumpkinRegistryKey("worldgen/feature");

    public static final ResourceKey<Registry<Fluid>> FLUID = pumpkinRegistryKey("fluid");

    public static final ResourceKey<Registry<GameEvent>> GAME_EVENT = pumpkinRegistryKey("game_event");

    public static final ResourceKey<Registry<HeightProviderType<?>>> HEIGHT_PROVIDER_TYPE = pumpkinRegistryKey("height_provider_type");

    public static final ResourceKey<Registry<MapCodec<? extends IntProvider>>> INT_PROVIDER_TYPE = pumpkinRegistryKey("int_provider_type");

    public static final ResourceKey<Registry<Item>> ITEM = pumpkinRegistryKey("item");

    public static final ResourceKey<Registry<MapCodec<? extends LootItemFunction>>> LOOT_FUNCTION_TYPE = pumpkinRegistryKey("loot_function_type");

    public static final ResourceKey<Registry<MenuType<?>>> MENU = pumpkinRegistryKey("menu");

    public static final ResourceKey<Registry<MobEffect>> MOB_EFFECT = pumpkinRegistryKey("mob_effect");

    public static final ResourceKey<Registry<ParticleType<?>>> PARTICLE_TYPE = pumpkinRegistryKey("particle_type");

    public static final ResourceKey<Registry<PlacementModifierType<?>>> PLACEMENT_MODIFIER_TYPE = pumpkinRegistryKey("worldgen/placement_modifier_type");

    public static final ResourceKey<Registry<RecipeDisplay.Type<?>>> RECIPE_DISPLAY = pumpkinRegistryKey("recipe_display");

    public static final ResourceKey<Registry<RecipeSerializer<?>>> RECIPE_SERIALIZER = pumpkinRegistryKey("recipe_serializer");

    public static final ResourceKey<Registry<RecipeType<?>>> RECIPE_TYPE = pumpkinRegistryKey("recipe_type");

    public static final ResourceKey<Registry<SlotDisplay.Type<?>>> SLOT_DISPLAY = pumpkinRegistryKey("slot_display");

    public static final ResourceKey<Registry<SoundEvent>> SOUND_EVENT = pumpkinRegistryKey("sound_event");

    public static final ResourceKey<Registry<Biome>> BIOME = pumpkinRegistryKey("worldgen/biome");

    public static final ResourceKey<Registry<DamageType>> DAMAGE_TYPE = pumpkinRegistryKey("damage_type");

    public static final ResourceKey<Registry<DimensionType>> DIMENSION_TYPE = pumpkinRegistryKey("dimension_type");

    public static final ResourceKey<Registry<Enchantment>> ENCHANTMENT = pumpkinRegistryKey("enchantment");

    public static final ResourceKey<Registry<PlacedFeature>> PLACED_FEATURE = pumpkinRegistryKey("worldgen/placed_feature");

    public static final ResourceKey<Registry<Structure>> STRUCTURE = pumpkinRegistryKey("worldgen/structure");

    public static final ResourceKey<Registry<CriterionTrigger<?>>> TRIGGER_TYPE = pumpkinRegistryKey("trigger_type");

    public static final ResourceKey<Registry<Recipe<?>>> RECIPE = pumpkinRegistryKey("recipe");

    public Registries() {
    }

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
        return ResourceKey.createRegistryKey(net.minecraft.resources.Identifier.fromNamespaceAndPath("minecraft", name));
    }
}
