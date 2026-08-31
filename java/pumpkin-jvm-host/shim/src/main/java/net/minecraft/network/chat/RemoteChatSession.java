package net.minecraft.network.chat;

import com.mojang.authlib.GameProfile;
import java.util.UUID;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.SignatureValidator;
import net.minecraft.world.entity.player.ProfilePublicKey;
import dev.pumpkin.shim.Unimplemented;

public record RemoteChatSession(UUID sessionId, ProfilePublicKey profilePublicKey) {

    public record Data(UUID sessionId, ProfilePublicKey.Data profilePublicKey) {

        public static RemoteChatSession.Data read(FriendlyByteBuf input) {
            throw Unimplemented.forMember("net/minecraft/network/chat/RemoteChatSession$Data.read:(Lnet/minecraft/network/FriendlyByteBuf;)Lnet/minecraft/network/chat/RemoteChatSession$Data;");
        }

        public static void write(FriendlyByteBuf output, RemoteChatSession.Data data) {
            throw Unimplemented.forMember("net/minecraft/network/chat/RemoteChatSession$Data.write:(Lnet/minecraft/network/FriendlyByteBuf;Lnet/minecraft/network/chat/RemoteChatSession$Data;)V");
        }

        public RemoteChatSession validate(GameProfile profile, SignatureValidator serviceSignatureValidator) throws ProfilePublicKey.ValidationException {
            throw Unimplemented.forMember("net/minecraft/network/chat/RemoteChatSession$Data.validate:(Lcom/mojang/authlib/GameProfile;Lnet/minecraft/util/SignatureValidator;)Lnet/minecraft/network/chat/RemoteChatSession;");
        }
    }
}
