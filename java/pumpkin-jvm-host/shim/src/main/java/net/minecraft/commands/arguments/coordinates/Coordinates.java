package net.minecraft.commands.arguments.coordinates;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public interface Coordinates {

    Vec3 getPosition(CommandSourceStack sender);

    Vec2 getRotation(CommandSourceStack sender);

    boolean isXRelative();

    boolean isYRelative();

    boolean isZRelative();
}
