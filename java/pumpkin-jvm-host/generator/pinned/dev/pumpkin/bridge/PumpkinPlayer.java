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
    // Pumpkin divergence: the real sneak state of the interacting player, carried
    // over the bridge. Crouching and shift answer the same fact here: the stand-in
    // has no pose model to separate them.
    private boolean pumpkinSneaking;

    public void pumpkinSetSneaking(boolean sneaking) {
        this.pumpkinSneaking = sneaking;
    }

    public boolean isShiftKeyDown() {
        return pumpkinSneaking;
    }

    public boolean isCrouching() {
        return pumpkinSneaking;
    }

    // Pumpkin divergence: where the player is looking, carried over the bridge.
    // A block that faces the placer reads exactly these two: Mekanism's facing
    // attribute rounds getXRot to decide floor/ceiling placement and floors getYRot
    // into one of four horizontals. Left unset they stay at zero, which reads as
    // "looking due south, level" -- so the bridge sets them on every placement rather
    // than letting a default stand in for a real player's aim.
    private float pumpkinYRot;

    private float pumpkinXRot;

    public void pumpkinSetRotation(float yRot, float xRot) {
        this.pumpkinYRot = yRot;
        this.pumpkinXRot = xRot;
    }

    @Override
    public float getYRot() {
        return pumpkinYRot;
    }

    @Override
    public float getXRot() {
        return pumpkinXRot;
    }

    // Pumpkin divergence: the real UUID of the interacting player, carried over the
    // bridge -- mod machines record it as the owner.
    private java.util.UUID pumpkinUuid;

    public void pumpkinSetUuid(java.util.UUID uuid) {
        this.pumpkinUuid = uuid;
    }

    public java.util.UUID getUUID() {
        if (pumpkinUuid == null) {
            throw dev.pumpkin.shim.Unimplemented.forMember(
                "net/minecraft/world/entity/Entity.getUUID:()Ljava/util/UUID; (no player on this interaction)");
        }
        return pumpkinUuid;
    }

    // Pumpkin divergence: the hands hold what the bridge was told -- the real held
    // stack in the main hand, nothing in the off hand.
    public ItemStack getMainHandItem() {
        return held;
    }

    public ItemStack getOffhandItem() {
        return ItemStack.EMPTY;
    }

    // Pumpkin divergence: the player lives in the shared one-interaction level.
    public net.minecraft.world.level.Level level() {
        return PumpkinInteractions.pumpkinLevel();
    }

    public PumpkinPlayer(ItemStack held, double x, double y, double z) {
        this.held = held;
        pumpkinSetPosition(new net.minecraft.world.phys.Vec3(x, y, z));
    }

    private String openedMenu;

    /** {@code type|windowId|title} for the menu the interaction opened, or null. */
    public String pumpkinOpenedMenu() {
        return openedMenu;
    }

    // Pumpkin divergence: the inventory really belongs to this player -- mod menus
    // reach the level through inv.player.
    private final net.minecraft.world.entity.player.Inventory inventory =
            new net.minecraft.world.entity.player.Inventory(this, null);

    @Override
    public net.minecraft.world.entity.player.Inventory getInventory() {
        return inventory;
    }

    @Override
    public java.util.OptionalInt openMenu(net.minecraft.world.MenuProvider provider,
            java.util.function.Consumer<net.minecraft.network.RegistryFriendlyByteBuf> extraData) {
        // NeoForge's extra-data overload: the buffer feeds the client-side menu ctor,
        // which Pumpkin never runs -- the server menu is what matters here.
        return openMenu(provider);
    }

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
