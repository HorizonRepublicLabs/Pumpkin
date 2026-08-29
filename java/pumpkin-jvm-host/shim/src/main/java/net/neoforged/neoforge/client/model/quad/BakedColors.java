package net.neoforged.neoforge.client.model.quad;

import dev.pumpkin.shim.Unimplemented;

public interface BakedColors {

    int color(int vertex);

    record PerQuad(int color) implements BakedColors {

        public int color(int vertex) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/model/quad/BakedColors$PerQuad.color:(I)I");
        }
    }

    record PerVertex(int color0, int color1, int color2, int color3) implements BakedColors {

        public int color(int vertex) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/model/quad/BakedColors$PerVertex.color:(I)I");
        }
    }
}
