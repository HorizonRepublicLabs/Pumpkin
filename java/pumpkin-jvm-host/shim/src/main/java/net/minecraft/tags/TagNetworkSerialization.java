package net.minecraft.tags;

import it.unimi.dsi.fastutil.ints.IntList;
import java.util.Map;
import net.minecraft.core.Registry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.Identifier;
import dev.pumpkin.shim.Unimplemented;

public class TagNetworkSerialization {

    public static final class NetworkPayload {

        public NetworkPayload(Map<Identifier, IntList> tags) {
        }

        public void write(FriendlyByteBuf buf) {
            throw Unimplemented.forMember("net/minecraft/tags/TagNetworkSerialization$NetworkPayload.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
        }

        public static TagNetworkSerialization.NetworkPayload read(FriendlyByteBuf buf) {
            throw Unimplemented.forMember("net/minecraft/tags/TagNetworkSerialization$NetworkPayload.read:(Lnet/minecraft/network/FriendlyByteBuf;)Lnet/minecraft/tags/TagNetworkSerialization$NetworkPayload;");
        }

        public boolean isEmpty() {
            throw Unimplemented.forMember("net/minecraft/tags/TagNetworkSerialization$NetworkPayload.isEmpty:()Z");
        }

        public int size() {
            throw Unimplemented.forMember("net/minecraft/tags/TagNetworkSerialization$NetworkPayload.size:()I");
        }

        public <T> TagLoader.LoadResult<T> resolve(Registry<T> registry) {
            throw Unimplemented.forMember("net/minecraft/tags/TagNetworkSerialization$NetworkPayload.resolve:(Lnet/minecraft/core/Registry;)Lnet/minecraft/tags/TagLoader$LoadResult;");
        }

        public NetworkPayload() {
        }
    }

    public TagNetworkSerialization() {
    }
}
