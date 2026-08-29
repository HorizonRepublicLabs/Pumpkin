package net.minecraft.data.tags;

import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.neoforged.neoforge.common.extensions.ITagAppenderExtension;
import dev.pumpkin.shim.Unimplemented;

public interface TagAppender<T> extends ITagAppenderExtension<T> {

    TagAppender<T> add(ResourceKey<T> element);

    default TagAppender<T> add(ResourceKey<T>... elements) {
        throw Unimplemented.forMember("net/minecraft/data/tags/TagAppender.add:([Lnet/minecraft/resources/ResourceKey;)Lnet/minecraft/data/tags/TagAppender;");
    }

    TagAppender<T> addOptional(ResourceKey<T> element);

    TagAppender<T> addTag(TagKey<T> tag);

    TagAppender<T> addOptionalTag(TagKey<T> tag);
}
