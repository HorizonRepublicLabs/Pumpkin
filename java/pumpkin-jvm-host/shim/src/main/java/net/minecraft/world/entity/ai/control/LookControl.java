package net.minecraft.world.entity.ai.control;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public class LookControl implements Control {

    public LookControl(Mob mob) {
    }

    public void setLookAt(Vec3 vec) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/control/LookControl.setLookAt:(Lnet/minecraft/world/phys/Vec3;)V");
    }

    public void setLookAt(Entity target) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/control/LookControl.setLookAt:(Lnet/minecraft/world/entity/Entity;)V");
    }

    public void setLookAt(Entity target, float yMaxRotSpeed, float xMaxRotAngle) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/control/LookControl.setLookAt:(Lnet/minecraft/world/entity/Entity;FF)V");
    }

    public void setLookAt(double x, double y, double z) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/control/LookControl.setLookAt:(DDD)V");
    }

    public void setLookAt(double x, double y, double z, float yMaxRotSpeed, float xMaxRotAngle) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/control/LookControl.setLookAt:(DDDFF)V");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/control/LookControl.tick:()V");
    }

    public LookControl() {
    }
}
