package net.neoforged.neoforge.common.util;

import it.unimi.dsi.fastutil.Hash;
import it.unimi.dsi.fastutil.objects.ObjectLinkedOpenCustomHashSet;
import dev.pumpkin.shim.Unimplemented;

public class InsertableLinkedOpenCustomHashSet<T> extends ObjectLinkedOpenCustomHashSet<T> {

    // Pumpkin divergence: the fastutil super has no no-arg constructor; chain the
    // strategies vanilla would. Identity strategy for the no-arg form, per NeoForge.
    public InsertableLinkedOpenCustomHashSet() {
        super(it.unimi.dsi.fastutil.Hash.DEFAULT_INITIAL_SIZE, it.unimi.dsi.fastutil.Hash.DEFAULT_LOAD_FACTOR,
                new Hash.Strategy<T>() {
                    public int hashCode(T o) {
                        return java.util.Objects.hashCode(o);
                    }

                    public boolean equals(T a, T b) {
                        return java.util.Objects.equals(a, b);
                    }
                });
    }

    public InsertableLinkedOpenCustomHashSet(Hash.Strategy<? super T> strategy) {
        super(strategy);
    }

    public boolean addAfter(T insertAfter, T element) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/util/InsertableLinkedOpenCustomHashSet.addAfter:(Ljava/lang/Object;Ljava/lang/Object;)Z");
    }

    public boolean addBefore(T insertBefore, T element) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/util/InsertableLinkedOpenCustomHashSet.addBefore:(Ljava/lang/Object;Ljava/lang/Object;)Z");
    }

    public void addFirst(T element) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/util/InsertableLinkedOpenCustomHashSet.addFirst:(Ljava/lang/Object;)V");
    }

    public void addLast(T element) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/util/InsertableLinkedOpenCustomHashSet.addLast:(Ljava/lang/Object;)V");
    }

    private int getPos(T existingElement) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/util/InsertableLinkedOpenCustomHashSet.getPos:(Ljava/lang/Object;)I");
    }
}
