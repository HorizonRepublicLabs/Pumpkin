package net.minecraft.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.exceptions.CommandExceptionType;
import net.minecraft.commands.execution.TraceCallbacks;
import net.minecraft.server.permissions.PermissionSetSupplier;

public interface ExecutionCommandSource<T extends ExecutionCommandSource<T>> extends PermissionSetSupplier {

    T withCallback(CommandResultCallback resultCallback);

    CommandResultCallback callback();

    CommandDispatcher<T> dispatcher();

    void handleError(CommandExceptionType type, Message message, boolean forked, TraceCallbacks tracer);

    boolean isSilent();
}
