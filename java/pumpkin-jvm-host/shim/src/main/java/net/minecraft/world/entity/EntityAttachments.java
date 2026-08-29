package net.minecraft.world.entity;

import java.util.List;
import java.util.Map;
import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public class EntityAttachments {

    private EntityAttachments(Map<EntityAttachment, List<Vec3>> attachments) {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityAttachments.<init>:(Ljava/util/Map;)V");
    }

    public Vec3 get(EntityAttachment attachment, int index, float rotY) {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityAttachments.get:(Lnet/minecraft/world/entity/EntityAttachment;IF)Lnet/minecraft/world/phys/Vec3;");
    }

    public static class Builder {

        protected Builder() {
            throw Unimplemented.forMember("net/minecraft/world/entity/EntityAttachments$Builder.<init>:()V");
        }

        public EntityAttachments build(float width, float height) {
            throw Unimplemented.forMember("net/minecraft/world/entity/EntityAttachments$Builder.build:(FF)Lnet/minecraft/world/entity/EntityAttachments;");
        }
    }

    protected EntityAttachments() {
    }
}
