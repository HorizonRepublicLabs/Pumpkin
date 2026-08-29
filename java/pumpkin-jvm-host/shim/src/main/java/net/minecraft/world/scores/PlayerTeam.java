package net.minecraft.world.scores;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import dev.pumpkin.shim.Unimplemented;

public class PlayerTeam extends Team {

    public PlayerTeam(Scoreboard scoreboard, String name) {
        throw Unimplemented.forMember("net/minecraft/world/scores/PlayerTeam.<init>:(Lnet/minecraft/world/scores/Scoreboard;Ljava/lang/String;)V");
    }

    public Scoreboard getScoreboard() {
        throw Unimplemented.forMember("net/minecraft/world/scores/PlayerTeam.getScoreboard:()Lnet/minecraft/world/scores/Scoreboard;");
    }

    public String getName() {
        throw Unimplemented.forMember("net/minecraft/world/scores/PlayerTeam.getName:()Ljava/lang/String;");
    }

    public Component getDisplayName() {
        throw Unimplemented.forMember("net/minecraft/world/scores/PlayerTeam.getDisplayName:()Lnet/minecraft/network/chat/Component;");
    }

    public Collection<String> getPlayers() {
        throw Unimplemented.forMember("net/minecraft/world/scores/PlayerTeam.getPlayers:()Ljava/util/Collection;");
    }

    public MutableComponent getFormattedName(Component teamMemberName) {
        throw Unimplemented.forMember("net/minecraft/world/scores/PlayerTeam.getFormattedName:(Lnet/minecraft/network/chat/Component;)Lnet/minecraft/network/chat/MutableComponent;");
    }

    public boolean isAllowFriendlyFire() {
        throw Unimplemented.forMember("net/minecraft/world/scores/PlayerTeam.isAllowFriendlyFire:()Z");
    }

    public boolean canSeeFriendlyInvisibles() {
        throw Unimplemented.forMember("net/minecraft/world/scores/PlayerTeam.canSeeFriendlyInvisibles:()Z");
    }

    public Team.Visibility getNameTagVisibility() {
        throw Unimplemented.forMember("net/minecraft/world/scores/PlayerTeam.getNameTagVisibility:()Lnet/minecraft/world/scores/Team$Visibility;");
    }

    public Team.Visibility getDeathMessageVisibility() {
        throw Unimplemented.forMember("net/minecraft/world/scores/PlayerTeam.getDeathMessageVisibility:()Lnet/minecraft/world/scores/Team$Visibility;");
    }

    public Team.CollisionRule getCollisionRule() {
        throw Unimplemented.forMember("net/minecraft/world/scores/PlayerTeam.getCollisionRule:()Lnet/minecraft/world/scores/Team$CollisionRule;");
    }

    public void setColor(Optional<TeamColor> color) {
        throw Unimplemented.forMember("net/minecraft/world/scores/PlayerTeam.setColor:(Ljava/util/Optional;)V");
    }

    public Optional<TeamColor> getColor() {
        throw Unimplemented.forMember("net/minecraft/world/scores/PlayerTeam.getColor:()Ljava/util/Optional;");
    }

    @Retention(RetentionPolicy.CLASS)
    @Target(ElementType.TYPE_USE)
    public @interface OptionFlags {
    }

    public record Packed(String name, Optional<Component> displayName, Optional<TeamColor> color, boolean allowFriendlyFire, boolean seeFriendlyInvisibles, Component memberNamePrefix, Component memberNameSuffix, Team.Visibility nameTagVisibility, Team.Visibility deathMessageVisibility, Team.CollisionRule collisionRule, List<String> players) {
    }

    public PlayerTeam() {
    }
}
