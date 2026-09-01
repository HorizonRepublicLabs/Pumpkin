package net.minecraft.world.item;

import com.mojang.serialization.Codec;
import java.util.function.Consumer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentInitializers;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.flag.FeatureElement;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.saveddata.maps.MapId;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.common.extensions.IItemExtension;
import net.neoforged.neoforge.common.extensions.IItemPropertiesExtensions;
import dev.pumpkin.shim.Unimplemented;

public class Item implements ItemLike, FeatureElement, IItemExtension {

    // Pumpkin divergence: a throwing codec, not null. DFU dereferences these while

    // composing at class-init; null there is an NPE naming nothing. This survives

    // composition and throws on first real serialisation, naming the field.

    public static final Codec<Holder<Item>> CODEC_WITH_BOUND_COMPONENTS = dev.pumpkin.shim.Stubs.throwingCodec("net/minecraft/world/item/Item.CODEC_WITH_BOUND_COMPONENTS");

    public static final Identifier BASE_ATTACK_DAMAGE_ID = null;

    public static final Identifier BASE_ATTACK_SPEED_ID = null;

    private final Holder.Reference<Item> builtInRegistryHolder = null;

    public static int getId(Item item) {
        throw Unimplemented.forMember("net/minecraft/world/item/Item.getId:(Lnet/minecraft/world/item/Item;)I");
    }

    public static Item byId(int id) {
        throw Unimplemented.forMember("net/minecraft/world/item/Item.byId:(I)Lnet/minecraft/world/item/Item;");
    }

    // Pumpkin divergence: kept, not discarded. The registration sink reads the declared
    // stack size and durability off this on the way to Pumpkin.
    private Item.Properties pumpkinItemProperties;

    public Item(Item.Properties properties) {
        this.pumpkinItemProperties = properties;
    }

    // Pumpkin divergence: no vanilla counterpart. Set by the registration sinks; the
    // interaction bridge reads it back to name this item across the JNI boundary.
    private String pumpkinRegisteredId;

    public void pumpkinSetRegisteredId(String id) {
        this.pumpkinRegisteredId = id;
    }

    public String pumpkinRegisteredId() {
        return pumpkinRegisteredId;
    }

    // Pumpkin divergence: no vanilla counterpart -- the declared default components,
    // for placed machines to read their initial state from. Supplier keys resolve now,
    // when every registry has flushed.
    @SuppressWarnings("unchecked")
    public java.util.Map<net.minecraft.core.component.DataComponentType<?>, Object> pumpkinDefaultComponents() {
        if (pumpkinItemProperties == null) {
            return java.util.Map.of();
        }
        java.util.LinkedHashMap<net.minecraft.core.component.DataComponentType<?>, Object> resolved =
                new java.util.LinkedHashMap<>();
        for (var entry : pumpkinItemProperties.pumpkinComponents.entrySet()) {
            Object key = entry.getKey();
            if (key instanceof java.util.function.Supplier<?> supplier) {
                key = supplier.get();
            }
            if (key instanceof net.minecraft.core.component.DataComponentType<?> type) {
                resolved.put(type, entry.getValue());
            }
        }
        return resolved;
    }

    // Pumpkin divergence: no vanilla counterpart. -1 means the mod did not say.
    public int pumpkinMaxStackSize() {
        return pumpkinItemProperties == null ? -1 : pumpkinItemProperties.pumpkinMaxStackSize();
    }

    // Pumpkin divergence: no vanilla counterpart. -1 means the mod did not say.
    public int pumpkinMaxDamage() {
        return pumpkinItemProperties == null ? -1 : pumpkinItemProperties.pumpkinMaxDamage();
    }

    // Pumpkin divergence: no vanilla counterpart. The block this item places, or null for
    // an ordinary item; BlockItem overrides it. Read by the registration sinks so a block
    // and its item end up linked in Pumpkin's registry.
    public String pumpkinPlacedBlockId() {
        return null;
    }

