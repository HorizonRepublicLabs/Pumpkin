package net.minecraft.commands;

import java.util.Map;
import net.minecraft.network.chat.PlayerChatMessage;
import dev.pumpkin.shim.Unimplemented;

public interface CommandSigningContext {

    PlayerChatMessage getArgument(String name);

    record SignedArguments(Map<String, PlayerChatMessage> arguments) implements CommandSigningContext {

        public PlayerChatMessage getArgument(String name) {
            throw Unimplemented.forMember("net/minecraft/commands/CommandSigningContext$SignedArguments.getArgument:(Ljava/lang/String;)Lnet/minecraft/network/chat/PlayerChatMessage;");
        }
    }
}
