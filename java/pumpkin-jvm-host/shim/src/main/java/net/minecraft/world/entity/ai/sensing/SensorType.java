package net.minecraft.world.entity.ai.sensing;

import java.util.function.Supplier;
import dev.pumpkin.shim.Unimplemented;

public class SensorType<U extends Sensor<?>> {

    public SensorType(Supplier<U> factory) {
    }

    public U create() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/sensing/SensorType.create:()Lnet/minecraft/world/entity/ai/sensing/Sensor;");
    }

    private static <U extends Sensor<?>> SensorType<U> register(String name, Supplier<U> factory) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/sensing/SensorType.register:(Ljava/lang/String;Ljava/util/function/Supplier;)Lnet/minecraft/world/entity/ai/sensing/SensorType;");
    }

    public SensorType() {
    }
}
