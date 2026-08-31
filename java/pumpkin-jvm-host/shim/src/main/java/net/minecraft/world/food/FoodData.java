package net.minecraft.world.food;

import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import dev.pumpkin.shim.Unimplemented;

public class FoodData {

    private void add(int food, float saturation) {
        throw Unimplemented.forMember("net/minecraft/world/food/FoodData.add:(IF)V");
    }

    public void eat(int food, float saturationModifier) {
        throw Unimplemented.forMember("net/minecraft/world/food/FoodData.eat:(IF)V");
    }

    public void eat(FoodProperties foodProperties) {
        throw Unimplemented.forMember("net/minecraft/world/food/FoodData.eat:(Lnet/minecraft/world/food/FoodProperties;)V");
    }

    public void readAdditionalSaveData(ValueInput input) {
        throw Unimplemented.forMember("net/minecraft/world/food/FoodData.readAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueInput;)V");
    }

    public void addAdditionalSaveData(ValueOutput output) {
        throw Unimplemented.forMember("net/minecraft/world/food/FoodData.addAdditionalSaveData:(Lnet/minecraft/world/level/storage/ValueOutput;)V");
    }

    public int getFoodLevel() {
        throw Unimplemented.forMember("net/minecraft/world/food/FoodData.getFoodLevel:()I");
    }

    public void addExhaustion(float amount) {
        throw Unimplemented.forMember("net/minecraft/world/food/FoodData.addExhaustion:(F)V");
    }

    public FoodData() {
    }
}
