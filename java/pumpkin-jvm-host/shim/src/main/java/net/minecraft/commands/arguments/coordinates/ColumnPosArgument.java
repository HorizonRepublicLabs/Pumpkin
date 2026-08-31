package net.minecraft.commands.arguments.coordinates;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.level.ColumnPos;
import dev.pumpkin.shim.Unimplemented;

public class ColumnPosArgument implements ArgumentType<Coordinates> {

    public static ColumnPosArgument columnPos() {
        throw Unimplemented.forMember("net/minecraft/commands/arguments/coordinates/ColumnPosArgument.columnPos:()Lnet/minecraft/commands/arguments/coordinates/ColumnPosArgument;");
    }

    public static ColumnPos getColumnPos(CommandContext<CommandSourceStack> context, String name) {
        throw Unimplemented.forMember("net/minecraft/commands/arguments/coordinates/ColumnPosArgument.getColumnPos:(Lcom/mojang/brigadier/context/CommandContext;Ljava/lang/String;)Lnet/minecraft/server/level/ColumnPos;");
    }

    public Coordinates parse(StringReader reader) throws CommandSyntaxException {
        throw Unimplemented.forMember("net/minecraft/commands/arguments/coordinates/ColumnPosArgument.parse:(Lcom/mojang/brigadier/StringReader;)Lnet/minecraft/commands/arguments/coordinates/Coordinates;");
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        throw Unimplemented.forMember("net/minecraft/commands/arguments/coordinates/ColumnPosArgument.listSuggestions:(Lcom/mojang/brigadier/context/CommandContext;Lcom/mojang/brigadier/suggestion/SuggestionsBuilder;)Ljava/util/concurrent/CompletableFuture;");
    }

    public Collection<String> getExamples() {
        throw Unimplemented.forMember("net/minecraft/commands/arguments/coordinates/ColumnPosArgument.getExamples:()Ljava/util/Collection;");
    }

    public ColumnPosArgument() {
    }
}
