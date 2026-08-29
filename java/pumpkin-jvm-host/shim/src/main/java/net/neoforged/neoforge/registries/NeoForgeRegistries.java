package net.neoforged.neoforge.registries;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.common.crafting.IngredientType;
import net.neoforged.neoforge.common.world.BiomeModifier;
import dev.pumpkin.shim.Unimplemented;

public class NeoForgeRegistries {

    public static final Registry<MapCodec<? extends BiomeModifier>> BIOME_MODIFIER_SERIALIZERS = null;

    public static final Registry<IngredientType<?>> INGREDIENT_TYPES = null;

    public static final Registry<MapCodec<? extends ICondition>> CONDITION_SERIALIZERS = null;

    public static final class Keys {

        protected Keys() {
        }

        static {
            if (true) {
                throw Unimplemented.forMember("net/neoforged/neoforge/registries/NeoForgeRegistries$Keys");
            }
        }
    }

    protected NeoForgeRegistries() {
    }

    static {
        if (true) {
            throw Unimplemented.forMember("net/neoforged/neoforge/registries/NeoForgeRegistries");
        }
    }
}
