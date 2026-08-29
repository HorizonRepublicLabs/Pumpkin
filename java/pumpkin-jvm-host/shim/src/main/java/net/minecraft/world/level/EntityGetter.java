package net.minecraft.world.level;

import java.util.List;
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

    List<? extends Player> players();

    default List<VoxelShape> getEntityCollisions(Entity source, AABB testArea) {
        throw Unimplemented.forMember("net/minecraft/world/level/EntityGetter.getEntityCollisions:(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/phys/AABB;)Ljava/util/List;");
    }
}
