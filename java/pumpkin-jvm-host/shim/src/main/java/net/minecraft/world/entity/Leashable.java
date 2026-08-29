package net.minecraft.world.entity;

import com.mojang.datafixers.util.Either;
import java.util.UUID;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public interface Leashable {

    Leashable.LeashData getLeashData();

    void setLeashData(Leashable.LeashData leashData);

    final class LeashData {

        private LeashData(Either<UUID, BlockPos> delayedLeashInfo) {
            throw Unimplemented.forMember("net/minecraft/world/entity/Leashable$LeashData.<init>:(Lcom/mojang/datafixers/util/Either;)V");
        }

        private LeashData(Entity entity) {
            throw Unimplemented.forMember("net/minecraft/world/entity/Leashable$LeashData.<init>:(Lnet/minecraft/world/entity/Entity;)V");
        }

        private LeashData(int entityId) {
            throw Unimplemented.forMember("net/minecraft/world/entity/Leashable$LeashData.<init>:(I)V");
        }

        protected LeashData() {
        }
    }

    record Wrench(Vec3 force, double torque) {
    }
}
