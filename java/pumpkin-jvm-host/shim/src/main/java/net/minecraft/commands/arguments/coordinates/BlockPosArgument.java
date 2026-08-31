package net.minecraft.commands.arguments.coordinates;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import dev.pumpkin.shim.Unimplemented;

public class BlockPosArgument implements ArgumentType<Coordinates> {

    public static final SimpleCommandExceptionType ERROR_OUT_OF_WORLD = null;

    public static BlockPosArgument blockPos() {
        throw Unimplemented.forMember("net/minecraft/commands/arguments/coordinates/BlockPosArgument.blockPos:()Lnet/minecraft/commands/arguments/coordinates/BlockPosArgument;");
    }

    public static BlockPos getLoadedBlockPos(CommandContext<CommandSourceStack> context, String name) throws CommandSyntaxException {
        throw Unimplemented.forMember("net/minecraft/commands/arguments/coordinates/BlockPosArgument.getLoadedBlockPos:(Lcom/mojang/brigadier/context/CommandContext;Ljava/lang/String;)Lnet/minecraft/core/BlockPos;");
    }

    public static BlockPos getLoadedBlockPos(CommandContext<CommandSourceStack> context, ServerLevel level, String name) throws CommandSyntaxException {
        throw Unimplemented.forMember("net/minecraft/commands/arguments/coordinates/BlockPosArgument.getLoadedBlockPos:(Lcom/mojang/brigadier/context/CommandContext;Lnet/minecraft/server/level/ServerLevel;Ljava/lang/String;)Lnet/minecraft/core/BlockPos;");
    }

    public Coordinates parse(StringReader reader) throws CommandSyntaxException {
        throw Unimplemented.forMember("net/minecraft/commands/arguments/coordinates/BlockPosArgument.parse:(Lcom/mojang/brigadier/StringReader;)Lnet/minecraft/commands/arguments/coordinates/Coordinates;");
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        throw Unimplemented.forMember("net/minecraft/commands/arguments/coordinates/BlockPosArgument.listSuggestions:(Lcom/mojang/brigadier/context/CommandContext;Lcom/mojang/brigadier/suggestion/SuggestionsBuilder;)Ljava/util/concurrent/CompletableFuture;");
    }

    public Collection<String> getExamples() {
        throw Unimplemented.forMember("net/minecraft/commands/arguments/coordinates/BlockPosArgument.getExamples:()Ljava/util/Collection;");
    }

    public BlockPosArgument() {
    }
}
