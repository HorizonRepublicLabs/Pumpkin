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

    public static Equippable.Builder builder(EquipmentSlot slot) {
        throw Unimplemented.forMember("net/minecraft/world/item/equipment/Equippable.builder:(Lnet/minecraft/world/entity/EquipmentSlot;)Lnet/minecraft/world/item/equipment/Equippable$Builder;");
    }

    public static class Builder {

        private Builder(EquipmentSlot slot) {
        }

        public Equippable.Builder setEquipSound(Holder<SoundEvent> equipSound) {
            throw Unimplemented.forMember("net/minecraft/world/item/equipment/Equippable$Builder.setEquipSound:(Lnet/minecraft/core/Holder;)Lnet/minecraft/world/item/equipment/Equippable$Builder;");
        }

        public Equippable.Builder setAsset(ResourceKey<EquipmentAsset> assetId) {
            throw Unimplemented.forMember("net/minecraft/world/item/equipment/Equippable$Builder.setAsset:(Lnet/minecraft/resources/ResourceKey;)Lnet/minecraft/world/item/equipment/Equippable$Builder;");
        }

        public Equippable.Builder setDamageOnHurt(boolean damageOnHurt) {
            throw Unimplemented.forMember("net/minecraft/world/item/equipment/Equippable$Builder.setDamageOnHurt:(Z)Lnet/minecraft/world/item/equipment/Equippable$Builder;");
        }

        public Equippable build() {
            throw Unimplemented.forMember("net/minecraft/world/item/equipment/Equippable$Builder.build:()Lnet/minecraft/world/item/equipment/Equippable;");
        }

        public Builder() {
        }
    }
}
