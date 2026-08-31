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
    private static <T extends Entity> EntityType<T> pumpkinVanilla(String name, MobCategory category) {
        EntityType type = new EntityType();
        type.pumpkinVanillaName = name;
        type.pumpkinCategory = category;
        type.canSpawnFarFromPlayer = category == MobCategory.CREATURE || category == MobCategory.MISC;
        return type;
    }


    public static final EntityType<ArmorStand> ARMOR_STAND = pumpkinVanilla("armor_stand", MobCategory.MISC);

    public static final EntityType<Bogged> BOGGED = pumpkinVanillaSized("bogged", MobCategory.MONSTER, 0.6F, 1.99F);

    // Pumpkin divergence: vanilla fact -- the mob's real hitbox size.
    private static <T extends Entity> EntityType<T> pumpkinVanillaSized(String name, MobCategory category, float width, float height) {
        EntityType<T> type = pumpkinVanilla(name, category);
        type.pumpkinDimensions = EntityDimensions.scalable(width, height);
        return type;
    }

    public static final EntityType<Creeper> CREEPER = pumpkinVanillaSized("creeper", MobCategory.MONSTER, 0.6F, 1.7F);

    public static final EntityType<EnderMan> ENDERMAN = pumpkinVanillaSized("enderman", MobCategory.MONSTER, 0.6F, 2.9F);

    public static final EntityType<ItemEntity> ITEM = pumpkinVanilla("item", MobCategory.MISC);

    public static final EntityType<LightningBolt> LIGHTNING_BOLT = pumpkinVanilla("lightning_bolt", MobCategory.MISC);

    public static final EntityType<Parched> PARCHED = pumpkinVanillaSized("parched", MobCategory.MONSTER, 0.6F, 1.99F);

    public static final EntityType<Skeleton> SKELETON = pumpkinVanillaSized("skeleton", MobCategory.MONSTER, 0.6F, 1.99F);

    public static final EntityType<Stray> STRAY = pumpkinVanillaSized("stray", MobCategory.MONSTER, 0.6F, 1.99F);

    public static final EntityType<WitherSkeleton> WITHER_SKELETON = pumpkinVanillaFireImmune("wither_skeleton", MobCategory.MONSTER);
    static {
        WITHER_SKELETON.pumpkinDimensions = EntityDimensions.scalable(0.7F, 2.4F);
    }

    // Pumpkin divergence: vanilla fact -- these mobs are fire immune.
    private static <T extends Entity> EntityType<T> pumpkinVanillaFireImmune(String name, MobCategory category) {
        EntityType<T> type = pumpkinVanilla(name, category);
        type.fireImmune = true;
        return type;
    }

    public static final EntityType<Player> PLAYER = pumpkinVanilla("player", MobCategory.MISC);

    private static <T extends Entity> EntityType<T> register(ResourceKey<EntityType<?>> id, EntityType.Builder<T> builder) {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityTypes.register:(Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/world/entity/EntityType$Builder;)Lnet/minecraft/world/entity/EntityType;");
    }

    public EntityTypes() {
    }

    // Pumpkin divergence: no throwing initializer -- stand-ins above are real.
}
