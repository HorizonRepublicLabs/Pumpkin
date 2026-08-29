package net.neoforged.neoforge.registries;

import java.util.function.Consumer;
import java.util.function.Supplier;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.neoforged.bus.api.Event;
import net.neoforged.fml.event.IModBusEvent;
import dev.pumpkin.shim.Unimplemented;

public class RegisterEvent extends Event implements IModBusEvent {

    RegisterEvent(ResourceKey<? extends Registry<?>> registryKey, Registry<?> registry) {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/RegisterEvent.<init>:(Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/core/Registry;)V");
    }

    public <T> void register(ResourceKey<? extends Registry<T>> registryKey, Identifier name, Supplier<T> valueSupplier) {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/RegisterEvent.register:(Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/resources/Identifier;Ljava/util/function/Supplier;)V");
    }

    public <T> void register(ResourceKey<? extends Registry<T>> registryKey, Consumer<RegisterHelper<T>> consumer) {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/RegisterEvent.register:(Lnet/minecraft/resources/ResourceKey;Ljava/util/function/Consumer;)V");
    }

    public interface RegisterHelper<T> {

        default void register(ResourceKey<T> key, T value) {
            throw Unimplemented.forMember("net/neoforged/neoforge/registries/RegisterEvent$RegisterHelper.register:(Lnet/minecraft/resources/ResourceKey;Ljava/lang/Object;)V");
        }

        void register(Identifier name, T value);
    }

    // Pumpkin divergence from the generated shim: public. In NeoForge this event is
    // constructed by the loader, once per registry, and mods only ever receive it. Pumpkin's
    // Bootstrap is the loader here and fires exactly one of these to mean "the server is
    // ready to take registrations", so it has to be able to build one. Re-apply by hand
    // after any regeneration -- grep for "Pumpkin divergence".
    public RegisterEvent() {
    }
}
