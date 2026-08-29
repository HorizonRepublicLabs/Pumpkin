package net.minecraft.commands.arguments;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public class EntityAnchorArgument implements ArgumentType<EntityAnchorArgument.Anchor> {

    public EntityAnchorArgument.Anchor parse(StringReader reader) throws CommandSyntaxException {
        throw Unimplemented.forMember("net/minecraft/commands/arguments/EntityAnchorArgument.parse:(Lcom/mojang/brigadier/StringReader;)Lnet/minecraft/commands/arguments/EntityAnchorArgument$Anchor;");
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        throw Unimplemented.forMember("net/minecraft/commands/arguments/EntityAnchorArgument.listSuggestions:(Lcom/mojang/brigadier/context/CommandContext;Lcom/mojang/brigadier/suggestion/SuggestionsBuilder;)Ljava/util/concurrent/CompletableFuture;");
    }

    public Collection<String> getExamples() {
        throw Unimplemented.forMember("net/minecraft/commands/arguments/EntityAnchorArgument.getExamples:()Ljava/util/Collection;");
    }

    public enum Anchor {

        FEET, EYES;

        public Vec3 apply(Entity entity) {
            throw Unimplemented.forMember("net/minecraft/commands/arguments/EntityAnchorArgument$Anchor.apply:(Lnet/minecraft/world/entity/Entity;)Lnet/minecraft/world/phys/Vec3;");
        }

        public Vec3 apply(CommandSourceStack source) {
            throw Unimplemented.forMember("net/minecraft/commands/arguments/EntityAnchorArgument$Anchor.apply:(Lnet/minecraft/commands/CommandSourceStack;)Lnet/minecraft/world/phys/Vec3;");
        }
    }

    public EntityAnchorArgument() {
    }
}
