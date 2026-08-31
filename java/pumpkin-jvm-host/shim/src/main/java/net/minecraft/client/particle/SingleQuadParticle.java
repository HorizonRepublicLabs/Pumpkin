package net.minecraft.client.particle;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.state.level.QuadParticleRenderState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.Identifier;
import org.joml.Quaternionf;
import dev.pumpkin.shim.Unimplemented;

public abstract class SingleQuadParticle extends Particle {

    protected float quadSize;

    protected float rCol;

    protected float gCol;

    protected float bCol;

    protected float alpha;

    protected TextureAtlasSprite sprite;

    public SingleQuadParticle(ClientLevel level, double x, double y, double z, TextureAtlasSprite sprite) {
    }

    public SingleQuadParticle(ClientLevel level, double x, double y, double z, double xa, double ya, double za, TextureAtlasSprite sprite) {
    }

    public void extract(QuadParticleRenderState particleTypeRenderState, Camera camera, float partialTickTime) {
        throw Unimplemented.forMember("net/minecraft/client/particle/SingleQuadParticle.extract:(Lnet/minecraft/client/renderer/state/level/QuadParticleRenderState;Lnet/minecraft/client/Camera;F)V");
    }

    public float getQuadSize(float a) {
        throw Unimplemented.forMember("net/minecraft/client/particle/SingleQuadParticle.getQuadSize:(F)F");
    }

    public Particle scale(float scale) {
        throw Unimplemented.forMember("net/minecraft/client/particle/SingleQuadParticle.scale:(F)Lnet/minecraft/client/particle/Particle;");
    }

    public ParticleRenderType getGroup() {
        throw Unimplemented.forMember("net/minecraft/client/particle/SingleQuadParticle.getGroup:()Lnet/minecraft/client/particle/ParticleRenderType;");
    }

    protected float getU0() {
        throw Unimplemented.forMember("net/minecraft/client/particle/SingleQuadParticle.getU0:()F");
    }

    protected float getU1() {
        throw Unimplemented.forMember("net/minecraft/client/particle/SingleQuadParticle.getU1:()F");
    }

    protected float getV0() {
        throw Unimplemented.forMember("net/minecraft/client/particle/SingleQuadParticle.getV0:()F");
    }

    protected float getV1() {
        throw Unimplemented.forMember("net/minecraft/client/particle/SingleQuadParticle.getV1:()F");
    }

    protected abstract SingleQuadParticle.Layer getLayer();

    public void setColor(float r, float g, float b) {
        throw Unimplemented.forMember("net/minecraft/client/particle/SingleQuadParticle.setColor:(FFF)V");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/client/particle/SingleQuadParticle.toString:()Ljava/lang/String;");
    }

    public interface FacingCameraMode {

        SingleQuadParticle.FacingCameraMode LOOKAT_XYZ = null;

        void setRotation(final Quaternionf target, final Camera camera, final float partialTickTime);
    }

    public record Layer(boolean translucent, Identifier textureAtlasLocation, RenderPipeline pipeline) {

        public static final SingleQuadParticle.Layer TRANSLUCENT = null;

        public static SingleQuadParticle.Layer bySprite(TextureAtlasSprite sprite) {
            throw Unimplemented.forMember("net/minecraft/client/particle/SingleQuadParticle$Layer.bySprite:(Lnet/minecraft/client/renderer/texture/TextureAtlasSprite;)Lnet/minecraft/client/particle/SingleQuadParticle$Layer;");
        }
    }

    public SingleQuadParticle() {
    }
}
