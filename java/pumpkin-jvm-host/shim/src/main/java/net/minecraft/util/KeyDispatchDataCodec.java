package net.minecraft.util;

import com.mojang.serialization.MapCodec;

public record KeyDispatchDataCodec<A>(MapCodec<A> codec) {
}
