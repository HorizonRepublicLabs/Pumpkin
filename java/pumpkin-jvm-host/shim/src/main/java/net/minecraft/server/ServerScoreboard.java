package net.minecraft.server;

import net.minecraft.world.scores.DisplaySlot;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.scores.PlayerTeam;
import net.minecraft.world.scores.Score;
import net.minecraft.world.scores.ScoreHolder;
import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.ScoreboardSaveData;
import dev.pumpkin.shim.Unimplemented;

public class ServerScoreboard extends Scoreboard {

    public ServerScoreboard(MinecraftServer server) {
        throw Unimplemented.forMember("net/minecraft/server/ServerScoreboard.<init>:(Lnet/minecraft/server/MinecraftServer;)V");
    }

    public void load(ScoreboardSaveData.Packed data) {
        throw Unimplemented.forMember("net/minecraft/server/ServerScoreboard.load:(Lnet/minecraft/world/scores/ScoreboardSaveData$Packed;)V");
    }

    protected void onScoreChanged(ScoreHolder owner, Objective objective, Score score) {
        throw Unimplemented.forMember("net/minecraft/server/ServerScoreboard.onScoreChanged:(Lnet/minecraft/world/scores/ScoreHolder;Lnet/minecraft/world/scores/Objective;Lnet/minecraft/world/scores/Score;)V");
    }

    protected void onScoreLockChanged(ScoreHolder owner, Objective objective) {
        throw Unimplemented.forMember("net/minecraft/server/ServerScoreboard.onScoreLockChanged:(Lnet/minecraft/world/scores/ScoreHolder;Lnet/minecraft/world/scores/Objective;)V");
    }

    public void onPlayerRemoved(ScoreHolder player) {
        throw Unimplemented.forMember("net/minecraft/server/ServerScoreboard.onPlayerRemoved:(Lnet/minecraft/world/scores/ScoreHolder;)V");
    }

    public void onPlayerScoreRemoved(ScoreHolder player, Objective objective) {
        throw Unimplemented.forMember("net/minecraft/server/ServerScoreboard.onPlayerScoreRemoved:(Lnet/minecraft/world/scores/ScoreHolder;Lnet/minecraft/world/scores/Objective;)V");
    }

    public void setDisplayObjective(DisplaySlot slot, Objective objective) {
        throw Unimplemented.forMember("net/minecraft/server/ServerScoreboard.setDisplayObjective:(Lnet/minecraft/world/scores/DisplaySlot;Lnet/minecraft/world/scores/Objective;)V");
    }

    public boolean addPlayerToTeam(String player, PlayerTeam team) {
        throw Unimplemented.forMember("net/minecraft/server/ServerScoreboard.addPlayerToTeam:(Ljava/lang/String;Lnet/minecraft/world/scores/PlayerTeam;)Z");
    }

    public void removePlayerFromTeam(String player, PlayerTeam team) {
        throw Unimplemented.forMember("net/minecraft/server/ServerScoreboard.removePlayerFromTeam:(Ljava/lang/String;Lnet/minecraft/world/scores/PlayerTeam;)V");
    }

    public void onObjectiveAdded(Objective objective) {
        throw Unimplemented.forMember("net/minecraft/server/ServerScoreboard.onObjectiveAdded:(Lnet/minecraft/world/scores/Objective;)V");
    }

    public void onObjectiveChanged(Objective objective) {
        throw Unimplemented.forMember("net/minecraft/server/ServerScoreboard.onObjectiveChanged:(Lnet/minecraft/world/scores/Objective;)V");
    }

    public void onObjectiveRemoved(Objective objective) {
        throw Unimplemented.forMember("net/minecraft/server/ServerScoreboard.onObjectiveRemoved:(Lnet/minecraft/world/scores/Objective;)V");
    }

    public void onTeamAdded(PlayerTeam team) {
        throw Unimplemented.forMember("net/minecraft/server/ServerScoreboard.onTeamAdded:(Lnet/minecraft/world/scores/PlayerTeam;)V");
    }

    public void onTeamChanged(PlayerTeam team) {
        throw Unimplemented.forMember("net/minecraft/server/ServerScoreboard.onTeamChanged:(Lnet/minecraft/world/scores/PlayerTeam;)V");
    }

    public void onTeamRemoved(PlayerTeam team) {
        throw Unimplemented.forMember("net/minecraft/server/ServerScoreboard.onTeamRemoved:(Lnet/minecraft/world/scores/PlayerTeam;)V");
    }

    protected ServerScoreboard() {
    }
}
