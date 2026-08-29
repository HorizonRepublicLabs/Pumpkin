package net.minecraft.stats;

import net.minecraft.world.inventory.RecipeBookType;
import dev.pumpkin.shim.Unimplemented;

public final class RecipeBookSettings {

    public RecipeBookSettings() {
        throw Unimplemented.forMember("net/minecraft/stats/RecipeBookSettings.<init>:()V");
    }

    private RecipeBookSettings(RecipeBookSettings.TypeSettings crafting, RecipeBookSettings.TypeSettings furnace, RecipeBookSettings.TypeSettings blastFurnace, RecipeBookSettings.TypeSettings smoker) {
        throw Unimplemented.forMember("net/minecraft/stats/RecipeBookSettings.<init>:(Lnet/minecraft/stats/RecipeBookSettings$TypeSettings;Lnet/minecraft/stats/RecipeBookSettings$TypeSettings;Lnet/minecraft/stats/RecipeBookSettings$TypeSettings;Lnet/minecraft/stats/RecipeBookSettings$TypeSettings;)V");
    }

    private RecipeBookSettings(RecipeBookSettings.TypeSettings crafting, RecipeBookSettings.TypeSettings furnace, RecipeBookSettings.TypeSettings blastFurnace, RecipeBookSettings.TypeSettings smoker, java.util.Map<RecipeBookType, RecipeBookSettings.TypeSettings> moddedSettings) {
        throw Unimplemented.forMember("net/minecraft/stats/RecipeBookSettings.<init>:(Lnet/minecraft/stats/RecipeBookSettings$TypeSettings;Lnet/minecraft/stats/RecipeBookSettings$TypeSettings;Lnet/minecraft/stats/RecipeBookSettings$TypeSettings;Lnet/minecraft/stats/RecipeBookSettings$TypeSettings;Ljava/util/Map;)V");
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
