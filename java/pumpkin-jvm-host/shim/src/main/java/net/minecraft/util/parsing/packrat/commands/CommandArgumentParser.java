package net.minecraft.util.parsing.packrat.commands;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.concurrent.CompletableFuture;

public interface CommandArgumentParser<T> {

    T parseForCommands(StringReader reader) throws CommandSyntaxException;

    CompletableFuture<Suggestions> parseForSuggestions(SuggestionsBuilder suggestionsBuilder);
}
