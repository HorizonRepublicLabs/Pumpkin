package net.minecraft.client.gui;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.textures.GpuSampler;
import com.mojang.blaze3d.textures.GpuTextureView;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.navigation.ScreenRectangle;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipPositioner;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.state.MapRenderState;
import net.minecraft.client.renderer.state.gui.GuiRenderState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.Identifier;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.joml.Matrix3x2fStack;
import org.joml.Quaternionfc;
import org.joml.Vector3fc;
import net.neoforged.neoforge.client.extensions.GuiGraphicsExtractorExtension;
import dev.pumpkin.shim.Unimplemented;

public class GuiGraphicsExtractor implements GuiGraphicsExtractorExtension {

    private final Matrix3x2fStack pose = null;

    private GuiGraphicsExtractor(Minecraft minecraft, Matrix3x2fStack pose, GuiRenderState guiRenderState, int mouseX, int mouseY) {
        throw Unimplemented.forMember("net/minecraft/client/gui/GuiGraphicsExtractor.<init>:(Lnet/minecraft/client/Minecraft;Lorg/joml/Matrix3x2fStack;Lnet/minecraft/client/renderer/state/gui/GuiRenderState;II)V");
    }

    public GuiGraphicsExtractor(Minecraft minecraft, GuiRenderState guiRenderState, int mouseX, int mouseY) {
        throw Unimplemented.forMember("net/minecraft/client/gui/GuiGraphicsExtractor.<init>:(Lnet/minecraft/client/Minecraft;Lnet/minecraft/client/renderer/state/gui/GuiRenderState;II)V");
    }

    public Matrix3x2fStack pose() {
        throw Unimplemented.forMember("net/minecraft/client/gui/GuiGraphicsExtractor.pose:()Lorg/joml/Matrix3x2fStack;");
    }

    public void enableScissor(int x0, int y0, int x1, int y1) {
        throw Unimplemented.forMember("net/minecraft/client/gui/GuiGraphicsExtractor.enableScissor:(IIII)V");
    }

    public void disableScissor() {
        throw Unimplemented.forMember("net/minecraft/client/gui/GuiGraphicsExtractor.disableScissor:()V");
    }

    public void fill(int x0, int y0, int x1, int y1, int col) {
        throw Unimplemented.forMember("net/minecraft/client/gui/GuiGraphicsExtractor.fill:(IIIII)V");
    }

    public void text(Font font, String str, int x, int y, int color) {
        throw Unimplemented.forMember("net/minecraft/client/gui/GuiGraphicsExtractor.text:(Lnet/minecraft/client/gui/Font;Ljava/lang/String;III)V");
    }

    public void text(Font font, String str, int x, int y, int color, boolean dropShadow) {
        throw Unimplemented.forMember("net/minecraft/client/gui/GuiGraphicsExtractor.text:(Lnet/minecraft/client/gui/Font;Ljava/lang/String;IIIZ)V");
    }

    public void text(Font font, FormattedCharSequence str, int x, int y, int color) {
        throw Unimplemented.forMember("net/minecraft/client/gui/GuiGraphicsExtractor.text:(Lnet/minecraft/client/gui/Font;Lnet/minecraft/util/FormattedCharSequence;III)V");
    }

    public void text(Font font, FormattedCharSequence str, int x, int y, int color, boolean dropShadow) {
        throw Unimplemented.forMember("net/minecraft/client/gui/GuiGraphicsExtractor.text:(Lnet/minecraft/client/gui/Font;Lnet/minecraft/util/FormattedCharSequence;IIIZ)V");
    }

    public void text(Font font, Component str, int x, int y, int color) {
        throw Unimplemented.forMember("net/minecraft/client/gui/GuiGraphicsExtractor.text:(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/Component;III)V");
    }

    public void text(Font font, Component str, int x, int y, int color, boolean dropShadow) {
        throw Unimplemented.forMember("net/minecraft/client/gui/GuiGraphicsExtractor.text:(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/Component;IIIZ)V");
    }

