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

    // Pumpkin divergence: a vanilla stand-in carries its own name; nothing constructs
    // entities through it, and anything deeper fails loudly on the member it needs.
    @SuppressWarnings({"unchecked", "rawtypes"})
    private static <T extends Entity> EntityType<T> pumpkinVanilla(String name) {
        EntityType type = new EntityType();
        type.pumpkinVanillaName = name;
        return type;
    }


    public static final EntityType<ArmorStand> ARMOR_STAND = pumpkinVanilla("armor_stand");

    public static final EntityType<Bogged> BOGGED = pumpkinVanilla("bogged");

    public static final EntityType<Creeper> CREEPER = pumpkinVanilla("creeper");

    public static final EntityType<EnderMan> ENDERMAN = pumpkinVanilla("enderman");

    public static final EntityType<ItemEntity> ITEM = pumpkinVanilla("item");

    public static final EntityType<LightningBolt> LIGHTNING_BOLT = pumpkinVanilla("lightning_bolt");

    public static final EntityType<Parched> PARCHED = pumpkinVanilla("parched");

    public static final EntityType<Skeleton> SKELETON = pumpkinVanilla("skeleton");

    public static final EntityType<Stray> STRAY = pumpkinVanilla("stray");

    public static final EntityType<WitherSkeleton> WITHER_SKELETON = pumpkinVanilla("wither_skeleton");

    public static final EntityType<Player> PLAYER = pumpkinVanilla("player");

    private static <T extends Entity> EntityType<T> register(ResourceKey<EntityType<?>> id, EntityType.Builder<T> builder) {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityTypes.register:(Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/world/entity/EntityType$Builder;)Lnet/minecraft/world/entity/EntityType;");
    }

    public EntityTypes() {
    }

    // Pumpkin divergence: no throwing initializer -- stand-ins above are real.
}
