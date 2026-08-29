package net.minecraft.world.level;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public interface Explosion {

    ServerLevel level();

    Explosion.BlockInteraction getBlockInteraction();

    LivingEntity getIndirectSourceEntity();

    Entity getDirectSourceEntity();

    float radius();

    Vec3 center();

    boolean canTriggerBlocks();

    boolean shouldAffectBlocklikeEntities();

    enum BlockInteraction {

        KEEP, DESTROY, DESTROY_WITH_DECAY, TRIGGER_BLOCK;

        public boolean shouldAffectBlocklikeEntities() {
            throw Unimplemented.forMember("net/minecraft/world/level/Explosion$BlockInteraction.shouldAffectBlocklikeEntities:()Z");
        }
    }
}
