package net.minecraft.client.player;

import net.minecraft.client.ClientRecipeBook;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.multiplayer.chat.ChatAbilities;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.server.dialog.Dialog;
import net.minecraft.server.permissions.PermissionSet;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.stats.StatsCounter;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Input;
import net.minecraft.world.entity.vehicle.minecart.MinecartCommandBlock;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.CommandBlockEntity;
import net.minecraft.world.level.block.entity.JigsawBlockEntity;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.entity.StructureBlockEntity;
import net.minecraft.world.level.block.entity.TestBlockEntity;
import net.minecraft.world.level.block.entity.TestInstanceBlockEntity;
import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public class LocalPlayer extends AbstractClientPlayer {

    public LocalPlayer(Minecraft minecraft, ClientLevel level, ClientPacketListener connection, StatsCounter stats, ClientRecipeBook recipeBook, Input lastSentInput, boolean wasSprinting, ChatAbilities chatAbilities) {
        throw Unimplemented.forMember("net/minecraft/client/player/LocalPlayer.<init>:(Lnet/minecraft/client/Minecraft;Lnet/minecraft/client/multiplayer/ClientLevel;Lnet/minecraft/client/multiplayer/ClientPacketListener;Lnet/minecraft/stats/StatsCounter;Lnet/minecraft/client/ClientRecipeBook;Lnet/minecraft/world/entity/player/Input;ZLnet/minecraft/client/multiplayer/chat/ChatAbilities;)V");
    }

    public void heal(float heal) {
        throw Unimplemented.forMember("net/minecraft/client/player/LocalPlayer.heal:(F)V");
    }

    public boolean startRiding(Entity entity, boolean force, boolean sendEventAndTriggers) {
        throw Unimplemented.forMember("net/minecraft/client/player/LocalPlayer.startRiding:(Lnet/minecraft/world/entity/Entity;ZZ)Z");
    }

    public void removeVehicle() {
        throw Unimplemented.forMember("net/minecraft/client/player/LocalPlayer.removeVehicle:()V");
    }

    public float getViewXRot(float a) {
        throw Unimplemented.forMember("net/minecraft/client/player/LocalPlayer.getViewXRot:(F)F");
    }

    public float getViewYRot(float a) {
        throw Unimplemented.forMember("net/minecraft/client/player/LocalPlayer.getViewYRot:(F)F");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/client/player/LocalPlayer.tick:()V");
    }

    public void swing(InteractionHand hand) {
        throw Unimplemented.forMember("net/minecraft/client/player/LocalPlayer.swing:(Lnet/minecraft/world/InteractionHand;)V");
    }

    public void closeContainer() {
        throw Unimplemented.forMember("net/minecraft/client/player/LocalPlayer.closeContainer:()V");
    }

    public void onUpdateAbilities() {
        throw Unimplemented.forMember("net/minecraft/client/player/LocalPlayer.onUpdateAbilities:()V");
    }

    public void setReducedDebugInfo(boolean reducedDebugInfo) {
        throw Unimplemented.forMember("net/minecraft/client/player/LocalPlayer.setReducedDebugInfo:(Z)V");
    }

    public boolean isLocalPlayer() {
        throw Unimplemented.forMember("net/minecraft/client/player/LocalPlayer.isLocalPlayer:()Z");
    }

    public boolean isSuppressingSlidingDownLadder() {
        throw Unimplemented.forMember("net/minecraft/client/player/LocalPlayer.isSuppressingSlidingDownLadder:()Z");
    }

    public boolean canSpawnSprintParticle() {
        throw Unimplemented.forMember("net/minecraft/client/player/LocalPlayer.canSpawnSprintParticle:()Z");
    }

    public PermissionSet permissions() {
        throw Unimplemented.forMember("net/minecraft/client/player/LocalPlayer.permissions:()Lnet/minecraft/server/permissions/PermissionSet;");
    }

    public void sendSystemMessage(Component message) {
        throw Unimplemented.forMember("net/minecraft/client/player/LocalPlayer.sendSystemMessage:(Lnet/minecraft/network/chat/Component;)V");
    }

    public void sendOverlayMessage(Component message) {
        throw Unimplemented.forMember("net/minecraft/client/player/LocalPlayer.sendOverlayMessage:(Lnet/minecraft/network/chat/Component;)V");
    }

    public void handleEntityEvent(byte id) {
        throw Unimplemented.forMember("net/minecraft/client/player/LocalPlayer.handleEntityEvent:(B)V");
    }

    public void playSound(SoundEvent sound, float volume, float pitch) {
        throw Unimplemented.forMember("net/minecraft/client/player/LocalPlayer.playSound:(Lnet/minecraft/sounds/SoundEvent;FF)V");
    }

    public void startUsingItem(InteractionHand hand) {
        throw Unimplemented.forMember("net/minecraft/client/player/LocalPlayer.startUsingItem:(Lnet/minecraft/world/InteractionHand;)V");
    }

    public boolean isUsingItem() {
        throw Unimplemented.forMember("net/minecraft/client/player/LocalPlayer.isUsingItem:()Z");
    }

    public void stopUsingItem() {
        throw Unimplemented.forMember("net/minecraft/client/player/LocalPlayer.stopUsingItem:()V");
    }

    public InteractionHand getUsedItemHand() {
        throw Unimplemented.forMember("net/minecraft/client/player/LocalPlayer.getUsedItemHand:()Lnet/minecraft/world/InteractionHand;");
    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> accessor) {
        throw Unimplemented.forMember("net/minecraft/client/player/LocalPlayer.onSyncedDataUpdated:(Lnet/minecraft/network/syncher/EntityDataAccessor;)V");
    }

    public boolean isTextFilteringEnabled() {
        throw Unimplemented.forMember("net/minecraft/client/player/LocalPlayer.isTextFilteringEnabled:()Z");
    }

    public void openTextEdit(SignBlockEntity sign, boolean isFrontText) {
        throw Unimplemented.forMember("net/minecraft/client/player/LocalPlayer.openTextEdit:(Lnet/minecraft/world/level/block/entity/SignBlockEntity;Z)V");
    }

    public void openMinecartCommandBlock(MinecartCommandBlock commandBlock) {
        throw Unimplemented.forMember("net/minecraft/client/player/LocalPlayer.openMinecartCommandBlock:(Lnet/minecraft/world/entity/vehicle/minecart/MinecartCommandBlock;)V");
    }

    public void openCommandBlock(CommandBlockEntity commandBlock) {
        throw Unimplemented.forMember("net/minecraft/client/player/LocalPlayer.openCommandBlock:(Lnet/minecraft/world/level/block/entity/CommandBlockEntity;)V");
    }

    public void openStructureBlock(StructureBlockEntity structureBlock) {
        throw Unimplemented.forMember("net/minecraft/client/player/LocalPlayer.openStructureBlock:(Lnet/minecraft/world/level/block/entity/StructureBlockEntity;)V");
    }

    public void openTestBlock(TestBlockEntity testBlock) {
        throw Unimplemented.forMember("net/minecraft/client/player/LocalPlayer.openTestBlock:(Lnet/minecraft/world/level/block/entity/TestBlockEntity;)V");
    }

    public void openTestInstanceBlock(TestInstanceBlockEntity testInstanceBlock) {
        throw Unimplemented.forMember("net/minecraft/client/player/LocalPlayer.openTestInstanceBlock:(Lnet/minecraft/world/level/block/entity/TestInstanceBlockEntity;)V");
    }

    public void openJigsawBlock(JigsawBlockEntity jigsawBlock) {
        throw Unimplemented.forMember("net/minecraft/client/player/LocalPlayer.openJigsawBlock:(Lnet/minecraft/world/level/block/entity/JigsawBlockEntity;)V");
    }

    public void openDialog(Holder<Dialog> dialog) {
        throw Unimplemented.forMember("net/minecraft/client/player/LocalPlayer.openDialog:(Lnet/minecraft/core/Holder;)V");
    }

    public void openItemGui(ItemStack itemStack, InteractionHand hand) {
        throw Unimplemented.forMember("net/minecraft/client/player/LocalPlayer.openItemGui:(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/InteractionHand;)V");
    }

    public void crit(Entity entity) {
        throw Unimplemented.forMember("net/minecraft/client/player/LocalPlayer.crit:(Lnet/minecraft/world/entity/Entity;)V");
    }

    public void magicCrit(Entity entity) {
        throw Unimplemented.forMember("net/minecraft/client/player/LocalPlayer.magicCrit:(Lnet/minecraft/world/entity/Entity;)V");
    }

    public boolean isShiftKeyDown() {
        throw Unimplemented.forMember("net/minecraft/client/player/LocalPlayer.isShiftKeyDown:()Z");
    }

    public boolean isCrouching() {
        throw Unimplemented.forMember("net/minecraft/client/player/LocalPlayer.isCrouching:()Z");
    }

    public void applyInput() {
        throw Unimplemented.forMember("net/minecraft/client/player/LocalPlayer.applyInput:()V");
    }

    public void aiStep() {
        throw Unimplemented.forMember("net/minecraft/client/player/LocalPlayer.aiStep:()V");
    }

    protected void tickDeath() {
        throw Unimplemented.forMember("net/minecraft/client/player/LocalPlayer.tickDeath:()V");
    }

    protected boolean canGlide() {
        throw Unimplemented.forMember("net/minecraft/client/player/LocalPlayer.canGlide:()Z");
    }

    public void rideTick() {
        throw Unimplemented.forMember("net/minecraft/client/player/LocalPlayer.rideTick:()V");
    }

    public void move(MoverType moverType, Vec3 delta) {
        throw Unimplemented.forMember("net/minecraft/client/player/LocalPlayer.move:(Lnet/minecraft/world/entity/MoverType;Lnet/minecraft/world/phys/Vec3;)V");
    }

    public boolean shouldRotateWithMinecart() {
        throw Unimplemented.forMember("net/minecraft/client/player/LocalPlayer.shouldRotateWithMinecart:()Z");
    }

    protected boolean isHorizontalCollisionMinor(Vec3 movement) {
        throw Unimplemented.forMember("net/minecraft/client/player/LocalPlayer.isHorizontalCollisionMinor:(Lnet/minecraft/world/phys/Vec3;)Z");
    }

    public boolean isUnderWater() {
        throw Unimplemented.forMember("net/minecraft/client/player/LocalPlayer.isUnderWater:()Z");
    }

    protected boolean updateIsUnderwater() {
        throw Unimplemented.forMember("net/minecraft/client/player/LocalPlayer.updateIsUnderwater:()Z");
    }

    public Vec3 getRopeHoldPosition(float partialTickTime) {
        throw Unimplemented.forMember("net/minecraft/client/player/LocalPlayer.getRopeHoldPosition:(F)Lnet/minecraft/world/phys/Vec3;");
    }

    public void updateTutorialInventoryAction(ItemStack itemCarried, ItemStack itemInSlot, ClickAction clickAction) {
        throw Unimplemented.forMember("net/minecraft/client/player/LocalPlayer.updateTutorialInventoryAction:(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/inventory/ClickAction;)V");
    }

    public float getVisualRotationYInDegrees() {
        throw Unimplemented.forMember("net/minecraft/client/player/LocalPlayer.getVisualRotationYInDegrees:()F");
    }

    public void handleCreativeModeItemDrop(ItemStack stack) {
        throw Unimplemented.forMember("net/minecraft/client/player/LocalPlayer.handleCreativeModeItemDrop:(Lnet/minecraft/world/item/ItemStack;)V");
    }

    public boolean canDropItems() {
        throw Unimplemented.forMember("net/minecraft/client/player/LocalPlayer.canDropItems:()Z");
    }

    public LocalPlayer() {
    }
}
