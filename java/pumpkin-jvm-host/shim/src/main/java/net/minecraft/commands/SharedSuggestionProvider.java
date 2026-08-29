package net.minecraft.commands;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.permissions.PermissionSetSupplier;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.level.Level;
import dev.pumpkin.shim.Unimplemented;

public interface SharedSuggestionProvider extends PermissionSetSupplier {

    Collection<String> getOnlinePlayerNames();

    Collection<String> getAllTeams();

    Stream<Identifier> getAvailableSounds();

    CompletableFuture<Suggestions> customSuggestion(CommandContext<?> context);

    Set<ResourceKey<Level>> levels();

    RegistryAccess registryAccess();

    FeatureFlagSet enabledFeatures();

    CompletableFuture<Suggestions> suggestRegistryElements(final ResourceKey<? extends Registry<?>> key, final SharedSuggestionProvider.ElementSuggestionType elements, final SuggestionsBuilder builder, final CommandContext<?> context);

    enum ElementSuggestionType {

        TAGS, ELEMENTS, ALL
    }

    class TextCoordinates {

        public TextCoordinates(String x, String y, String z) {
            throw Unimplemented.forMember("net/minecraft/commands/SharedSuggestionProvider$TextCoordinates.<init>:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V");
        }

        protected TextCoordinates() {
        }
    }
}
