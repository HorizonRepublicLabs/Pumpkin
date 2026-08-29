package net.minecraft.server.dialog.body;

import com.mojang.serialization.MapCodec;

public interface DialogBody {

    MapCodec<? extends DialogBody> mapCodec();
}
