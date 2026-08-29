package net.minecraft.world.entity;

import java.util.function.Consumer;
import dev.pumpkin.shim.Unimplemented;

public enum InsideBlockEffectType {

    FREEZE, CLEAR_FREEZE, FIRE_IGNITE, LAVA_IGNITE, EXTINGUISH;

    public Consumer<Entity> effect() {
        throw Unimplemented.forMember("net/minecraft/world/entity/InsideBlockEffectType.effect:()Ljava/util/function/Consumer;");
    }
}
