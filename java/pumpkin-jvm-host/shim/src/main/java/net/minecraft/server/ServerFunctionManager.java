package net.minecraft.server;

import java.util.Optional;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.functions.CommandFunction;
import net.minecraft.resources.Identifier;
import dev.pumpkin.shim.Unimplemented;

public class ServerFunctionManager {

    public ServerFunctionManager(MinecraftServer server, ServerFunctionLibrary library) {
        throw Unimplemented.forMember("net/minecraft/server/ServerFunctionManager.<init>:(Lnet/minecraft/server/MinecraftServer;Lnet/minecraft/server/ServerFunctionLibrary;)V");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/server/ServerFunctionManager.tick:()V");
    }

    public Optional<CommandFunction<CommandSourceStack>> get(Identifier id) {
        throw Unimplemented.forMember("net/minecraft/server/ServerFunctionManager.get:(Lnet/minecraft/resources/Identifier;)Ljava/util/Optional;");
    }

    protected ServerFunctionManager() {
    }
}
