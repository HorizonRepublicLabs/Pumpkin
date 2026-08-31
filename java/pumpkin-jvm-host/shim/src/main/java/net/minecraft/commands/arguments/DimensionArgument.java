package net.minecraft.commands.arguments;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import dev.pumpkin.shim.Unimplemented;

public class DimensionArgument implements ArgumentType<Identifier> {

    public Identifier parse(StringReader reader) throws CommandSyntaxException {
        throw Unimplemented.forMember("net/minecraft/commands/arguments/DimensionArgument.parse:(Lcom/mojang/brigadier/StringReader;)Lnet/minecraft/resources/Identifier;");
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        throw Unimplemented.forMember("net/minecraft/commands/arguments/DimensionArgument.listSuggestions:(Lcom/mojang/brigadier/context/CommandContext;Lcom/mojang/brigadier/suggestion/SuggestionsBuilder;)Ljava/util/concurrent/CompletableFuture;");
    }

    public Collection<String> getExamples() {
        throw Unimplemented.forMember("net/minecraft/commands/arguments/DimensionArgument.getExamples:()Ljava/util/Collection;");
    }

    public static DimensionArgument dimension() {
        throw Unimplemented.forMember("net/minecraft/commands/arguments/DimensionArgument.dimension:()Lnet/minecraft/commands/arguments/DimensionArgument;");
    }

    public static ServerLevel getDimension(CommandContext<CommandSourceStack> context, String name) throws CommandSyntaxException {
        throw Unimplemented.forMember("net/minecraft/commands/arguments/DimensionArgument.getDimension:(Lcom/mojang/brigadier/context/CommandContext;Ljava/lang/String;)Lnet/minecraft/server/level/ServerLevel;");
    }

    public DimensionArgument() {
    }
}
