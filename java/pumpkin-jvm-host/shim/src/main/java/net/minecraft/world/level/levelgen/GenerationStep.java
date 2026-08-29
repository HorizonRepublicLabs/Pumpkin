package net.minecraft.world.level.levelgen;

import net.minecraft.util.StringRepresentable;
import dev.pumpkin.shim.Unimplemented;

public class GenerationStep {

    public enum Decoration implements StringRepresentable {

        RAW_GENERATION,
        LAKES,
        LOCAL_MODIFICATIONS,
        UNDERGROUND_STRUCTURES,
        SURFACE_STRUCTURES,
        STRONGHOLDS,
        UNDERGROUND_ORES,
        UNDERGROUND_DECORATION,
        FLUID_SPRINGS,
        VEGETAL_DECORATION,
        TOP_LAYER_MODIFICATION;

        public String getName() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/GenerationStep$Decoration.getName:()Ljava/lang/String;");
        }

        public String getSerializedName() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/GenerationStep$Decoration.getSerializedName:()Ljava/lang/String;");
        }
    }

    protected GenerationStep() {
    }
}
