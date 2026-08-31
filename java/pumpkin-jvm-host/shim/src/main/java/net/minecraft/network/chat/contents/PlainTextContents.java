package net.minecraft.network.chat.contents;

import com.mojang.serialization.MapCodec;
import java.util.Optional;
import net.minecraft.network.chat.ComponentContents;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.Style;
import dev.pumpkin.shim.Unimplemented;

public interface PlainTextContents extends ComponentContents {

    static PlainTextContents create(String text) {
        throw Unimplemented.forMember("net/minecraft/network/chat/contents/PlainTextContents.create:(Ljava/lang/String;)Lnet/minecraft/network/chat/contents/PlainTextContents;");
    }

    String text();

    default MapCodec<PlainTextContents> codec() {
        throw Unimplemented.forMember("net/minecraft/network/chat/contents/PlainTextContents.codec:()Lcom/mojang/serialization/MapCodec;");
    }

    record LiteralContents(String text) implements PlainTextContents {

        public <T> Optional<T> visit(FormattedText.ContentConsumer<T> output) {
            throw Unimplemented.forMember("net/minecraft/network/chat/contents/PlainTextContents$LiteralContents.visit:(Lnet/minecraft/network/chat/FormattedText$ContentConsumer;)Ljava/util/Optional;");
        }

        public <T> Optional<T> visit(FormattedText.StyledContentConsumer<T> output, Style currentStyle) {
            throw Unimplemented.forMember("net/minecraft/network/chat/contents/PlainTextContents$LiteralContents.visit:(Lnet/minecraft/network/chat/FormattedText$StyledContentConsumer;Lnet/minecraft/network/chat/Style;)Ljava/util/Optional;");
        }

        public String toString() {
            throw Unimplemented.forMember("net/minecraft/network/chat/contents/PlainTextContents$LiteralContents.toString:()Ljava/lang/String;");
        }
    }
}
