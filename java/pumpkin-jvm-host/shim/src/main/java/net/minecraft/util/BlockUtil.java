package net.minecraft.util;

import net.minecraft.core.BlockPos;
import dev.pumpkin.shim.Unimplemented;

public class BlockUtil {

    public static class FoundRectangle {

        public FoundRectangle(BlockPos minCorner, int axis1Size, int axis2Size) {
            throw Unimplemented.forMember("net/minecraft/util/BlockUtil$FoundRectangle.<init>:(Lnet/minecraft/core/BlockPos;II)V");
        }

        protected FoundRectangle() {
        }
    }

    public static class IntBounds {

        public IntBounds(int min, int max) {
            throw Unimplemented.forMember("net/minecraft/util/BlockUtil$IntBounds.<init>:(II)V");
        }

        public String toString() {
            throw Unimplemented.forMember("net/minecraft/util/BlockUtil$IntBounds.toString:()Ljava/lang/String;");
        }

        protected IntBounds() {
        }
    }

    protected BlockUtil() {
    }
}
