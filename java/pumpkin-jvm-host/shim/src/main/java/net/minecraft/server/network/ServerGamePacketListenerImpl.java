package net.minecraft.server.network;

import com.mojang.authlib.GameProfile;
import java.util.Set;
import net.minecraft.network.Connection;
import net.minecraft.network.DisconnectionDetails;
import net.minecraft.network.TickablePacketListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.common.ServerboundClientInformationPacket;
import net.minecraft.network.protocol.common.ServerboundCustomPayloadPacket;
import net.minecraft.network.protocol.game.GameProtocols;
import net.minecraft.network.protocol.game.ServerGamePacketListener;
import net.minecraft.network.protocol.game.ServerboundAcceptTeleportationPacket;
import net.minecraft.network.protocol.game.ServerboundAttackPacket;
import net.minecraft.network.protocol.game.ServerboundBlockEntityTagQueryPacket;
import net.minecraft.network.protocol.game.ServerboundChangeDifficultyPacket;
import net.minecraft.network.protocol.game.ServerboundChangeGameModePacket;
import net.minecraft.network.protocol.game.ServerboundChatAckPacket;
import net.minecraft.network.protocol.game.ServerboundChatCommandPacket;
import net.minecraft.network.protocol.game.ServerboundChatCommandSignedPacket;
import net.minecraft.network.protocol.game.ServerboundChatPacket;
import net.minecraft.network.protocol.game.ServerboundChatSessionUpdatePacket;
import net.minecraft.network.protocol.game.ServerboundChunkBatchReceivedPacket;
import net.minecraft.network.protocol.game.ServerboundClientCommandPacket;
import net.minecraft.network.protocol.game.ServerboundClientTickEndPacket;
import net.minecraft.network.protocol.game.ServerboundCommandSuggestionPacket;
import net.minecraft.network.protocol.game.ServerboundConfigurationAcknowledgedPacket;
import net.minecraft.network.protocol.game.ServerboundContainerButtonClickPacket;
import net.minecraft.network.protocol.game.ServerboundContainerClickPacket;
import net.minecraft.network.protocol.game.ServerboundContainerClosePacket;
import net.minecraft.network.protocol.game.ServerboundContainerSlotStateChangedPacket;
import net.minecraft.network.protocol.game.ServerboundDebugSubscriptionRequestPacket;
import net.minecraft.network.protocol.game.ServerboundEditBookPacket;
import net.minecraft.network.protocol.game.ServerboundEntityTagQueryPacket;
import net.minecraft.network.protocol.game.ServerboundInteractPacket;
import net.minecraft.network.protocol.game.ServerboundJigsawGeneratePacket;
import net.minecraft.network.protocol.game.ServerboundLockDifficultyPacket;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket;
import net.minecraft.network.protocol.game.ServerboundMoveVehiclePacket;
import net.minecraft.network.protocol.game.ServerboundPaddleBoatPacket;
import net.minecraft.network.protocol.game.ServerboundPickItemFromBlockPacket;
import net.minecraft.network.protocol.game.ServerboundPickItemFromEntityPacket;
import net.minecraft.network.protocol.game.ServerboundPlaceRecipePacket;
import net.minecraft.network.protocol.game.ServerboundPlayerAbilitiesPacket;
import net.minecraft.network.protocol.game.ServerboundPlayerActionPacket;
import net.minecraft.network.protocol.game.ServerboundPlayerCommandPacket;
import net.minecraft.network.protocol.game.ServerboundPlayerInputPacket;
import net.minecraft.network.protocol.game.ServerboundPlayerLoadedPacket;
import net.minecraft.network.protocol.game.ServerboundRecipeBookChangeSettingsPacket;
import net.minecraft.network.protocol.game.ServerboundRecipeBookSeenRecipePacket;
import net.minecraft.network.protocol.game.ServerboundRenameItemPacket;
import net.minecraft.network.protocol.game.ServerboundSeenAdvancementsPacket;
import net.minecraft.network.protocol.game.ServerboundSelectBundleItemPacket;
import net.minecraft.network.protocol.game.ServerboundSelectTradePacket;
import net.minecraft.network.protocol.game.ServerboundSetBeaconPacket;
import net.minecraft.network.protocol.game.ServerboundSetCarriedItemPacket;
import net.minecraft.network.protocol.game.ServerboundSetCommandBlockPacket;
import net.minecraft.network.protocol.game.ServerboundSetCommandMinecartPacket;
import net.minecraft.network.protocol.game.ServerboundSetCreativeModeSlotPacket;
import net.minecraft.network.protocol.game.ServerboundSetGameRulePacket;
import net.minecraft.network.protocol.game.ServerboundSetJigsawBlockPacket;
import net.minecraft.network.protocol.game.ServerboundSetStructureBlockPacket;
import net.minecraft.network.protocol.game.ServerboundSetTestBlockPacket;
import net.minecraft.network.protocol.game.ServerboundSignUpdatePacket;
import net.minecraft.network.protocol.game.ServerboundSpectatorActionPacket;
import net.minecraft.network.protocol.game.ServerboundSwingPacket;
import net.minecraft.network.protocol.game.ServerboundTeleportToEntityPacket;
import net.minecraft.network.protocol.game.ServerboundTestInstanceBlockActionPacket;
import net.minecraft.network.protocol.game.ServerboundUseItemOnPacket;
import net.minecraft.network.protocol.game.ServerboundUseItemPacket;
import net.minecraft.network.protocol.ping.ServerboundPingRequestPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.PositionMoveRotation;
import net.minecraft.world.entity.Relative;
import dev.pumpkin.shim.Unimplemented;

