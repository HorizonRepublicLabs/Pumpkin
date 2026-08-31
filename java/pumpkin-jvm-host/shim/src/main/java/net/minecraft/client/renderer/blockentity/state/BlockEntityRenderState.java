package net.minecraft.client.renderer.blockentity.state;

import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.core.BlockPos;

public class BlockEntityRenderState {

    public BlockPos blockPos;

    public int lightCoords;

    public ModelFeatureRenderer.CrumblingOverlay breakProgress;

    public BlockEntityRenderState() {
    }
}