    // Pumpkin divergence: no vanilla counterpart. Pumpkin registers an item by copying an
    // existing one's definition, and "stone" is the deliberate default template -- the
    // same choice Block's registration path makes. A vanilla stand-in from Items sets its
    // own name here, so identity checks and template copies see the real item.
    public String pumpkinVanillaName;

    public String pumpkinTemplate() {
        return pumpkinVanillaName != null ? pumpkinVanillaName : "stone";
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

    // Pumpkin divergence: vanilla body verbatim.
    public Item asItem() {
        return this;
    }

    public InteractionResult useOn(UseOnContext context) {
        throw Unimplemented.forMember("net/minecraft/world/item/Item.useOn:(Lnet/minecraft/world/item/context/UseOnContext;)Lnet/minecraft/world/InteractionResult;");
    }

    public float getDestroySpeed(ItemStack itemStack, BlockState state) {
        throw Unimplemented.forMember("net/minecraft/world/item/Item.getDestroySpeed:(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/block/state/BlockState;)F");
    }

    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        throw Unimplemented.forMember("net/minecraft/world/item/Item.use:(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/InteractionResult;");
    }

    public void postHurtEnemy(ItemStack itemStack, LivingEntity mob, LivingEntity attacker) {
        throw Unimplemented.forMember("net/minecraft/world/item/Item.postHurtEnemy:(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/entity/LivingEntity;)V");
    }

    public boolean mineBlock(ItemStack itemStack, Level level, BlockState state, BlockPos pos, LivingEntity owner) {
        throw Unimplemented.forMember("net/minecraft/world/item/Item.mineBlock:(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/LivingEntity;)Z");
    }

    public boolean isCorrectToolForDrops(ItemStack itemStack, BlockState state) {
        throw Unimplemented.forMember("net/minecraft/world/item/Item.isCorrectToolForDrops:(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/block/state/BlockState;)Z");
    }

    public InteractionResult interactLivingEntity(ItemStack itemStack, Player player, LivingEntity target, InteractionHand type) {
        throw Unimplemented.forMember("net/minecraft/world/item/Item.interactLivingEntity:(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/InteractionResult;");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/world/item/Item.toString:()Ljava/lang/String;");
    }

    public final ItemStackTemplate getCraftingRemainder() {
        throw Unimplemented.forMember("net/minecraft/world/item/Item.getCraftingRemainder:()Lnet/minecraft/world/item/ItemStackTemplate;");
    }

    public void inventoryTick(ItemStack itemStack, ServerLevel level, Entity owner, EquipmentSlot slot) {
        throw Unimplemented.forMember("net/minecraft/world/item/Item.inventoryTick:(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/entity/EquipmentSlot;)V");
    }

    public void onCraftedBy(ItemStack itemStack, Player player) {
        throw Unimplemented.forMember("net/minecraft/world/item/Item.onCraftedBy:(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/player/Player;)V");
    }

    public int getUseDuration(ItemStack itemStack, LivingEntity user) {
        throw Unimplemented.forMember("net/minecraft/world/item/Item.getUseDuration:(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/LivingEntity;)I");
    }

    public boolean releaseUsing(ItemStack itemStack, Level level, LivingEntity entity, int remainingTime) {
        throw Unimplemented.forMember("net/minecraft/world/item/Item.releaseUsing:(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;I)Z");
    }

    public void appendHoverText(ItemStack itemStack, Item.TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        throw Unimplemented.forMember("net/minecraft/world/item/Item.appendHoverText:(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/Item$TooltipContext;Lnet/minecraft/world/item/component/TooltipDisplay;Ljava/util/function/Consumer;Lnet/minecraft/world/item/TooltipFlag;)V");
    }

    public final String getDescriptionId() {
        throw Unimplemented.forMember("net/minecraft/world/item/Item.getDescriptionId:()Ljava/lang/String;");
    }

    public Component getName(ItemStack itemStack) {
        throw Unimplemented.forMember("net/minecraft/world/item/Item.getName:(Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/network/chat/Component;");
    }

