package net.neoforged.neoforge.event.entity.living;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.ICancellableEvent;
import net.neoforged.neoforge.common.damagesource.DamageContainer;
import net.neoforged.neoforge.common.damagesource.IReductionFunction;
import dev.pumpkin.shim.Unimplemented;

public class LivingIncomingDamageEvent extends LivingEvent implements ICancellableEvent {

    public LivingIncomingDamageEvent(LivingEntity entity, DamageContainer container) {
    }

    public DamageContainer getContainer() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/living/LivingIncomingDamageEvent.getContainer:()Lnet/neoforged/neoforge/common/damagesource/DamageContainer;");
    }

    public DamageSource getSource() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/living/LivingIncomingDamageEvent.getSource:()Lnet/minecraft/world/damagesource/DamageSource;");
    }

    public float getAmount() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/living/LivingIncomingDamageEvent.getAmount:()F");
    }

    public void addReductionModifier(DamageContainer.Reduction type, IReductionFunction reductionFunc) {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/living/LivingIncomingDamageEvent.addReductionModifier:(Lnet/neoforged/neoforge/common/damagesource/DamageContainer$Reduction;Lnet/neoforged/neoforge/common/damagesource/IReductionFunction;)V");
    }

    public LivingIncomingDamageEvent() {
    }
}
