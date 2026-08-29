package net.minecraft.client.multiplayer;

import java.util.Set;
import net.minecraft.client.ClientClockManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.dialog.DialogConnectionAccess;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.Connection;
import net.minecraft.network.TickablePacketListener;
import net.minecraft.network.protocol.common.ClientboundUpdateTagsPacket;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.protocol.game.ClientboundAnimatePacket;
import net.minecraft.network.protocol.game.ClientboundAwardStatsPacket;
import net.minecraft.network.protocol.game.ClientboundBlockChangedAckPacket;
import net.minecraft.network.protocol.game.ClientboundBlockDestructionPacket;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.network.protocol.game.ClientboundBlockEventPacket;
import net.minecraft.network.protocol.game.ClientboundBlockUpdatePacket;
import net.minecraft.network.protocol.game.ClientboundBossEventPacket;
import net.minecraft.network.protocol.game.ClientboundBundlePacket;
import net.minecraft.network.protocol.game.ClientboundChangeDifficultyPacket;
import net.minecraft.network.protocol.game.ClientboundChunkBatchFinishedPacket;
import net.minecraft.network.protocol.game.ClientboundChunkBatchStartPacket;
import net.minecraft.network.protocol.game.ClientboundChunksBiomesPacket;
import net.minecraft.network.protocol.game.ClientboundClearTitlesPacket;
import net.minecraft.network.protocol.game.ClientboundCommandSuggestionsPacket;
import net.minecraft.network.protocol.game.ClientboundCommandsPacket;
import net.minecraft.network.protocol.game.ClientboundContainerClosePacket;
import net.minecraft.network.protocol.game.ClientboundContainerSetContentPacket;
import net.minecraft.network.protocol.game.ClientboundContainerSetDataPacket;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.network.protocol.game.ClientboundCooldownPacket;
import net.minecraft.network.protocol.game.ClientboundCustomChatCompletionsPacket;
import net.minecraft.network.protocol.game.ClientboundDamageEventPacket;
import net.minecraft.network.protocol.game.ClientboundDebugBlockValuePacket;
import net.minecraft.network.protocol.game.ClientboundDebugChunkValuePacket;
import net.minecraft.network.protocol.game.ClientboundDebugEntityValuePacket;
import net.minecraft.network.protocol.game.ClientboundDebugEventPacket;
import net.minecraft.network.protocol.game.ClientboundDebugSamplePacket;
import net.minecraft.network.protocol.game.ClientboundDeleteChatPacket;
import net.minecraft.network.protocol.game.ClientboundDisguisedChatPacket;
import net.minecraft.network.protocol.game.ClientboundEntityEventPacket;
import net.minecraft.network.protocol.game.ClientboundEntityPositionSyncPacket;
import net.minecraft.network.protocol.game.ClientboundExplodePacket;
import net.minecraft.network.protocol.game.ClientboundForgetLevelChunkPacket;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.network.protocol.game.ClientboundGameRuleValuesPacket;
import net.minecraft.network.protocol.game.ClientboundGameTestHighlightPosPacket;
import net.minecraft.network.protocol.game.ClientboundHurtAnimationPacket;
import net.minecraft.network.protocol.game.ClientboundInitializeBorderPacket;
import net.minecraft.network.protocol.game.ClientboundLevelChunkWithLightPacket;
import net.minecraft.network.protocol.game.ClientboundLevelEventPacket;
import net.minecraft.network.protocol.game.ClientboundLevelParticlesPacket;
import net.minecraft.network.protocol.game.ClientboundLightUpdatePacket;
import net.minecraft.network.protocol.game.ClientboundLoginPacket;
import net.minecraft.network.protocol.game.ClientboundLowDiskSpaceWarningPacket;
import net.minecraft.network.protocol.game.ClientboundMapItemDataPacket;
import net.minecraft.network.protocol.game.ClientboundMerchantOffersPacket;
import net.minecraft.network.protocol.game.ClientboundMountScreenOpenPacket;
import net.minecraft.network.protocol.game.ClientboundMoveEntityPacket;
import net.minecraft.network.protocol.game.ClientboundMoveMinecartPacket;
import net.minecraft.network.protocol.game.ClientboundMoveVehiclePacket;
import net.minecraft.network.protocol.game.ClientboundOpenBookPacket;
import net.minecraft.network.protocol.game.ClientboundOpenScreenPacket;
import net.minecraft.network.protocol.game.ClientboundOpenSignEditorPacket;
import net.minecraft.network.protocol.game.ClientboundPlaceGhostRecipePacket;
import net.minecraft.network.protocol.game.ClientboundPlayerAbilitiesPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerChatPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerCombatEndPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerCombatEnterPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerCombatKillPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoRemovePacket;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket;
import net.minecraft.network.protocol.game.ClientboundPlayerLookAtPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerPositionPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerRotationPacket;
import net.minecraft.network.protocol.game.ClientboundProjectilePowerPacket;
import net.minecraft.network.protocol.game.ClientboundRecipeBookAddPacket;
import net.minecraft.network.protocol.game.ClientboundRecipeBookRemovePacket;
import net.minecraft.network.protocol.game.ClientboundRecipeBookSettingsPacket;
import net.minecraft.network.protocol.game.ClientboundRemoveEntitiesPacket;
import net.minecraft.network.protocol.game.ClientboundRemoveMobEffectPacket;
import net.minecraft.network.protocol.game.ClientboundResetScorePacket;
import net.minecraft.network.protocol.game.ClientboundRespawnPacket;
import net.minecraft.network.protocol.game.ClientboundRotateHeadPacket;
import net.minecraft.network.protocol.game.ClientboundSectionBlocksUpdatePacket;
import net.minecraft.network.protocol.game.ClientboundSelectAdvancementsTabPacket;
import net.minecraft.network.protocol.game.ClientboundServerDataPacket;
import net.minecraft.network.protocol.game.ClientboundSetActionBarTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetBorderCenterPacket;
import net.minecraft.network.protocol.game.ClientboundSetBorderLerpSizePacket;
import net.minecraft.network.protocol.game.ClientboundSetBorderSizePacket;
import net.minecraft.network.protocol.game.ClientboundSetBorderWarningDelayPacket;
import net.minecraft.network.protocol.game.ClientboundSetBorderWarningDistancePacket;
import net.minecraft.network.protocol.game.ClientboundSetCameraPacket;
import net.minecraft.network.protocol.game.ClientboundSetChunkCacheCenterPacket;
import net.minecraft.network.protocol.game.ClientboundSetChunkCacheRadiusPacket;
import net.minecraft.network.protocol.game.ClientboundSetCursorItemPacket;
import net.minecraft.network.protocol.game.ClientboundSetDefaultSpawnPositionPacket;
import net.minecraft.network.protocol.game.ClientboundSetDisplayObjectivePacket;
import net.minecraft.network.protocol.game.ClientboundSetEntityDataPacket;
import net.minecraft.network.protocol.game.ClientboundSetEntityLinkPacket;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.network.protocol.game.ClientboundSetEquipmentPacket;
import net.minecraft.network.protocol.game.ClientboundSetExperiencePacket;
import net.minecraft.network.protocol.game.ClientboundSetHealthPacket;
import net.minecraft.network.protocol.game.ClientboundSetHeldSlotPacket;
import net.minecraft.network.protocol.game.ClientboundSetObjectivePacket;
import net.minecraft.network.protocol.game.ClientboundSetPassengersPacket;
import net.minecraft.network.protocol.game.ClientboundSetPlayerInventoryPacket;
import net.minecraft.network.protocol.game.ClientboundSetPlayerTeamPacket;
import net.minecraft.network.protocol.game.ClientboundSetScorePacket;
import net.minecraft.network.protocol.game.ClientboundSetSimulationDistancePacket;
import net.minecraft.network.protocol.game.ClientboundSetSubtitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTimePacket;
import net.minecraft.network.protocol.game.ClientboundSetTitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitlesAnimationPacket;
import net.minecraft.network.protocol.game.ClientboundSoundEntityPacket;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.network.protocol.game.ClientboundStartConfigurationPacket;
import net.minecraft.network.protocol.game.ClientboundStopSoundPacket;
import net.minecraft.network.protocol.game.ClientboundSystemChatPacket;
import net.minecraft.network.protocol.game.ClientboundTabListPacket;
import net.minecraft.network.protocol.game.ClientboundTagQueryPacket;
import net.minecraft.network.protocol.game.ClientboundTakeItemEntityPacket;
import net.minecraft.network.protocol.game.ClientboundTeleportEntityPacket;
import net.minecraft.network.protocol.game.ClientboundTestInstanceBlockStatus;
import net.minecraft.network.protocol.game.ClientboundTickingStatePacket;
import net.minecraft.network.protocol.game.ClientboundTickingStepPacket;
import net.minecraft.network.protocol.game.ClientboundTrackedWaypointPacket;
import net.minecraft.network.protocol.game.ClientboundUpdateAdvancementsPacket;
import net.minecraft.network.protocol.game.ClientboundUpdateAttributesPacket;
import net.minecraft.network.protocol.game.ClientboundUpdateMobEffectPacket;
import net.minecraft.network.protocol.game.ClientboundUpdateRecipesPacket;
import net.minecraft.network.protocol.ping.ClientboundPongResponsePacket;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.FuelValues;
import dev.pumpkin.shim.Unimplemented;

