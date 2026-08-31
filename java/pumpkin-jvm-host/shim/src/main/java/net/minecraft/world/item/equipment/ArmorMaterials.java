package net.minecraft.world.item.equipment;

public interface ArmorMaterials {

    // Pumpkin divergence: vanilla's numbers (durability 37, defense 3/6/8/3/19,
    // enchant 15, toughness 3, knockback resistance 0.1). The sound, repair tag and
    // asset are identity-bearing references Mekanism's stat reads never touch.
    ArmorMaterial NETHERITE = new ArmorMaterial(37,
            java.util.Map.of(ArmorType.BOOTS, 3, ArmorType.LEGGINGS, 6,
                    ArmorType.CHESTPLATE, 8, ArmorType.HELMET, 3, ArmorType.BODY, 19),
            15, null, 3.0F, 0.1F, null, null);
}
