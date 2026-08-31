package net.minecraft.util;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.state.properties.Property;
import dev.pumpkin.shim.Unimplemented;

public class Util {

    public static <T extends Comparable<T>> String getPropertyName(Property<T> key, Object value) {
        throw Unimplemented.forMember("net/minecraft/util/Util.getPropertyName:(Lnet/minecraft/world/level/block/state/properties/Property;Ljava/lang/Object;)Ljava/lang/String;");
    }

    public static String makeDescriptionId(String prefix, Identifier location) {
        throw Unimplemented.forMember("net/minecraft/util/Util.makeDescriptionId:(Ljava/lang/String;Lnet/minecraft/resources/Identifier;)Ljava/lang/String;");
    }

    public static long getMillis() {
        throw Unimplemented.forMember("net/minecraft/util/Util.getMillis:()J");
    }

    public static long getNanos() {
        throw Unimplemented.forMember("net/minecraft/util/Util.getNanos:()J");
    }

    public static <T> String getRegisteredName(Registry<T> registry, T entry) {
        throw Unimplemented.forMember("net/minecraft/util/Util.getRegisteredName:(Lnet/minecraft/core/Registry;Ljava/lang/Object;)Ljava/lang/String;");
    }

    // Pumpkin divergence: real bodies, copied from vanilla -- the ARGB rule.
    public static <T> T make(Supplier<T> factory) {
        return factory.get();
    }

    public static <T> T make(T t, Consumer<? super T> consumer) {
        consumer.accept(t);
        return t;
    }

    public static <T> T getRandom(T[] array, RandomSource random) {
        throw Unimplemented.forMember("net/minecraft/util/Util.getRandom:([Ljava/lang/Object;Lnet/minecraft/util/RandomSource;)Ljava/lang/Object;");
    }

    public static int getRandom(int[] array, RandomSource random) {
        throw Unimplemented.forMember("net/minecraft/util/Util.getRandom:([ILnet/minecraft/util/RandomSource;)I");
    }

    public static <T> T getRandom(List<T> list, RandomSource random) {
        throw Unimplemented.forMember("net/minecraft/util/Util.getRandom:(Ljava/util/List;Lnet/minecraft/util/RandomSource;)Ljava/lang/Object;");
    }

    public static <T> Optional<T> getRandomSafe(List<T> list, RandomSource random) {
        throw Unimplemented.forMember("net/minecraft/util/Util.getRandomSafe:(Ljava/util/List;Lnet/minecraft/util/RandomSource;)Ljava/util/Optional;");
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
