package net.minecraft.advancements.predicates;

import java.util.Optional;
import java.util.function.Predicate;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.level.ItemLike;
import dev.pumpkin.shim.Unimplemented;

public record ItemPredicate(Optional<HolderSet<Item>> items, MinMaxBounds.Ints count, DataComponentMatchers components) implements Predicate<ItemInstance> {

    public boolean test(ItemInstance itemStack) {
        throw Unimplemented.forMember("net/minecraft/advancements/predicates/ItemPredicate.test:(Lnet/minecraft/world/item/ItemInstance;)Z");
    }

    public static class Builder {

        public static ItemPredicate.Builder item() {
            throw Unimplemented.forMember("net/minecraft/advancements/predicates/ItemPredicate$Builder.item:()Lnet/minecraft/advancements/predicates/ItemPredicate$Builder;");
        }

        public ItemPredicate.Builder of(HolderGetter<Item> lookup, ItemLike... items) {
            throw Unimplemented.forMember("net/minecraft/advancements/predicates/ItemPredicate$Builder.of:(Lnet/minecraft/core/HolderGetter;[Lnet/minecraft/world/level/ItemLike;)Lnet/minecraft/advancements/predicates/ItemPredicate$Builder;");
        }

        public ItemPredicate.Builder of(HolderGetter<Item> lookup, TagKey<Item> tag) {
            throw Unimplemented.forMember("net/minecraft/advancements/predicates/ItemPredicate$Builder.of:(Lnet/minecraft/core/HolderGetter;Lnet/minecraft/tags/TagKey;)Lnet/minecraft/advancements/predicates/ItemPredicate$Builder;");
        }

        public ItemPredicate.Builder withComponents(DataComponentMatchers components) {
            throw Unimplemented.forMember("net/minecraft/advancements/predicates/ItemPredicate$Builder.withComponents:(Lnet/minecraft/advancements/predicates/DataComponentMatchers;)Lnet/minecraft/advancements/predicates/ItemPredicate$Builder;");
        }

        public ItemPredicate build() {
            throw Unimplemented.forMember("net/minecraft/advancements/predicates/ItemPredicate$Builder.build:()Lnet/minecraft/advancements/predicates/ItemPredicate;");
        }

        public Builder() {
        }
    }
}
