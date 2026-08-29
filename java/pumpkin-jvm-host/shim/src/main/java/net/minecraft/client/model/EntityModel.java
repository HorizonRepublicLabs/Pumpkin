package net.minecraft.client.model;

import java.util.function.Function;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.resources.Identifier;
import dev.pumpkin.shim.Unimplemented;

public abstract class EntityModel<T extends EntityRenderState> extends Model<T> {

    protected EntityModel(ModelPart root) {
        throw Unimplemented.forMember("net/minecraft/client/model/EntityModel.<init>:(Lnet/minecraft/client/model/geom/ModelPart;)V");
    }

    protected EntityModel(ModelPart root, Function<Identifier, RenderType> renderType) {
        throw Unimplemented.forMember("net/minecraft/client/model/EntityModel.<init>:(Lnet/minecraft/client/model/geom/ModelPart;Ljava/util/function/Function;)V");
    }

    protected EntityModel() {
    }
}
