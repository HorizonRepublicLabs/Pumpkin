package net.neoforged.neoforge.common.extensions;

import net.minecraft.data.tags.TagAppender;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagEntry;
import net.minecraft.tags.TagKey;
import dev.pumpkin.shim.Unimplemented;

public interface ITagAppenderExtension<T> {

    default TagAppender<T> addTags(TagKey<T>... values) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/ITagAppenderExtension.addTags:([Lnet/minecraft/tags/TagKey;)Lnet/minecraft/data/tags/TagAppender;");
    }

    TagAppender<T> add(TagEntry entry);

    TagAppender<T> replace(boolean value);

    TagAppender<T> remove(final ResourceKey<T> element);

    TagAppender<T> remove(TagKey<T> tag);
}
