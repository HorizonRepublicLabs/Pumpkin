package net.minecraft.network.chat;

import java.util.BitSet;
import java.util.List;
import java.util.Optional;
import net.minecraft.network.FriendlyByteBuf;
import dev.pumpkin.shim.Unimplemented;

public record LastSeenMessages(List<MessageSignature> entries) {

    public LastSeenMessages.Packed pack(MessageSignatureCache cache) {
        throw Unimplemented.forMember("net/minecraft/network/chat/LastSeenMessages.pack:(Lnet/minecraft/network/chat/MessageSignatureCache;)Lnet/minecraft/network/chat/LastSeenMessages$Packed;");
    }

    public record Packed(List<MessageSignature.Packed> entries) {

        public Packed(FriendlyByteBuf input) {
            this((List<MessageSignature.Packed>) null);
            throw Unimplemented.forMember("net/minecraft/network/chat/LastSeenMessages$Packed.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
        }

        public void write(FriendlyByteBuf output) {
            throw Unimplemented.forMember("net/minecraft/network/chat/LastSeenMessages$Packed.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
        }

        public Optional<LastSeenMessages> unpack(MessageSignatureCache cache) {
            throw Unimplemented.forMember("net/minecraft/network/chat/LastSeenMessages$Packed.unpack:(Lnet/minecraft/network/chat/MessageSignatureCache;)Ljava/util/Optional;");
        }
    }

    public record Update(int offset, BitSet acknowledged, byte checksum) {

        public Update(FriendlyByteBuf input) {
            this((int) 0, (BitSet) null, (byte) 0);
            throw Unimplemented.forMember("net/minecraft/network/chat/LastSeenMessages$Update.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
        }

        public void write(FriendlyByteBuf output) {
            throw Unimplemented.forMember("net/minecraft/network/chat/LastSeenMessages$Update.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
        }
    }
}
