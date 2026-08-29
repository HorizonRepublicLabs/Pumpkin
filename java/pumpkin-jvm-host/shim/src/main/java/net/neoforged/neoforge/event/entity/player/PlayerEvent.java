package net.neoforged.neoforge.event.entity.player;

import java.io.File;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.Container;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.ICancellableEvent;
import net.neoforged.neoforge.event.entity.living.LivingEvent;
import dev.pumpkin.shim.Unimplemented;

public abstract class PlayerEvent extends LivingEvent {

    public PlayerEvent(Player player) {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/player/PlayerEvent.<init>:(Lnet/minecraft/world/entity/player/Player;)V");
    }

    public Player getEntity() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/player/PlayerEvent.getEntity:()Lnet/minecraft/world/entity/player/Player;");
    }

    public static class HarvestCheck extends PlayerEvent {

        public HarvestCheck(Player player, BlockState state, BlockGetter level, BlockPos pos, boolean success) {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/player/PlayerEvent$HarvestCheck.<init>:(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Z)V");
        }

        public BlockGetter getLevel() {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/player/PlayerEvent$HarvestCheck.getLevel:()Lnet/minecraft/world/level/BlockGetter;");
        }

        public BlockPos getPos() {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/player/PlayerEvent$HarvestCheck.getPos:()Lnet/minecraft/core/BlockPos;");
        }

        public HarvestCheck() {
        }
    }

    public static class BreakSpeed extends PlayerEvent implements ICancellableEvent {

        public BreakSpeed(Player player, BlockState state, float original, BlockPos pos) {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/player/PlayerEvent$BreakSpeed.<init>:(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/level/block/state/BlockState;FLnet/minecraft/core/BlockPos;)V");
        }

        public BreakSpeed() {
        }
    }

    public static class NameFormat extends PlayerEvent {

        public NameFormat(Player player, Component username) {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/player/PlayerEvent$NameFormat.<init>:(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/network/chat/Component;)V");
        }

        public NameFormat() {
        }
    }

    public static class TabListNameFormat extends PlayerEvent {

        public TabListNameFormat(Player player) {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/player/PlayerEvent$TabListNameFormat.<init>:(Lnet/minecraft/world/entity/player/Player;)V");
        }

        public Component getDisplayName() {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/player/PlayerEvent$TabListNameFormat.getDisplayName:()Lnet/minecraft/network/chat/Component;");
        }

        public TabListNameFormat() {
        }
    }

    public static class Clone extends PlayerEvent {

        public Clone(Player _new, Player oldPlayer, boolean wasDeath) {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/player/PlayerEvent$Clone.<init>:(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/entity/player/Player;Z)V");
        }

        public Clone() {
        }
    }

    public static class StartTracking extends PlayerEvent {

        public StartTracking(Player player, Entity target) {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/player/PlayerEvent$StartTracking.<init>:(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/entity/Entity;)V");
        }

        public Entity getTarget() {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/player/PlayerEvent$StartTracking.getTarget:()Lnet/minecraft/world/entity/Entity;");
        }

        public StartTracking() {
        }
    }

    public static class StopTracking extends PlayerEvent {

        public StopTracking(Player player, Entity target) {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/player/PlayerEvent$StopTracking.<init>:(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/entity/Entity;)V");
        }

        public Entity getTarget() {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/player/PlayerEvent$StopTracking.getTarget:()Lnet/minecraft/world/entity/Entity;");
        }

        public StopTracking() {
        }
    }

    public static class LoadFromFile extends PlayerEvent {

        public LoadFromFile(Player player, File originDirectory, String playerUUID) {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/player/PlayerEvent$LoadFromFile.<init>:(Lnet/minecraft/world/entity/player/Player;Ljava/io/File;Ljava/lang/String;)V");
        }

        public LoadFromFile() {
        }
    }

    public static class SaveToFile extends PlayerEvent {

        public SaveToFile(Player player, File originDirectory, String playerUUID) {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/player/PlayerEvent$SaveToFile.<init>:(Lnet/minecraft/world/entity/player/Player;Ljava/io/File;Ljava/lang/String;)V");
        }

        public SaveToFile() {
        }
    }

    public static class ItemCraftedEvent extends PlayerEvent {

        public ItemCraftedEvent(Player player, ItemStack crafting, Container craftMatrix) {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/player/PlayerEvent$ItemCraftedEvent.<init>:(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/Container;)V");
        }

        public ItemCraftedEvent() {
        }
    }

    public static class ItemSmeltedEvent extends PlayerEvent {

        public ItemSmeltedEvent(Player player, ItemStack crafting, int amountRemoved) {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/player/PlayerEvent$ItemSmeltedEvent.<init>:(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/item/ItemStack;I)V");
        }

        public ItemSmeltedEvent() {
        }
    }

    public static class PlayerLoggedInEvent extends PlayerEvent {

        public PlayerLoggedInEvent(Player player) {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/player/PlayerEvent$PlayerLoggedInEvent.<init>:(Lnet/minecraft/world/entity/player/Player;)V");
        }

        public PlayerLoggedInEvent() {
        }
    }

    public static class PlayerLoggedOutEvent extends PlayerEvent {

        public PlayerLoggedOutEvent(Player player) {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/player/PlayerEvent$PlayerLoggedOutEvent.<init>:(Lnet/minecraft/world/entity/player/Player;)V");
        }

        public PlayerLoggedOutEvent() {
        }
    }

    public static class PlayerRespawnEvent extends PlayerEvent {

        public PlayerRespawnEvent(Player player, boolean endConquered) {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/player/PlayerEvent$PlayerRespawnEvent.<init>:(Lnet/minecraft/world/entity/player/Player;Z)V");
        }

        public PlayerRespawnEvent() {
        }
    }

    public static class PlayerChangedDimensionEvent extends PlayerEvent {

        public PlayerChangedDimensionEvent(Player player, ResourceKey<Level> fromDim, ResourceKey<Level> toDim) {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/player/PlayerEvent$PlayerChangedDimensionEvent.<init>:(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/resources/ResourceKey;)V");
        }

        public PlayerChangedDimensionEvent() {
        }
    }

    public static class PlayerChangeGameModeEvent extends PlayerEvent implements ICancellableEvent {

        public PlayerChangeGameModeEvent(Player player, GameType currentGameMode, GameType newGameMode) {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/player/PlayerEvent$PlayerChangeGameModeEvent.<init>:(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/level/GameType;Lnet/minecraft/world/level/GameType;)V");
        }

        public PlayerChangeGameModeEvent() {
        }
    }

    public PlayerEvent() {
    }
}
