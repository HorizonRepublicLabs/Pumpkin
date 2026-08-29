package net.minecraft.server.packs.resources;

import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DynamicOps;
import java.util.Map;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.resources.FileToIdConverter;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.profiling.ProfilerFiller;
import dev.pumpkin.shim.Unimplemented;

public abstract class SimpleJsonResourceReloadListener<T> extends SimplePreparableReloadListener<Map<Identifier, T>> {

    protected SimpleJsonResourceReloadListener(HolderLookup.Provider registries, Codec<T> codec, ResourceKey<? extends Registry<T>> registryKey) {
        throw Unimplemented.forMember("net/minecraft/server/packs/resources/SimpleJsonResourceReloadListener.<init>:(Lnet/minecraft/core/HolderLookup$Provider;Lcom/mojang/serialization/Codec;Lnet/minecraft/resources/ResourceKey;)V");
    }

    protected SimpleJsonResourceReloadListener(Codec<T> codec, FileToIdConverter lister) {
        throw Unimplemented.forMember("net/minecraft/server/packs/resources/SimpleJsonResourceReloadListener.<init>:(Lcom/mojang/serialization/Codec;Lnet/minecraft/resources/FileToIdConverter;)V");
    }

    private SimpleJsonResourceReloadListener(DynamicOps<JsonElement> ops, Codec<T> codec, FileToIdConverter lister) {
        throw Unimplemented.forMember("net/minecraft/server/packs/resources/SimpleJsonResourceReloadListener.<init>:(Lcom/mojang/serialization/DynamicOps;Lcom/mojang/serialization/Codec;Lnet/minecraft/resources/FileToIdConverter;)V");
    }

    protected Map<Identifier, T> prepare(ResourceManager manager, ProfilerFiller profiler) {
        throw Unimplemented.forMember("net/minecraft/server/packs/resources/SimpleJsonResourceReloadListener.prepare:(Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)Ljava/util/Map;");
    }

    protected SimpleJsonResourceReloadListener() {
    }
}
