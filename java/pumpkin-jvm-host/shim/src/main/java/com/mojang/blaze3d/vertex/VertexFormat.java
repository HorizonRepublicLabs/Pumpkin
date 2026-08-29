package com.mojang.blaze3d.vertex;

import java.util.List;
import dev.pumpkin.shim.Unimplemented;

public class VertexFormat {

    private VertexFormat(List<VertexFormatElement> elements, int vertexSize, int stepRate) {
        throw Unimplemented.forMember("com/mojang/blaze3d/vertex/VertexFormat.<init>:(Ljava/util/List;II)V");
    }

    public String toString() {
        throw Unimplemented.forMember("com/mojang/blaze3d/vertex/VertexFormat.toString:()Ljava/lang/String;");
    }

    public boolean contains(String attributeName) {
        throw Unimplemented.forMember("com/mojang/blaze3d/vertex/VertexFormat.contains:(Ljava/lang/String;)Z");
    }

    public boolean equals(Object o) {
        throw Unimplemented.forMember("com/mojang/blaze3d/vertex/VertexFormat.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("com/mojang/blaze3d/vertex/VertexFormat.hashCode:()I");
    }

    public static class Builder {

        private Builder(int stepRate) {
            throw Unimplemented.forMember("com/mojang/blaze3d/vertex/VertexFormat$Builder.<init>:(I)V");
        }

        public VertexFormat build() {
            throw Unimplemented.forMember("com/mojang/blaze3d/vertex/VertexFormat$Builder.build:()Lcom/mojang/blaze3d/vertex/VertexFormat;");
        }

        protected Builder() {
        }
    }

    protected VertexFormat() {
    }
}
