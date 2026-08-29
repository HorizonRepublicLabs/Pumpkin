package net.minecraft.server.dialog.input;

import com.mojang.serialization.MapCodec;

public interface InputControl {

    MapCodec<? extends InputControl> mapCodec();
}
