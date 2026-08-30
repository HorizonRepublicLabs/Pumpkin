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
    }

    public <T> void register(ResourceKey<? extends Registry<T>> registryKey, Identifier name, Supplier<T> valueSupplier) {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/RegisterEvent.register:(Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/resources/Identifier;Ljava/util/function/Supplier;)V");
    }

    // Pumpkin divergence: real body. The other way a mod registers content -- straight into
    // the game during the event, rather than declaring it up front through a
    // DeferredRegister. MysticalAgriculture uses both.
    //
    // The helper routes to the same sink DeferredRegister's flush does, so the two paths
    // cannot drift into registering differently. Only blocks are carried so far, and anything
    // else stops loudly rather than being dropped -- a silently ignored registration is a mod
    // whose content simply is not there, with nothing to say why.
    public <T> void register(ResourceKey<? extends Registry<T>> registryKey, Consumer<RegisterHelper<T>> consumer) {
        consumer.accept((name, value) -> {
            DeferredHolder.pumpkinRecordValue(registryKey.identifier().toString(), name, value);
            if (value instanceof net.minecraft.world.level.block.Block block) {
                net.minecraft.world.level.block.state.BlockBehaviour.Properties props = block.pumpkinProperties();
                // Recorded on the block so its BlockItem, registering later, can name it.
                block.pumpkinSetRegisteredId(name.toString());
                DeferredRegister.pumpkinSink().registerBlock(name.toString(), block.pumpkinTemplate(),
                        props.pumpkinDestroyTime(), props.pumpkinExplosionResistance(),
                        props.pumpkinRequiresTool(),
                        DeferredRegister.pumpkinStateProperties(block));
            } else if (value instanceof net.minecraft.world.item.Item item) {
                item.pumpkinSetRegisteredId(name.toString());
                DeferredRegister.pumpkinSink().registerItem(name.toString(), item.pumpkinTemplate(),
                        item.pumpkinMaxStackSize(), item.pumpkinMaxDamage(),
                        item.pumpkinPlacedBlockId());
            } else if (value instanceof net.minecraft.world.level.block.entity.BlockEntityType<?> type) {
                DeferredRegister.pumpkinSink().registerBlockEntityType(name.toString(),
                        DeferredRegister.pumpkinJoinRegisteredBlockIds(type));
            } else if (value instanceof net.minecraft.world.item.CreativeModeTab tab) {
                DeferredRegister.pumpkinReportCreativeTab(name.toString(), tab);
            } else if (value instanceof net.minecraft.world.inventory.MenuType) {
                DeferredRegister.pumpkinSink().registerMenuType(name.toString());
            } else if (value instanceof net.minecraft.sounds.SoundEvent) {
                DeferredRegister.pumpkinSink().registerSoundEvent(name.toString());
            } else if (value instanceof net.minecraft.core.component.DataComponentType) {
                DeferredRegister.pumpkinSink().registerDataComponentType(name.toString());
            } else {
                DeferredRegister.pumpkinWarnUnsupported(registryKey.identifier().toString(), name.toString());
            }
        });
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
