package net.minecraft.network.chat;

import java.util.Optional;
import net.minecraft.network.FriendlyByteBuf;
import dev.pumpkin.shim.Unimplemented;

public record MessageSignature(byte[] bytes) {

    public static MessageSignature read(FriendlyByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/chat/MessageSignature.read:(Lnet/minecraft/network/FriendlyByteBuf;)Lnet/minecraft/network/chat/MessageSignature;");
    }

    public static void write(FriendlyByteBuf output, MessageSignature signature) {
        throw Unimplemented.forMember("net/minecraft/network/chat/MessageSignature.write:(Lnet/minecraft/network/FriendlyByteBuf;Lnet/minecraft/network/chat/MessageSignature;)V");
    }

    public boolean equals(Object o) {
        throw Unimplemented.forMember("net/minecraft/network/chat/MessageSignature.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/minecraft/network/chat/MessageSignature.hashCode:()I");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/network/chat/MessageSignature.toString:()Ljava/lang/String;");
    }

    public MessageSignature.Packed pack(MessageSignatureCache cache) {
        throw Unimplemented.forMember("net/minecraft/network/chat/MessageSignature.pack:(Lnet/minecraft/network/chat/MessageSignatureCache;)Lnet/minecraft/network/chat/MessageSignature$Packed;");
    }

    public record Packed(int id, MessageSignature fullSignature) {

        public Packed(MessageSignature signature) {
            this((int) 0, (MessageSignature) null);
        }

        public Packed(int id) {
            this((int) 0, (MessageSignature) null);
        }

        public static MessageSignature.Packed read(FriendlyByteBuf input) {
            throw Unimplemented.forMember("net/minecraft/network/chat/MessageSignature$Packed.read:(Lnet/minecraft/network/FriendlyByteBuf;)Lnet/minecraft/network/chat/MessageSignature$Packed;");
        }

        public static void write(FriendlyByteBuf output, MessageSignature.Packed packed) {
            throw Unimplemented.forMember("net/minecraft/network/chat/MessageSignature$Packed.write:(Lnet/minecraft/network/FriendlyByteBuf;Lnet/minecraft/network/chat/MessageSignature$Packed;)V");
        }

        public Optional<MessageSignature> unpack(MessageSignatureCache cache) {
            throw Unimplemented.forMember("net/minecraft/network/chat/MessageSignature$Packed.unpack:(Lnet/minecraft/network/chat/MessageSignatureCache;)Ljava/util/Optional;");
        }
    }
}
