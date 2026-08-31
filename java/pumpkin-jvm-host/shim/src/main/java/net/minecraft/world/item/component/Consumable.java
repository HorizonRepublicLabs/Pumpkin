package net.minecraft.world.item.component;

import java.util.List;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.consume_effects.ConsumeEffect;
import dev.pumpkin.shim.Unimplemented;

public record Consumable(float consumeSeconds, ItemUseAnimation animation, Holder<SoundEvent> sound, boolean hasConsumeParticles, List<ConsumeEffect> onConsumeEffects) {

    public InteractionResult startConsuming(LivingEntity user, ItemStack stack, InteractionHand hand) {
        throw Unimplemented.forMember("net/minecraft/world/item/component/Consumable.startConsuming:(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/InteractionResult;");
    }

    public static Consumable.Builder builder() {
        return new Builder();
    }

    public static class Builder {

        // Pumpkin divergence: real data builder over the record's own fields. The sound
        // holder stays null until a mod sets one; the record carries what was declared.
        float pumpkinConsumeSeconds = 1.6F;
        ItemUseAnimation pumpkinAnimation = ItemUseAnimation.EAT;
        Holder<SoundEvent> pumpkinSound;
        boolean pumpkinHasConsumeParticles = true;
        final java.util.ArrayList<ConsumeEffect> pumpkinEffects = new java.util.ArrayList<>();

        protected Builder() {
        }

        public Builder consumeSeconds(float seconds) {
            pumpkinConsumeSeconds = seconds;
            return this;
        }

        public Builder animation(ItemUseAnimation animation) {
            pumpkinAnimation = animation;
            return this;
        }

        public Builder sound(Holder<SoundEvent> sound) {
            pumpkinSound = sound;
            return this;
        }

        public Builder soundAfterConsume(Holder<SoundEvent> sound) {
            return this;
        }

        public Builder hasConsumeParticles(boolean hasConsumeParticles) {
            pumpkinHasConsumeParticles = hasConsumeParticles;
            return this;
        }

        public Builder onConsume(ConsumeEffect effect) {
            pumpkinEffects.add(effect);
            return this;
        }

        public Consumable build() {
            return new Consumable(pumpkinConsumeSeconds, pumpkinAnimation, pumpkinSound, pumpkinHasConsumeParticles, List.copyOf(pumpkinEffects));
        }
    }

    public interface OverrideConsumeSound {

        SoundEvent getConsumeSound(final ItemStack itemStack);
    }
}
