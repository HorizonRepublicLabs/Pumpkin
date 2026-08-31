package net.neoforged.neoforge.common.extensions;

import java.util.Set;
import net.neoforged.neoforge.common.util.ValueIOSerializable;
import dev.pumpkin.shim.Unimplemented;

public interface ValueInputExtension {

    default Set<String> keySet() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/ValueInputExtension.keySet:()Ljava/util/Set;");
    }

    default void readChild(String key, ValueIOSerializable object) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/ValueInputExtension.readChild:(Ljava/lang/String;Lnet/neoforged/neoforge/common/util/ValueIOSerializable;)V");
    }
}