public class ServerGamePacketListenerImpl extends ServerCommonPacketListenerImpl implements ServerGamePacketListener, ServerPlayerConnection, TickablePacketListener, GameProtocols.Context {

    private int aboveGroundTickCount;

    public ServerGamePacketListenerImpl(MinecraftServer server, Connection connection, ServerPlayer player, CommonListenerCookie cookie) {
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.tick:()V");
    }

    public boolean isAcceptingMessages() {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.isAcceptingMessages:()Z");
    }

    public boolean shouldHandleMessage(Packet<?> packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.shouldHandleMessage:(Lnet/minecraft/network/protocol/Packet;)Z");
    }

    protected GameProfile playerProfile() {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.playerProfile:()Lcom/mojang/authlib/GameProfile;");
    }

    public void handlePlayerInput(ServerboundPlayerInputPacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.handlePlayerInput:(Lnet/minecraft/network/protocol/game/ServerboundPlayerInputPacket;)V");
    }

    public void handleMoveVehicle(ServerboundMoveVehiclePacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.handleMoveVehicle:(Lnet/minecraft/network/protocol/game/ServerboundMoveVehiclePacket;)V");
    }

    public void handleAcceptTeleportPacket(ServerboundAcceptTeleportationPacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.handleAcceptTeleportPacket:(Lnet/minecraft/network/protocol/game/ServerboundAcceptTeleportationPacket;)V");
    }

    public void handleAcceptPlayerLoad(ServerboundPlayerLoadedPacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.handleAcceptPlayerLoad:(Lnet/minecraft/network/protocol/game/ServerboundPlayerLoadedPacket;)V");
    }

    public void handleRecipeBookSeenRecipePacket(ServerboundRecipeBookSeenRecipePacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.handleRecipeBookSeenRecipePacket:(Lnet/minecraft/network/protocol/game/ServerboundRecipeBookSeenRecipePacket;)V");
    }

    public void handleBundleItemSelectedPacket(ServerboundSelectBundleItemPacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.handleBundleItemSelectedPacket:(Lnet/minecraft/network/protocol/game/ServerboundSelectBundleItemPacket;)V");
    }

    public void handleRecipeBookChangeSettingsPacket(ServerboundRecipeBookChangeSettingsPacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.handleRecipeBookChangeSettingsPacket:(Lnet/minecraft/network/protocol/game/ServerboundRecipeBookChangeSettingsPacket;)V");
    }

