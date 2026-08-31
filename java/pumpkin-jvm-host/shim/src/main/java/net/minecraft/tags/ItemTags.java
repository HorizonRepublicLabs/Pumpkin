package net.minecraft.tags;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import dev.pumpkin.shim.Unimplemented;

public final class ItemTags {

    protected ItemTags() {
    }

    // Pumpkin divergence: real body -- TagKey.create over the item registry's key.
    public static TagKey<Item> create(final Identifier name) {
        return TagKey.create(net.minecraft.resources.ResourceKey.createRegistryKey(net.minecraft.resources.Identifier.fromNamespaceAndPath("minecraft", "item")), name);
    }
}
