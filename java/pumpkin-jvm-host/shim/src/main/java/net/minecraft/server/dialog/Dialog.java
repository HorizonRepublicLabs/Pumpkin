package net.minecraft.server.dialog;

import com.mojang.serialization.MapCodec;
import java.util.Optional;
import net.minecraft.server.dialog.action.Action;

public interface Dialog {

    CommonDialogData common();

    MapCodec<? extends Dialog> codec();

    Optional<Action> onCancel();
}
