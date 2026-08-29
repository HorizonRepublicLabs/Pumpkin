package net.minecraft.network.chat;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.MapCodec;
import java.util.Optional;
import dev.pumpkin.shim.Unimplemented;

public interface ComponentContents {

    default <T> Optional<T> visit(FormattedText.StyledContentConsumer<T> output, Style currentStyle) {
        throw Unimplemented.forMember("net/minecraft/network/chat/ComponentContents.visit:(Lnet/minecraft/network/chat/FormattedText$StyledContentConsumer;Lnet/minecraft/network/chat/Style;)Ljava/util/Optional;");
    }

    default <T> Optional<T> visit(FormattedText.ContentConsumer<T> output) {
        throw Unimplemented.forMember("net/minecraft/network/chat/ComponentContents.visit:(Lnet/minecraft/network/chat/FormattedText$ContentConsumer;)Ljava/util/Optional;");
    }

    default MutableComponent resolve(ResolutionContext context, int recursionDepth) throws CommandSyntaxException {
        throw Unimplemented.forMember("net/minecraft/network/chat/ComponentContents.resolve:(Lnet/minecraft/network/chat/ResolutionContext;I)Lnet/minecraft/network/chat/MutableComponent;");
    }

    MapCodec<? extends ComponentContents> codec();
}
