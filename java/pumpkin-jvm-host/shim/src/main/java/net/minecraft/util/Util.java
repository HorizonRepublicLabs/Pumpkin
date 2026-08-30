package net.minecraft.util;

import java.net.URI;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import dev.pumpkin.shim.Unimplemented;

public class Util {

    // Pumpkin divergence: real bodies, copied from vanilla -- the ARGB rule.
    public static <T> T make(Supplier<T> factory) {
        return factory.get();
    }

    public static <T> T make(T t, Consumer<? super T> consumer) {
        consumer.accept(t);
        return t;
    }

    public static <T, R> Function<T, R> memoize(Function<T, R> function) {
        throw Unimplemented.forMember("net/minecraft/util/Util.memoize:(Ljava/util/function/Function;)Ljava/util/function/Function;");
    }

    public static <T, U, R> BiFunction<T, U, R> memoize(BiFunction<T, U, R> function) {
        throw Unimplemented.forMember("net/minecraft/util/Util.memoize:(Ljava/util/function/BiFunction;)Ljava/util/function/BiFunction;");
    }

    public enum OS {

        LINUX, SOLARIS, WINDOWS {

            protected String[] getOpenUriArguments(URI uri) {
                throw Unimplemented.forMember("net/minecraft/util/Util$OS$WINDOWS.getOpenUriArguments:()");
            }
        }
        , OSX {

            protected String[] getOpenUriArguments(URI uri) {
                throw Unimplemented.forMember("net/minecraft/util/Util$OS$OSX.getOpenUriArguments:()");
            }
        }
        , UNKNOWN
    }

    public Util() {
    }
}
