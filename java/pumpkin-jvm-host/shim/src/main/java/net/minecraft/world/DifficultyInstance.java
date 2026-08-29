package net.minecraft.world;

import dev.pumpkin.shim.Unimplemented;

public class DifficultyInstance {

    public DifficultyInstance(Difficulty base, long totalGameTime, long localGameTime, float moonBrightness) {
    }

    public Difficulty getDifficulty() {
        throw Unimplemented.forMember("net/minecraft/world/DifficultyInstance.getDifficulty:()Lnet/minecraft/world/Difficulty;");
    }

    public DifficultyInstance() {
    }
}
