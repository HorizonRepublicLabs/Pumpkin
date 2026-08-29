package net.minecraft.core.dispenser;

import dev.pumpkin.shim.Unimplemented;

public abstract class OptionalDispenseItemBehavior extends DefaultDispenseItemBehavior {

    public boolean isSuccess() {
        throw Unimplemented.forMember("net/minecraft/core/dispenser/OptionalDispenseItemBehavior.isSuccess:()Z");
    }

    public void setSuccess(boolean success) {
        throw Unimplemented.forMember("net/minecraft/core/dispenser/OptionalDispenseItemBehavior.setSuccess:(Z)V");
    }

    protected void playSound(BlockSource source) {
        throw Unimplemented.forMember("net/minecraft/core/dispenser/OptionalDispenseItemBehavior.playSound:(Lnet/minecraft/core/dispenser/BlockSource;)V");
    }

    public OptionalDispenseItemBehavior() {
    }
}
