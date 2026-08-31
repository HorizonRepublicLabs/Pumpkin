package net.minecraft.world.entity;

import net.minecraft.world.level.Level;

public interface NeutralMob {

    long getPersistentAngerEndTime();

    void setPersistentAngerEndTime(long endTime);

    EntityReference<LivingEntity> getPersistentAngerTarget();

    void setPersistentAngerTarget(final EntityReference<LivingEntity> persistentAngerTarget);

    void startPersistentAngerTimer();

    Level level();

    LivingEntity getLastHurtByMob();

    void setLastHurtByMob(final LivingEntity hurtBy);

    void setTarget(final LivingEntity target);

    boolean canAttack(final LivingEntity target);

    LivingEntity getTarget();

    LivingEntity getTargetUnchecked();
}
