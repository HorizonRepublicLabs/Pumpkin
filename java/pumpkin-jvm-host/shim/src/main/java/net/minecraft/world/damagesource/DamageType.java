package net.minecraft.world.damagesource;

public record DamageType(String msgId, DamageScaling scaling, float exhaustion, DamageEffects effects, DeathMessageType deathMessageType) {

    public DamageType(String msgdId, DamageScaling scaling, float exhaustion) {
        this((String) null, (DamageScaling) null, (float) 0.0F, (DamageEffects) null, (DeathMessageType) null);
    }

    public DamageType(String msgdId, DamageScaling scaling, float exhaustion, DamageEffects effects) {
        this((String) null, (DamageScaling) null, (float) 0.0F, (DamageEffects) null, (DeathMessageType) null);
    }

    public DamageType(String msgdId, float exhaustion, DamageEffects effects) {
        this((String) null, (DamageScaling) null, (float) 0.0F, (DamageEffects) null, (DeathMessageType) null);
    }

    public DamageType(String msgdId, float exhaustion) {
        this((String) null, (DamageScaling) null, (float) 0.0F, (DamageEffects) null, (DeathMessageType) null);
    }
}
