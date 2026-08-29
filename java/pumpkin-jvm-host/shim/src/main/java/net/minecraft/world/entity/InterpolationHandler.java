package net.minecraft.world.entity;

import java.util.function.Consumer;
import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public class InterpolationHandler {

    public InterpolationHandler(Entity entity) {
        throw Unimplemented.forMember("net/minecraft/world/entity/InterpolationHandler.<init>:(Lnet/minecraft/world/entity/Entity;)V");
    }

    public InterpolationHandler(Entity entity, int interpolationSteps) {
        throw Unimplemented.forMember("net/minecraft/world/entity/InterpolationHandler.<init>:(Lnet/minecraft/world/entity/Entity;I)V");
    }

    public InterpolationHandler(Entity entity, Consumer<InterpolationHandler> onInterpolationStart) {
        throw Unimplemented.forMember("net/minecraft/world/entity/InterpolationHandler.<init>:(Lnet/minecraft/world/entity/Entity;Ljava/util/function/Consumer;)V");
    }

    public InterpolationHandler(Entity entity, int interpolationSteps, Consumer<InterpolationHandler> onInterpolationStart) {
        throw Unimplemented.forMember("net/minecraft/world/entity/InterpolationHandler.<init>:(Lnet/minecraft/world/entity/Entity;ILjava/util/function/Consumer;)V");
    }

    public Vec3 position() {
        throw Unimplemented.forMember("net/minecraft/world/entity/InterpolationHandler.position:()Lnet/minecraft/world/phys/Vec3;");
    }

    private static class InterpolationData {

        private InterpolationData(int steps, Vec3 position, float yRot, float xRot) {
            throw Unimplemented.forMember("net/minecraft/world/entity/InterpolationHandler$InterpolationData.<init>:(ILnet/minecraft/world/phys/Vec3;FF)V");
        }

        protected InterpolationData() {
        }
    }

    protected InterpolationHandler() {
    }
}
