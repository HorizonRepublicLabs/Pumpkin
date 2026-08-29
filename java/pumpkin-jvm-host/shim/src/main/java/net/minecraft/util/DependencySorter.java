package net.minecraft.util;

import java.util.function.Consumer;

public class DependencySorter<K, V extends DependencySorter.Entry<K>> {

    public interface Entry<K> {

        void visitRequiredDependencies(final Consumer<K> output);

        void visitOptionalDependencies(final Consumer<K> output);
    }

    public DependencySorter() {
    }
}
