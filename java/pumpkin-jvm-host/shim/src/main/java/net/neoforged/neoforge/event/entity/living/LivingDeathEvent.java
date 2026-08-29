package net.neoforged.neoforge.event.entity.living;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.ICancellableEvent;
import dev.pumpkin.shim.Unimplemented;

public class LivingDeathEvent extends LivingEvent implements ICancellableEvent {

    public LivingDeathEvent(LivingEntity entity, DamageSource source) {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/living/LivingDeathEvent.<init>:(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/damagesource/DamageSource;)V");
    }

    public DamageSource getSource() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/living/LivingDeathEvent.getSource:()Lnet/minecraft/world/damagesource/DamageSource;");
    }

    protected LivingDeathEvent() {
    }
}
