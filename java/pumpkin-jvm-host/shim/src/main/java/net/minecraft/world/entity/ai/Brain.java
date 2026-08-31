package net.minecraft.world.entity.ai;

import java.util.Collection;
import java.util.List;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.memory.MemoryMap;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.schedule.Activity;
import dev.pumpkin.shim.Unimplemented;

public class Brain<E extends LivingEntity> {

    protected Brain(Collection<? extends MemoryModuleType<?>> memoryTypes, Collection<? extends SensorType<? extends Sensor<? super E>>> sensorTypes, List<ActivityData<E>> activities, MemoryMap memories, RandomSource randomSource) {
    }

    public Brain() {
    }

    public void forEach(Brain.Visitor visitor) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/Brain.forEach:(Lnet/minecraft/world/entity/ai/Brain$Visitor;)V");
    }

    public boolean isActive(Activity activity) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/Brain.isActive:(Lnet/minecraft/world/entity/schedule/Activity;)Z");
    }

    public void tick(ServerLevel level, E body) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/Brain.tick:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/LivingEntity;)V");
    }

    public interface ActivitySupplier<E extends LivingEntity> {

        List<ActivityData<E>> createActivities(E body);
    }

    public record Packed(MemoryMap memories) {
    }

    public static final class Provider<E extends LivingEntity> {

        private Provider(Collection<? extends MemoryModuleType<?>> memoryTypes, Collection<? extends SensorType<? extends Sensor<? super E>>> sensorTypes, Brain.ActivitySupplier<E> activities) {
        }

        public Provider() {
        }
    }

    public interface Visitor {

        <U> void acceptEmpty(MemoryModuleType<U> type);

        <U> void accept(MemoryModuleType<U> type, U value);

        <U> void accept(MemoryModuleType<U> type, U value, long timeToLive);
    }
}
