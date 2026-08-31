package net.neoforged.neoforge.common.util.strategy;

import it.unimi.dsi.fastutil.Hash;
import dev.pumpkin.shim.Unimplemented;

public class BasicStrategy implements Hash.Strategy<Object> {

    public static final Hash.Strategy<? super Object> BASIC = null;

    protected BasicStrategy() {
    }

    public int hashCode(Object o) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/util/strategy/BasicStrategy.hashCode:(Ljava/lang/Object;)I");
    }

    public boolean equals(Object a, Object b) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/util/strategy/BasicStrategy.equals:(Ljava/lang/Object;Ljava/lang/Object;)Z");
    }
}
