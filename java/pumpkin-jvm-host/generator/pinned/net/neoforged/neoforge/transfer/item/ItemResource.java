package net.neoforged.neoforge.transfer.item;

import com.mojang.serialization.Codec;
import java.util.function.Predicate;
import java.util.function.Supplier;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.transfer.resource.DataComponentHolderResource;
import dev.pumpkin.shim.Stubs;
import dev.pumpkin.shim.Unimplemented;

public final class ItemResource implements DataComponentHolderResource<Item> {

    // Pumpkin divergence: a real empty resource; a null here NPEs every isEmpty check.
    public static final ItemResource EMPTY = new ItemResource();

    // Pumpkin divergence: inert codecs -- compose at class-init, throw by name on use.
    public static final Codec<ItemResource> CODEC =
            dev.pumpkin.shim.Stubs.throwingCodec("net/neoforged/neoforge/transfer/item/ItemResource.CODEC");

    public static final Codec<ItemResource> OPTIONAL_CODEC =
            dev.pumpkin.shim.Stubs.throwingCodec("net/neoforged/neoforge/transfer/item/ItemResource.OPTIONAL_CODEC");

    public static final StreamCodec<RegistryFriendlyByteBuf, ItemResource> STREAM_CODEC = Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");

    // Pumpkin divergence: real bodies for the stack-shaped subset the interaction path
    // uses. A resource is an item reference without a count; EMPTY-ness follows the item.
    private ItemLike pumpkinItem;

    // Pumpkin divergence: the component patch is data the resource carries.
    private DataComponentPatch pumpkinPatch = DataComponentPatch.EMPTY;

    private ItemResource pumpkinWith(DataComponentPatch patch) {
        ItemResource resource = new ItemResource();
        resource.pumpkinItem = pumpkinItem;
        resource.pumpkinPatch = patch;
        return resource;
    }

    public static ItemResource of(ItemStack stack) {
        ItemResource resource = new ItemResource();
        resource.pumpkinItem = stack.isEmpty() ? null : stack.getItem();
        return resource;
    }

    public static ItemResource of(ItemStackTemplate template) {
        ItemResource resource = new ItemResource();
        resource.pumpkinItem = template.item() == null ? null : template.item().value();
        return resource;
    }

    public static ItemResource of(ItemLike item) {
        ItemResource resource = new ItemResource();
        resource.pumpkinItem = item;
        return resource;
    }

    public static ItemResource of(ItemLike item, DataComponentPatch patch) {
        return of(item).pumpkinWith(patch);
    }

    public static ItemResource of(Holder<Item> holder) {
        ItemResource resource = new ItemResource();
        resource.pumpkinItem = holder.value();
        return resource;
    }

    public static ItemResource of(Holder<Item> holder, DataComponentPatch patch) {
        return of(holder).pumpkinWith(patch);
    }

    private ItemResource(ItemStack stack) {
    }

    public Item value() {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemResource.value:()Lnet/minecraft/world/item/Item;");
    }

    // Pumpkin divergence: real body over the carried item.
    public Item getItem() {
        return pumpkinItem == null ? null : pumpkinItem.asItem();
    }

    // Pumpkin divergence: a real holder over the carried item, the same shape the
    // ingredient values use.
    @SuppressWarnings("unchecked")
    public Holder<Item> typeHolder() {
        return (Holder<Item>) dev.pumpkin.shim.Stubs.of(Holder.class,
                "net/minecraft/core/Holder(ItemResource)", java.util.Map.of("value", getItem()));
    }

    public boolean isEmpty() {
        return pumpkinItem == null;
    }

