package net.minecraft.world.item.equipment;

import java.util.Optional;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import dev.pumpkin.shim.Unimplemented;

public record Equippable(EquipmentSlot slot, Holder<SoundEvent> equipSound, Optional<ResourceKey<EquipmentAsset>> assetId, Optional<Identifier> cameraOverlay, Optional<HolderSet<EntityType<?>>> allowedEntities, boolean dispensable, boolean swappable, boolean damageOnHurt, boolean equipOnInteract, boolean canBeSheared, Holder<SoundEvent> shearingSound) {

    public static class Builder {

        private Builder(EquipmentSlot slot) {
            throw Unimplemented.forMember("net/minecraft/world/item/equipment/Equippable$Builder.<init>:(Lnet/minecraft/world/entity/EquipmentSlot;)V");
        }

        public Equippable build() {
            throw Unimplemented.forMember("net/minecraft/world/item/equipment/Equippable$Builder.build:()Lnet/minecraft/world/item/equipment/Equippable;");
        }

        protected Builder() {
        }
    }
}
