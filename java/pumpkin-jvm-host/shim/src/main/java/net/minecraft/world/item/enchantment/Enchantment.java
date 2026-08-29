package net.minecraft.world.item.enchantment;

import com.mojang.serialization.Codec;
import java.util.List;
import java.util.Optional;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import dev.pumpkin.shim.Stubs;
import dev.pumpkin.shim.Unimplemented;

public record Enchantment(Component description, Enchantment.EnchantmentDefinition definition, HolderSet<Enchantment> exclusiveSet, DataComponentMap effects) {

    public static final Codec<Holder<Enchantment>> CODEC = null;

    public static final StreamCodec<RegistryFriendlyByteBuf, Holder<Enchantment>> STREAM_CODEC = Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");

    public int getWeight() {
        throw Unimplemented.forMember("net/minecraft/world/item/enchantment/Enchantment.getWeight:()I");
    }

    public int getMaxLevel() {
        throw Unimplemented.forMember("net/minecraft/world/item/enchantment/Enchantment.getMaxLevel:()I");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/world/item/enchantment/Enchantment.toString:()Ljava/lang/String;");
    }

    public static boolean areCompatible(Holder<Enchantment> enchantment, Holder<Enchantment> other) {
        throw Unimplemented.forMember("net/minecraft/world/item/enchantment/Enchantment.areCompatible:(Lnet/minecraft/core/Holder;Lnet/minecraft/core/Holder;)Z");
    }

    public boolean canEnchant(ItemStack itemStack) {
        throw Unimplemented.forMember("net/minecraft/world/item/enchantment/Enchantment.canEnchant:(Lnet/minecraft/world/item/ItemStack;)Z");
    }

    public void tick(ServerLevel serverLevel, int enchantmentLevel, EnchantedItemInUse item, Entity entity) {
        throw Unimplemented.forMember("net/minecraft/world/item/enchantment/Enchantment.tick:(Lnet/minecraft/server/level/ServerLevel;ILnet/minecraft/world/item/enchantment/EnchantedItemInUse;Lnet/minecraft/world/entity/Entity;)V");
    }

    public static class Builder {

        public Builder(Enchantment.EnchantmentDefinition definition) {
            throw Unimplemented.forMember("net/minecraft/world/item/enchantment/Enchantment$Builder.<init>:(Lnet/minecraft/world/item/enchantment/Enchantment$EnchantmentDefinition;)V");
        }

        public Builder() {
        }
    }

    public record Cost(int base, int perLevelAboveFirst) {

        public int calculate(int level) {
            throw Unimplemented.forMember("net/minecraft/world/item/enchantment/Enchantment$Cost.calculate:(I)I");
        }
    }

    public record EnchantmentDefinition(HolderSet<Item> supportedItems, Optional<HolderSet<Item>> primaryItems, int weight, int maxLevel, Enchantment.Cost minCost, Enchantment.Cost maxCost, int anvilCost, List<EquipmentSlotGroup> slots) {
    }

    private interface FloatAction<T> {

        float apply(T effect, float value);
    }

    private interface GenericAction<T> {

        void apply(T effect);
    }
}
