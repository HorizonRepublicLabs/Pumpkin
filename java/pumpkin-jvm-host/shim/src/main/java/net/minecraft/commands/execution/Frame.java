package net.minecraft.commands.execution;

import net.minecraft.commands.CommandResultCallback;
import dev.pumpkin.shim.Unimplemented;

public record Frame(int depth, CommandResultCallback returnValueConsumer, Frame.FrameControl frameControl) {

    public void discard() {
        throw Unimplemented.forMember("net/minecraft/commands/execution/Frame.discard:()V");
    }

    public interface FrameControl {

        void discard();
    }
}
