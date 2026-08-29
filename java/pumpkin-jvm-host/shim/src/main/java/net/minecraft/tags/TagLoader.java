package net.minecraft.tags;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;
import java.util.function.Consumer;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.DependencySorter;
import dev.pumpkin.shim.Unimplemented;

public class TagLoader<T> {

    public TagLoader(TagLoader.ElementLookup<T> elementLookup, String directory) {
        throw Unimplemented.forMember("net/minecraft/tags/TagLoader.<init>:(Lnet/minecraft/tags/TagLoader$ElementLookup;Ljava/lang/String;)V");
    }

    public Map<Identifier, List<TagLoader.EntryWithSource>> load(ResourceManager resourceManager) {
        throw Unimplemented.forMember("net/minecraft/tags/TagLoader.load:(Lnet/minecraft/server/packs/resources/ResourceManager;)Ljava/util/Map;");
    }

    public interface ElementLookup<T> {

        Optional<? extends T> get(Identifier id, boolean required);
    }

    public static record EntryWithSource(TagEntry entry, String source, boolean remove) {

        public EntryWithSource(TagEntry entry, String source) {
            this((TagEntry) null, (String) null, (boolean) false);
            throw Unimplemented.forMember("net/minecraft/tags/TagLoader$EntryWithSource.<init>:(Lnet/minecraft/tags/TagEntry;Ljava/lang/String;)V");
        }

        public String toString() {
            throw Unimplemented.forMember("net/minecraft/tags/TagLoader$EntryWithSource.toString:()Ljava/lang/String;");
        }
    }

    public record LoadResult<T>(ResourceKey<? extends Registry<T>> key, Map<TagKey<T>, List<Holder<T>>> tags) {
    }

    private record SortingEntry(List<TagLoader.EntryWithSource> entries) implements DependencySorter.Entry<Identifier> {

        public void visitRequiredDependencies(Consumer<Identifier> output) {
            throw Unimplemented.forMember("net/minecraft/tags/TagLoader$SortingEntry.visitRequiredDependencies:(Ljava/util/function/Consumer;)V");
        }

        public void visitOptionalDependencies(Consumer<Identifier> output) {
            throw Unimplemented.forMember("net/minecraft/tags/TagLoader$SortingEntry.visitOptionalDependencies:(Ljava/util/function/Consumer;)V");
        }
    }

    public TagLoader() {
    }
}
