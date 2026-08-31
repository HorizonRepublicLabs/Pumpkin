package net.neoforged.neoforge.registries;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.resources.ResourceKey;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.common.crafting.IngredientType;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.StructureModifier;
import net.neoforged.neoforge.fluids.FluidType;
import net.minecraft.resources.Identifier;
import dev.pumpkin.shim.Stubs;
import dev.pumpkin.shim.Unimplemented;

public class NeoForgeRegistries {

    public static final Registry<MapCodec<? extends BiomeModifier>> BIOME_MODIFIER_SERIALIZERS = Stubs.of(Registry.class, "net/minecraft/core/Registry", java.util.Map.of("key", ResourceKey.createRegistryKey(Identifier.fromNamespaceAndPath("neoforge", "biome_modifier_serializers"))));

    public static final Registry<IngredientType<?>> INGREDIENT_TYPES = Stubs.of(Registry.class, "net/minecraft/core/Registry", java.util.Map.of("key", ResourceKey.createRegistryKey(Identifier.fromNamespaceAndPath("neoforge", "ingredient_serializer"))));

    public static final Registry<MapCodec<? extends ICondition>> CONDITION_SERIALIZERS = Stubs.of(Registry.class, "net/minecraft/core/Registry", java.util.Map.of("key", ResourceKey.createRegistryKey(Identifier.fromNamespaceAndPath("neoforge", "condition_codecs"))));

    public static final class Keys {

        public static final ResourceKey<Registry<EntityDataSerializer<?>>> ENTITY_DATA_SERIALIZERS = ResourceKey.createRegistryKey(
                net.minecraft.resources.Identifier.fromNamespaceAndPath("neoforge", "entity_data_serializers"));

        public static final ResourceKey<Registry<MapCodec<? extends BiomeModifier>>> BIOME_MODIFIER_SERIALIZERS = ResourceKey.createRegistryKey(
                net.minecraft.resources.Identifier.fromNamespaceAndPath("neoforge", "biome_modifier_serializers"));

        public static final ResourceKey<Registry<MapCodec<? extends StructureModifier>>> STRUCTURE_MODIFIER_SERIALIZERS = ResourceKey.createRegistryKey(
                net.minecraft.resources.Identifier.fromNamespaceAndPath("neoforge", "structure_modifier_serializers"));

        public static final ResourceKey<Registry<FluidType>> FLUID_TYPES = ResourceKey.createRegistryKey(
                net.minecraft.resources.Identifier.fromNamespaceAndPath("neoforge", "fluid_type"));

        public static final ResourceKey<Registry<MapCodec<? extends ICondition>>> CONDITION_CODECS = ResourceKey.createRegistryKey(
                net.minecraft.resources.Identifier.fromNamespaceAndPath("neoforge", "condition_codecs"));

        public static final ResourceKey<Registry<AttachmentType<?>>> ATTACHMENT_TYPES = ResourceKey.createRegistryKey(
                net.minecraft.resources.Identifier.fromNamespaceAndPath("neoforge", "attachment_types"));

        public static final ResourceKey<Registry<BiomeModifier>> BIOME_MODIFIERS = ResourceKey.createRegistryKey(
                net.minecraft.resources.Identifier.fromNamespaceAndPath("neoforge", "biome_modifier"));

        public static final ResourceKey<Registry<StructureModifier>> STRUCTURE_MODIFIERS = ResourceKey.createRegistryKey(
                net.minecraft.resources.Identifier.fromNamespaceAndPath("neoforge", "structure_modifier"));

        public Keys() {
        }

        // Pumpkin divergence: no throwing initializer -- names from NeoForge's table.
    }

    public NeoForgeRegistries() {
    }
}
