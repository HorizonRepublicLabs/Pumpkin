package net.minecraft.client.gui.navigation;

import dev.pumpkin.shim.Unimplemented;

public interface FocusNavigationEvent {

    ScreenDirection getVerticalDirectionForInitialFocus();

    record ArrowNavigation(ScreenDirection direction, ScreenRectangle previousFocus) implements FocusNavigationEvent {

        public ArrowNavigation(ScreenDirection direction) {
            this((ScreenDirection) null, (ScreenRectangle) null);
        }

        public ScreenDirection getVerticalDirectionForInitialFocus() {
            throw Unimplemented.forMember("net/minecraft/client/gui/navigation/FocusNavigationEvent$ArrowNavigation.getVerticalDirectionForInitialFocus:()Lnet/minecraft/client/gui/navigation/ScreenDirection;");
        }

        public FocusNavigationEvent.ArrowNavigation with(ScreenRectangle previousFocus) {
            throw Unimplemented.forMember("net/minecraft/client/gui/navigation/FocusNavigationEvent$ArrowNavigation.with:(Lnet/minecraft/client/gui/navigation/ScreenRectangle;)Lnet/minecraft/client/gui/navigation/FocusNavigationEvent$ArrowNavigation;");
        }
    }

    class InitialFocus implements FocusNavigationEvent {

        public ScreenDirection getVerticalDirectionForInitialFocus() {
            throw Unimplemented.forMember("net/minecraft/client/gui/navigation/FocusNavigationEvent$InitialFocus.getVerticalDirectionForInitialFocus:()Lnet/minecraft/client/gui/navigation/ScreenDirection;");
        }

        protected InitialFocus() {
        }
    }

    record TabNavigation(boolean forward) implements FocusNavigationEvent {

        public ScreenDirection getVerticalDirectionForInitialFocus() {
            throw Unimplemented.forMember("net/minecraft/client/gui/navigation/FocusNavigationEvent$TabNavigation.getVerticalDirectionForInitialFocus:()Lnet/minecraft/client/gui/navigation/ScreenDirection;");
        }
    }
}
