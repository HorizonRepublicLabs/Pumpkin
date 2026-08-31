package net.minecraft.world.level.storage.loot;

import java.util.List;
import dev.pumpkin.shim.Unimplemented;

public interface Validatable {

    void validate(ValidationContext context);

    static void validate(ValidationContext context, List<? extends Validatable> list) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/loot/Validatable.validate:(Lnet/minecraft/world/level/storage/loot/ValidationContext;Ljava/util/List;)V");
    }
}
