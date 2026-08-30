package net.minecraft.world.item;

import com.mojang.serialization.Codec;
import java.util.function.Consumer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.flag.FeatureElement;
import net.minecraft.world.flag.FeatureFlagSet;
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
    // same choice Block's registration path makes. It is a stand-in, not a guess at the
    // mod's intent: stack size and components come from stone until item behaviour gets
    // its own slice.
    public String pumpkinTemplate() {
        return "stone";
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

    public int getUseDuration(ItemStack itemStack, LivingEntity user) {
        throw Unimplemented.forMember("net/minecraft/world/item/Item.getUseDuration:(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/LivingEntity;)I");
    }

    public void appendHoverText(ItemStack itemStack, Item.TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        throw Unimplemented.forMember("net/minecraft/world/item/Item.appendHoverText:(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/Item$TooltipContext;Lnet/minecraft/world/item/component/TooltipDisplay;Ljava/util/function/Consumer;Lnet/minecraft/world/item/TooltipFlag;)V");
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

    public final FeatureFlagSet requiredFeatures() {
        throw Unimplemented.forMember("net/minecraft/world/item/Item.requiredFeatures:()Lnet/minecraft/world/flag/FeatureFlagSet;");
    }

    public static class Properties implements IItemPropertiesExtensions {

        // Pumpkin divergence: real body. Item metadata Pumpkin does not model yet;

        // accepted and dropped, chain returns `this`.

        public Item.Properties setNoCombineRepair() {

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

        // Pumpkin divergence: real body. Item metadata Pumpkin does not model yet;

        // accepted and dropped, chain returns `this`.

        public <T> Item.Properties component(DataComponentType<T> type, T value) {

            return this;

        }

        public Properties() {
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

    public Item() {
    }
}
