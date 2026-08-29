package net.minecraft.world.entity;

import com.mojang.datafixers.util.Either;
import java.util.UUID;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;

public interface Leashable {

    Leashable.LeashData getLeashData();

    void setLeashData(Leashable.LeashData leashData);

    final class LeashData {

        private LeashData(Either<UUID, BlockPos> delayedLeashInfo) {
        }

        private LeashData(Entity entity) {
        }

        private LeashData(int entityId) {
        }

        protected LeashData() {
        }
    }

    record Wrench(Vec3 force, double torque) {
    }
}
