package net.minecraft.world.item;

import dev.pumpkin.shim.Unimplemented;

public class Items {
    // Pumpkin divergence: a vanilla stand-in carries its own name -- identity-stable, and
    // the registration path can copy the real item's definition from it.
    private static Item pumpkinVanilla(String name) {
        Item item = new Item(new Item.Properties());
        item.pumpkinVanillaName = name;
        return item;
    }


    public static final Item AIR = pumpkinVanilla("air");

    public static final Item STONE = pumpkinVanilla("stone");

    public static final Item COBBLESTONE = pumpkinVanilla("cobblestone");

    public static final Item CRAFTING_TABLE = pumpkinVanilla("crafting_table");

    public static final Item FURNACE = pumpkinVanilla("furnace");

    public static final Item BARRIER = pumpkinVanilla("barrier");

    public static final Item REDSTONE = pumpkinVanilla("redstone");

    public static final Item FLINT_AND_STEEL = pumpkinVanilla("flint_and_steel");

    public static final Item BOW = pumpkinVanilla("bow");

    public static final Item WOODEN_SHOVEL = pumpkinVanilla("wooden_shovel");

    public static final Item WOODEN_PICKAXE = pumpkinVanilla("wooden_pickaxe");

    public static final Item WOODEN_AXE = pumpkinVanilla("wooden_axe");

    public static final Item STONE_SHOVEL = pumpkinVanilla("stone_shovel");

    public static final Item STONE_PICKAXE = pumpkinVanilla("stone_pickaxe");

    public static final Item STONE_AXE = pumpkinVanilla("stone_axe");

    public static final Item GOLDEN_SHOVEL = pumpkinVanilla("golden_shovel");

    public static final Item GOLDEN_PICKAXE = pumpkinVanilla("golden_pickaxe");

    public static final Item GOLDEN_AXE = pumpkinVanilla("golden_axe");

    public static final Item IRON_SHOVEL = pumpkinVanilla("iron_shovel");

    public static final Item IRON_PICKAXE = pumpkinVanilla("iron_pickaxe");

    public static final Item IRON_AXE = pumpkinVanilla("iron_axe");

    public static final Item DIAMOND_SWORD = pumpkinVanilla("diamond_sword");

    public static final Item DIAMOND_SHOVEL = pumpkinVanilla("diamond_shovel");

    public static final Item DIAMOND_PICKAXE = pumpkinVanilla("diamond_pickaxe");

    public static final Item DIAMOND_AXE = pumpkinVanilla("diamond_axe");

    public static final Item DIAMOND_HOE = pumpkinVanilla("diamond_hoe");

    public static final Item NETHERITE_SHOVEL = pumpkinVanilla("netherite_shovel");

    public static final Item NETHERITE_PICKAXE = pumpkinVanilla("netherite_pickaxe");

    public static final Item NETHERITE_AXE = pumpkinVanilla("netherite_axe");

    public static final Item GUNPOWDER = pumpkinVanilla("gunpowder");

    public static final Item DIAMOND_HELMET = pumpkinVanilla("diamond_helmet");

    public static final Item DIAMOND_CHESTPLATE = pumpkinVanilla("diamond_chestplate");

    public static final Item DIAMOND_LEGGINGS = pumpkinVanilla("diamond_leggings");

    public static final Item DIAMOND_BOOTS = pumpkinVanilla("diamond_boots");

    public static final Item BUCKET = pumpkinVanilla("bucket");

    public static final Item WATER_BUCKET = pumpkinVanilla("water_bucket");

    public static final Item BOOK = pumpkinVanilla("book");

    public static final Item FISHING_ROD = pumpkinVanilla("fishing_rod");

    public static final Item SHEARS = pumpkinVanilla("shears");

    public static final Item NETHER_WART = pumpkinVanilla("nether_wart");

    public static final Item FIRE_CHARGE = pumpkinVanilla("fire_charge");

    public static final Item DIAMOND_SPEAR = pumpkinVanilla("diamond_spear");

    public static final Item TOTEM_OF_UNDYING = pumpkinVanilla("totem_of_undying");

    public static final Item CROSSBOW = pumpkinVanilla("crossbow");

    public Items() {
    }

    // Pumpkin divergence: no throwing initializer -- every stand-in above is real.
}
