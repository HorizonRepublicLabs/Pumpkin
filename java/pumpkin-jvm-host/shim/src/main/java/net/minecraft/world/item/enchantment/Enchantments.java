package net.minecraft.world.item.enchantment;

import net.minecraft.resources.ResourceKey;
import dev.pumpkin.shim.Unimplemented;

public class Enchantments {

    public static final ResourceKey<Enchantment> AQUA_AFFINITY = null;

    public static final ResourceKey<Enchantment> DEPTH_STRIDER = null;

    public static final ResourceKey<Enchantment> FROST_WALKER = null;

    public static final ResourceKey<Enchantment> SOUL_SPEED = null;

    public static final ResourceKey<Enchantment> SWIFT_SNEAK = null;

    public static final ResourceKey<Enchantment> SHARPNESS = null;

    public static final ResourceKey<Enchantment> EFFICIENCY = null;

    // Pumpkin divergence: real values. A ResourceKey is a pair of names; these are
    // vanilla's, and BaseReusableItem reads them at class-init.
    public static final ResourceKey<Enchantment> SILK_TOUCH = pumpkinKey("silk_touch");

    public static final ResourceKey<Enchantment> UNBREAKING = pumpkinKey("unbreaking");

    private static ResourceKey<Enchantment> pumpkinKey(String name) {
        return ResourceKey.create(
                ResourceKey.createRegistryKey(net.minecraft.resources.Identifier.fromNamespaceAndPath("minecraft", "enchantment")),
                net.minecraft.resources.Identifier.fromNamespaceAndPath("minecraft", name));
    }

    public static final ResourceKey<Enchantment> FORTUNE = null;

    public static final ResourceKey<Enchantment> FLAME = null;

    public Enchantments() {
    }

}
