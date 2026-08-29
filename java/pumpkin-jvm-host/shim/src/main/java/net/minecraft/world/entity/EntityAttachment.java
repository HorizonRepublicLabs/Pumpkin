package net.minecraft.world.entity;

import java.util.List;
import net.minecraft.world.phys.Vec3;

public enum EntityAttachment {

    PASSENGER, VEHICLE, NAME_TAG, WARDEN_CHEST;

    public interface Fallback {

        List<Vec3> create(float width, float height);
    }
}
