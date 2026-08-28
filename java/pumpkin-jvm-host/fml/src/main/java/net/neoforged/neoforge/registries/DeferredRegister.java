package net.neoforged.neoforge.registries;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;

/**
 * Collects registrations and replays them when {@link RegisterEvent} fires.
 *
 * <p>The sink is settable so the stub can be tested without a Pumpkin host; the bootstrap
 * points it at {@code PumpkinHost::registerBlock}.
 */
public final class DeferredRegister<T> {
    /** Where a registration ends up. Returns the assigned id. */
    @FunctionalInterface
    public interface Sink {
        int registerBlock(String id, String template);
    }

    private static Sink sink = (id, template) -> {
        throw new IllegalStateException("no registration sink installed for " + id);
    };

    public static void setSink(Sink replacement) {
        sink = replacement;
    }

    private final ResourceKey<T> registry;
    private final String namespace;
    private final List<DeferredHolder<T, ? extends T>> pending = new ArrayList<>();

    private DeferredRegister(ResourceKey<T> registry, String namespace) {
        this.registry = registry;
        this.namespace = namespace;
    }

    public static <T> DeferredRegister<T> create(ResourceKey<T> registry, String namespace) {
        return new DeferredRegister<>(registry, namespace);
    }

    public <I extends T> DeferredHolder<T, I> register(String path, Supplier<? extends I> factory) {
        DeferredHolder<T, I> holder =
                new DeferredHolder<>(Identifier.fromNamespaceAndPath(namespace, path), factory::get);
        pending.add(holder);
        return holder;
    }

    public void register(IEventBus bus) {
        bus.addListener(RegisterEvent.class, event -> flush());
    }

    private void flush() {
        for (DeferredHolder<T, ? extends T> holder : pending) {
            Object object = holder.get();
            if (object instanceof Block block) {
                sink.registerBlock(holder.getId().toString(), block.pumpkinTemplate());
            } else {
                throw new IllegalStateException(
                        "registry " + registry.location() + " is not supported yet: " + holder.getId());
            }
        }
    }
}
