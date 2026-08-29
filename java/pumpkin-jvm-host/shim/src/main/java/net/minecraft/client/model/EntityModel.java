package net.minecraft.client.model;

import java.util.function.Function;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.resources.Identifier;

public abstract class EntityModel<T extends EntityRenderState> extends Model<T> {

    protected EntityModel(ModelPart root) {
    }

    protected EntityModel(ModelPart root, Function<Identifier, RenderType> renderType) {
    }

    public EntityModel() {
    }
}
