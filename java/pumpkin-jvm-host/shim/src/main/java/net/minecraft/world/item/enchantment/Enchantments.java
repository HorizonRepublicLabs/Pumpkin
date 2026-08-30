package net.minecraft.world.item.enchantment;

import net.minecraft.resources.ResourceKey;
import dev.pumpkin.shim.Unimplemented;

public class Enchantments {

    // Pumpkin divergence: real values. A ResourceKey is a pair of names; these are
    // vanilla's, and BaseReusableItem reads them at class-init.
    public static final ResourceKey<Enchantment> SILK_TOUCH = pumpkinKey("silk_touch");

    public static final ResourceKey<Enchantment> UNBREAKING = pumpkinKey("unbreaking");

    private static ResourceKey<Enchantment> pumpkinKey(String name) {
        return ResourceKey.create(
                ResourceKey.createRegistryKey(net.minecraft.resources.Identifier.fromNamespaceAndPath("minecraft", "enchantment")),
                net.minecraft.resources.Identifier.fromNamespaceAndPath("minecraft", name));
    }

    public Enchantments() {
    }

}
