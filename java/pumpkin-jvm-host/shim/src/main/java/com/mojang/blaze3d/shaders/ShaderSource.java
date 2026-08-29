package com.mojang.blaze3d.shaders;

import net.minecraft.resources.Identifier;

public interface ShaderSource {

    String get(Identifier id, ShaderType type);
}
