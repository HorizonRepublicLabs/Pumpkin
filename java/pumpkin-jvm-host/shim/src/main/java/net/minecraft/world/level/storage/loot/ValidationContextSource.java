package net.minecraft.world.level.storage.loot;

import net.minecraft.core.HolderGetter;
import net.minecraft.util.ProblemReporter;
import net.minecraft.util.context.ContextKeySet;
import dev.pumpkin.shim.Unimplemented;

public class ValidationContextSource {

    public ValidationContextSource(ProblemReporter reporter, HolderGetter.Provider lootData) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/loot/ValidationContextSource.<init>:(Lnet/minecraft/util/ProblemReporter;Lnet/minecraft/core/HolderGetter$Provider;)V");
    }

    public ValidationContext context(ContextKeySet params) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/loot/ValidationContextSource.context:(Lnet/minecraft/util/context/ContextKeySet;)Lnet/minecraft/world/level/storage/loot/ValidationContext;");
    }

    public ValidationContextSource() {
    }
}
