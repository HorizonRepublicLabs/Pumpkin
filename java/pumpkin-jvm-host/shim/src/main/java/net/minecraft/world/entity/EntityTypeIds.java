package net.minecraft.world.entity;

import net.minecraft.resources.ResourceKey;
import dev.pumpkin.shim.Unimplemented;

public class EntityTypeIds {

    public static final ResourceKey<EntityType<?>> BOGGED = create("bogged");

    public static final ResourceKey<EntityType<?>> CREEPER = create("creeper");

    public static final ResourceKey<EntityType<?>> ENDERMAN = create("enderman");

    public static final ResourceKey<EntityType<?>> PARCHED = create("parched");

    public static final ResourceKey<EntityType<?>> SKELETON = create("skeleton");

    public static final ResourceKey<EntityType<?>> STRAY = create("stray");

    public static final ResourceKey<EntityType<?>> WITHER_SKELETON = create("wither_skeleton");

    // Pumpkin divergence: vanilla body -- a key under the entity_type registry.
    private static ResourceKey<EntityType<?>> create(String name) {
        return ResourceKey.create(net.minecraft.core.registries.Registries.ENTITY_TYPE,
                net.minecraft.resources.Identifier.fromNamespaceAndPath("minecraft", name));
    }

    public EntityTypeIds() {
    }

    // Pumpkin divergence: no throwing initializer -- every key above is real.
}
