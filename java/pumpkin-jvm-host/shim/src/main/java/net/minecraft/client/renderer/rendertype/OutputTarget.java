package net.minecraft.client.renderer.rendertype;

import com.mojang.blaze3d.pipeline.RenderTarget;
import java.util.function.Supplier;
import dev.pumpkin.shim.Unimplemented;

public class OutputTarget {

    public OutputTarget(String name, Supplier<RenderTarget> renderTargetSupplier) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/rendertype/OutputTarget.<init>:(Ljava/lang/String;Ljava/util/function/Supplier;)V");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/rendertype/OutputTarget.toString:()Ljava/lang/String;");
    }

    protected OutputTarget() {
    }
}
