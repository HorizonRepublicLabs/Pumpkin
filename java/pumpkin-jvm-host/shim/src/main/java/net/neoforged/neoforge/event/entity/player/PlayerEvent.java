package net.neoforged.neoforge.event.entity.player;

import java.io.File;
import java.util.Optional;
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
    }

    public Player getEntity() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/player/PlayerEvent.getEntity:()Lnet/minecraft/world/entity/player/Player;");
    }

    public static class HarvestCheck extends PlayerEvent {

        public HarvestCheck(Player player, BlockState state, BlockGetter level, BlockPos pos, boolean success) {
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
        }

        public BlockState getState() {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/player/PlayerEvent$BreakSpeed.getState:()Lnet/minecraft/world/level/block/state/BlockState;");
        }

        public float getNewSpeed() {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/player/PlayerEvent$BreakSpeed.getNewSpeed:()F");
        }

        public void setNewSpeed(float newSpeed) {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/player/PlayerEvent$BreakSpeed.setNewSpeed:(F)V");
        }

        public Optional<BlockPos> getPosition() {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/player/PlayerEvent$BreakSpeed.getPosition:()Ljava/util/Optional;");
        }

        public BreakSpeed() {
        }
    }

    public static class NameFormat extends PlayerEvent {

        public NameFormat(Player player, Component username) {
        }

        public NameFormat() {
        }
    }

    public static class TabListNameFormat extends PlayerEvent {

        public TabListNameFormat(Player player) {
        }

        public Component getDisplayName() {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/player/PlayerEvent$TabListNameFormat.getDisplayName:()Lnet/minecraft/network/chat/Component;");
        }

        public TabListNameFormat() {
        }
    }

    public static class Clone extends PlayerEvent {

        public Clone(Player _new, Player oldPlayer, boolean wasDeath) {
        }

        public Clone() {
        }
    }

    public static class StartTracking extends PlayerEvent {

        public StartTracking(Player player, Entity target) {
        }

        public Entity getTarget() {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/player/PlayerEvent$StartTracking.getTarget:()Lnet/minecraft/world/entity/Entity;");
        }

        public StartTracking() {
        }
    }

    public static class StopTracking extends PlayerEvent {

        public StopTracking(Player player, Entity target) {
        }

        public Entity getTarget() {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/player/PlayerEvent$StopTracking.getTarget:()Lnet/minecraft/world/entity/Entity;");
        }

        public StopTracking() {
        }
    }

    public static class LoadFromFile extends PlayerEvent {

        public LoadFromFile(Player player, File originDirectory, String playerUUID) {
        }

        public LoadFromFile() {
        }
    }

    public static class SaveToFile extends PlayerEvent {

        public SaveToFile(Player player, File originDirectory, String playerUUID) {
        }

        public SaveToFile() {
        }
    }

    public static class ItemCraftedEvent extends PlayerEvent {

        public ItemCraftedEvent(Player player, ItemStack crafting, Container craftMatrix) {
        }

        public ItemStack getCrafting() {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/player/PlayerEvent$ItemCraftedEvent.getCrafting:()Lnet/minecraft/world/item/ItemStack;");
        }

        public Container getInventory() {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/player/PlayerEvent$ItemCraftedEvent.getInventory:()Lnet/minecraft/world/Container;");
        }

        public ItemCraftedEvent() {
        }
    }

    public static class ItemSmeltedEvent extends PlayerEvent {

        public ItemSmeltedEvent(Player player, ItemStack crafting, int amountRemoved) {
        }

        public ItemSmeltedEvent() {
        }
    }

    public static class PlayerLoggedInEvent extends PlayerEvent {

        public PlayerLoggedInEvent(Player player) {
        }

        public PlayerLoggedInEvent() {
        }
    }

    public static class PlayerLoggedOutEvent extends PlayerEvent {

        public PlayerLoggedOutEvent(Player player) {
        }

        public PlayerLoggedOutEvent() {
        }
    }

    public static class PlayerRespawnEvent extends PlayerEvent {

        public PlayerRespawnEvent(Player player, boolean endConquered) {
        }

        public PlayerRespawnEvent() {
        }
    }

    public static class PlayerChangedDimensionEvent extends PlayerEvent {

        public PlayerChangedDimensionEvent(Player player, ResourceKey<Level> fromDim, ResourceKey<Level> toDim) {
        }

        public PlayerChangedDimensionEvent() {
        }
    }

    public static class PlayerChangeGameModeEvent extends PlayerEvent implements ICancellableEvent {

        public PlayerChangeGameModeEvent(Player player, GameType currentGameMode, GameType newGameMode) {
        }

        public PlayerChangeGameModeEvent() {
        }
    }

    public PlayerEvent() {
    }
}
