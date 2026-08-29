package net.minecraft.world.entity;

import java.util.function.Consumer;
import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public class InterpolationHandler {

    public InterpolationHandler(Entity entity) {
    }

    public InterpolationHandler(Entity entity, int interpolationSteps) {
    }

    public InterpolationHandler(Entity entity, Consumer<InterpolationHandler> onInterpolationStart) {
    }

    public InterpolationHandler(Entity entity, int interpolationSteps, Consumer<InterpolationHandler> onInterpolationStart) {
    }

    public Vec3 position() {
        throw Unimplemented.forMember("net/minecraft/world/entity/InterpolationHandler.position:()Lnet/minecraft/world/phys/Vec3;");
    }

    private static class InterpolationData {

        private InterpolationData(int steps, Vec3 position, float yRot, float xRot) {
        }

        protected InterpolationData() {
        }
    }

    public InterpolationHandler() {
    }
}
