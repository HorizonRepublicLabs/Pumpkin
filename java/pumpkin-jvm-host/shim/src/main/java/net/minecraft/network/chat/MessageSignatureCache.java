package net.minecraft.network.chat;

import java.util.ArrayDeque;
import java.util.List;
import dev.pumpkin.shim.Unimplemented;

public class MessageSignatureCache {

    public MessageSignatureCache(int capacity) {
        throw Unimplemented.forMember("net/minecraft/network/chat/MessageSignatureCache.<init>:(I)V");
    }

    public int pack(MessageSignature signature) {
        throw Unimplemented.forMember("net/minecraft/network/chat/MessageSignatureCache.pack:(Lnet/minecraft/network/chat/MessageSignature;)I");
    }

    public MessageSignature unpack(int id) {
        throw Unimplemented.forMember("net/minecraft/network/chat/MessageSignatureCache.unpack:(I)Lnet/minecraft/network/chat/MessageSignature;");
    }

    void push(List<MessageSignature> entries) {
        throw Unimplemented.forMember("net/minecraft/network/chat/MessageSignatureCache.push:(Ljava/util/List;)V");
    }

    private void push(ArrayDeque<MessageSignature> queue) {
        throw Unimplemented.forMember("net/minecraft/network/chat/MessageSignatureCache.push:(Ljava/util/ArrayDeque;)V");
    }

    public MessageSignatureCache() {
    }
}
