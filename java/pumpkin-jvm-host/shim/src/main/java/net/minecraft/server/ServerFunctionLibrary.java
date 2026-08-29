package net.minecraft.server;

import com.mojang.brigadier.CommandDispatcher;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.permissions.PermissionSet;
import dev.pumpkin.shim.Unimplemented;

public class ServerFunctionLibrary implements PreparableReloadListener {

    public ServerFunctionLibrary(PermissionSet functionCompilationPermissions, CommandDispatcher<CommandSourceStack> dispatcher) {
    }

    public CompletableFuture<Void> reload(PreparableReloadListener.SharedState currentReload, Executor taskExecutor, PreparableReloadListener.PreparationBarrier preparationBarrier, Executor reloadExecutor) {
        throw Unimplemented.forMember("net/minecraft/server/ServerFunctionLibrary.reload:(Lnet/minecraft/server/packs/resources/PreparableReloadListener$SharedState;Ljava/util/concurrent/Executor;Lnet/minecraft/server/packs/resources/PreparableReloadListener$PreparationBarrier;Ljava/util/concurrent/Executor;)Ljava/util/concurrent/CompletableFuture;");
    }

    public ServerFunctionLibrary() {
    }
}