    public void handleSeenAdvancements(ServerboundSeenAdvancementsPacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.handleSeenAdvancements:(Lnet/minecraft/network/protocol/game/ServerboundSeenAdvancementsPacket;)V");
    }

    public void handleCustomCommandSuggestions(ServerboundCommandSuggestionPacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.handleCustomCommandSuggestions:(Lnet/minecraft/network/protocol/game/ServerboundCommandSuggestionPacket;)V");
    }

    public void handleSetCommandBlock(ServerboundSetCommandBlockPacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.handleSetCommandBlock:(Lnet/minecraft/network/protocol/game/ServerboundSetCommandBlockPacket;)V");
    }

    public void handleSetCommandMinecart(ServerboundSetCommandMinecartPacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.handleSetCommandMinecart:(Lnet/minecraft/network/protocol/game/ServerboundSetCommandMinecartPacket;)V");
    }

    public void handlePickItemFromBlock(ServerboundPickItemFromBlockPacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.handlePickItemFromBlock:(Lnet/minecraft/network/protocol/game/ServerboundPickItemFromBlockPacket;)V");
    }

    public void handlePickItemFromEntity(ServerboundPickItemFromEntityPacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.handlePickItemFromEntity:(Lnet/minecraft/network/protocol/game/ServerboundPickItemFromEntityPacket;)V");
    }

    public void handleRenameItem(ServerboundRenameItemPacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.handleRenameItem:(Lnet/minecraft/network/protocol/game/ServerboundRenameItemPacket;)V");
    }

    public void handleSetBeaconPacket(ServerboundSetBeaconPacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.handleSetBeaconPacket:(Lnet/minecraft/network/protocol/game/ServerboundSetBeaconPacket;)V");
    }

    public void handleSetGameRule(ServerboundSetGameRulePacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.handleSetGameRule:(Lnet/minecraft/network/protocol/game/ServerboundSetGameRulePacket;)V");
    }

    public void handleSetStructureBlock(ServerboundSetStructureBlockPacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.handleSetStructureBlock:(Lnet/minecraft/network/protocol/game/ServerboundSetStructureBlockPacket;)V");
    }

    public void handleSetTestBlock(ServerboundSetTestBlockPacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.handleSetTestBlock:(Lnet/minecraft/network/protocol/game/ServerboundSetTestBlockPacket;)V");
    }

    public void handleTestInstanceBlockAction(ServerboundTestInstanceBlockActionPacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.handleTestInstanceBlockAction:(Lnet/minecraft/network/protocol/game/ServerboundTestInstanceBlockActionPacket;)V");
    }

    public void handleSetJigsawBlock(ServerboundSetJigsawBlockPacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.handleSetJigsawBlock:(Lnet/minecraft/network/protocol/game/ServerboundSetJigsawBlockPacket;)V");
    }

    public void handleJigsawGenerate(ServerboundJigsawGeneratePacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.handleJigsawGenerate:(Lnet/minecraft/network/protocol/game/ServerboundJigsawGeneratePacket;)V");
    }

    public void handleSelectTrade(ServerboundSelectTradePacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.handleSelectTrade:(Lnet/minecraft/network/protocol/game/ServerboundSelectTradePacket;)V");
    }

    public void handleEditBook(ServerboundEditBookPacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.handleEditBook:(Lnet/minecraft/network/protocol/game/ServerboundEditBookPacket;)V");
    }

    public void handleEntityTagQuery(ServerboundEntityTagQueryPacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.handleEntityTagQuery:(Lnet/minecraft/network/protocol/game/ServerboundEntityTagQueryPacket;)V");
    }

    public void handleContainerSlotStateChanged(ServerboundContainerSlotStateChangedPacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.handleContainerSlotStateChanged:(Lnet/minecraft/network/protocol/game/ServerboundContainerSlotStateChangedPacket;)V");
    }

