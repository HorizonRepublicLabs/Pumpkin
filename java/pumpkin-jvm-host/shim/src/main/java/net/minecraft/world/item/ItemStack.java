package net.minecraft.world.item;

import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentHolder;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.PatchedDataComponentMap;
import net.minecraft.core.component.TypedDataComponent;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.component.TooltipProvider;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.commons.lang3.function.TriConsumer;
import net.neoforged.neoforge.common.MutableDataComponentHolder;
import net.neoforged.neoforge.common.extensions.IItemStackExtension;
import dev.pumpkin.shim.Stubs;
import dev.pumpkin.shim.Unimplemented;

public final class ItemStack implements DataComponentHolder, ItemInstance, IItemStackExtension, MutableDataComponentHolder {

    // Pumpkin divergence: tag membership answered from the real tag tables (mod
    // datapacks + vanilla, via PumpkinTags); an unregistered item wears no tags.
    @Override
    public boolean is(net.minecraft.tags.TagKey<Item> tag) {
        Item item = getItem();
        String id = item == null ? null : item.pumpkinRegisteredId();
        return id != null && dev.pumpkin.bridge.PumpkinTags.contains(tag.location().toString(), id);
    }

    public static final StreamCodec<RegistryFriendlyByteBuf, ItemStack> OPTIONAL_STREAM_CODEC = Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");

    // Pumpkin divergence: a real empty stack, because everything compares against it.
    public static final ItemStack EMPTY = new ItemStack((ItemLike) null, 0);

    private int count;

    public Optional<TooltipComponent> getTooltipImage() {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStack.getTooltipImage:()Ljava/util/Optional;");
    }

    public DataComponentMap getComponents() {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStack.getComponents:()Lnet/minecraft/core/component/DataComponentMap;");
    }

    public DataComponentMap getPrototype() {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStack.getPrototype:()Lnet/minecraft/core/component/DataComponentMap;");
    }

    public DataComponentPatch getComponentsPatch() {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStack.getComponentsPatch:()Lnet/minecraft/core/component/DataComponentPatch;");
    }

    public boolean isComponentsPatchEmpty() {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStack.isComponentsPatchEmpty:()Z");
    }

    // Pumpkin divergence: a stack really carries its item and count. The interaction
    // bridge builds these and mods read them back; without real fields every isEmpty()
    // is a guess.
    private ItemLike pumpkinItem;

    private int pumpkinCount = 1;

    public ItemLike pumpkinItemLike() {
        return pumpkinItem;
    }

    // Pumpkin divergence: in-place shrink, for the one algorithm (moveItemStackTo) that
    // vanilla writes against a mutable count.
    public void pumpkinShrink(int by) {
        pumpkinCount = Math.max(0, pumpkinCount - by);
        if (pumpkinCount == 0) {
            pumpkinItem = null;
        }
    }

    public ItemStack(ItemLike item, int count) {
        this.pumpkinItem = item;
        this.pumpkinCount = count;
    }

    public ItemStack(ItemLike item) {
        this(item, 1);
    }

    public ItemStack(Holder<Item> item, int count) {
    }

    public ItemStack(Holder<Item> item) {
    }

    public ItemStack(Holder<Item> item, int count, DataComponentPatch components) {
    }

    private ItemStack(Holder<Item> item, int count, PatchedDataComponentMap components) {
    }

    private ItemStack(Void nullMarker) {
    }

    // Pumpkin divergence: real body.
    public boolean isEmpty() {
        return pumpkinItem == null || pumpkinCount <= 0;
    }

    public ItemStack split(int amount) {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStack.split:(I)Lnet/minecraft/world/item/ItemStack;");
    }

    // Pumpkin divergence: real body.
    public Item getItem() {
        return pumpkinItem == null ? null : pumpkinItem.asItem();
    }

    // Pumpkin divergence: a real holder over the carried item, the same shape the
    // ingredient values use.
    @SuppressWarnings("unchecked")
    public Holder<Item> typeHolder() {
        return (Holder<Item>) dev.pumpkin.shim.Stubs.of(Holder.class,
                "net/minecraft/core/Holder(ItemStack)", java.util.Map.of("value", getItem()));
    }

    public boolean is(Predicate<Holder<Item>> item) {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStack.is:(Ljava/util/function/Predicate;)Z");
    }

    public InteractionResult onItemUseFirst(UseOnContext context) {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStack.onItemUseFirst:(Lnet/minecraft/world/item/context/UseOnContext;)Lnet/minecraft/world/InteractionResult;");
    }

