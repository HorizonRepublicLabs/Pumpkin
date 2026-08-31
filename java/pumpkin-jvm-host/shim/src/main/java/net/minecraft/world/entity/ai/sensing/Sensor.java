package net.minecraft.world.entity.ai.sensing;

import java.util.Set;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import dev.pumpkin.shim.Unimplemented;

public abstract class Sensor<E extends LivingEntity> {

    public Sensor(int scanRate) {
    }

    public Sensor() {
    }

    public final void tick(ServerLevel level, E body) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/sensing/Sensor.tick:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/LivingEntity;)V");
    }

    protected abstract void doTick(final ServerLevel level, final E body);

    public abstract Set<MemoryModuleType<?>> requires();
}
