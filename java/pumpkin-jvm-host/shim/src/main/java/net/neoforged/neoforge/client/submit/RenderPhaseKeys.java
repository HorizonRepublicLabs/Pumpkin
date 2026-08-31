package net.neoforged.neoforge.client.submit;

import net.minecraft.client.renderer.feature.submit.SubmitNode;
import net.minecraft.client.renderer.feature.submit.TranslucentSubmit;
import dev.pumpkin.shim.Unimplemented;

public final class RenderPhaseKeys {

    public static final RenderPhaseKey<SubmitNode> SOLID = null;

    public static final RenderPhaseKey<SubmitNode> SHAPE_OUTLINES = null;

    public static final RenderPhaseKey<TranslucentSubmit> TRANSLUCENT_BLOCKS_AND_ITEMS = null;

    public static final RenderPhaseKey<SubmitNode> AFTER_TERRAIN = null;

    public static final RenderPhaseKey<SubmitNode> OUTLINE = null;

    protected RenderPhaseKeys() {
    }

    static {
        if (true) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/submit/RenderPhaseKeys");
        }
    }
}
