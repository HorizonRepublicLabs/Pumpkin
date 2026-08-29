package net.neoforged.neoforge.client.model.quad;

import org.joml.Vector3fc;
import dev.pumpkin.shim.Unimplemented;

public interface BakedNormals {

    int normal(int vertex);

    record PerQuad(int normal) implements BakedNormals {

        public int normal(int vertex) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/model/quad/BakedNormals$PerQuad.normal:(I)I");
        }
    }

    record PerVertex(int normal0, int normal1, int normal2, int normal3) implements BakedNormals {

        public int normal(int vertex) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/model/quad/BakedNormals$PerVertex.normal:(I)I");
        }
    }

    static int pack(Vector3fc normal) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/model/quad/BakedNormals.pack:(Lorg/joml/Vector3fc;)I");
    }
}
