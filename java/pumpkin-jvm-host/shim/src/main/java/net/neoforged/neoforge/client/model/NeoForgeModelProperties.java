package net.neoforged.neoforge.client.model;

import java.util.Map;
import net.minecraft.util.context.ContextKey;
import dev.pumpkin.shim.Unimplemented;

public final class NeoForgeModelProperties {

    protected NeoForgeModelProperties() {
    }

    public static final ContextKey<Map<String, Boolean>> PART_VISIBILITY = null;

    static {
        if (true) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/model/NeoForgeModelProperties");
        }
    }
}
