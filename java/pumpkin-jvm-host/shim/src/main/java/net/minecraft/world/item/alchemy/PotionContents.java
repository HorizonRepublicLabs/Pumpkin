package net.minecraft.world.item.alchemy;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.ConsumableListener;
import net.minecraft.world.item.component.TooltipProvider;
import net.minecraft.world.level.Level;
import dev.pumpkin.shim.Unimplemented;

public record PotionContents(Optional<Holder<Potion>> potion, Optional<Integer> customColor, List<MobEffectInstance> customEffects, Optional<String> customName) implements ConsumableListener, TooltipProvider {

    public PotionContents(Holder<Potion> potion) {
        this((Optional<Holder<Potion>>) null, (Optional<Integer>) null, (List<MobEffectInstance>) null, (Optional<String>) null);
    }

    public static ItemStack createItemStack(Item item, Holder<Potion> potion) {
        throw Unimplemented.forMember("net/minecraft/world/item/alchemy/PotionContents.createItemStack:(Lnet/minecraft/world/item/Item;Lnet/minecraft/core/Holder;)Lnet/minecraft/world/item/ItemStack;");
    }

    public boolean is(Holder<Potion> potion) {
        throw Unimplemented.forMember("net/minecraft/world/item/alchemy/PotionContents.is:(Lnet/minecraft/core/Holder;)Z");
    }

    public Iterable<MobEffectInstance> getAllEffects() {
        throw Unimplemented.forMember("net/minecraft/world/item/alchemy/PotionContents.getAllEffects:()Ljava/lang/Iterable;");
    }

    public int getColor() {
        throw Unimplemented.forMember("net/minecraft/world/item/alchemy/PotionContents.getColor:()I");
    }

    public Component getName(String prefix) {
        throw Unimplemented.forMember("net/minecraft/world/item/alchemy/PotionContents.getName:(Ljava/lang/String;)Lnet/minecraft/network/chat/Component;");
    }

    public void onConsume(Level level, LivingEntity user, ItemStack stack, Consumable consumable) {
        throw Unimplemented.forMember("net/minecraft/world/item/alchemy/PotionContents.onConsume:(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/component/Consumable;)V");
    }

    public void addToTooltip(Item.TooltipContext context, Consumer<Component> consumer, TooltipFlag flag, DataComponentGetter components) {
        throw Unimplemented.forMember("net/minecraft/world/item/alchemy/PotionContents.addToTooltip:(Lnet/minecraft/world/item/Item$TooltipContext;Ljava/util/function/Consumer;Lnet/minecraft/world/item/TooltipFlag;Lnet/minecraft/core/component/DataComponentGetter;)V");
    }
}