    public void blit(RenderPipeline renderPipeline, Identifier texture, int x, int y, float u, float v, int width, int height, int textureWidth, int textureHeight, int color) {
        throw Unimplemented.forMember("net/minecraft/client/gui/GuiGraphicsExtractor.blit:(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIFFIIIII)V");
    }

    public void blit(RenderPipeline renderPipeline, Identifier texture, int x, int y, float u, float v, int width, int height, int textureWidth, int textureHeight) {
        throw Unimplemented.forMember("net/minecraft/client/gui/GuiGraphicsExtractor.blit:(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIFFIIII)V");
    }

    public void blit(RenderPipeline renderPipeline, Identifier texture, int x, int y, float u, float v, int width, int height, int srcWidth, int srcHeight, int textureWidth, int textureHeight) {
        throw Unimplemented.forMember("net/minecraft/client/gui/GuiGraphicsExtractor.blit:(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIFFIIIIII)V");
    }

    public void blit(RenderPipeline renderPipeline, Identifier texture, int x, int y, float u, float v, int width, int height, int srcWidth, int srcHeight, int textureWidth, int textureHeight, int color) {
        throw Unimplemented.forMember("net/minecraft/client/gui/GuiGraphicsExtractor.blit:(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIFFIIIIIII)V");
    }

    public void blit(Identifier location, int x0, int y0, int x1, int y1, float u0, float u1, float v0, float v1) {
        throw Unimplemented.forMember("net/minecraft/client/gui/GuiGraphicsExtractor.blit:(Lnet/minecraft/resources/Identifier;IIIIFFFF)V");
    }

    public void blit(GpuTextureView textureView, GpuSampler sampler, int x0, int y0, int x1, int y1, float u0, float u1, float v0, float v1) {
        throw Unimplemented.forMember("net/minecraft/client/gui/GuiGraphicsExtractor.blit:(Lcom/mojang/blaze3d/textures/GpuTextureView;Lcom/mojang/blaze3d/textures/GpuSampler;IIIIFFFF)V");
    }

    public void blitSprite(RenderPipeline renderPipeline, Identifier location, int x, int y, int width, int height) {
        throw Unimplemented.forMember("net/minecraft/client/gui/GuiGraphicsExtractor.blitSprite:(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIII)V");
    }

    public void blitSprite(RenderPipeline renderPipeline, Identifier location, int x, int y, int width, int height, float alpha) {
        throw Unimplemented.forMember("net/minecraft/client/gui/GuiGraphicsExtractor.blitSprite:(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIIIF)V");
    }

    public void blitSprite(RenderPipeline renderPipeline, Identifier location, int x, int y, int width, int height, int color) {
        throw Unimplemented.forMember("net/minecraft/client/gui/GuiGraphicsExtractor.blitSprite:(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIIII)V");
    }

    public void blitSprite(RenderPipeline renderPipeline, Identifier location, int spriteWidth, int spriteHeight, int textureX, int textureY, int x, int y, int width, int height) {
        throw Unimplemented.forMember("net/minecraft/client/gui/GuiGraphicsExtractor.blitSprite:(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIIIIIII)V");
    }

    public void blitSprite(RenderPipeline renderPipeline, Identifier location, int spriteWidth, int spriteHeight, int textureX, int textureY, int x, int y, int width, int height, int color) {
        throw Unimplemented.forMember("net/minecraft/client/gui/GuiGraphicsExtractor.blitSprite:(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIIIIIIII)V");
    }

    public void blitSprite(RenderPipeline renderPipeline, TextureAtlasSprite sprite, int x, int y, int width, int height) {
        throw Unimplemented.forMember("net/minecraft/client/gui/GuiGraphicsExtractor.blitSprite:(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/client/renderer/texture/TextureAtlasSprite;IIII)V");
    }

    public void blitSprite(RenderPipeline renderPipeline, TextureAtlasSprite sprite, int x, int y, int width, int height, int color) {
        throw Unimplemented.forMember("net/minecraft/client/gui/GuiGraphicsExtractor.blitSprite:(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/client/renderer/texture/TextureAtlasSprite;IIIII)V");
    }

