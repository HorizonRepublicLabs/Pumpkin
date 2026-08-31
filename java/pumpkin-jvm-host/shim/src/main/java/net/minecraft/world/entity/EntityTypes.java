package net.minecraft.world.entity;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.skeleton.Bogged;
import net.minecraft.world.entity.monster.skeleton.Parched;
import net.minecraft.world.entity.monster.skeleton.Skeleton;
import net.minecraft.world.entity.monster.skeleton.Stray;
import net.minecraft.world.entity.monster.skeleton.WitherSkeleton;
import net.minecraft.world.entity.player.Player;
import dev.pumpkin.shim.Unimplemented;

public class EntityTypes {

    public static final EntityType<ArmorStand> ARMOR_STAND = null;

    public static final EntityType<Bogged> BOGGED = null;

    public static final EntityType<Creeper> CREEPER = null;

    public static final EntityType<EnderMan> ENDERMAN = null;

    public static final EntityType<ItemEntity> ITEM = null;

    public static final EntityType<LightningBolt> LIGHTNING_BOLT = null;

    public static final EntityType<Parched> PARCHED = null;

    public static final EntityType<Skeleton> SKELETON = null;

    public static final EntityType<Stray> STRAY = null;

    public static final EntityType<WitherSkeleton> WITHER_SKELETON = null;

    public static final EntityType<Player> PLAYER = null;

    private static <T extends Entity> EntityType<T> register(ResourceKey<EntityType<?>> id, EntityType.Builder<T> builder) {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityTypes.register:(Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/world/entity/EntityType$Builder;)Lnet/minecraft/world/entity/EntityType;");
    }

    public EntityTypes() {
    }

    static {
        if (true) {
            throw Unimplemented.forMember("net/minecraft/world/entity/EntityTypes");
        }
    }
}
