package net.minecraft.client;

import com.mojang.serialization.Codec;
import java.util.List;
import java.util.Optional;
import java.util.function.BooleanSupplier;
import java.util.function.Function;
import java.util.function.IntSupplier;
import java.util.function.Supplier;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractOptionSliderButton;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.client.gui.components.ResettableOptionWidget;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;
import dev.pumpkin.shim.Unimplemented;

public final class OptionInstance<T> {

    public OptionInstance(String captionId, OptionInstance.TooltipSupplier<T> tooltip, OptionInstance.CaptionBasedToString<T> toString, OptionInstance.ValueSet<T> values, T initialValue, OptionInstance.ValueUpdateListener<? super T> onValueUpdate) {
    }

    public OptionInstance(String captionId, OptionInstance.TooltipSupplier<T> tooltip, OptionInstance.CaptionBasedToString<T> toString, OptionInstance.ValueSet<T> values, Codec<T> codec, T initialValue, OptionInstance.ValueUpdateListener<? super T> onValueUpdate) {
    }

    public T get() {
        throw Unimplemented.forMember("net/minecraft/client/OptionInstance.get:()Ljava/lang/Object;");
    }

    public Codec<T> codec() {
        throw Unimplemented.forMember("net/minecraft/client/OptionInstance.codec:()Lcom/mojang/serialization/Codec;");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/client/OptionInstance.toString:()Ljava/lang/String;");
    }

    public void set(T value) {
        throw Unimplemented.forMember("net/minecraft/client/OptionInstance.set:(Ljava/lang/Object;)V");
    }

    public record AltEnum<T>(List<T> values, List<T> altValues, BooleanSupplier altCondition, OptionInstance.CycleableValueSet.ValueSetter<T> valueSetter, Codec<T> codec) implements OptionInstance.CycleableValueSet<T> {

        public CycleButton.ValueListSupplier<T> valueListSupplier() {
            throw Unimplemented.forMember("net/minecraft/client/OptionInstance$AltEnum.valueListSupplier:()Lnet/minecraft/client/gui/components/CycleButton$ValueListSupplier;");
        }

        public Optional<T> validateValue(T value) {
            throw Unimplemented.forMember("net/minecraft/client/OptionInstance$AltEnum.validateValue:(Ljava/lang/Object;)Ljava/util/Optional;");
        }
    }

    public interface CaptionBasedToString<T> {

        Component toString(Component caption, T value);
    }

    public record ClampingLazyMaxIntRange(int minInclusive, IntSupplier maxSupplier, int encodableMaxInclusive) implements OptionInstance.IntRangeBase, OptionInstance.SliderableOrCyclableValueSet<Integer> {

        public Optional<Integer> validateValue(Integer value) {
            throw Unimplemented.forMember("net/minecraft/client/OptionInstance$ClampingLazyMaxIntRange.validateValue:(Ljava/lang/Integer;)Ljava/util/Optional;");
        }

        public int maxInclusive() {
            throw Unimplemented.forMember("net/minecraft/client/OptionInstance$ClampingLazyMaxIntRange.maxInclusive:()I");
        }

        public Codec<Integer> codec() {
            throw Unimplemented.forMember("net/minecraft/client/OptionInstance$ClampingLazyMaxIntRange.codec:()Lcom/mojang/serialization/Codec;");
        }

        public boolean createCycleButton() {
            throw Unimplemented.forMember("net/minecraft/client/OptionInstance$ClampingLazyMaxIntRange.createCycleButton:()Z");
        }

        public CycleButton.ValueListSupplier<Integer> valueListSupplier() {
            throw Unimplemented.forMember("net/minecraft/client/OptionInstance$ClampingLazyMaxIntRange.valueListSupplier:()Lnet/minecraft/client/gui/components/CycleButton$ValueListSupplier;");
        }
    }

    public interface CycleableValueSet<T> extends OptionInstance.ValueSet<T> {

        CycleButton.ValueListSupplier<T> valueListSupplier();

        default Function<OptionInstance<T>, AbstractWidget> createButton(OptionInstance.TooltipSupplier<T> tooltip, Options options, int x, int y, int width, OptionInstance.ValueUpdateListener<? super T> onValueChanged) {
            throw Unimplemented.forMember("net/minecraft/client/OptionInstance$CycleableValueSet.createButton:(Lnet/minecraft/client/OptionInstance$TooltipSupplier;Lnet/minecraft/client/Options;IIILnet/minecraft/client/OptionInstance$ValueUpdateListener;)Ljava/util/function/Function;");
        }

