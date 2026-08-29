package com.mojang.blaze3d.vertex;

import com.mojang.math.Transformation;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4fc;
import org.joml.Quaternionfc;
import dev.pumpkin.shim.Unimplemented;

public class PoseStack {

    public PoseStack() {
    }

    public void translate(double xo, double yo, double zo) {
        throw Unimplemented.forMember("com/mojang/blaze3d/vertex/PoseStack.translate:(DDD)V");
    }

    public void translate(float xo, float yo, float zo) {
        throw Unimplemented.forMember("com/mojang/blaze3d/vertex/PoseStack.translate:(FFF)V");
    }

    public void translate(Vec3 offset) {
        throw Unimplemented.forMember("com/mojang/blaze3d/vertex/PoseStack.translate:(Lnet/minecraft/world/phys/Vec3;)V");
    }

    public void scale(float xScale, float yScale, float zScale) {
        throw Unimplemented.forMember("com/mojang/blaze3d/vertex/PoseStack.scale:(FFF)V");
    }

    public void mulPose(Quaternionfc by) {
        throw Unimplemented.forMember("com/mojang/blaze3d/vertex/PoseStack.mulPose:(Lorg/joml/Quaternionfc;)V");
    }

    public void pushPose() {
        throw Unimplemented.forMember("com/mojang/blaze3d/vertex/PoseStack.pushPose:()V");
    }

    public void popPose() {
        throw Unimplemented.forMember("com/mojang/blaze3d/vertex/PoseStack.popPose:()V");
    }

    public boolean isEmpty() {
        throw Unimplemented.forMember("com/mojang/blaze3d/vertex/PoseStack.isEmpty:()Z");
    }

    public void mulPose(Matrix4fc matrix) {
        throw Unimplemented.forMember("com/mojang/blaze3d/vertex/PoseStack.mulPose:(Lorg/joml/Matrix4fc;)V");
    }

    public void mulPose(Transformation matrix) {
        throw Unimplemented.forMember("com/mojang/blaze3d/vertex/PoseStack.mulPose:(Lcom/mojang/math/Transformation;)V");
    }

    public static final class Pose {

        public void set(PoseStack.Pose pose) {
            throw Unimplemented.forMember("com/mojang/blaze3d/vertex/PoseStack$Pose.set:(Lcom/mojang/blaze3d/vertex/PoseStack$Pose;)V");
        }

        public PoseStack.Pose copy() {
            throw Unimplemented.forMember("com/mojang/blaze3d/vertex/PoseStack$Pose.copy:()Lcom/mojang/blaze3d/vertex/PoseStack$Pose;");
        }

        public Pose() {
        }
    }
}
