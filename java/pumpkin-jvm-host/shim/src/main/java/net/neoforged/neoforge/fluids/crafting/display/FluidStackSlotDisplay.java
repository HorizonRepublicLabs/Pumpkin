package net.neoforged.neoforge.fluids.crafting.display;

import java.util.stream.Stream;
import net.minecraft.util.context.ContextMap;
import net.minecraft.world.item.crafting.display.DisplayContentsFactory;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import net.neoforged.neoforge.fluids.FluidStackTemplate;
import dev.pumpkin.shim.Unimplemented;

public record FluidStackSlotDisplay(FluidStackTemplate stack) implements SlotDisplay {

    public SlotDisplay.Type<FluidStackSlotDisplay> type() {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/crafting/display/FluidStackSlotDisplay.type:()Lnet/minecraft/world/item/crafting/display/SlotDisplay$Type;");
    }

    public <T> Stream<T> resolve(ContextMap context, DisplayContentsFactory<T> factory) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/crafting/display/FluidStackSlotDisplay.resolve:(Lnet/minecraft/util/context/ContextMap;Lnet/minecraft/world/item/crafting/display/DisplayContentsFactory;)Ljava/util/stream/Stream;");
    }
}
