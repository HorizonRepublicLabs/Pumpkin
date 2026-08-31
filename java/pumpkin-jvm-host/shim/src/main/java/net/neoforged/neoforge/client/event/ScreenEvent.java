package net.neoforged.neoforge.client.event;

import java.util.List;
import java.util.function.Consumer;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.CharacterEvent;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.input.PreeditEvent;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.ICancellableEvent;
import dev.pumpkin.shim.Unimplemented;

public abstract class ScreenEvent extends Event {

    protected ScreenEvent(Screen screen) {
    }

    public static abstract class Init extends ScreenEvent {

        protected Init(Screen screen, List<GuiEventListener> listenerList, Consumer<GuiEventListener> add, Consumer<GuiEventListener> remove) {
        }

        public static class Pre extends Init implements ICancellableEvent {

            public Pre(Screen screen, List<GuiEventListener> list, Consumer<GuiEventListener> add, Consumer<GuiEventListener> remove) {
            }

            public Pre() {
            }
        }

        public static class Post extends Init {

            public Post(Screen screen, List<GuiEventListener> list, Consumer<GuiEventListener> add, Consumer<GuiEventListener> remove) {
            }

            public Post() {
            }
        }

        public Init() {
        }
    }

    public static abstract class Render extends ScreenEvent {

        protected Render(Screen screen, GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTick) {
        }

        public static class Pre extends Render implements ICancellableEvent {

            public Pre(Screen screen, GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTick) {
            }

            public Pre() {
            }
        }

        public static class Background extends ScreenEvent.Render {

            public Background(Screen screen, GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTick) {
            }

            public Background() {
            }
        }

        public static class Foreground extends Render {

            public Foreground(Screen screen, GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTick) {
            }

            public Foreground() {
            }
        }

        public static class Post extends Render {

            public Post(Screen screen, GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTick) {
            }

            public Post() {
            }
        }

        public Render() {
        }
    }

    public static class RenderInventoryMobEffects extends ScreenEvent implements ICancellableEvent {

        public RenderInventoryMobEffects(Screen screen, int availableSpace, boolean compact, int horizontalOffset) {
        }

        public RenderInventoryMobEffects() {
        }
    }

    private static abstract class MouseInput extends ScreenEvent {

        protected MouseInput(Screen screen, double mouseX, double mouseY) {
        }

        protected MouseInput() {
        }
    }

    public static abstract class MouseButtonPressed extends MouseInput {

        public MouseButtonPressed(Screen screen, MouseButtonEvent mouseEvent, boolean doubleClick) {
        }

        public static class Pre extends MouseButtonPressed implements ICancellableEvent {

            public Pre(Screen screen, MouseButtonEvent mouseEvent, boolean doubleClick) {
            }

            public Pre() {
            }
        }

        public static class Post extends MouseButtonPressed {

            public Post(Screen screen, MouseButtonEvent mouseEvent, boolean doubleClick, boolean handled) {
            }

            public static enum Result {

                FORCE_HANDLED, DEFAULT, FORCE_UNHANDLED
            }

            public Post() {
            }
        }

        public MouseButtonPressed() {
        }
    }

    public static abstract class MouseButtonReleased extends MouseInput {

        public MouseButtonReleased(Screen screen, MouseButtonEvent mouseEvent) {
        }

        public static class Pre extends MouseButtonReleased implements ICancellableEvent {

            public Pre(Screen screen, MouseButtonEvent mouseEvent) {
            }

            public Pre() {
            }
        }

        public static class Post extends MouseButtonReleased {

            public Post(Screen screen, MouseButtonEvent mouseEvent, boolean handled) {
            }

            public static enum Result {

                FORCE_HANDLED, DEFAULT, FORCE_UNHANDLED
            }

            public Post() {
            }
        }

        public MouseButtonReleased() {
        }
    }

    public static abstract class MouseDragged extends MouseInput {

        public MouseDragged(Screen screen, MouseButtonEvent mouseEvent, double dragX, double dragY) {
        }

