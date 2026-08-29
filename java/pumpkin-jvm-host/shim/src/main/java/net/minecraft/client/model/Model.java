package net.minecraft.client.model;

import java.util.function.Function;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Unit;
import dev.pumpkin.shim.Unimplemented;

public abstract class Model<S> {

    public Model(ModelPart root, Function<Identifier, RenderType> renderType) {
    }

    public final RenderType renderType(Identifier texture) {
        throw Unimplemented.forMember("net/minecraft/client/model/Model.renderType:(Lnet/minecraft/resources/Identifier;)Lnet/minecraft/client/renderer/rendertype/RenderType;");
    }

    public static class Simple extends Model<Unit> {

        public Simple(ModelPart root, Function<Identifier, RenderType> renderType) {
        }

        public Simple() {
        }
    }

    public Model() {
    }
}
