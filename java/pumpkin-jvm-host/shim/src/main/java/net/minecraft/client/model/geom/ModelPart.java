package net.minecraft.client.model.geom;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import net.minecraft.core.Direction;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import dev.pumpkin.shim.Unimplemented;

public final class ModelPart {

    public float y;

    public boolean visible;

    private final List<ModelPart.Cube> cubes = null;

    private final Map<String, ModelPart> children = null;

    public ModelPart(List<ModelPart.Cube> cubes, Map<String, ModelPart> children) {
    }

    public void resetPose() {
        throw Unimplemented.forMember("net/minecraft/client/model/geom/ModelPart.resetPose:()V");
    }

    public ModelPart getChild(String name) {
        throw Unimplemented.forMember("net/minecraft/client/model/geom/ModelPart.getChild:(Ljava/lang/String;)Lnet/minecraft/client/model/geom/ModelPart;");
    }

    public void setRotation(float xRot, float yRot, float zRot) {
        throw Unimplemented.forMember("net/minecraft/client/model/geom/ModelPart.setRotation:(FFF)V");
    }

    public void render(PoseStack poseStack, VertexConsumer buffer, int lightCoords, int overlayCoords) {
        throw Unimplemented.forMember("net/minecraft/client/model/geom/ModelPart.render:(Lcom/mojang/blaze3d/vertex/PoseStack;Lcom/mojang/blaze3d/vertex/VertexConsumer;II)V");
    }

    public void render(PoseStack poseStack, VertexConsumer buffer, int lightCoords, int overlayCoords, int color) {
        throw Unimplemented.forMember("net/minecraft/client/model/geom/ModelPart.render:(Lcom/mojang/blaze3d/vertex/PoseStack;Lcom/mojang/blaze3d/vertex/VertexConsumer;III)V");
    }

    public void rotateBy(Quaternionf rotation) {
        throw Unimplemented.forMember("net/minecraft/client/model/geom/ModelPart.rotateBy:(Lorg/joml/Quaternionf;)V");
    }

    public void getExtentsForGui(PoseStack poseStack, Consumer<Vector3fc> output) {
        throw Unimplemented.forMember("net/minecraft/client/model/geom/ModelPart.getExtentsForGui:(Lcom/mojang/blaze3d/vertex/PoseStack;Ljava/util/function/Consumer;)V");
    }

    public void visit(PoseStack poseStack, ModelPart.Visitor visitor) {
        throw Unimplemented.forMember("net/minecraft/client/model/geom/ModelPart.visit:(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/model/geom/ModelPart$Visitor;)V");
    }

    public void translateAndRotate(PoseStack poseStack) {
        throw Unimplemented.forMember("net/minecraft/client/model/geom/ModelPart.translateAndRotate:(Lcom/mojang/blaze3d/vertex/PoseStack;)V");
    }

    public boolean isEmpty() {
        throw Unimplemented.forMember("net/minecraft/client/model/geom/ModelPart.isEmpty:()Z");
    }

    public void offsetPos(Vector3f offset) {
        throw Unimplemented.forMember("net/minecraft/client/model/geom/ModelPart.offsetPos:(Lorg/joml/Vector3f;)V");
    }

    public void offsetScale(Vector3f offset) {
        throw Unimplemented.forMember("net/minecraft/client/model/geom/ModelPart.offsetScale:(Lorg/joml/Vector3f;)V");
    }

    public List<ModelPart> getAllParts() {
        throw Unimplemented.forMember("net/minecraft/client/model/geom/ModelPart.getAllParts:()Ljava/util/List;");
    }

    public static class Cube {

        public final ModelPart.Polygon[] polygons = null;

        public Cube(int xTexOffs, int yTexOffs, float minX, float minY, float minZ, float width, float height, float depth, float growX, float growY, float growZ, boolean mirror, float xTexSize, float yTexSize, Set<Direction> visibleFaces) {
        }

        public Cube() {
        }
    }

    public record Polygon(ModelPart.Vertex[] vertices, Vector3fc normal) {

        public Polygon(ModelPart.Vertex[] vertices, float u0, float v0, float u1, float v1, float xTexSize, float yTexSize, boolean mirror, Direction facing) {
            this((ModelPart.Vertex[]) null, (Vector3fc) null);
        }
    }

    public record Vertex(float x, float y, float z, float u, float v) {

        public float worldX() {
            throw Unimplemented.forMember("net/minecraft/client/model/geom/ModelPart$Vertex.worldX:()F");
        }

        public float worldY() {
            throw Unimplemented.forMember("net/minecraft/client/model/geom/ModelPart$Vertex.worldY:()F");
        }

        public float worldZ() {
            throw Unimplemented.forMember("net/minecraft/client/model/geom/ModelPart$Vertex.worldZ:()F");
        }
    }

    public interface Visitor {

        void visit(final PoseStack.Pose pose, final String partPath, final int cubeIndex, final ModelPart.Cube cube);
    }

    public ModelPart() {
    }
}
