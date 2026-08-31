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

    public static class Builder {

        protected Builder() {
        }

        public Consumable build() {
            throw Unimplemented.forMember("net/minecraft/world/item/component/Consumable$Builder.build:()Lnet/minecraft/world/item/component/Consumable;");
        }
    }

    public interface OverrideConsumeSound {

        SoundEvent getConsumeSound(final ItemStack itemStack);
    }
}
