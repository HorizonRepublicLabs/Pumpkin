package net.minecraft.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandExceptionType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import java.util.stream.Stream;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.commands.execution.TraceCallbacks;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.permissions.PermissionSet;
import net.minecraft.util.TaskChainer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.extensions.ICommandSourceStackExtension;
import dev.pumpkin.shim.Stubs;
import dev.pumpkin.shim.Unimplemented;

public class CommandSourceStack implements SharedSuggestionProvider, ExecutionCommandSource<CommandSourceStack>, ICommandSourceStackExtension {

    private final PermissionSet permissions = Stubs.of(PermissionSet.class, "net/minecraft/server/permissions/PermissionSet");

    public CommandSourceStack(CommandSource source, Vec3 position, Vec2 rotation, ServerLevel level, PermissionSet permissions, String textName, Component displayName, MinecraftServer server, Entity entity) {
        throw Unimplemented.forMember("net/minecraft/commands/CommandSourceStack.<init>:(Lnet/minecraft/commands/CommandSource;Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/world/phys/Vec2;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/server/permissions/PermissionSet;Ljava/lang/String;Lnet/minecraft/network/chat/Component;Lnet/minecraft/server/MinecraftServer;Lnet/minecraft/world/entity/Entity;)V");
    }

    private CommandSourceStack(CommandSource source, Vec3 position, Vec2 rotation, ServerLevel level, PermissionSet permissions, String textName, Component displayName, MinecraftServer server, Entity entity, boolean silent, CommandResultCallback resultCallback, EntityAnchorArgument.Anchor anchor, CommandSigningContext signingContext, TaskChainer chatMessageChainer) {
        throw Unimplemented.forMember("net/minecraft/commands/CommandSourceStack.<init>:(Lnet/minecraft/commands/CommandSource;Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/world/phys/Vec2;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/server/permissions/PermissionSet;Ljava/lang/String;Lnet/minecraft/network/chat/Component;Lnet/minecraft/server/MinecraftServer;Lnet/minecraft/world/entity/Entity;ZLnet/minecraft/commands/CommandResultCallback;Lnet/minecraft/commands/arguments/EntityAnchorArgument$Anchor;Lnet/minecraft/commands/CommandSigningContext;Lnet/minecraft/util/TaskChainer;)V");
    }

    public CommandSourceStack withCallback(CommandResultCallback resultCallback) {
        throw Unimplemented.forMember("net/minecraft/commands/CommandSourceStack.withCallback:(Lnet/minecraft/commands/CommandResultCallback;)Lnet/minecraft/commands/CommandSourceStack;");
    }

    public Component getDisplayName() {
        throw Unimplemented.forMember("net/minecraft/commands/CommandSourceStack.getDisplayName:()Lnet/minecraft/network/chat/Component;");
    }

    public PermissionSet permissions() {
        throw Unimplemented.forMember("net/minecraft/commands/CommandSourceStack.permissions:()Lnet/minecraft/server/permissions/PermissionSet;");
    }

    public ServerLevel getLevel() {
        throw Unimplemented.forMember("net/minecraft/commands/CommandSourceStack.getLevel:()Lnet/minecraft/server/level/ServerLevel;");
    }

    public ServerPlayer getPlayerOrException() throws CommandSyntaxException {
        throw Unimplemented.forMember("net/minecraft/commands/CommandSourceStack.getPlayerOrException:()Lnet/minecraft/server/level/ServerPlayer;");
    }

    public ServerPlayer getPlayer() {
        throw Unimplemented.forMember("net/minecraft/commands/CommandSourceStack.getPlayer:()Lnet/minecraft/server/level/ServerPlayer;");
    }

    public MinecraftServer getServer() {
        throw Unimplemented.forMember("net/minecraft/commands/CommandSourceStack.getServer:()Lnet/minecraft/server/MinecraftServer;");
    }

    public void sendSystemMessage(Component message) {
        throw Unimplemented.forMember("net/minecraft/commands/CommandSourceStack.sendSystemMessage:(Lnet/minecraft/network/chat/Component;)V");
    }

    public void sendSuccess(Supplier<Component> messageSupplier, boolean broadcast) {
        throw Unimplemented.forMember("net/minecraft/commands/CommandSourceStack.sendSuccess:(Ljava/util/function/Supplier;Z)V");
    }

    public void sendFailure(Component message) {
        throw Unimplemented.forMember("net/minecraft/commands/CommandSourceStack.sendFailure:(Lnet/minecraft/network/chat/Component;)V");
    }

    public CommandResultCallback callback() {
        throw Unimplemented.forMember("net/minecraft/commands/CommandSourceStack.callback:()Lnet/minecraft/commands/CommandResultCallback;");
    }

    public Collection<String> getOnlinePlayerNames() {
        throw Unimplemented.forMember("net/minecraft/commands/CommandSourceStack.getOnlinePlayerNames:()Ljava/util/Collection;");
    }

    public Collection<String> getAllTeams() {
        throw Unimplemented.forMember("net/minecraft/commands/CommandSourceStack.getAllTeams:()Ljava/util/Collection;");
    }

    public Stream<Identifier> getAvailableSounds() {
        throw Unimplemented.forMember("net/minecraft/commands/CommandSourceStack.getAvailableSounds:()Ljava/util/stream/Stream;");
    }

    public CompletableFuture<Suggestions> customSuggestion(CommandContext<?> context) {
        throw Unimplemented.forMember("net/minecraft/commands/CommandSourceStack.customSuggestion:(Lcom/mojang/brigadier/context/CommandContext;)Ljava/util/concurrent/CompletableFuture;");
    }

    public CompletableFuture<Suggestions> suggestRegistryElements(ResourceKey<? extends Registry<?>> key, SharedSuggestionProvider.ElementSuggestionType elements, SuggestionsBuilder builder, CommandContext<?> context) {
        throw Unimplemented.forMember("net/minecraft/commands/CommandSourceStack.suggestRegistryElements:(Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/commands/SharedSuggestionProvider$ElementSuggestionType;Lcom/mojang/brigadier/suggestion/SuggestionsBuilder;Lcom/mojang/brigadier/context/CommandContext;)Ljava/util/concurrent/CompletableFuture;");
    }

    public Set<ResourceKey<Level>> levels() {
        throw Unimplemented.forMember("net/minecraft/commands/CommandSourceStack.levels:()Ljava/util/Set;");
    }

    public RegistryAccess registryAccess() {
        throw Unimplemented.forMember("net/minecraft/commands/CommandSourceStack.registryAccess:()Lnet/minecraft/core/RegistryAccess;");
    }

    public FeatureFlagSet enabledFeatures() {
        throw Unimplemented.forMember("net/minecraft/commands/CommandSourceStack.enabledFeatures:()Lnet/minecraft/world/flag/FeatureFlagSet;");
    }

    public CommandDispatcher<CommandSourceStack> dispatcher() {
        throw Unimplemented.forMember("net/minecraft/commands/CommandSourceStack.dispatcher:()Lcom/mojang/brigadier/CommandDispatcher;");
    }

    public void handleError(CommandExceptionType type, Message message, boolean forked, TraceCallbacks tracer) {
        throw Unimplemented.forMember("net/minecraft/commands/CommandSourceStack.handleError:(Lcom/mojang/brigadier/exceptions/CommandExceptionType;Lcom/mojang/brigadier/Message;ZLnet/minecraft/commands/execution/TraceCallbacks;)V");
    }

    public boolean isSilent() {
        throw Unimplemented.forMember("net/minecraft/commands/CommandSourceStack.isSilent:()Z");
    }

    public CommandSourceStack() {
    }
}
