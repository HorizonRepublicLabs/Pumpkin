package com.mojang.blaze3d.vertex;

import org.joml.Matrix4fc;
import org.joml.Vector3fc;
import net.neoforged.neoforge.client.extensions.IVertexConsumerExtension;
import dev.pumpkin.shim.Unimplemented;

public interface VertexConsumer extends IVertexConsumerExtension {

    VertexConsumer addVertex(float x, float y, float z);

    VertexConsumer setColor(int r, int g, int b, int a);

    VertexConsumer setColor(int color);

    VertexConsumer setUv(float u, float v);

    VertexConsumer setUv1(int u, int v);

    VertexConsumer setUv2(int u, int v);

    VertexConsumer setNormal(float x, float y, float z);

    VertexConsumer setLineWidth(float width);

    default void addVertex(float x, float y, float z, int color, float u, float v, int overlayCoords, int lightCoords, float nx, float ny, float nz) {
        throw Unimplemented.forMember("com/mojang/blaze3d/vertex/VertexConsumer.addVertex:(FFFIFFIIFFF)V");
    }

    default VertexConsumer setColor(float r, float g, float b, float a) {
        throw Unimplemented.forMember("com/mojang/blaze3d/vertex/VertexConsumer.setColor:(FFFF)Lcom/mojang/blaze3d/vertex/VertexConsumer;");
    }

    default VertexConsumer setLight(int packedLightCoords) {
        throw Unimplemented.forMember("com/mojang/blaze3d/vertex/VertexConsumer.setLight:(I)Lcom/mojang/blaze3d/vertex/VertexConsumer;");
    }

    default VertexConsumer addVertex(Vector3fc position) {
        throw Unimplemented.forMember("com/mojang/blaze3d/vertex/VertexConsumer.addVertex:(Lorg/joml/Vector3fc;)Lcom/mojang/blaze3d/vertex/VertexConsumer;");
    }

    default VertexConsumer addVertex(PoseStack.Pose pose, Vector3fc position) {
        throw Unimplemented.forMember("com/mojang/blaze3d/vertex/VertexConsumer.addVertex:(Lcom/mojang/blaze3d/vertex/PoseStack$Pose;Lorg/joml/Vector3fc;)Lcom/mojang/blaze3d/vertex/VertexConsumer;");
    }

    default VertexConsumer addVertex(PoseStack.Pose pose, float x, float y, float z) {
        throw Unimplemented.forMember("com/mojang/blaze3d/vertex/VertexConsumer.addVertex:(Lcom/mojang/blaze3d/vertex/PoseStack$Pose;FFF)Lcom/mojang/blaze3d/vertex/VertexConsumer;");
    }

    default VertexConsumer addVertex(Matrix4fc pose, float x, float y, float z) {
        throw Unimplemented.forMember("com/mojang/blaze3d/vertex/VertexConsumer.addVertex:(Lorg/joml/Matrix4fc;FFF)Lcom/mojang/blaze3d/vertex/VertexConsumer;");
    }

    default VertexConsumer setNormal(PoseStack.Pose pose, float x, float y, float z) {
        throw Unimplemented.forMember("com/mojang/blaze3d/vertex/VertexConsumer.setNormal:(Lcom/mojang/blaze3d/vertex/PoseStack$Pose;FFF)Lcom/mojang/blaze3d/vertex/VertexConsumer;");
    }

    default VertexConsumer setNormal(PoseStack.Pose pose, Vector3fc normal) {
        throw Unimplemented.forMember("com/mojang/blaze3d/vertex/VertexConsumer.setNormal:(Lcom/mojang/blaze3d/vertex/PoseStack$Pose;Lorg/joml/Vector3fc;)Lcom/mojang/blaze3d/vertex/VertexConsumer;");
    }
}