public class ClientPacketListener extends ClientCommonPacketListenerImpl implements ClientGamePacketListener, TickablePacketListener {

    public ClientPacketListener(Minecraft minecraft, Connection connection, CommonListenerCookie cookie) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.<init>:(Lnet/minecraft/client/Minecraft;Lnet/minecraft/network/Connection;Lnet/minecraft/client/multiplayer/CommonListenerCookie;)V");
    }

    public void close() {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.close:()V");
    }

    public void handleLogin(ClientboundLoginPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleLogin:(Lnet/minecraft/network/protocol/game/ClientboundLoginPacket;)V");
    }

    public void handleAddEntity(ClientboundAddEntityPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleAddEntity:(Lnet/minecraft/network/protocol/game/ClientboundAddEntityPacket;)V");
    }

    public void handleSetEntityMotion(ClientboundSetEntityMotionPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleSetEntityMotion:(Lnet/minecraft/network/protocol/game/ClientboundSetEntityMotionPacket;)V");
    }

    public void handleSetEntityData(ClientboundSetEntityDataPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleSetEntityData:(Lnet/minecraft/network/protocol/game/ClientboundSetEntityDataPacket;)V");
    }

    public void handleEntityPositionSync(ClientboundEntityPositionSyncPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleEntityPositionSync:(Lnet/minecraft/network/protocol/game/ClientboundEntityPositionSyncPacket;)V");
    }

    public void handleTeleportEntity(ClientboundTeleportEntityPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleTeleportEntity:(Lnet/minecraft/network/protocol/game/ClientboundTeleportEntityPacket;)V");
    }

    public void handleTickingState(ClientboundTickingStatePacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleTickingState:(Lnet/minecraft/network/protocol/game/ClientboundTickingStatePacket;)V");
    }

    public void handleTickingStep(ClientboundTickingStepPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleTickingStep:(Lnet/minecraft/network/protocol/game/ClientboundTickingStepPacket;)V");
    }

    public void handleSetHeldSlot(ClientboundSetHeldSlotPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleSetHeldSlot:(Lnet/minecraft/network/protocol/game/ClientboundSetHeldSlotPacket;)V");
    }

    public void handleMoveEntity(ClientboundMoveEntityPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleMoveEntity:(Lnet/minecraft/network/protocol/game/ClientboundMoveEntityPacket;)V");
    }

    public void handleMinecartAlongTrack(ClientboundMoveMinecartPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleMinecartAlongTrack:(Lnet/minecraft/network/protocol/game/ClientboundMoveMinecartPacket;)V");
    }

    public void handleRotateMob(ClientboundRotateHeadPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleRotateMob:(Lnet/minecraft/network/protocol/game/ClientboundRotateHeadPacket;)V");
    }

    public void handleRemoveEntities(ClientboundRemoveEntitiesPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleRemoveEntities:(Lnet/minecraft/network/protocol/game/ClientboundRemoveEntitiesPacket;)V");
    }

    public void handleMovePlayer(ClientboundPlayerPositionPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleMovePlayer:(Lnet/minecraft/network/protocol/game/ClientboundPlayerPositionPacket;)V");
    }

    public void handleRotatePlayer(ClientboundPlayerRotationPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleRotatePlayer:(Lnet/minecraft/network/protocol/game/ClientboundPlayerRotationPacket;)V");
    }

    public void handleChunkBlocksUpdate(ClientboundSectionBlocksUpdatePacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleChunkBlocksUpdate:(Lnet/minecraft/network/protocol/game/ClientboundSectionBlocksUpdatePacket;)V");
    }

    public void handleLevelChunkWithLight(ClientboundLevelChunkWithLightPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleLevelChunkWithLight:(Lnet/minecraft/network/protocol/game/ClientboundLevelChunkWithLightPacket;)V");
    }

    public void handleChunksBiomes(ClientboundChunksBiomesPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleChunksBiomes:(Lnet/minecraft/network/protocol/game/ClientboundChunksBiomesPacket;)V");
    }

    public void handleForgetLevelChunk(ClientboundForgetLevelChunkPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleForgetLevelChunk:(Lnet/minecraft/network/protocol/game/ClientboundForgetLevelChunkPacket;)V");
    }

    public void handleBlockUpdate(ClientboundBlockUpdatePacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleBlockUpdate:(Lnet/minecraft/network/protocol/game/ClientboundBlockUpdatePacket;)V");
    }

    public void handleConfigurationStart(ClientboundStartConfigurationPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleConfigurationStart:(Lnet/minecraft/network/protocol/game/ClientboundStartConfigurationPacket;)V");
    }

    public void handleTakeItemEntity(ClientboundTakeItemEntityPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleTakeItemEntity:(Lnet/minecraft/network/protocol/game/ClientboundTakeItemEntityPacket;)V");
    }

    public void handleSystemChat(ClientboundSystemChatPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleSystemChat:(Lnet/minecraft/network/protocol/game/ClientboundSystemChatPacket;)V");
    }

    public void handlePlayerChat(ClientboundPlayerChatPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handlePlayerChat:(Lnet/minecraft/network/protocol/game/ClientboundPlayerChatPacket;)V");
    }

    public void handleDisguisedChat(ClientboundDisguisedChatPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleDisguisedChat:(Lnet/minecraft/network/protocol/game/ClientboundDisguisedChatPacket;)V");
    }

    public void handleDeleteChat(ClientboundDeleteChatPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleDeleteChat:(Lnet/minecraft/network/protocol/game/ClientboundDeleteChatPacket;)V");
    }

    public void handleAnimate(ClientboundAnimatePacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleAnimate:(Lnet/minecraft/network/protocol/game/ClientboundAnimatePacket;)V");
    }

    public void handleHurtAnimation(ClientboundHurtAnimationPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleHurtAnimation:(Lnet/minecraft/network/protocol/game/ClientboundHurtAnimationPacket;)V");
    }

    public void handleSetTime(ClientboundSetTimePacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleSetTime:(Lnet/minecraft/network/protocol/game/ClientboundSetTimePacket;)V");
    }

    public void handleSetSpawn(ClientboundSetDefaultSpawnPositionPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleSetSpawn:(Lnet/minecraft/network/protocol/game/ClientboundSetDefaultSpawnPositionPacket;)V");
    }

    public void handleSetEntityPassengersPacket(ClientboundSetPassengersPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleSetEntityPassengersPacket:(Lnet/minecraft/network/protocol/game/ClientboundSetPassengersPacket;)V");
    }

    public void handleEntityLinkPacket(ClientboundSetEntityLinkPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleEntityLinkPacket:(Lnet/minecraft/network/protocol/game/ClientboundSetEntityLinkPacket;)V");
    }

    public void handleEntityEvent(ClientboundEntityEventPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleEntityEvent:(Lnet/minecraft/network/protocol/game/ClientboundEntityEventPacket;)V");
    }

    public void handleDamageEvent(ClientboundDamageEventPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleDamageEvent:(Lnet/minecraft/network/protocol/game/ClientboundDamageEventPacket;)V");
    }

    public void handleSetHealth(ClientboundSetHealthPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleSetHealth:(Lnet/minecraft/network/protocol/game/ClientboundSetHealthPacket;)V");
    }

    public void handleSetExperience(ClientboundSetExperiencePacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleSetExperience:(Lnet/minecraft/network/protocol/game/ClientboundSetExperiencePacket;)V");
    }

    public void handleRespawn(ClientboundRespawnPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleRespawn:(Lnet/minecraft/network/protocol/game/ClientboundRespawnPacket;)V");
    }

    public void handleExplosion(ClientboundExplodePacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleExplosion:(Lnet/minecraft/network/protocol/game/ClientboundExplodePacket;)V");
    }

    public void handleMountScreenOpen(ClientboundMountScreenOpenPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleMountScreenOpen:(Lnet/minecraft/network/protocol/game/ClientboundMountScreenOpenPacket;)V");
    }

    public void handleOpenScreen(ClientboundOpenScreenPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleOpenScreen:(Lnet/minecraft/network/protocol/game/ClientboundOpenScreenPacket;)V");
    }

    public void handleContainerSetSlot(ClientboundContainerSetSlotPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleContainerSetSlot:(Lnet/minecraft/network/protocol/game/ClientboundContainerSetSlotPacket;)V");
    }

    public void handleSetCursorItem(ClientboundSetCursorItemPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleSetCursorItem:(Lnet/minecraft/network/protocol/game/ClientboundSetCursorItemPacket;)V");
    }

    public void handleSetPlayerInventory(ClientboundSetPlayerInventoryPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleSetPlayerInventory:(Lnet/minecraft/network/protocol/game/ClientboundSetPlayerInventoryPacket;)V");
    }

    public void handleContainerContent(ClientboundContainerSetContentPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleContainerContent:(Lnet/minecraft/network/protocol/game/ClientboundContainerSetContentPacket;)V");
    }

    public void handleOpenSignEditor(ClientboundOpenSignEditorPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleOpenSignEditor:(Lnet/minecraft/network/protocol/game/ClientboundOpenSignEditorPacket;)V");
    }

    public void handleBlockEntityData(ClientboundBlockEntityDataPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleBlockEntityData:(Lnet/minecraft/network/protocol/game/ClientboundBlockEntityDataPacket;)V");
    }

    public void handleContainerSetData(ClientboundContainerSetDataPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleContainerSetData:(Lnet/minecraft/network/protocol/game/ClientboundContainerSetDataPacket;)V");
    }

    public void handleSetEquipment(ClientboundSetEquipmentPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleSetEquipment:(Lnet/minecraft/network/protocol/game/ClientboundSetEquipmentPacket;)V");
    }

    public void handleContainerClose(ClientboundContainerClosePacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleContainerClose:(Lnet/minecraft/network/protocol/game/ClientboundContainerClosePacket;)V");
    }

    public void handleBlockEvent(ClientboundBlockEventPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleBlockEvent:(Lnet/minecraft/network/protocol/game/ClientboundBlockEventPacket;)V");
    }

    public void handleBlockDestruction(ClientboundBlockDestructionPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleBlockDestruction:(Lnet/minecraft/network/protocol/game/ClientboundBlockDestructionPacket;)V");
    }

    public void handleGameEvent(ClientboundGameEventPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleGameEvent:(Lnet/minecraft/network/protocol/game/ClientboundGameEventPacket;)V");
    }

    public void handleMapItemData(ClientboundMapItemDataPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleMapItemData:(Lnet/minecraft/network/protocol/game/ClientboundMapItemDataPacket;)V");
    }

    public void handleLevelEvent(ClientboundLevelEventPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleLevelEvent:(Lnet/minecraft/network/protocol/game/ClientboundLevelEventPacket;)V");
    }

    public void handleUpdateAdvancementsPacket(ClientboundUpdateAdvancementsPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleUpdateAdvancementsPacket:(Lnet/minecraft/network/protocol/game/ClientboundUpdateAdvancementsPacket;)V");
    }

    public void handleSelectAdvancementsTab(ClientboundSelectAdvancementsTabPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleSelectAdvancementsTab:(Lnet/minecraft/network/protocol/game/ClientboundSelectAdvancementsTabPacket;)V");
    }

    public void handleCommands(ClientboundCommandsPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleCommands:(Lnet/minecraft/network/protocol/game/ClientboundCommandsPacket;)V");
    }

    public void handleStopSoundEvent(ClientboundStopSoundPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleStopSoundEvent:(Lnet/minecraft/network/protocol/game/ClientboundStopSoundPacket;)V");
    }

    public void handleCommandSuggestions(ClientboundCommandSuggestionsPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleCommandSuggestions:(Lnet/minecraft/network/protocol/game/ClientboundCommandSuggestionsPacket;)V");
    }

    public void handleUpdateRecipes(ClientboundUpdateRecipesPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleUpdateRecipes:(Lnet/minecraft/network/protocol/game/ClientboundUpdateRecipesPacket;)V");
    }

    public void handleLookAt(ClientboundPlayerLookAtPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleLookAt:(Lnet/minecraft/network/protocol/game/ClientboundPlayerLookAtPacket;)V");
    }

    public void handleTagQueryPacket(ClientboundTagQueryPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleTagQueryPacket:(Lnet/minecraft/network/protocol/game/ClientboundTagQueryPacket;)V");
    }

    public void handleAwardStats(ClientboundAwardStatsPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleAwardStats:(Lnet/minecraft/network/protocol/game/ClientboundAwardStatsPacket;)V");
    }

    public void handleRecipeBookAdd(ClientboundRecipeBookAddPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleRecipeBookAdd:(Lnet/minecraft/network/protocol/game/ClientboundRecipeBookAddPacket;)V");
    }

    public void handleRecipeBookRemove(ClientboundRecipeBookRemovePacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleRecipeBookRemove:(Lnet/minecraft/network/protocol/game/ClientboundRecipeBookRemovePacket;)V");
    }

    public void handleRecipeBookSettings(ClientboundRecipeBookSettingsPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleRecipeBookSettings:(Lnet/minecraft/network/protocol/game/ClientboundRecipeBookSettingsPacket;)V");
    }

    public void handleUpdateMobEffect(ClientboundUpdateMobEffectPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleUpdateMobEffect:(Lnet/minecraft/network/protocol/game/ClientboundUpdateMobEffectPacket;)V");
    }

    public void handleUpdateTags(ClientboundUpdateTagsPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleUpdateTags:(Lnet/minecraft/network/protocol/common/ClientboundUpdateTagsPacket;)V");
    }

    public void handlePlayerCombatEnd(ClientboundPlayerCombatEndPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handlePlayerCombatEnd:(Lnet/minecraft/network/protocol/game/ClientboundPlayerCombatEndPacket;)V");
    }

    public void handlePlayerCombatEnter(ClientboundPlayerCombatEnterPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handlePlayerCombatEnter:(Lnet/minecraft/network/protocol/game/ClientboundPlayerCombatEnterPacket;)V");
    }

    public void handlePlayerCombatKill(ClientboundPlayerCombatKillPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handlePlayerCombatKill:(Lnet/minecraft/network/protocol/game/ClientboundPlayerCombatKillPacket;)V");
    }

    public void handleChangeDifficulty(ClientboundChangeDifficultyPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleChangeDifficulty:(Lnet/minecraft/network/protocol/game/ClientboundChangeDifficultyPacket;)V");
    }

    public void handleSetCamera(ClientboundSetCameraPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleSetCamera:(Lnet/minecraft/network/protocol/game/ClientboundSetCameraPacket;)V");
    }

    public void handleInitializeBorder(ClientboundInitializeBorderPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleInitializeBorder:(Lnet/minecraft/network/protocol/game/ClientboundInitializeBorderPacket;)V");
    }

    public void handleSetBorderCenter(ClientboundSetBorderCenterPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleSetBorderCenter:(Lnet/minecraft/network/protocol/game/ClientboundSetBorderCenterPacket;)V");
    }

    public void handleSetBorderLerpSize(ClientboundSetBorderLerpSizePacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleSetBorderLerpSize:(Lnet/minecraft/network/protocol/game/ClientboundSetBorderLerpSizePacket;)V");
    }

    public void handleSetBorderSize(ClientboundSetBorderSizePacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleSetBorderSize:(Lnet/minecraft/network/protocol/game/ClientboundSetBorderSizePacket;)V");
    }

    public void handleSetBorderWarningDistance(ClientboundSetBorderWarningDistancePacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleSetBorderWarningDistance:(Lnet/minecraft/network/protocol/game/ClientboundSetBorderWarningDistancePacket;)V");
    }

    public void handleSetBorderWarningDelay(ClientboundSetBorderWarningDelayPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleSetBorderWarningDelay:(Lnet/minecraft/network/protocol/game/ClientboundSetBorderWarningDelayPacket;)V");
    }

    public void handleTitlesClear(ClientboundClearTitlesPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleTitlesClear:(Lnet/minecraft/network/protocol/game/ClientboundClearTitlesPacket;)V");
    }

    public void handleServerData(ClientboundServerDataPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleServerData:(Lnet/minecraft/network/protocol/game/ClientboundServerDataPacket;)V");
    }

    public void handleCustomChatCompletions(ClientboundCustomChatCompletionsPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleCustomChatCompletions:(Lnet/minecraft/network/protocol/game/ClientboundCustomChatCompletionsPacket;)V");
    }

    public void setActionBarText(ClientboundSetActionBarTextPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.setActionBarText:(Lnet/minecraft/network/protocol/game/ClientboundSetActionBarTextPacket;)V");
    }

    public void setTitleText(ClientboundSetTitleTextPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.setTitleText:(Lnet/minecraft/network/protocol/game/ClientboundSetTitleTextPacket;)V");
    }

    public void setSubtitleText(ClientboundSetSubtitleTextPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.setSubtitleText:(Lnet/minecraft/network/protocol/game/ClientboundSetSubtitleTextPacket;)V");
    }

    public void setTitlesAnimation(ClientboundSetTitlesAnimationPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.setTitlesAnimation:(Lnet/minecraft/network/protocol/game/ClientboundSetTitlesAnimationPacket;)V");
    }

    public void handleTabListCustomisation(ClientboundTabListPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleTabListCustomisation:(Lnet/minecraft/network/protocol/game/ClientboundTabListPacket;)V");
    }

    public void handleRemoveMobEffect(ClientboundRemoveMobEffectPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleRemoveMobEffect:(Lnet/minecraft/network/protocol/game/ClientboundRemoveMobEffectPacket;)V");
    }

    public void handlePlayerInfoRemove(ClientboundPlayerInfoRemovePacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handlePlayerInfoRemove:(Lnet/minecraft/network/protocol/game/ClientboundPlayerInfoRemovePacket;)V");
    }

    public void handlePlayerInfoUpdate(ClientboundPlayerInfoUpdatePacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handlePlayerInfoUpdate:(Lnet/minecraft/network/protocol/game/ClientboundPlayerInfoUpdatePacket;)V");
    }

    public void handlePlayerAbilities(ClientboundPlayerAbilitiesPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handlePlayerAbilities:(Lnet/minecraft/network/protocol/game/ClientboundPlayerAbilitiesPacket;)V");
    }

    public void handleGameRuleValues(ClientboundGameRuleValuesPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleGameRuleValues:(Lnet/minecraft/network/protocol/game/ClientboundGameRuleValuesPacket;)V");
    }

    public void handleSoundEvent(ClientboundSoundPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleSoundEvent:(Lnet/minecraft/network/protocol/game/ClientboundSoundPacket;)V");
    }

    public void handleSoundEntityEvent(ClientboundSoundEntityPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleSoundEntityEvent:(Lnet/minecraft/network/protocol/game/ClientboundSoundEntityPacket;)V");
    }

    public void handleBossUpdate(ClientboundBossEventPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleBossUpdate:(Lnet/minecraft/network/protocol/game/ClientboundBossEventPacket;)V");
    }

    public void handleItemCooldown(ClientboundCooldownPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleItemCooldown:(Lnet/minecraft/network/protocol/game/ClientboundCooldownPacket;)V");
    }

    public void handleMoveVehicle(ClientboundMoveVehiclePacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleMoveVehicle:(Lnet/minecraft/network/protocol/game/ClientboundMoveVehiclePacket;)V");
    }

    public void handleOpenBook(ClientboundOpenBookPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleOpenBook:(Lnet/minecraft/network/protocol/game/ClientboundOpenBookPacket;)V");
    }

    public void handleCustomPayload(CustomPacketPayload payload) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleCustomPayload:(Lnet/minecraft/network/protocol/common/custom/CustomPacketPayload;)V");
    }

    public void handleAddObjective(ClientboundSetObjectivePacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleAddObjective:(Lnet/minecraft/network/protocol/game/ClientboundSetObjectivePacket;)V");
    }

    public void handleSetScore(ClientboundSetScorePacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleSetScore:(Lnet/minecraft/network/protocol/game/ClientboundSetScorePacket;)V");
    }

    public void handleResetScore(ClientboundResetScorePacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleResetScore:(Lnet/minecraft/network/protocol/game/ClientboundResetScorePacket;)V");
    }

    public void handleSetDisplayObjective(ClientboundSetDisplayObjectivePacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleSetDisplayObjective:(Lnet/minecraft/network/protocol/game/ClientboundSetDisplayObjectivePacket;)V");
    }

    public void handleSetPlayerTeamPacket(ClientboundSetPlayerTeamPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleSetPlayerTeamPacket:(Lnet/minecraft/network/protocol/game/ClientboundSetPlayerTeamPacket;)V");
    }

    public void handleParticleEvent(ClientboundLevelParticlesPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleParticleEvent:(Lnet/minecraft/network/protocol/game/ClientboundLevelParticlesPacket;)V");
    }

    public void handleUpdateAttributes(ClientboundUpdateAttributesPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleUpdateAttributes:(Lnet/minecraft/network/protocol/game/ClientboundUpdateAttributesPacket;)V");
    }

    public void handlePlaceRecipe(ClientboundPlaceGhostRecipePacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handlePlaceRecipe:(Lnet/minecraft/network/protocol/game/ClientboundPlaceGhostRecipePacket;)V");
    }

    public void handleLightUpdatePacket(ClientboundLightUpdatePacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleLightUpdatePacket:(Lnet/minecraft/network/protocol/game/ClientboundLightUpdatePacket;)V");
    }

    public void handleMerchantOffers(ClientboundMerchantOffersPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleMerchantOffers:(Lnet/minecraft/network/protocol/game/ClientboundMerchantOffersPacket;)V");
    }

    public void handleSetChunkCacheRadius(ClientboundSetChunkCacheRadiusPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleSetChunkCacheRadius:(Lnet/minecraft/network/protocol/game/ClientboundSetChunkCacheRadiusPacket;)V");
    }

    public void handleSetSimulationDistance(ClientboundSetSimulationDistancePacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleSetSimulationDistance:(Lnet/minecraft/network/protocol/game/ClientboundSetSimulationDistancePacket;)V");
    }

    public void handleSetChunkCacheCenter(ClientboundSetChunkCacheCenterPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleSetChunkCacheCenter:(Lnet/minecraft/network/protocol/game/ClientboundSetChunkCacheCenterPacket;)V");
    }

    public void handleBlockChangedAck(ClientboundBlockChangedAckPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleBlockChangedAck:(Lnet/minecraft/network/protocol/game/ClientboundBlockChangedAckPacket;)V");
    }

    public void handleBundlePacket(ClientboundBundlePacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleBundlePacket:(Lnet/minecraft/network/protocol/game/ClientboundBundlePacket;)V");
    }

    public void handleProjectilePowerPacket(ClientboundProjectilePowerPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleProjectilePowerPacket:(Lnet/minecraft/network/protocol/game/ClientboundProjectilePowerPacket;)V");
    }

    public void handleChunkBatchStart(ClientboundChunkBatchStartPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleChunkBatchStart:(Lnet/minecraft/network/protocol/game/ClientboundChunkBatchStartPacket;)V");
    }

    public void handleChunkBatchFinished(ClientboundChunkBatchFinishedPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleChunkBatchFinished:(Lnet/minecraft/network/protocol/game/ClientboundChunkBatchFinishedPacket;)V");
    }

    public void handleDebugSample(ClientboundDebugSamplePacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleDebugSample:(Lnet/minecraft/network/protocol/game/ClientboundDebugSamplePacket;)V");
    }

    public void handlePongResponse(ClientboundPongResponsePacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handlePongResponse:(Lnet/minecraft/network/protocol/ping/ClientboundPongResponsePacket;)V");
    }

    public void handleTestInstanceBlockStatus(ClientboundTestInstanceBlockStatus packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleTestInstanceBlockStatus:(Lnet/minecraft/network/protocol/game/ClientboundTestInstanceBlockStatus;)V");
    }

    public void handleWaypoint(ClientboundTrackedWaypointPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleWaypoint:(Lnet/minecraft/network/protocol/game/ClientboundTrackedWaypointPacket;)V");
    }

    public void handleDebugChunkValue(ClientboundDebugChunkValuePacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleDebugChunkValue:(Lnet/minecraft/network/protocol/game/ClientboundDebugChunkValuePacket;)V");
    }

    public void handleDebugBlockValue(ClientboundDebugBlockValuePacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleDebugBlockValue:(Lnet/minecraft/network/protocol/game/ClientboundDebugBlockValuePacket;)V");
    }

    public void handleDebugEntityValue(ClientboundDebugEntityValuePacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleDebugEntityValue:(Lnet/minecraft/network/protocol/game/ClientboundDebugEntityValuePacket;)V");
    }

    public void handleDebugEvent(ClientboundDebugEventPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleDebugEvent:(Lnet/minecraft/network/protocol/game/ClientboundDebugEventPacket;)V");
    }

    public void handleGameTestHighlightPos(ClientboundGameTestHighlightPosPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleGameTestHighlightPos:(Lnet/minecraft/network/protocol/game/ClientboundGameTestHighlightPosPacket;)V");
    }

    public void handleLowDiskSpaceWarning(ClientboundLowDiskSpaceWarningPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.handleLowDiskSpaceWarning:(Lnet/minecraft/network/protocol/game/ClientboundLowDiskSpaceWarningPacket;)V");
    }

    public Connection getConnection() {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.getConnection:()Lnet/minecraft/network/Connection;");
    }

    public boolean isAcceptingMessages() {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.isAcceptingMessages:()Z");
    }

    public ClientLevel getLevel() {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.getLevel:()Lnet/minecraft/client/multiplayer/ClientLevel;");
    }

    public Set<ResourceKey<Level>> levels() {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.levels:()Ljava/util/Set;");
    }

    public RegistryAccess.Frozen registryAccess() {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.registryAccess:()Lnet/minecraft/core/RegistryAccess$Frozen;");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.tick:()V");
    }

    protected DialogConnectionAccess createDialogAccess() {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.createDialogAccess:()Lnet/minecraft/client/gui/screens/dialog/DialogConnectionAccess;");
    }

    public FeatureFlagSet enabledFeatures() {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.enabledFeatures:()Lnet/minecraft/world/flag/FeatureFlagSet;");
    }

    public net.neoforged.neoforge.network.connection.ConnectionType getConnectionType() {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.getConnectionType:()Lnet/neoforged/neoforge/network/connection/ConnectionType;");
    }

    public PotionBrewing potionBrewing() {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.potionBrewing:()Lnet/minecraft/world/item/alchemy/PotionBrewing;");
    }

    public FuelValues fuelValues() {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.fuelValues:()Lnet/minecraft/world/level/block/entity/FuelValues;");
    }

    public void registerForCleaning(CacheSlot<?, ?> slot) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.registerForCleaning:(Lnet/minecraft/client/multiplayer/CacheSlot;)V");
    }

    public ClientClockManager clockManager() {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientPacketListener.clockManager:()Lnet/minecraft/client/ClientClockManager;");
    }

    private enum CommandCheckResult {

        NO_ISSUES, PARSE_ERRORS, SIGNATURE_REQUIRED, PERMISSIONS_REQUIRED
    }

    protected ClientPacketListener() {
    }
}
