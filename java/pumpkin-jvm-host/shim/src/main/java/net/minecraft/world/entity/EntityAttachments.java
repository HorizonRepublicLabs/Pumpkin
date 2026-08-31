package net.minecraft.world.entity;

import java.util.List;
import java.util.Map;
import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public class EntityAttachments {

    // Pumpkin divergence: NeoForge access-transforms this field public; the map is
    // real, and an EntityAttachments built without points carries the empty map.
    public final Map<EntityAttachment, List<Vec3>> attachments;

    private EntityAttachments(Map<EntityAttachment, List<Vec3>> attachments) {
        this.attachments = attachments;
    }

    public static EntityAttachments pumpkinEmpty() {
        return new EntityAttachments(Map.of());
    }

    public Vec3 get(EntityAttachment attachment, int index, float rotY) {
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityAttachments.get:(Lnet/minecraft/world/entity/EntityAttachment;IF)Lnet/minecraft/world/phys/Vec3;");
    }

    public static class Builder {

        protected Builder() {
        }

        public EntityAttachments build(float width, float height) {
            throw Unimplemented.forMember("net/minecraft/world/entity/EntityAttachments$Builder.build:(FF)Lnet/minecraft/world/entity/EntityAttachments;");
        }
    }

    public EntityAttachments() {
        this(Map.of());
    }
}
