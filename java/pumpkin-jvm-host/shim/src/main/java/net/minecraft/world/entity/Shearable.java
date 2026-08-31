package net.minecraft.world.entity;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.IShearable;

public interface Shearable extends IShearable {

    void shear(ServerLevel level, SoundSource soundSource, ItemStack tool);

    boolean readyForShearing();
}
