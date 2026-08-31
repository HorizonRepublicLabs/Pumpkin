package net.minecraft.world.phys;

import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.core.Vec3i;
import org.joml.Vector3fc;
import dev.pumpkin.shim.Unimplemented;

public class Vec3 implements Position {

    // Pumpkin divergence: a vector really carries its coordinates; the pruner had
    // zeroed them because the originals were assigned in a stripped constructor.
    public final double x;

    public final double y;

    public final double z;

    public static Vec3 atCenterOf(Vec3i pos) {
        throw Unimplemented.forMember("net/minecraft/world/phys/Vec3.atCenterOf:(Lnet/minecraft/core/Vec3i;)Lnet/minecraft/world/phys/Vec3;");
    }

    public Vec3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vec3(Vector3fc vec) {
        this.x = 0.0;
        this.y = 0.0;
        this.z = 0.0;
    }

    public Vec3(Vec3i vec) {
        this.x = 0.0;
        this.y = 0.0;
        this.z = 0.0;
    }

    public Vec3 add(double value) {
        throw Unimplemented.forMember("net/minecraft/world/phys/Vec3.add:(D)Lnet/minecraft/world/phys/Vec3;");
    }

    public Vec3 add(Vec3 vec) {
        throw Unimplemented.forMember("net/minecraft/world/phys/Vec3.add:(Lnet/minecraft/world/phys/Vec3;)Lnet/minecraft/world/phys/Vec3;");
    }

    public Vec3 add(double x, double y, double z) {
        throw Unimplemented.forMember("net/minecraft/world/phys/Vec3.add:(DDD)Lnet/minecraft/world/phys/Vec3;");
    }

    public double length() {
        throw Unimplemented.forMember("net/minecraft/world/phys/Vec3.length:()D");
    }

    public boolean equals(Object o) {
        throw Unimplemented.forMember("net/minecraft/world/phys/Vec3.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/minecraft/world/phys/Vec3.hashCode:()I");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/world/phys/Vec3.toString:()Ljava/lang/String;");
    }

    public double get(Direction.Axis axis) {
        throw Unimplemented.forMember("net/minecraft/world/phys/Vec3.get:(Lnet/minecraft/core/Direction$Axis;)D");
    }

    public Vec3 with(Direction.Axis axis, double value) {
        throw Unimplemented.forMember("net/minecraft/world/phys/Vec3.with:(Lnet/minecraft/core/Direction$Axis;D)Lnet/minecraft/world/phys/Vec3;");
    }

    // Pumpkin divergence: vanilla bodies -- the record-style accessors over the fields.
    public final double x() {
        return x;
    }

    public final double y() {
        return y;
    }

    public final double z() {
        return z;
    }

    public Vec3() {
        this.x = 0.0;
        this.y = 0.0;
        this.z = 0.0;
    }
}
