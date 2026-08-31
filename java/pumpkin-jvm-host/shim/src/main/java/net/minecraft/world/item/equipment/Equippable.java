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

    // Pumpkin divergence: real chain -- the built component carries the slot, the one
    // fact the mod declared; presentation fields stay empty.
    public static Equippable.Builder builder(EquipmentSlot slot) {
        Builder builder = new Builder();
        builder.pumpkinSlot = slot;
        return builder;
    }

    public static class Builder {

        private Builder(EquipmentSlot slot) {
        }

        EquipmentSlot pumpkinSlot;

        public Equippable.Builder setEquipSound(Holder<SoundEvent> equipSound) {
            return this;
        }

        public Equippable.Builder setAsset(ResourceKey<EquipmentAsset> assetId) {
            return this;
        }

        public Equippable.Builder setDamageOnHurt(boolean damageOnHurt) {
            return this;
        }

        public Equippable build() {
            return new Equippable(pumpkinSlot, null, Optional.empty(), Optional.empty(),
                    Optional.empty(), false, false, false, false, false, null);
        }

        public Builder() {
        }
    }
}
