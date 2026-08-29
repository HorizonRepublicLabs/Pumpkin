package net.minecraft.world.item.trading;

import java.util.Optional;
import net.minecraft.world.item.ItemStack;
import dev.pumpkin.shim.Unimplemented;

public class MerchantOffer {

    private MerchantOffer(ItemCost baseCostA, Optional<ItemCost> costB, ItemStack result, int uses, int maxUses, boolean rewardExp, int specialPriceDiff, int demand, float priceMultiplier, int xp) {
        throw Unimplemented.forMember("net/minecraft/world/item/trading/MerchantOffer.<init>:(Lnet/minecraft/world/item/trading/ItemCost;Ljava/util/Optional;Lnet/minecraft/world/item/ItemStack;IIZIIFI)V");
    }

    public MerchantOffer(ItemCost buy, ItemStack result, int maxUses, int xp, float priceMultiplier) {
        throw Unimplemented.forMember("net/minecraft/world/item/trading/MerchantOffer.<init>:(Lnet/minecraft/world/item/trading/ItemCost;Lnet/minecraft/world/item/ItemStack;IIF)V");
    }

    public MerchantOffer(ItemCost baseCostA, Optional<ItemCost> costB, ItemStack result, int maxUses, int xp, float priceMultiplier) {
        throw Unimplemented.forMember("net/minecraft/world/item/trading/MerchantOffer.<init>:(Lnet/minecraft/world/item/trading/ItemCost;Ljava/util/Optional;Lnet/minecraft/world/item/ItemStack;IIF)V");
    }

    public MerchantOffer(ItemCost baseCostA, Optional<ItemCost> costB, ItemStack result, int uses, int maxUses, int xp, float priceMultiplier) {
        throw Unimplemented.forMember("net/minecraft/world/item/trading/MerchantOffer.<init>:(Lnet/minecraft/world/item/trading/ItemCost;Ljava/util/Optional;Lnet/minecraft/world/item/ItemStack;IIIF)V");
    }

    public MerchantOffer(ItemCost baseCostA, Optional<ItemCost> costB, ItemStack result, int uses, int maxUses, int xp, float priceMultiplier, int demand) {
        throw Unimplemented.forMember("net/minecraft/world/item/trading/MerchantOffer.<init>:(Lnet/minecraft/world/item/trading/ItemCost;Ljava/util/Optional;Lnet/minecraft/world/item/ItemStack;IIIFI)V");
    }

    private MerchantOffer(MerchantOffer offer) {
        throw Unimplemented.forMember("net/minecraft/world/item/trading/MerchantOffer.<init>:(Lnet/minecraft/world/item/trading/MerchantOffer;)V");
    }

    public MerchantOffer copy() {
        throw Unimplemented.forMember("net/minecraft/world/item/trading/MerchantOffer.copy:()Lnet/minecraft/world/item/trading/MerchantOffer;");
    }

    public MerchantOffer() {
    }
}
