package net.minecraft.world.level;

import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.storage.ValueInput;
import dev.pumpkin.shim.Unimplemented;

public abstract class BaseCommandBlock {

    public void load(ValueInput input) {
        throw Unimplemented.forMember("net/minecraft/world/level/BaseCommandBlock.load:(Lnet/minecraft/world/level/storage/ValueInput;)V");
    }

    public Component getName() {
        throw Unimplemented.forMember("net/minecraft/world/level/BaseCommandBlock.getName:()Lnet/minecraft/network/chat/Component;");
    }

    public abstract void onUpdated(ServerLevel level);

    public abstract CommandSourceStack createCommandSourceStack(ServerLevel level, CommandSource source);

    public abstract boolean isValid();

    protected class CloseableCommandBlockSource implements CommandSource, AutoCloseable {

        protected CloseableCommandBlockSource(ServerLevel level) {
        }

        public boolean acceptsSuccess() {
            throw Unimplemented.forMember("net/minecraft/world/level/BaseCommandBlock$CloseableCommandBlockSource.acceptsSuccess:()Z");
        }

        public boolean acceptsFailure() {
            throw Unimplemented.forMember("net/minecraft/world/level/BaseCommandBlock$CloseableCommandBlockSource.acceptsFailure:()Z");
        }

        public boolean shouldInformAdmins() {
            throw Unimplemented.forMember("net/minecraft/world/level/BaseCommandBlock$CloseableCommandBlockSource.shouldInformAdmins:()Z");
        }

        public void sendSystemMessage(Component message) {
            throw Unimplemented.forMember("net/minecraft/world/level/BaseCommandBlock$CloseableCommandBlockSource.sendSystemMessage:(Lnet/minecraft/network/chat/Component;)V");
        }

        public void close() throws Exception {
            throw Unimplemented.forMember("net/minecraft/world/level/BaseCommandBlock$CloseableCommandBlockSource.close:()V");
        }

        protected CloseableCommandBlockSource() {
        }
    }

    public BaseCommandBlock() {
    }
}
