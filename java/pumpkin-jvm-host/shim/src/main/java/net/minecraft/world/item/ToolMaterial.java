package net.minecraft.world.item;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public record ToolMaterial(TagKey<Block> incorrectBlocksForDrops, int durability, float speed, float attackDamageBonus, int enchantmentValue, TagKey<Item> repairItems) {

    public static final ToolMaterial WOOD = null;

    public static final ToolMaterial STONE = null;

    public static final ToolMaterial COPPER = null;

    public static final ToolMaterial IRON = null;

    public static final ToolMaterial DIAMOND = null;

    public static final ToolMaterial GOLD = null;

    public static final ToolMaterial NETHERITE = null;
}
