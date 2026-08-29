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
        throw Unimplemented.forMember("net/neoforged/neoforge/event/level/LevelEvent.<init>:(Lnet/minecraft/world/level/LevelAccessor;)V");
    }

    public LevelAccessor getLevel() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/level/LevelEvent.getLevel:()Lnet/minecraft/world/level/LevelAccessor;");
    }

    public static class Load extends LevelEvent {

        public Load(LevelAccessor level) {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/level/LevelEvent$Load.<init>:(Lnet/minecraft/world/level/LevelAccessor;)V");
        }

        protected Load() {
        }
    }

    public static class Unload extends LevelEvent {

        public Unload(LevelAccessor level) {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/level/LevelEvent$Unload.<init>:(Lnet/minecraft/world/level/LevelAccessor;)V");
        }

        protected Unload() {
        }
    }

    public static class Save extends LevelEvent {

        public Save(LevelAccessor level) {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/level/LevelEvent$Save.<init>:(Lnet/minecraft/world/level/LevelAccessor;)V");
        }

        protected Save() {
        }
    }

    public static class CreateSpawnPosition extends LevelEvent implements ICancellableEvent {

        public CreateSpawnPosition(LevelAccessor level, ServerLevelData settings) {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/level/LevelEvent$CreateSpawnPosition.<init>:(Lnet/minecraft/world/level/LevelAccessor;Lnet/minecraft/world/level/storage/ServerLevelData;)V");
        }

        protected CreateSpawnPosition() {
        }
    }

    public static class PotentialSpawns extends LevelEvent implements ICancellableEvent {

        public PotentialSpawns(LevelAccessor level, MobCategory category, BlockPos pos, WeightedList<MobSpawnSettings.SpawnerData> oldList) {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/level/LevelEvent$PotentialSpawns.<init>:(Lnet/minecraft/world/level/LevelAccessor;Lnet/minecraft/world/entity/MobCategory;Lnet/minecraft/core/BlockPos;Lnet/minecraft/util/random/WeightedList;)V");
        }

        public BlockPos getPos() {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/level/LevelEvent$PotentialSpawns.getPos:()Lnet/minecraft/core/BlockPos;");
        }

        protected PotentialSpawns() {
        }
    }

    protected LevelEvent() {
    }
}
