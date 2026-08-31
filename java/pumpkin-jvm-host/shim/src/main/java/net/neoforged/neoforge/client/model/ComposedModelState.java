package net.neoforged.neoforge.client.model;

import com.mojang.math.Transformation;
import net.minecraft.client.renderer.block.dispatch.ModelState;
import net.minecraft.core.Direction;
import org.joml.Matrix4fc;
import dev.pumpkin.shim.Unimplemented;

public record ComposedModelState(ModelState parent, Transformation transformation) implements ModelState {

    public Matrix4fc faceTransformation(Direction side) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/model/ComposedModelState.faceTransformation:(Lnet/minecraft/core/Direction;)Lorg/joml/Matrix4fc;");
    }

    public Matrix4fc inverseFaceTransformation(Direction side) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/model/ComposedModelState.inverseFaceTransformation:(Lnet/minecraft/core/Direction;)Lorg/joml/Matrix4fc;");
    }
}
