package net.minecraft.world.damagesource;

import dev.pumpkin.shim.Unimplemented;

public record DamageType(String msgId, DamageScaling scaling, float exhaustion, DamageEffects effects, DeathMessageType deathMessageType) {

    public DamageType(String msgdId, DamageScaling scaling, float exhaustion) {
        this((String) null, (DamageScaling) null, (float) 0.0F, (DamageEffects) null, (DeathMessageType) null);
        throw Unimplemented.forMember("net/minecraft/world/damagesource/DamageType.<init>:(Ljava/lang/String;Lnet/minecraft/world/damagesource/DamageScaling;F)V");
    }

    public DamageType(String msgdId, DamageScaling scaling, float exhaustion, DamageEffects effects) {
        this((String) null, (DamageScaling) null, (float) 0.0F, (DamageEffects) null, (DeathMessageType) null);
        throw Unimplemented.forMember("net/minecraft/world/damagesource/DamageType.<init>:(Ljava/lang/String;Lnet/minecraft/world/damagesource/DamageScaling;FLnet/minecraft/world/damagesource/DamageEffects;)V");
    }

    public DamageType(String msgdId, float exhaustion, DamageEffects effects) {
        this((String) null, (DamageScaling) null, (float) 0.0F, (DamageEffects) null, (DeathMessageType) null);
        throw Unimplemented.forMember("net/minecraft/world/damagesource/DamageType.<init>:(Ljava/lang/String;FLnet/minecraft/world/damagesource/DamageEffects;)V");
    }

    public DamageType(String msgdId, float exhaustion) {
        this((String) null, (DamageScaling) null, (float) 0.0F, (DamageEffects) null, (DeathMessageType) null);
        throw Unimplemented.forMember("net/minecraft/world/damagesource/DamageType.<init>:(Ljava/lang/String;F)V");
    }
}
