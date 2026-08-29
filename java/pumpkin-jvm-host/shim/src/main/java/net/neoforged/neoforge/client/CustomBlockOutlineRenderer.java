package net.neoforged.neoforge.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.state.level.BlockOutlineRenderState;
import net.minecraft.client.renderer.state.level.LevelRenderState;

public interface CustomBlockOutlineRenderer {

    boolean render(BlockOutlineRenderState renderState, SubmitNodeCollector submitNodeCollector, PoseStack poseStack, LevelRenderState levelRenderState);
}
