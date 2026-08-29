package net.minecraft.client.renderer.extract;

import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.state.level.LevelRenderState;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import dev.pumpkin.shim.Unimplemented;

public class LevelExtractor implements ResourceManagerReloadListener {

    public LevelExtractor(Minecraft minecraft, LevelRenderState levelRenderState, LevelRenderer levelRenderer) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/extract/LevelExtractor.<init>:(Lnet/minecraft/client/Minecraft;Lnet/minecraft/client/renderer/state/level/LevelRenderState;Lnet/minecraft/client/renderer/LevelRenderer;)V");
    }

    public void extract(DeltaTracker deltaTracker, Camera camera, float deltaPartialTick) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/extract/LevelExtractor.extract:(Lnet/minecraft/client/DeltaTracker;Lnet/minecraft/client/Camera;F)V");
    }

    public void onResourceManagerReload(ResourceManager resourceManager) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/extract/LevelExtractor.onResourceManagerReload:(Lnet/minecraft/server/packs/resources/ResourceManager;)V");
    }

    public LevelExtractor() {
    }
}
