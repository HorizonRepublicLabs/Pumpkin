package net.neoforged.neoforge.registries;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.common.crafting.IngredientType;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import dev.pumpkin.shim.Stubs;

public class NeoForgeRegistries {

    public static final Registry<MapCodec<? extends BiomeModifier>> BIOME_MODIFIER_SERIALIZERS = Stubs.of(Registry.class, "net/minecraft/core/Registry", java.util.Map.of("key", ResourceKey.createRegistryKey(Identifier.fromNamespaceAndPath("neoforge", "biome_modifier_serializers"))));

    public static final Registry<IngredientType<?>> INGREDIENT_TYPES = Stubs.of(Registry.class, "net/minecraft/core/Registry", java.util.Map.of("key", ResourceKey.createRegistryKey(Identifier.fromNamespaceAndPath("neoforge", "ingredient_serializer"))));

    public static final Registry<MapCodec<? extends ICondition>> CONDITION_SERIALIZERS = Stubs.of(Registry.class, "net/minecraft/core/Registry", java.util.Map.of("key", ResourceKey.createRegistryKey(Identifier.fromNamespaceAndPath("neoforge", "condition_codecs"))));

    public static final class Keys {

        protected Keys() {
        }
    }

    protected NeoForgeRegistries() {
    }
}
