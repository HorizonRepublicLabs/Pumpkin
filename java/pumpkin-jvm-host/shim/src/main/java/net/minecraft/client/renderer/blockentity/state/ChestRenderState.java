package net.minecraft.client.renderer.blockentity.state;

public class ChestRenderState extends BlockEntityRenderState {

    public enum ChestMaterialType {

        ENDER_CHEST,
        CHRISTMAS,
        TRAPPED,
        COPPER_UNAFFECTED,
        COPPER_EXPOSED,
        COPPER_WEATHERED,
        COPPER_OXIDIZED,
        REGULAR
    }

    public ChestRenderState() {
    }
}
