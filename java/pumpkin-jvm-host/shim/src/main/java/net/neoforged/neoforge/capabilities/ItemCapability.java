package net.neoforged.neoforge.capabilities;

import java.util.List;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import dev.pumpkin.shim.Unimplemented;

public final class ItemCapability<T, C extends Object> extends BaseCapability<T, C> {

    // Pumpkin divergence: real bodies -- interned by name, as NeoForge's registry does,
    // so creating the same capability twice hands back the same token and identity
    // comparisons hold. Queries against these tokens are a later subsystem; creating
    // and carrying one is pure identity.
    private static final java.util.concurrent.ConcurrentHashMap<Identifier, ItemCapability<?, ?>> PUMPKIN_INTERNED =
            new java.util.concurrent.ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    public static <T, C extends Object> ItemCapability<T, C> create(Identifier name, Class<T> typeClass, Class<C> contextClass) {
        return (ItemCapability<T, C>) PUMPKIN_INTERNED.computeIfAbsent(name,
                key -> new ItemCapability<>(key, typeClass, contextClass));
    }

    public static <T> ItemCapability<T, Void> createVoid(Identifier name, Class<T> typeClass) {
        return create(name, typeClass, Void.class);
    }

    public static synchronized List<ItemCapability<?, ?>> getAll() {
        throw Unimplemented.forMember("net/neoforged/neoforge/capabilities/ItemCapability.getAll:()Ljava/util/List;");
    }

    private ItemCapability(Identifier name, Class<T> typeClass, Class<C> contextClass) {
    }

    public T getCapability(ItemStack stack, C context) {
        throw Unimplemented.forMember("net/neoforged/neoforge/capabilities/ItemCapability.getCapability:(Lnet/minecraft/world/item/ItemStack;Ljava/lang/Object;)Ljava/lang/Object;");
    }

    public ItemCapability() {
    }
}
