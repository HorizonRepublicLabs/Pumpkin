package net.neoforged.neoforge.common;

import com.electronwill.nightconfig.core.Config;
import com.electronwill.nightconfig.core.EnumGetMethod;
import com.electronwill.nightconfig.core.UnmodifiableCommentedConfig;
import com.electronwill.nightconfig.core.UnmodifiableConfig;
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
        throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec.<init>:(Lcom/electronwill/nightconfig/core/UnmodifiableConfig;Lcom/electronwill/nightconfig/core/UnmodifiableConfig;Ljava/util/Map;Ljava/util/Map;)V");
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

    public void save() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec.save:()V");
    }

    public boolean isCorrect(UnmodifiableCommentedConfig config) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec.isCorrect:(Lcom/electronwill/nightconfig/core/UnmodifiableCommentedConfig;)Z");
    }

    public static class Builder {

        public <T> ConfigValue<T> define(String path, T defaultValue) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.define:(Ljava/lang/String;Ljava/lang/Object;)Lnet/neoforged/neoforge/common/ModConfigSpec$ConfigValue;");
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

        public BooleanValue define(String path, boolean defaultValue) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.define:(Ljava/lang/String;Z)Lnet/neoforged/neoforge/common/ModConfigSpec$BooleanValue;");
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

        public DoubleValue defineInRange(String path, double defaultValue, double min, double max) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.defineInRange:(Ljava/lang/String;DDD)Lnet/neoforged/neoforge/common/ModConfigSpec$DoubleValue;");
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

        public IntValue defineInRange(String path, int defaultValue, int min, int max) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.defineInRange:(Ljava/lang/String;III)Lnet/neoforged/neoforge/common/ModConfigSpec$IntValue;");
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

        public Builder comment(String comment) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.comment:(Ljava/lang/String;)Lnet/neoforged/neoforge/common/ModConfigSpec$Builder;");
        }

        public Builder comment(String... comment) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.comment:([Ljava/lang/String;)Lnet/neoforged/neoforge/common/ModConfigSpec$Builder;");
        }

        public Builder push(String path) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.push:(Ljava/lang/String;)Lnet/neoforged/neoforge/common/ModConfigSpec$Builder;");
        }

        public Builder push(List<String> path) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.push:(Ljava/util/List;)Lnet/neoforged/neoforge/common/ModConfigSpec$Builder;");
        }

        public Builder pop() {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.pop:()Lnet/neoforged/neoforge/common/ModConfigSpec$Builder;");
        }

        public Builder pop(int count) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.pop:(I)Lnet/neoforged/neoforge/common/ModConfigSpec$Builder;");
        }

        public <T> Pair<T, ModConfigSpec> configure(Function<Builder, T> consumer) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.configure:(Ljava/util/function/Function;)Lorg/apache/commons/lang3/tuple/Pair;");
        }

        public ModConfigSpec build() {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.build:()Lnet/neoforged/neoforge/common/ModConfigSpec;");
        }

        public Builder() {
        }
    }

    private static class BuilderContext {

        protected BuilderContext() {
        }
    }

    public static class Range<V extends Comparable<? super V>> implements Predicate<Object> {

        private Range(Class<V> clazz, V min, V max) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Range.<init>:(Ljava/lang/Class;Ljava/lang/Comparable;Ljava/lang/Comparable;)V");
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
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$ValueSpec.<init>:(Ljava/util/function/Supplier;Ljava/util/function/Predicate;Lnet/neoforged/neoforge/common/ModConfigSpec$BuilderContext;Ljava/util/List;)V");
        }

        public boolean test(Object value) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$ValueSpec.test:(Ljava/lang/Object;)Z");
        }

        public ValueSpec() {
        }
    }

    public static class ListValueSpec extends ValueSpec {

        private ListValueSpec(Supplier<?> supplier, Supplier<?> newElementSupplier, Predicate<Object> listValidator, Predicate<Object> elementValidator, BuilderContext context, List<String> path, Range<Integer> sizeRange) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$ListValueSpec.<init>:(Ljava/util/function/Supplier;Ljava/util/function/Supplier;Ljava/util/function/Predicate;Ljava/util/function/Predicate;Lnet/neoforged/neoforge/common/ModConfigSpec$BuilderContext;Ljava/util/List;Lnet/neoforged/neoforge/common/ModConfigSpec$Range;)V");
        }

        public ListValueSpec() {
        }
    }

    public static class ConfigValue<T> implements Supplier<T> {

        ConfigValue(Builder parent, List<String> path, Supplier<T> defaultSupplier) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$ConfigValue.<init>:(Lnet/neoforged/neoforge/common/ModConfigSpec$Builder;Ljava/util/List;Ljava/util/function/Supplier;)V");
        }

        public T get() {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$ConfigValue.get:()Ljava/lang/Object;");
        }

        public T getRaw() {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$ConfigValue.getRaw:()Ljava/lang/Object;");
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

        BooleanValue(Builder parent, List<String> path, Supplier<Boolean> defaultSupplier) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$BooleanValue.<init>:(Lnet/neoforged/neoforge/common/ModConfigSpec$Builder;Ljava/util/List;Ljava/util/function/Supplier;)V");
        }

        public boolean getAsBoolean() {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$BooleanValue.getAsBoolean:()Z");
        }

        public BooleanValue() {
        }
    }

    public static class IntValue extends ConfigValue<Integer> implements IntSupplier {

        IntValue(Builder parent, List<String> path, Supplier<Integer> defaultSupplier) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$IntValue.<init>:(Lnet/neoforged/neoforge/common/ModConfigSpec$Builder;Ljava/util/List;Ljava/util/function/Supplier;)V");
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
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$LongValue.<init>:(Lnet/neoforged/neoforge/common/ModConfigSpec$Builder;Ljava/util/List;Ljava/util/function/Supplier;)V");
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

        DoubleValue(Builder parent, List<String> path, Supplier<Double> defaultSupplier) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$DoubleValue.<init>:(Lnet/neoforged/neoforge/common/ModConfigSpec$Builder;Ljava/util/List;Ljava/util/function/Supplier;)V");
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
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$EnumValue.<init>:(Lnet/neoforged/neoforge/common/ModConfigSpec$Builder;Ljava/util/List;Ljava/util/function/Supplier;Lcom/electronwill/nightconfig/core/EnumGetMethod;Ljava/lang/Class;)V");
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
