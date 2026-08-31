package net.minecraft.world.entity.ai.control;

import net.minecraft.world.entity.Mob;
import dev.pumpkin.shim.Unimplemented;

public class MoveControl<T extends Mob> implements Control {

    public MoveControl(T mob) {
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/control/MoveControl.tick:()V");
    }

    protected enum Operation {

        WAIT, MOVE_TO, STRAFE, JUMPING
    }

    public MoveControl() {
    }
}
