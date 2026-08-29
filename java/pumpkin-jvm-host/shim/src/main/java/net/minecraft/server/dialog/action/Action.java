package net.minecraft.server.dialog.action;

import com.mojang.serialization.MapCodec;
import java.util.Map;
import java.util.Optional;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.ClickEvent;

public interface Action {

    MapCodec<? extends Action> codec();

    Optional<ClickEvent> createAction(Map<String, Action.ValueGetter> parameters);

    interface ValueGetter {

        String asTemplateSubstitution();

        Tag asTag();
    }
}
