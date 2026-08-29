package net.minecraft.data.tags;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagBuilder;
import net.minecraft.tags.TagKey;
import dev.pumpkin.shim.Unimplemented;

public abstract class TagsProvider<T> implements DataProvider {

    protected TagsProvider(PackOutput output, ResourceKey<? extends Registry<T>> registryKey, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        throw Unimplemented.forMember("net/minecraft/data/tags/TagsProvider.<init>:(Lnet/minecraft/data/PackOutput;Lnet/minecraft/resources/ResourceKey;Ljava/util/concurrent/CompletableFuture;)V");
    }

    protected TagsProvider(PackOutput output, ResourceKey<? extends Registry<T>> registryKey, CompletableFuture<HolderLookup.Provider> lookupProvider, String modId) {
        throw Unimplemented.forMember("net/minecraft/data/tags/TagsProvider.<init>:(Lnet/minecraft/data/PackOutput;Lnet/minecraft/resources/ResourceKey;Ljava/util/concurrent/CompletableFuture;Ljava/lang/String;)V");
    }

    protected TagsProvider(PackOutput output, ResourceKey<? extends Registry<T>> registryKey, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagsProvider.TagLookup<T>> parentProvider) {
        throw Unimplemented.forMember("net/minecraft/data/tags/TagsProvider.<init>:(Lnet/minecraft/data/PackOutput;Lnet/minecraft/resources/ResourceKey;Ljava/util/concurrent/CompletableFuture;Ljava/util/concurrent/CompletableFuture;)V");
    }

    protected TagsProvider(PackOutput output, ResourceKey<? extends Registry<T>> registryKey, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagsProvider.TagLookup<T>> parentProvider, String modId) {
        throw Unimplemented.forMember("net/minecraft/data/tags/TagsProvider.<init>:(Lnet/minecraft/data/PackOutput;Lnet/minecraft/resources/ResourceKey;Ljava/util/concurrent/CompletableFuture;Ljava/util/concurrent/CompletableFuture;Ljava/lang/String;)V");
    }

    public String getName() {
        throw Unimplemented.forMember("net/minecraft/data/tags/TagsProvider.getName:()Ljava/lang/String;");
    }

    protected abstract void addTags(HolderLookup.Provider registries);

    public CompletableFuture<?> run(CachedOutput cache) {
        throw Unimplemented.forMember("net/minecraft/data/tags/TagsProvider.run:(Lnet/minecraft/data/CachedOutput;)Ljava/util/concurrent/CompletableFuture;");
    }

    protected TagAppender<T> tag(TagKey<T> tag) {
        throw Unimplemented.forMember("net/minecraft/data/tags/TagsProvider.tag:(Lnet/minecraft/tags/TagKey;)Lnet/minecraft/data/tags/TagAppender;");
    }

    protected TagAppender<T> tag(TagKey<T> tag, boolean replace) {
        throw Unimplemented.forMember("net/minecraft/data/tags/TagsProvider.tag:(Lnet/minecraft/tags/TagKey;Z)Lnet/minecraft/data/tags/TagAppender;");
    }

    public interface TagLookup<T> extends Function<TagKey<T>, Optional<TagBuilder>> {

        default boolean contains(TagKey<T> key) {
            throw Unimplemented.forMember("net/minecraft/data/tags/TagsProvider$TagLookup.contains:(Lnet/minecraft/tags/TagKey;)Z");
        }
    }

    public TagsProvider() {
    }
}
