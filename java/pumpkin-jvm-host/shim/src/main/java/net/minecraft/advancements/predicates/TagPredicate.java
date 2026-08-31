package net.minecraft.advancements.predicates;

import net.minecraft.core.Holder;
import net.minecraft.tags.TagKey;
import dev.pumpkin.shim.Unimplemented;

public record TagPredicate<T>(TagKey<T> tag, boolean expected) {

    public static <T> TagPredicate<T> is(TagKey<T> tag) {
        throw Unimplemented.forMember("net/minecraft/advancements/predicates/TagPredicate.is:(Lnet/minecraft/tags/TagKey;)Lnet/minecraft/advancements/predicates/TagPredicate;");
    }

    public boolean matches(Holder<T> holder) {
        throw Unimplemented.forMember("net/minecraft/advancements/predicates/TagPredicate.matches:(Lnet/minecraft/core/Holder;)Z");
    }
}
