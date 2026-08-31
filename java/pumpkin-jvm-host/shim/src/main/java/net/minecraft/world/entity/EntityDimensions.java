package net.minecraft.world.entity;

import dev.pumpkin.shim.Unimplemented;

public record EntityDimensions(float width, float height, float eyeHeight, EntityAttachments attachments, boolean fixed) {

    private EntityDimensions(float width, float height, boolean fixed) {
        this((float) 0.0F, (float) 0.0F, (float) 0.0F, (EntityAttachments) null, (boolean) false);
    }

    // Pumpkin divergence: vanilla math -- eyeHeight is the LivingEntity default
    // (0.85 * height); attachment points are not modeled, so the map is real but empty.
    public static EntityDimensions scalable(float width, float height) {
        return new EntityDimensions(width, height, height * 0.85F, EntityAttachments.pumpkinEmpty(), false);
    }

    public EntityDimensions scale(float scaleFactor) {
        return scale(scaleFactor, scaleFactor);
    }

    public EntityDimensions scale(float widthScaleFactor, float heightScaleFactor) {
        if (fixed || (widthScaleFactor == 1.0F && heightScaleFactor == 1.0F)) {
            return this;
        }
        return new EntityDimensions(width * widthScaleFactor, height * heightScaleFactor, eyeHeight * heightScaleFactor, EntityAttachments.pumpkinEmpty(), false);
    }
}
