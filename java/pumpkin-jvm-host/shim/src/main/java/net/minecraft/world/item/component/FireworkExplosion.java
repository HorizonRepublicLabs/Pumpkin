package net.minecraft.world.item.component;

import it.unimi.dsi.fastutil.ints.IntList;
import java.util.function.Consumer;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.fml.common.asm.enumextension.IExtensibleEnum;
import dev.pumpkin.shim.Unimplemented;

public record FireworkExplosion(FireworkExplosion.Shape shape, IntList colors, IntList fadeColors, boolean hasTrail, boolean hasTwinkle) implements TooltipProvider {

    public void addToTooltip(Item.TooltipContext context, Consumer<Component> consumer, TooltipFlag flag, DataComponentGetter components) {
        throw Unimplemented.forMember("net/minecraft/world/item/component/FireworkExplosion.addToTooltip:(Lnet/minecraft/world/item/Item$TooltipContext;Ljava/util/function/Consumer;Lnet/minecraft/world/item/TooltipFlag;Lnet/minecraft/core/component/DataComponentGetter;)V");
    }

    public enum Shape implements StringRepresentable, IExtensibleEnum {

        SMALL_BALL, LARGE_BALL, STAR, CREEPER, BURST;

        public MutableComponent getName() {
            throw Unimplemented.forMember("net/minecraft/world/item/component/FireworkExplosion$Shape.getName:()Lnet/minecraft/network/chat/MutableComponent;");
        }

        public int getId() {
            throw Unimplemented.forMember("net/minecraft/world/item/component/FireworkExplosion$Shape.getId:()I");
        }

        public static FireworkExplosion.Shape byId(int id) {
            throw Unimplemented.forMember("net/minecraft/world/item/component/FireworkExplosion$Shape.byId:(I)Lnet/minecraft/world/item/component/FireworkExplosion$Shape;");
        }

        public String getSerializedName() {
            throw Unimplemented.forMember("net/minecraft/world/item/component/FireworkExplosion$Shape.getSerializedName:()Ljava/lang/String;");
        }
    }
}
