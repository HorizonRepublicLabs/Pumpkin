package net.minecraft.world.entity.player;

import dev.pumpkin.shim.Unimplemented;

public class Abilities {

    public boolean invulnerable;

    public boolean flying;

    public boolean instabuild;

    public void apply(Abilities.Packed packed) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/Abilities.apply:(Lnet/minecraft/world/entity/player/Abilities$Packed;)V");
    }

    public record Packed(boolean invulnerable, boolean flying, boolean mayFly, boolean instabuild, boolean mayBuild, float flyingSpeed, float walkingSpeed) {
    }

    public Abilities() {
    }
}
