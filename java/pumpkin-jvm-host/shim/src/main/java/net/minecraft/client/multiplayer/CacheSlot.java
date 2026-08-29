package net.minecraft.client.multiplayer;

import java.util.function.Function;
import dev.pumpkin.shim.Unimplemented;

public class CacheSlot<C extends CacheSlot.Cleaner<C>, D> {

    public CacheSlot(Function<C, D> operation) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/CacheSlot.<init>:(Ljava/util/function/Function;)V");
    }

    public D compute(C context) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/CacheSlot.compute:(Lnet/minecraft/client/multiplayer/CacheSlot$Cleaner;)Ljava/lang/Object;");
    }

    public void clear() {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/CacheSlot.clear:()V");
    }

    public interface Cleaner<C extends CacheSlot.Cleaner<C>> {

        void registerForCleaning(CacheSlot<C, ?> slot);
    }

    protected CacheSlot() {
    }
}
