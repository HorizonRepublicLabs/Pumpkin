package net.minecraft.world.scores;

import java.util.Collection;
import java.util.Optional;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.util.StringRepresentable;
import dev.pumpkin.shim.Unimplemented;

public abstract class Team {

    public abstract String getName();

    public abstract MutableComponent getFormattedName(Component teamMemberName);

    public abstract boolean canSeeFriendlyInvisibles();

    public abstract boolean isAllowFriendlyFire();

    public abstract Team.Visibility getNameTagVisibility();

    public abstract Optional<TeamColor> getColor();

    public abstract Collection<String> getPlayers();

    public abstract Team.Visibility getDeathMessageVisibility();

    public abstract Team.CollisionRule getCollisionRule();

    public enum CollisionRule implements StringRepresentable {

        ALWAYS, NEVER, PUSH_OTHER_TEAMS, PUSH_OWN_TEAM;

        public Component getDisplayName() {
            throw Unimplemented.forMember("net/minecraft/world/scores/Team$CollisionRule.getDisplayName:()Lnet/minecraft/network/chat/Component;");
        }

        public String getSerializedName() {
            throw Unimplemented.forMember("net/minecraft/world/scores/Team$CollisionRule.getSerializedName:()Ljava/lang/String;");
        }
    }

    public enum Visibility implements StringRepresentable {

        ALWAYS, NEVER, HIDE_FOR_OTHER_TEAMS, HIDE_FOR_OWN_TEAM;

        public Component getDisplayName() {
            throw Unimplemented.forMember("net/minecraft/world/scores/Team$Visibility.getDisplayName:()Lnet/minecraft/network/chat/Component;");
        }

        public String getSerializedName() {
            throw Unimplemented.forMember("net/minecraft/world/scores/Team$Visibility.getSerializedName:()Ljava/lang/String;");
        }
    }

    protected Team() {
    }
}
