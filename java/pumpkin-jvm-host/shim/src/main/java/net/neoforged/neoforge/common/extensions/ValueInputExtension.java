package net.neoforged.neoforge.common.extensions;

import java.util.Set;
import net.neoforged.neoforge.common.util.ValueIOSerializable;
import dev.pumpkin.shim.Unimplemented;

public interface ValueInputExtension {

    default Set<String> keySet() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/ValueInputExtension.keySet:()Ljava/util/Set;");
    }

    // Pumpkin divergence: NeoForge's own body -- deserialize the child compound into
    // the target; an absent child leaves the target's own defaults standing.
    default void readChild(String key, ValueIOSerializable object) {
        object.deserialize(((net.minecraft.world.level.storage.ValueInput) this).childOrEmpty(key));
    }
}
