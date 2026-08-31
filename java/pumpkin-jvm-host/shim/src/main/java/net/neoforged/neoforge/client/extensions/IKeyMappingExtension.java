package net.neoforged.neoforge.client.extensions;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.client.settings.IKeyConflictContext;
import net.neoforged.neoforge.client.settings.KeyModifier;
import dev.pumpkin.shim.Unimplemented;

public interface IKeyMappingExtension {

    InputConstants.Key getKey();

    default boolean isActiveAndMatches(InputConstants.Key keyCode) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/extensions/IKeyMappingExtension.isActiveAndMatches:(Lcom/mojang/blaze3d/platform/InputConstants$Key;)Z");
    }

    void setKeyConflictContext(IKeyConflictContext keyConflictContext);

    IKeyConflictContext getKeyConflictContext();

    KeyModifier getDefaultKeyModifier();

    KeyModifier getKeyModifier();

    void setKeyModifierAndCode(KeyModifier keyModifier, InputConstants.Key keyCode);

    default boolean isConflictContextAndModifierActive() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/extensions/IKeyMappingExtension.isConflictContextAndModifierActive:()Z");
    }

    default Component getDisplayName() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/extensions/IKeyMappingExtension.getDisplayName:()Lnet/minecraft/network/chat/Component;");
    }
}
