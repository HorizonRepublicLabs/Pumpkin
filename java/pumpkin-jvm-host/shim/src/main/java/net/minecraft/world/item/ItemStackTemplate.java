package net.minecraft.world.item;

import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import dev.pumpkin.shim.Stubs;
import dev.pumpkin.shim.Unimplemented;

public record ItemStackTemplate(Holder<Item> item, int count, DataComponentPatch components) implements ItemInstance {

    // Pumpkin divergence: a throwing codec, not null. DFU dereferences these while

    // composing at class-init; null there is an NPE naming nothing. This survives

    // composition and throws on first real serialisation, naming the field.

    // Pumpkin divergence: a real codec for the one shape recipe results use -- a map of
    // {id, optional count}. Components in a result refuse loudly; nothing decodes them
    // here yet.
    public static final Codec<ItemStackTemplate> CODEC = new com.mojang.serialization.codecs.PrimitiveCodec<ItemStackTemplate>() {
        @Override
        public <T> com.mojang.serialization.DataResult<ItemStackTemplate> read(
                com.mojang.serialization.DynamicOps<T> ops, T input) {
            var map = ops.getMap(input);
            if (map.result().isEmpty()) {
                return com.mojang.serialization.DataResult.error(() -> "result is not a map");
            }
            var like = map.result().get();
            T idValue = like.get("id");
            if (idValue == null) {
                return com.mojang.serialization.DataResult.error(() -> "result has no id");
            }
            var id = ops.getStringValue(idValue);
            if (id.result().isEmpty()) {
                return com.mojang.serialization.DataResult.error(() -> "result id is not a string");
            }
            if (like.get("components") != null) {
                return com.mojang.serialization.DataResult.error(
                        () -> "result components are not decodable here");
            }
            int count = 1;
            T countValue = like.get("count");
            if (countValue != null) {
                var parsed = ops.getNumberValue(countValue);
                if (parsed.result().isPresent()) {
                    count = parsed.result().get().intValue();
                }
            }
            net.minecraft.world.item.ItemStack stack = dev.pumpkin.bridge.PumpkinInteractions
                    .pumpkinBuildStack(id.result().get(), count);
            Item item = stack.getItem();
            @SuppressWarnings("unchecked")
            Holder<Item> holder = (Holder<Item>) dev.pumpkin.shim.Stubs.of(Holder.class,
                    "net/minecraft/core/Holder", java.util.Map.of("value", item));
            return com.mojang.serialization.DataResult.success(
                    new ItemStackTemplate(holder, count, (DataComponentPatch) null));
        }

        @Override
        public <T> T write(com.mojang.serialization.DynamicOps<T> ops, ItemStackTemplate value) {
            throw dev.pumpkin.shim.Unimplemented.forMember(
                    "net/minecraft/world/item/ItemStackTemplate.CODEC.encode");
        }
    };

    public static final StreamCodec<RegistryFriendlyByteBuf, ItemStackTemplate> STREAM_CODEC = Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");

    public ItemStackTemplate(Item item) {
        this((Holder<Item>) null, (int) 0, (DataComponentPatch) null);
    }

    public ItemStackTemplate(Item item, int count) {
        this((Holder<Item>) null, (int) 0, (DataComponentPatch) null);
    }

    public ItemStackTemplate(Item item, DataComponentPatch patch) {
        this((Holder<Item>) null, (int) 0, (DataComponentPatch) null);
    }

    public ItemStackTemplate(Item item, int count, DataComponentPatch patch) {
        this((Holder<Item>) null, (int) 0, (DataComponentPatch) null);
    }

    public ItemStackTemplate(Holder<Item> item) {
        this((Holder<Item>) null, (int) 0, (DataComponentPatch) null);
    }

    public ItemStackTemplate(Holder<Item> item, int count) {
        this((Holder<Item>) null, (int) 0, (DataComponentPatch) null);
    }

    public ItemStackTemplate(Holder<Item> item, DataComponentPatch patch) {
        this((Holder<Item>) null, (int) 0, (DataComponentPatch) null);
    }

    public static ItemStackTemplate fromNonEmptyStack(ItemStack itemStack) {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemStackTemplate.fromNonEmptyStack:(Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/world/item/ItemStackTemplate;");
    }

    // Pumpkin divergence: real body -- the template's whole point is making this stack.
    public ItemStack create() {
        if (item == null || count <= 0) {
            return ItemStack.EMPTY;
        }
        return new ItemStack(item.value(), count);
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
