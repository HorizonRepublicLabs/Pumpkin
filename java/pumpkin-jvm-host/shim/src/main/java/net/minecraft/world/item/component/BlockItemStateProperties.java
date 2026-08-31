package net.minecraft.world.item.component;

import java.util.Map;
import java.util.function.Consumer;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import dev.pumpkin.shim.Unimplemented;

public record BlockItemStateProperties(Map<String, String> properties) implements TooltipProvider {

    public <T extends Comparable<T>> BlockItemStateProperties with(Property<T> property, T value) {
        throw Unimplemented.forMember("net/minecraft/world/item/component/BlockItemStateProperties.with:(Lnet/minecraft/world/level/block/state/properties/Property;Ljava/lang/Comparable;)Lnet/minecraft/world/item/component/BlockItemStateProperties;");
    }

    public <T extends Comparable<T>> BlockItemStateProperties with(Property<T> property, BlockState state) {
        throw Unimplemented.forMember("net/minecraft/world/item/component/BlockItemStateProperties.with:(Lnet/minecraft/world/level/block/state/properties/Property;Lnet/minecraft/world/level/block/state/BlockState;)Lnet/minecraft/world/item/component/BlockItemStateProperties;");
    }

    public <T extends Comparable<T>> T get(Property<T> property) {
        throw Unimplemented.forMember("net/minecraft/world/item/component/BlockItemStateProperties.get:(Lnet/minecraft/world/level/block/state/properties/Property;)Ljava/lang/Comparable;");
    }

    public BlockState apply(BlockState state) {
        throw Unimplemented.forMember("net/minecraft/world/item/component/BlockItemStateProperties.apply:(Lnet/minecraft/world/level/block/state/BlockState;)Lnet/minecraft/world/level/block/state/BlockState;");
    }

    public boolean isEmpty() {
        throw Unimplemented.forMember("net/minecraft/world/item/component/BlockItemStateProperties.isEmpty:()Z");
    }

    public void addToTooltip(Item.TooltipContext context, Consumer<Component> consumer, TooltipFlag flag, DataComponentGetter components) {
        throw Unimplemented.forMember("net/minecraft/world/item/component/BlockItemStateProperties.addToTooltip:(Lnet/minecraft/world/item/Item$TooltipContext;Ljava/util/function/Consumer;Lnet/minecraft/world/item/TooltipFlag;Lnet/minecraft/core/component/DataComponentGetter;)V");
    }
}
