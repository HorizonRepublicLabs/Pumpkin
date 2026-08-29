package net.minecraft.client.gui.components;

import java.util.Collection;
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.function.Function;
import java.util.function.Supplier;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.input.InputWithModifiers;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.Identifier;
import dev.pumpkin.shim.Unimplemented;

public class CycleButton<T> extends AbstractButton implements ResettableOptionWidget {

    private CycleButton(int x, int y, int width, int height, Component message, Component name, int index, T value, Supplier<T> defaultValueSupplier, CycleButton.ValueListSupplier<T> values, Function<T, Component> valueStringifier, Function<CycleButton<T>, MutableComponent> narrationProvider, CycleButton.OnValueChange<T> onValueChange, OptionInstance.TooltipSupplier<T> tooltipSupplier, CycleButton.DisplayState displayState, CycleButton.SpriteSupplier<T> spriteSupplier) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/CycleButton.<init>:(IIIILnet/minecraft/network/chat/Component;Lnet/minecraft/network/chat/Component;ILjava/lang/Object;Ljava/util/function/Supplier;Lnet/minecraft/client/gui/components/CycleButton$ValueListSupplier;Ljava/util/function/Function;Ljava/util/function/Function;Lnet/minecraft/client/gui/components/CycleButton$OnValueChange;Lnet/minecraft/client/OptionInstance$TooltipSupplier;Lnet/minecraft/client/gui/components/CycleButton$DisplayState;Lnet/minecraft/client/gui/components/CycleButton$SpriteSupplier;)V");
    }

    protected void extractContents(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/CycleButton.extractContents:(Lnet/minecraft/client/gui/GuiGraphicsExtractor;IIF)V");
    }

    public void onPress(InputWithModifiers input) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/CycleButton.onPress:(Lnet/minecraft/client/input/InputWithModifiers;)V");
    }

    public boolean mouseScrolled(double x, double y, double scrollX, double scrollY) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/CycleButton.mouseScrolled:(DDDD)Z");
    }

    public void resetValue() {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/CycleButton.resetValue:()V");
    }

    protected MutableComponent createNarrationMessage() {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/CycleButton.createNarrationMessage:()Lnet/minecraft/network/chat/MutableComponent;");
    }

    public void updateWidgetNarration(NarrationElementOutput output) {
        throw Unimplemented.forMember("net/minecraft/client/gui/components/CycleButton.updateWidgetNarration:(Lnet/minecraft/client/gui/narration/NarrationElementOutput;)V");
    }

    public static class Builder<T> {

        public Builder(Function<T, Component> valueStringifier, Supplier<T> defaultValueSupplier) {
            throw Unimplemented.forMember("net/minecraft/client/gui/components/CycleButton$Builder.<init>:(Ljava/util/function/Function;Ljava/util/function/Supplier;)V");
        }

        public CycleButton<T> create(Component name, CycleButton.OnValueChange<T> valueChangeListener) {
            throw Unimplemented.forMember("net/minecraft/client/gui/components/CycleButton$Builder.create:(Lnet/minecraft/network/chat/Component;Lnet/minecraft/client/gui/components/CycleButton$OnValueChange;)Lnet/minecraft/client/gui/components/CycleButton;");
        }

        public CycleButton<T> create(int x, int y, int width, int height, Component name, CycleButton.OnValueChange<T> valueChangeListener) {
            throw Unimplemented.forMember("net/minecraft/client/gui/components/CycleButton$Builder.create:(IIIILnet/minecraft/network/chat/Component;Lnet/minecraft/client/gui/components/CycleButton$OnValueChange;)Lnet/minecraft/client/gui/components/CycleButton;");
        }

        public Builder() {
        }
    }

    public enum DisplayState {

        NAME_AND_VALUE, VALUE, HIDE
    }

    public interface OnValueChange<T> {

        void onValueChange(CycleButton<T> button, T value);
    }

    public interface SpriteSupplier<T> {

        Identifier apply(CycleButton<T> button, T value);
    }

    public interface ValueListSupplier<T> {

        List<T> getSelectedList();

        List<T> getDefaultList();

        static <T> CycleButton.ValueListSupplier<T> create(Collection<T> values) {
            throw Unimplemented.forMember("net/minecraft/client/gui/components/CycleButton$ValueListSupplier.create:(Ljava/util/Collection;)Lnet/minecraft/client/gui/components/CycleButton$ValueListSupplier;");
        }

        static <T> CycleButton.ValueListSupplier<T> create(BooleanSupplier altSelector, List<T> defaultList, List<T> altList) {
            throw Unimplemented.forMember("net/minecraft/client/gui/components/CycleButton$ValueListSupplier.create:(Ljava/util/function/BooleanSupplier;Ljava/util/List;Ljava/util/List;)Lnet/minecraft/client/gui/components/CycleButton$ValueListSupplier;");
        }
    }

    public CycleButton() {
    }
}
