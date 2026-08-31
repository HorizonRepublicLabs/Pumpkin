package net.minecraft.nbt;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.pumpkin.shim.Unimplemented;

public final class NbtUtils {

    protected NbtUtils() {
    }

    public static String structureToSnbt(CompoundTag structure) {
        throw Unimplemented.forMember("net/minecraft/nbt/NbtUtils.structureToSnbt:(Lnet/minecraft/nbt/CompoundTag;)Ljava/lang/String;");
    }

    public static CompoundTag snbtToStructure(String snbt) throws CommandSyntaxException {
        throw Unimplemented.forMember("net/minecraft/nbt/NbtUtils.snbtToStructure:(Ljava/lang/String;)Lnet/minecraft/nbt/CompoundTag;");
    }
}
