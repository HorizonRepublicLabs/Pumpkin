package net.minecraft.tags;

import java.util.List;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.common.extensions.ITagBuilderExtension;
import dev.pumpkin.shim.Unimplemented;

public class TagBuilder implements ITagBuilderExtension {

    public static TagBuilder create() {
        throw Unimplemented.forMember("net/minecraft/tags/TagBuilder.create:()Lnet/minecraft/tags/TagBuilder;");
    }

    public List<TagEntry> build() {
        throw Unimplemented.forMember("net/minecraft/tags/TagBuilder.build:()Ljava/util/List;");
    }

    public TagBuilder add(TagEntry entry) {
        throw Unimplemented.forMember("net/minecraft/tags/TagBuilder.add:(Lnet/minecraft/tags/TagEntry;)Lnet/minecraft/tags/TagBuilder;");
    }

    public TagBuilder addTag(Identifier id) {
        throw Unimplemented.forMember("net/minecraft/tags/TagBuilder.addTag:(Lnet/minecraft/resources/Identifier;)Lnet/minecraft/tags/TagBuilder;");
    }

    public TagBuilder addOptionalTag(Identifier id) {
        throw Unimplemented.forMember("net/minecraft/tags/TagBuilder.addOptionalTag:(Lnet/minecraft/resources/Identifier;)Lnet/minecraft/tags/TagBuilder;");
    }

    public TagBuilder remove(TagEntry entry) {
        throw Unimplemented.forMember("net/minecraft/tags/TagBuilder.remove:(Lnet/minecraft/tags/TagEntry;)Lnet/minecraft/tags/TagBuilder;");
    }

    public TagBuilder() {
    }
}