    public boolean isFoil(ItemStack itemStack) {
        throw Unimplemented.forMember("net/minecraft/world/item/Item.isFoil:(Lnet/minecraft/world/item/ItemStack;)Z");
    }

    public static BlockHitResult getPlayerPOVHitResult(Level level, Player player, ClipContext.Fluid fluid) {
        throw Unimplemented.forMember("net/minecraft/world/item/Item.getPlayerPOVHitResult:(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/level/ClipContext$Fluid;)Lnet/minecraft/world/phys/BlockHitResult;");
    }

    public boolean isCombineRepairable(ItemStack stack) {
        throw Unimplemented.forMember("net/minecraft/world/item/Item.isCombineRepairable:(Lnet/minecraft/world/item/ItemStack;)Z");
    }

    public ItemStack getDefaultInstance() {
        throw Unimplemented.forMember("net/minecraft/world/item/Item.getDefaultInstance:()Lnet/minecraft/world/item/ItemStack;");
    }

    public final FeatureFlagSet requiredFeatures() {
        throw Unimplemented.forMember("net/minecraft/world/item/Item.requiredFeatures:()Lnet/minecraft/world/flag/FeatureFlagSet;");
    }

    public static class Properties implements IItemPropertiesExtensions {

        // Pumpkin divergence: real body. Item metadata Pumpkin does not model yet;

        // accepted and dropped, chain returns `this`.

        public Item.Properties setNoCombineRepair() {

            return this;

        }

        // Pumpkin divergence: declared item metadata, accepted and dropped.
        public Item.Properties usingConvertsTo(Item item) {
            return this;
        }

        // Pumpkin divergence: real body. Recorded so the registration sink can carry it;
        // -1 means the mod did not say.
        private int pumpkinMaxStackSize = -1;

        private int pumpkinMaxDamage = -1;

        int pumpkinMaxStackSize() {
            return pumpkinMaxStackSize;
        }

        int pumpkinMaxDamage() {
            return pumpkinMaxDamage;
        }

        public Item.Properties stacksTo(int max) {
            this.pumpkinMaxStackSize = max;
            return this;
        }

        // Pumpkin divergence: real body. Recorded so the registration sink can carry it.
        public Item.Properties durability(int maxDamage) {
            this.pumpkinMaxDamage = maxDamage;
            return this;
        }

        // Pumpkin divergence: real body. Item metadata Pumpkin does not model yet;

        // accepted and dropped, chain returns `this`.

        public Item.Properties craftRemainder(Item craftingRemainingItem) {

            return this;

        }

        // Pumpkin divergence: real body. Item metadata Pumpkin does not model yet;

        // accepted and dropped, chain returns `this`.

        public Item.Properties craftRemainder(ItemStackTemplate craftingRemainingItem) {

            return this;

        }

        // Pumpkin divergence: declared item metadata, accepted and dropped.
        public Item.Properties rarity(Rarity rarity) {
            return this;
        }

        // Pumpkin divergence: declared item metadata, accepted and dropped.
        public Item.Properties fireResistant() {
            return this;
        }

        // Pumpkin divergence: declared item metadata, accepted and dropped.
        public Item.Properties enchantable(int value) {
            return this;
        }

        // Pumpkin divergence: declared item metadata, accepted and dropped.
        public Item.Properties repairable(Item repairItem) {
            return this;
        }

        // Pumpkin divergence: declared item metadata, accepted and dropped.
        public Item.Properties repairable(TagKey<Item> repairItems) {
            return this;
        }

        // Pumpkin divergence: declared item metadata, accepted and dropped.
        public Item.Properties equippableUnswappable(EquipmentSlot slot) {
            return this;
        }

        // Pumpkin divergence: real body. Item metadata Pumpkin does not model yet;

        // accepted and dropped, chain returns `this`.

        public Item.Properties tool(ToolMaterial material, TagKey<Block> minesEfficiently, float attackDamageBaseline, float attackSpeedBaseline, float disableBlockingSeconds) {

            return this;

        }

