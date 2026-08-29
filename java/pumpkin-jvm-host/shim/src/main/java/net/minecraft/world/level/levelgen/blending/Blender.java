package net.minecraft.world.level.levelgen.blending;

import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import dev.pumpkin.shim.Unimplemented;

public class Blender {

    private Blender(Long2ObjectOpenHashMap<BlendingData> heightAndBiomeBlendingData, Long2ObjectOpenHashMap<BlendingData> densityBlendingData) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/blending/Blender.<init>:(Lit/unimi/dsi/fastutil/longs/Long2ObjectOpenHashMap;Lit/unimi/dsi/fastutil/longs/Long2ObjectOpenHashMap;)V");
    }

    public boolean isEmpty() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/blending/Blender.isEmpty:()Z");
    }

    public record BlendingOutput(double alpha, double blendingOffset) {
    }

    private interface CellValueGetter {

        double get(BlendingData data, int cellX, int cellY, int cellZ);
    }

    public interface DistanceGetter {

        double getDistance(double x, double y, double z);
    }

    public Blender() {
    }
}