    public float getDestroySpeed(BlockState state) {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStack.getDestroySpeed:(Lnet/minecraft/world/level/block/state/BlockState;)F");
    }

    // Pumpkin divergence: the item's declared max stack size; 64, the vanilla
    // default, when the mod did not say.
    public int getMaxStackSize() {
        int declared = getItem() == null ? -1 : getItem().pumpkinMaxStackSize();
        return declared > 0 ? declared : 64;
    }

    public boolean isDamageableItem() {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStack.isDamageableItem:()Z");
    }

    public boolean isDamaged() {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStack.isDamaged:()Z");
    }

    public int getDamageValue() {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStack.getDamageValue:()I");
    }

    public void setDamageValue(int value) {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStack.setDamageValue:(I)V");
    }

    public int getMaxDamage() {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStack.getMaxDamage:()I");
    }

    public boolean isBroken() {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStack.isBroken:()Z");
    }

    public void hurtAndBreak(int amount, ServerLevel level, ServerPlayer player, Consumer<Item> onBreak) {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStack.hurtAndBreak:(ILnet/minecraft/server/level/ServerLevel;Lnet/minecraft/server/level/ServerPlayer;Ljava/util/function/Consumer;)V");
    }

    public void hurtAndBreak(int amount, ServerLevel level, LivingEntity player, Consumer<Item> onBreak) {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStack.hurtAndBreak:(ILnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/LivingEntity;Ljava/util/function/Consumer;)V");
    }

    private void applyDamage(int newDamage, ServerPlayer player, Consumer<Item> onBreak) {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStack.applyDamage:(ILnet/minecraft/server/level/ServerPlayer;Ljava/util/function/Consumer;)V");
    }

    private void applyDamage(int newDamage, LivingEntity player, Consumer<Item> onBreak) {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStack.applyDamage:(ILnet/minecraft/world/entity/LivingEntity;Ljava/util/function/Consumer;)V");
    }

    public void hurtAndBreak(int amount, LivingEntity owner, InteractionHand hand) {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStack.hurtAndBreak:(ILnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/InteractionHand;)V");
    }

    public void hurtAndBreak(int amount, LivingEntity owner, EquipmentSlot slot) {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStack.hurtAndBreak:(ILnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/entity/EquipmentSlot;)V");
    }

    public void mineBlock(Level level, BlockState state, BlockPos pos, Player owner) {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStack.mineBlock:(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/player/Player;)V");
    }

    public boolean isCorrectToolForDrops(BlockState state) {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStack.isCorrectToolForDrops:(Lnet/minecraft/world/level/block/state/BlockState;)Z");
    }

    // Pumpkin divergence: real body.
    public ItemStack copy() {
        return copyWithCount(pumpkinCount);
    }

    // Pumpkin divergence: real body.
    public ItemStack copyWithCount(int count) {
        return new ItemStack(pumpkinItem, count);
    }

    public static boolean matches(ItemStack a, ItemStack b) {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStack.matches:(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemStack;)Z");
    }

    public static boolean matches(ItemStack a, ItemStackTemplate b) {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStack.matches:(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemStackTemplate;)Z");
    }

    public static boolean isSameItemSameComponents(ItemStack a, ItemStack b) {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStack.isSameItemSameComponents:(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemStack;)Z");
    }

    public static boolean isSameItemSameComponents(ItemStack a, ItemStackTemplate b) {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStack.isSameItemSameComponents:(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemStackTemplate;)Z");
    }

