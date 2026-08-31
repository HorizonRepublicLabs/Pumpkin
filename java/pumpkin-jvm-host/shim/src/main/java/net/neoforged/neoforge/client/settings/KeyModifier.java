package net.neoforged.neoforge.client.settings;

import com.mojang.blaze3d.platform.InputConstants;
import java.util.function.Supplier;
import net.minecraft.network.chat.Component;
import dev.pumpkin.shim.Unimplemented;

public enum KeyModifier {

    CONTROL {

        public boolean matches(InputConstants.Key key) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/settings/KeyModifier$CONTROL.matches:()");
        }

        public boolean isActive(IKeyConflictContext conflictContext) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/settings/KeyModifier$CONTROL.isActive:()");
        }

        public Component getCombinedName(InputConstants.Key key, Supplier<Component> defaultLogic) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/settings/KeyModifier$CONTROL.getCombinedName:()");
        }

        public InputConstants.Key[] codes() {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/settings/KeyModifier$CONTROL.codes:()");
        }
    }
    , CONTROL_OR_COMMAND {

        public boolean matches(InputConstants.Key key) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/settings/KeyModifier$CONTROL_OR_COMMAND.matches:()");
        }

        public boolean isActive(IKeyConflictContext conflictContext) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/settings/KeyModifier$CONTROL_OR_COMMAND.isActive:()");
        }

        public Component getCombinedName(InputConstants.Key key, Supplier<Component> defaultLogic) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/settings/KeyModifier$CONTROL_OR_COMMAND.getCombinedName:()");
        }

        public InputConstants.Key[] codes() {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/settings/KeyModifier$CONTROL_OR_COMMAND.codes:()");
        }
    }
    , SHIFT {

        public boolean matches(InputConstants.Key key) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/settings/KeyModifier$SHIFT.matches:()");
        }

        public boolean isActive(IKeyConflictContext conflictContext) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/settings/KeyModifier$SHIFT.isActive:()");
        }

        public Component getCombinedName(InputConstants.Key key, Supplier<Component> defaultLogic) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/settings/KeyModifier$SHIFT.getCombinedName:()");
        }

        public InputConstants.Key[] codes() {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/settings/KeyModifier$SHIFT.codes:()");
        }
    }
    , ALT {

        public boolean matches(InputConstants.Key key) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/settings/KeyModifier$ALT.matches:()");
        }

        public boolean isActive(IKeyConflictContext conflictContext) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/settings/KeyModifier$ALT.isActive:()");
        }

        public Component getCombinedName(InputConstants.Key keyCode, Supplier<Component> defaultLogic) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/settings/KeyModifier$ALT.getCombinedName:()");
        }

        public InputConstants.Key[] codes() {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/settings/KeyModifier$ALT.codes:()");
        }
    }
    , NONE {

        public boolean matches(InputConstants.Key key) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/settings/KeyModifier$NONE.matches:()");
        }

        public boolean isActive(IKeyConflictContext conflictContext) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/settings/KeyModifier$NONE.isActive:()");
        }

        public Component getCombinedName(InputConstants.Key key, Supplier<Component> defaultLogic) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/settings/KeyModifier$NONE.getCombinedName:()");
        }

        public InputConstants.Key[] codes() {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/settings/KeyModifier$NONE.codes:()");
        }
    }
    ;

    public static boolean isKeyCodeModifier(InputConstants.Key key) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/settings/KeyModifier.isKeyCodeModifier:(Lcom/mojang/blaze3d/platform/InputConstants$Key;)Z");
    }

    public abstract boolean matches(InputConstants.Key key);

    public abstract boolean isActive(IKeyConflictContext conflictContext);

    public abstract Component getCombinedName(InputConstants.Key key, Supplier<Component> defaultLogic);

    public abstract InputConstants.Key[] codes();
}
