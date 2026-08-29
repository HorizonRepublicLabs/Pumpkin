package net.minecraft.commands.arguments;

import java.util.List;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.MessageSignature;
import dev.pumpkin.shim.Unimplemented;

public record ArgumentSignatures(List<ArgumentSignatures.Entry> entries) {

    public ArgumentSignatures(FriendlyByteBuf input) {
        this((List<ArgumentSignatures.Entry>) null);
        throw Unimplemented.forMember("net/minecraft/commands/arguments/ArgumentSignatures.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/commands/arguments/ArgumentSignatures.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public record Entry(String name, MessageSignature signature) {

        public Entry(FriendlyByteBuf input) {
            this((String) null, (MessageSignature) null);
            throw Unimplemented.forMember("net/minecraft/commands/arguments/ArgumentSignatures$Entry.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
        }

        public void write(FriendlyByteBuf output) {
            throw Unimplemented.forMember("net/minecraft/commands/arguments/ArgumentSignatures$Entry.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
        }
    }

    public interface Signer {

        MessageSignature sign(String content);
    }
}