    public static int hashItemAndComponents(ItemStack item) {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStack.hashItemAndComponents:(Lnet/minecraft/world/item/ItemStack;)I");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStack.toString:()Ljava/lang/String;");
    }

    public void onCraftedBy(Player player, int craftCount) {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStack.onCraftedBy:(Lnet/minecraft/world/entity/player/Player;I)V");
    }

    public int getUseDuration(LivingEntity user) {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStack.getUseDuration:(Lnet/minecraft/world/entity/LivingEntity;)I");
    }

    // Pumpkin divergence: real bodies. A stack's own components genuinely live on the
    // stack, so a mod that sets one and reads it back gets its value. The item's base
    // components are not consulted here -- get() answers only what was set on this stack,
    // and pumpkinComponents() hands the map to whoever needs the rest of the merge later.
    private final java.util.Map<DataComponentType<?>, Object> pumpkinComponents =
            new java.util.HashMap<>();

    @SuppressWarnings("unchecked")
    public <T> T set(DataComponentType<T> type, T value) {
        return (T) pumpkinComponents.put(type, value);
    }

    // Pumpkin divergence: no vanilla counterpart. The components set on this stack.
    public java.util.Map<DataComponentType<?>, Object> pumpkinComponents() {
        return pumpkinComponents;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(DataComponentType<? extends T> type) {
        return (T) pumpkinComponents.get(type);
    }

    @Override
    public boolean has(DataComponentType<?> type) {
        return pumpkinComponents.containsKey(type);
    }

    @Override
    public <T> T getOrDefault(DataComponentType<? extends T> type, T defaultValue) {
        T value = get(type);
        return value == null ? defaultValue : value;
    }

    public <T> T set(TypedDataComponent<T> value) {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStack.set:(Lnet/minecraft/core/component/TypedDataComponent;)Ljava/lang/Object;");
    }

    public <T, U> T update(DataComponentType<T> type, T defaultValue, U value, BiFunction<T, U, T> combiner) {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStack.update:(Lnet/minecraft/core/component/DataComponentType;Ljava/lang/Object;Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;");
    }

    public <T> T update(DataComponentType<T> type, T defaultValue, UnaryOperator<T> function) {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStack.update:(Lnet/minecraft/core/component/DataComponentType;Ljava/lang/Object;Ljava/util/function/UnaryOperator;)Ljava/lang/Object;");
    }

    public <T> T remove(DataComponentType<? extends T> type) {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStack.remove:(Lnet/minecraft/core/component/DataComponentType;)Ljava/lang/Object;");
    }

    public void applyComponents(DataComponentPatch patch) {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStack.applyComponents:(Lnet/minecraft/core/component/DataComponentPatch;)V");
    }

    public void applyComponents(DataComponentMap components) {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStack.applyComponents:(Lnet/minecraft/core/component/DataComponentMap;)V");
    }

    public Component getHoverName() {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStack.getHoverName:()Lnet/minecraft/network/chat/Component;");
    }

    public <T extends TooltipProvider> void addToTooltip(DataComponentType<T> type, Item.TooltipContext context, TooltipDisplay display, Consumer<Component> consumer, TooltipFlag flag) {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStack.addToTooltip:(Lnet/minecraft/core/component/DataComponentType;Lnet/minecraft/world/item/Item$TooltipContext;Lnet/minecraft/world/item/component/TooltipDisplay;Ljava/util/function/Consumer;Lnet/minecraft/world/item/TooltipFlag;)V");
    }

    public List<Component> getTooltipLines(Item.TooltipContext context, Player player, TooltipFlag tooltipFlag) {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStack.getTooltipLines:(Lnet/minecraft/world/item/Item$TooltipContext;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/item/TooltipFlag;)Ljava/util/List;");
    }

    public boolean hasFoil() {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStack.hasFoil:()Z");
    }

    public void enchant(Holder<Enchantment> enchantment, int level) {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStack.enchant:(Lnet/minecraft/core/Holder;I)V");
    }

    public ItemEnchantments getTagEnchantments() {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStack.getTagEnchantments:()Lnet/minecraft/world/item/enchantment/ItemEnchantments;");
    }

    public void forEachModifier(EquipmentSlotGroup slot, TriConsumer<Holder<Attribute>, AttributeModifier, ItemAttributeModifiers.Display> consumer) {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStack.forEachModifier:(Lnet/minecraft/world/entity/EquipmentSlotGroup;Lorg/apache/commons/lang3/function/TriConsumer;)V");
    }

    public void forEachModifier(EquipmentSlot slot, BiConsumer<Holder<Attribute>, AttributeModifier> consumer) {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStack.forEachModifier:(Lnet/minecraft/world/entity/EquipmentSlot;Ljava/util/function/BiConsumer;)V");
    }

    public Component getDisplayName() {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStack.getDisplayName:()Lnet/minecraft/network/chat/Component;");
    }

    public int getCount() {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStack.getCount:()I");
    }

    // Pumpkin divergence: real body.
    public int count() {
        return pumpkinCount;
    }

    public void setCount(int count) {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStack.setCount:(I)V");
    }

    public void grow(int amount) {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStack.grow:(I)V");
    }

    public void shrink(int amount) {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStack.shrink:(I)V");
    }

    public void consume(int amount, LivingEntity owner) {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStack.consume:(ILnet/minecraft/world/entity/LivingEntity;)V");
    }

    public void onDestroyed(ItemEntity itemEntity) {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStack.onDestroyed:(Lnet/minecraft/world/entity/item/ItemEntity;)V");
    }

    public ItemStack() {
    }
}
