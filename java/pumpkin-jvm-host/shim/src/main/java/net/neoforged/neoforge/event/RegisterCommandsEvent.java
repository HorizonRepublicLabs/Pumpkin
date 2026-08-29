package net.neoforged.neoforge.event;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.neoforged.bus.api.Event;
import dev.pumpkin.shim.Unimplemented;

public class RegisterCommandsEvent extends Event {

    public RegisterCommandsEvent(CommandDispatcher<CommandSourceStack> dispatcher, Commands.CommandSelection environment, CommandBuildContext context) {
    }

    public CommandDispatcher<CommandSourceStack> getDispatcher() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/RegisterCommandsEvent.getDispatcher:()Lcom/mojang/brigadier/CommandDispatcher;");
    }

    public RegisterCommandsEvent() {
    }
}
