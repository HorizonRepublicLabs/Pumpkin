package net.minecraft.world.entity.monster;

import net.minecraft.world.entity.LivingEntity;

public interface CrossbowAttackMob extends RangedAttackMob {

    void setChargingCrossbow(final boolean isCharging);

    LivingEntity getTarget();

    void onCrossbowAttackPerformed();
}
