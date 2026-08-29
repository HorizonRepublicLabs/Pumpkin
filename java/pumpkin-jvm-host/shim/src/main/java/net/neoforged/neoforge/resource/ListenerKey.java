package net.neoforged.neoforge.resource;

import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import dev.pumpkin.shim.Unimplemented;

public final class ListenerKey<T extends PreparableReloadListener> {

    public static <T extends PreparableReloadListener> ListenerKey<T> create(Identifier listenerId) {
        throw Unimplemented.forMember("net/neoforged/neoforge/resource/ListenerKey.create:(Lnet/minecraft/resources/Identifier;)Lnet/neoforged/neoforge/resource/ListenerKey;");
    }

    private ListenerKey(Identifier listenerId) {
        throw Unimplemented.forMember("net/neoforged/neoforge/resource/ListenerKey.<init>:(Lnet/minecraft/resources/Identifier;)V");
    }

    public String toString() {
        throw Unimplemented.forMember("net/neoforged/neoforge/resource/ListenerKey.toString:()Ljava/lang/String;");
    }

    protected ListenerKey() {
    }
}
