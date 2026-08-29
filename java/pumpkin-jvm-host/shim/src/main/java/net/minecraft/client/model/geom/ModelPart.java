package net.minecraft.client.model.geom;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.core.Direction;
import org.joml.Vector3fc;
import dev.pumpkin.shim.Unimplemented;

public final class ModelPart {

    public ModelPart(List<ModelPart.Cube> cubes, Map<String, ModelPart> children) {
        throw Unimplemented.forMember("net/minecraft/client/model/geom/ModelPart.<init>:(Ljava/util/List;Ljava/util/Map;)V");
    }

    public void render(PoseStack poseStack, VertexConsumer buffer, int lightCoords, int overlayCoords) {
        throw Unimplemented.forMember("net/minecraft/client/model/geom/ModelPart.render:(Lcom/mojang/blaze3d/vertex/PoseStack;Lcom/mojang/blaze3d/vertex/VertexConsumer;II)V");
    }

    public void visit(PoseStack poseStack, ModelPart.Visitor visitor) {
        throw Unimplemented.forMember("net/minecraft/client/model/geom/ModelPart.visit:(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/model/geom/ModelPart$Visitor;)V");
    }

    public boolean isEmpty() {
        throw Unimplemented.forMember("net/minecraft/client/model/geom/ModelPart.isEmpty:()Z");
    }

    public static class Cube {

        public Cube(int xTexOffs, int yTexOffs, float minX, float minY, float minZ, float width, float height, float depth, float growX, float growY, float growZ, boolean mirror, float xTexSize, float yTexSize, Set<Direction> visibleFaces) {
            throw Unimplemented.forMember("net/minecraft/client/model/geom/ModelPart$Cube.<init>:(IIFFFFFFFFFZFFLjava/util/Set;)V");
        }

        protected Cube() {
        }
    }

    public record Polygon(ModelPart.Vertex[] vertices, Vector3fc normal) {

        public Polygon(ModelPart.Vertex[] vertices, float u0, float v0, float u1, float v1, float xTexSize, float yTexSize, boolean mirror, Direction facing) {
            this((ModelPart.Vertex[]) null, (Vector3fc) null);
            throw Unimplemented.forMember("net/minecraft/client/model/geom/ModelPart$Polygon.<init>:([Lnet/minecraft/client/model/geom/ModelPart$Vertex;FFFFFFZLnet/minecraft/core/Direction;)V");
        }
    }

    public record Vertex(float x, float y, float z, float u, float v) {
    }

    public interface Visitor {

        void visit(final PoseStack.Pose pose, final String partPath, final int cubeIndex, final ModelPart.Cube cube);
    }

    protected ModelPart() {
    }
}
