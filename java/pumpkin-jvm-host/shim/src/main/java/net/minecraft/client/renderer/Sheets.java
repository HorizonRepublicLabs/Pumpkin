package net.minecraft.client.renderer;

import net.minecraft.client.renderer.rendertype.RenderType;
import dev.pumpkin.shim.Unimplemented;

public class Sheets {

    public static final SpriteMapper BLOCKS_MAPPER = null;

    public static final SpriteMapper SHIELD_MAPPER = null;

    public static RenderType cutoutBlockItemSheet() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/Sheets.cutoutBlockItemSheet:()Lnet/minecraft/client/renderer/rendertype/RenderType;");
    }

    public static RenderType translucentBlockItemSheet() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/Sheets.translucentBlockItemSheet:()Lnet/minecraft/client/renderer/rendertype/RenderType;");
    }

    public Sheets() {
    }

    static {
        if (true) {
            throw Unimplemented.forMember("net/minecraft/client/renderer/Sheets");
        }
    }
}
