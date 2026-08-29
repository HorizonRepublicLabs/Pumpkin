package net.minecraft.world.entity;

import dev.pumpkin.shim.Unimplemented;

public record EntityDimensions(float width, float height, float eyeHeight, EntityAttachments attachments, boolean fixed) {

    private EntityDimensions(float width, float height, boolean fixed) {
        this((float) 0.0F, (float) 0.0F, (float) 0.0F, (EntityAttachments) null, (boolean) false);
        throw Unimplemented.forMember("net/minecraft/world/entity/EntityDimensions.<init>:(FFZ)V");
    }
}
