package net.minecraft.network.protocol.game;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.tree.ArgumentCommandNode;
import com.mojang.brigadier.tree.CommandNode;
import com.mojang.brigadier.tree.RootCommandNode;
import java.util.List;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.synchronization.ArgumentTypeInfo;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.resources.Identifier;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundCommandsPacket implements Packet<ClientGamePacketListener> {

    public <S> ClientboundCommandsPacket(RootCommandNode<S> root, ClientboundCommandsPacket.NodeInspector<S> inspector) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundCommandsPacket.<init>:(Lcom/mojang/brigadier/tree/RootCommandNode;Lnet/minecraft/network/protocol/game/ClientboundCommandsPacket$NodeInspector;)V");
    }

    private ClientboundCommandsPacket(FriendlyByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundCommandsPacket.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundCommandsPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    private static ClientboundCommandsPacket.NodeStub read(FriendlyByteBuf input, byte flags) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundCommandsPacket.read:(Lnet/minecraft/network/FriendlyByteBuf;B)Lnet/minecraft/network/protocol/game/ClientboundCommandsPacket$NodeStub;");
    }

    public PacketType<ClientboundCommandsPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundCommandsPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundCommandsPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    private record ArgumentNodeStub(String id, ArgumentTypeInfo.Template<?> argumentType, Identifier suggestionId) implements ClientboundCommandsPacket.NodeStub {

        public <S> ArgumentBuilder<S, ?> build(CommandBuildContext context, ClientboundCommandsPacket.NodeBuilder<S> builder) {
            throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundCommandsPacket$ArgumentNodeStub.build:(Lnet/minecraft/commands/CommandBuildContext;Lnet/minecraft/network/protocol/game/ClientboundCommandsPacket$NodeBuilder;)Lcom/mojang/brigadier/builder/ArgumentBuilder;");
        }

        public void write(FriendlyByteBuf output) {
            throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundCommandsPacket$ArgumentNodeStub.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
        }
    }

    private record Entry(ClientboundCommandsPacket.NodeStub stub, int flags, int redirect, int[] children) {

        public void write(FriendlyByteBuf output) {
            throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundCommandsPacket$Entry.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
        }
    }

    private record LiteralNodeStub(String id) implements ClientboundCommandsPacket.NodeStub {

        public <S> ArgumentBuilder<S, ?> build(CommandBuildContext context, ClientboundCommandsPacket.NodeBuilder<S> builder) {
            throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundCommandsPacket$LiteralNodeStub.build:(Lnet/minecraft/commands/CommandBuildContext;Lnet/minecraft/network/protocol/game/ClientboundCommandsPacket$NodeBuilder;)Lcom/mojang/brigadier/builder/ArgumentBuilder;");
        }

        public void write(FriendlyByteBuf output) {
            throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundCommandsPacket$LiteralNodeStub.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
        }
    }

    public interface NodeBuilder<S> {

        ArgumentBuilder<S, ?> createLiteral(String id);

        ArgumentBuilder<S, ?> createArgument(String id, ArgumentType<?> argumentType, Identifier suggestionId);

        ArgumentBuilder<S, ?> configure(ArgumentBuilder<S, ?> input, boolean executable, boolean restricted);
    }

    public interface NodeInspector<S> {

        Identifier suggestionId(ArgumentCommandNode<S, ?> node);

        boolean isExecutable(CommandNode<S> node);

        boolean isRestricted(CommandNode<S> node);
    }

    private static class NodeResolver<S> {

        private NodeResolver(CommandBuildContext context, ClientboundCommandsPacket.NodeBuilder<S> builder, List<ClientboundCommandsPacket.Entry> entries) {
            throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundCommandsPacket$NodeResolver.<init>:(Lnet/minecraft/commands/CommandBuildContext;Lnet/minecraft/network/protocol/game/ClientboundCommandsPacket$NodeBuilder;Ljava/util/List;)V");
        }

        public CommandNode<S> resolve(int index) {
            throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundCommandsPacket$NodeResolver.resolve:(I)Lcom/mojang/brigadier/tree/CommandNode;");
        }

        protected NodeResolver() {
        }
    }

    private interface NodeStub {

        <S> ArgumentBuilder<S, ?> build(CommandBuildContext context, ClientboundCommandsPacket.NodeBuilder<S> builder);

        void write(FriendlyByteBuf output);
    }

    protected ClientboundCommandsPacket() {
    }
}
