package net.neoforged.neoforge.client.event;

import net.minecraft.client.Camera;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.fog.FogData;
import net.minecraft.client.renderer.fog.environment.FogEnvironment;
import net.minecraft.world.level.material.FogType;
import net.neoforged.bus.api.Event;
import org.joml.Vector4f;
import dev.pumpkin.shim.Unimplemented;

public abstract class ViewportEvent extends Event {

    public ViewportEvent(GameRenderer renderer, Camera camera, double partialTick) {
    }

    public Camera getCamera() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/ViewportEvent.getCamera:()Lnet/minecraft/client/Camera;");
    }

    public static class RenderFog extends ViewportEvent {

        public RenderFog(FogEnvironment environment, FogType type, Camera camera, float partialTicks, FogData fogData) {
        }

        public FogType getType() {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/event/ViewportEvent$RenderFog.getType:()Lnet/minecraft/world/level/material/FogType;");
        }

        public float getFarPlaneDistance() {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/event/ViewportEvent$RenderFog.getFarPlaneDistance:()F");
        }

        public void setFarPlaneDistance(float distance) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/event/ViewportEvent$RenderFog.setFarPlaneDistance:(F)V");
        }

        public void setNearPlaneDistance(float distance) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/event/ViewportEvent$RenderFog.setNearPlaneDistance:(F)V");
        }

        public void scaleFarPlaneDistance(float factor) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/event/ViewportEvent$RenderFog.scaleFarPlaneDistance:(F)V");
        }

        public RenderFog() {
        }
    }

    public static class ComputeFogColor extends ViewportEvent {

        public ComputeFogColor(Camera camera, float partialTicks, Vector4f color) {
        }

        public float getRed() {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/event/ViewportEvent$ComputeFogColor.getRed:()F");
        }

        public void setRed(float red) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/event/ViewportEvent$ComputeFogColor.setRed:(F)V");
        }

        public float getGreen() {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/event/ViewportEvent$ComputeFogColor.getGreen:()F");
        }

        public void setGreen(float green) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/event/ViewportEvent$ComputeFogColor.setGreen:(F)V");
        }

        public float getBlue() {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/event/ViewportEvent$ComputeFogColor.getBlue:()F");
        }

        public void setBlue(float blue) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/event/ViewportEvent$ComputeFogColor.setBlue:(F)V");
        }

        public ComputeFogColor() {
        }
    }

    public static class ComputeCameraAngles extends ViewportEvent {

        public ComputeCameraAngles(Camera camera, double renderPartialTicks, float yaw, float pitch, float roll) {
        }

        public float getPitch() {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/event/ViewportEvent$ComputeCameraAngles.getPitch:()F");
        }

        public ComputeCameraAngles() {
        }
    }

    public static class ComputeFov extends ViewportEvent {

        public ComputeFov(GameRenderer renderer, Camera camera, float renderPartialTicks, float fov) {
        }

        public ComputeFov() {
        }
    }

    public ViewportEvent() {
    }
}
