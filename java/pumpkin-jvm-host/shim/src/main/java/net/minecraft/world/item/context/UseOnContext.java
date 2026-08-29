package net.minecraft.world.item.context;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import dev.pumpkin.shim.Unimplemented;

public class UseOnContext {

    public UseOnContext(Player player, InteractionHand hand, BlockHitResult hitResult) {
        throw Unimplemented.forMember("net/minecraft/world/item/context/UseOnContext.<init>:(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;Lnet/minecraft/world/phys/BlockHitResult;)V");
    }

    public UseOnContext(Level level, Player player, InteractionHand hand, ItemStack itemStack, BlockHitResult hitResult) {
        throw Unimplemented.forMember("net/minecraft/world/item/context/UseOnContext.<init>:(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/phys/BlockHitResult;)V");
    }

    public BlockPos getClickedPos() {
        throw Unimplemented.forMember("net/minecraft/world/item/context/UseOnContext.getClickedPos:()Lnet/minecraft/core/BlockPos;");
    }

    public Direction getClickedFace() {
        throw Unimplemented.forMember("net/minecraft/world/item/context/UseOnContext.getClickedFace:()Lnet/minecraft/core/Direction;");
    }

    public ItemStack getItemInHand() {
        throw Unimplemented.forMember("net/minecraft/world/item/context/UseOnContext.getItemInHand:()Lnet/minecraft/world/item/ItemStack;");
    }

    public Player getPlayer() {
        throw Unimplemented.forMember("net/minecraft/world/item/context/UseOnContext.getPlayer:()Lnet/minecraft/world/entity/player/Player;");
    }

    public InteractionHand getHand() {
        throw Unimplemented.forMember("net/minecraft/world/item/context/UseOnContext.getHand:()Lnet/minecraft/world/InteractionHand;");
    }

    public Level getLevel() {
        throw Unimplemented.forMember("net/minecraft/world/item/context/UseOnContext.getLevel:()Lnet/minecraft/world/level/Level;");
    }

    protected UseOnContext() {
    }
}
