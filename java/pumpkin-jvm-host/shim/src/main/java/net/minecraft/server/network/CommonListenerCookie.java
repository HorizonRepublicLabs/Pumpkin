package net.minecraft.server.network;

import com.mojang.authlib.GameProfile;
import net.minecraft.server.level.ClientInformation;

public record CommonListenerCookie(GameProfile gameProfile, int latency, ClientInformation clientInformation, boolean transferred, net.neoforged.neoforge.network.connection.ConnectionType connectionType) {

    public CommonListenerCookie(GameProfile gameProfile, int latency, ClientInformation clientInformation, boolean transferred) {
        this((GameProfile) null, (int) 0, (ClientInformation) null, (boolean) false, (net.neoforged.neoforge.network.connection.ConnectionType) null);
    }
}
