package net.minecraft.core.registries;

import net.minecraft.core.DefaultedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.server.level.TicketType;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.material.Fluid;
import dev.pumpkin.shim.Stubs;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import dev.pumpkin.shim.Unimplemented;

public class BuiltInRegistries {

    public static final Registry<SoundEvent> SOUND_EVENT = Stubs.of(Registry.class, "net/minecraft/core/Registry", java.util.Map.of("key", ResourceKey.createRegistryKey(Identifier.fromNamespaceAndPath("minecraft", "sound_event"))));

    public static final DefaultedRegistry<Fluid> FLUID = Stubs.of(DefaultedRegistry.class, "net/minecraft/core/DefaultedRegistry", java.util.Map.of("key", ResourceKey.createRegistryKey(Identifier.fromNamespaceAndPath("minecraft", "fluid"))));

    public static final DefaultedRegistry<Block> BLOCK = Stubs.of(DefaultedRegistry.class, "net/minecraft/core/DefaultedRegistry");

    public static final DefaultedRegistry<EntityType<?>> ENTITY_TYPE = Stubs.of(DefaultedRegistry.class, "net/minecraft/core/DefaultedRegistry", java.util.Map.of("key", ResourceKey.createRegistryKey(Identifier.fromNamespaceAndPath("minecraft", "entity_type"))));

    public static final DefaultedRegistry<Item> ITEM = Stubs.of(DefaultedRegistry.class, "net/minecraft/core/DefaultedRegistry", java.util.Map.of("key", ResourceKey.createRegistryKey(Identifier.fromNamespaceAndPath("minecraft", "item"))));

    public static final Registry<BlockEntityType<?>> BLOCK_ENTITY_TYPE = Stubs.of(Registry.class,
            "net/minecraft/core/Registry", java.util.Map.of("key",
                    net.minecraft.resources.ResourceKey.createRegistryKey(
                            net.minecraft.resources.Identifier.fromNamespaceAndPath("minecraft", "block_entity_type"))));

    public static final Registry<MenuType<?>> MENU = Stubs.of(Registry.class,
            "net/minecraft/core/Registry", java.util.Map.of("key",
                    net.minecraft.resources.ResourceKey.createRegistryKey(
                            net.minecraft.resources.Identifier.fromNamespaceAndPath("minecraft", "menu"))));

    public static final Registry<RecipeType<?>> RECIPE_TYPE = Stubs.of(Registry.class, "net/minecraft/core/Registry", java.util.Map.of("key", ResourceKey.createRegistryKey(Identifier.fromNamespaceAndPath("minecraft", "recipe_type"))));

    public static final Registry<RecipeSerializer<?>> RECIPE_SERIALIZER = Stubs.of(Registry.class, "net/minecraft/core/Registry", java.util.Map.of("key", ResourceKey.createRegistryKey(Identifier.fromNamespaceAndPath("minecraft", "recipe_serializer"))));

    public static final Registry<DataComponentType<?>> DATA_COMPONENT_TYPE = Stubs.of(Registry.class,
            "net/minecraft/core/Registry", java.util.Map.of("key",
                    net.minecraft.resources.ResourceKey.createRegistryKey(
                            net.minecraft.resources.Identifier.fromNamespaceAndPath("minecraft", "data_component_type"))));

    public static final Registry<TicketType> TICKET_TYPE = Stubs.of(Registry.class,
            "net/minecraft/core/Registry", java.util.Map.of("key",
                    net.minecraft.resources.ResourceKey.createRegistryKey(
                            net.minecraft.resources.Identifier.fromNamespaceAndPath("minecraft", "ticket_type"))));

    public static final Registry<? extends Registry<?>> REGISTRY = Stubs.of(Registry.class, "net/minecraft/core/Registry");

    private static void freeze() {
        throw Unimplemented.forMember("net/minecraft/core/registries/BuiltInRegistries.freeze:()V");
    }

    private static <T extends Registry<?>> void validate(Registry<T> registry) {
        throw Unimplemented.forMember("net/minecraft/core/registries/BuiltInRegistries.validate:(Lnet/minecraft/core/Registry;)V");
    }

    private interface RegistryBootstrap<T> {

        Object run(Registry<T> registry);
    }

    public BuiltInRegistries() {
    }
}
