package net.minecraft.client.gui.render;

import com.mojang.blaze3d.textures.GpuSampler;
import com.mojang.blaze3d.textures.GpuTextureView;
import dev.pumpkin.shim.Unimplemented;

public record TextureSetup(GpuTextureView texure0, GpuTextureView texure1, GpuTextureView texure2, GpuSampler sampler0, GpuSampler sampler1, GpuSampler sampler2) {

    public static TextureSetup singleTexture(GpuTextureView texture, GpuSampler sampler) {
        throw Unimplemented.forMember("net/minecraft/client/gui/render/TextureSetup.singleTexture:(Lcom/mojang/blaze3d/textures/GpuTextureView;Lcom/mojang/blaze3d/textures/GpuSampler;)Lnet/minecraft/client/gui/render/TextureSetup;");
    }

    public static TextureSetup noTexture() {
        throw Unimplemented.forMember("net/minecraft/client/gui/render/TextureSetup.noTexture:()Lnet/minecraft/client/gui/render/TextureSetup;");
    }
}
