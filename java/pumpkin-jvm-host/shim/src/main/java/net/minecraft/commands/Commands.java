package net.minecraft.commands;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.permissions.PermissionCheck;
import net.minecraft.server.permissions.PermissionProviderCheck;
import net.minecraft.server.permissions.PermissionSetSupplier;
import dev.pumpkin.shim.Stubs;
import dev.pumpkin.shim.Unimplemented;

public class Commands {

    public static final PermissionCheck LEVEL_ALL = Stubs.of(PermissionCheck.class, "net/minecraft/server/permissions/PermissionCheck");

    public static final PermissionCheck LEVEL_GAMEMASTERS = Stubs.of(PermissionCheck.class, "net/minecraft/server/permissions/PermissionCheck");

    public Commands(Commands.CommandSelection commandSelection, CommandBuildContext context) {
    }

    public static LiteralArgumentBuilder<CommandSourceStack> literal(String literal) {
        throw Unimplemented.forMember("net/minecraft/commands/Commands.literal:(Ljava/lang/String;)Lcom/mojang/brigadier/builder/LiteralArgumentBuilder;");
    }

    public static <T> RequiredArgumentBuilder<CommandSourceStack, T> argument(String name, ArgumentType<T> type) {
        throw Unimplemented.forMember("net/minecraft/commands/Commands.argument:(Ljava/lang/String;Lcom/mojang/brigadier/arguments/ArgumentType;)Lcom/mojang/brigadier/builder/RequiredArgumentBuilder;");
    }

    public static <T extends PermissionSetSupplier> PermissionProviderCheck<T> hasPermission(PermissionCheck permission) {
        throw Unimplemented.forMember("net/minecraft/commands/Commands.hasPermission:(Lnet/minecraft/server/permissions/PermissionCheck;)Lnet/minecraft/server/permissions/PermissionProviderCheck;");
    }

    public enum CommandSelection {

        ALL, DEDICATED, INTEGRATED
    }

    public interface ParseFunction {

        void parse(StringReader value) throws CommandSyntaxException;
    }

    public Commands() {
    }
}
