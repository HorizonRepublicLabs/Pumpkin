package net.minecraft.core;

import java.util.Iterator;
import dev.pumpkin.shim.Unimplemented;

public class IdMapper<T> implements IdMap<T> {

    public IdMapper() {
    }

    public IdMapper(int expectedSize) {
    }

    public void add(T thing) {
        throw Unimplemented.forMember("net/minecraft/core/IdMapper.add:(Ljava/lang/Object;)V");
    }

    public int getId(T thing) {
        throw Unimplemented.forMember("net/minecraft/core/IdMapper.getId:(Ljava/lang/Object;)I");
    }

    public final T byId(int id) {
        throw Unimplemented.forMember("net/minecraft/core/IdMapper.byId:(I)Ljava/lang/Object;");
    }

    public Iterator<T> iterator() {
        throw Unimplemented.forMember("net/minecraft/core/IdMapper.iterator:()Ljava/util/Iterator;");
    }

    public boolean contains(int id) {
        throw Unimplemented.forMember("net/minecraft/core/IdMapper.contains:(I)Z");
    }

    public int size() {
        throw Unimplemented.forMember("net/minecraft/core/IdMapper.size:()I");
    }
}
