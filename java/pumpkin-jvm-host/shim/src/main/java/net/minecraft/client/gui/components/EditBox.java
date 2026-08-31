package net.minecraft.client.gui.components;

import java.util.function.Consumer;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.input.CharacterEvent;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.input.PreeditEvent;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.util.FormattedCharSequence;
import dev.pumpkin.shim.Unimplemented;

public class EditBox extends AbstractWidget {

    private int maxLength;

    private boolean canLoseFocus;

    private int highlightPos;

    public EditBox(Font font, Component narration) {
    }

    public EditBox(Font font, int width, int height, Component narration) {
    }

    public EditBox(Font font, int x, int y, int width, int height, Component narration) {
    }

    public EditBox(Font font, int x, int y, int width, int height, EditBox oldBox, Component narration) {
    }

    public void setResponder(Consumer<String> responder) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/EditBox.setResponder:(Ljava/util/function/Consumer;)V");
    }

    protected MutableComponent createNarrationMessage() {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/EditBox.createNarrationMessage:()Lnet/minecraft/network/chat/MutableComponent;");
    }

    public void setValue(String value) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/EditBox.setValue:(Ljava/lang/String;)V");
    }

    public String getValue() {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/EditBox.getValue:()Ljava/lang/String;");
    }

    public void setX(int x) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/EditBox.setX:(I)V");
    }

    public void setY(int y) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/EditBox.setY:(I)V");
    }

    public void insertText(String input) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/EditBox.insertText:(Ljava/lang/String;)V");
    }

    public void setCursorPosition(int pos) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/EditBox.setCursorPosition:(I)V");
    }

    public boolean keyPressed(KeyEvent event) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/EditBox.keyPressed:(Lnet/minecraft/client/input/KeyEvent;)Z");
    }

    public boolean canConsumeInput() {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/EditBox.canConsumeInput:()Z");
    }

    public boolean charTyped(CharacterEvent event) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/EditBox.charTyped:(Lnet/minecraft/client/input/CharacterEvent;)Z");
    }

    public boolean preeditUpdated(PreeditEvent event) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/EditBox.preeditUpdated:(Lnet/minecraft/client/input/PreeditEvent;)Z");
    }

    public void onClick(MouseButtonEvent event, boolean doubleClick) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/EditBox.onClick:(Lnet/minecraft/client/input/MouseButtonEvent;Z)V");
    }

    protected void onDrag(MouseButtonEvent event, double dx, double dy) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/EditBox.onDrag:(Lnet/minecraft/client/input/MouseButtonEvent;DD)V");
    }

    public void playDownSound(SoundManager soundManager) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/EditBox.playDownSound:(Lnet/minecraft/client/sounds/SoundManager;)V");
    }

    public void extractWidgetRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/EditBox.extractWidgetRenderState:(Lnet/minecraft/client/gui/GuiGraphicsExtractor;IIF)V");
    }

    public void setMaxLength(int maxLength) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/EditBox.setMaxLength:(I)V");
    }

    public int getCursorPosition() {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/EditBox.getCursorPosition:()I");
    }

    public void setBordered(boolean bordered) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/EditBox.setBordered:(Z)V");
    }

    public void setTextColor(int textColor) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/EditBox.setTextColor:(I)V");
    }

    public void setTextColorUneditable(int textColorUneditable) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/EditBox.setTextColorUneditable:(I)V");
    }

    public void setFocused(boolean focused) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/EditBox.setFocused:(Z)V");
    }

    public void setEditable(boolean isEditable) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/EditBox.setEditable:(Z)V");
    }

    public void setHighlightPos(int pos) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/EditBox.setHighlightPos:(I)V");
    }

    public void setCanLoseFocus(boolean canLoseFocus) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/EditBox.setCanLoseFocus:(Z)V");
    }

    public void setVisible(boolean visible) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/EditBox.setVisible:(Z)V");
    }

    public void updateWidgetNarration(NarrationElementOutput output) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/EditBox.updateWidgetNarration:(Lnet/minecraft/client/gui/narration/NarrationElementOutput;)V");
    }

    public interface TextFormatter {

        FormattedCharSequence format(final String text, final int offset);
    }

    public EditBox() {
    }
}