        // Pumpkin divergence: real body. Item metadata Pumpkin does not model yet;

        // accepted and dropped, chain returns `this`.

        public Item.Properties pickaxe(ToolMaterial material, float attackDamageBaseline, float attackSpeedBaseline) {

            return this;

        }

        // Pumpkin divergence: real body. Item metadata Pumpkin does not model yet;

        // accepted and dropped, chain returns `this`.

        public Item.Properties sword(ToolMaterial material, float attackDamageBaseline, float attackSpeedBaseline) {

            return this;

        }

        // Pumpkin divergence: real body. Item metadata Pumpkin does not model yet;

        // accepted and dropped, chain returns `this`.

        public Item.Properties spear(ToolMaterial material, float attackDuration, float damageMultiplier, float delay, float dismountTime, float dismountThreshold, float knockbackTime, float knockbackThreshold, float damageTime, float damageThreshold) {

            return this;

        }

        // Pumpkin divergence: declared item metadata, accepted and dropped.
        public Item.Properties spawnEgg(EntityType<?> type) {
            return this;
        }

        // Pumpkin divergence: real body. Item metadata Pumpkin does not model yet;

        // accepted and dropped, chain returns `this`.

        public Item.Properties humanoidArmor(ArmorMaterial material, ArmorType type) {

            return this;

        }

        // Pumpkin divergence: real body. Item metadata Pumpkin does not model yet;

        // accepted and dropped, chain returns `this`.

        public Item.Properties setId(ResourceKey<Item> id) {

            return this;

        }

        // Pumpkin divergence: real body. Item metadata Pumpkin does not model yet;

        // accepted and dropped, chain returns `this`.

        public Item.Properties overrideDescription(String descriptionId) {

            return this;

        }

        // Pumpkin divergence: real body. Item metadata Pumpkin does not model yet;

        // accepted and dropped, chain returns `this`.

        public Item.Properties useBlockDescriptionPrefix() {

            return this;

        }

        // Pumpkin divergence: the declared default components are recorded -- placed
        // machines read their initial state (Mekanism's side configs) from them. Keys
        // arrive as types or as suppliers of types (the NeoForge overload); suppliers
        // stay lazy, because the component type may register after the item does.
        final java.util.LinkedHashMap<Object, Object> pumpkinComponents =
                new java.util.LinkedHashMap<>();

        public <T> Item.Properties component(DataComponentType<T> type, T value) {
            pumpkinComponents.put(type, value);
            return this;
        }

        public <T> Item.Properties component(
                java.util.function.Supplier<? extends DataComponentType<T>> type, T value) {
            pumpkinComponents.put(type, value);
            return this;
        }

        // Pumpkin divergence: declared item metadata, accepted and dropped.
        public <T> Item.Properties delayedComponent(DataComponentType<T> type, DataComponentInitializers.SingleComponentInitializer<T> initializer) {
            return this;
        }

        // Pumpkin divergence: declared item metadata, accepted and dropped.
        public Item.Properties attributes(ItemAttributeModifiers attributes) {
            return this;
        }

        public Properties() {
        }
    }

    public interface TooltipContext {

        Item.TooltipContext EMPTY = null;

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

        static Item.TooltipContext of(Level level) {
            throw Unimplemented.forMember("net/minecraft/world/item/Item$TooltipContext.of:(Lnet/minecraft/world/level/Level;)Lnet/minecraft/world/item/Item$TooltipContext;");
        }

        static Item.TooltipContext of(Level pLevel, Player player) {
            throw Unimplemented.forMember("net/minecraft/world/item/Item$TooltipContext.of:(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/player/Player;)Lnet/minecraft/world/item/Item$TooltipContext;");
        }

        static Item.TooltipContext of(HolderLookup.Provider registries) {
            throw Unimplemented.forMember("net/minecraft/world/item/Item$TooltipContext.of:(Lnet/minecraft/core/HolderLookup$Provider;)Lnet/minecraft/world/item/Item$TooltipContext;");
        }
    }

    public Item() {
    }
}
