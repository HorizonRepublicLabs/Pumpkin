package net.minecraft.world.item;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public record ToolMaterial(TagKey<Block> incorrectBlocksForDrops, int durability, float speed, float attackDamageBonus, int enchantmentValue, TagKey<Item> repairItems) {

    // Pumpkin divergence: real values, copied from vanilla -- a record over numbers
    // and tag keys, all of which are real here. The tag keys are built inline because
    // the INCORRECT_FOR_*/. *_TOOL_MATERIALS holder fields did not survive pruning.
    public static final ToolMaterial WOOD = new ToolMaterial(
            net.minecraft.tags.BlockTags.create(net.minecraft.resources.Identifier.fromNamespaceAndPath("minecraft", "incorrect_for_wood_tool")),
            59, 2.0F, 0.0F, 15,
            net.minecraft.tags.ItemTags.create(net.minecraft.resources.Identifier.fromNamespaceAndPath("minecraft", "wooden_tool_materials")));

    public static final ToolMaterial STONE = new ToolMaterial(
            net.minecraft.tags.BlockTags.create(net.minecraft.resources.Identifier.fromNamespaceAndPath("minecraft", "incorrect_for_stone_tool")),
            131, 4.0F, 1.0F, 5,
            net.minecraft.tags.ItemTags.create(net.minecraft.resources.Identifier.fromNamespaceAndPath("minecraft", "stone_tool_materials")));

    public static final ToolMaterial COPPER = new ToolMaterial(
            net.minecraft.tags.BlockTags.create(net.minecraft.resources.Identifier.fromNamespaceAndPath("minecraft", "incorrect_for_copper_tool")),
            190, 5.0F, 1.0F, 13,
            net.minecraft.tags.ItemTags.create(net.minecraft.resources.Identifier.fromNamespaceAndPath("minecraft", "copper_tool_materials")));

    public static final ToolMaterial IRON = new ToolMaterial(
            net.minecraft.tags.BlockTags.create(net.minecraft.resources.Identifier.fromNamespaceAndPath("minecraft", "incorrect_for_iron_tool")),
            250, 6.0F, 2.0F, 14,
            net.minecraft.tags.ItemTags.create(net.minecraft.resources.Identifier.fromNamespaceAndPath("minecraft", "iron_tool_materials")));

    public static final ToolMaterial DIAMOND = new ToolMaterial(
            net.minecraft.tags.BlockTags.create(net.minecraft.resources.Identifier.fromNamespaceAndPath("minecraft", "incorrect_for_diamond_tool")),
            1561, 8.0F, 3.0F, 10,
            net.minecraft.tags.ItemTags.create(net.minecraft.resources.Identifier.fromNamespaceAndPath("minecraft", "diamond_tool_materials")));

    public static final ToolMaterial GOLD = new ToolMaterial(
            net.minecraft.tags.BlockTags.create(net.minecraft.resources.Identifier.fromNamespaceAndPath("minecraft", "incorrect_for_gold_tool")),
            32, 12.0F, 0.0F, 22,
            net.minecraft.tags.ItemTags.create(net.minecraft.resources.Identifier.fromNamespaceAndPath("minecraft", "gold_tool_materials")));

    public static final ToolMaterial NETHERITE = new ToolMaterial(
            net.minecraft.tags.BlockTags.create(net.minecraft.resources.Identifier.fromNamespaceAndPath("minecraft", "incorrect_for_netherite_tool")),
            2031, 9.0F, 4.0F, 15,
            net.minecraft.tags.ItemTags.create(net.minecraft.resources.Identifier.fromNamespaceAndPath("minecraft", "netherite_tool_materials")));
}
