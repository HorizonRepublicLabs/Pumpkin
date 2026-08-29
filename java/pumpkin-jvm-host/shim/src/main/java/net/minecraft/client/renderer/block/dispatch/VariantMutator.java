package net.minecraft.client.renderer.block.dispatch;

import java.util.function.UnaryOperator;

public interface VariantMutator extends UnaryOperator<Variant> {

    interface VariantProperty<T> {

        Variant apply(Variant input, T value);
    }
}
