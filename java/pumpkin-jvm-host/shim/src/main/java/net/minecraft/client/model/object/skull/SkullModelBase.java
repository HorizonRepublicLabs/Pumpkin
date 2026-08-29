package net.minecraft.client.model.object.skull;

import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import dev.pumpkin.shim.Unimplemented;

public abstract class SkullModelBase extends Model<SkullModelBase.State> {

    public SkullModelBase(ModelPart root) {
        throw Unimplemented.forMember("net/minecraft/client/model/object/skull/SkullModelBase.<init>:(Lnet/minecraft/client/model/geom/ModelPart;)V");
    }

    public static class State {

        public State() {
        }
    }

    public SkullModelBase() {
    }
}
