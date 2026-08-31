package net.minecraft.world.food;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.ConsumableListener;
import net.minecraft.world.level.Level;
import dev.pumpkin.shim.Unimplemented;

public record FoodProperties(int nutrition, float saturation, boolean canAlwaysEat) implements ConsumableListener {

    public void onConsume(Level level, LivingEntity user, ItemStack stack, Consumable consumable) {
        throw Unimplemented.forMember("net/minecraft/world/food/FoodProperties.onConsume:(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/component/Consumable;)V");
    }

    public static class Builder {

        public FoodProperties build() {
            throw Unimplemented.forMember("net/minecraft/world/food/FoodProperties$Builder.build:()Lnet/minecraft/world/food/FoodProperties;");
        }

        public Builder() {
        }
    }
}
