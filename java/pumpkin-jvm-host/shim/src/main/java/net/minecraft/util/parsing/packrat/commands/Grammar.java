package net.minecraft.util.parsing.packrat.commands;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import net.minecraft.util.parsing.packrat.Dictionary;
import net.minecraft.util.parsing.packrat.NamedRule;
import net.minecraft.util.parsing.packrat.ParseState;
import dev.pumpkin.shim.Unimplemented;

public record Grammar<T>(Dictionary<StringReader> rules, NamedRule<StringReader, T> top) implements CommandArgumentParser<T> {

    public Optional<T> parse(ParseState<StringReader> state) {
        throw Unimplemented.forMember("net/minecraft/util/parsing/packrat/commands/Grammar.parse:(Lnet/minecraft/util/parsing/packrat/ParseState;)Ljava/util/Optional;");
    }

    public T parseForCommands(StringReader reader) throws CommandSyntaxException {
        throw Unimplemented.forMember("net/minecraft/util/parsing/packrat/commands/Grammar.parseForCommands:(Lcom/mojang/brigadier/StringReader;)Ljava/lang/Object;");
    }

    public CompletableFuture<Suggestions> parseForSuggestions(SuggestionsBuilder suggestionsBuilder) {
        throw Unimplemented.forMember("net/minecraft/util/parsing/packrat/commands/Grammar.parseForSuggestions:(Lcom/mojang/brigadier/suggestion/SuggestionsBuilder;)Ljava/util/concurrent/CompletableFuture;");
    }
}
