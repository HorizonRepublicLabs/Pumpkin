package net.minecraft.commands;

import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.common.extensions.ICommandSourceExtension;

public interface CommandSource extends ICommandSourceExtension {

    void sendSystemMessage(Component message);

    boolean acceptsSuccess();

    boolean acceptsFailure();

    boolean shouldInformAdmins();
}
