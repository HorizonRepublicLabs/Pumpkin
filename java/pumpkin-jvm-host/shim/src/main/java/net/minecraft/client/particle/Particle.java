package net.minecraft.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Stubs;
import dev.pumpkin.shim.Unimplemented;

public abstract class Particle {

    protected final ClientLevel level = null;

    protected double xo;

    protected double yo;

    protected double zo;

    protected double x;

    protected double y;

    protected double z;

    protected float bbWidth;

    protected float bbHeight;

    protected final RandomSource random = Stubs.of(RandomSource.class, "net/minecraft/util/RandomSource");

    protected int age;

    protected int lifetime;

    protected float gravity;

    public Particle(ClientLevel level, double x, double y, double z) {
    }

    public Particle(ClientLevel level, double x, double y, double z, double xa, double ya, double za) {
    }

    public Particle setPower(float power) {
        throw Unimplemented.forMember("net/minecraft/client/particle/Particle.setPower:(F)Lnet/minecraft/client/particle/Particle;");
    }

    public Particle scale(float scale) {
        throw Unimplemented.forMember("net/minecraft/client/particle/Particle.scale:(F)Lnet/minecraft/client/particle/Particle;");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/client/particle/Particle.tick:()V");
    }

    public abstract ParticleRenderType getGroup();

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/client/particle/Particle.toString:()Ljava/lang/String;");
    }

    public void remove() {
        throw Unimplemented.forMember("net/minecraft/client/particle/Particle.remove:()V");
    }

    protected int getLightCoords(float a) {
        throw Unimplemented.forMember("net/minecraft/client/particle/Particle.getLightCoords:(F)I");
    }

    public AABB getBoundingBox() {
        throw Unimplemented.forMember("net/minecraft/client/particle/Particle.getBoundingBox:()Lnet/minecraft/world/phys/AABB;");
    }

    public void setBoundingBox(AABB bb) {
        throw Unimplemented.forMember("net/minecraft/client/particle/Particle.setBoundingBox:(Lnet/minecraft/world/phys/AABB;)V");
    }

    public Vec3 getPos() {
        throw Unimplemented.forMember("net/minecraft/client/particle/Particle.getPos:()Lnet/minecraft/world/phys/Vec3;");
    }

    public record LifetimeAlpha(float startAlpha, float endAlpha, float startAtNormalizedAge, float endAtNormalizedAge) {
    }

    public Particle() {
    }
}
