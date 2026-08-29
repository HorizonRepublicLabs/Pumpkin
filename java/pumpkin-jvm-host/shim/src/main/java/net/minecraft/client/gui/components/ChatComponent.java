package net.minecraft.client.gui.components;

import java.util.List;
import java.util.function.Consumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ActiveTextCollector;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.multiplayer.chat.GuiMessage;
import net.minecraft.client.multiplayer.chat.GuiMessageTag;
import net.minecraft.network.chat.MessageSignature;
import net.minecraft.network.chat.Style;
import net.minecraft.util.FormattedCharSequence;
import org.joml.Matrix3x2f;
import dev.pumpkin.shim.Unimplemented;

public class ChatComponent {

    public ChatComponent(Minecraft minecraft) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/ChatComponent.<init>:(Lnet/minecraft/client/Minecraft;)V");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/ChatComponent.tick:()V");
    }

    private void extractRenderState(ChatComponent.ChatGraphicsAccess graphics, int screenHeight, int ticks, ChatComponent.DisplayMode displayMode) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/ChatComponent.extractRenderState:(Lnet/minecraft/client/gui/components/ChatComponent$ChatGraphicsAccess;IILnet/minecraft/client/gui/components/ChatComponent$DisplayMode;)V");
    }

    private int getWidth() {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/ChatComponent.getWidth:()I");
    }

    private int getHeight() {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/ChatComponent.getHeight:()I");
    }

    public static int getWidth(double pct) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/ChatComponent.getWidth:(D)I");
    }

    public static int getHeight(double pct) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/ChatComponent.getHeight:(D)I");
    }

    private interface AlphaCalculator {

        float calculate(GuiMessage.Line message);
    }

    public interface ChatGraphicsAccess {

        void updatePose(final Consumer<Matrix3x2f> updater);

        void fill(int x0, int y0, int x1, int y1, int color);

        boolean handleMessage(int textTop, float opacity, FormattedCharSequence message);

        void handleTag(int x0, int y0, int x1, int y1, float opacity, GuiMessageTag tag);

        void handleTagIcon(int left, int bottom, boolean forceVisible, GuiMessageTag tag, GuiMessageTag.Icon icon);
    }

    public enum ChatMethod {

        MESSAGE {

            public boolean isDraftRestorable(ChatComponent.Draft draft) {
                throw Unimplemented.forMember("net/minecraft/client/gui/components/ChatComponent$ChatMethod$MESSAGE.isDraftRestorable:()");
            }
        }
        , COMMAND {

            public boolean isDraftRestorable(ChatComponent.Draft draft) {
                throw Unimplemented.forMember("net/minecraft/client/gui/components/ChatComponent$ChatMethod$COMMAND.isDraftRestorable:()");
            }
        }
        ;

        public abstract boolean isDraftRestorable(ChatComponent.Draft draft);
    }

    private static class ClickableTextOnlyGraphicsAccess implements ChatComponent.ChatGraphicsAccess {

        public ClickableTextOnlyGraphicsAccess(ActiveTextCollector output) {
            throw Unimplemented.forMember("net/minecraft/client/gui/components/ChatComponent$ClickableTextOnlyGraphicsAccess.<init>:(Lnet/minecraft/client/gui/ActiveTextCollector;)V");
        }

        public void updatePose(Consumer<Matrix3x2f> updater) {
            throw Unimplemented.forMember("net/minecraft/client/gui/components/ChatComponent$ClickableTextOnlyGraphicsAccess.updatePose:(Ljava/util/function/Consumer;)V");
        }

        public void fill(int x0, int y0, int x1, int y1, int color) {
            throw Unimplemented.forMember("net/minecraft/client/gui/components/ChatComponent$ClickableTextOnlyGraphicsAccess.fill:(IIIII)V");
        }

        public boolean handleMessage(int textTop, float opacity, FormattedCharSequence message) {
            throw Unimplemented.forMember("net/minecraft/client/gui/components/ChatComponent$ClickableTextOnlyGraphicsAccess.handleMessage:(IFLnet/minecraft/util/FormattedCharSequence;)Z");
        }

        public void handleTag(int x0, int y0, int x1, int y1, float opacity, GuiMessageTag tag) {
            throw Unimplemented.forMember("net/minecraft/client/gui/components/ChatComponent$ClickableTextOnlyGraphicsAccess.handleTag:(IIIIFLnet/minecraft/client/multiplayer/chat/GuiMessageTag;)V");
        }

        public void handleTagIcon(int left, int bottom, boolean forceVisible, GuiMessageTag tag, GuiMessageTag.Icon icon) {
            throw Unimplemented.forMember("net/minecraft/client/gui/components/ChatComponent$ClickableTextOnlyGraphicsAccess.handleTagIcon:(IIZLnet/minecraft/client/multiplayer/chat/GuiMessageTag;Lnet/minecraft/client/multiplayer/chat/GuiMessageTag$Icon;)V");
        }

        protected ClickableTextOnlyGraphicsAccess() {
        }
    }

    private record DelayedMessageDeletion(MessageSignature signature, int deletableAfter) {
    }

    public enum DisplayMode {

        BACKGROUND, FOREGROUND, FOREGROUND_RESTRICTED
    }

    public record Draft(String text, ChatComponent.ChatMethod chatMethod) {
    }

    private static class DrawingBackgroundGraphicsAccess implements ChatComponent.ChatGraphicsAccess {

        public DrawingBackgroundGraphicsAccess(GuiGraphicsExtractor graphics) {
            throw Unimplemented.forMember("net/minecraft/client/gui/components/ChatComponent$DrawingBackgroundGraphicsAccess.<init>:(Lnet/minecraft/client/gui/GuiGraphicsExtractor;)V");
        }

        public void updatePose(Consumer<Matrix3x2f> updater) {
            throw Unimplemented.forMember("net/minecraft/client/gui/components/ChatComponent$DrawingBackgroundGraphicsAccess.updatePose:(Ljava/util/function/Consumer;)V");
        }

        public void fill(int x0, int y0, int x1, int y1, int color) {
            throw Unimplemented.forMember("net/minecraft/client/gui/components/ChatComponent$DrawingBackgroundGraphicsAccess.fill:(IIIII)V");
        }

        public boolean handleMessage(int textTop, float opacity, FormattedCharSequence message) {
            throw Unimplemented.forMember("net/minecraft/client/gui/components/ChatComponent$DrawingBackgroundGraphicsAccess.handleMessage:(IFLnet/minecraft/util/FormattedCharSequence;)Z");
        }

        public void handleTag(int x0, int y0, int x1, int y1, float opacity, GuiMessageTag tag) {
            throw Unimplemented.forMember("net/minecraft/client/gui/components/ChatComponent$DrawingBackgroundGraphicsAccess.handleTag:(IIIIFLnet/minecraft/client/multiplayer/chat/GuiMessageTag;)V");
        }

        public void handleTagIcon(int left, int bottom, boolean forceVisible, GuiMessageTag tag, GuiMessageTag.Icon icon) {
            throw Unimplemented.forMember("net/minecraft/client/gui/components/ChatComponent$DrawingBackgroundGraphicsAccess.handleTagIcon:(IIZLnet/minecraft/client/multiplayer/chat/GuiMessageTag;Lnet/minecraft/client/multiplayer/chat/GuiMessageTag$Icon;)V");
        }

        protected DrawingBackgroundGraphicsAccess() {
        }
    }

    private static class DrawingFocusedGraphicsAccess implements ChatComponent.ChatGraphicsAccess, Consumer<Style> {

        public DrawingFocusedGraphicsAccess(GuiGraphicsExtractor graphics, Font font, int mouseX, int mouseY, boolean changeCursorOnInsertions) {
            throw Unimplemented.forMember("net/minecraft/client/gui/components/ChatComponent$DrawingFocusedGraphicsAccess.<init>:(Lnet/minecraft/client/gui/GuiGraphicsExtractor;Lnet/minecraft/client/gui/Font;IIZ)V");
        }

        public void updatePose(Consumer<Matrix3x2f> updater) {
            throw Unimplemented.forMember("net/minecraft/client/gui/components/ChatComponent$DrawingFocusedGraphicsAccess.updatePose:(Ljava/util/function/Consumer;)V");
        }

        public void fill(int x0, int y0, int x1, int y1, int color) {
            throw Unimplemented.forMember("net/minecraft/client/gui/components/ChatComponent$DrawingFocusedGraphicsAccess.fill:(IIIII)V");
        }

        public void accept(Style style) {
            throw Unimplemented.forMember("net/minecraft/client/gui/components/ChatComponent$DrawingFocusedGraphicsAccess.accept:(Lnet/minecraft/network/chat/Style;)V");
        }

        public boolean handleMessage(int textTop, float opacity, FormattedCharSequence message) {
            throw Unimplemented.forMember("net/minecraft/client/gui/components/ChatComponent$DrawingFocusedGraphicsAccess.handleMessage:(IFLnet/minecraft/util/FormattedCharSequence;)Z");
        }

        public void handleTag(int x0, int y0, int x1, int y1, float opacity, GuiMessageTag tag) {
            throw Unimplemented.forMember("net/minecraft/client/gui/components/ChatComponent$DrawingFocusedGraphicsAccess.handleTag:(IIIIFLnet/minecraft/client/multiplayer/chat/GuiMessageTag;)V");
        }

        public void handleTagIcon(int left, int bottom, boolean forceVisible, GuiMessageTag tag, GuiMessageTag.Icon icon) {
            throw Unimplemented.forMember("net/minecraft/client/gui/components/ChatComponent$DrawingFocusedGraphicsAccess.handleTagIcon:(IIZLnet/minecraft/client/multiplayer/chat/GuiMessageTag;Lnet/minecraft/client/multiplayer/chat/GuiMessageTag$Icon;)V");
        }

        protected DrawingFocusedGraphicsAccess() {
        }
    }

    private interface LineConsumer {

        void accept(GuiMessage.Line line, int lineIndex, float alpha);
    }

    public static class State {

        public State(List<GuiMessage> messages, List<String> history, List<ChatComponent.DelayedMessageDeletion> delayedMessageDeletions) {
            throw Unimplemented.forMember("net/minecraft/client/gui/components/ChatComponent$State.<init>:(Ljava/util/List;Ljava/util/List;Ljava/util/List;)V");
        }

        protected State() {
        }
    }

    protected ChatComponent() {
    }
}
