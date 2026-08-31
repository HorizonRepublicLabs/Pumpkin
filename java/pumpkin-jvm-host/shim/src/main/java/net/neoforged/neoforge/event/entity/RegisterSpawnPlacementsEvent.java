package net.neoforged.neoforge.event.entity;

import java.util.Map;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacementType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.Event;
import net.neoforged.fml.event.IModBusEvent;
import dev.pumpkin.shim.Unimplemented;

public class RegisterSpawnPlacementsEvent extends Event implements IModBusEvent {

    public RegisterSpawnPlacementsEvent(Map<EntityType<?>, MergedSpawnPredicate<?>> map) {
    }

    public <T extends Entity> void register(EntityType<T> entityType, SpawnPlacements.SpawnPredicate<T> predicate) {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/RegisterSpawnPlacementsEvent.register:(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/entity/SpawnPlacements$SpawnPredicate;)V");
    }

    public <T extends Entity> void register(EntityType<T> entityType, SpawnPlacements.SpawnPredicate<T> predicate, Operation operation) {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/RegisterSpawnPlacementsEvent.register:(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/entity/SpawnPlacements$SpawnPredicate;Lnet/neoforged/neoforge/event/entity/RegisterSpawnPlacementsEvent$Operation;)V");
    }

    public <T extends Entity> void register(EntityType<T> entityType, SpawnPlacementType placementType, Heightmap.Types heightmap, SpawnPlacements.SpawnPredicate<T> predicate, Operation operation) {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/RegisterSpawnPlacementsEvent.register:(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/entity/SpawnPlacementType;Lnet/minecraft/world/level/levelgen/Heightmap$Types;Lnet/minecraft/world/entity/SpawnPlacements$SpawnPredicate;Lnet/neoforged/neoforge/event/entity/RegisterSpawnPlacementsEvent$Operation;)V");
    }

    public enum Operation {

        AND, OR, REPLACE
    }

    public static class MergedSpawnPredicate<T extends Entity> {

        public MergedSpawnPredicate(SpawnPlacements.SpawnPredicate<T> originalPredicate, SpawnPlacementType spawnType, Heightmap.Types heightmapType) {
        }

        public SpawnPlacements.SpawnPredicate<T> build() {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/RegisterSpawnPlacementsEvent$MergedSpawnPredicate.build:()Lnet/minecraft/world/entity/SpawnPlacements$SpawnPredicate;");
        }

        public MergedSpawnPredicate() {
        }
    }

    public RegisterSpawnPlacementsEvent() {
    }
}
