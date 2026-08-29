package net.minecraft.stats;

import net.minecraft.world.inventory.RecipeBookType;
import dev.pumpkin.shim.Unimplemented;

public final class RecipeBookSettings {

    public RecipeBookSettings() {
    }

    private RecipeBookSettings(RecipeBookSettings.TypeSettings crafting, RecipeBookSettings.TypeSettings furnace, RecipeBookSettings.TypeSettings blastFurnace, RecipeBookSettings.TypeSettings smoker) {
    }

    private RecipeBookSettings(RecipeBookSettings.TypeSettings crafting, RecipeBookSettings.TypeSettings furnace, RecipeBookSettings.TypeSettings blastFurnace, RecipeBookSettings.TypeSettings smoker, java.util.Map<RecipeBookType, RecipeBookSettings.TypeSettings> moddedSettings) {
    }

    public RecipeBookSettings copy() {
        throw Unimplemented.forMember("net/minecraft/stats/RecipeBookSettings.copy:()Lnet/minecraft/stats/RecipeBookSettings;");
    }

    public record TypeSettings(boolean open, boolean filtering) {

        public String toString() {
            throw Unimplemented.forMember("net/minecraft/stats/RecipeBookSettings$TypeSettings.toString:()Ljava/lang/String;");
        }
    }
}
