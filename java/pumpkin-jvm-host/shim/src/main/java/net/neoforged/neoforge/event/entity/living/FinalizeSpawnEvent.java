package net.neoforged.neoforge.event.entity.living;

import com.mojang.datafixers.util.Either;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.bus.api.ICancellableEvent;
import dev.pumpkin.shim.Unimplemented;

public class FinalizeSpawnEvent extends MobSpawnEvent implements ICancellableEvent {

    public FinalizeSpawnEvent(Mob entity, ServerLevelAccessor level, double x, double y, double z, DifficultyInstance difficulty, EntitySpawnReason spawnType, SpawnGroupData spawnData, Either<BlockEntity, Entity> spawner) {
    }

    public DifficultyInstance getDifficulty() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/living/FinalizeSpawnEvent.getDifficulty:()Lnet/minecraft/world/DifficultyInstance;");
    }

    public void setDifficulty(DifficultyInstance inst) {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/living/FinalizeSpawnEvent.setDifficulty:(Lnet/minecraft/world/DifficultyInstance;)V");
    }

    public FinalizeSpawnEvent() {
    }
}
