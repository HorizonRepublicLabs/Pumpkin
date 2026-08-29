package net.minecraft.client.model;

import net.minecraft.client.renderer.entity.state.EntityRenderState;
import dev.pumpkin.shim.Unimplemented;

public abstract class EntityModel<T extends EntityRenderState> extends Model<T> {

    static {
        if (true) {
            throw Unimplemented.forMember("net/minecraft/client/model/EntityModel");
        }
    }
}
