package net.neoforged.neoforge.common;

import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.material.Fluid;
import dev.pumpkin.shim.Unimplemented;

public class Tags {

    public static class Blocks {

        private static TagKey<Block> tag(String name) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/Tags$Blocks.tag:(Ljava/lang/String;)Lnet/minecraft/tags/TagKey;");
        }

        public Blocks() {
        }
    }

    public static class EntityTypes {

        // Pumpkin divergence: the real key, name read from NeoForge's own source.
        public static final TagKey<EntityType<?>> TELEPORTING_NOT_SUPPORTED = net.minecraft.tags.TagKey.create(
                net.minecraft.core.registries.Registries.ENTITY_TYPE,
                net.minecraft.resources.Identifier.fromNamespaceAndPath("c", "teleporting_not_supported"));

        private static TagKey<EntityType<?>> tag(String name) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/Tags$EntityTypes.tag:(Ljava/lang/String;)Lnet/minecraft/tags/TagKey;");
        }

        public EntityTypes() {
        }

        // Pumpkin divergence: no throwing initializer -- the keys above are real.
    }

    public static class Items {

        // Pumpkin divergence: the real key, name read from NeoForge's own source.
        public static final TagKey<Item> GEMS_LAPIS = net.minecraft.tags.TagKey.create(
                net.minecraft.core.registries.Registries.ITEM,
                net.minecraft.resources.Identifier.fromNamespaceAndPath("c", "gems/lapis"));

        // Pumpkin divergence: the real key, name read from NeoForge's own source.
        public static final TagKey<Item> ORES = net.minecraft.tags.TagKey.create(
                net.minecraft.core.registries.Registries.ITEM,
                net.minecraft.resources.Identifier.fromNamespaceAndPath("c", "ores"));

        // Pumpkin divergence: the real key, name read from NeoForge's own source.
        public static final TagKey<Item> ORES_COPPER = net.minecraft.tags.TagKey.create(
                net.minecraft.core.registries.Registries.ITEM,
                net.minecraft.resources.Identifier.fromNamespaceAndPath("c", "ores/copper"));

        // Pumpkin divergence: the real key, name read from NeoForge's own source.
        public static final TagKey<Item> ORES_GOLD = net.minecraft.tags.TagKey.create(
                net.minecraft.core.registries.Registries.ITEM,
                net.minecraft.resources.Identifier.fromNamespaceAndPath("c", "ores/gold"));

        // Pumpkin divergence: the real key, name read from NeoForge's own source.
        public static final TagKey<Item> ORES_IRON = net.minecraft.tags.TagKey.create(
                net.minecraft.core.registries.Registries.ITEM,
                net.minecraft.resources.Identifier.fromNamespaceAndPath("c", "ores/iron"));

        private static TagKey<Item> tag(String name) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/Tags$Items.tag:(Ljava/lang/String;)Lnet/minecraft/tags/TagKey;");
        }

        public Items() {
        }

        // Pumpkin divergence: no throwing initializer -- the keys above are real.
    }

    public static class Fluids {

        // Pumpkin divergence: the real key, name read from NeoForge's own source.
        public static final TagKey<Fluid> GASEOUS = net.minecraft.tags.TagKey.create(
                net.minecraft.core.registries.Registries.FLUID,
                net.minecraft.resources.Identifier.fromNamespaceAndPath("c", "gaseous"));

        // Pumpkin divergence: the real key, name read from NeoForge's own source.
        public static final TagKey<Fluid> HIDDEN_FROM_RECIPE_VIEWERS = net.minecraft.tags.TagKey.create(
                net.minecraft.core.registries.Registries.FLUID,
                net.minecraft.resources.Identifier.fromNamespaceAndPath("c", "hidden_from_recipe_viewers"));

        private static TagKey<Fluid> tag(String name) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/Tags$Fluids.tag:(Ljava/lang/String;)Lnet/minecraft/tags/TagKey;");
        }

        public Fluids() {
        }

        // Pumpkin divergence: no throwing initializer -- the keys above are real.
    }

    public static class Enchantments {

        private static TagKey<Enchantment> tag(String name) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/Tags$Enchantments.tag:(Ljava/lang/String;)Lnet/minecraft/tags/TagKey;");
        }

        public Enchantments() {
        }
    }

    public static class Potions {

        private static TagKey<Potion> tag(String name) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/Tags$Potions.tag:(Ljava/lang/String;)Lnet/minecraft/tags/TagKey;");
        }

        public Potions() {
        }
    }

    public static class Biomes {

        private static TagKey<Biome> tag(String name) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/Tags$Biomes.tag:(Ljava/lang/String;)Lnet/minecraft/tags/TagKey;");
        }

        public Biomes() {
        }
    }

    public static class Structures {

        private static TagKey<Structure> tag(String name) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/Tags$Structures.tag:(Ljava/lang/String;)Lnet/minecraft/tags/TagKey;");
        }

        public Structures() {
        }
    }

    public static class DamageTypes {

        // Pumpkin divergence: the real key, name read from NeoForge's own source.
        public static final TagKey<DamageType> IS_TECHNICAL = net.minecraft.tags.TagKey.create(
                net.minecraft.core.registries.Registries.DAMAGE_TYPE,
                net.minecraft.resources.Identifier.fromNamespaceAndPath("neoforge", "is_technical"));

        public DamageTypes() {
        }

        // Pumpkin divergence: no throwing initializer -- the keys above are real.
    }

    public static class WorldClocks {

        public WorldClocks() {
        }
    }

    public Tags() {
    }
}
