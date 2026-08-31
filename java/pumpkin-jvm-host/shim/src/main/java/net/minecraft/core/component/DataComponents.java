package net.minecraft.core.component;

import java.util.function.UnaryOperator;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Unit;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.component.BlocksAttacks;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.component.TypedEntityData;
import net.minecraft.world.item.component.UseRemainder;
import net.minecraft.world.item.enchantment.Enchantable;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.item.equipment.Equippable;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.saveddata.maps.MapId;
import dev.pumpkin.shim.Stubs;
import dev.pumpkin.shim.Unimplemented;

public class DataComponents {

    public static final DataComponentType<CustomData> CUSTOM_DATA = Stubs.of(DataComponentType.class, "net/minecraft/core/component/DataComponentType");

    public static final DataComponentType<Integer> DAMAGE = Stubs.of(DataComponentType.class, "net/minecraft/core/component/DataComponentType");

    public static final DataComponentType<Unit> UNBREAKABLE = Stubs.of(DataComponentType.class, "net/minecraft/core/component/DataComponentType");

    public static final DataComponentType<Component> CUSTOM_NAME = Stubs.of(DataComponentType.class, "net/minecraft/core/component/DataComponentType");

    public static final DataComponentType<Rarity> RARITY = Stubs.of(DataComponentType.class, "net/minecraft/core/component/DataComponentType");

    public static final DataComponentType<ItemEnchantments> ENCHANTMENTS = Stubs.of(DataComponentType.class, "net/minecraft/core/component/DataComponentType");

    public static final DataComponentType<ItemAttributeModifiers> ATTRIBUTE_MODIFIERS = Stubs.of(DataComponentType.class, "net/minecraft/core/component/DataComponentType");

    public static final DataComponentType<FoodProperties> FOOD = Stubs.of(DataComponentType.class, "net/minecraft/core/component/DataComponentType");

    public static final DataComponentType<Consumable> CONSUMABLE = Stubs.of(DataComponentType.class, "net/minecraft/core/component/DataComponentType");

    public static final DataComponentType<UseRemainder> USE_REMAINDER = Stubs.of(DataComponentType.class, "net/minecraft/core/component/DataComponentType");

    public static final DataComponentType<Tool> TOOL = Stubs.of(DataComponentType.class, "net/minecraft/core/component/DataComponentType");

    public static final DataComponentType<Enchantable> ENCHANTABLE = Stubs.of(DataComponentType.class, "net/minecraft/core/component/DataComponentType");

    public static final DataComponentType<Equippable> EQUIPPABLE = Stubs.of(DataComponentType.class, "net/minecraft/core/component/DataComponentType");

    public static final DataComponentType<Unit> GLIDER = Stubs.of(DataComponentType.class, "net/minecraft/core/component/DataComponentType");

    public static final DataComponentType<BlocksAttacks> BLOCKS_ATTACKS = Stubs.of(DataComponentType.class, "net/minecraft/core/component/DataComponentType");

    public static final DataComponentType<MapId> MAP_ID = Stubs.of(DataComponentType.class, "net/minecraft/core/component/DataComponentType");

    public static final DataComponentType<PotionContents> POTION_CONTENTS = Stubs.of(DataComponentType.class, "net/minecraft/core/component/DataComponentType");

    public static final DataComponentType<TypedEntityData<BlockEntityType<?>>> BLOCK_ENTITY_DATA = Stubs.of(DataComponentType.class, "net/minecraft/core/component/DataComponentType");

    public static final DataComponentType<BannerPatternLayers> BANNER_PATTERNS = Stubs.of(DataComponentType.class, "net/minecraft/core/component/DataComponentType");

    public static final DataComponentType<DyeColor> BASE_COLOR = Stubs.of(DataComponentType.class, "net/minecraft/core/component/DataComponentType");

    public static final DataComponentType<ItemContainerContents> CONTAINER = Stubs.of(DataComponentType.class, "net/minecraft/core/component/DataComponentType");

    public static final DataComponentType<Holder<SoundEvent>> BREAK_SOUND = Stubs.of(DataComponentType.class, "net/minecraft/core/component/DataComponentType");

    private static <T> DataComponentType<T> register(String id, UnaryOperator<DataComponentType.Builder<T>> builder) {
        throw Unimplemented.forMember("net/minecraft/core/component/DataComponents.register:(Ljava/lang/String;Ljava/util/function/UnaryOperator;)Lnet/minecraft/core/component/DataComponentType;");
    }

    public DataComponents() {
    }
}
