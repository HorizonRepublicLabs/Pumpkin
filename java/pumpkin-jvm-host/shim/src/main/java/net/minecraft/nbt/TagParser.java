package net.minecraft.nbt;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.DynamicOps;
import net.minecraft.util.parsing.packrat.commands.Grammar;
import dev.pumpkin.shim.Unimplemented;

public class TagParser<T> {

    private TagParser(DynamicOps<T> ops, Grammar<T> grammar) {
        throw Unimplemented.forMember("net/minecraft/nbt/TagParser.<init>:(Lcom/mojang/serialization/DynamicOps;Lnet/minecraft/util/parsing/packrat/commands/Grammar;)V");
    }

    public static <T> TagParser<T> create(DynamicOps<T> ops) {
        throw Unimplemented.forMember("net/minecraft/nbt/TagParser.create:(Lcom/mojang/serialization/DynamicOps;)Lnet/minecraft/nbt/TagParser;");
    }

    public static CompoundTag parseCompoundFully(String input) throws CommandSyntaxException {
        throw Unimplemented.forMember("net/minecraft/nbt/TagParser.parseCompoundFully:(Ljava/lang/String;)Lnet/minecraft/nbt/CompoundTag;");
    }

    public TagParser() {
    }
}
