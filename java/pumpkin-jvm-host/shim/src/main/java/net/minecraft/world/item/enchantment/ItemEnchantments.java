package net.minecraft.world.item.enchantment;

import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap.Entry;
import java.util.Set;
import java.util.function.Consumer;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;
import dev.pumpkin.shim.Unimplemented;

public class ItemEnchantments implements TooltipProvider {

    public static final ItemEnchantments EMPTY = null;

    private ItemEnchantments(Object2IntOpenHashMap<Holder<Enchantment>> enchantments) {
        throw Unimplemented.forMember("net/minecraft/world/item/enchantment/ItemEnchantments.<init>:(Lit/unimi/dsi/fastutil/objects/Object2IntOpenHashMap;)V");
    }

    public int getLevel(Holder<Enchantment> enchantment) {
        throw Unimplemented.forMember("net/minecraft/world/item/enchantment/ItemEnchantments.getLevel:(Lnet/minecraft/core/Holder;)I");
    }

    public void addToTooltip(Item.TooltipContext context, Consumer<Component> consumer, TooltipFlag flag, DataComponentGetter components) {
        throw Unimplemented.forMember("net/minecraft/world/item/enchantment/ItemEnchantments.addToTooltip:(Lnet/minecraft/world/item/Item$TooltipContext;Ljava/util/function/Consumer;Lnet/minecraft/world/item/TooltipFlag;Lnet/minecraft/core/component/DataComponentGetter;)V");
    }

    public Set<Holder<Enchantment>> keySet() {
        throw Unimplemented.forMember("net/minecraft/world/item/enchantment/ItemEnchantments.keySet:()Ljava/util/Set;");
    }

    public Set<Entry<Holder<Enchantment>>> entrySet() {
        throw Unimplemented.forMember("net/minecraft/world/item/enchantment/ItemEnchantments.entrySet:()Ljava/util/Set;");
    }

    public int size() {
        throw Unimplemented.forMember("net/minecraft/world/item/enchantment/ItemEnchantments.size:()I");
    }

    public boolean isEmpty() {
        throw Unimplemented.forMember("net/minecraft/world/item/enchantment/ItemEnchantments.isEmpty:()Z");
    }

    public boolean equals(Object obj) {
        throw Unimplemented.forMember("net/minecraft/world/item/enchantment/ItemEnchantments.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/minecraft/world/item/enchantment/ItemEnchantments.hashCode:()I");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/world/item/enchantment/ItemEnchantments.toString:()Ljava/lang/String;");
    }

    public static class Mutable {

        public Mutable(ItemEnchantments enchantments) {
            throw Unimplemented.forMember("net/minecraft/world/item/enchantment/ItemEnchantments$Mutable.<init>:(Lnet/minecraft/world/item/enchantment/ItemEnchantments;)V");
        }

        public void set(Holder<Enchantment> enchantment, int level) {
            throw Unimplemented.forMember("net/minecraft/world/item/enchantment/ItemEnchantments$Mutable.set:(Lnet/minecraft/core/Holder;I)V");
        }

        public int getLevel(Holder<Enchantment> enchantment) {
            throw Unimplemented.forMember("net/minecraft/world/item/enchantment/ItemEnchantments$Mutable.getLevel:(Lnet/minecraft/core/Holder;)I");
        }

        public Set<Holder<Enchantment>> keySet() {
            throw Unimplemented.forMember("net/minecraft/world/item/enchantment/ItemEnchantments$Mutable.keySet:()Ljava/util/Set;");
        }

        public ItemEnchantments toImmutable() {
            throw Unimplemented.forMember("net/minecraft/world/item/enchantment/ItemEnchantments$Mutable.toImmutable:()Lnet/minecraft/world/item/enchantment/ItemEnchantments;");
        }

        public Mutable() {
        }
    }

    public ItemEnchantments() {
    }
}