        interface ValueSetter<T> {

            void set(final OptionInstance<T> instance, final T value);
        }
    }

    public record Enum<T>(List<T> values, Codec<T> codec) implements OptionInstance.CycleableValueSet<T> {

        public Optional<T> validateValue(T value) {
            throw Unimplemented.forMember("net/minecraft/client/OptionInstance$Enum.validateValue:(Ljava/lang/Object;)Ljava/util/Optional;");
        }

        public CycleButton.ValueListSupplier<T> valueListSupplier() {
            throw Unimplemented.forMember("net/minecraft/client/OptionInstance$Enum.valueListSupplier:()Lnet/minecraft/client/gui/components/CycleButton$ValueListSupplier;");
        }
    }

    public record IntRange(int minInclusive, int maxInclusive, boolean applyValueImmediately) implements OptionInstance.IntRangeBase {

        public IntRange(int minInclusive, int maxInclusive) {
            this((int) 0, (int) 0, (boolean) false);
        }

        public Optional<Integer> validateValue(Integer value) {
            throw Unimplemented.forMember("net/minecraft/client/OptionInstance$IntRange.validateValue:(Ljava/lang/Integer;)Ljava/util/Optional;");
        }

        public Codec<Integer> codec() {
            throw Unimplemented.forMember("net/minecraft/client/OptionInstance$IntRange.codec:()Lcom/mojang/serialization/Codec;");
        }
    }

    public interface IntRangeBase extends OptionInstance.SliderableValueSet<Integer> {

        int minInclusive();

        int maxInclusive();

        default Optional<Integer> next(Integer current) {
            throw Unimplemented.forMember("net/minecraft/client/OptionInstance$IntRangeBase.next:(Ljava/lang/Integer;)Ljava/util/Optional;");
        }

        default double toSliderValue(Integer value) {
            throw Unimplemented.forMember("net/minecraft/client/OptionInstance$IntRangeBase.toSliderValue:(Ljava/lang/Integer;)D");
        }

        default Integer fromSliderValue(double slider) {
            throw Unimplemented.forMember("net/minecraft/client/OptionInstance$IntRangeBase.fromSliderValue:(D)Ljava/lang/Integer;");
        }
    }

    public record LazyEnum<T>(Supplier<List<T>> values, Function<T, Optional<T>> validateValue, Codec<T> codec) implements OptionInstance.CycleableValueSet<T> {

        public Optional<T> validateValue(T value) {
            throw Unimplemented.forMember("net/minecraft/client/OptionInstance$LazyEnum.validateValue:(Ljava/lang/Object;)Ljava/util/Optional;");
        }

        public CycleButton.ValueListSupplier<T> valueListSupplier() {
            throw Unimplemented.forMember("net/minecraft/client/OptionInstance$LazyEnum.valueListSupplier:()Lnet/minecraft/client/gui/components/CycleButton$ValueListSupplier;");
        }
    }

    public static final class OptionInstanceSliderButton<N> extends AbstractOptionSliderButton implements ResettableOptionWidget {

        private OptionInstanceSliderButton(Options options, int x, int y, int width, int height, OptionInstance<N> instance, OptionInstance.SliderableValueSet<N> values, OptionInstance.TooltipSupplier<N> tooltipSupplier, OptionInstance.ValueUpdateListener<? super N> onValueChanged, boolean applyValueImmediately) {
        }

        protected void updateMessage() {
            throw Unimplemented.forMember("net/minecraft/client/OptionInstance$OptionInstanceSliderButton.updateMessage:()V");
        }

        protected void applyValue() {
            throw Unimplemented.forMember("net/minecraft/client/OptionInstance$OptionInstanceSliderButton.applyValue:()V");
        }

        public void resetValue() {
            throw Unimplemented.forMember("net/minecraft/client/OptionInstance$OptionInstanceSliderButton.resetValue:()V");
        }

        public void extractWidgetRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
            throw Unimplemented.forMember("net/minecraft/client/OptionInstance$OptionInstanceSliderButton.extractWidgetRenderState:(Lnet/minecraft/client/gui/GuiGraphicsExtractor;IIF)V");
        }

        public void onRelease(MouseButtonEvent event) {
            throw Unimplemented.forMember("net/minecraft/client/OptionInstance$OptionInstanceSliderButton.onRelease:(Lnet/minecraft/client/input/MouseButtonEvent;)V");
        }

        public boolean keyPressed(KeyEvent event) {
            throw Unimplemented.forMember("net/minecraft/client/OptionInstance$OptionInstanceSliderButton.keyPressed:(Lnet/minecraft/client/input/KeyEvent;)Z");
        }

