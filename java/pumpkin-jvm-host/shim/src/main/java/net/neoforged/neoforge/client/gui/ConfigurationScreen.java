package net.neoforged.neoforge.client.gui;

import com.electronwill.nightconfig.core.UnmodifiableConfig.Entry;
import com.mojang.datafixers.util.Function4;
import com.mojang.serialization.Codec;
import it.unimi.dsi.fastutil.booleans.BooleanConsumer;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractContainerWidget;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.ConfirmScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import net.minecraft.network.chat.Component;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.config.ModConfig.Type;
import net.neoforged.neoforge.client.gui.ConfigurationScreen.ConfigurationSectionScreen.Filter;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.ConfigValue;
import net.neoforged.neoforge.common.ModConfigSpec.ListValueSpec;
import dev.pumpkin.shim.Unimplemented;

public final class ConfigurationScreen extends OptionsSubScreen {

    private static final class TooltipConfirmScreen extends ConfirmScreen {

        private TooltipConfirmScreen(BooleanConsumer callback, Component title, Component message, Component yesButton, Component noButton) {
        }

        protected void addButtons(LinearLayout layout) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/gui/ConfigurationScreen$TooltipConfirmScreen.addButtons:(Lnet/minecraft/client/gui/layouts/LinearLayout;)V");
        }

