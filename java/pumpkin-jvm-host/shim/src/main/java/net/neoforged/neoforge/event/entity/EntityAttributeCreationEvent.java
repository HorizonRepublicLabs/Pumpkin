package net.neoforged.neoforge.event.entity;

import java.util.Map;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.neoforged.bus.api.Event;
import net.neoforged.fml.event.IModBusEvent;
import dev.pumpkin.shim.Unimplemented;

public class EntityAttributeCreationEvent extends Event implements IModBusEvent {

    public EntityAttributeCreationEvent(Map<EntityType<? extends LivingEntity>, AttributeSupplier> map) {
    }

    public void put(EntityType<? extends LivingEntity> entity, AttributeSupplier map) {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/EntityAttributeCreationEvent.put:(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/entity/ai/attributes/AttributeSupplier;)V");
    }

    public EntityAttributeCreationEvent() {
    }
}