    public void handleBlockEntityTagQuery(ServerboundBlockEntityTagQueryPacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.handleBlockEntityTagQuery:(Lnet/minecraft/network/protocol/game/ServerboundBlockEntityTagQueryPacket;)V");
    }

    public void handleMovePlayer(ServerboundMovePlayerPacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.handleMovePlayer:(Lnet/minecraft/network/protocol/game/ServerboundMovePlayerPacket;)V");
    }

    public void teleport(double x, double y, double z, float yRot, float xRot) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.teleport:(DDDFF)V");
    }

    public void teleport(PositionMoveRotation destination, Set<Relative> relatives) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.teleport:(Lnet/minecraft/world/entity/PositionMoveRotation;Ljava/util/Set;)V");
    }

    public void handlePlayerAction(ServerboundPlayerActionPacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.handlePlayerAction:(Lnet/minecraft/network/protocol/game/ServerboundPlayerActionPacket;)V");
    }

    public void handleUseItemOn(ServerboundUseItemOnPacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.handleUseItemOn:(Lnet/minecraft/network/protocol/game/ServerboundUseItemOnPacket;)V");
    }

    public void handleUseItem(ServerboundUseItemPacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.handleUseItem:(Lnet/minecraft/network/protocol/game/ServerboundUseItemPacket;)V");
    }

    public void handleTeleportToEntityPacket(ServerboundTeleportToEntityPacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.handleTeleportToEntityPacket:(Lnet/minecraft/network/protocol/game/ServerboundTeleportToEntityPacket;)V");
    }

    public void handlePaddleBoat(ServerboundPaddleBoatPacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.handlePaddleBoat:(Lnet/minecraft/network/protocol/game/ServerboundPaddleBoatPacket;)V");
    }

    public void onDisconnect(DisconnectionDetails details) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.onDisconnect:(Lnet/minecraft/network/DisconnectionDetails;)V");
    }

    public void handleSetCarriedItem(ServerboundSetCarriedItemPacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.handleSetCarriedItem:(Lnet/minecraft/network/protocol/game/ServerboundSetCarriedItemPacket;)V");
    }

    public void handleChat(ServerboundChatPacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.handleChat:(Lnet/minecraft/network/protocol/game/ServerboundChatPacket;)V");
    }

    public void handleChatCommand(ServerboundChatCommandPacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.handleChatCommand:(Lnet/minecraft/network/protocol/game/ServerboundChatCommandPacket;)V");
    }

    public void handleSignedChatCommand(ServerboundChatCommandSignedPacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.handleSignedChatCommand:(Lnet/minecraft/network/protocol/game/ServerboundChatCommandSignedPacket;)V");
    }

    public void handleChatAck(ServerboundChatAckPacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.handleChatAck:(Lnet/minecraft/network/protocol/game/ServerboundChatAckPacket;)V");
    }

    public void handleAnimate(ServerboundSwingPacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.handleAnimate:(Lnet/minecraft/network/protocol/game/ServerboundSwingPacket;)V");
    }

    public void handlePlayerCommand(ServerboundPlayerCommandPacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.handlePlayerCommand:(Lnet/minecraft/network/protocol/game/ServerboundPlayerCommandPacket;)V");
    }

    public void handlePingRequest(ServerboundPingRequestPacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.handlePingRequest:(Lnet/minecraft/network/protocol/ping/ServerboundPingRequestPacket;)V");
    }

    public void handleAttack(ServerboundAttackPacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.handleAttack:(Lnet/minecraft/network/protocol/game/ServerboundAttackPacket;)V");
    }

    public void handleInteract(ServerboundInteractPacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.handleInteract:(Lnet/minecraft/network/protocol/game/ServerboundInteractPacket;)V");
    }

    public void handleSpectatorAction(ServerboundSpectatorActionPacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.handleSpectatorAction:(Lnet/minecraft/network/protocol/game/ServerboundSpectatorActionPacket;)V");
    }

    public void handleClientCommand(ServerboundClientCommandPacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.handleClientCommand:(Lnet/minecraft/network/protocol/game/ServerboundClientCommandPacket;)V");
    }

    public void handleContainerClose(ServerboundContainerClosePacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.handleContainerClose:(Lnet/minecraft/network/protocol/game/ServerboundContainerClosePacket;)V");
    }

    public void handleContainerClick(ServerboundContainerClickPacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.handleContainerClick:(Lnet/minecraft/network/protocol/game/ServerboundContainerClickPacket;)V");
    }

    public void handlePlaceRecipe(ServerboundPlaceRecipePacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.handlePlaceRecipe:(Lnet/minecraft/network/protocol/game/ServerboundPlaceRecipePacket;)V");
    }

    public void handleContainerButtonClick(ServerboundContainerButtonClickPacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.handleContainerButtonClick:(Lnet/minecraft/network/protocol/game/ServerboundContainerButtonClickPacket;)V");
    }

    public void handleSetCreativeModeSlot(ServerboundSetCreativeModeSlotPacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.handleSetCreativeModeSlot:(Lnet/minecraft/network/protocol/game/ServerboundSetCreativeModeSlotPacket;)V");
    }

    public void handleSignUpdate(ServerboundSignUpdatePacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.handleSignUpdate:(Lnet/minecraft/network/protocol/game/ServerboundSignUpdatePacket;)V");
    }

    public void handlePlayerAbilities(ServerboundPlayerAbilitiesPacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.handlePlayerAbilities:(Lnet/minecraft/network/protocol/game/ServerboundPlayerAbilitiesPacket;)V");
    }

    public void handleClientInformation(ServerboundClientInformationPacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.handleClientInformation:(Lnet/minecraft/network/protocol/common/ServerboundClientInformationPacket;)V");
    }

    public void handleChangeDifficulty(ServerboundChangeDifficultyPacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.handleChangeDifficulty:(Lnet/minecraft/network/protocol/game/ServerboundChangeDifficultyPacket;)V");
    }

    public void handleChangeGameMode(ServerboundChangeGameModePacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.handleChangeGameMode:(Lnet/minecraft/network/protocol/game/ServerboundChangeGameModePacket;)V");
    }

    public void handleLockDifficulty(ServerboundLockDifficultyPacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.handleLockDifficulty:(Lnet/minecraft/network/protocol/game/ServerboundLockDifficultyPacket;)V");
    }

    public void handleChatSessionUpdate(ServerboundChatSessionUpdatePacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.handleChatSessionUpdate:(Lnet/minecraft/network/protocol/game/ServerboundChatSessionUpdatePacket;)V");
    }

    public void handleConfigurationAcknowledged(ServerboundConfigurationAcknowledgedPacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.handleConfigurationAcknowledged:(Lnet/minecraft/network/protocol/game/ServerboundConfigurationAcknowledgedPacket;)V");
    }

    public void handleChunkBatchReceived(ServerboundChunkBatchReceivedPacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.handleChunkBatchReceived:(Lnet/minecraft/network/protocol/game/ServerboundChunkBatchReceivedPacket;)V");
    }

    public void handleDebugSubscriptionRequest(ServerboundDebugSubscriptionRequestPacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.handleDebugSubscriptionRequest:(Lnet/minecraft/network/protocol/game/ServerboundDebugSubscriptionRequestPacket;)V");
    }

    public void handleCustomPayload(ServerboundCustomPayloadPacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.handleCustomPayload:(Lnet/minecraft/network/protocol/common/ServerboundCustomPayloadPacket;)V");
    }

    public void handleClientTickEnd(ServerboundClientTickEndPacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.handleClientTickEnd:(Lnet/minecraft/network/protocol/game/ServerboundClientTickEndPacket;)V");
    }

    public boolean hasInfiniteMaterials() {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.hasInfiniteMaterials:()Z");
    }

    public ServerPlayer getPlayer() {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerGamePacketListenerImpl.getPlayer:()Lnet/minecraft/server/level/ServerPlayer;");
    }

    public ServerGamePacketListenerImpl() {
    }
}
