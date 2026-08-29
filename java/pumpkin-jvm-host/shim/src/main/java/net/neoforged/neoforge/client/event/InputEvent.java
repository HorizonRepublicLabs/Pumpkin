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
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/InputEvent.<init>:()V");
    }

    public static abstract class MouseButton extends InputEvent {

        protected MouseButton(MouseButtonInfo mouseButtonInfo, int action) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/event/InputEvent$MouseButton.<init>:(Lnet/minecraft/client/input/MouseButtonInfo;I)V");
        }

        public static class Pre extends MouseButton implements ICancellableEvent {

            public Pre(MouseButtonInfo mouseButtonInfo, int action) {
                throw Unimplemented.forMember("net/neoforged/neoforge/client/event/InputEvent$MouseButton$Pre.<init>:(Lnet/minecraft/client/input/MouseButtonInfo;I)V");
            }

            public Pre() {
            }
        }

        public static class Post extends MouseButton {

            public Post(MouseButtonInfo mouseButtonInfo, int action) {
                throw Unimplemented.forMember("net/neoforged/neoforge/client/event/InputEvent$MouseButton$Post.<init>:(Lnet/minecraft/client/input/MouseButtonInfo;I)V");
            }

            public Post() {
            }
        }

        public MouseButton() {
        }
    }

    public static class MouseScrollingEvent extends InputEvent implements ICancellableEvent {

        public MouseScrollingEvent(double scrollDeltaX, double scrollDeltaY, Vector2ic accumulatedScroll, boolean leftDown, boolean middleDown, boolean rightDown, double mouseX, double mouseY) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/event/InputEvent$MouseScrollingEvent.<init>:(DDLorg/joml/Vector2ic;ZZZDD)V");
        }

        public MouseScrollingEvent() {
        }
    }

    public static class Key extends InputEvent {

        public Key(KeyEvent keyEvent, int action) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/event/InputEvent$Key.<init>:(Lnet/minecraft/client/input/KeyEvent;I)V");
        }

        public int getKey() {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/event/InputEvent$Key.getKey:()I");
        }

        public Key() {
        }
    }

    public static class InteractionKeyMappingTriggered extends InputEvent implements ICancellableEvent {

        public InteractionKeyMappingTriggered(int button, KeyMapping keyMapping, InteractionHand hand) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/event/InputEvent$InteractionKeyMappingTriggered.<init>:(ILnet/minecraft/client/KeyMapping;Lnet/minecraft/world/InteractionHand;)V");
        }

        public InteractionKeyMappingTriggered() {
        }
    }
}
