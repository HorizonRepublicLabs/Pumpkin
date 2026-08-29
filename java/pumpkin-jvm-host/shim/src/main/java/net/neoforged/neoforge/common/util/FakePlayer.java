package net.neoforged.neoforge.common.util;

import com.mojang.authlib.GameProfile;
import com.mojang.datafixers.DataFixer;
import io.netty.channel.ChannelFutureListener;
import java.nio.file.Path;
import java.util.OptionalInt;
import java.util.Set;
import java.util.function.Consumer;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.network.DisconnectionDetails;
import net.minecraft.network.PacketListener;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.PlayerChatMessage;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.common.ServerboundClientInformationPacket;
import net.minecraft.network.protocol.common.ServerboundCustomPayloadPacket;
import net.minecraft.network.protocol.common.ServerboundKeepAlivePacket;
import net.minecraft.network.protocol.common.ServerboundResourcePackPacket;
import net.minecraft.network.protocol.game.ServerboundAcceptTeleportationPacket;
import net.minecraft.network.protocol.game.ServerboundBlockEntityTagQueryPacket;
import net.minecraft.network.protocol.game.ServerboundChangeDifficultyPacket;
import net.minecraft.network.protocol.game.ServerboundChatAckPacket;
import net.minecraft.network.protocol.game.ServerboundChatCommandPacket;
import net.minecraft.network.protocol.game.ServerboundChatPacket;
import net.minecraft.network.protocol.game.ServerboundChatSessionUpdatePacket;
import net.minecraft.network.protocol.game.ServerboundClientCommandPacket;
import net.minecraft.network.protocol.game.ServerboundCommandSuggestionPacket;
import net.minecraft.network.protocol.game.ServerboundContainerButtonClickPacket;
import net.minecraft.network.protocol.game.ServerboundContainerClickPacket;
import net.minecraft.network.protocol.game.ServerboundContainerClosePacket;
import net.minecraft.network.protocol.game.ServerboundEditBookPacket;
import net.minecraft.network.protocol.game.ServerboundEntityTagQueryPacket;
import net.minecraft.network.protocol.game.ServerboundInteractPacket;
import net.minecraft.network.protocol.game.ServerboundJigsawGeneratePacket;
import net.minecraft.network.protocol.game.ServerboundLockDifficultyPacket;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket;
import net.minecraft.network.protocol.game.ServerboundMoveVehiclePacket;
import net.minecraft.network.protocol.game.ServerboundPaddleBoatPacket;
import net.minecraft.network.protocol.game.ServerboundPlaceRecipePacket;
import net.minecraft.network.protocol.game.ServerboundPlayerAbilitiesPacket;
import net.minecraft.network.protocol.game.ServerboundPlayerActionPacket;
import net.minecraft.network.protocol.game.ServerboundPlayerCommandPacket;
import net.minecraft.network.protocol.game.ServerboundPlayerInputPacket;
import net.minecraft.network.protocol.game.ServerboundRecipeBookChangeSettingsPacket;
import net.minecraft.network.protocol.game.ServerboundRecipeBookSeenRecipePacket;
import net.minecraft.network.protocol.game.ServerboundRenameItemPacket;
import net.minecraft.network.protocol.game.ServerboundSeenAdvancementsPacket;
import net.minecraft.network.protocol.game.ServerboundSelectTradePacket;
import net.minecraft.network.protocol.game.ServerboundSetBeaconPacket;
import net.minecraft.network.protocol.game.ServerboundSetCarriedItemPacket;
import net.minecraft.network.protocol.game.ServerboundSetCommandBlockPacket;
import net.minecraft.network.protocol.game.ServerboundSetCommandMinecartPacket;
import net.minecraft.network.protocol.game.ServerboundSetCreativeModeSlotPacket;
import net.minecraft.network.protocol.game.ServerboundSetJigsawBlockPacket;
import net.minecraft.network.protocol.game.ServerboundSetStructureBlockPacket;
import net.minecraft.network.protocol.game.ServerboundSignUpdatePacket;
import net.minecraft.network.protocol.game.ServerboundSwingPacket;
import net.minecraft.network.protocol.game.ServerboundTeleportToEntityPacket;
import net.minecraft.network.protocol.game.ServerboundUseItemOnPacket;
import net.minecraft.network.protocol.game.ServerboundUseItemPacket;
import net.minecraft.resources.Identifier;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerAdvancements;
import net.minecraft.server.ServerAdvancementManager;
import net.minecraft.server.level.ClientInformation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.server.players.PlayerList;
import net.minecraft.stats.Stat;
import net.minecraft.world.Container;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.PositionMoveRotation;
import net.minecraft.world.entity.Relative;
import net.minecraft.world.entity.animal.equine.AbstractHorse;
import net.minecraft.world.entity.player.Player;
import dev.pumpkin.shim.Unimplemented;

