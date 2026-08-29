package net.minecraft.client.renderer.feature;

import net.minecraft.client.renderer.feature.submit.SubmitNode;
import dev.pumpkin.shim.Unimplemented;

public record FeatureRendererType<Submit extends SubmitNode>(int id, String name) {

    public static <Submit extends SubmitNode> FeatureRendererType<Submit> create(String name) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/feature/FeatureRendererType.create:(Ljava/lang/String;)Lnet/minecraft/client/renderer/feature/FeatureRendererType;");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/feature/FeatureRendererType.toString:()Ljava/lang/String;");
    }
}
