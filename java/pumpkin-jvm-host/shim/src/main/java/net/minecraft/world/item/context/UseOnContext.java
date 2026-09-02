package net.minecraft.world.item.context;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public class UseOnContext {

    // Pumpkin divergence: the fields behind the real getters below. The bridge fills
    // them in through pumpkinSet before handing the context to the mod.
    protected net.minecraft.core.BlockPos pumpkinClickedPos;

    protected Direction pumpkinClickedFace;

    protected Player pumpkinPlayer;

    protected ItemStack pumpkinItemInHand;

    public void pumpkinSet(net.minecraft.core.BlockPos clickedPos, Direction clickedFace,
            Player player, ItemStack itemInHand) {
        this.pumpkinClickedPos = clickedPos;
        this.pumpkinClickedFace = clickedFace;
        this.pumpkinPlayer = player;
        this.pumpkinItemInHand = itemInHand;
    }

    public UseOnContext(Player player, InteractionHand hand, BlockHitResult hitResult) {
    }

    public UseOnContext(Level level, Player player, InteractionHand hand, ItemStack itemStack, BlockHitResult hitResult) {
    }

    // Pumpkin divergence: real body.
    public BlockPos getClickedPos() {
        return pumpkinClickedPos;
    }

    // Pumpkin divergence: real body.
    public Direction getClickedFace() {
        return pumpkinClickedFace;
    }

    public Vec3 getClickLocation() {
        throw Unimplemented.forMember("net/minecraft/world/item/context/UseOnContext.getClickLocation:()Lnet/minecraft/world/phys/Vec3;");
    }

    public boolean isInside() {
        throw Unimplemented.forMember("net/minecraft/world/item/context/UseOnContext.isInside:()Z");
    }

    // Pumpkin divergence: real body.
    public ItemStack getItemInHand() {
        return pumpkinItemInHand;
    }

    // Pumpkin divergence: real body.
    public Player getPlayer() {
        return pumpkinPlayer;
    }

    public InteractionHand getHand() {
        throw Unimplemented.forMember("net/minecraft/world/item/context/UseOnContext.getHand:()Lnet/minecraft/world/InteractionHand;");
    }

    // Pumpkin divergence: real body -- the shared stand-in level.
    public Level getLevel() {
        return dev.pumpkin.bridge.PumpkinInteractions.pumpkinLevel();
    }

    // Pumpkin divergence: real body -- the placer's facing, from their yaw.
    public Direction getHorizontalDirection() {
        return Direction.fromYRot(pumpkinPlayer.getYRot());
    }

    public UseOnContext() {
    }
}
