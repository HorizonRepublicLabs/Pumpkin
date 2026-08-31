package net.minecraft.world.item.component;

import java.util.List;
import java.util.Optional;
import net.minecraft.core.HolderSet;
import net.minecraft.world.level.block.Block;
import dev.pumpkin.shim.Unimplemented;

public record Tool(List<Tool.Rule> rules, float defaultMiningSpeed, int damagePerBlock, boolean canDestroyBlocksInCreative) {

    public record Rule(HolderSet<Block> blocks, Optional<Float> speed, Optional<Boolean> correctForDrops) {

        public static Tool.Rule deniesDrops(HolderSet<Block> blocks) {
            throw Unimplemented.forMember("net/minecraft/world/item/component/Tool$Rule.deniesDrops:(Lnet/minecraft/core/HolderSet;)Lnet/minecraft/world/item/component/Tool$Rule;");
        }
    }
}
