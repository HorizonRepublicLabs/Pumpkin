package net.neoforged.neoforge.common.world;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.neoforged.neoforge.common.world.ModifiableStructureInfo.StructureInfo;

public interface StructureModifier {

    void modify(Holder<Structure> structure, Phase phase, StructureInfo.Builder builder);

    MapCodec<? extends StructureModifier> codec();

    enum Phase {

        BEFORE_EVERYTHING, ADD, REMOVE, MODIFY, AFTER_EVERYTHING
    }
}
