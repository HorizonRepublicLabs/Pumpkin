package net.minecraft.world.item;

import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.flag.FeatureElement;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.saveddata.maps.MapId;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import net.neoforged.neoforge.common.extensions.IItemExtension;
import net.neoforged.neoforge.common.extensions.IItemPropertiesExtensions;
import dev.pumpkin.shim.Unimplemented;

public class Item implements ItemLike, FeatureElement, IItemExtension {

    public static final Codec<Holder<Item>> CODEC_WITH_BOUND_COMPONENTS = null;

    private final Holder.Reference<Item> builtInRegistryHolder = null;

    public static int getId(Item item) {
        throw Unimplemented.forMember("net/minecraft/world/item/Item.getId:(Lnet/minecraft/world/item/Item;)I");
    }

    public static Item byId(int id) {
        throw Unimplemented.forMember("net/minecraft/world/item/Item.byId:(I)Lnet/minecraft/world/item/Item;");
    }

    public Item(Item.Properties properties) {
        throw Unimplemented.forMember("net/minecraft/world/item/Item.<init>:(Lnet/minecraft/world/item/Item$Properties;)V");
    }

    public Holder.Reference<Item> builtInRegistryHolder() {
        throw Unimplemented.forMember("net/minecraft/world/item/Item.builtInRegistryHolder:()Lnet/minecraft/core/Holder$Reference;");
    }

    public DataComponentMap components() {
        throw Unimplemented.forMember("net/minecraft/world/item/Item.components:()Lnet/minecraft/core/component/DataComponentMap;");
    }

    public void onDestroyed(ItemEntity itemEntity) {
        throw Unimplemented.forMember("net/minecraft/world/item/Item.onDestroyed:(Lnet/minecraft/world/entity/item/ItemEntity;)V");
    }

    public Item asItem() {
        throw Unimplemented.forMember("net/minecraft/world/item/Item.asItem:()Lnet/minecraft/world/item/Item;");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/world/item/Item.toString:()Ljava/lang/String;");
    }

    public final ItemStackTemplate getCraftingRemainder() {
        throw Unimplemented.forMember("net/minecraft/world/item/Item.getCraftingRemainder:()Lnet/minecraft/world/item/ItemStackTemplate;");
    }

    public Component getName(ItemStack itemStack) {
        throw Unimplemented.forMember("net/minecraft/world/item/Item.getName:(Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/network/chat/Component;");
    }

    public boolean isCombineRepairable(ItemStack stack) {
        throw Unimplemented.forMember("net/minecraft/world/item/Item.isCombineRepairable:(Lnet/minecraft/world/item/ItemStack;)Z");
    }

    public final FeatureFlagSet requiredFeatures() {
        throw Unimplemented.forMember("net/minecraft/world/item/Item.requiredFeatures:()Lnet/minecraft/world/flag/FeatureFlagSet;");
    }

    public static class Properties implements IItemPropertiesExtensions {

        public Item.Properties setNoCombineRepair() {
            throw Unimplemented.forMember("net/minecraft/world/item/Item$Properties.setNoCombineRepair:()Lnet/minecraft/world/item/Item$Properties;");
        }

        public Item.Properties stacksTo(int max) {
            throw Unimplemented.forMember("net/minecraft/world/item/Item$Properties.stacksTo:(I)Lnet/minecraft/world/item/Item$Properties;");
        }

        public Item.Properties durability(int maxDamage) {
            throw Unimplemented.forMember("net/minecraft/world/item/Item$Properties.durability:(I)Lnet/minecraft/world/item/Item$Properties;");
        }

        public Item.Properties craftRemainder(Item craftingRemainingItem) {
            throw Unimplemented.forMember("net/minecraft/world/item/Item$Properties.craftRemainder:(Lnet/minecraft/world/item/Item;)Lnet/minecraft/world/item/Item$Properties;");
        }

