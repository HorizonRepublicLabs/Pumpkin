package net.minecraft.client.multiplayer;

import com.mojang.authlib.GameProfile;
import java.util.Map;
import java.util.UUID;
import net.minecraft.client.gui.components.ChatComponent;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.telemetry.WorldSessionTelemetryManager;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.Identifier;
import net.minecraft.server.ServerLinks;
import net.minecraft.world.flag.FeatureFlagSet;

public record CommonListenerCookie(LevelLoadTracker levelLoadTracker, GameProfile localGameProfile, WorldSessionTelemetryManager telemetryManager, RegistryAccess.Frozen receivedRegistries, FeatureFlagSet enabledFeatures, String serverBrand, ServerData serverData, Screen postDisconnectScreen, Map<Identifier, byte[]> serverCookies, ChatComponent.State chatState, Map<String, String> customReportDetails, ServerLinks serverLinks, Map<UUID, PlayerInfo> seenPlayers, boolean seenInsecureChatWarning, net.neoforged.neoforge.network.connection.ConnectionType connectionType) {

    public CommonListenerCookie(LevelLoadTracker levelLoadTracker, GameProfile localGameProfile, WorldSessionTelemetryManager telemetryManager, RegistryAccess.Frozen receivedRegistries, FeatureFlagSet enabledFeatures, String serverBrand, ServerData serverData, Screen postDisconnectScreen, Map<Identifier, byte[]> serverCookies, ChatComponent.State chatState, Map<String, String> customReportDetails, ServerLinks serverLinks, Map<UUID, PlayerInfo> seenPlayers, boolean seenInsecureChatWarning) {
        this((LevelLoadTracker) null, (GameProfile) null, (WorldSessionTelemetryManager) null, (RegistryAccess.Frozen) null, (FeatureFlagSet) null, (String) null, (ServerData) null, (Screen) null, (Map<Identifier, byte[]>) null, (ChatComponent.State) null, (Map<String, String>) null, (ServerLinks) null, (Map<UUID, PlayerInfo>) null, (boolean) false, (net.neoforged.neoforge.network.connection.ConnectionType) null);
    }
}
