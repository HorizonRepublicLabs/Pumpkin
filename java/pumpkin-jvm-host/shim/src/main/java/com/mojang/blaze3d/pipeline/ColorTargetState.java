package com.mojang.blaze3d.pipeline;

import com.mojang.blaze3d.GpuFormat;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Optional;

public record ColorTargetState(Optional<BlendFunction> blendFunction, GpuFormat format, int writeMask) {

    public ColorTargetState(BlendFunction blendFunction) {
        this((Optional<BlendFunction>) null, (GpuFormat) null, (int) 0);
    }

    @Retention(RetentionPolicy.CLASS)
    @Target(ElementType.TYPE_USE)
    public @interface WriteMask {
    }
}
