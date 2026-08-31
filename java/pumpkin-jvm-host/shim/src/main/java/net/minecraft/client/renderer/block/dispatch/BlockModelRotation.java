package net.minecraft.client.renderer.block.dispatch;

import com.mojang.math.OctahedralGroup;
import com.mojang.math.Transformation;
import net.minecraft.core.Direction;
import org.joml.Matrix4fc;
import dev.pumpkin.shim.Unimplemented;

public class BlockModelRotation implements ModelState {

    public static final BlockModelRotation IDENTITY = null;

    private BlockModelRotation(OctahedralGroup orientation) {
    }

    public Transformation transformation() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/block/dispatch/BlockModelRotation.transformation:()Lcom/mojang/math/Transformation;");
    }

    public static BlockModelRotation get(OctahedralGroup group) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/block/dispatch/BlockModelRotation.get:(Lcom/mojang/math/OctahedralGroup;)Lnet/minecraft/client/renderer/block/dispatch/BlockModelRotation;");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/block/dispatch/BlockModelRotation.toString:()Ljava/lang/String;");
    }

    public record WithUvLock(BlockModelRotation parent) implements ModelState {

        public Transformation transformation() {
            throw Unimplemented.forMember("net/minecraft/client/renderer/block/dispatch/BlockModelRotation$WithUvLock.transformation:()Lcom/mojang/math/Transformation;");
        }

        public Matrix4fc faceTransformation(Direction face) {
            throw Unimplemented.forMember("net/minecraft/client/renderer/block/dispatch/BlockModelRotation$WithUvLock.faceTransformation:(Lnet/minecraft/core/Direction;)Lorg/joml/Matrix4fc;");
        }

        public Matrix4fc inverseFaceTransformation(Direction face) {
            throw Unimplemented.forMember("net/minecraft/client/renderer/block/dispatch/BlockModelRotation$WithUvLock.inverseFaceTransformation:(Lnet/minecraft/core/Direction;)Lorg/joml/Matrix4fc;");
        }

        public String toString() {
            throw Unimplemented.forMember("net/minecraft/client/renderer/block/dispatch/BlockModelRotation$WithUvLock.toString:()Ljava/lang/String;");
        }
    }

    public BlockModelRotation() {
    }
}
