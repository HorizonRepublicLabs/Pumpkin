package net.neoforged.neoforge.common;

import com.electronwill.nightconfig.core.Config;
import com.electronwill.nightconfig.core.EnumGetMethod;
import com.electronwill.nightconfig.core.UnmodifiableCommentedConfig;
import com.electronwill.nightconfig.core.UnmodifiableConfig;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import java.util.function.Function;
import java.util.function.IntSupplier;
import java.util.function.LongSupplier;
import java.util.function.Predicate;
import java.util.function.Supplier;
import net.neoforged.fml.config.IConfigSpec;
import net.neoforged.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;
import dev.pumpkin.shim.Unimplemented;

public class ModConfigSpec implements IConfigSpec {

    private ModConfigSpec(UnmodifiableConfig spec, UnmodifiableConfig values, Map<List<String>, String> levelComments, Map<List<String>, String> levelTranslationKeys) {
    }

    public boolean isEmpty() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec.isEmpty:()Z");
    }

    public void acceptConfig(ILoadedConfig config) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec.acceptConfig:(Lnet/neoforged/neoforge/common/ILoadedConfig;)V");
    }

    public void validateSpec(ModConfig config) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec.validateSpec:(Lnet/neoforged/fml/config/ModConfig;)V");
    }

    public boolean isLoaded() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec.isLoaded:()Z");
    }

    public void save() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec.save:()V");
    }

    public boolean isCorrect(UnmodifiableCommentedConfig config) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec.isCorrect:(Lcom/electronwill/nightconfig/core/UnmodifiableCommentedConfig;)Z");
    }

    public static class Builder {

        // Pumpkin divergence: no vanilla counterpart. push/pop nest sections, and a value's
        // key is the whole path -- without this, two mods defining "enabled" in different
        // sections would look like the same setting.
        private final java.util.List<String> pumpkinPath = new java.util.ArrayList<>();

        // Pumpkin divergence: real body.
        public <T> ConfigValue<T> define(String path, T defaultValue) {
            return new ConfigValue<>(defaultValue);
        }

        public <T> ConfigValue<T> define(List<String> path, T defaultValue) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.define:(Ljava/util/List;Ljava/lang/Object;)Lnet/neoforged/neoforge/common/ModConfigSpec$ConfigValue;");
        }

        public <T> ConfigValue<T> define(String path, T defaultValue, Predicate<Object> validator) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.define:(Ljava/lang/String;Ljava/lang/Object;Ljava/util/function/Predicate;)Lnet/neoforged/neoforge/common/ModConfigSpec$ConfigValue;");
        }

        public <T> ConfigValue<T> define(List<String> path, T defaultValue, Predicate<Object> validator) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.define:(Ljava/util/List;Ljava/lang/Object;Ljava/util/function/Predicate;)Lnet/neoforged/neoforge/common/ModConfigSpec$ConfigValue;");
        }

        public <T> ConfigValue<T> define(String path, Supplier<T> defaultSupplier, Predicate<Object> validator) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.define:(Ljava/lang/String;Ljava/util/function/Supplier;Ljava/util/function/Predicate;)Lnet/neoforged/neoforge/common/ModConfigSpec$ConfigValue;");
        }

        public <T> ConfigValue<T> define(List<String> path, Supplier<T> defaultSupplier, Predicate<Object> validator) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.define:(Ljava/util/List;Ljava/util/function/Supplier;Ljava/util/function/Predicate;)Lnet/neoforged/neoforge/common/ModConfigSpec$ConfigValue;");
        }

        public <T> ConfigValue<T> define(List<String> path, Supplier<T> defaultSupplier, Predicate<Object> validator, Class<?> clazz) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.define:(Ljava/util/List;Ljava/util/function/Supplier;Ljava/util/function/Predicate;Ljava/lang/Class;)Lnet/neoforged/neoforge/common/ModConfigSpec$ConfigValue;");
        }

        public <T> ConfigValue<T> define(List<String> path, ValueSpec value, Supplier<T> defaultSupplier) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.define:(Ljava/util/List;Lnet/neoforged/neoforge/common/ModConfigSpec$ValueSpec;Ljava/util/function/Supplier;)Lnet/neoforged/neoforge/common/ModConfigSpec$ConfigValue;");
        }

        public <V extends Comparable<? super V>> ConfigValue<V> defineInRange(String path, V defaultValue, V min, V max, Class<V> clazz) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.defineInRange:(Ljava/lang/String;Ljava/lang/Comparable;Ljava/lang/Comparable;Ljava/lang/Comparable;Ljava/lang/Class;)Lnet/neoforged/neoforge/common/ModConfigSpec$ConfigValue;");
        }

        public <V extends Comparable<? super V>> ConfigValue<V> defineInRange(List<String> path, V defaultValue, V min, V max, Class<V> clazz) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.defineInRange:(Ljava/util/List;Ljava/lang/Comparable;Ljava/lang/Comparable;Ljava/lang/Comparable;Ljava/lang/Class;)Lnet/neoforged/neoforge/common/ModConfigSpec$ConfigValue;");
        }

        public <V extends Comparable<? super V>> ConfigValue<V> defineInRange(String path, Supplier<V> defaultSupplier, V min, V max, Class<V> clazz) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.defineInRange:(Ljava/lang/String;Ljava/util/function/Supplier;Ljava/lang/Comparable;Ljava/lang/Comparable;Ljava/lang/Class;)Lnet/neoforged/neoforge/common/ModConfigSpec$ConfigValue;");
        }

        public <V extends Comparable<? super V>> ConfigValue<V> defineInRange(List<String> path, Supplier<V> defaultSupplier, V min, V max, Class<V> clazz) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.defineInRange:(Ljava/util/List;Ljava/util/function/Supplier;Ljava/lang/Comparable;Ljava/lang/Comparable;Ljava/lang/Class;)Lnet/neoforged/neoforge/common/ModConfigSpec$ConfigValue;");
        }

        public <T> ConfigValue<List<? extends T>> defineListAllowEmpty(String path, List<? extends T> defaultValue, Predicate<Object> elementValidator) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.defineListAllowEmpty:(Ljava/lang/String;Ljava/util/List;Ljava/util/function/Predicate;)Lnet/neoforged/neoforge/common/ModConfigSpec$ConfigValue;");
        }

        public <T> ConfigValue<List<? extends T>> defineListAllowEmpty(String path, List<? extends T> defaultValue, Supplier<T> newElementSupplier, Predicate<Object> elementValidator) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.defineListAllowEmpty:(Ljava/lang/String;Ljava/util/List;Ljava/util/function/Supplier;Ljava/util/function/Predicate;)Lnet/neoforged/neoforge/common/ModConfigSpec$ConfigValue;");
        }

        public <T> ConfigValue<List<? extends T>> defineListAllowEmpty(String path, Supplier<List<? extends T>> defaultSupplier, Predicate<Object> elementValidator) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.defineListAllowEmpty:(Ljava/lang/String;Ljava/util/function/Supplier;Ljava/util/function/Predicate;)Lnet/neoforged/neoforge/common/ModConfigSpec$ConfigValue;");
        }

        public <T> ConfigValue<List<? extends T>> defineListAllowEmpty(String path, Supplier<List<? extends T>> defaultSupplier, Supplier<T> newElementSupplier, Predicate<Object> elementValidator) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.defineListAllowEmpty:(Ljava/lang/String;Ljava/util/function/Supplier;Ljava/util/function/Supplier;Ljava/util/function/Predicate;)Lnet/neoforged/neoforge/common/ModConfigSpec$ConfigValue;");
        }

        public <T> ConfigValue<List<? extends T>> defineListAllowEmpty(List<String> path, List<? extends T> defaultValue, Predicate<Object> elementValidator) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.defineListAllowEmpty:(Ljava/util/List;Ljava/util/List;Ljava/util/function/Predicate;)Lnet/neoforged/neoforge/common/ModConfigSpec$ConfigValue;");
        }

        public <T> ConfigValue<List<? extends T>> defineListAllowEmpty(List<String> path, List<? extends T> defaultValue, Supplier<T> newElementSupplier, Predicate<Object> elementValidator) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.defineListAllowEmpty:(Ljava/util/List;Ljava/util/List;Ljava/util/function/Supplier;Ljava/util/function/Predicate;)Lnet/neoforged/neoforge/common/ModConfigSpec$ConfigValue;");
        }

        public <T> ConfigValue<List<? extends T>> defineListAllowEmpty(List<String> path, Supplier<List<? extends T>> defaultSupplier, Predicate<Object> elementValidator) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.defineListAllowEmpty:(Ljava/util/List;Ljava/util/function/Supplier;Ljava/util/function/Predicate;)Lnet/neoforged/neoforge/common/ModConfigSpec$ConfigValue;");
        }

        public <T> ConfigValue<List<? extends T>> defineListAllowEmpty(List<String> path, Supplier<List<? extends T>> defaultSupplier, Supplier<T> newElementSupplier, Predicate<Object> elementValidator) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.defineListAllowEmpty:(Ljava/util/List;Ljava/util/function/Supplier;Ljava/util/function/Supplier;Ljava/util/function/Predicate;)Lnet/neoforged/neoforge/common/ModConfigSpec$ConfigValue;");
        }

        public <V extends Enum<V>> EnumValue<V> defineEnum(String path, V defaultValue) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.defineEnum:(Ljava/lang/String;Ljava/lang/Enum;)Lnet/neoforged/neoforge/common/ModConfigSpec$EnumValue;");
        }

        public <V extends Enum<V>> EnumValue<V> defineEnum(String path, V defaultValue, EnumGetMethod converter) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.defineEnum:(Ljava/lang/String;Ljava/lang/Enum;Lcom/electronwill/nightconfig/core/EnumGetMethod;)Lnet/neoforged/neoforge/common/ModConfigSpec$EnumValue;");
        }

        public <V extends Enum<V>> EnumValue<V> defineEnum(List<String> path, V defaultValue) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.defineEnum:(Ljava/util/List;Ljava/lang/Enum;)Lnet/neoforged/neoforge/common/ModConfigSpec$EnumValue;");
        }

        public <V extends Enum<V>> EnumValue<V> defineEnum(List<String> path, V defaultValue, EnumGetMethod converter) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.defineEnum:(Ljava/util/List;Ljava/lang/Enum;Lcom/electronwill/nightconfig/core/EnumGetMethod;)Lnet/neoforged/neoforge/common/ModConfigSpec$EnumValue;");
        }

        public <V extends Enum<V>> EnumValue<V> defineEnum(String path, V defaultValue, V... acceptableValues) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.defineEnum:(Ljava/lang/String;Ljava/lang/Enum;[Ljava/lang/Enum;)Lnet/neoforged/neoforge/common/ModConfigSpec$EnumValue;");
        }

        public <V extends Enum<V>> EnumValue<V> defineEnum(String path, V defaultValue, EnumGetMethod converter, V... acceptableValues) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.defineEnum:(Ljava/lang/String;Ljava/lang/Enum;Lcom/electronwill/nightconfig/core/EnumGetMethod;[Ljava/lang/Enum;)Lnet/neoforged/neoforge/common/ModConfigSpec$EnumValue;");
        }

        public <V extends Enum<V>> EnumValue<V> defineEnum(List<String> path, V defaultValue, V... acceptableValues) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.defineEnum:(Ljava/util/List;Ljava/lang/Enum;[Ljava/lang/Enum;)Lnet/neoforged/neoforge/common/ModConfigSpec$EnumValue;");
        }

        public <V extends Enum<V>> EnumValue<V> defineEnum(List<String> path, V defaultValue, EnumGetMethod converter, V... acceptableValues) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.defineEnum:(Ljava/util/List;Ljava/lang/Enum;Lcom/electronwill/nightconfig/core/EnumGetMethod;[Ljava/lang/Enum;)Lnet/neoforged/neoforge/common/ModConfigSpec$EnumValue;");
        }

        public <V extends Enum<V>> EnumValue<V> defineEnum(String path, V defaultValue, Collection<V> acceptableValues) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.defineEnum:(Ljava/lang/String;Ljava/lang/Enum;Ljava/util/Collection;)Lnet/neoforged/neoforge/common/ModConfigSpec$EnumValue;");
        }

        public <V extends Enum<V>> EnumValue<V> defineEnum(String path, V defaultValue, EnumGetMethod converter, Collection<V> acceptableValues) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.defineEnum:(Ljava/lang/String;Ljava/lang/Enum;Lcom/electronwill/nightconfig/core/EnumGetMethod;Ljava/util/Collection;)Lnet/neoforged/neoforge/common/ModConfigSpec$EnumValue;");
        }

        public <V extends Enum<V>> EnumValue<V> defineEnum(List<String> path, V defaultValue, Collection<V> acceptableValues) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.defineEnum:(Ljava/util/List;Ljava/lang/Enum;Ljava/util/Collection;)Lnet/neoforged/neoforge/common/ModConfigSpec$EnumValue;");
        }

        public <V extends Enum<V>> EnumValue<V> defineEnum(List<String> path, V defaultValue, EnumGetMethod converter, Collection<V> acceptableValues) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.defineEnum:(Ljava/util/List;Ljava/lang/Enum;Lcom/electronwill/nightconfig/core/EnumGetMethod;Ljava/util/Collection;)Lnet/neoforged/neoforge/common/ModConfigSpec$EnumValue;");
        }

        public <V extends Enum<V>> EnumValue<V> defineEnum(String path, V defaultValue, Predicate<Object> validator) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.defineEnum:(Ljava/lang/String;Ljava/lang/Enum;Ljava/util/function/Predicate;)Lnet/neoforged/neoforge/common/ModConfigSpec$EnumValue;");
        }

        public <V extends Enum<V>> EnumValue<V> defineEnum(String path, V defaultValue, EnumGetMethod converter, Predicate<Object> validator) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.defineEnum:(Ljava/lang/String;Ljava/lang/Enum;Lcom/electronwill/nightconfig/core/EnumGetMethod;Ljava/util/function/Predicate;)Lnet/neoforged/neoforge/common/ModConfigSpec$EnumValue;");
        }

        public <V extends Enum<V>> EnumValue<V> defineEnum(List<String> path, V defaultValue, Predicate<Object> validator) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.defineEnum:(Ljava/util/List;Ljava/lang/Enum;Ljava/util/function/Predicate;)Lnet/neoforged/neoforge/common/ModConfigSpec$EnumValue;");
        }

        public <V extends Enum<V>> EnumValue<V> defineEnum(List<String> path, V defaultValue, EnumGetMethod converter, Predicate<Object> validator) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.defineEnum:(Ljava/util/List;Ljava/lang/Enum;Lcom/electronwill/nightconfig/core/EnumGetMethod;Ljava/util/function/Predicate;)Lnet/neoforged/neoforge/common/ModConfigSpec$EnumValue;");
        }

        public <V extends Enum<V>> EnumValue<V> defineEnum(String path, Supplier<V> defaultSupplier, Predicate<Object> validator, Class<V> clazz) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.defineEnum:(Ljava/lang/String;Ljava/util/function/Supplier;Ljava/util/function/Predicate;Ljava/lang/Class;)Lnet/neoforged/neoforge/common/ModConfigSpec$EnumValue;");
        }

        public <V extends Enum<V>> EnumValue<V> defineEnum(String path, Supplier<V> defaultSupplier, EnumGetMethod converter, Predicate<Object> validator, Class<V> clazz) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.defineEnum:(Ljava/lang/String;Ljava/util/function/Supplier;Lcom/electronwill/nightconfig/core/EnumGetMethod;Ljava/util/function/Predicate;Ljava/lang/Class;)Lnet/neoforged/neoforge/common/ModConfigSpec$EnumValue;");
        }

        public <V extends Enum<V>> EnumValue<V> defineEnum(List<String> path, Supplier<V> defaultSupplier, Predicate<Object> validator, Class<V> clazz) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.defineEnum:(Ljava/util/List;Ljava/util/function/Supplier;Ljava/util/function/Predicate;Ljava/lang/Class;)Lnet/neoforged/neoforge/common/ModConfigSpec$EnumValue;");
        }

        public <V extends Enum<V>> EnumValue<V> defineEnum(List<String> path, Supplier<V> defaultSupplier, EnumGetMethod converter, Predicate<Object> validator, Class<V> clazz) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.defineEnum:(Ljava/util/List;Ljava/util/function/Supplier;Lcom/electronwill/nightconfig/core/EnumGetMethod;Ljava/util/function/Predicate;Ljava/lang/Class;)Lnet/neoforged/neoforge/common/ModConfigSpec$EnumValue;");
        }

        // Pumpkin divergence: real body.
        public BooleanValue define(String path, boolean defaultValue) {
            return new BooleanValue(defaultValue);
        }

        public BooleanValue define(List<String> path, boolean defaultValue) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.define:(Ljava/util/List;Z)Lnet/neoforged/neoforge/common/ModConfigSpec$BooleanValue;");
        }

        public BooleanValue define(String path, Supplier<Boolean> defaultSupplier) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.define:(Ljava/lang/String;Ljava/util/function/Supplier;)Lnet/neoforged/neoforge/common/ModConfigSpec$BooleanValue;");
        }

        public BooleanValue define(List<String> path, Supplier<Boolean> defaultSupplier) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.define:(Ljava/util/List;Ljava/util/function/Supplier;)Lnet/neoforged/neoforge/common/ModConfigSpec$BooleanValue;");
        }

        // Pumpkin divergence: real body. The range is not enforced: it constrains what an
        // operator may write in a file, and there is no file.
        public DoubleValue defineInRange(String path, double defaultValue, double min, double max) {
            return new DoubleValue(defaultValue);
        }

        public DoubleValue defineInRange(List<String> path, double defaultValue, double min, double max) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.defineInRange:(Ljava/util/List;DDD)Lnet/neoforged/neoforge/common/ModConfigSpec$DoubleValue;");
        }

        public DoubleValue defineInRange(String path, Supplier<Double> defaultSupplier, double min, double max) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.defineInRange:(Ljava/lang/String;Ljava/util/function/Supplier;DD)Lnet/neoforged/neoforge/common/ModConfigSpec$DoubleValue;");
        }

        public DoubleValue defineInRange(List<String> path, Supplier<Double> defaultSupplier, double min, double max) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.defineInRange:(Ljava/util/List;Ljava/util/function/Supplier;DD)Lnet/neoforged/neoforge/common/ModConfigSpec$DoubleValue;");
        }

        // Pumpkin divergence: real body. See the double overload for the range.
        public IntValue defineInRange(String path, int defaultValue, int min, int max) {
            return new IntValue(defaultValue);
        }

        public IntValue defineInRange(List<String> path, int defaultValue, int min, int max) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.defineInRange:(Ljava/util/List;III)Lnet/neoforged/neoforge/common/ModConfigSpec$IntValue;");
        }

        public IntValue defineInRange(String path, Supplier<Integer> defaultSupplier, int min, int max) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.defineInRange:(Ljava/lang/String;Ljava/util/function/Supplier;II)Lnet/neoforged/neoforge/common/ModConfigSpec$IntValue;");
        }

        public IntValue defineInRange(List<String> path, Supplier<Integer> defaultSupplier, int min, int max) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.defineInRange:(Ljava/util/List;Ljava/util/function/Supplier;II)Lnet/neoforged/neoforge/common/ModConfigSpec$IntValue;");
        }

        public LongValue defineInRange(String path, long defaultValue, long min, long max) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.defineInRange:(Ljava/lang/String;JJJ)Lnet/neoforged/neoforge/common/ModConfigSpec$LongValue;");
        }

        public LongValue defineInRange(List<String> path, long defaultValue, long min, long max) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.defineInRange:(Ljava/util/List;JJJ)Lnet/neoforged/neoforge/common/ModConfigSpec$LongValue;");
        }

        public LongValue defineInRange(String path, Supplier<Long> defaultSupplier, long min, long max) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.defineInRange:(Ljava/lang/String;Ljava/util/function/Supplier;JJ)Lnet/neoforged/neoforge/common/ModConfigSpec$LongValue;");
        }

        public LongValue defineInRange(List<String> path, Supplier<Long> defaultSupplier, long min, long max) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.defineInRange:(Ljava/util/List;Ljava/util/function/Supplier;JJ)Lnet/neoforged/neoforge/common/ModConfigSpec$LongValue;");
        }

        // Pumpkin divergence: real body. The single-String overload, which is the one
        // both mods call -- the varargs one is a different method and implementing only
        // that left this still throwing.
        public Builder comment(String comment) {
            return this;
        }

        // Pumpkin divergence: real body. A comment is documentation for a config file
        // nobody writes yet, so it is accepted and dropped -- the builder chain must return
        // `this` for the mod's next call to land.
        public Builder comment(String... comment) {
            return this;
        }

        public Builder translation(String translationKey) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.translation:(Ljava/lang/String;)Lnet/neoforged/neoforge/common/ModConfigSpec$Builder;");
        }

        public Builder worldRestart() {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.worldRestart:()Lnet/neoforged/neoforge/common/ModConfigSpec$Builder;");
        }

        public Builder gameRestart() {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.gameRestart:()Lnet/neoforged/neoforge/common/ModConfigSpec$Builder;");
        }

        // Pumpkin divergence: real body. Sections nest, and a value's key is the whole path
        // -- two mods defining "enabled" under different sections must not collide.
        public Builder push(String path) {
            pumpkinPath.add(path);
            return this;
        }

        public Builder push(List<String> path) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.push:(Ljava/util/List;)Lnet/neoforged/neoforge/common/ModConfigSpec$Builder;");
        }

        // Pumpkin divergence: real body.
        public Builder pop() {
            if (!pumpkinPath.isEmpty()) {
                pumpkinPath.remove(pumpkinPath.size() - 1);
            }
            return this;
        }

        public Builder pop(int count) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.pop:(I)Lnet/neoforged/neoforge/common/ModConfigSpec$Builder;");
        }

        public <T> Pair<T, ModConfigSpec> configure(Function<Builder, T> consumer) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.configure:(Ljava/util/function/Function;)Lorg/apache/commons/lang3/tuple/Pair;");
        }

        // Pumpkin divergence: real body. The spec carries nothing: every value already
        // holds its own default, and there is no file to reconcile them against.
        public ModConfigSpec build() {
            return new ModConfigSpec();
        }

        public Builder() {
        }
    }

    private static class BuilderContext {

        private void validate(Object value, String message) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$BuilderContext.validate:(Ljava/lang/Object;Ljava/lang/String;)V");
        }

        private void validate(boolean value, String message) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$BuilderContext.validate:(ZLjava/lang/String;)V");
        }

        protected BuilderContext() {
        }
    }

    public static class Range<V extends Comparable<? super V>> implements Predicate<Object> {

        private Range(Class<V> clazz, V min, V max) {
        }

        public boolean test(Object t) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Range.test:(Ljava/lang/Object;)Z");
        }

        public String toString() {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Range.toString:()Ljava/lang/String;");
        }

        public Range() {
        }
    }

    public static class ValueSpec {

        private ValueSpec(Supplier<?> supplier, Predicate<Object> validator, BuilderContext context, List<String> path) {
        }

        public boolean test(Object value) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$ValueSpec.test:(Ljava/lang/Object;)Z");
        }

        public ValueSpec() {
        }
    }

    public static class ListValueSpec extends ValueSpec {

        private ListValueSpec(Supplier<?> supplier, Supplier<?> newElementSupplier, Predicate<Object> listValidator, Predicate<Object> elementValidator, BuilderContext context, List<String> path, Range<Integer> sizeRange) {
        }

        public ListValueSpec() {
        }
    }

    public static class ConfigValue<T> implements Supplier<T> {

        // Pumpkin divergence: this field, the constructor and get() carry real behaviour.
        //
        // The value returned is the default the mod itself declared. That is not a
        // fabricated zero: absent a config file it is the answer NeoForge gives too, and it
        // is the mod's own data rather than something invented here. What is missing is the
        // file -- an operator cannot yet change any of it.
        //
        // Not final: the generator synthesises a no-arg constructor for every class that
        // declares none and cannot initialise a field it does not know about. Nothing calls
        // that constructor -- a value always comes from define() -- so the only cost is this.
        private T pumpkinDefault;

        ConfigValue(T defaultValue) {
            this.pumpkinDefault = defaultValue;
        }

        ConfigValue(Builder parent, List<String> path, Supplier<T> defaultSupplier) {
            this(defaultSupplier.get());
        }

        public T get() {
            return pumpkinDefault;
        }

        public T getRaw() {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$ConfigValue.getRaw:()Ljava/lang/Object;");
        }

        public T getDefault() {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$ConfigValue.getDefault:()Ljava/lang/Object;");
        }

        public Builder next() {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$ConfigValue.next:()Lnet/neoforged/neoforge/common/ModConfigSpec$Builder;");
        }

        public void save() {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$ConfigValue.save:()V");
        }

        public void set(T value) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$ConfigValue.set:(Ljava/lang/Object;)V");
        }

        public ConfigValue() {
        }
    }

    public static class BooleanValue extends ConfigValue<Boolean> implements BooleanSupplier {

        // Pumpkin divergence: real body.
        BooleanValue(Boolean defaultValue) {
            super(defaultValue);
        }

        BooleanValue(Builder parent, List<String> path, Supplier<Boolean> defaultSupplier) {
        }

        public boolean getAsBoolean() {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$BooleanValue.getAsBoolean:()Z");
        }

        public BooleanValue() {
        }
    }

    public static class IntValue extends ConfigValue<Integer> implements IntSupplier {

        // Pumpkin divergence: real body.
        IntValue(Integer defaultValue) {
            super(defaultValue);
        }

        IntValue(Builder parent, List<String> path, Supplier<Integer> defaultSupplier) {
        }

        public Integer getRaw(Config config, List<String> path, Supplier<Integer> defaultSupplier) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$IntValue.getRaw:(Lcom/electronwill/nightconfig/core/Config;Ljava/util/List;Ljava/util/function/Supplier;)Ljava/lang/Integer;");
        }

        public int getAsInt() {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$IntValue.getAsInt:()I");
        }

        public IntValue() {
        }
    }

    public static class LongValue extends ConfigValue<Long> implements LongSupplier {

        LongValue(Builder parent, List<String> path, Supplier<Long> defaultSupplier) {
        }

        public Long getRaw(Config config, List<String> path, Supplier<Long> defaultSupplier) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$LongValue.getRaw:(Lcom/electronwill/nightconfig/core/Config;Ljava/util/List;Ljava/util/function/Supplier;)Ljava/lang/Long;");
        }

        public long getAsLong() {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$LongValue.getAsLong:()J");
        }

        public LongValue() {
        }
    }

    public static class DoubleValue extends ConfigValue<Double> implements DoubleSupplier {

        // Pumpkin divergence: real body.
        DoubleValue(Double defaultValue) {
            super(defaultValue);
        }

        DoubleValue(Builder parent, List<String> path, Supplier<Double> defaultSupplier) {
        }

        public Double getRaw(Config config, List<String> path, Supplier<Double> defaultSupplier) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$DoubleValue.getRaw:(Lcom/electronwill/nightconfig/core/Config;Ljava/util/List;Ljava/util/function/Supplier;)Ljava/lang/Double;");
        }

        public double getAsDouble() {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$DoubleValue.getAsDouble:()D");
        }

        public DoubleValue() {
        }
    }

    public static class EnumValue<T extends Enum<T>> extends ConfigValue<T> {

        EnumValue(Builder parent, List<String> path, Supplier<T> defaultSupplier, EnumGetMethod converter, Class<T> clazz) {
        }

        public T getRaw(Config config, List<String> path, Supplier<T> defaultSupplier) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$EnumValue.getRaw:(Lcom/electronwill/nightconfig/core/Config;Ljava/util/List;Ljava/util/function/Supplier;)Ljava/lang/Enum;");
        }

        public EnumValue() {
        }
    }

    public enum RestartType {

        NONE, WORLD, GAME;

        public RestartType with(RestartType other) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$RestartType.with:(Lnet/neoforged/neoforge/common/ModConfigSpec$RestartType;)Lnet/neoforged/neoforge/common/ModConfigSpec$RestartType;");
        }
    }

    public ModConfigSpec() {
    }
}
