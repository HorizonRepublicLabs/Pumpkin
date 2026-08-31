package net.minecraft.commands.arguments.selector;

import java.util.List;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import net.minecraft.advancements.predicates.MinMaxBounds;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class EntitySelector {

    public EntitySelector(int maxResults, boolean includesEntities, boolean worldLimited, List<Predicate<Entity>> contextFreePredicates, MinMaxBounds.Doubles range, Function<Vec3, Vec3> position, AABB aabb, BiConsumer<Vec3, List<? extends Entity>> order, boolean currentEntity, String playerName, UUID entityUUID, EntityType<?> type, boolean usesSelector) {
    }

    public EntitySelector() {
    }
}
