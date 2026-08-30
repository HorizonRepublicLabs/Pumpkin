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
