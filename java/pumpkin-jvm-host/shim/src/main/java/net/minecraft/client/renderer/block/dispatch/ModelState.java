package net.minecraft.client.renderer.block.dispatch;

import com.mojang.math.Transformation;
import net.minecraft.core.Direction;
import org.joml.Matrix4fc;
import net.neoforged.neoforge.client.extensions.ModelStateExtension;
import dev.pumpkin.shim.Unimplemented;

public interface ModelState extends ModelStateExtension {

    default Transformation transformation() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/block/dispatch/ModelState.transformation:()Lcom/mojang/math/Transformation;");
    }

    default Matrix4fc faceTransformation(Direction face) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/block/dispatch/ModelState.faceTransformation:(Lnet/minecraft/core/Direction;)Lorg/joml/Matrix4fc;");
    }

    default Matrix4fc inverseFaceTransformation(Direction face) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/block/dispatch/ModelState.inverseFaceTransformation:(Lnet/minecraft/core/Direction;)Lorg/joml/Matrix4fc;");
    }
}
