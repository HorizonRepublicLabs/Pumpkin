package net.neoforged.neoforge.client.extensions;

import net.minecraft.client.renderer.feature.submit.SubmitNode;
import net.neoforged.neoforge.client.submit.RenderPhaseKey;
import dev.pumpkin.shim.Unimplemented;

public interface OrderedSubmitNodeCollectorExtension {

    default <T extends SubmitNode, S extends T> void submitSpecial(RenderPhaseKey<T> phaseKey, S submitNode) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/extensions/OrderedSubmitNodeCollectorExtension.submitSpecial:(Lnet/neoforged/neoforge/client/submit/RenderPhaseKey;Lnet/neoforged/neoforge/client/extensions/T;)V");
    }
}
