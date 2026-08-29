package net.minecraft.world.item.trading;

import java.util.Optional;
import net.minecraft.world.item.ItemStack;
import dev.pumpkin.shim.Unimplemented;

public class MerchantOffer {

    private MerchantOffer(ItemCost baseCostA, Optional<ItemCost> costB, ItemStack result, int uses, int maxUses, boolean rewardExp, int specialPriceDiff, int demand, float priceMultiplier, int xp) {
    }

    public MerchantOffer(ItemCost buy, ItemStack result, int maxUses, int xp, float priceMultiplier) {
    }

    public MerchantOffer(ItemCost baseCostA, Optional<ItemCost> costB, ItemStack result, int maxUses, int xp, float priceMultiplier) {
    }

    public MerchantOffer(ItemCost baseCostA, Optional<ItemCost> costB, ItemStack result, int uses, int maxUses, int xp, float priceMultiplier) {
    }

    public MerchantOffer(ItemCost baseCostA, Optional<ItemCost> costB, ItemStack result, int uses, int maxUses, int xp, float priceMultiplier, int demand) {
    }

    private MerchantOffer(MerchantOffer offer) {
    }

    public MerchantOffer copy() {
        throw Unimplemented.forMember("net/minecraft/world/item/trading/MerchantOffer.copy:()Lnet/minecraft/world/item/trading/MerchantOffer;");
    }

    public MerchantOffer() {
    }
}
