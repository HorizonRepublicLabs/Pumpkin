package net.neoforged.neoforge.event.level;

import net.minecraft.core.BlockPos;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.storage.ServerLevelData;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.ICancellableEvent;
import dev.pumpkin.shim.Unimplemented;

public abstract class LevelEvent extends Event {

    public LevelEvent(LevelAccessor level) {
    }

    public LevelAccessor getLevel() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/level/LevelEvent.getLevel:()Lnet/minecraft/world/level/LevelAccessor;");
    }

    public static class Load extends LevelEvent {

        public Load(LevelAccessor level) {
        }

        public Load() {
        }
    }

    public static class Unload extends LevelEvent {

        public Unload(LevelAccessor level) {
        }

        public Unload() {
        }
    }

    public static class Save extends LevelEvent {

        public Save(LevelAccessor level) {
        }

        public Save() {
        }
    }

    public static class CreateSpawnPosition extends LevelEvent implements ICancellableEvent {

        public CreateSpawnPosition(LevelAccessor level, ServerLevelData settings) {
        }

        public CreateSpawnPosition() {
        }
    }

    public static class PotentialSpawns extends LevelEvent implements ICancellableEvent {

        public PotentialSpawns(LevelAccessor level, MobCategory category, BlockPos pos, WeightedList<MobSpawnSettings.SpawnerData> oldList) {
        }

        public BlockPos getPos() {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/level/LevelEvent$PotentialSpawns.getPos:()Lnet/minecraft/core/BlockPos;");
        }

        public PotentialSpawns() {
        }
    }

    public LevelEvent() {
    }
}
