package net.minecraft.world.item;

import java.util.Optional;
import net.minecraft.core.Holder;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import dev.pumpkin.shim.Unimplemented;

public class SpawnEggItem extends Item {

    public SpawnEggItem(Item.Properties properties) {
    }

    public InteractionResult useOn(UseOnContext context) {
        throw Unimplemented.forMember("net/minecraft/world/item/SpawnEggItem.useOn:(Lnet/minecraft/world/item/context/UseOnContext;)Lnet/minecraft/world/InteractionResult;");
    }

    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        throw Unimplemented.forMember("net/minecraft/world/item/SpawnEggItem.use:(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/world/InteractionResult;");
    }

    public static Optional<Holder<Item>> byId(EntityType<?> type) {
        throw Unimplemented.forMember("net/minecraft/world/item/SpawnEggItem.byId:(Lnet/minecraft/world/entity/EntityType;)Ljava/util/Optional;");
    }

    public static EntityType<?> getType(ItemStack itemStack) {
        throw Unimplemented.forMember("net/minecraft/world/item/SpawnEggItem.getType:(Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/world/entity/EntityType;");
    }

    public boolean shouldPrintOpWarning(ItemStack stack, Player player) {
        throw Unimplemented.forMember("net/minecraft/world/item/SpawnEggItem.shouldPrintOpWarning:(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/player/Player;)Z");
    }

    public SpawnEggItem() {
    }
}
