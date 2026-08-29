package net.minecraft.world.item.trading;

import java.util.ArrayList;
import java.util.Collection;
import net.minecraft.world.item.ItemStack;
import dev.pumpkin.shim.Unimplemented;

public class MerchantOffers extends ArrayList<MerchantOffer> {

    public MerchantOffers() {
        throw Unimplemented.forMember("net/minecraft/world/item/trading/MerchantOffers.<init>:()V");
    }

    private MerchantOffers(int initialCapacity) {
        throw Unimplemented.forMember("net/minecraft/world/item/trading/MerchantOffers.<init>:(I)V");
    }

    private MerchantOffers(Collection<MerchantOffer> offers) {
        throw Unimplemented.forMember("net/minecraft/world/item/trading/MerchantOffers.<init>:(Ljava/util/Collection;)V");
    }

    public MerchantOffer getRecipeFor(ItemStack buyA, ItemStack buyB, int selectionHint) {
        throw Unimplemented.forMember("net/minecraft/world/item/trading/MerchantOffers.getRecipeFor:(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemStack;I)Lnet/minecraft/world/item/trading/MerchantOffer;");
    }

    public MerchantOffers copy() {
        throw Unimplemented.forMember("net/minecraft/world/item/trading/MerchantOffers.copy:()Lnet/minecraft/world/item/trading/MerchantOffers;");
    }
}
