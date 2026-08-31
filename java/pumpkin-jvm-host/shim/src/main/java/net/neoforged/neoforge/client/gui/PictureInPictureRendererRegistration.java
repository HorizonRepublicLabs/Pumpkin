package net.neoforged.neoforge.client.gui;

import java.util.function.Supplier;
import net.minecraft.client.gui.render.pip.PictureInPictureRenderer;
import net.minecraft.client.renderer.state.gui.pip.PictureInPictureRenderState;

public record PictureInPictureRendererRegistration<T extends PictureInPictureRenderState>(Class<T> stateClass, Supplier<PictureInPictureRenderer<T>> factory) {
}
