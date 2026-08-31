package net.minecraft.world.level.storage;

import com.mojang.serialization.DynamicOps;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.Tag;
import dev.pumpkin.shim.Unimplemented;

public class ValueInputContextHelper {

    public ValueInputContextHelper(HolderLookup.Provider lookup, DynamicOps<Tag> ops) {
    }

    public HolderLookup.Provider lookup() {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/ValueInputContextHelper.lookup:()Lnet/minecraft/core/HolderLookup$Provider;");
    }

    public ValueInputContextHelper() {
    }
}
