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

        public static final ResourceKey<Registry<EntityDataSerializer<?>>> ENTITY_DATA_SERIALIZERS = null;

        public static final ResourceKey<Registry<MapCodec<? extends BiomeModifier>>> BIOME_MODIFIER_SERIALIZERS = null;

        public static final ResourceKey<Registry<MapCodec<? extends StructureModifier>>> STRUCTURE_MODIFIER_SERIALIZERS = null;

        public static final ResourceKey<Registry<FluidType>> FLUID_TYPES = null;

        public static final ResourceKey<Registry<MapCodec<? extends ICondition>>> CONDITION_CODECS = null;

        public static final ResourceKey<Registry<AttachmentType<?>>> ATTACHMENT_TYPES = null;

        public static final ResourceKey<Registry<BiomeModifier>> BIOME_MODIFIERS = null;

        public static final ResourceKey<Registry<StructureModifier>> STRUCTURE_MODIFIERS = null;

        public Keys() {
        }

        static {
            if (true) {
                throw Unimplemented.forMember("net/neoforged/neoforge/registries/NeoForgeRegistries$Keys");
            }
        }
    }

    public NeoForgeRegistries() {
    }
}