    public boolean matches(ItemStack stack) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemResource.matches:(Lnet/minecraft/world/item/ItemStack;)Z");
    }

    public boolean matches(ItemStackTemplate template) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemResource.matches:(Lnet/minecraft/world/item/ItemStackTemplate;)Z");
    }

    public boolean is(ItemLike item) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemResource.is:(Lnet/minecraft/world/level/ItemLike;)Z");
    }

    public boolean test(Predicate<ItemStack> predicate) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemResource.test:(Ljava/util/function/Predicate;)Z");
    }

    public boolean isComponentsPatchEmpty() {
        return pumpkinPatch.isEmpty();
    }

    public ItemResource withMergedPatch(DataComponentPatch patch) {
        DataComponentPatch.Builder merged = DataComponentPatch.builder();
        DataComponentPatch built = merged.build();
        built.pumpkinMap.putAll(pumpkinPatch.pumpkinMap);
        built.pumpkinMap.putAll(patch.pumpkinMap);
        return pumpkinWith(built);
    }

    public <D> ItemResource with(DataComponentType<D> type, D data) {
        return withMergedPatch(DataComponentPatch.builder().set(type, data).build());
    }

    public <D> ItemResource with(Supplier<? extends DataComponentType<D>> type, D data) {
        return with(type.get(), data);
    }

    public ItemResource without(DataComponentType<?> type) {
        return withMergedPatch(DataComponentPatch.builder().remove(type).build());
    }

    public ItemResource without(Supplier<? extends DataComponentType<?>> type) {
        return without(type.get());
    }

    public DataComponentMap getComponents() {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemResource.getComponents:()Lnet/minecraft/core/component/DataComponentMap;");
    }

    public DataComponentPatch getComponentsPatch() {
        return pumpkinPatch;
    }

    // Pumpkin divergence: component questions answered from the patch this resource
    // carries -- the same divergence ItemStack documents: only what was set is seen.
    @Override
    public boolean has(DataComponentType<?> type) {
        java.util.Optional<?> entry = pumpkinPatch.pumpkinMap.get(type);
        return entry != null && entry.isPresent();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(DataComponentType<? extends T> type) {
        java.util.Optional<?> entry = pumpkinPatch.pumpkinMap.get(type);
        return entry == null ? null : (T) entry.orElse(null);
    }

    @Override
    public <T> T getOrDefault(DataComponentType<? extends T> type, T defaultValue) {
        T value = get(type);
        return value == null ? defaultValue : value;
    }

    public ItemStack toStack(int count) {
        return pumpkinItem == null ? new ItemStack((ItemLike) null, 0)
                : new ItemStack(pumpkinItem, count);
    }

    public ItemStack toStack() {
        return toStack(1);
    }

    // Pumpkin divergence: the item's declared max stack size; 64, the vanilla
    // default, when the mod did not say.
    public int getMaxStackSize() {
        Item item = getItem();
        int declared = item == null ? -1 : item.pumpkinMaxStackSize();
        return declared > 0 ? declared : 64;
    }

    public Component getHoverName() {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemResource.getHoverName:()Lnet/minecraft/network/chat/Component;");
    }

    // Pumpkin divergence: value semantics over the two facts a resource carries --
    // its item and its component patch. NeoForge's own meaning.
    public boolean equals(Object obj) {
        boolean result = obj instanceof ItemResource other
                && pumpkinItem == other.pumpkinItem
                && pumpkinPatch.equals(other.pumpkinPatch);
        if (!result && obj instanceof ItemResource other) {
            System.err.println("[pumpkin-debug] ItemResource mismatch: "
                    + pumpkinItem + " vs " + other.pumpkinItem
                    + " patches " + pumpkinPatch.pumpkinMap + " vs " + other.pumpkinPatch.pumpkinMap);
        }
        return result;
    }

    public int hashCode() {
        return java.util.Objects.hash(pumpkinItem, pumpkinPatch);
    }

    // Pumpkin divergence: real body -- an item resource prints as the item it names.
    // A resource that cannot be printed breaks debuggers and any mod log line that
    // mentions one, and says nothing about Minecraft in exchange.
    public String toString() {
        if (isEmpty()) {
            return "ItemResource[empty]";
        }
        return "ItemResource[" + dev.pumpkin.bridge.PumpkinInteractions.pumpkinItemId(toStack(1))
                + "]";
    }

    public ItemResource() {
    }
}
