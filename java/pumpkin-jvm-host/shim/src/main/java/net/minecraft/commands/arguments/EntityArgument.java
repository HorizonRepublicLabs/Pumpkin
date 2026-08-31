package net.minecraft.commands.arguments;

import com.google.gson.JsonObject;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.selector.EntitySelector;
import net.minecraft.commands.synchronization.ArgumentTypeInfo;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import dev.pumpkin.shim.Unimplemented;

public class EntityArgument implements ArgumentType<EntitySelector> {

    protected EntityArgument(boolean single, boolean playersOnly) {
    }

    public static EntityArgument entities() {
        throw Unimplemented.forMember("net/minecraft/commands/arguments/EntityArgument.entities:()Lnet/minecraft/commands/arguments/EntityArgument;");
    }

    public static Collection<? extends Entity> getEntities(CommandContext<CommandSourceStack> context, String name) throws CommandSyntaxException {
        throw Unimplemented.forMember("net/minecraft/commands/arguments/EntityArgument.getEntities:(Lcom/mojang/brigadier/context/CommandContext;Ljava/lang/String;)Ljava/util/Collection;");
    }

    public static EntityArgument player() {
        throw Unimplemented.forMember("net/minecraft/commands/arguments/EntityArgument.player:()Lnet/minecraft/commands/arguments/EntityArgument;");
    }

    public static EntityArgument players() {
        throw Unimplemented.forMember("net/minecraft/commands/arguments/EntityArgument.players:()Lnet/minecraft/commands/arguments/EntityArgument;");
    }

    public static Collection<ServerPlayer> getPlayers(CommandContext<CommandSourceStack> context, String name) throws CommandSyntaxException {
        throw Unimplemented.forMember("net/minecraft/commands/arguments/EntityArgument.getPlayers:(Lcom/mojang/brigadier/context/CommandContext;Ljava/lang/String;)Ljava/util/Collection;");
    }

    public EntitySelector parse(StringReader reader) throws CommandSyntaxException {
        throw Unimplemented.forMember("net/minecraft/commands/arguments/EntityArgument.parse:(Lcom/mojang/brigadier/StringReader;)Lnet/minecraft/commands/arguments/selector/EntitySelector;");
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> contextBuilder, SuggestionsBuilder builder) {
        throw Unimplemented.forMember("net/minecraft/commands/arguments/EntityArgument.listSuggestions:(Lcom/mojang/brigadier/context/CommandContext;Lcom/mojang/brigadier/suggestion/SuggestionsBuilder;)Ljava/util/concurrent/CompletableFuture;");
    }

    public Collection<String> getExamples() {
        throw Unimplemented.forMember("net/minecraft/commands/arguments/EntityArgument.getExamples:()Ljava/util/Collection;");
    }

    public static class Info implements ArgumentTypeInfo<EntityArgument, EntityArgument.Info.Template> {

        public void serializeToNetwork(EntityArgument.Info.Template template, FriendlyByteBuf out) {
            throw Unimplemented.forMember("net/minecraft/commands/arguments/EntityArgument$Info.serializeToNetwork:(Lnet/minecraft/commands/arguments/EntityArgument$Info$Template;Lnet/minecraft/network/FriendlyByteBuf;)V");
        }

        public EntityArgument.Info.Template deserializeFromNetwork(FriendlyByteBuf in) {
            throw Unimplemented.forMember("net/minecraft/commands/arguments/EntityArgument$Info.deserializeFromNetwork:(Lnet/minecraft/network/FriendlyByteBuf;)Lnet/minecraft/commands/arguments/EntityArgument$Info$Template;");
        }

        public void serializeToJson(EntityArgument.Info.Template template, JsonObject out) {
            throw Unimplemented.forMember("net/minecraft/commands/arguments/EntityArgument$Info.serializeToJson:(Lnet/minecraft/commands/arguments/EntityArgument$Info$Template;Lcom/google/gson/JsonObject;)V");
        }

        public EntityArgument.Info.Template unpack(EntityArgument argument) {
            throw Unimplemented.forMember("net/minecraft/commands/arguments/EntityArgument$Info.unpack:(Lnet/minecraft/commands/arguments/EntityArgument;)Lnet/minecraft/commands/arguments/EntityArgument$Info$Template;");
        }

        public final class Template implements ArgumentTypeInfo.Template<EntityArgument> {

            private Template(boolean single, boolean playersOnly) {
            }

            public EntityArgument instantiate(CommandBuildContext context) {
                throw Unimplemented.forMember("net/minecraft/commands/arguments/EntityArgument$Info$Template.instantiate:(Lnet/minecraft/commands/CommandBuildContext;)Lnet/minecraft/commands/arguments/EntityArgument;");
            }

            public ArgumentTypeInfo<EntityArgument, ?> type() {
                throw Unimplemented.forMember("net/minecraft/commands/arguments/EntityArgument$Info$Template.type:()Lnet/minecraft/commands/synchronization/ArgumentTypeInfo;");
            }

            public Template() {
            }
        }

        public Info() {
        }
    }

    public EntityArgument() {
    }
}
