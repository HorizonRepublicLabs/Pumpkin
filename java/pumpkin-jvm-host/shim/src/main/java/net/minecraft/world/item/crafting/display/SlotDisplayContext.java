package net.minecraft.world.item.crafting.display;

import net.minecraft.core.HolderLookup;
import net.minecraft.util.context.ContextKey;
import net.minecraft.util.context.ContextMap;
import net.minecraft.world.level.Level;
import dev.pumpkin.shim.Unimplemented;

public class SlotDisplayContext {

    public static final ContextKey<HolderLookup.Provider> REGISTRIES = null;

    public static ContextMap fromLevel(Level level) {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/display/SlotDisplayContext.fromLevel:(Lnet/minecraft/world/level/Level;)Lnet/minecraft/util/context/ContextMap;");
    }

    public SlotDisplayContext() {
    }

    static {
        if (true) {
            throw Unimplemented.forMember("net/minecraft/world/item/crafting/display/SlotDisplayContext");
        }
    }
}
