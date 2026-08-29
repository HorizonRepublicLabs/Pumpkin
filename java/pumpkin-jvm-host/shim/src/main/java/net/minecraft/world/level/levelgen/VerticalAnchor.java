package net.minecraft.world.level.levelgen;

import dev.pumpkin.shim.Unimplemented;

public interface VerticalAnchor {

    static VerticalAnchor bottom() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/VerticalAnchor.bottom:()Lnet/minecraft/world/level/levelgen/VerticalAnchor;");
    }

    static VerticalAnchor top() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/VerticalAnchor.top:()Lnet/minecraft/world/level/levelgen/VerticalAnchor;");
    }

    int resolveY(final WorldGenerationContext heightAccessor);

    record AboveBottom(int offset) implements VerticalAnchor {

        public int resolveY(WorldGenerationContext heightAccessor) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/VerticalAnchor$AboveBottom.resolveY:(Lnet/minecraft/world/level/levelgen/WorldGenerationContext;)I");
        }

        public String toString() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/VerticalAnchor$AboveBottom.toString:()Ljava/lang/String;");
        }
    }

    record Absolute(int y) implements VerticalAnchor {

        public int resolveY(WorldGenerationContext heightAccessor) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/VerticalAnchor$Absolute.resolveY:(Lnet/minecraft/world/level/levelgen/WorldGenerationContext;)I");
        }

        public String toString() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/VerticalAnchor$Absolute.toString:()Ljava/lang/String;");
        }
    }

    record BelowTop(int offset) implements VerticalAnchor {

        public int resolveY(WorldGenerationContext heightAccessor) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/VerticalAnchor$BelowTop.resolveY:(Lnet/minecraft/world/level/levelgen/WorldGenerationContext;)I");
        }

        public String toString() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/VerticalAnchor$BelowTop.toString:()Ljava/lang/String;");
        }
    }
}
