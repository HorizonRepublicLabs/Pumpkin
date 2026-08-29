package net.minecraft.server.network;

import com.mojang.authlib.GameProfile;
import net.minecraft.server.level.ClientInformation;
import dev.pumpkin.shim.Unimplemented;

public record CommonListenerCookie(GameProfile gameProfile, int latency, ClientInformation clientInformation, boolean transferred, net.neoforged.neoforge.network.connection.ConnectionType connectionType) {

    public CommonListenerCookie(GameProfile gameProfile, int latency, ClientInformation clientInformation, boolean transferred) {
        this((GameProfile) null, (int) 0, (ClientInformation) null, (boolean) false, (net.neoforged.neoforge.network.connection.ConnectionType) null);
        throw Unimplemented.forMember("net/minecraft/server/network/CommonListenerCookie.<init>:(Lcom/mojang/authlib/GameProfile;ILnet/minecraft/server/level/ClientInformation;Z)V");
    }
}
