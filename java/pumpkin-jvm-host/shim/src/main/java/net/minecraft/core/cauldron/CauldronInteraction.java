package net.minecraft.core.cauldron;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import dev.pumpkin.shim.Unimplemented;

public interface CauldronInteraction {

    InteractionResult interact(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, ItemStack itemInHand);

    class Dispatcher {

        public void put(Item item, CauldronInteraction interaction) {
            throw Unimplemented.forMember("net/minecraft/core/cauldron/CauldronInteraction$Dispatcher.put:(Lnet/minecraft/world/item/Item;Lnet/minecraft/core/cauldron/CauldronInteraction;)V");
        }

        public void put(TagKey<Item> tag, CauldronInteraction interaction) {
            throw Unimplemented.forMember("net/minecraft/core/cauldron/CauldronInteraction$Dispatcher.put:(Lnet/minecraft/tags/TagKey;Lnet/minecraft/core/cauldron/CauldronInteraction;)V");
        }

        public CauldronInteraction get(ItemStack itemStack) {
            throw Unimplemented.forMember("net/minecraft/core/cauldron/CauldronInteraction$Dispatcher.get:(Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/core/cauldron/CauldronInteraction;");
        }

        protected Dispatcher() {
        }
    }
}
