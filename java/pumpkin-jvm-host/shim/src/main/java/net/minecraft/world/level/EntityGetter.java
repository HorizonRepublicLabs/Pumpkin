package net.minecraft.world.level;

import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.VoxelShape;
import dev.pumpkin.shim.Unimplemented;

public interface EntityGetter {

    List<Entity> getEntities(Entity except, AABB bb, Predicate<? super Entity> selector);

    <T extends Entity> List<T> getEntities(final EntityTypeTest<Entity, T> type, final AABB bb, final Predicate<? super T> selector);

    default <T extends Entity> List<T> getEntitiesOfClass(Class<T> baseClass, AABB bb, Predicate<? super T> selector) {
        throw Unimplemented.forMember("net/minecraft/world/level/EntityGetter.getEntitiesOfClass:(Ljava/lang/Class;Lnet/minecraft/world/phys/AABB;Ljava/util/function/Predicate;)Ljava/util/List;");
    }

    List<? extends Player> players();

    default List<Entity> getEntities(Entity except, AABB bb) {
        throw Unimplemented.forMember("net/minecraft/world/level/EntityGetter.getEntities:(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/phys/AABB;)Ljava/util/List;");
    }

    default boolean isUnobstructed(Entity source, VoxelShape shape) {
        throw Unimplemented.forMember("net/minecraft/world/level/EntityGetter.isUnobstructed:(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/phys/shapes/VoxelShape;)Z");
    }

    default <T extends Entity> List<T> getEntitiesOfClass(Class<T> baseClass, AABB bb) {
        throw Unimplemented.forMember("net/minecraft/world/level/EntityGetter.getEntitiesOfClass:(Ljava/lang/Class;Lnet/minecraft/world/phys/AABB;)Ljava/util/List;");
    }

    default List<VoxelShape> getEntityCollisions(Entity source, AABB testArea) {
        throw Unimplemented.forMember("net/minecraft/world/level/EntityGetter.getEntityCollisions:(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/phys/AABB;)Ljava/util/List;");
    }

    default Player getNearestPlayer(double x, double y, double z, double range, Predicate<Entity> predicate) {
        throw Unimplemented.forMember("net/minecraft/world/level/EntityGetter.getNearestPlayer:(DDDDLjava/util/function/Predicate;)Lnet/minecraft/world/entity/player/Player;");
    }

    default Player getNearestPlayer(Entity source, double maxDist) {
        throw Unimplemented.forMember("net/minecraft/world/level/EntityGetter.getNearestPlayer:(Lnet/minecraft/world/entity/Entity;D)Lnet/minecraft/world/entity/player/Player;");
    }

    default Player getNearestPlayer(double x, double y, double z, double maxDist, boolean filterOutCreative) {
        throw Unimplemented.forMember("net/minecraft/world/level/EntityGetter.getNearestPlayer:(DDDDZ)Lnet/minecraft/world/entity/player/Player;");
    }

    default Player getPlayerByUUID(UUID uuid) {
        throw Unimplemented.forMember("net/minecraft/world/level/EntityGetter.getPlayerByUUID:(Ljava/util/UUID;)Lnet/minecraft/world/entity/player/Player;");
    }
}
