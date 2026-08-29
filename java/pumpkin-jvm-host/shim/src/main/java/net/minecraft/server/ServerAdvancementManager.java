package net.minecraft.server;

import java.util.Map;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import dev.pumpkin.shim.Unimplemented;

public class ServerAdvancementManager extends SimpleJsonResourceReloadListener<Advancement> {

    public ServerAdvancementManager(HolderLookup.Provider registries) {
        throw Unimplemented.forMember("net/minecraft/server/ServerAdvancementManager.<init>:(Lnet/minecraft/core/HolderLookup$Provider;)V");
    }

    protected void apply(Map<Identifier, Advancement> preparations, ResourceManager manager, ProfilerFiller profiler) {
        throw Unimplemented.forMember("net/minecraft/server/ServerAdvancementManager.apply:(Ljava/util/Map;Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)V");
    }

    public AdvancementHolder get(Identifier id) {
        throw Unimplemented.forMember("net/minecraft/server/ServerAdvancementManager.get:(Lnet/minecraft/resources/Identifier;)Lnet/minecraft/advancements/AdvancementHolder;");
    }

    protected ServerAdvancementManager() {
    }
}