    private void blitSprite(RenderPipeline renderPipeline, TextureAtlasSprite sprite, int spriteWidth, int spriteHeight, int textureX, int textureY, int x, int y, int width, int height, int color) {
        throw Unimplemented.forMember("net/minecraft/client/gui/GuiGraphicsExtractor.blitSprite:(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/client/renderer/texture/TextureAtlasSprite;IIIIIIIII)V");
    }

    public void item(ItemStack itemStack, int x, int y) {
        throw Unimplemented.forMember("net/minecraft/client/gui/GuiGraphicsExtractor.item:(Lnet/minecraft/world/item/ItemStack;II)V");
    }

    public void item(ItemStack itemStack, int x, int y, int seed) {
        throw Unimplemented.forMember("net/minecraft/client/gui/GuiGraphicsExtractor.item:(Lnet/minecraft/world/item/ItemStack;III)V");
    }

    public void item(LivingEntity owner, ItemStack itemStack, int x, int y, int seed) {
        throw Unimplemented.forMember("net/minecraft/client/gui/GuiGraphicsExtractor.item:(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;III)V");
    }

    private void item(LivingEntity owner, Level level, ItemStack itemStack, int x, int y, int seed) {
        throw Unimplemented.forMember("net/minecraft/client/gui/GuiGraphicsExtractor.item:(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/item/ItemStack;III)V");
    }

    public void fakeItem(ItemStack itemStack, int x, int y) {
        throw Unimplemented.forMember("net/minecraft/client/gui/GuiGraphicsExtractor.fakeItem:(Lnet/minecraft/world/item/ItemStack;II)V");
    }

    public void fakeItem(ItemStack itemStack, int x, int y, int seed) {
        throw Unimplemented.forMember("net/minecraft/client/gui/GuiGraphicsExtractor.fakeItem:(Lnet/minecraft/world/item/ItemStack;III)V");
    }

    public void itemDecorations(Font font, ItemStack itemStack, int x, int y) {
        throw Unimplemented.forMember("net/minecraft/client/gui/GuiGraphicsExtractor.itemDecorations:(Lnet/minecraft/client/gui/Font;Lnet/minecraft/world/item/ItemStack;II)V");
    }

    public void itemDecorations(Font font, ItemStack itemStack, int x, int y, String countText) {
        throw Unimplemented.forMember("net/minecraft/client/gui/GuiGraphicsExtractor.itemDecorations:(Lnet/minecraft/client/gui/Font;Lnet/minecraft/world/item/ItemStack;IILjava/lang/String;)V");
    }

    public void map(MapRenderState mapRenderState) {
        throw Unimplemented.forMember("net/minecraft/client/gui/GuiGraphicsExtractor.map:(Lnet/minecraft/client/renderer/state/MapRenderState;)V");
    }

    public void entity(EntityRenderState renderState, float scale, Vector3fc translation, Quaternionfc rotation, Quaternionfc overrideCameraAngle, int x0, int y0, int x1, int y1) {
        throw Unimplemented.forMember("net/minecraft/client/gui/GuiGraphicsExtractor.entity:(Lnet/minecraft/client/renderer/entity/state/EntityRenderState;FLorg/joml/Vector3fc;Lorg/joml/Quaternionfc;Lorg/joml/Quaternionfc;IIII)V");
    }

    public void setTooltipForNextFrame(Component component, int x, int y) {
        throw Unimplemented.forMember("net/minecraft/client/gui/GuiGraphicsExtractor.setTooltipForNextFrame:(Lnet/minecraft/network/chat/Component;II)V");
    }

    public void setTooltipForNextFrame(List<FormattedCharSequence> formattedCharSequences, int x, int y) {
        throw Unimplemented.forMember("net/minecraft/client/gui/GuiGraphicsExtractor.setTooltipForNextFrame:(Ljava/util/List;II)V");
    }

