package net.minecraft.gizmos;

import java.util.OptionalDouble;
import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public record TextGizmo(Vec3 pos, String text, TextGizmo.Style style) implements Gizmo {

    public void emit(GizmoPrimitives primitives, float alphaMultiplier) {
        throw Unimplemented.forMember("net/minecraft/gizmos/TextGizmo.emit:(Lnet/minecraft/gizmos/GizmoPrimitives;F)V");
    }

    public record Style(int color, float scale, OptionalDouble adjustLeft) {
    }
}
