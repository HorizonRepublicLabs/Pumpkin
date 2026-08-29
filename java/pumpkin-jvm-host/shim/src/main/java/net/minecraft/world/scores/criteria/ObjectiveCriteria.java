package net.minecraft.world.scores.criteria;

import net.minecraft.util.StringRepresentable;
import dev.pumpkin.shim.Unimplemented;

public class ObjectiveCriteria {

    protected ObjectiveCriteria(String name) {
        throw Unimplemented.forMember("net/minecraft/world/scores/criteria/ObjectiveCriteria.<init>:(Ljava/lang/String;)V");
    }

    protected ObjectiveCriteria(String name, boolean readOnly, ObjectiveCriteria.RenderType renderType) {
        throw Unimplemented.forMember("net/minecraft/world/scores/criteria/ObjectiveCriteria.<init>:(Ljava/lang/String;ZLnet/minecraft/world/scores/criteria/ObjectiveCriteria$RenderType;)V");
    }

    public String getName() {
        throw Unimplemented.forMember("net/minecraft/world/scores/criteria/ObjectiveCriteria.getName:()Ljava/lang/String;");
    }

    public enum RenderType implements StringRepresentable {

        INTEGER, HEARTS;

        public String getId() {
            throw Unimplemented.forMember("net/minecraft/world/scores/criteria/ObjectiveCriteria$RenderType.getId:()Ljava/lang/String;");
        }

        public String getSerializedName() {
            throw Unimplemented.forMember("net/minecraft/world/scores/criteria/ObjectiveCriteria$RenderType.getSerializedName:()Ljava/lang/String;");
        }

        public static ObjectiveCriteria.RenderType byId(String key) {
            throw Unimplemented.forMember("net/minecraft/world/scores/criteria/ObjectiveCriteria$RenderType.byId:(Ljava/lang/String;)Lnet/minecraft/world/scores/criteria/ObjectiveCriteria$RenderType;");
        }
    }

    protected ObjectiveCriteria() {
    }
}
