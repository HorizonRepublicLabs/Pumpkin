package net.minecraft.world.item.component;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.TooltipFlag;
import dev.pumpkin.shim.Unimplemented;

public final class ItemContainerContents implements TooltipProvider {

    private ItemContainerContents(List<Optional<ItemStackTemplate>> items) {
    }

    public boolean equals(Object obj) {
        throw Unimplemented.forMember("net/minecraft/world/item/component/ItemContainerContents.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/minecraft/world/item/component/ItemContainerContents.hashCode:()I");
    }

    public void addToTooltip(Item.TooltipContext context, Consumer<Component> consumer, TooltipFlag flag, DataComponentGetter components) {
        throw Unimplemented.forMember("net/minecraft/world/item/component/ItemContainerContents.addToTooltip:(Lnet/minecraft/world/item/Item$TooltipContext;Ljava/util/function/Consumer;Lnet/minecraft/world/item/TooltipFlag;Lnet/minecraft/core/component/DataComponentGetter;)V");
    }

    private record Slot(int index, ItemStackTemplate item) {
    }

    public ItemContainerContents() {
    }
}