public class FakePlayer extends ServerPlayer {

    public FakePlayer(ServerLevel level, GameProfile name) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer.<init>:(Lnet/minecraft/server/level/ServerLevel;Lcom/mojang/authlib/GameProfile;)V");
    }

    public void sendSystemMessage(Component chatComponent, boolean actionBar) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer.sendSystemMessage:(Lnet/minecraft/network/chat/Component;Z)V");
    }

    public void awardStat(Stat<?> stat, int amount) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer.awardStat:(Lnet/minecraft/stats/Stat;I)V");
    }

    public boolean canHarmPlayer(Player player) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer.canHarmPlayer:(Lnet/minecraft/world/entity/player/Player;)Z");
    }

    public void die(DamageSource source) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer.die:(Lnet/minecraft/world/damagesource/DamageSource;)V");
    }

    public void tick() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer.tick:()V");
    }

    public void updateOptions(ClientInformation information) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer.updateOptions:(Lnet/minecraft/server/level/ClientInformation;)V");
    }

    public OptionalInt openMenu(MenuProvider menuProvider, Consumer<RegistryFriendlyByteBuf> extraDataWriter) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer.openMenu:(Lnet/minecraft/world/MenuProvider;Ljava/util/function/Consumer;)Ljava/util/OptionalInt;");
    }

    public void openHorseInventory(AbstractHorse horse, Container container) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer.openHorseInventory:(Lnet/minecraft/world/entity/animal/equine/AbstractHorse;Lnet/minecraft/world/Container;)V");
    }

    public boolean startRiding(Entity entityToRide, boolean force, boolean sendEventAndTriggers) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer.startRiding:(Lnet/minecraft/world/entity/Entity;ZZ)Z");
    }

    public boolean isFakePlayer() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer.isFakePlayer:()Z");
    }

    public static class FakePlayerAdvancements extends PlayerAdvancements {

        public FakePlayerAdvancements(DataFixer fixer, PlayerList playerList, ServerAdvancementManager manager, Path path, ServerPlayer player) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerAdvancements.<init>:(Lcom/mojang/datafixers/DataFixer;Lnet/minecraft/server/players/PlayerList;Lnet/minecraft/server/ServerAdvancementManager;Ljava/nio/file/Path;Lnet/minecraft/server/level/ServerPlayer;)V");
        }

        protected void load(ServerAdvancementManager manager) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerAdvancements.load:(Lnet/minecraft/server/ServerAdvancementManager;)V");
        }

        public void setPlayer(ServerPlayer player) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerAdvancements.setPlayer:(Lnet/minecraft/server/level/ServerPlayer;)V");
        }

        public void clearTriggers() {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerAdvancements.clearTriggers:()V");
        }

        public void reload(ServerAdvancementManager manager) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerAdvancements.reload:(Lnet/minecraft/server/ServerAdvancementManager;)V");
        }

        public void save() {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerAdvancements.save:()V");
        }

        public boolean award(AdvancementHolder advancement, String criterion) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerAdvancements.award:(Lnet/minecraft/advancements/AdvancementHolder;Ljava/lang/String;)Z");
        }

        public boolean revoke(AdvancementHolder advancement, String criterion) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerAdvancements.revoke:(Lnet/minecraft/advancements/AdvancementHolder;Ljava/lang/String;)Z");
        }

        public void flushDirty(ServerPlayer player, boolean showAdvancements) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerAdvancements.flushDirty:(Lnet/minecraft/server/level/ServerPlayer;Z)V");
        }

        public void setSelectedTab(AdvancementHolder advancement) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerAdvancements.setSelectedTab:(Lnet/minecraft/advancements/AdvancementHolder;)V");
        }

        public AdvancementProgress getOrStartProgress(AdvancementHolder advancement) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerAdvancements.getOrStartProgress:(Lnet/minecraft/advancements/AdvancementHolder;)Lnet/minecraft/advancements/AdvancementProgress;");
        }

        protected FakePlayerAdvancements() {
        }
    }

    private static class FakePlayerNetHandler extends ServerGamePacketListenerImpl {

        public FakePlayerNetHandler(MinecraftServer server, ServerPlayer player) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerNetHandler.<init>:(Lnet/minecraft/server/MinecraftServer;Lnet/minecraft/server/level/ServerPlayer;)V");
        }

        public void tick() {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerNetHandler.tick:()V");
        }

        public void resetPosition() {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerNetHandler.resetPosition:()V");
        }

        public void disconnect(Component message) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerNetHandler.disconnect:(Lnet/minecraft/network/chat/Component;)V");
        }

        public void handlePlayerInput(ServerboundPlayerInputPacket packet) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerNetHandler.handlePlayerInput:(Lnet/minecraft/network/protocol/game/ServerboundPlayerInputPacket;)V");
        }

        public void handleMoveVehicle(ServerboundMoveVehiclePacket packet) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerNetHandler.handleMoveVehicle:(Lnet/minecraft/network/protocol/game/ServerboundMoveVehiclePacket;)V");
        }

        public void handleAcceptTeleportPacket(ServerboundAcceptTeleportationPacket packet) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerNetHandler.handleAcceptTeleportPacket:(Lnet/minecraft/network/protocol/game/ServerboundAcceptTeleportationPacket;)V");
        }

        public void handleRecipeBookSeenRecipePacket(ServerboundRecipeBookSeenRecipePacket packet) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerNetHandler.handleRecipeBookSeenRecipePacket:(Lnet/minecraft/network/protocol/game/ServerboundRecipeBookSeenRecipePacket;)V");
        }

        public void handleRecipeBookChangeSettingsPacket(ServerboundRecipeBookChangeSettingsPacket packet) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerNetHandler.handleRecipeBookChangeSettingsPacket:(Lnet/minecraft/network/protocol/game/ServerboundRecipeBookChangeSettingsPacket;)V");
        }

        public void handleSeenAdvancements(ServerboundSeenAdvancementsPacket packet) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerNetHandler.handleSeenAdvancements:(Lnet/minecraft/network/protocol/game/ServerboundSeenAdvancementsPacket;)V");
        }

        public void handleCustomCommandSuggestions(ServerboundCommandSuggestionPacket packet) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerNetHandler.handleCustomCommandSuggestions:(Lnet/minecraft/network/protocol/game/ServerboundCommandSuggestionPacket;)V");
        }

        public void handleSetCommandBlock(ServerboundSetCommandBlockPacket packet) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerNetHandler.handleSetCommandBlock:(Lnet/minecraft/network/protocol/game/ServerboundSetCommandBlockPacket;)V");
        }

        public void handleSetCommandMinecart(ServerboundSetCommandMinecartPacket packet) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerNetHandler.handleSetCommandMinecart:(Lnet/minecraft/network/protocol/game/ServerboundSetCommandMinecartPacket;)V");
        }

        public void handleRenameItem(ServerboundRenameItemPacket packet) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerNetHandler.handleRenameItem:(Lnet/minecraft/network/protocol/game/ServerboundRenameItemPacket;)V");
        }

        public void handleSetBeaconPacket(ServerboundSetBeaconPacket packet) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerNetHandler.handleSetBeaconPacket:(Lnet/minecraft/network/protocol/game/ServerboundSetBeaconPacket;)V");
        }

        public void handleSetStructureBlock(ServerboundSetStructureBlockPacket packet) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerNetHandler.handleSetStructureBlock:(Lnet/minecraft/network/protocol/game/ServerboundSetStructureBlockPacket;)V");
        }

        public void handleSetJigsawBlock(ServerboundSetJigsawBlockPacket packet) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerNetHandler.handleSetJigsawBlock:(Lnet/minecraft/network/protocol/game/ServerboundSetJigsawBlockPacket;)V");
        }

        public void handleJigsawGenerate(ServerboundJigsawGeneratePacket packet) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerNetHandler.handleJigsawGenerate:(Lnet/minecraft/network/protocol/game/ServerboundJigsawGeneratePacket;)V");
        }

        public void handleSelectTrade(ServerboundSelectTradePacket packet) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerNetHandler.handleSelectTrade:(Lnet/minecraft/network/protocol/game/ServerboundSelectTradePacket;)V");
        }

        public void handleEditBook(ServerboundEditBookPacket packet) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerNetHandler.handleEditBook:(Lnet/minecraft/network/protocol/game/ServerboundEditBookPacket;)V");
        }

        public void handleEntityTagQuery(ServerboundEntityTagQueryPacket packet) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerNetHandler.handleEntityTagQuery:(Lnet/minecraft/network/protocol/game/ServerboundEntityTagQueryPacket;)V");
        }

        public void handleBlockEntityTagQuery(ServerboundBlockEntityTagQueryPacket packet) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerNetHandler.handleBlockEntityTagQuery:(Lnet/minecraft/network/protocol/game/ServerboundBlockEntityTagQueryPacket;)V");
        }

        public void handleMovePlayer(ServerboundMovePlayerPacket packet) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerNetHandler.handleMovePlayer:(Lnet/minecraft/network/protocol/game/ServerboundMovePlayerPacket;)V");
        }

        public void teleport(double x, double y, double z, float yaw, float pitch) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerNetHandler.teleport:(DDDFF)V");
        }

        public void handlePlayerAction(ServerboundPlayerActionPacket packet) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerNetHandler.handlePlayerAction:(Lnet/minecraft/network/protocol/game/ServerboundPlayerActionPacket;)V");
        }

        public void handleUseItemOn(ServerboundUseItemOnPacket packet) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerNetHandler.handleUseItemOn:(Lnet/minecraft/network/protocol/game/ServerboundUseItemOnPacket;)V");
        }

        public void handleUseItem(ServerboundUseItemPacket packet) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerNetHandler.handleUseItem:(Lnet/minecraft/network/protocol/game/ServerboundUseItemPacket;)V");
        }

        public void handleTeleportToEntityPacket(ServerboundTeleportToEntityPacket packet) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerNetHandler.handleTeleportToEntityPacket:(Lnet/minecraft/network/protocol/game/ServerboundTeleportToEntityPacket;)V");
        }

        public void handleResourcePackResponse(ServerboundResourcePackPacket packet) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerNetHandler.handleResourcePackResponse:(Lnet/minecraft/network/protocol/common/ServerboundResourcePackPacket;)V");
        }

        public void handlePaddleBoat(ServerboundPaddleBoatPacket packet) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerNetHandler.handlePaddleBoat:(Lnet/minecraft/network/protocol/game/ServerboundPaddleBoatPacket;)V");
        }

        public void onDisconnect(DisconnectionDetails details) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerNetHandler.onDisconnect:(Lnet/minecraft/network/DisconnectionDetails;)V");
        }

        public void send(Packet<?> packet) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerNetHandler.send:(Lnet/minecraft/network/protocol/Packet;)V");
        }

        public void send(Packet<?> packet, ChannelFutureListener sendListener) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerNetHandler.send:(Lnet/minecraft/network/protocol/Packet;Lio/netty/channel/ChannelFutureListener;)V");
        }

        public void handleSetCarriedItem(ServerboundSetCarriedItemPacket packet) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerNetHandler.handleSetCarriedItem:(Lnet/minecraft/network/protocol/game/ServerboundSetCarriedItemPacket;)V");
        }

        public void handleChat(ServerboundChatPacket packet) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerNetHandler.handleChat:(Lnet/minecraft/network/protocol/game/ServerboundChatPacket;)V");
        }

        public void handleAnimate(ServerboundSwingPacket packet) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerNetHandler.handleAnimate:(Lnet/minecraft/network/protocol/game/ServerboundSwingPacket;)V");
        }

        public void handlePlayerCommand(ServerboundPlayerCommandPacket packet) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerNetHandler.handlePlayerCommand:(Lnet/minecraft/network/protocol/game/ServerboundPlayerCommandPacket;)V");
        }

        public void handleInteract(ServerboundInteractPacket packet) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerNetHandler.handleInteract:(Lnet/minecraft/network/protocol/game/ServerboundInteractPacket;)V");
        }

        public void handleClientCommand(ServerboundClientCommandPacket packet) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerNetHandler.handleClientCommand:(Lnet/minecraft/network/protocol/game/ServerboundClientCommandPacket;)V");
        }

        public void handleContainerClose(ServerboundContainerClosePacket packet) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerNetHandler.handleContainerClose:(Lnet/minecraft/network/protocol/game/ServerboundContainerClosePacket;)V");
        }

        public void handleContainerClick(ServerboundContainerClickPacket packet) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerNetHandler.handleContainerClick:(Lnet/minecraft/network/protocol/game/ServerboundContainerClickPacket;)V");
        }

        public void handlePlaceRecipe(ServerboundPlaceRecipePacket packet) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerNetHandler.handlePlaceRecipe:(Lnet/minecraft/network/protocol/game/ServerboundPlaceRecipePacket;)V");
        }

        public void handleContainerButtonClick(ServerboundContainerButtonClickPacket packet) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerNetHandler.handleContainerButtonClick:(Lnet/minecraft/network/protocol/game/ServerboundContainerButtonClickPacket;)V");
        }

        public void handleSetCreativeModeSlot(ServerboundSetCreativeModeSlotPacket packet) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerNetHandler.handleSetCreativeModeSlot:(Lnet/minecraft/network/protocol/game/ServerboundSetCreativeModeSlotPacket;)V");
        }

        public void handleSignUpdate(ServerboundSignUpdatePacket packet) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerNetHandler.handleSignUpdate:(Lnet/minecraft/network/protocol/game/ServerboundSignUpdatePacket;)V");
        }

        public void handleKeepAlive(ServerboundKeepAlivePacket packet) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerNetHandler.handleKeepAlive:(Lnet/minecraft/network/protocol/common/ServerboundKeepAlivePacket;)V");
        }

        public void handleCustomPayload(ServerboundCustomPayloadPacket packet) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerNetHandler.handleCustomPayload:(Lnet/minecraft/network/protocol/common/ServerboundCustomPayloadPacket;)V");
        }

        public void handleClientInformation(ServerboundClientInformationPacket packet) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerNetHandler.handleClientInformation:(Lnet/minecraft/network/protocol/common/ServerboundClientInformationPacket;)V");
        }

        public void handlePlayerAbilities(ServerboundPlayerAbilitiesPacket packet) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerNetHandler.handlePlayerAbilities:(Lnet/minecraft/network/protocol/game/ServerboundPlayerAbilitiesPacket;)V");
        }

        public void handleChangeDifficulty(ServerboundChangeDifficultyPacket packet) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerNetHandler.handleChangeDifficulty:(Lnet/minecraft/network/protocol/game/ServerboundChangeDifficultyPacket;)V");
        }

        public void handleLockDifficulty(ServerboundLockDifficultyPacket packet) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerNetHandler.handleLockDifficulty:(Lnet/minecraft/network/protocol/game/ServerboundLockDifficultyPacket;)V");
        }

        public void teleport(PositionMoveRotation posMoveRot, Set<Relative> relatives) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerNetHandler.teleport:(Lnet/minecraft/world/entity/PositionMoveRotation;Ljava/util/Set;)V");
        }

        public void ackBlockChangesUpTo(int sequence) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerNetHandler.ackBlockChangesUpTo:(I)V");
        }

        public void handleChatCommand(ServerboundChatCommandPacket packet) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerNetHandler.handleChatCommand:(Lnet/minecraft/network/protocol/game/ServerboundChatCommandPacket;)V");
        }

        public void handleChatAck(ServerboundChatAckPacket packet) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerNetHandler.handleChatAck:(Lnet/minecraft/network/protocol/game/ServerboundChatAckPacket;)V");
        }

        public void sendPlayerChatMessage(PlayerChatMessage message, ChatType.Bound boundChatType) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerNetHandler.sendPlayerChatMessage:(Lnet/minecraft/network/chat/PlayerChatMessage;Lnet/minecraft/network/chat/ChatType$Bound;)V");
        }

        public void sendDisguisedChatMessage(Component content, ChatType.Bound boundChatType) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerNetHandler.sendDisguisedChatMessage:(Lnet/minecraft/network/chat/Component;Lnet/minecraft/network/chat/ChatType$Bound;)V");
        }

        public void handleChatSessionUpdate(ServerboundChatSessionUpdatePacket packet) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerNetHandler.handleChatSessionUpdate:(Lnet/minecraft/network/protocol/game/ServerboundChatSessionUpdatePacket;)V");
        }

        public boolean hasChannel(Identifier payloadId) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakePlayerNetHandler.hasChannel:(Lnet/minecraft/resources/Identifier;)Z");
        }

        protected FakePlayerNetHandler() {
        }
    }

    private static final class FakeConnection extends net.minecraft.network.Connection {

        public FakeConnection() {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakeConnection.<init>:()V");
        }

        public void setListenerForServerboundHandshake(PacketListener listener) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/util/FakePlayer$FakeConnection.setListenerForServerboundHandshake:(Lnet/minecraft/network/PacketListener;)V");
        }
    }

    protected FakePlayer() {
    }
}
