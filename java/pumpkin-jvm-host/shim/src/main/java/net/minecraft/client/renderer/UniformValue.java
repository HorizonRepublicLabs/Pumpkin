package net.minecraft.client.renderer;

import com.mojang.blaze3d.buffers.Std140Builder;
import com.mojang.blaze3d.buffers.Std140SizeCalculator;
import net.minecraft.util.StringRepresentable;
import org.joml.Matrix4fc;
import org.joml.Vector2fc;
import org.joml.Vector3fc;
import org.joml.Vector3ic;
import org.joml.Vector4fc;
import dev.pumpkin.shim.Unimplemented;

public interface UniformValue {

    void writeTo(Std140Builder builder);

    void addSize(Std140SizeCalculator calculator);

    UniformValue.Type type();

    record FloatUniform(float value) implements UniformValue {

        public void writeTo(Std140Builder builder) {
            throw Unimplemented.forMember("net/minecraft/client/renderer/UniformValue$FloatUniform.writeTo:(Lcom/mojang/blaze3d/buffers/Std140Builder;)V");
        }

        public void addSize(Std140SizeCalculator calculator) {
            throw Unimplemented.forMember("net/minecraft/client/renderer/UniformValue$FloatUniform.addSize:(Lcom/mojang/blaze3d/buffers/Std140SizeCalculator;)V");
        }

        public UniformValue.Type type() {
            throw Unimplemented.forMember("net/minecraft/client/renderer/UniformValue$FloatUniform.type:()Lnet/minecraft/client/renderer/UniformValue$Type;");
        }
    }

    record IVec3Uniform(Vector3ic value) implements UniformValue {

        public void writeTo(Std140Builder builder) {
            throw Unimplemented.forMember("net/minecraft/client/renderer/UniformValue$IVec3Uniform.writeTo:(Lcom/mojang/blaze3d/buffers/Std140Builder;)V");
        }

        public void addSize(Std140SizeCalculator calculator) {
            throw Unimplemented.forMember("net/minecraft/client/renderer/UniformValue$IVec3Uniform.addSize:(Lcom/mojang/blaze3d/buffers/Std140SizeCalculator;)V");
        }

        public UniformValue.Type type() {
            throw Unimplemented.forMember("net/minecraft/client/renderer/UniformValue$IVec3Uniform.type:()Lnet/minecraft/client/renderer/UniformValue$Type;");
        }
    }

    record IntUniform(int value) implements UniformValue {

        public void writeTo(Std140Builder builder) {
            throw Unimplemented.forMember("net/minecraft/client/renderer/UniformValue$IntUniform.writeTo:(Lcom/mojang/blaze3d/buffers/Std140Builder;)V");
        }

        public void addSize(Std140SizeCalculator calculator) {
            throw Unimplemented.forMember("net/minecraft/client/renderer/UniformValue$IntUniform.addSize:(Lcom/mojang/blaze3d/buffers/Std140SizeCalculator;)V");
        }

        public UniformValue.Type type() {
            throw Unimplemented.forMember("net/minecraft/client/renderer/UniformValue$IntUniform.type:()Lnet/minecraft/client/renderer/UniformValue$Type;");
        }
    }

    record Matrix4x4Uniform(Matrix4fc value) implements UniformValue {

        public void writeTo(Std140Builder builder) {
            throw Unimplemented.forMember("net/minecraft/client/renderer/UniformValue$Matrix4x4Uniform.writeTo:(Lcom/mojang/blaze3d/buffers/Std140Builder;)V");
        }

        public void addSize(Std140SizeCalculator calculator) {
            throw Unimplemented.forMember("net/minecraft/client/renderer/UniformValue$Matrix4x4Uniform.addSize:(Lcom/mojang/blaze3d/buffers/Std140SizeCalculator;)V");
        }

        public UniformValue.Type type() {
            throw Unimplemented.forMember("net/minecraft/client/renderer/UniformValue$Matrix4x4Uniform.type:()Lnet/minecraft/client/renderer/UniformValue$Type;");
        }
    }

    enum Type implements StringRepresentable {

        INT,
        IVEC3,
        FLOAT,
        VEC2,
        VEC3,
        VEC4,
        MATRIX4X4;

        public String getSerializedName() {
            throw Unimplemented.forMember("net/minecraft/client/renderer/UniformValue$Type.getSerializedName:()Ljava/lang/String;");
        }
    }

    record Vec2Uniform(Vector2fc value) implements UniformValue {

        public void writeTo(Std140Builder builder) {
            throw Unimplemented.forMember("net/minecraft/client/renderer/UniformValue$Vec2Uniform.writeTo:(Lcom/mojang/blaze3d/buffers/Std140Builder;)V");
        }

        public void addSize(Std140SizeCalculator calculator) {
            throw Unimplemented.forMember("net/minecraft/client/renderer/UniformValue$Vec2Uniform.addSize:(Lcom/mojang/blaze3d/buffers/Std140SizeCalculator;)V");
        }

        public UniformValue.Type type() {
            throw Unimplemented.forMember("net/minecraft/client/renderer/UniformValue$Vec2Uniform.type:()Lnet/minecraft/client/renderer/UniformValue$Type;");
        }
    }

    record Vec3Uniform(Vector3fc value) implements UniformValue {

        public void writeTo(Std140Builder builder) {
            throw Unimplemented.forMember("net/minecraft/client/renderer/UniformValue$Vec3Uniform.writeTo:(Lcom/mojang/blaze3d/buffers/Std140Builder;)V");
        }

        public void addSize(Std140SizeCalculator calculator) {
            throw Unimplemented.forMember("net/minecraft/client/renderer/UniformValue$Vec3Uniform.addSize:(Lcom/mojang/blaze3d/buffers/Std140SizeCalculator;)V");
        }

        public UniformValue.Type type() {
            throw Unimplemented.forMember("net/minecraft/client/renderer/UniformValue$Vec3Uniform.type:()Lnet/minecraft/client/renderer/UniformValue$Type;");
        }
    }

    record Vec4Uniform(Vector4fc value) implements UniformValue {

        public void writeTo(Std140Builder builder) {
            throw Unimplemented.forMember("net/minecraft/client/renderer/UniformValue$Vec4Uniform.writeTo:(Lcom/mojang/blaze3d/buffers/Std140Builder;)V");
        }

        public void addSize(Std140SizeCalculator calculator) {
            throw Unimplemented.forMember("net/minecraft/client/renderer/UniformValue$Vec4Uniform.addSize:(Lcom/mojang/blaze3d/buffers/Std140SizeCalculator;)V");
        }

        public UniformValue.Type type() {
            throw Unimplemented.forMember("net/minecraft/client/renderer/UniformValue$Vec4Uniform.type:()Lnet/minecraft/client/renderer/UniformValue$Type;");
        }
    }
}
