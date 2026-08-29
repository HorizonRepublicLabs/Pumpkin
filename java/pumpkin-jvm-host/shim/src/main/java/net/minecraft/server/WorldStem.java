package net.minecraft.server;

import net.minecraft.core.LayeredRegistryAccess;
import net.minecraft.server.packs.resources.CloseableResourceManager;
import net.minecraft.world.level.storage.LevelDataAndDimensions;
import dev.pumpkin.shim.Unimplemented;

public record WorldStem(CloseableResourceManager resourceManager, ReloadableServerResources dataPackResources, LayeredRegistryAccess<RegistryLayer> registries, LevelDataAndDimensions.WorldDataAndGenSettings worldDataAndGenSettings) implements AutoCloseable {

    public void close() {
        throw Unimplemented.forMember("net/minecraft/server/WorldStem.close:()V");
    }
}
