package net.neoforged.neoforge.common.extensions;

import net.minecraft.world.scores.Scoreboard;
import dev.pumpkin.shim.Unimplemented;

public interface ICommandSourceStackExtension {

    default Scoreboard getScoreboard() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/ICommandSourceStackExtension.getScoreboard:()Lnet/minecraft/world/scores/Scoreboard;");
    }
}
