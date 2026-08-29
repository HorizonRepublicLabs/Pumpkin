package net.minecraft.util;

import net.minecraft.core.BlockPos;
import dev.pumpkin.shim.Unimplemented;

public class BlockUtil {

    public static class FoundRectangle {

        public FoundRectangle(BlockPos minCorner, int axis1Size, int axis2Size) {
        }

        public FoundRectangle() {
        }
    }

    public static class IntBounds {

        public IntBounds(int min, int max) {
        }

        public String toString() {
            throw Unimplemented.forMember("net/minecraft/util/BlockUtil$IntBounds.toString:()Ljava/lang/String;");
        }

        public IntBounds() {
        }
    }

    public BlockUtil() {
    }
}
