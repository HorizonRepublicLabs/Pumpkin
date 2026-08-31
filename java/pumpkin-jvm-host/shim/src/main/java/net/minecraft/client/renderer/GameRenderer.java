package net.minecraft.client.renderer;

import com.mojang.blaze3d.platform.Lighting;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.state.GameRenderState;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.waypoints.TrackedWaypoint;
import dev.pumpkin.shim.Unimplemented;

public class GameRenderer implements AutoCloseable, TrackedWaypoint.Projector {

    private final GameRenderState gameRenderState = null;

    private final Lighting lighting = null;

    public GameRenderer(Minecraft minecraft, ItemInHandRenderer itemInHandRenderer, ModelManager modelManager) {
    }

    public void close() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/GameRenderer.close:()V");
    }

    public GameRenderState gameRenderState() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/GameRenderer.gameRenderState:()Lnet/minecraft/client/renderer/state/GameRenderState;");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/GameRenderer.tick:()V");
    }

    public void update(DeltaTracker deltaTracker) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/GameRenderer.update:(Lnet/minecraft/client/DeltaTracker;)V");
    }

    public void extract(DeltaTracker deltaTracker, boolean advanceGameTime) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/GameRenderer.extract:(Lnet/minecraft/client/DeltaTracker;Z)V");
    }

    public void render(DeltaTracker deltaTracker, boolean advanceGameTime) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/GameRenderer.render:(Lnet/minecraft/client/DeltaTracker;Z)V");
    }

    public Vec3 projectPointToScreen(Vec3 point) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/GameRenderer.projectPointToScreen:(Lnet/minecraft/world/phys/Vec3;)Lnet/minecraft/world/phys/Vec3;");
    }

    public double projectHorizonToScreen() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/GameRenderer.projectHorizonToScreen:()D");
    }

    public Lighting lighting() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/GameRenderer.lighting:()Lcom/mojang/blaze3d/platform/Lighting;");
    }

    public GameRenderer() {
    }
}
