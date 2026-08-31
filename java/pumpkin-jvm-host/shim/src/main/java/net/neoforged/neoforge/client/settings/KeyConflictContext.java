package net.neoforged.neoforge.client.settings;

import dev.pumpkin.shim.Unimplemented;

public enum KeyConflictContext implements IKeyConflictContext {

    UNIVERSAL {

        public boolean isActive() {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/settings/KeyConflictContext$UNIVERSAL.isActive:()");
        }

        public boolean conflicts(IKeyConflictContext other) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/settings/KeyConflictContext$UNIVERSAL.conflicts:()");
        }
    }
    , GUI {

        public boolean isActive() {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/settings/KeyConflictContext$GUI.isActive:()");
        }

        public boolean conflicts(IKeyConflictContext other) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/settings/KeyConflictContext$GUI.conflicts:()");
        }
    }
    , IN_GAME {

        public boolean isActive() {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/settings/KeyConflictContext$IN_GAME.isActive:()");
        }

        public boolean conflicts(IKeyConflictContext other) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/settings/KeyConflictContext$IN_GAME.conflicts:()");
        }
    }

}
