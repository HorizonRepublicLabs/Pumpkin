package net.minecraft.server;

import java.util.List;
import net.minecraft.commands.Commands;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.LayeredRegistryAccess;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentInitializers;
import net.minecraft.server.permissions.PermissionSet;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.crafting.RecipeManager;
import dev.pumpkin.shim.Unimplemented;

public class ReloadableServerResources {

    private final RecipeManager recipes = null;

    private ReloadableServerResources(LayeredRegistryAccess<RegistryLayer> fullLayers, HolderLookup.Provider loadingContext, FeatureFlagSet enabledFeatures, Commands.CommandSelection commandSelection, List<Registry.PendingTags<?>> postponedTags, PermissionSet functionCompilationPermissions, List<DataComponentInitializers.PendingComponents<?>> newComponents) {
        throw Unimplemented.forMember("net/minecraft/server/ReloadableServerResources.<init>:(Lnet/minecraft/core/LayeredRegistryAccess;Lnet/minecraft/core/HolderLookup$Provider;Lnet/minecraft/world/flag/FeatureFlagSet;Lnet/minecraft/commands/Commands$CommandSelection;Ljava/util/List;Lnet/minecraft/server/permissions/PermissionSet;Ljava/util/List;)V");
    }

    public void updateComponentsAndStaticRegistryTags() {
        throw Unimplemented.forMember("net/minecraft/server/ReloadableServerResources.updateComponentsAndStaticRegistryTags:()V");
    }

    public ReloadableServerResources() {
    }
}
