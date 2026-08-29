package net.minecraft.core.registries;

import net.minecraft.core.DefaultedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.material.Fluid;
import dev.pumpkin.shim.Unimplemented;

public class BuiltInRegistries {

    public static final Registry<SoundEvent> SOUND_EVENT = null;

    public static final DefaultedRegistry<Fluid> FLUID = null;

    public static final DefaultedRegistry<EntityType<?>> ENTITY_TYPE = null;

    public static final DefaultedRegistry<Item> ITEM = null;

    public static final Registry<RecipeType<?>> RECIPE_TYPE = null;

    public static final Registry<RecipeSerializer<?>> RECIPE_SERIALIZER = null;

    private static void freeze() {
        throw Unimplemented.forMember("net/minecraft/core/registries/BuiltInRegistries.freeze:()V");
    }

    private static <T extends Registry<?>> void validate(Registry<T> registry) {
        throw Unimplemented.forMember("net/minecraft/core/registries/BuiltInRegistries.validate:(Lnet/minecraft/core/Registry;)V");
    }

    private interface RegistryBootstrap<T> {

        Object run(Registry<T> registry);
    }

    protected BuiltInRegistries() {
    }

    static {
        if (true) {
            throw Unimplemented.forMember("net/minecraft/core/registries/BuiltInRegistries");
        }
    }
}
