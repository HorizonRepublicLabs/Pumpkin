package net.minecraft.world.scores;

import java.util.List;
import java.util.Map;
import net.minecraft.world.level.saveddata.SavedData;
import dev.pumpkin.shim.Unimplemented;

public class ScoreboardSaveData extends SavedData {

    protected ScoreboardSaveData() {
        throw Unimplemented.forMember("net/minecraft/world/scores/ScoreboardSaveData.<init>:()V");
    }

    public ScoreboardSaveData(ScoreboardSaveData.Packed data) {
        throw Unimplemented.forMember("net/minecraft/world/scores/ScoreboardSaveData.<init>:(Lnet/minecraft/world/scores/ScoreboardSaveData$Packed;)V");
    }

    public record Packed(List<Objective.Packed> objectives, List<Scoreboard.PackedScore> scores, Map<DisplaySlot, String> displaySlots, List<PlayerTeam.Packed> teams) {
    }
}
