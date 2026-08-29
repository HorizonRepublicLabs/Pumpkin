package net.minecraft.world.item;

import net.minecraft.resources.Identifier;
import dev.pumpkin.shim.Unimplemented;

public class ItemCooldowns {

    public boolean isOnCooldown(ItemStack item) {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemCooldowns.isOnCooldown:(Lnet/minecraft/world/item/ItemStack;)Z");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemCooldowns.tick:()V");
    }

    public void addCooldown(ItemStack item, int time) {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemCooldowns.addCooldown:(Lnet/minecraft/world/item/ItemStack;I)V");
    }

    public void addCooldown(Identifier cooldownGroup, int time) {
        throw Unimplemented.forMember("net/minecraft/world/item/ItemCooldowns.addCooldown:(Lnet/minecraft/resources/Identifier;I)V");
    }

    private record CooldownInstance(int startTime, int endTime) {
    }

    public ItemCooldowns() {
    }
}