    public void setTooltipForNextFrame(Font font, ItemStack itemStack, int xo, int yo) {
        throw Unimplemented.forMember("net/minecraft/client/gui/GuiGraphicsExtractor.setTooltipForNextFrame:(Lnet/minecraft/client/gui/Font;Lnet/minecraft/world/item/ItemStack;II)V");
    }

    public void setTooltipForNextFrame(Font font, List<Component> textComponents, Optional<TooltipComponent> tooltipComponent, ItemStack stack, int mouseX, int mouseY) {
        throw Unimplemented.forMember("net/minecraft/client/gui/GuiGraphicsExtractor.setTooltipForNextFrame:(Lnet/minecraft/client/gui/Font;Ljava/util/List;Ljava/util/Optional;Lnet/minecraft/world/item/ItemStack;II)V");
    }

    public void setTooltipForNextFrame(Font font, List<Component> textComponents, Optional<TooltipComponent> tooltipComponent, ItemStack stack, int mouseX, int mouseY, Identifier backgroundTexture) {
        throw Unimplemented.forMember("net/minecraft/client/gui/GuiGraphicsExtractor.setTooltipForNextFrame:(Lnet/minecraft/client/gui/Font;Ljava/util/List;Ljava/util/Optional;Lnet/minecraft/world/item/ItemStack;IILnet/minecraft/resources/Identifier;)V");
    }

    public void setTooltipForNextFrame(Font font, List<Component> texts, Optional<TooltipComponent> optionalImage, int xo, int yo) {
        throw Unimplemented.forMember("net/minecraft/client/gui/GuiGraphicsExtractor.setTooltipForNextFrame:(Lnet/minecraft/client/gui/Font;Ljava/util/List;Ljava/util/Optional;II)V");
    }

    public void setTooltipForNextFrame(Font font, List<Component> texts, Optional<TooltipComponent> optionalImage, int xo, int yo, Identifier style) {
        throw Unimplemented.forMember("net/minecraft/client/gui/GuiGraphicsExtractor.setTooltipForNextFrame:(Lnet/minecraft/client/gui/Font;Ljava/util/List;Ljava/util/Optional;IILnet/minecraft/resources/Identifier;)V");
    }

    public void setTooltipForNextFrame(Font font, List<FormattedCharSequence> tooltip, Optional<TooltipComponent> component, ClientTooltipPositioner positioner, int xo, int yo, boolean replaceExisting, Identifier style) {
        throw Unimplemented.forMember("net/minecraft/client/gui/GuiGraphicsExtractor.setTooltipForNextFrame:(Lnet/minecraft/client/gui/Font;Ljava/util/List;Ljava/util/Optional;Lnet/minecraft/client/gui/screens/inventory/tooltip/ClientTooltipPositioner;IIZLnet/minecraft/resources/Identifier;)V");
    }

    public void setTooltipForNextFrame(Font font, Component text, int xo, int yo) {
        throw Unimplemented.forMember("net/minecraft/client/gui/GuiGraphicsExtractor.setTooltipForNextFrame:(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/Component;II)V");
    }

    public void setTooltipForNextFrame(Font font, Component text, int xo, int yo, Identifier style) {
        throw Unimplemented.forMember("net/minecraft/client/gui/GuiGraphicsExtractor.setTooltipForNextFrame:(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/Component;IILnet/minecraft/resources/Identifier;)V");
    }

    public void setTooltipForNextFrame(Font font, List<? extends FormattedCharSequence> lines, int xo, int yo) {
        throw Unimplemented.forMember("net/minecraft/client/gui/GuiGraphicsExtractor.setTooltipForNextFrame:(Lnet/minecraft/client/gui/Font;Ljava/util/List;II)V");
    }

    public void setTooltipForNextFrame(Font font, List<? extends FormattedCharSequence> lines, int xo, int yo, Identifier style) {
        throw Unimplemented.forMember("net/minecraft/client/gui/GuiGraphicsExtractor.setTooltipForNextFrame:(Lnet/minecraft/client/gui/Font;Ljava/util/List;IILnet/minecraft/resources/Identifier;)V");
    }

