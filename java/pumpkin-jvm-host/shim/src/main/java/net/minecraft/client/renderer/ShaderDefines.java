package net.minecraft.client.renderer;

import java.util.Map;
import java.util.Set;
import dev.pumpkin.shim.Unimplemented;

public record ShaderDefines(Map<String, String> values, Set<String> flags) {

    public boolean isEmpty() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/ShaderDefines.isEmpty:()Z");
    }

    public static class Builder {

        protected Builder() {
            throw Unimplemented.forMember("net/minecraft/client/renderer/ShaderDefines$Builder.<init>:()V");
        }

        public ShaderDefines build() {
            throw Unimplemented.forMember("net/minecraft/client/renderer/ShaderDefines$Builder.build:()Lnet/minecraft/client/renderer/ShaderDefines;");
        }
    }
}
