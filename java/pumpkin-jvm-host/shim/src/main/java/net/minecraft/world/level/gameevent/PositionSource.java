package net.minecraft.world.level.gameevent;

import java.util.Optional;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public interface PositionSource {

    Optional<Vec3> getPosition(final Level level);

    PositionSourceType<? extends PositionSource> getType();
}
