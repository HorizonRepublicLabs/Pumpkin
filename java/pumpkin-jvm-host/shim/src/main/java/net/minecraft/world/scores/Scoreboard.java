package net.minecraft.world.scores;

public class Scoreboard {

    public record PackedScore(String owner, String objective, Score.Packed score) {
    }

    public Scoreboard() {
    }
}
