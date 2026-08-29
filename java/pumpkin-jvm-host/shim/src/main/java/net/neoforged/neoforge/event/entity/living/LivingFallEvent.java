package net.neoforged.neoforge.event.entity.living;

import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.ICancellableEvent;
import dev.pumpkin.shim.Unimplemented;

public class LivingFallEvent extends LivingEvent implements ICancellableEvent {

    public LivingFallEvent(LivingEntity entity, double distance, float damageMultiplier) {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/living/LivingFallEvent.<init>:(Lnet/minecraft/world/entity/LivingEntity;DF)V");
    }

    public void setDamageMultiplier(float damageMultiplier) {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/living/LivingFallEvent.setDamageMultiplier:(F)V");
    }

    protected LivingFallEvent() {
    }
}
