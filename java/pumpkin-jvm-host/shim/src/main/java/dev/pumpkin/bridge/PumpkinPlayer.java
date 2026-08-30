package dev.pumpkin.bridge;

import dev.pumpkin.shim.Unimplemented;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;

/**
 * The {@code Player} a mod's interaction code sees.
 *
 * <p>One instance per interaction. It answers what a right-click handler asks -- what is
 * in the hand, replace what is in the hand, where is the player -- and refuses the rest
 * with keys. The bridge reads the replaced hand stack back out and applies it to the real
 * player on the Rust side.
 */
public final class PumpkinPlayer extends net.minecraft.world.entity.player.Player {
    private ItemStack held;
    private boolean heldChanged;
    public PumpkinPlayer(ItemStack held, double x, double y, double z) {
        this.held = held;
        pumpkinSetPosition(new net.minecraft.world.phys.Vec3(x, y, z));
    }

    private String openedMenu;

    /** {@code type|windowId|title} for the menu the interaction opened, or null. */
    public String pumpkinOpenedMenu() {
        return openedMenu;
    }

    private final net.minecraft.world.entity.player.Inventory inventory =
            new net.minecraft.world.entity.player.Inventory();

    @Override
    public net.minecraft.world.entity.player.Inventory getInventory() {
        return inventory;
    }

    @Override
    public java.util.OptionalInt openMenu(net.minecraft.world.MenuProvider provider,
            net.minecraft.core.BlockPos pos) {
        // The extension overload NeoForge blocks actually call; the position adds
        // nothing the provider does not already know.
        return openMenu(provider);
    }

    @Override
    public java.util.OptionalInt openMenu(net.minecraft.world.MenuProvider provider) {
        int windowId = dev.pumpkin.bridge.PumpkinMenusAccess.nextWindowId();
        net.minecraft.world.inventory.AbstractContainerMenu menu =
                provider.createMenu(windowId, inventory, this);
        if (menu == null) {
            return java.util.OptionalInt.empty();
        }
        dev.pumpkin.bridge.PumpkinMenusAccess.register(windowId, menu);
        dev.pumpkin.bridge.PumpkinMenusAccess.bindPlayer(windowId, this);
        String type = dev.pumpkin.bridge.PumpkinMenusAccess.typeName(menu);
        String title = provider.getDisplayName() == null ? ""
                : provider.getDisplayName().getString();
        openedMenu = (type == null ? "unknown" : type) + "|" + windowId + "|" + title;
        return java.util.OptionalInt.of(windowId);
    }

    /** The hand stack after the interaction, or null when the mod never replaced it. */
    public ItemStack pumpkinHeldAfter() {
        return heldChanged ? held : null;
    }

    @Override
    public ItemStack getItemInHand(InteractionHand hand) {
        return held;
    }

    @Override
    public void setItemInHand(InteractionHand hand, ItemStack stack) {
        held = stack;
        heldChanged = true;
    }

    @Override
    public net.minecraft.world.level.GameType gameMode() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Player.gameMode");
    }
}
