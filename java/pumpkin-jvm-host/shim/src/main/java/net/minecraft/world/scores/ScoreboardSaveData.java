package net.minecraft.world.scores;

import java.util.List;
import java.util.Map;
import net.minecraft.world.level.saveddata.SavedData;

public class ScoreboardSaveData extends SavedData {

    protected ScoreboardSaveData() {
    }

    public ScoreboardSaveData(ScoreboardSaveData.Packed data) {
    }

    public record Packed(List<Objective.Packed> objectives, List<Scoreboard.PackedScore> scores, Map<DisplaySlot, String> displaySlots, List<PlayerTeam.Packed> teams) {
    }
}