        protected TooltipConfirmScreen() {
        }
    }

    public static class TranslationChecker {

        public String check(final String translationKey) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/gui/ConfigurationScreen$TranslationChecker.check:(Ljava/lang/String;)Ljava/lang/String;");
        }

        public TranslationChecker() {
        }
    }

    public ConfigurationScreen(final ModContainer mod, final Screen parent) {
    }

    public ConfigurationScreen(final ModContainer mod, final Screen parent, ConfigurationSectionScreen.Filter filter) {
    }

    public ConfigurationScreen(final ModContainer mod, final Screen parent, Function4<ConfigurationScreen, ModConfig.Type, ModConfig, Component, Screen> sectionScreen) {
    }

    protected void addOptions() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/gui/ConfigurationScreen.addOptions:()V");
    }

    public void added() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/gui/ConfigurationScreen.added:()V");
    }

    public void onClose() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/gui/ConfigurationScreen.onClose:()V");
    }

    public static class ConfigurationSectionScreen extends OptionsSubScreen {

        public record Context(String modId, Screen parent, ModConfig modConfig, ModConfigSpec modSpec, Set<? extends Entry> entries, Map<String, Object> valueSpecs, List<String> keylist, Filter filter) {

            public static Context list(final Context parentContext, final Screen parent) {
                throw Unimplemented.forMember("net/neoforged/neoforge/client/gui/ConfigurationScreen$ConfigurationSectionScreen$Context.list:(Lnet/neoforged/neoforge/client/gui/ConfigurationScreen$ConfigurationSectionScreen$Context;Lnet/minecraft/client/gui/screens/Screen;)Lnet/neoforged/neoforge/client/gui/ConfigurationScreen$ConfigurationSectionScreen$Context;");
            }
        }

        public record Element(Component name, Component tooltip, AbstractWidget widget, OptionInstance<?> option, boolean undoable) {

            public Element(final Component name, final Component tooltip, final AbstractWidget widget) {
                this((Component) null, (Component) null, (AbstractWidget) null, (OptionInstance<?>) null, (boolean) false);
            }

            public Element(final Component name, final Component tooltip, final AbstractWidget widget, boolean undoable) {
                this((Component) null, (Component) null, (AbstractWidget) null, (OptionInstance<?>) null, (boolean) false);
            }

            public Element(final Component name, final Component tooltip, final OptionInstance<?> option) {
                this((Component) null, (Component) null, (AbstractWidget) null, (OptionInstance<?>) null, (boolean) false);
            }

            public Element(final Component name, final Component tooltip, final OptionInstance<?> option, boolean undoable) {
                this((Component) null, (Component) null, (AbstractWidget) null, (OptionInstance<?>) null, (boolean) false);
            }
        }

        public interface Filter {

            Element filterEntry(Context context, String key, Element original);
        }

        public ConfigurationSectionScreen(final Screen parent, final ModConfig.Type type, final ModConfig modConfig, Component title) {
        }

        public ConfigurationSectionScreen(final Screen parent, final ModConfig.Type type, final ModConfig modConfig, Component title, Filter filter) {
        }

        public ConfigurationSectionScreen(final Context parentContext, final Screen parent, final Map<String, Object> valueSpecs, final String key, final Set<? extends Entry> entrySet, Component title) {
        }

        protected ConfigurationSectionScreen(final Context context, final Component title) {
        }

        protected void addOptions() {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/gui/ConfigurationScreen$ConfigurationSectionScreen.addOptions:()V");
        }

        public record Custom<T>(List<T> values) implements OptionInstance.ValueSet<T> {

            public Function<OptionInstance<T>, AbstractWidget> createButton(OptionInstance.TooltipSupplier<T> tooltip, Options options, int x, int y, int width, OptionInstance.ValueUpdateListener<? super T> target) {
                throw Unimplemented.forMember("net/neoforged/neoforge/client/gui/ConfigurationScreen$ConfigurationSectionScreen$Custom.createButton:(Lnet/minecraft/client/OptionInstance$TooltipSupplier;Lnet/minecraft/client/Options;IIILnet/minecraft/client/OptionInstance$ValueUpdateListener;)Ljava/util/function/Function;");
            }

            public Optional<T> validateValue(T value) {
                throw Unimplemented.forMember("net/neoforged/neoforge/client/gui/ConfigurationScreen$ConfigurationSectionScreen$Custom.validateValue:(Ljava/lang/Object;)Ljava/util/Optional;");
            }

            public Codec<T> codec() {
                throw Unimplemented.forMember("net/neoforged/neoforge/client/gui/ConfigurationScreen$ConfigurationSectionScreen$Custom.codec:()Lcom/mojang/serialization/Codec;");
            }
        }

        public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/gui/ConfigurationScreen$ConfigurationSectionScreen.extractRenderState:(Lnet/minecraft/client/gui/GuiGraphicsExtractor;IIF)V");
        }

        protected void addFooter() {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/gui/ConfigurationScreen$ConfigurationSectionScreen.addFooter:()V");
        }

        public void onClose() {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/gui/ConfigurationScreen$ConfigurationSectionScreen.onClose:()V");
        }

        public ConfigurationSectionScreen() {
        }
    }

    public static class ConfigurationListScreen<T> extends ConfigurationSectionScreen {

        public ConfigurationListScreen(final Context context, final String key, final Component title, final ListValueSpec spec, final ModConfigSpec.ConfigValue<List<T>> valueList) {
        }

        protected ConfigurationSectionScreen rebuild() {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/gui/ConfigurationScreen$ConfigurationListScreen.rebuild:()Lnet/neoforged/neoforge/client/gui/ConfigurationScreen$ConfigurationSectionScreen;");
        }

        protected boolean isAnyNondefault() {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/gui/ConfigurationScreen$ConfigurationListScreen.isAnyNondefault:()Z");
        }

        public void onClose() {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/gui/ConfigurationScreen$ConfigurationListScreen.onClose:()V");
        }

        public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/gui/ConfigurationScreen$ConfigurationListScreen.extractRenderState:(Lnet/minecraft/client/gui/GuiGraphicsExtractor;IIF)V");
        }

        protected void onChanged(final String key) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/gui/ConfigurationScreen$ConfigurationListScreen.onChanged:(Ljava/lang/String;)V");
        }

        protected void createResetButton() {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/gui/ConfigurationScreen$ConfigurationListScreen.createResetButton:()V");
        }

        public class ListLabelWidget extends AbstractContainerWidget {

            public ListLabelWidget(final int x, final int y, final int width, final int height, final Component labelText, final int idx) {
            }

            public void setX(final int pX) {
                throw Unimplemented.forMember("net/neoforged/neoforge/client/gui/ConfigurationScreen$ConfigurationListScreen$ListLabelWidget.setX:(I)V");
            }

            public void setY(final int pY) {
                throw Unimplemented.forMember("net/neoforged/neoforge/client/gui/ConfigurationScreen$ConfigurationListScreen$ListLabelWidget.setY:(I)V");
            }

            public void setHeight(final int pHeight) {
                throw Unimplemented.forMember("net/neoforged/neoforge/client/gui/ConfigurationScreen$ConfigurationListScreen$ListLabelWidget.setHeight:(I)V");
            }

            public void setWidth(int pWidth) {
                throw Unimplemented.forMember("net/neoforged/neoforge/client/gui/ConfigurationScreen$ConfigurationListScreen$ListLabelWidget.setWidth:(I)V");
            }

            public void setSize(int pWidth, int pHeight) {
                throw Unimplemented.forMember("net/neoforged/neoforge/client/gui/ConfigurationScreen$ConfigurationListScreen$ListLabelWidget.setSize:(II)V");
            }

            public List<? extends GuiEventListener> children() {
                throw Unimplemented.forMember("net/neoforged/neoforge/client/gui/ConfigurationScreen$ConfigurationListScreen$ListLabelWidget.children:()Ljava/util/List;");
            }

            protected void extractWidgetRenderState(final GuiGraphicsExtractor pGuiGraphics, final int pMouseX, final int pMouseY, final float pPartialTick) {
                throw Unimplemented.forMember("net/neoforged/neoforge/client/gui/ConfigurationScreen$ConfigurationListScreen$ListLabelWidget.extractWidgetRenderState:(Lnet/minecraft/client/gui/GuiGraphicsExtractor;IIF)V");
            }

            protected void updateWidgetNarration(final NarrationElementOutput pNarrationElementOutput) {
                throw Unimplemented.forMember("net/neoforged/neoforge/client/gui/ConfigurationScreen$ConfigurationListScreen$ListLabelWidget.updateWidgetNarration:(Lnet/minecraft/client/gui/narration/NarrationElementOutput;)V");
            }

            protected int contentHeight() {
                throw Unimplemented.forMember("net/neoforged/neoforge/client/gui/ConfigurationScreen$ConfigurationListScreen$ListLabelWidget.contentHeight:()I");
            }

            protected double scrollRate() {
                throw Unimplemented.forMember("net/neoforged/neoforge/client/gui/ConfigurationScreen$ConfigurationListScreen$ListLabelWidget.scrollRate:()D");
            }

            public ListLabelWidget() {
            }
        }

        public ConfigurationListScreen() {
        }
    }

    public static final class UndoManager {

        public record Step<T>(Consumer<T> run, T newValue, Consumer<T> undo, T oldValue) {
        }

        private void add(Step<?> step, boolean execute) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/gui/ConfigurationScreen$UndoManager.add:(Lnet/neoforged/neoforge/client/gui/ConfigurationScreen$UndoManager$Step;Z)V");
        }

        public void add(Step<?>... steps) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/gui/ConfigurationScreen$UndoManager.add:([Lnet/neoforged/neoforge/client/gui/ConfigurationScreen$UndoManager$Step;)V");
        }

        public void add(final List<Step<?>> steps) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/gui/ConfigurationScreen$UndoManager.add:(Ljava/util/List;)V");
        }

        public UndoManager() {
        }
    }

    public ConfigurationScreen() {
    }
}
