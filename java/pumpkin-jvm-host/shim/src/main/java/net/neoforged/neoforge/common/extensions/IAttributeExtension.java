package net.neoforged.neoforge.common.extensions;

import net.minecraft.network.chat.TextColor;

public interface IAttributeExtension {

    TextColor getMergedStyle(boolean isPositive);
}
