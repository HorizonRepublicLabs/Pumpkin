package net.minecraft.client.gui.components;

import java.util.function.Supplier;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.input.InputWithModifiers;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import dev.pumpkin.shim.Unimplemented;

public abstract class Button extends AbstractButton {

    protected Button(int x, int y, int width, int height, Component message, Button.OnPress onPress, Button.CreateNarration createNarration) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/Button.<init>:(IIIILnet/minecraft/network/chat/Component;Lnet/minecraft/client/gui/components/Button$OnPress;Lnet/minecraft/client/gui/components/Button$CreateNarration;)V");
    }

    protected Button(Builder builder) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/Button.<init>:(Lnet/minecraft/client/gui/components/Button$Builder;)V");
    }

    public void onPress(InputWithModifiers input) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/Button.onPress:(Lnet/minecraft/client/input/InputWithModifiers;)V");
    }

    protected MutableComponent createNarrationMessage() {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/Button.createNarrationMessage:()Lnet/minecraft/network/chat/MutableComponent;");
    }

    public void updateWidgetNarration(NarrationElementOutput output) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/Button.updateWidgetNarration:(Lnet/minecraft/client/gui/narration/NarrationElementOutput;)V");
    }

    public static class Builder {

        public Builder(Component message, Button.OnPress onPress) {
            throw Unimplemented.forMember("net/minecraft/client/gui/components/Button$Builder.<init>:(Lnet/minecraft/network/chat/Component;Lnet/minecraft/client/gui/components/Button$OnPress;)V");
        }

        public Button build() {
            throw Unimplemented.forMember("net/minecraft/client/gui/components/Button$Builder.build:()Lnet/minecraft/client/gui/components/Button;");
        }

        protected Builder() {
        }
    }

    public interface CreateNarration {

        MutableComponent createNarrationMessage(Supplier<MutableComponent> defaultNarrationSupplier);
    }

    public interface OnPress {

        void onPress(final Button button);
    }

    public static class Plain extends Button {

        protected Plain(int x, int y, int width, int height, Component message, Button.OnPress onPress, Button.CreateNarration createNarration) {
            throw Unimplemented.forMember("net/minecraft/client/gui/components/Button$Plain.<init>:(IIIILnet/minecraft/network/chat/Component;Lnet/minecraft/client/gui/components/Button$OnPress;Lnet/minecraft/client/gui/components/Button$CreateNarration;)V");
        }

        protected Plain(Builder builder) {
            throw Unimplemented.forMember("net/minecraft/client/gui/components/Button$Plain.<init>:(Lnet/minecraft/client/gui/components/Button$Builder;)V");
        }

        protected void extractContents(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
            throw Unimplemented.forMember("net/minecraft/client/gui/components/Button$Plain.extractContents:(Lnet/minecraft/client/gui/GuiGraphicsExtractor;IIF)V");
        }

        protected Plain() {
        }
    }

    protected Button() {
    }
}
