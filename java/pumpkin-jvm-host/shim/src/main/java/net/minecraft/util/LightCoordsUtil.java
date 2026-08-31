package net.minecraft.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndLightGetter;
import net.minecraft.world.level.block.state.BlockState;
import dev.pumpkin.shim.Unimplemented;

public class LightCoordsUtil {

    public static int pack(int block, int sky) {
        throw Unimplemented.forMember("net/minecraft/util/LightCoordsUtil.pack:(II)I");
    }

    public static int block(int packed) {
        throw Unimplemented.forMember("net/minecraft/util/LightCoordsUtil.block:(I)I");
    }

    public static int sky(int packed) {
        throw Unimplemented.forMember("net/minecraft/util/LightCoordsUtil.sky:(I)I");
    }

    public static int withBlock(int coords, int block) {
        throw Unimplemented.forMember("net/minecraft/util/LightCoordsUtil.withBlock:(II)I");
    }

    public static int max(int coords1, int coords2) {
        throw Unimplemented.forMember("net/minecraft/util/LightCoordsUtil.max:(II)I");
    }

    public static int lightCoordsWithEmission(int lightCoords, int emission) {
        throw Unimplemented.forMember("net/minecraft/util/LightCoordsUtil.lightCoordsWithEmission:(II)I");
    }

    public static int getLightCoords(BlockAndLightGetter level, BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/util/LightCoordsUtil.getLightCoords:(Lnet/minecraft/world/level/BlockAndLightGetter;Lnet/minecraft/core/BlockPos;)I");
    }

    public static int getLightCoords(LightCoordsUtil.BrightnessGetter brightnessGetter, BlockAndLightGetter level, BlockState state, BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/util/LightCoordsUtil.getLightCoords:(Lnet/minecraft/util/LightCoordsUtil$BrightnessGetter;Lnet/minecraft/world/level/BlockAndLightGetter;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/BlockPos;)I");
    }

    public interface BrightnessGetter {

        LightCoordsUtil.BrightnessGetter DEFAULT = null;

        int packedBrightness(BlockAndLightGetter level, BlockPos pos);
    }

    public LightCoordsUtil() {
    }
}
