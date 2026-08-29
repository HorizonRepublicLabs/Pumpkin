package com.mojang.blaze3d.shaders;

import dev.pumpkin.shim.Unimplemented;

public enum ShaderType {

    VERTEX, FRAGMENT;

    public String getName() {
        throw Unimplemented.forMember("com/mojang/blaze3d/shaders/ShaderType.getName:()Ljava/lang/String;");
    }
}