    public void setTooltipForNextFrame(Font font, List<FormattedCharSequence> tooltip, ClientTooltipPositioner positioner, int xo, int yo, boolean replaceExisting) {
        throw Unimplemented.forMember("net/minecraft/client/gui/GuiGraphicsExtractor.setTooltipForNextFrame:(Lnet/minecraft/client/gui/Font;Ljava/util/List;Lnet/minecraft/client/gui/screens/inventory/tooltip/ClientTooltipPositioner;IIZ)V");
    }

    public enum HoveredTextEffects {

        NONE, TOOLTIP_ONLY, TOOLTIP_AND_CURSOR
    }

    private class RenderingTextCollector implements ActiveTextCollector, Consumer<Style> {

        private RenderingTextCollector(ActiveTextCollector.Parameters initialParameters, GuiGraphicsExtractor.HoveredTextEffects hoveredTextEffects, Consumer<Style> additonalConsumer) {
            throw Unimplemented.forMember("net/minecraft/client/gui/GuiGraphicsExtractor$RenderingTextCollector.<init>:(Lnet/minecraft/client/gui/ActiveTextCollector$Parameters;Lnet/minecraft/client/gui/GuiGraphicsExtractor$HoveredTextEffects;Ljava/util/function/Consumer;)V");
        }

        public ActiveTextCollector.Parameters defaultParameters() {
            throw Unimplemented.forMember("net/minecraft/client/gui/GuiGraphicsExtractor$RenderingTextCollector.defaultParameters:()Lnet/minecraft/client/gui/ActiveTextCollector$Parameters;");
        }

        public void defaultParameters(ActiveTextCollector.Parameters newParameters) {
            throw Unimplemented.forMember("net/minecraft/client/gui/GuiGraphicsExtractor$RenderingTextCollector.defaultParameters:(Lnet/minecraft/client/gui/ActiveTextCollector$Parameters;)V");
        }

        public void accept(Style style) {
            throw Unimplemented.forMember("net/minecraft/client/gui/GuiGraphicsExtractor$RenderingTextCollector.accept:(Lnet/minecraft/network/chat/Style;)V");
        }

        public void accept(TextAlignment alignment, int anchorX, int y, ActiveTextCollector.Parameters parameters, FormattedCharSequence text) {
            throw Unimplemented.forMember("net/minecraft/client/gui/GuiGraphicsExtractor$RenderingTextCollector.accept:(Lnet/minecraft/client/gui/TextAlignment;IILnet/minecraft/client/gui/ActiveTextCollector$Parameters;Lnet/minecraft/util/FormattedCharSequence;)V");
        }

        public void acceptScrolling(Component message, int centerX, int left, int right, int top, int bottom, ActiveTextCollector.Parameters parameters) {
            throw Unimplemented.forMember("net/minecraft/client/gui/GuiGraphicsExtractor$RenderingTextCollector.acceptScrolling:(Lnet/minecraft/network/chat/Component;IIIIILnet/minecraft/client/gui/ActiveTextCollector$Parameters;)V");
        }

        protected RenderingTextCollector() {
        }
    }

    private static class ScissorStack {

        private ScissorStack(ScreenRectangle screenSize) {
            throw Unimplemented.forMember("net/minecraft/client/gui/GuiGraphicsExtractor$ScissorStack.<init>:(Lnet/minecraft/client/gui/navigation/ScreenRectangle;)V");
        }

        public void push(ScreenRectangle rectangle) {
            throw Unimplemented.forMember("net/minecraft/client/gui/GuiGraphicsExtractor$ScissorStack.push:(Lnet/minecraft/client/gui/navigation/ScreenRectangle;)V");
        }

        public void pop() {
            throw Unimplemented.forMember("net/minecraft/client/gui/GuiGraphicsExtractor$ScissorStack.pop:()V");
        }

        protected ScissorStack() {
        }
    }

    protected GuiGraphicsExtractor() {
    }
}