        public Item.Properties craftRemainder(ItemStackTemplate craftingRemainingItem) {
            throw Unimplemented.forMember("net/minecraft/world/item/Item$Properties.craftRemainder:(Lnet/minecraft/world/item/ItemStackTemplate;)Lnet/minecraft/world/item/Item$Properties;");
        }

        public Item.Properties tool(ToolMaterial material, TagKey<Block> minesEfficiently, float attackDamageBaseline, float attackSpeedBaseline, float disableBlockingSeconds) {
            throw Unimplemented.forMember("net/minecraft/world/item/Item$Properties.tool:(Lnet/minecraft/world/item/ToolMaterial;Lnet/minecraft/tags/TagKey;FFF)Lnet/minecraft/world/item/Item$Properties;");
        }

        public Item.Properties pickaxe(ToolMaterial material, float attackDamageBaseline, float attackSpeedBaseline) {
            throw Unimplemented.forMember("net/minecraft/world/item/Item$Properties.pickaxe:(Lnet/minecraft/world/item/ToolMaterial;FF)Lnet/minecraft/world/item/Item$Properties;");
        }

        public Item.Properties sword(ToolMaterial material, float attackDamageBaseline, float attackSpeedBaseline) {
            throw Unimplemented.forMember("net/minecraft/world/item/Item$Properties.sword:(Lnet/minecraft/world/item/ToolMaterial;FF)Lnet/minecraft/world/item/Item$Properties;");
        }

        public Item.Properties spear(ToolMaterial material, float attackDuration, float damageMultiplier, float delay, float dismountTime, float dismountThreshold, float knockbackTime, float knockbackThreshold, float damageTime, float damageThreshold) {
            throw Unimplemented.forMember("net/minecraft/world/item/Item$Properties.spear:(Lnet/minecraft/world/item/ToolMaterial;FFFFFFFFF)Lnet/minecraft/world/item/Item$Properties;");
        }

        public Item.Properties humanoidArmor(ArmorMaterial material, ArmorType type) {
            throw Unimplemented.forMember("net/minecraft/world/item/Item$Properties.humanoidArmor:(Lnet/minecraft/world/item/equipment/ArmorMaterial;Lnet/minecraft/world/item/equipment/ArmorType;)Lnet/minecraft/world/item/Item$Properties;");
        }

        public Item.Properties setId(ResourceKey<Item> id) {
            throw Unimplemented.forMember("net/minecraft/world/item/Item$Properties.setId:(Lnet/minecraft/resources/ResourceKey;)Lnet/minecraft/world/item/Item$Properties;");
        }

        public Item.Properties overrideDescription(String descriptionId) {
            throw Unimplemented.forMember("net/minecraft/world/item/Item$Properties.overrideDescription:(Ljava/lang/String;)Lnet/minecraft/world/item/Item$Properties;");
        }

        public Item.Properties useBlockDescriptionPrefix() {
            throw Unimplemented.forMember("net/minecraft/world/item/Item$Properties.useBlockDescriptionPrefix:()Lnet/minecraft/world/item/Item$Properties;");
        }

        public <T> Item.Properties component(DataComponentType<T> type, T value) {
            throw Unimplemented.forMember("net/minecraft/world/item/Item$Properties.component:(Lnet/minecraft/core/component/DataComponentType;Ljava/lang/Object;)Lnet/minecraft/world/item/Item$Properties;");
        }

        protected Properties() {
        }
    }

    public interface TooltipContext {

        HolderLookup.Provider registries();

        float tickRate();

        MapItemSavedData mapData(MapId id);

        boolean isPeaceful();

        default Level level() {
            throw Unimplemented.forMember("net/minecraft/world/item/Item$TooltipContext.level:()Lnet/minecraft/world/level/Level;");
        }

        default Player player() {
            throw Unimplemented.forMember("net/minecraft/world/item/Item$TooltipContext.player:()Lnet/minecraft/world/entity/player/Player;");
        }
    }

    protected Item() {
    }
}
