package net.minecraft.tags;

import java.util.Collection;
import java.util.function.Consumer;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ExtraCodecs;
import dev.pumpkin.shim.Unimplemented;

public class TagEntry {

    private TagEntry(Identifier id, boolean tag, boolean required) {
        throw Unimplemented.forMember("net/minecraft/tags/TagEntry.<init>:(Lnet/minecraft/resources/Identifier;ZZ)V");
    }

    private TagEntry(ExtraCodecs.TagOrElementLocation elementOrTag, boolean required) {
        throw Unimplemented.forMember("net/minecraft/tags/TagEntry.<init>:(Lnet/minecraft/util/ExtraCodecs$TagOrElementLocation;Z)V");
    }

    public static TagEntry tag(Identifier id) {
        throw Unimplemented.forMember("net/minecraft/tags/TagEntry.tag:(Lnet/minecraft/resources/Identifier;)Lnet/minecraft/tags/TagEntry;");
    }

    public <T> boolean build(TagEntry.Lookup<T> lookup, Consumer<T> output) {
        throw Unimplemented.forMember("net/minecraft/tags/TagEntry.build:(Lnet/minecraft/tags/TagEntry$Lookup;Ljava/util/function/Consumer;)Z");
    }

    public void visitRequiredDependencies(Consumer<Identifier> output) {
        throw Unimplemented.forMember("net/minecraft/tags/TagEntry.visitRequiredDependencies:(Ljava/util/function/Consumer;)V");
    }

    public void visitOptionalDependencies(Consumer<Identifier> output) {
        throw Unimplemented.forMember("net/minecraft/tags/TagEntry.visitOptionalDependencies:(Ljava/util/function/Consumer;)V");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/tags/TagEntry.toString:()Ljava/lang/String;");
    }

    public Identifier getId() {
        throw Unimplemented.forMember("net/minecraft/tags/TagEntry.getId:()Lnet/minecraft/resources/Identifier;");
    }

    public interface Lookup<T> {

        T element(Identifier key, boolean required);

        Collection<T> tag(Identifier key);
    }

    public TagEntry() {
    }
}
