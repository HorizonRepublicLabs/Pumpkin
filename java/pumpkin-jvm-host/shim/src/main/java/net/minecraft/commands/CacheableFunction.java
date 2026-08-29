package net.minecraft.commands;

import java.util.Optional;
import net.minecraft.commands.functions.CommandFunction;
import net.minecraft.resources.Identifier;
import net.minecraft.server.ServerFunctionManager;
import dev.pumpkin.shim.Unimplemented;

public class CacheableFunction {

    public CacheableFunction(Identifier id) {
        throw Unimplemented.forMember("net/minecraft/commands/CacheableFunction.<init>:(Lnet/minecraft/resources/Identifier;)V");
    }

    public Optional<CommandFunction<CommandSourceStack>> get(ServerFunctionManager manager) {
        throw Unimplemented.forMember("net/minecraft/commands/CacheableFunction.get:(Lnet/minecraft/server/ServerFunctionManager;)Ljava/util/Optional;");
    }

    public Identifier getId() {
        throw Unimplemented.forMember("net/minecraft/commands/CacheableFunction.getId:()Lnet/minecraft/resources/Identifier;");
    }

    public boolean equals(Object obj) {
        throw Unimplemented.forMember("net/minecraft/commands/CacheableFunction.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/minecraft/commands/CacheableFunction.hashCode:()I");
    }

    public CacheableFunction() {
    }
}
