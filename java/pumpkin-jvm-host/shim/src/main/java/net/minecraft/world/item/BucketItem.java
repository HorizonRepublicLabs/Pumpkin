package net.minecraft.world.item;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.BlockHitResult;
import dev.pumpkin.shim.Unimplemented;

public class BucketItem extends Item implements DispensibleContainerItem {

    public final Fluid content = null;

    public BucketItem(Fluid content, Item.Properties properties) {
    }

    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        throw Unimplemented.forMember("net/minecraft/world/item/BucketItem.use:(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/InteractionResult;");
    }

    public void checkExtraContent(LivingEntity user, Level level, ItemStack itemStack, BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/item/BucketItem.checkExtraContent:(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/core/BlockPos;)V");
    }

    public boolean emptyContents(LivingEntity user, Level level, BlockPos pos, BlockHitResult hitResult) {
        throw Unimplemented.forMember("net/minecraft/world/item/BucketItem.emptyContents:(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/phys/BlockHitResult;)Z");
    }

    public BucketItem() {
    }
}
