package net.minecraft.client.main;

import com.mojang.blaze3d.platform.DisplayData;
import java.io.File;
import java.net.Proxy;
import net.minecraft.client.PreferredGraphicsApi;
import net.minecraft.client.User;
import dev.pumpkin.shim.Unimplemented;

public class GameConfig {

    public GameConfig(GameConfig.UserData userData, DisplayData displayData, GameConfig.FolderData folderData, GameConfig.GameData gameData, GameConfig.QuickPlayData quickPlayData) {
        throw Unimplemented.forMember("net/minecraft/client/main/GameConfig.<init>:(Lnet/minecraft/client/main/GameConfig$UserData;Lcom/mojang/blaze3d/platform/DisplayData;Lnet/minecraft/client/main/GameConfig$FolderData;Lnet/minecraft/client/main/GameConfig$GameData;Lnet/minecraft/client/main/GameConfig$QuickPlayData;)V");
    }

    public static class FolderData {

        public FolderData(File gameDirectory, File resourcePackDirectory, File assetDirectory, String assetIndex) {
            throw Unimplemented.forMember("net/minecraft/client/main/GameConfig$FolderData.<init>:(Ljava/io/File;Ljava/io/File;Ljava/io/File;Ljava/lang/String;)V");
        }

        protected FolderData() {
        }
    }

    public static class GameData {

        public GameData(boolean demo, String launchVersion, String versionType, boolean disableMultiplayer, boolean disableChat, boolean captureTracyImages, boolean vulkanValidation, boolean renderDebugLabels, PreferredGraphicsApi forcedGraphicsApi, boolean offlineDeveloperMode) {
            throw Unimplemented.forMember("net/minecraft/client/main/GameConfig$GameData.<init>:(ZLjava/lang/String;Ljava/lang/String;ZZZZZLnet/minecraft/client/PreferredGraphicsApi;Z)V");
        }

        protected GameData() {
        }
    }

    public record QuickPlayData(String logPath, GameConfig.QuickPlayVariant variant) {

        public boolean isEnabled() {
            throw Unimplemented.forMember("net/minecraft/client/main/GameConfig$QuickPlayData.isEnabled:()Z");
        }
    }

    public record QuickPlayDisabled() implements GameConfig.QuickPlayVariant {

        public boolean isEnabled() {
            throw Unimplemented.forMember("net/minecraft/client/main/GameConfig$QuickPlayDisabled.isEnabled:()Z");
        }
    }

    public record QuickPlayMultiplayerData(String serverAddress) implements GameConfig.QuickPlayVariant {

        public boolean isEnabled() {
            throw Unimplemented.forMember("net/minecraft/client/main/GameConfig$QuickPlayMultiplayerData.isEnabled:()Z");
        }
    }

    public record QuickPlayRealmsData(String realmId) implements GameConfig.QuickPlayVariant {

        public boolean isEnabled() {
            throw Unimplemented.forMember("net/minecraft/client/main/GameConfig$QuickPlayRealmsData.isEnabled:()Z");
        }
    }

    public record QuickPlaySinglePlayerData(String worldId) implements GameConfig.QuickPlayVariant {

        public boolean isEnabled() {
            throw Unimplemented.forMember("net/minecraft/client/main/GameConfig$QuickPlaySinglePlayerData.isEnabled:()Z");
        }
    }

    public interface QuickPlayVariant {

        boolean isEnabled();
    }

    public static class UserData {

        public UserData(User user, Proxy proxy) {
            throw Unimplemented.forMember("net/minecraft/client/main/GameConfig$UserData.<init>:(Lnet/minecraft/client/User;Ljava/net/Proxy;)V");
        }

        protected UserData() {
        }
    }

    protected GameConfig() {
    }
}
