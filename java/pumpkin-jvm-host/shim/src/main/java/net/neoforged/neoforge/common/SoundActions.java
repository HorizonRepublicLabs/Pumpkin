package net.neoforged.neoforge.common;

import dev.pumpkin.shim.Unimplemented;

public final class SoundActions {

    protected SoundActions() {
    }

    public static final SoundAction BUCKET_FILL = SoundAction.get("bucket_fill");

    public static final SoundAction BUCKET_EMPTY = SoundAction.get("bucket_empty");

    // Pumpkin divergence: no throwing initializer -- the actions above are real.
}
