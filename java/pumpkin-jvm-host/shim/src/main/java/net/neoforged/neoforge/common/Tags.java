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

        public static final TagKey<EntityType<?>> TELEPORTING_NOT_SUPPORTED = null;

        private static TagKey<EntityType<?>> tag(String name) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/Tags$EntityTypes.tag:(Ljava/lang/String;)Lnet/minecraft/tags/TagKey;");
        }

        public EntityTypes() {
        }

        static {
            if (true) {
                throw Unimplemented.forMember("net/neoforged/neoforge/common/Tags$EntityTypes");
            }
        }
    }

    public static class Items {

        public static final TagKey<Item> GEMS_LAPIS = null;

        public static final TagKey<Item> ORES = null;

        public static final TagKey<Item> ORES_COPPER = null;

        public static final TagKey<Item> ORES_GOLD = null;

        public static final TagKey<Item> ORES_IRON = null;

        private static TagKey<Item> tag(String name) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/Tags$Items.tag:(Ljava/lang/String;)Lnet/minecraft/tags/TagKey;");
        }

        public Items() {
        }

        static {
            if (true) {
                throw Unimplemented.forMember("net/neoforged/neoforge/common/Tags$Items");
            }
        }
    }

    public static class Fluids {

        public static final TagKey<Fluid> GASEOUS = null;

        public static final TagKey<Fluid> HIDDEN_FROM_RECIPE_VIEWERS = null;

        private static TagKey<Fluid> tag(String name) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/Tags$Fluids.tag:(Ljava/lang/String;)Lnet/minecraft/tags/TagKey;");
        }

        public Fluids() {
        }

        static {
            if (true) {
                throw Unimplemented.forMember("net/neoforged/neoforge/common/Tags$Fluids");
            }
        }
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

        public static final TagKey<DamageType> IS_TECHNICAL = null;

        public DamageTypes() {
        }

        static {
            if (true) {
                throw Unimplemented.forMember("net/neoforged/neoforge/common/Tags$DamageTypes");
            }
        }
    }

    public static class WorldClocks {

        public WorldClocks() {
        }
    }

    public Tags() {
    }
}
