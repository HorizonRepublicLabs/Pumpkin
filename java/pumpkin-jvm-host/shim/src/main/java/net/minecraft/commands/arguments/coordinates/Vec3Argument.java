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
import dev.pumpkin.shim.Unimplemented;

public class Vec3Argument implements ArgumentType<Coordinates> {

    public Vec3Argument(boolean centerCorrect) {
    }

    public static Vec3Argument vec3() {
        throw Unimplemented.forMember("net/minecraft/commands/arguments/coordinates/Vec3Argument.vec3:()Lnet/minecraft/commands/arguments/coordinates/Vec3Argument;");
    }

    public static Vec3Argument vec3(boolean centerCorrect) {
        throw Unimplemented.forMember("net/minecraft/commands/arguments/coordinates/Vec3Argument.vec3:(Z)Lnet/minecraft/commands/arguments/coordinates/Vec3Argument;");
    }

    public static Coordinates getCoordinates(CommandContext<CommandSourceStack> context, String name) {
        throw Unimplemented.forMember("net/minecraft/commands/arguments/coordinates/Vec3Argument.getCoordinates:(Lcom/mojang/brigadier/context/CommandContext;Ljava/lang/String;)Lnet/minecraft/commands/arguments/coordinates/Coordinates;");
    }

    public Coordinates parse(StringReader reader) throws CommandSyntaxException {
        throw Unimplemented.forMember("net/minecraft/commands/arguments/coordinates/Vec3Argument.parse:(Lcom/mojang/brigadier/StringReader;)Lnet/minecraft/commands/arguments/coordinates/Coordinates;");
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        throw Unimplemented.forMember("net/minecraft/commands/arguments/coordinates/Vec3Argument.listSuggestions:(Lcom/mojang/brigadier/context/CommandContext;Lcom/mojang/brigadier/suggestion/SuggestionsBuilder;)Ljava/util/concurrent/CompletableFuture;");
    }

    public Collection<String> getExamples() {
        throw Unimplemented.forMember("net/minecraft/commands/arguments/coordinates/Vec3Argument.getExamples:()Ljava/util/Collection;");
    }

    public Vec3Argument() {
    }
}
