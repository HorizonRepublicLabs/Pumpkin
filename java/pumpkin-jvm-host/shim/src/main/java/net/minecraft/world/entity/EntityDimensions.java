package net.minecraft.world.entity;

public record EntityDimensions(float width, float height, float eyeHeight, EntityAttachments attachments, boolean fixed) {

    private EntityDimensions(float width, float height, boolean fixed) {
        this((float) 0.0F, (float) 0.0F, (float) 0.0F, (EntityAttachments) null, (boolean) false);
    }
}
