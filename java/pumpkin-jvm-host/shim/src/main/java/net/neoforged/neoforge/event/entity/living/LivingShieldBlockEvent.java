package net.neoforged.neoforge.event.entity.living;

import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.common.damagesource.DamageContainer;
import dev.pumpkin.shim.Unimplemented;

public class LivingShieldBlockEvent extends LivingEvent {

    private int shieldDamage;

    public LivingShieldBlockEvent(LivingEntity blocker, DamageContainer container, float blockedDamage, boolean originalBlockedState) {
    }

    public float getBlockedDamage() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/living/LivingShieldBlockEvent.getBlockedDamage:()F");
    }

    public int shieldDamage() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/living/LivingShieldBlockEvent.shieldDamage:()I");
    }

    public boolean getBlocked() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/living/LivingShieldBlockEvent.getBlocked:()Z");
    }

    public LivingShieldBlockEvent() {
    }
}
