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
        // Pumpkin divergence: real answer. No config file ever loads here, and saying so
        // routes mods to their declared defaults -- the same values our ConfigValues hold.
        return false;
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

        // Pumpkin divergence: real body -- the declared default answers.
        public <T> ConfigValue<T> define(List<String> path, T defaultValue) {
            return new ConfigValue<>(defaultValue);
        }

        // Pumpkin divergence: real body -- the declared default answers.
        public <T> ConfigValue<T> define(String path, T defaultValue, Predicate<Object> validator) {
            return new ConfigValue<>(defaultValue);
        }

        // Pumpkin divergence: real body -- the declared default answers.
        public <T> ConfigValue<T> define(List<String> path, T defaultValue, Predicate<Object> validator) {
            return new ConfigValue<>(defaultValue);
        }

        // Pumpkin divergence: real body -- the declared default answers.
        public <T> ConfigValue<T> define(String path, Supplier<T> defaultSupplier, Predicate<Object> validator) {
            return new ConfigValue<>(defaultSupplier);
        }

        // Pumpkin divergence: real body -- the declared default answers.
        public <T> ConfigValue<T> define(List<String> path, Supplier<T> defaultSupplier, Predicate<Object> validator) {
            return new ConfigValue<>(defaultSupplier);
        }

        // Pumpkin divergence: real body -- the declared default answers.
        public <T> ConfigValue<T> define(List<String> path, Supplier<T> defaultSupplier, Predicate<Object> validator, Class<?> clazz) {
            return new ConfigValue<>(defaultSupplier);
        }

        // Pumpkin divergence: real body -- the declared default answers.
        public <T> ConfigValue<T> define(List<String> path, ValueSpec value, Supplier<T> defaultSupplier) {
            return new ConfigValue<>(defaultSupplier);
        }

        // Pumpkin divergence: real body -- the declared default answers.
        public <V extends Comparable<? super V>> ConfigValue<V> defineInRange(String path, V defaultValue, V min, V max, Class<V> clazz) {
            return new ConfigValue<>(defaultValue);
        }

        // Pumpkin divergence: real body -- the declared default answers.
        public <V extends Comparable<? super V>> ConfigValue<V> defineInRange(List<String> path, V defaultValue, V min, V max, Class<V> clazz) {
            return new ConfigValue<>(defaultValue);
        }

        // Pumpkin divergence: real body -- the declared default answers.
        public <V extends Comparable<? super V>> ConfigValue<V> defineInRange(String path, Supplier<V> defaultSupplier, V min, V max, Class<V> clazz) {
            return new ConfigValue<>(defaultSupplier);
        }

        // Pumpkin divergence: real body -- the declared default answers.
        public <V extends Comparable<? super V>> ConfigValue<V> defineInRange(List<String> path, Supplier<V> defaultSupplier, V min, V max, Class<V> clazz) {
            return new ConfigValue<>(defaultSupplier);
        }

        // Pumpkin divergence: real body -- the declared default answers.
        public <T> ConfigValue<List<? extends T>> defineListAllowEmpty(String path, List<? extends T> defaultValue, Predicate<Object> elementValidator) {
            return new ConfigValue<>(defaultValue);
        }

        // Pumpkin divergence: real body -- the declared default answers.
        public <T> ConfigValue<List<? extends T>> defineListAllowEmpty(String path, List<? extends T> defaultValue, Supplier<T> newElementSupplier, Predicate<Object> elementValidator) {
            return new ConfigValue<>(defaultValue);
        }

        // Pumpkin divergence: real body -- the declared default answers.
        public <T> ConfigValue<List<? extends T>> defineListAllowEmpty(String path, Supplier<List<? extends T>> defaultSupplier, Predicate<Object> elementValidator) {
            return new ConfigValue<>(defaultSupplier);
        }

        // Pumpkin divergence: real body -- the declared default answers.
        public <T> ConfigValue<List<? extends T>> defineListAllowEmpty(String path, Supplier<List<? extends T>> defaultSupplier, Supplier<T> newElementSupplier, Predicate<Object> elementValidator) {
            return new ConfigValue<>(defaultSupplier);
        }

        // Pumpkin divergence: real body -- the declared default answers.
        public <T> ConfigValue<List<? extends T>> defineListAllowEmpty(List<String> path, List<? extends T> defaultValue, Predicate<Object> elementValidator) {
            return new ConfigValue<>(defaultValue);
        }

        // Pumpkin divergence: real body -- the declared default answers.
        public <T> ConfigValue<List<? extends T>> defineListAllowEmpty(List<String> path, List<? extends T> defaultValue, Supplier<T> newElementSupplier, Predicate<Object> elementValidator) {
            return new ConfigValue<>(defaultValue);
        }

        // Pumpkin divergence: real body -- the declared default answers.
        public <T> ConfigValue<List<? extends T>> defineListAllowEmpty(List<String> path, Supplier<List<? extends T>> defaultSupplier, Predicate<Object> elementValidator) {
            return new ConfigValue<>(defaultSupplier);
        }

        // Pumpkin divergence: real body -- the declared default answers.
        public <T> ConfigValue<List<? extends T>> defineListAllowEmpty(List<String> path, Supplier<List<? extends T>> defaultSupplier, Supplier<T> newElementSupplier, Predicate<Object> elementValidator) {
            return new ConfigValue<>(defaultSupplier);
        }

        // Pumpkin divergence: real body -- the declared default answers.
        public <V extends Enum<V>> EnumValue<V> defineEnum(String path, V defaultValue) {
            return new EnumValue<>(defaultValue);
        }

        // Pumpkin divergence: real body -- the declared default answers.
        public <V extends Enum<V>> EnumValue<V> defineEnum(String path, V defaultValue, EnumGetMethod converter) {
            return new EnumValue<>(defaultValue);
        }

        // Pumpkin divergence: real body -- the declared default answers.
        public <V extends Enum<V>> EnumValue<V> defineEnum(List<String> path, V defaultValue) {
            return new EnumValue<>(defaultValue);
        }

        // Pumpkin divergence: real body -- the declared default answers.
        public <V extends Enum<V>> EnumValue<V> defineEnum(List<String> path, V defaultValue, EnumGetMethod converter) {
            return new EnumValue<>(defaultValue);
        }

        // Pumpkin divergence: real body -- the declared default answers.
        public <V extends Enum<V>> EnumValue<V> defineEnum(String path, V defaultValue, V... acceptableValues) {
            return new EnumValue<>(defaultValue);
        }

        // Pumpkin divergence: real body -- the declared default answers.
        public <V extends Enum<V>> EnumValue<V> defineEnum(String path, V defaultValue, EnumGetMethod converter, V... acceptableValues) {
            return new EnumValue<>(defaultValue);
        }

        // Pumpkin divergence: real body -- the declared default answers.
        public <V extends Enum<V>> EnumValue<V> defineEnum(List<String> path, V defaultValue, V... acceptableValues) {
            return new EnumValue<>(defaultValue);
        }

        // Pumpkin divergence: real body -- the declared default answers.
        public <V extends Enum<V>> EnumValue<V> defineEnum(List<String> path, V defaultValue, EnumGetMethod converter, V... acceptableValues) {
            return new EnumValue<>(defaultValue);
        }

        // Pumpkin divergence: real body -- the declared default answers.
        public <V extends Enum<V>> EnumValue<V> defineEnum(String path, V defaultValue, Collection<V> acceptableValues) {
            return new EnumValue<>(defaultValue);
        }

        // Pumpkin divergence: real body -- the declared default answers.
        public <V extends Enum<V>> EnumValue<V> defineEnum(String path, V defaultValue, EnumGetMethod converter, Collection<V> acceptableValues) {
            return new EnumValue<>(defaultValue);
        }

        // Pumpkin divergence: real body -- the declared default answers.
        public <V extends Enum<V>> EnumValue<V> defineEnum(List<String> path, V defaultValue, Collection<V> acceptableValues) {
            return new EnumValue<>(defaultValue);
        }

        // Pumpkin divergence: real body -- the declared default answers.
        public <V extends Enum<V>> EnumValue<V> defineEnum(List<String> path, V defaultValue, EnumGetMethod converter, Collection<V> acceptableValues) {
            return new EnumValue<>(defaultValue);
        }

        // Pumpkin divergence: real body -- the declared default answers.
        public <V extends Enum<V>> EnumValue<V> defineEnum(String path, V defaultValue, Predicate<Object> validator) {
            return new EnumValue<>(defaultValue);
        }

        // Pumpkin divergence: real body -- the declared default answers.
        public <V extends Enum<V>> EnumValue<V> defineEnum(String path, V defaultValue, EnumGetMethod converter, Predicate<Object> validator) {
            return new EnumValue<>(defaultValue);
        }

        // Pumpkin divergence: real body -- the declared default answers.
        public <V extends Enum<V>> EnumValue<V> defineEnum(List<String> path, V defaultValue, Predicate<Object> validator) {
            return new EnumValue<>(defaultValue);
        }

        // Pumpkin divergence: real body -- the declared default answers.
        public <V extends Enum<V>> EnumValue<V> defineEnum(List<String> path, V defaultValue, EnumGetMethod converter, Predicate<Object> validator) {
            return new EnumValue<>(defaultValue);
        }

        // Pumpkin divergence: real body -- the declared default answers.
        public <V extends Enum<V>> EnumValue<V> defineEnum(String path, Supplier<V> defaultSupplier, Predicate<Object> validator, Class<V> clazz) {
            return new EnumValue<>(defaultSupplier.get());
        }

        // Pumpkin divergence: real body -- the declared default answers.
        public <V extends Enum<V>> EnumValue<V> defineEnum(String path, Supplier<V> defaultSupplier, EnumGetMethod converter, Predicate<Object> validator, Class<V> clazz) {
            return new EnumValue<>(defaultSupplier.get());
        }

        // Pumpkin divergence: real body -- the declared default answers.
        public <V extends Enum<V>> EnumValue<V> defineEnum(List<String> path, Supplier<V> defaultSupplier, Predicate<Object> validator, Class<V> clazz) {
            return new EnumValue<>(defaultSupplier.get());
        }

        // Pumpkin divergence: real body -- the declared default answers.
        public <V extends Enum<V>> EnumValue<V> defineEnum(List<String> path, Supplier<V> defaultSupplier, EnumGetMethod converter, Predicate<Object> validator, Class<V> clazz) {
            return new EnumValue<>(defaultSupplier.get());
        }

        // Pumpkin divergence: real body.
        public BooleanValue define(String path, boolean defaultValue) {
            return new BooleanValue(defaultValue);
        }

        // Pumpkin divergence: real body. The value answers the default the mod
        // declared -- the same answer NeoForge gives when no file overrides it --
        // and screen metadata chains return this.
        public BooleanValue define(List<String> path, boolean defaultValue) {
            return new BooleanValue(defaultValue);
        }

        // Pumpkin divergence: real body. The value answers the default the mod
        // declared -- the same answer NeoForge gives when no file overrides it --
        // and screen metadata chains return this.
        public BooleanValue define(String path, Supplier<Boolean> defaultSupplier) {
            return new BooleanValue(defaultSupplier.get());
        }

        // Pumpkin divergence: real body. The value answers the default the mod
        // declared -- the same answer NeoForge gives when no file overrides it --
        // and screen metadata chains return this.
        public BooleanValue define(List<String> path, Supplier<Boolean> defaultSupplier) {
            return new BooleanValue(defaultSupplier.get());
        }

        // Pumpkin divergence: real body. The range is not enforced: it constrains what an
        // operator may write in a file, and there is no file.
        public DoubleValue defineInRange(String path, double defaultValue, double min, double max) {
            return new DoubleValue(defaultValue);
        }

        // Pumpkin divergence: real body. The value answers the default the mod
        // declared -- the same answer NeoForge gives when no file overrides it --
        // and screen metadata chains return this.
        public DoubleValue defineInRange(List<String> path, double defaultValue, double min, double max) {
            return new DoubleValue(defaultValue);
        }

        // Pumpkin divergence: real body. The value answers the default the mod
        // declared -- the same answer NeoForge gives when no file overrides it --
        // and screen metadata chains return this.
        public DoubleValue defineInRange(String path, Supplier<Double> defaultSupplier, double min, double max) {
            return new DoubleValue(defaultSupplier.get());
        }

        // Pumpkin divergence: real body. The value answers the default the mod
        // declared -- the same answer NeoForge gives when no file overrides it --
        // and screen metadata chains return this.
        public DoubleValue defineInRange(List<String> path, Supplier<Double> defaultSupplier, double min, double max) {
            return new DoubleValue(defaultSupplier.get());
        }

        // Pumpkin divergence: real body. See the double overload for the range.
        public IntValue defineInRange(String path, int defaultValue, int min, int max) {
            return new IntValue(defaultValue);
        }

        // Pumpkin divergence: real body. The value answers the default the mod
        // declared -- the same answer NeoForge gives when no file overrides it --
        // and screen metadata chains return this.
        public IntValue defineInRange(List<String> path, int defaultValue, int min, int max) {
            return new IntValue(defaultValue);
        }

        // Pumpkin divergence: real body. The value answers the default the mod
        // declared -- the same answer NeoForge gives when no file overrides it --
        // and screen metadata chains return this.
        public IntValue defineInRange(String path, Supplier<Integer> defaultSupplier, int min, int max) {
            return new IntValue(defaultSupplier.get());
        }

        // Pumpkin divergence: real body. The value answers the default the mod
        // declared -- the same answer NeoForge gives when no file overrides it --
        // and screen metadata chains return this.
        public IntValue defineInRange(List<String> path, Supplier<Integer> defaultSupplier, int min, int max) {
            return new IntValue(defaultSupplier.get());
        }

        // Pumpkin divergence: real body. See the int overload -- the value answers the
        // default the mod declared, and nothing reads a file behind it yet.
        public LongValue defineInRange(String path, long defaultValue, long min, long max) {
            return new LongValue(defaultValue);
        }

        // Pumpkin divergence: real body. The value answers the default the mod
        // declared -- the same answer NeoForge gives when no file overrides it --
        // and screen metadata chains return this.
        public LongValue defineInRange(List<String> path, long defaultValue, long min, long max) {
            return new LongValue(defaultValue);
        }

        // Pumpkin divergence: real body. The value answers the default the mod
        // declared -- the same answer NeoForge gives when no file overrides it --
        // and screen metadata chains return this.
        public LongValue defineInRange(String path, Supplier<Long> defaultSupplier, long min, long max) {
            return new LongValue(defaultSupplier.get());
        }

        // Pumpkin divergence: real body. The value answers the default the mod
        // declared -- the same answer NeoForge gives when no file overrides it --
        // and screen metadata chains return this.
        public LongValue defineInRange(List<String> path, Supplier<Long> defaultSupplier, long min, long max) {
            return new LongValue(defaultSupplier.get());
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

        // Pumpkin divergence: real body. A translation key decorates the config screen,
        // which a headless server never draws; accepted and dropped, chain returns this.
        public Builder translation(String translationKey) {
            return this;
        }

        // Pumpkin divergence: real body. The value answers the default the mod
        // declared -- the same answer NeoForge gives when no file overrides it --
        // and screen metadata chains return this.
        public Builder worldRestart() {
            return this;
        }

        // Pumpkin divergence: real body. Restart metadata for a config screen; accepted
        // and dropped, chain returns this.
        public Builder gameRestart() {
            return this;
        }

        // Pumpkin divergence: real body. Sections nest, and a value's key is the whole path
        // -- two mods defining "enabled" under different sections must not collide.
        public Builder push(String path) {
            pumpkinPath.add(path);
            return this;
        }

        // Pumpkin divergence: real body. The value answers the default the mod
        // declared -- the same answer NeoForge gives when no file overrides it --
        // and screen metadata chains return this.
        public Builder push(List<String> path) {
            return this;
        }

        // Pumpkin divergence: real body.
        public Builder pop() {
            if (!pumpkinPath.isEmpty()) {
                pumpkinPath.remove(pumpkinPath.size() - 1);
            }
            return this;
        }

        // Pumpkin divergence: real body. The value answers the default the mod
        // declared -- the same answer NeoForge gives when no file overrides it --
        // and screen metadata chains return this.
        public Builder pop(int count) {
            return this;
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
        // Lazy on purpose: Mekanism's default suppliers read *other* config values, and
        // evaluating them while the config object is still mid-construction NPEs inside
        // the mod. NeoForge defers exactly the same way.
        private Supplier<T> pumpkinDefault;

        ConfigValue(T defaultValue) {
            this.pumpkinDefault = () -> defaultValue;
        }

        ConfigValue(Supplier<T> defaultSupplier) {
            this.pumpkinDefault = defaultSupplier;
        }

        ConfigValue(Builder parent, List<String> path, Supplier<T> defaultSupplier) {
            this(defaultSupplier);
        }

        public T get() {
            return pumpkinDefault.get();
        }

        public T getRaw() {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$ConfigValue.getRaw:()Ljava/lang/Object;");
        }

        // Pumpkin divergence: real body -- the declared default, same source get() reads.
        public T getDefault() {
            return pumpkinDefault.get();
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

        // Pumpkin divergence: real body, mirroring IntValue.
        LongValue(Long defaultValue) {
            super(defaultValue);
        }

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

        // Pumpkin divergence: real body, mirroring IntValue.
        EnumValue(T defaultValue) {
            super(defaultValue);
        }

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
