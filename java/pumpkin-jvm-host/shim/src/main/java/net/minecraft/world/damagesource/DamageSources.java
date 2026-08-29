package net.minecraft.world.damagesource;

import net.minecraft.core.RegistryAccess;
import net.minecraft.world.entity.player.Player;
import dev.pumpkin.shim.Unimplemented;

public class DamageSources {

    private final DamageSource fall = null;

    public DamageSources(RegistryAccess registries) {
        throw Unimplemented.forMember("net/minecraft/world/damagesource/DamageSources.<init>:(Lnet/minecraft/core/RegistryAccess;)V");
    }

    public DamageSource fall() {
        throw Unimplemented.forMember("net/minecraft/world/damagesource/DamageSources.fall:()Lnet/minecraft/world/damagesource/DamageSource;");
    }

    public DamageSource freeze() {
        throw Unimplemented.forMember("net/minecraft/world/damagesource/DamageSources.freeze:()Lnet/minecraft/world/damagesource/DamageSource;");
    }

    public DamageSource playerAttack(Player player) {
        throw Unimplemented.forMember("net/minecraft/world/damagesource/DamageSources.playerAttack:(Lnet/minecraft/world/entity/player/Player;)Lnet/minecraft/world/damagesource/DamageSource;");
    }

    protected DamageSources() {
    }
}
