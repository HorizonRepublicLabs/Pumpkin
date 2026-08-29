package net.minecraft.advancements;

import java.util.Map;
import dev.pumpkin.shim.Unimplemented;

public class AdvancementProgress implements Comparable<AdvancementProgress> {

    private AdvancementProgress(Map<String, CriterionProgress> criteria) {
        throw Unimplemented.forMember("net/minecraft/advancements/AdvancementProgress.<init>:(Ljava/util/Map;)V");
    }

    public AdvancementProgress() {
        throw Unimplemented.forMember("net/minecraft/advancements/AdvancementProgress.<init>:()V");
    }

    public void update(AdvancementRequirements requirements) {
        throw Unimplemented.forMember("net/minecraft/advancements/AdvancementProgress.update:(Lnet/minecraft/advancements/AdvancementRequirements;)V");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/advancements/AdvancementProgress.toString:()Ljava/lang/String;");
    }

    public int compareTo(AdvancementProgress o) {
        throw Unimplemented.forMember("net/minecraft/advancements/AdvancementProgress.compareTo:(Lnet/minecraft/advancements/AdvancementProgress;)I");
    }
}