        public static class Pre extends MouseDragged implements ICancellableEvent {

            public Pre(Screen screen, MouseButtonEvent mouseEvent, double dragX, double dragY) {
            }

            public Pre() {
            }
        }

        public static class Post extends MouseDragged {

            public Post(Screen screen, MouseButtonEvent mouseEvent, double dragX, double dragY) {
            }

            public Post() {
            }
        }

        public MouseDragged() {
        }
    }

    public static abstract class MouseScrolled extends MouseInput {

        public MouseScrolled(Screen screen, double mouseX, double mouseY, double scrollDeltaX, double scrollDeltaY) {
        }

        public static class Pre extends MouseScrolled implements ICancellableEvent {

            public Pre(Screen screen, double mouseX, double mouseY, double scrollDeltaX, double scrollDeltaY) {
            }

            public Pre() {
            }
        }

        public static class Post extends MouseScrolled {

            public Post(Screen screen, double mouseX, double mouseY, double scrollDeltaX, double scrollDeltaY) {
            }

            public Post() {
            }
        }

        public MouseScrolled() {
        }
    }

    private static abstract class KeyInput extends ScreenEvent {

        protected KeyInput(Screen screen, KeyEvent keyEvent) {
        }

        protected KeyInput() {
        }
    }

    public static abstract class KeyPressed extends KeyInput {

        public KeyPressed(Screen screen, KeyEvent keyEvent) {
        }

        public static class Pre extends KeyPressed implements ICancellableEvent {

            public Pre(Screen screen, KeyEvent keyEvent) {
            }

            public Pre() {
            }
        }

        public static class Post extends KeyPressed implements ICancellableEvent {

            public Post(Screen screen, KeyEvent keyEvent) {
            }

            public Post() {
            }
        }

        public KeyPressed() {
        }
    }

    public static abstract class KeyReleased extends KeyInput {

        public KeyReleased(Screen screen, KeyEvent keyEvent) {
        }

        public static class Pre extends KeyReleased implements ICancellableEvent {

            public Pre(Screen screen, KeyEvent keyEvent) {
            }

            public Pre() {
            }
        }

        public static class Post extends KeyReleased implements ICancellableEvent {

            public Post(Screen screen, KeyEvent keyEvent) {
            }

            public Post() {
            }
        }

        public KeyReleased() {
        }
    }

    public static abstract class CharacterTyped extends ScreenEvent {

        public CharacterTyped(Screen screen, CharacterEvent charEvent) {
        }

        public static class Pre extends CharacterTyped implements ICancellableEvent {

            public Pre(Screen screen, CharacterEvent charEvent) {
            }

            public Pre() {
            }
        }

        public static class Post extends CharacterTyped {

            public Post(Screen screen, CharacterEvent charEvent) {
            }

            public Post() {
            }
        }

        public CharacterTyped() {
        }
    }

    public static abstract class Preedit extends ScreenEvent {

        public Preedit(Screen screen, PreeditEvent preeditEvent) {
        }

        public static class Pre extends Preedit implements ICancellableEvent {

            public Pre(Screen screen, PreeditEvent preeditEvent) {
            }

            public Pre() {
            }
        }

        public static class Post extends Preedit {

            public Post(Screen screen, PreeditEvent preeditEvent) {
            }

            public Post() {
            }
        }

        public Preedit() {
        }
    }

    public static class Opening extends ScreenEvent implements ICancellableEvent {

        public Opening(Screen currentScreen, Screen screen) {
        }

        public Screen getCurrentScreen() {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/event/ScreenEvent$Opening.getCurrentScreen:()Lnet/minecraft/client/gui/screens/Screen;");
        }

        public Screen getNewScreen() {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/event/ScreenEvent$Opening.getNewScreen:()Lnet/minecraft/client/gui/screens/Screen;");
        }

        public Opening() {
        }
    }

    public static class Closing extends ScreenEvent {

        public Closing(Screen screen) {
        }

        public Closing() {
        }
    }

    public ScreenEvent() {
    }
}
