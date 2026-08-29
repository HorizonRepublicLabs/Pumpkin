package net.neoforged.neoforge.common.extensions;

import java.util.Set;
import dev.pumpkin.shim.Unimplemented;

public interface ValueInputExtension {

    default Set<String> keySet() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/ValueInputExtension.keySet:()Ljava/util/Set;");
    }
}