        public OptionInstanceSliderButton() {
        }
    }

    public record SliderableEnum<T>(List<T> values, Codec<T> codec) implements OptionInstance.SliderableValueSet<T> {

        public double toSliderValue(T value) {
            throw Unimplemented.forMember("net/minecraft/client/OptionInstance$SliderableEnum.toSliderValue:(Ljava/lang/Object;)D");
        }

        public Optional<T> next(T current) {
            throw Unimplemented.forMember("net/minecraft/client/OptionInstance$SliderableEnum.next:(Ljava/lang/Object;)Ljava/util/Optional;");
        }

        public Optional<T> previous(T current) {
            throw Unimplemented.forMember("net/minecraft/client/OptionInstance$SliderableEnum.previous:(Ljava/lang/Object;)Ljava/util/Optional;");
        }

        public T fromSliderValue(double slider) {
            throw Unimplemented.forMember("net/minecraft/client/OptionInstance$SliderableEnum.fromSliderValue:(D)Ljava/lang/Object;");
        }

        public Optional<T> validateValue(T value) {
            throw Unimplemented.forMember("net/minecraft/client/OptionInstance$SliderableEnum.validateValue:(Ljava/lang/Object;)Ljava/util/Optional;");
        }
    }

    public interface SliderableOrCyclableValueSet<T> extends OptionInstance.SliderableValueSet<T>, OptionInstance.CycleableValueSet<T> {

        boolean createCycleButton();

        default Function<OptionInstance<T>, AbstractWidget> createButton(OptionInstance.TooltipSupplier<T> tooltip, Options options, int x, int y, int width, OptionInstance.ValueUpdateListener<? super T> onValueChanged) {
            throw Unimplemented.forMember("net/minecraft/client/OptionInstance$SliderableOrCyclableValueSet.createButton:(Lnet/minecraft/client/OptionInstance$TooltipSupplier;Lnet/minecraft/client/Options;IIILnet/minecraft/client/OptionInstance$ValueUpdateListener;)Ljava/util/function/Function;");
        }
    }

    public interface SliderableValueSet<T> extends OptionInstance.ValueSet<T> {

        double toSliderValue(final T value);

        default Optional<T> next(T current) {
            throw Unimplemented.forMember("net/minecraft/client/OptionInstance$SliderableValueSet.next:(Ljava/lang/Object;)Ljava/util/Optional;");
        }

        T fromSliderValue(final double slider);

        default Function<OptionInstance<T>, AbstractWidget> createButton(OptionInstance.TooltipSupplier<T> tooltip, Options options, int x, int y, int width, OptionInstance.ValueUpdateListener<? super T> onValueChanged) {
            throw Unimplemented.forMember("net/minecraft/client/OptionInstance$SliderableValueSet.createButton:(Lnet/minecraft/client/OptionInstance$TooltipSupplier;Lnet/minecraft/client/Options;IIILnet/minecraft/client/OptionInstance$ValueUpdateListener;)Ljava/util/function/Function;");
        }
    }

    public interface TooltipSupplier<T> {

        Tooltip apply(T value);
    }

    public enum UnitDouble implements OptionInstance.SliderableValueSet<Double> {

        INSTANCE;

        public Optional<Double> validateValue(Double value) {
            throw Unimplemented.forMember("net/minecraft/client/OptionInstance$UnitDouble.validateValue:(Ljava/lang/Double;)Ljava/util/Optional;");
        }

        public double toSliderValue(Double value) {
            throw Unimplemented.forMember("net/minecraft/client/OptionInstance$UnitDouble.toSliderValue:(Ljava/lang/Double;)D");
        }

        public Double fromSliderValue(double slider) {
            throw Unimplemented.forMember("net/minecraft/client/OptionInstance$UnitDouble.fromSliderValue:(D)Ljava/lang/Double;");
        }

        public Codec<Double> codec() {
            throw Unimplemented.forMember("net/minecraft/client/OptionInstance$UnitDouble.codec:()Lcom/mojang/serialization/Codec;");
        }
    }

    public interface ValueSet<T> {

        Function<OptionInstance<T>, AbstractWidget> createButton(final OptionInstance.TooltipSupplier<T> tooltip, Options options, final int x, final int y, final int width, final OptionInstance.ValueUpdateListener<? super T> onValueChanged);

        Optional<T> validateValue(final T value);

        Codec<T> codec();
    }

    public interface ValueUpdateListener<T> {

        void valueChanged(T newValue);
    }

    public OptionInstance() {
    }
}
