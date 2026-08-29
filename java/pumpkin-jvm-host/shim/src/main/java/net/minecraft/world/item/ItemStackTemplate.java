package net.minecraft.world.item;

import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import dev.pumpkin.shim.Unimplemented;

public record ItemStackTemplate(Holder<Item> item, int count, DataComponentPatch components) implements ItemInstance {

    public static final Codec<ItemStackTemplate> CODEC = null;

    public static final StreamCodec<RegistryFriendlyByteBuf, ItemStackTemplate> STREAM_CODEC = null;

    public ItemStackTemplate(Item item) {
        this((Holder<Item>) null, (int) 0, (DataComponentPatch) null);
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStackTemplate.<init>:(Lnet/minecraft/world/item/Item;)V");
    }

    public ItemStackTemplate(Item item, int count) {
        this((Holder<Item>) null, (int) 0, (DataComponentPatch) null);
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStackTemplate.<init>:(Lnet/minecraft/world/item/Item;I)V");
    }

    public ItemStackTemplate(Item item, DataComponentPatch patch) {
        this((Holder<Item>) null, (int) 0, (DataComponentPatch) null);
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStackTemplate.<init>:(Lnet/minecraft/world/item/Item;Lnet/minecraft/core/component/DataComponentPatch;)V");
    }

    public ItemStackTemplate(Item item, int count, DataComponentPatch patch) {
        this((Holder<Item>) null, (int) 0, (DataComponentPatch) null);
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStackTemplate.<init>:(Lnet/minecraft/world/item/Item;ILnet/minecraft/core/component/DataComponentPatch;)V");
    }

    public ItemStackTemplate(Holder<Item> item) {
        this((Holder<Item>) null, (int) 0, (DataComponentPatch) null);
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStackTemplate.<init>:(Lnet/minecraft/core/Holder;)V");
    }

    public ItemStackTemplate(Holder<Item> item, int count) {
        this((Holder<Item>) null, (int) 0, (DataComponentPatch) null);
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStackTemplate.<init>:(Lnet/minecraft/core/Holder;I)V");
    }

    public ItemStackTemplate(Holder<Item> item, DataComponentPatch patch) {
        this((Holder<Item>) null, (int) 0, (DataComponentPatch) null);
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStackTemplate.<init>:(Lnet/minecraft/core/Holder;Lnet/minecraft/core/component/DataComponentPatch;)V");
    }

    public static ItemStackTemplate fromNonEmptyStack(ItemStack itemStack) {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStackTemplate.fromNonEmptyStack:(Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/world/item/ItemStackTemplate;");
    }

    public ItemStack create() {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStackTemplate.create:()Lnet/minecraft/world/item/ItemStack;");
    }

    private ItemStack validate(ItemStack result) {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStackTemplate.validate:(Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/world/item/ItemStack;");
    }

    public ItemStack apply(DataComponentPatch additionalPatch) {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStackTemplate.apply:(Lnet/minecraft/core/component/DataComponentPatch;)Lnet/minecraft/world/item/ItemStack;");
    }

    public ItemStack apply(int count, DataComponentPatch additionalPatch) {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStackTemplate.apply:(ILnet/minecraft/core/component/DataComponentPatch;)Lnet/minecraft/world/item/ItemStack;");
    }

    public Holder<Item> typeHolder() {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStackTemplate.typeHolder:()Lnet/minecraft/core/Holder;");
    }

    public <T> T get(DataComponentType<? extends T> type) {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStackTemplate.get:(Lnet/minecraft/core/component/DataComponentType;)Ljava/lang/Object;");
    }
}
