package net.minecraft.commands.execution;

public interface UnboundEntryAction<T> {

    void execute(T sender, ExecutionContext<T> context, Frame frame);
}
