package net.minecraft.world.level.storage.loot;

import dev.pumpkin.shim.Unimplemented;

public interface LootContextUser extends Validatable {

    default void validate(ValidationContext context) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/loot/LootContextUser.validate:(Lnet/minecraft/world/level/storage/loot/ValidationContext;)V");
    }
}
