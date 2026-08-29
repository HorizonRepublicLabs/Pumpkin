package net.minecraft.world.scores;

import java.util.Optional;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.numbers.NumberFormat;
import net.minecraft.world.scores.criteria.ObjectiveCriteria;
import dev.pumpkin.shim.Unimplemented;

public class Objective {

    public Objective(Scoreboard scoreboard, String name, ObjectiveCriteria criteria, Component displayName, ObjectiveCriteria.RenderType renderType, boolean displayAutoUpdate, NumberFormat numberFormat) {
        throw Unimplemented.forMember("net/minecraft/world/scores/Objective.<init>:(Lnet/minecraft/world/scores/Scoreboard;Ljava/lang/String;Lnet/minecraft/world/scores/criteria/ObjectiveCriteria;Lnet/minecraft/network/chat/Component;Lnet/minecraft/world/scores/criteria/ObjectiveCriteria$RenderType;ZLnet/minecraft/network/chat/numbers/NumberFormat;)V");
    }

    public Scoreboard getScoreboard() {
        throw Unimplemented.forMember("net/minecraft/world/scores/Objective.getScoreboard:()Lnet/minecraft/world/scores/Scoreboard;");
    }

    public String getName() {
        throw Unimplemented.forMember("net/minecraft/world/scores/Objective.getName:()Ljava/lang/String;");
    }

    public Component getDisplayName() {
        throw Unimplemented.forMember("net/minecraft/world/scores/Objective.getDisplayName:()Lnet/minecraft/network/chat/Component;");
    }

    public NumberFormat numberFormat() {
        throw Unimplemented.forMember("net/minecraft/world/scores/Objective.numberFormat:()Lnet/minecraft/network/chat/numbers/NumberFormat;");
    }

    public record Packed(String name, ObjectiveCriteria criteria, Component displayName, ObjectiveCriteria.RenderType renderType, boolean displayAutoUpdate, Optional<NumberFormat> numberFormat) {
    }

    protected Objective() {
    }
}
