package net.neoforged.neoforge.client.event;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonInfo;
import net.minecraft.world.InteractionHand;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.ICancellableEvent;
import org.joml.Vector2ic;
import dev.pumpkin.shim.Unimplemented;

public abstract class InputEvent extends Event {

    protected InputEvent() {
    }

    public static abstract class MouseButton extends InputEvent {

        protected MouseButton(MouseButtonInfo mouseButtonInfo, int action) {
        }

        public static class Pre extends MouseButton implements ICancellableEvent {

            public Pre(MouseButtonInfo mouseButtonInfo, int action) {
            }

            public Pre() {
            }
        }

        public static class Post extends MouseButton {

            public Post(MouseButtonInfo mouseButtonInfo, int action) {
            }

            public Post() {
            }
        }

        public MouseButton() {
        }
    }

    public static class MouseScrollingEvent extends InputEvent implements ICancellableEvent {

        public MouseScrollingEvent(double scrollDeltaX, double scrollDeltaY, Vector2ic accumulatedScroll, boolean leftDown, boolean middleDown, boolean rightDown, double mouseX, double mouseY) {
        }

        public double getScrollDeltaY() {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/event/InputEvent$MouseScrollingEvent.getScrollDeltaY:()D");
        }

        public MouseScrollingEvent() {
        }
    }

    public static class Key extends InputEvent {

        public Key(KeyEvent keyEvent, int action) {
        }

        public int getKey() {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/event/InputEvent$Key.getKey:()I");
        }

        public Key() {
        }
    }

    public static class InteractionKeyMappingTriggered extends InputEvent implements ICancellableEvent {

        public InteractionKeyMappingTriggered(int button, KeyMapping keyMapping, InteractionHand hand) {
        }

        public InteractionKeyMappingTriggered() {
        }
    }
}
