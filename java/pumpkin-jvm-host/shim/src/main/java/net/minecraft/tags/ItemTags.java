package net.minecraft.tags;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import dev.pumpkin.shim.Unimplemented;

public final class ItemTags {

    protected ItemTags() {
    }

    public static TagKey<Item> create(final Identifier name) {
        throw Unimplemented.forMember("net/minecraft/tags/ItemTags.create:(Lnet/minecraft/resources/Identifier;)Lnet/minecraft/tags/TagKey;");
    }
}
