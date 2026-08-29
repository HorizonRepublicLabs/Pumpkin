package net.minecraft.util;

import java.util.function.Consumer;
import dev.pumpkin.shim.Unimplemented;

public class StaticCache2D<T> {

    public static <T> StaticCache2D<T> create(int centerX, int centerZ, int range, StaticCache2D.Initializer<T> initializer) {
        throw Unimplemented.forMember("net/minecraft/util/StaticCache2D.create:(IIILnet/minecraft/util/StaticCache2D$Initializer;)Lnet/minecraft/util/StaticCache2D;");
    }

    private StaticCache2D(int minX, int minZ, int sizeX, int sizeZ, StaticCache2D.Initializer<T> initializer) {
        throw Unimplemented.forMember("net/minecraft/util/StaticCache2D.<init>:(IIIILnet/minecraft/util/StaticCache2D$Initializer;)V");
    }

    public void forEach(Consumer<T> consumer) {
        throw Unimplemented.forMember("net/minecraft/util/StaticCache2D.forEach:(Ljava/util/function/Consumer;)V");
    }

    public T get(int x, int z) {
        throw Unimplemented.forMember("net/minecraft/util/StaticCache2D.get:(II)Ljava/lang/Object;");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/util/StaticCache2D.toString:()Ljava/lang/String;");
    }

    public interface Initializer<T> {

        T get(int x, int z);
    }

    public StaticCache2D() {
    }
}
