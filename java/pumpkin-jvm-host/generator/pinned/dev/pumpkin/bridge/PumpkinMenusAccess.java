package dev.pumpkin.bridge;

import net.minecraft.world.inventory.AbstractContainerMenu;

/** Package-front for {@link PumpkinMenus} so classes outside the package can reach the
 * pieces the player needs without widening the registry itself. */
public final class PumpkinMenusAccess {
    private PumpkinMenusAccess() {
    }

    public static int nextWindowId() {
        return PumpkinMenus.nextWindowId();
    }

    public static void register(int windowId, AbstractContainerMenu menu) {
        PumpkinMenus.register(windowId, menu);
    }

    public static String typeName(AbstractContainerMenu menu) {
        return PumpkinMenus.typeName(menu);
    }

    public static void bindPlayer(int windowId, PumpkinPlayer player) {
        PumpkinMenus.bindPlayer(windowId, player);
    }
}
