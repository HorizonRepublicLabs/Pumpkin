package net.minecraft.world.entity.player;

import java.security.PublicKey;
import java.time.Instant;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ThrowingComponent;
import dev.pumpkin.shim.Unimplemented;

public record ProfilePublicKey(ProfilePublicKey.Data data) {

    public record Data(Instant expiresAt, PublicKey key, byte[] keySignature) {

        public Data(FriendlyByteBuf input) {
            this((Instant) null, (PublicKey) null, (byte[]) null);
            throw Unimplemented.forMember("net/minecraft/world/entity/player/ProfilePublicKey$Data.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
        }

        public void write(FriendlyByteBuf output) {
            throw Unimplemented.forMember("net/minecraft/world/entity/player/ProfilePublicKey$Data.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
        }

        public boolean equals(Object o) {
            throw Unimplemented.forMember("net/minecraft/world/entity/player/ProfilePublicKey$Data.equals:(Ljava/lang/Object;)Z");
        }
    }

    public static class ValidationException extends ThrowingComponent {

        public ValidationException(Component component) {
            throw Unimplemented.forMember("net/minecraft/world/entity/player/ProfilePublicKey$ValidationException.<init>:(Lnet/minecraft/network/chat/Component;)V");
        }

        public ValidationException() {
        }
    }
}
