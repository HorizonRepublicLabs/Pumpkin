package net.minecraft.world.level.block.entity;

import java.util.List;
import java.util.function.Consumer;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;
import dev.pumpkin.shim.Unimplemented;

public record BannerPatternLayers(List<BannerPatternLayers.Layer> layers) implements TooltipProvider {

    public static final BannerPatternLayers EMPTY = null;

    public void addToTooltip(Item.TooltipContext context, Consumer<Component> consumer, TooltipFlag flag, DataComponentGetter components) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BannerPatternLayers.addToTooltip:(Lnet/minecraft/world/item/Item$TooltipContext;Ljava/util/function/Consumer;Lnet/minecraft/world/item/TooltipFlag;Lnet/minecraft/core/component/DataComponentGetter;)V");
    }

    public static class Builder {

        public BannerPatternLayers.Builder add(Holder<BannerPattern> pattern, DyeColor color) {
            throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BannerPatternLayers$Builder.add:(Lnet/minecraft/core/Holder;Lnet/minecraft/world/item/DyeColor;)Lnet/minecraft/world/level/block/entity/BannerPatternLayers$Builder;");
        }

        public BannerPatternLayers.Builder add(BannerPatternLayers.Layer layer) {
            throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BannerPatternLayers$Builder.add:(Lnet/minecraft/world/level/block/entity/BannerPatternLayers$Layer;)Lnet/minecraft/world/level/block/entity/BannerPatternLayers$Builder;");
        }

        public BannerPatternLayers build() {
            throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BannerPatternLayers$Builder.build:()Lnet/minecraft/world/level/block/entity/BannerPatternLayers;");
        }

        public Builder() {
        }
    }

    public record Layer(Holder<BannerPattern> pattern, DyeColor color) {

        public MutableComponent description() {
            throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BannerPatternLayers$Layer.description:()Lnet/minecraft/network/chat/MutableComponent;");
        }
    }
}
