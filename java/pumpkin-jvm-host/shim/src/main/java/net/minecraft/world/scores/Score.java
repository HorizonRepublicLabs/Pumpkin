package net.minecraft.world.scores;

import java.util.Optional;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.numbers.NumberFormat;
import dev.pumpkin.shim.Unimplemented;

public class Score implements ReadOnlyScoreInfo {

    public Score() {
        throw Unimplemented.forMember("net/minecraft/world/scores/Score.<init>:()V");
    }

    public Score(Score.Packed packed) {
        throw Unimplemented.forMember("net/minecraft/world/scores/Score.<init>:(Lnet/minecraft/world/scores/Score$Packed;)V");
    }

    public int value() {
        throw Unimplemented.forMember("net/minecraft/world/scores/Score.value:()I");
    }

    public boolean isLocked() {
        throw Unimplemented.forMember("net/minecraft/world/scores/Score.isLocked:()Z");
    }

    public NumberFormat numberFormat() {
        throw Unimplemented.forMember("net/minecraft/world/scores/Score.numberFormat:()Lnet/minecraft/network/chat/numbers/NumberFormat;");
    }

    public record Packed(int value, boolean locked, Optional<Component> display, Optional<NumberFormat> numberFormat) {
    }
}
