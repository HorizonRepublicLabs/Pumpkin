package net.neoforged.neoforge.client.extensions;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.client.settings.IKeyConflictContext;
import net.neoforged.neoforge.client.settings.KeyModifier;
import dev.pumpkin.shim.Unimplemented;

public interface IKeyMappingExtension {

    InputConstants.Key getKey();

    void setKeyConflictContext(IKeyConflictContext keyConflictContext);

    IKeyConflictContext getKeyConflictContext();

    KeyModifier getDefaultKeyModifier();

    KeyModifier getKeyModifier();

    void setKeyModifierAndCode(KeyModifier keyModifier, InputConstants.Key keyCode);

    default Component getDisplayName() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/extensions/IKeyMappingExtension.getDisplayName:()Lnet/minecraft/network/chat/Component;");
    }
}
