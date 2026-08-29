package net.minecraft.network.chat;

import java.time.Instant;
import java.util.Optional;
import net.minecraft.network.FriendlyByteBuf;
import dev.pumpkin.shim.Unimplemented;

public record SignedMessageBody(String content, Instant timeStamp, long salt, LastSeenMessages lastSeen) {

    public SignedMessageBody.Packed pack(MessageSignatureCache cache) {
        throw Unimplemented.forMember("net/minecraft/network/chat/SignedMessageBody.pack:(Lnet/minecraft/network/chat/MessageSignatureCache;)Lnet/minecraft/network/chat/SignedMessageBody$Packed;");
    }

    public record Packed(String content, Instant timeStamp, long salt, LastSeenMessages.Packed lastSeen) {

        public Packed(FriendlyByteBuf input) {
            this((String) null, (Instant) null, (long) 0L, (LastSeenMessages.Packed) null);
            throw Unimplemented.forMember("net/minecraft/network/chat/SignedMessageBody$Packed.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
        }

        public void write(FriendlyByteBuf output) {
            throw Unimplemented.forMember("net/minecraft/network/chat/SignedMessageBody$Packed.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
        }

        public Optional<SignedMessageBody> unpack(MessageSignatureCache cache) {
            throw Unimplemented.forMember("net/minecraft/network/chat/SignedMessageBody$Packed.unpack:(Lnet/minecraft/network/chat/MessageSignatureCache;)Ljava/util/Optional;");
        }
    }
}
