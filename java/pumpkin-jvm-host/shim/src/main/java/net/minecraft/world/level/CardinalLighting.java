package net.minecraft.world.level;

import net.minecraft.util.StringRepresentable;
import dev.pumpkin.shim.Unimplemented;

public record CardinalLighting(float down, float up, float north, float south, float west, float east) {

    public enum Type implements StringRepresentable {

        DEFAULT, NETHER;

        public CardinalLighting get() {
            throw Unimplemented.forMember("net/minecraft/world/level/CardinalLighting$Type.get:()Lnet/minecraft/world/level/CardinalLighting;");
        }

        public String getSerializedName() {
            throw Unimplemented.forMember("net/minecraft/world/level/CardinalLighting$Type.getSerializedName:()Ljava/lang/String;");
        }
    }
}
