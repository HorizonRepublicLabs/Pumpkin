package net.minecraft.network.chat;

import java.util.function.Predicate;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.contents.objects.ObjectInfo;
import net.minecraft.world.entity.Entity;
import dev.pumpkin.shim.Unimplemented;

public record ResolutionContext(CommandSourceStack source, Entity defaultScoreboardEntity, Predicate<ObjectInfo> objectInfoValidator, int depthLimit, ResolutionContext.LimitBehavior depthLimitBehavior) {

    public ObjectInfo validate(ObjectInfo description) {
        throw Unimplemented.forMember("net/minecraft/network/chat/ResolutionContext.validate:(Lnet/minecraft/network/chat/contents/objects/ObjectInfo;)Lnet/minecraft/network/chat/contents/objects/ObjectInfo;");
    }

    public static ResolutionContext create(CommandSourceStack source) {
        throw Unimplemented.forMember("net/minecraft/network/chat/ResolutionContext.create:(Lnet/minecraft/commands/CommandSourceStack;)Lnet/minecraft/network/chat/ResolutionContext;");
    }

    public static class Builder {

        public ResolutionContext build() {
            throw Unimplemented.forMember("net/minecraft/network/chat/ResolutionContext$Builder.build:()Lnet/minecraft/network/chat/ResolutionContext;");
        }

        protected Builder() {
        }
    }

    public enum LimitBehavior {

        DISCARD_REMAINING, STOP_PROCESSING_AND_COPY_REMAINING
    }
}
