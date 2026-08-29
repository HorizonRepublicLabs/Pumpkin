package net.minecraft.world.entity;

import java.util.function.Consumer;
import dev.pumpkin.shim.Unimplemented;

public interface InsideBlockEffectApplier {

    void apply(InsideBlockEffectType type);

    void runBefore(InsideBlockEffectType type, Consumer<Entity> effect);

    void runAfter(InsideBlockEffectType type, Consumer<Entity> effect);

    class StepBasedCollector implements InsideBlockEffectApplier {

        public void apply(InsideBlockEffectType type) {
            throw Unimplemented.forMember("net/minecraft/world/entity/InsideBlockEffectApplier$StepBasedCollector.apply:(Lnet/minecraft/world/entity/InsideBlockEffectType;)V");
        }

        public void runBefore(InsideBlockEffectType type, Consumer<Entity> effect) {
            throw Unimplemented.forMember("net/minecraft/world/entity/InsideBlockEffectApplier$StepBasedCollector.runBefore:(Lnet/minecraft/world/entity/InsideBlockEffectType;Ljava/util/function/Consumer;)V");
        }

        public void runAfter(InsideBlockEffectType type, Consumer<Entity> effect) {
            throw Unimplemented.forMember("net/minecraft/world/entity/InsideBlockEffectApplier$StepBasedCollector.runAfter:(Lnet/minecraft/world/entity/InsideBlockEffectType;Ljava/util/function/Consumer;)V");
        }

        protected StepBasedCollector() {
        }
    }
}
