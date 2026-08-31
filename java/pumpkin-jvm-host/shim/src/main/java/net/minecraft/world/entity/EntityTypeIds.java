package net.minecraft.world.entity;

import net.minecraft.resources.ResourceKey;
import dev.pumpkin.shim.Unimplemented;

public class EntityTypeIds {

    public static final ResourceKey<EntityType<?>> BOGGED = null;

    public static final ResourceKey<EntityType<?>> CREEPER = null;

    public static final ResourceKey<EntityType<?>> ENDERMAN = null;

    public static final ResourceKey<EntityType<?>> PARCHED = null;

    public static final ResourceKey<EntityType<?>> SKELETON = null;

    public static final ResourceKey<EntityType<?>> STRAY = null;

    public static final ResourceKey<EntityType<?>> WITHER_SKELETON = null;

    private static ResourceKey<EntityType<?>> create(String name) {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityTypeIds.create:(Ljava/lang/String;)Lnet/minecraft/resources/ResourceKey;");
    }

    public EntityTypeIds() {
    }

    static {
        if (true) {
            throw Unimplemented.forMember("net/minecraft/world/entity/EntityTypeIds");
        }
    }
}
