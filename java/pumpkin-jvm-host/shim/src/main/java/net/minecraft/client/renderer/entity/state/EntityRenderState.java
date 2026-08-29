package net.minecraft.client.renderer.entity.state;

import net.minecraft.world.phys.shapes.VoxelShape;

public class EntityRenderState extends net.neoforged.neoforge.client.renderstate.BaseRenderState {

    public float boundingBoxHeight;

    public int lightCoords;

    public static class LeashState {

        protected LeashState() {
        }
    }

    public record ShadowPiece(float relativeX, float relativeY, float relativeZ, VoxelShape shapeBelow, float alpha) {
    }

    protected EntityRenderState() {
    }
}
