package net.neoforged.neoforge.common.extensions;

import java.util.List;
import java.util.Map;
import net.minecraft.core.Holder;
import net.minecraft.tags.TagKey;

public interface PendingTagsExtension<T> {

    Map<TagKey<T>, List<Holder<T>>> contents();
}
