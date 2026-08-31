package net.minecraft.network.chat.contents;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.MapCodec;
import java.util.List;
import java.util.Optional;
import net.minecraft.network.chat.ComponentContents;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.ResolutionContext;
import net.minecraft.network.chat.Style;
import dev.pumpkin.shim.Unimplemented;

public class TranslatableContents implements ComponentContents {

    // Pumpkin divergence: vanilla body.
    public static boolean isAllowedPrimitiveArgument(Object object) {
        return object instanceof Number || object instanceof Boolean || object instanceof String;
    }

    private static TranslatableContents create(String key, Optional<String> fallback, Optional<List<Object>> args) {
        throw Unimplemented.forMember("net/minecraft/network/chat/contents/TranslatableContents.create:(Ljava/lang/String;Ljava/util/Optional;Ljava/util/Optional;)Lnet/minecraft/network/chat/contents/TranslatableContents;");
    }

    public TranslatableContents(String key, String fallback, Object[] args) {
    }

    public MapCodec<TranslatableContents> codec() {
        throw Unimplemented.forMember("net/minecraft/network/chat/contents/TranslatableContents.codec:()Lcom/mojang/serialization/MapCodec;");
    }

    private FormattedText getArgument(int index) {
        throw Unimplemented.forMember("net/minecraft/network/chat/contents/TranslatableContents.getArgument:(I)Lnet/minecraft/network/chat/FormattedText;");
    }

    public <T> Optional<T> visit(FormattedText.StyledContentConsumer<T> output, Style currentStyle) {
        throw Unimplemented.forMember("net/minecraft/network/chat/contents/TranslatableContents.visit:(Lnet/minecraft/network/chat/FormattedText$StyledContentConsumer;Lnet/minecraft/network/chat/Style;)Ljava/util/Optional;");
    }

    public <T> Optional<T> visit(FormattedText.ContentConsumer<T> output) {
        throw Unimplemented.forMember("net/minecraft/network/chat/contents/TranslatableContents.visit:(Lnet/minecraft/network/chat/FormattedText$ContentConsumer;)Ljava/util/Optional;");
    }

    public MutableComponent resolve(ResolutionContext context, int recursionDepth) throws CommandSyntaxException {
        throw Unimplemented.forMember("net/minecraft/network/chat/contents/TranslatableContents.resolve:(Lnet/minecraft/network/chat/ResolutionContext;I)Lnet/minecraft/network/chat/MutableComponent;");
    }

    public boolean equals(Object o) {
        throw Unimplemented.forMember("net/minecraft/network/chat/contents/TranslatableContents.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/minecraft/network/chat/contents/TranslatableContents.hashCode:()I");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/network/chat/contents/TranslatableContents.toString:()Ljava/lang/String;");
    }

    public String getKey() {
        throw Unimplemented.forMember("net/minecraft/network/chat/contents/TranslatableContents.getKey:()Ljava/lang/String;");
    }

    public TranslatableContents() {
    }
}
