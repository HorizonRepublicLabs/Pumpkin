package net.minecraft.client.resources.model.geometry;

import java.util.List;
import net.minecraft.core.Direction;
import dev.pumpkin.shim.Unimplemented;

public class QuadCollection {

    private QuadCollection(List<BakedQuad> all, List<BakedQuad> unculled, List<BakedQuad> north, List<BakedQuad> south, List<BakedQuad> east, List<BakedQuad> west, List<BakedQuad> up, List<BakedQuad> down) {
        throw Unimplemented.forMember("net/minecraft/client/resources/model/geometry/QuadCollection.<init>:(Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;)V");
    }

    public List<BakedQuad> getQuads(Direction direction) {
        throw Unimplemented.forMember("net/minecraft/client/resources/model/geometry/QuadCollection.getQuads:(Lnet/minecraft/core/Direction;)Ljava/util/List;");
    }

    public List<BakedQuad> getAll() {
        throw Unimplemented.forMember("net/minecraft/client/resources/model/geometry/QuadCollection.getAll:()Ljava/util/List;");
    }

    public int materialFlags() {
        throw Unimplemented.forMember("net/minecraft/client/resources/model/geometry/QuadCollection.materialFlags:()I");
    }

    public static class Builder {

        public QuadCollection build() {
            throw Unimplemented.forMember("net/minecraft/client/resources/model/geometry/QuadCollection$Builder.build:()Lnet/minecraft/client/resources/model/geometry/QuadCollection;");
        }

        protected Builder() {
        }
    }

    protected QuadCollection() {
    }
}
