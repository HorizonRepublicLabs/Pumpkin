package net.minecraft.core;

import java.util.stream.Stream;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.neoforged.neoforge.common.extensions.TypedInstanceExtension;
import dev.pumpkin.shim.Unimplemented;

public interface TypedInstance<T> extends TypedInstanceExtension<T> {

    Holder<T> typeHolder();

    default Stream<TagKey<T>> tags() {
        throw Unimplemented.forMember("net/minecraft/core/TypedInstance.tags:()Ljava/util/stream/Stream;");
    }

    default boolean is(TagKey<T> tag) {
        throw Unimplemented.forMember("net/minecraft/core/TypedInstance.is:(Lnet/minecraft/tags/TagKey;)Z");
    }

    default boolean is(HolderSet<T> set) {
        throw Unimplemented.forMember("net/minecraft/core/TypedInstance.is:(Lnet/minecraft/core/HolderSet;)Z");
    }

    default boolean is(T rawType) {
        throw Unimplemented.forMember("net/minecraft/core/TypedInstance.is:(Ljava/lang/Object;)Z");
    }

    default boolean is(Holder<T> type) {
        throw Unimplemented.forMember("net/minecraft/core/TypedInstance.is:(Lnet/minecraft/core/Holder;)Z");
    }

    default boolean is(ResourceKey<T> type) {
        throw Unimplemented.forMember("net/minecraft/core/TypedInstance.is:(Lnet/minecraft/resources/ResourceKey;)Z");
    }
}
