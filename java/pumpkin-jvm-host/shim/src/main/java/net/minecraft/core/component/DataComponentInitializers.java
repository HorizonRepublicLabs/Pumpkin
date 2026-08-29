package net.minecraft.core.component;

import java.util.Map;
import java.util.function.BiConsumer;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import dev.pumpkin.shim.Unimplemented;

public class DataComponentInitializers {

    public <T> void add(ResourceKey<T> key, DataComponentInitializers.Initializer<T> initializer) {
        throw Unimplemented.forMember("net/minecraft/core/component/DataComponentInitializers.add:(Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/core/component/DataComponentInitializers$Initializer;)V");
    }

    private record BakedEntry<T>(Holder.Reference<T> element, DataComponentMap components) {

        public void apply() {
            throw Unimplemented.forMember("net/minecraft/core/component/DataComponentInitializers$BakedEntry.apply:()V");
        }
    }

    public interface Initializer<T> {

        void run(DataComponentMap.Builder components, HolderLookup.Provider context, ResourceKey<T> key);

        default <C> DataComponentInitializers.Initializer<T> add(DataComponentType<C> type, C value) {
            throw Unimplemented.forMember("net/minecraft/core/component/DataComponentInitializers$Initializer.add:(Lnet/minecraft/core/component/DataComponentType;Ljava/lang/Object;)Lnet/minecraft/core/component/DataComponentInitializers$Initializer;");
        }
    }

    private record InitializerEntry<T>(ResourceKey<T> key, DataComponentInitializers.Initializer<T> initializer) {
    }

    private record PendingComponentBuilders<T>(ResourceKey<? extends Registry<T>> registryKey, Map<ResourceKey<T>, DataComponentMap.Builder> builders) {
    }

    public interface PendingComponents<T> {

        ResourceKey<? extends Registry<? extends T>> key();

        void forEach(BiConsumer<Holder.Reference<T>, DataComponentMap> output);

        void apply();
    }

    public interface SingleComponentInitializer<C> {

        C create(HolderLookup.Provider context);
    }

    protected DataComponentInitializers() {
    }
}
