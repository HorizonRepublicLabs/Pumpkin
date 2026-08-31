package net.minecraft.world.level.block.entity;

import com.mojang.serialization.Codec;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;
import dev.pumpkin.shim.Unimplemented;

public record PotDecorations(Optional<Item> back, Optional<Item> left, Optional<Item> right, Optional<Item> front) implements TooltipProvider {

    public static final PotDecorations EMPTY = null;

    public static final Codec<PotDecorations> CODEC = null;

    private PotDecorations(List<Item> items) {
        this((Optional<Item>) null, (Optional<Item>) null, (Optional<Item>) null, (Optional<Item>) null);
    }

    public PotDecorations(Item back, Item left, Item right, Item front) {
        this((Optional<Item>) null, (Optional<Item>) null, (Optional<Item>) null, (Optional<Item>) null);
    }

    public void addToTooltip(Item.TooltipContext context, Consumer<Component> consumer, TooltipFlag flag, DataComponentGetter components) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/PotDecorations.addToTooltip:(Lnet/minecraft/world/item/Item$TooltipContext;Ljava/util/function/Consumer;Lnet/minecraft/world/item/TooltipFlag;Lnet/minecraft/core/component/DataComponentGetter;)V");
    }
}
