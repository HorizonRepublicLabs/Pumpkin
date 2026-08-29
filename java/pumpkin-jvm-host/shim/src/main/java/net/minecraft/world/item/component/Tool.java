package net.minecraft.world.item.component;

import java.util.List;
import java.util.Optional;
import net.minecraft.core.HolderSet;
import net.minecraft.world.level.block.Block;

public record Tool(List<Tool.Rule> rules, float defaultMiningSpeed, int damagePerBlock, boolean canDestroyBlocksInCreative) {

    public record Rule(HolderSet<Block> blocks, Optional<Float> speed, Optional<Boolean> correctForDrops) {
    }
}
