package net.minecraft.client.renderer.gizmos;

import java.util.List;
import net.minecraft.gizmos.GizmoPrimitives;
import net.minecraft.gizmos.TextGizmo;
import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public class DrawableGizmoPrimitives implements GizmoPrimitives {

    public void addPoint(Vec3 pos, int color, float size) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/gizmos/DrawableGizmoPrimitives.addPoint:(Lnet/minecraft/world/phys/Vec3;IF)V");
    }

    public void addLine(Vec3 start, Vec3 end, int color, float width) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/gizmos/DrawableGizmoPrimitives.addLine:(Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/world/phys/Vec3;IF)V");
    }

    public void addTriangleFan(Vec3[] points, int color) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/gizmos/DrawableGizmoPrimitives.addTriangleFan:([Lnet/minecraft/world/phys/Vec3;I)V");
    }

    public void addQuad(Vec3 a, Vec3 b, Vec3 c, Vec3 d, int color) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/gizmos/DrawableGizmoPrimitives.addQuad:(Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/world/phys/Vec3;I)V");
    }

    public void addText(Vec3 pos, String text, TextGizmo.Style style) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/gizmos/DrawableGizmoPrimitives.addText:(Lnet/minecraft/world/phys/Vec3;Ljava/lang/String;Lnet/minecraft/gizmos/TextGizmo$Style;)V");
    }

    public record Group(boolean opaque, List<DrawableGizmoPrimitives.Line> lines, List<DrawableGizmoPrimitives.Quad> quads, List<DrawableGizmoPrimitives.TriangleFan> triangleFans, List<DrawableGizmoPrimitives.Text> texts, List<DrawableGizmoPrimitives.Point> points) {

        private Group(boolean opaque) {
            this((boolean) false, (List<DrawableGizmoPrimitives.Line>) null, (List<DrawableGizmoPrimitives.Quad>) null, (List<DrawableGizmoPrimitives.TriangleFan>) null, (List<DrawableGizmoPrimitives.Text>) null, (List<DrawableGizmoPrimitives.Point>) null);
            throw Unimplemented.forMember("net/minecraft/client/renderer/gizmos/DrawableGizmoPrimitives$Group.<init>:(Z)V");
        }
    }

    public record Line(Vec3 start, Vec3 end, int color, float width) {
    }

    public record Point(Vec3 pos, int color, float size) {
    }

    public record Quad(Vec3 a, Vec3 b, Vec3 c, Vec3 d, int color) {
    }

    public record Text(Vec3 pos, String text, TextGizmo.Style style) {
    }

    public record TriangleFan(Vec3[] points, int color) {
    }

    protected DrawableGizmoPrimitives() {
    }
}
