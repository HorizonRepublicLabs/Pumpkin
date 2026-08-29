package net.minecraft.world.level.portal;

import java.util.Set;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Relative;
import net.minecraft.world.phys.Vec3;

public record TeleportTransition(ServerLevel newLevel, Vec3 position, Vec3 deltaMovement, float yRot, float xRot, boolean missingRespawnBlock, boolean asPassenger, Set<Relative> relatives, TeleportTransition.PostTeleportTransition postTeleportTransition) {

    public TeleportTransition(ServerLevel newLevel, Vec3 pos, Vec3 speed, float yRot, float xRot, TeleportTransition.PostTeleportTransition postTeleportTransition) {
        this((ServerLevel) null, (Vec3) null, (Vec3) null, (float) 0.0F, (float) 0.0F, (boolean) false, (boolean) false, (Set<Relative>) null, (TeleportTransition.PostTeleportTransition) null);
    }

    public TeleportTransition(ServerLevel newLevel, Vec3 pos, Vec3 speed, float yRot, float xRot, Set<Relative> relatives, TeleportTransition.PostTeleportTransition postTeleportTransition) {
        this((ServerLevel) null, (Vec3) null, (Vec3) null, (float) 0.0F, (float) 0.0F, (boolean) false, (boolean) false, (Set<Relative>) null, (TeleportTransition.PostTeleportTransition) null);
    }

    public interface PostTeleportTransition {

        void onTransition(final Entity entity);
    }
}
