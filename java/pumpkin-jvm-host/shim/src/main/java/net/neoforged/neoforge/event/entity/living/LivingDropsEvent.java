package net.neoforged.neoforge.event.entity.living;

import java.util.Collection;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.neoforged.bus.api.ICancellableEvent;
import dev.pumpkin.shim.Unimplemented;

public class LivingDropsEvent extends LivingEvent implements ICancellableEvent {

    public LivingDropsEvent(LivingEntity entity, DamageSource source, Collection<ItemEntity> drops, boolean recentlyHit) {
    }

    public DamageSource getSource() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/living/LivingDropsEvent.getSource:()Lnet/minecraft/world/damagesource/DamageSource;");
    }

    public Collection<ItemEntity> getDrops() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/living/LivingDropsEvent.getDrops:()Ljava/util/Collection;");
    }

    public LivingDropsEvent() {
    }
}
