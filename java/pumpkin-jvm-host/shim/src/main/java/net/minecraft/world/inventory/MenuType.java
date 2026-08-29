package net.minecraft.world.inventory;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.flag.FeatureElement;
import net.minecraft.world.flag.FeatureFlagSet;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import dev.pumpkin.shim.Unimplemented;

public class MenuType<T extends AbstractContainerMenu> implements FeatureElement, IMenuTypeExtension<T> {

    private static <T extends AbstractContainerMenu> MenuType<T> register(String name, MenuType.MenuSupplier<T> constructor) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/MenuType.register:(Ljava/lang/String;Lnet/minecraft/world/inventory/MenuType$MenuSupplier;)Lnet/minecraft/world/inventory/MenuType;");
    }

    public MenuType(MenuType.MenuSupplier<T> constructor, FeatureFlagSet requiredFeatures) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/MenuType.<init>:(Lnet/minecraft/world/inventory/MenuType$MenuSupplier;Lnet/minecraft/world/flag/FeatureFlagSet;)V");
    }

    public T create(int containerId, Inventory inventory) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/MenuType.create:(ILnet/minecraft/world/entity/player/Inventory;)Lnet/minecraft/world/inventory/AbstractContainerMenu;");
    }

    public T create(int windowId, Inventory playerInv, net.minecraft.network.RegistryFriendlyByteBuf extraData) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/MenuType.create:(ILnet/minecraft/world/entity/player/Inventory;Lnet/minecraft/network/RegistryFriendlyByteBuf;)Lnet/minecraft/world/inventory/AbstractContainerMenu;");
    }

    public FeatureFlagSet requiredFeatures() {
        throw Unimplemented.forMember("net/minecraft/world/inventory/MenuType.requiredFeatures:()Lnet/minecraft/world/flag/FeatureFlagSet;");
    }

    public interface MenuSupplier<T extends AbstractContainerMenu> {

        T create(int containerId, Inventory inventory);
    }

    protected MenuType() {
    }
}
