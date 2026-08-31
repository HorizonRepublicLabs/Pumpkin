package net.minecraft.world.entity;

import net.minecraft.world.scores.PlayerTeam;
import dev.pumpkin.shim.Unimplemented;

public record ConversionParams(ConversionType type, boolean keepEquipment, boolean preserveCanPickUpLoot, PlayerTeam team) {

    public static ConversionParams single(Mob mob, boolean keepEquipment, boolean preserveCanPickUpLoot) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ConversionParams.single:(Lnet/minecraft/world/entity/Mob;ZZ)Lnet/minecraft/world/entity/ConversionParams;");
    }

    public interface AfterConversion<T extends Mob> {

        void finalizeConversion(T mob);
    }
}
