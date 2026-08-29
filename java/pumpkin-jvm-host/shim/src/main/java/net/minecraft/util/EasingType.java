package net.minecraft.util;

import dev.pumpkin.shim.Unimplemented;

public interface EasingType {

    float apply(float x);

    final class CubicBezier implements EasingType {

        public CubicBezier(EasingType.CubicBezierControls controls) {
        }

        public float apply(float x) {
            throw Unimplemented.forMember("net/minecraft/util/EasingType$CubicBezier.apply:(F)F");
        }

        public boolean equals(Object obj) {
            throw Unimplemented.forMember("net/minecraft/util/EasingType$CubicBezier.equals:(Ljava/lang/Object;)Z");
        }

        public int hashCode() {
            throw Unimplemented.forMember("net/minecraft/util/EasingType$CubicBezier.hashCode:()I");
        }

        public String toString() {
            throw Unimplemented.forMember("net/minecraft/util/EasingType$CubicBezier.toString:()Ljava/lang/String;");
        }

        private record CubicCurve(float a, float b, float c) {

            public float sample(float t) {
                throw Unimplemented.forMember("net/minecraft/util/EasingType$CubicBezier$CubicCurve.sample:(F)F");
            }
        }

        protected CubicBezier() {
        }
    }

    record CubicBezierControls(float x1, float y1, float x2, float y2) {
    }
}
